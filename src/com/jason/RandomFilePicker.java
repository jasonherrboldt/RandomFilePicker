package com.jason;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Opens a file at random in a given directory.
 *
 * Created by Jason Herrboldt (intothefuture@gmail.com) on 6/6/2016.
 */
public class RandomFilePicker {

    private File directory;
    private int maxLength;
    private String extension;
    private boolean recursive;
    private boolean printOnly;
    private Random randomGenerator;
    private List<File> discoveredFiles;
    String filenamePattern;
    Pattern pattern;

    /**
     * Public constructor.
     *
     * @param directory   The directory to explore
     * @param maxLength   The max number of files the program is allowed to discover.
     * @param extension   Limit the search to files of this type.
     * @param recursive   Whether the search should be recursive.
     * @param printOnly   Whether the search should only print results.
     *
     */
    public RandomFilePicker(File directory, int maxLength, String extension, boolean recursive, boolean printOnly) {
        this.directory = directory;

        // Debug:
//        this.directory= new File("/Users/jasonherrboldt/Music/iTunes/iTunes Media/Music");
//        if (!this.directory.exists()) {
//            throw new IllegalArgumentException("Unable to access directory. Please make sure the root directory is the first argument.");
//        }

        this.maxLength = maxLength;
        this.extension = extension;
        this.recursive = recursive;
        this.printOnly = printOnly;
        randomGenerator = new Random();
        discoveredFiles = new ArrayList<>();
        if(OSDetector.isWindows7() || OSDetector.isMac()) {
            // Making a guess that these special characters are allowed for W7 and Mac directory names: _ ' ( ) - ^ ,
            // (Includes whitespace character.)
            filenamePattern = "^[a-zA-Z0-9_\'()-^,\\s]+\\.[a-zA-Z0-9]+$";
        }
        // todo: Need to implement pattern for valid Mac filenames.
        if(filenamePattern != null) {
            pattern = Pattern.compile(filenamePattern);
        }
    }

    /**
     * Print the requested execution details to the console.
     */
    private void printUserStats() {
        System.out.println("\nRuntime details:\n");
        String directoryName = this.directory.getName();
        if(directoryName.equals(".")) {
            directoryName = "(the current directory)";
        } else {
            File directory = new File(directoryName);
            directoryName = directory.getAbsolutePath();
        }
        String os = "";
        if(OSDetector.isWindows7()) {
            os = "Windows 7";
        } else if (OSDetector.isMac()) {
            os = "Mac";
        } else {
            os = "Unknown";
        }
        System.out.println("User's OS: " + os);
        System.out.println("Root directory: " + directoryName);
        System.out.println("Max length: " + maxLength);
        System.out.println("Extension: " + extension);
        System.out.println("Recursive: " + recursive);
        System.out.println("Print only: " + printOnly + "\n");
    }

    /**
     * Chain of commands triggered from Main.
     */
    public void run() {
        printUserStats();
        discoverFiles();
        if(printOnly) {
            printDiscoveredFiles();
        } else {
            printDiscoveredFiles(); // Remove (for debug).
            openFile(getRandomFile());
        }
    }

    /**
     * Open a file.
     *
     * @param file The file to open.
     */
    private void openFile(File file) {
        // System.out.println("oh hai from openFile. I'm supposed to open " + file.getName());
        if(OSDetector.isWindows7()) {
            // System.out.println("oh hai windows");
            try {
                Runtime.getRuntime().exec(new String[]{"rundll32", "url.dll,FileProtocolHandler", file.getAbsolutePath()});
            } catch (IOException e) {
                System.out.println("Error opening file " + file.getName());
            }
        } else if (OSDetector.isMac()) {
            try {
                Runtime.getRuntime().exec(new String[]{"/usr/bin/open", file.getAbsolutePath()});
            } catch (IOException e) {
                System.out.println("Error opening file " + file.getName());
            }
        }
    }

    /**
     * Pick and return a file at random from discoveredFiles.
     */
    private File getRandomFile() {
        int index = randomGenerator.nextInt(discoveredFiles.size());
        return discoveredFiles.get(index);
    }

    /**
     * Print the discovered files to the console.
     */
    private void printDiscoveredFiles() {
        int printCount = 1;
        System.out.println("\nPrinting discovered files:\n");
        for (File file : discoveredFiles) {
            System.out.println(printCount + ": " + file.toString());
            printCount++;
        }
    }

    /**
     * Discover files in the provided directory.
     */
    private void discoverFiles() {
        if(!recursive) {
            File[] files = directory.listFiles();
            for(File file : files) {
                if(file != null) {
                    if(matchesRequestedExtension(file.getName())) {
                        if(discoveredFiles.size() >= maxLength) {
                            break;
                        }
                        if(file.isFile() && filenameMatchesPattern(file.getName())) {
                            discoveredFiles.add(file);
                        }
                    }
                }
            }
        } else {
            discoverFiles(directory);
        }
    }

    /**
     * Discover files in a recursive directory.
     *
     * @param directory The directory to explore.
     */
    private void discoverFiles(File directory) {
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                discoverFiles(file);
            } else {
                if(matchesRequestedExtension(file.getName())) {
                    if(discoveredFiles.size() >= maxLength) {
                        break;
                    }
                    if(filenameMatchesPattern((file.getName()))) {
                        discoveredFiles.add(file);
                    }
                }
            }
        }
    }

    /**
     * @param fileName   Name of file to analyze.
     * @return           True if file extension matches user's specification, false otherwise.
     */
    private boolean matchesRequestedExtension(String fileName) {
        if(extension.equals("")) {
            // User did not specify a file extension.
            // (Yes, I know this conditional doesn't belong in here. Don't care. Makes everything easier.)
            return true;
        } else {
            String[] tokens = fileName.split("\\.");
            if(tokens.length > 0) {
                if(tokens[tokens.length - 1].equals(extension)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Forces the program to only open files of the type *.*
     *
     * @param filename  The file to inspect.
     * @return          True if filename matches the specified pattern, false otherwise.
     */
    private boolean filenameMatchesPattern(String filename) {
        Matcher m = pattern.matcher(filename);
        return m.find();
    }
}