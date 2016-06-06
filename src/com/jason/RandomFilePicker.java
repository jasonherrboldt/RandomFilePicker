package com.jason;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Opens a file at random in a given directory.
 *
 * Created by Jason Herrboldt (intothefuture@gmail.com) on 6/6/2016.
 */
public class RandomFilePicker {

    private File directory;
    private boolean recursive;
    private List<File> discoveredFiles;

    /**
     * Public constructor.
     *
     * @param directory   The directory to explore
     * @param recursive   Whether the search should be recursive.
     */
    public RandomFilePicker(File directory, boolean recursive) {
        this.directory = directory;
        this.recursive = recursive;
        discoveredFiles = new ArrayList<>();
        System.out.println("oh hai from RandomFilePicker constructor. Directory = " + directory.toString() + ", recursive = " + recursive + ".");
    }


    public void openRandomFile() {
        discoverFiles();
        printDiscoveredFiles();
        // Open a file at random.
    }

    // For debug:
    private void printDiscoveredFiles() {
        System.out.println("Printing contents of discoveredFiles:");
        for (File file : discoveredFiles) {
            System.out.println(file.toString());
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
                    if(file.isFile()) {
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
                discoveredFiles.add(file);
            }
        }
    }
}













