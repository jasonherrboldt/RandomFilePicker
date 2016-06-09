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
    private boolean searchOnly;
    private boolean recursive;
    private int maxLength;
    private Random randomGenerator;
    private List<File> discoveredFiles;
    String filenamePattern;
    Pattern pattern;

    /**
     * Public constructor.
     *
     * @param directory   The directory to explore
     * @param searchOnly  Whether the search should only print results.
     * @param recursive   Whether the search should be recursive.
     * @param maxLength   The max number of files the program is allowed to discover.
     *
     */
    public RandomFilePicker(File directory, boolean searchOnly, boolean recursive, int maxLength) {
        this.directory = directory;
        this.searchOnly = searchOnly;
        this.recursive = recursive;
        this.maxLength = maxLength;
        randomGenerator = new Random();
        discoveredFiles = new ArrayList<>();
        if(OSDetector.isWindows7()) {
            // Allowed special characters (_ ' ( ) - ^ ,) are arbitrary guesses for allowed W7 directory names.
            filenamePattern = "^[a-zA-Z0-9_\'()-^,]+\\.[a-zA-Z0-9]+$";
        }
        // todo: Need to implement pattern for valid Mac filenames.
        pattern = Pattern.compile(filenamePattern);

        String directoryName = this.directory.getName();
        if(directoryName.equals(".")) {
            directoryName = "(the current directory)";
        }

        // Remove (for debug):
        System.out.println("\nArguments received: root directory: " + directoryName + ", recursive: " + recursive +
                ", maxLength: " + maxLength + ", searchOnly: " + searchOnly + ".\n");
    }

    /**
     * Chain of commands triggered from Main.
     */
    public void run() {
        discoverFiles();
        if(searchOnly) {
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
        System.out.println("oh hai from openFile. I'm supposed to open " + file.getName());
        if(OSDetector.isWindows7()) {
            // System.out.println("oh hai windows");
            try {
                Runtime.getRuntime().exec(new String[]{"rundll32", "url.dll,FileProtocolHandler", file.getAbsolutePath()});
            } catch (IOException e) {
                System.out.println("Error opening file " + file.getName());
            }
        } else if (OSDetector.isMac()) {
            System.out.println("oh hai mac");
            System.out.println("Program currently only supports Windows 7. More OS versions coming soon.");
//            try {
//                Runtime.getRuntime().exec(new String[]{"/usr/bin/open", file.getAbsolutePath()});
//            } catch (IOException e) {
//                System.out.println("Error opening file " + file.getName());
//            }
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
        System.out.println("Printing discovered files:\n");
        for (File file : discoveredFiles) {
            System.out.println(printCount + ": " + file.toString());
            printCount++;
        }
        System.out.println("");
    }

    /**
     * Discover files in the provided directory.
     */
    private void discoverFiles() {
        if(!recursive) {
            File[] files = directory.listFiles();
            for(File file : files) {
                if(file != null) {
                    if(discoveredFiles.size() >= maxLength) {
                        break;
                    }
                    if(file.isFile() && filenameHasDot(file.getName())) {
                        discoveredFiles.add(file);
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
                if(discoveredFiles.size() >= maxLength) {
                    break;
                }
                if(filenameHasDot((file.getName()))) {
                    discoveredFiles.add(file);
                }
            }
        }
    }

    /**
     * Determine if a discovered file is of the type *.*
     *
     * @param filename The file to inspect.
     * @return
     */
    private boolean filenameHasDot(String filename) {
        Matcher m = pattern.matcher(filename);
        if (m.find( )) {
            return true;
        }
        return false;
    }
}