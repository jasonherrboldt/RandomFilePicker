package com.jason;

import java.io.File;

public class Main {

    // todo: For Windows: Put a warning here and in the readme about root directory names with spaces (inner directory names can have spaces).

    /*

        Test filepath for runtime config using a specific filepath:
        C:\dev\demos\RandomFilePicker\Test_Directory -r

        Using the current directory:
        . -r

     */


    public static void main(String[] args) {

        // Remove (for debug).
        // System.getProperties().list(System.out);
        // System.out.println("\n");

        // Blow up if the user isn't on Windows 7.
        if(!OSDetector.isWindows7()) {
            throw new IllegalArgumentException("Program currently only supports Windows 7. More OS versions coming soon.");
        }

        // Blow up if the user's not submitting the correct number of arguments.
        if(args.length != 1 && args.length != 2 && args.length != 4) {
            throw new IllegalArgumentException("Invalid number of arguments received; must be 1, 2, or 4.");
        }

        // Blow up if unable to verify existence of chosen directory.
        String directoryName = args[0];
        File directory = new File(directoryName);
        if (!directory.exists()) {
            throw new IllegalArgumentException("Unable to access directory " + directoryName);
        }

        // Find out if user wants a recursive exploration.
        boolean recursive = false;
        int maxlength = 0;
//        if(args.length == 2) {
//            if(args[1].equalsIgnoreCase("-r")) {
//                recursive = true;
//            } else {
//                throw new IllegalArgumentException("Illegal argument received: " + args[1]);
//            }
//        }

        for (int i = 0; i < args.length; i++) {
            switch(args[i]) {
                case "-r": {
                    recursive = true;
                    break;
                }
                case "-maxlength": {
                    testArg(args, i);
                    try {
                        maxlength = Integer.parseInt(args[i + 1]);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Received illegal maxlength argument (must be a positive integer): " + args[i + 1]);
                    }
                    if(maxlength < 1 || maxlength > 200000) {
                        throw new IllegalArgumentException("Received illegal maxlength argument (must be >= 1 and <= 200,000): " + args[i + 1]);
                    }
                    break;
                }
            }
        }

        // Create the RandomFilePicker object and run it.
        RandomFilePicker rfp = new RandomFilePicker(directory, recursive, maxlength);
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
