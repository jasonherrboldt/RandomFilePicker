package com.jason;

import java.io.*;

/**
 * Opens a file at random in a given directory.
 *
 * Created by Jason Herrboldt (intothefuture@gmail.com) on 6/6/2016.
 */
public class RandomFilePicker {

    private File directory;
    private boolean recursive;

    /**
     * Public constructor.
     *
     * @param directory   The directory to explore
     * @param recursive   Whether the search should be recursive.
     */
    public RandomFilePicker(File directory, boolean recursive) {
        this.directory = directory;
        this.recursive = recursive;
        System.out.println("oh hai from RandomFilePicker constructor. Directory = " + directory.toString() + ", recursive = " + recursive + ".");
    }

    /**
     * Open a random file
     */
    public void openRandomFile() {
        System.out.println("oh hai from openRandomFile. Printing discovered filenames...");
        File[] listOfFiles = directory.listFiles();
        for(File file : listOfFiles) {
            if(file != null) {
                System.out.println(file.toString());
            }
        }
    }
}













