package com.jason;

import java.io.*;
import java.util.*;

/**
 * Opens a file at random in a given directory.
 *
 * Created by Jason Herrboldt (intothefuture@gmail.com) on 6/6/2016.
 */
public class RandomFilePicker {

    private File directory;
    private int maxLength;
    private List<String> extensions;
    private boolean recursive;
    private boolean printOnly;
    private Random randomGenerator;
    private List<File> discoveredFiles;
    private Set<String> extensionsFound;

    /**
     * Public constructor.
     *
     * @param directory   The directory to explore
     * @param maxLength   The max number of files the program is allowed to discover.
     * @param extensions  Limit the search to files of this type.
     * @param recursive   Whether the search should be recursive.
     * @param printOnly   Whether the search should only print results.
     *
     */
    public RandomFilePicker(File directory, int maxLength, String extensions, boolean recursive, boolean printOnly) {
        this.directory = directory;
        this.maxLength = maxLength;
        this.extensions = new ArrayList<>();
        String[] tokens = extensions.split("\\s");
        if(tokens.length > 0) {
            for(String t : tokens) {
                this.extensions.add(t);
            }
        }
        this.recursive = recursive;
        this.printOnly = printOnly;
        randomGenerator = new Random();
        discoveredFiles = new ArrayList<>();
        extensionsFound = new TreeSet<>();
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
            directoryName = this.directory.getAbsolutePath();
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
        if(printOnly) {
            System.out.println("Extension limiter(s): (N/A -- all extensions discovered when using -p option.)");
        } else {
            System.out.println("Extension limiter(s): " + extensions);
        }
        System.out.println("Recursive: " + recursive);
        System.out.println("Print only: " + printOnly + "\n");
    }

    /**
     * Chain of commands triggered from Main.
     */
    public void run() {
        discoverFiles();
        if(printOnly) {
            printDiscoveredFiles();
            printDiscoveredExtensions();
        } else {
            // printDiscoveredFiles(); // Remove (for debug).
            openFile(getRandomFile());
        }
        printUserStats();
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
     * Print the discovered extensions to the console.
     */
    private void printDiscoveredExtensions() {
        System.out.println("\nThe following file type extensions were discovered:\n");
        for(String e : extensionsFound) {
            System.out.println("." + e);
        }
        System.out.println("");
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
                if(discoveredFiles.size() >= maxLength) {
                    break;
                }
                if(printOnly) {
                    if(file.isFile()) {
                        discoveredFiles.add(file);
                        recordExtension(file.getName());
                    }
                } else {
                    if(matchesRequestedExtensions(file.getName())) {
                        if(file.isFile()) {
                            discoveredFiles.add(file);
                            recordExtension(file.getName());
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
            if(discoveredFiles.size() >= maxLength) {
                break;
            }
            if (file.isDirectory()) {
                discoverFiles(file);
            } else {
                if(printOnly) {
                    discoveredFiles.add(file);
                    recordExtension(file.getName());
                } else {
                    if(matchesRequestedExtensions(file.getName())) {
                        discoveredFiles.add(file);
                        recordExtension(file.getName());
                    }
                }
            }
        }
    }

    /**
     * Add the discovered extensions to the extensionsFound map.
     *
     * @param fileName The name of the file to inspect.
     */
    private void recordExtension(String fileName) {
        String[] tokens = fileName.split("\\.");
        if(tokens.length > 0) {
            String extension = tokens[tokens.length - 1];
            extensionsFound.add(extension);
        }
    }

    /**
     * @param fileName   Name of file to analyze.
     * @return           True if file extensions matches user's specification, false otherwise.
     */
    private boolean matchesRequestedExtensions(String fileName) {
        String[] tokens = fileName.split("\\.");
        String thisExtension = tokens[tokens.length - 1];
        return extensions.contains(thisExtension);

//        if(extensions.equals("")) {
//            // User did not specify a file extensions.
//            return true;
//        } else {
//            String[] tokens = fileName.split("\\.");
//            if(tokens.length > 0) {
//                if(tokens[tokens.length - 1].equals(extensions)) {
//                    return true;
//                }
//            }
//            return false;
//        }
    }
}