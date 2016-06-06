package com.jason;

import java.io.*;

/**
 * Opens a file at random in a given directory.
 *
 * Created by Jason Herrboldt (intothefuture@gmail.com) on 6/6/2016.
 */
public class RandomFilePicker {

    // http://stackoverflow.com/questions/7024031/java-open-a-file-windows-mac

    /*
            System.out.println("Exploring directory " + file.getName());
        if(OSDetector.isWindows()) {
            System.out.println("oh hai windows");
            try {
                Runtime.getRuntime().exec(new String[] {"rundll32", "url.dll,FileProtocolHandler", file.getAbsolutePath()});
            } catch (IOException e) {
                System.out.println("Error opening file " + file.getName());
            }
        } else if (OSDetector.isMac()) {
            System.out.println("oh hai mac");
            try {
                Runtime.getRuntime().exec(new String[]{"/usr/bin/open", file.getAbsolutePath()});
            } catch (IOException e) {
                // todo
            }
        }
     */

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
        System.out.println("oh hai from openRandomFile.");
    }
}

