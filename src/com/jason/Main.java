package com.jason;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        // Blow up if the user isn't on Windows 7 or Mac.
        if(!OSDetector.isWindows7() && !OSDetector.isMac()) {
            throw new IllegalArgumentException("Program currently only supports Windows 7 and OS X. More OS versions coming soon.");
        }

        // Blow up if the user's not submitting the correct number of arguments.
        if(args.length < 1 || args.length > 8) {
            throw new IllegalArgumentException("Invalid number of arguments received; must be > 0 and < 9.\n" +
                    "(Please also make sure the root directory is the first argument, and that it is wrapped in double quotes.\n");
        }

        // Blow up if unable to verify existence of chosen directory.
        String directoryName = args[0];
        File directory = new File(directoryName);
        if (!directory.exists()) {
            throw new IllegalArgumentException("Unable to access directory " + directoryName + ".\nPlease make sure the root directory is the first argument," +
            " and that it is wrapped in double quotes.");
        }

        // Parse remaining command line arguments.
        int maxLength = 100000;
        String extensions = "";
        boolean recursive = false;
        boolean printOnly = false;
        boolean showInfo = false;

        for (int i = 1; i < args.length; i++) {
            switch(args[i]) {
                case "-m": {
                    testArg(args, i);
                    try {
                        maxLength = Integer.parseInt(args[i + 1]);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Received illegal -m argument (must be a positive integer): " + args[i + 1]);
                    }
                    break;
                }
                case "-e": {
                    testArg(args, i);
                    extensions = args[i + 1];
                }
                case "-r": {
                    recursive = true;
                    break;
                }
                case "-p": {
                    printOnly = true;
                    break;
                }
                case "-i": {
                    showInfo = true;
                    break;
                }
            }
        }

        RandomFilePicker rfp = new RandomFilePicker(directory, maxLength, extensions, recursive, printOnly, showInfo);
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