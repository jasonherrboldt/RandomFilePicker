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

        // Debug, remove:
        // System.getProperties().list(System.out);
        // System.out.println("\n");

        // Blow up if the user isn't on Windows or Mac.
        if(!OSDetector.isWindows() && !OSDetector.isMac()) {
            throw new IllegalArgumentException("Program currently only supports Windows and Mac operating systems.");
        }

        // Blow up if the user's not submitting the correct number of arguments.
        if(args.length != 1 && args.length != 2) {
            throw new IllegalArgumentException("Invalid number of arguments received; must be 1 or 2.");
        }

        // Blow up if unable to verify existence of chosen directory.
        String directoryName = args[0];
        File directory = new File(directoryName);
        if (!directory.exists()) {
            throw new IllegalArgumentException("Unable to access directory " + directoryName);
        }

        // Find out if user wants a recursive exploration.
        boolean recursive = false;
        if(args.length == 2) {
            if(args[1].equalsIgnoreCase("-r")) {
                recursive = true;
            } else {
                throw new IllegalArgumentException("Illegal argument received: " + args[1]);
            }
        }

        // Create the RandomFilePicker object and run it.
        RandomFilePicker rfp = new RandomFilePicker(directory, recursive);
        rfp.run();
    }
}
