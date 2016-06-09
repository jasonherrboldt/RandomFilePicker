package com.jason;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        // Remove (for debug).
        // System.getProperties().list(System.out);
        // System.out.println("\n");

        // Blow up if the user isn't on Windows 7.
        if(!OSDetector.isWindows7()) {
            throw new IllegalArgumentException("Program currently only supports Windows 7. More OS versions coming soon.");
        }

        // Blow up if the user's not submitting the correct number of arguments.
        if(args.length < 1 || args.length > 5) {
            throw new IllegalArgumentException("Invalid number of arguments received; must be > 0 and < 6.");
        }

        // Blow up if unable to verify existence of chosen directory.
        String directoryName = args[0];
        File directory = new File(directoryName);
        if (!directory.exists()) {
            throw new IllegalArgumentException("Unable to access directory " + directoryName + ". Please make sure the root directory is the first argument.");
        }

        // Parse remaining command line arguments.
        boolean printOnly = false;
        boolean recursive = false;
        int maxLength = 100000;

        for (int i = 1; i < args.length; i++) {
            switch(args[i]) {
                case "-p": {
                    printOnly = true;
                    break;
                }
                case "-r": {
                    recursive = true;
                    break;
                }
                case "-m": {
                    testArg(args, i);
                    try {
                        maxLength = Integer.parseInt(args[i + 1]);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Received illegal -m argument (must be a positive integer): " + args[i + 1]);
                    }
                    if(maxLength < 1 || maxLength > 100000) {
                        throw new IllegalArgumentException("Received illegal -m argument (must be >= 1 and <= 100,000): " + args[i + 1]);
                    }
                    break;
                }
            }
        }

        RandomFilePicker rfp = new RandomFilePicker(directory, printOnly, recursive, maxLength);
        rfp.run();
    }

    /**
     * Make sure a command line flag is followed by an argument.
     *
     * @param args The args to analyze
     * @param i The exact arg to analyze.
     */
    private static void testArg(String[] args, int i) {
        try {
            String test = args[i + 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("The flag " + args[i] + " was not followed by an argument.");
        }
        if (args[i + 1].charAt(0) == '-') {
            throw new IllegalArgumentException("The flag " + args[i] + " was followed by the invalid argument " +
                    args[i + 1] + ".");
        }
    }
}