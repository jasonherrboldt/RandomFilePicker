package com.jason;

import java.io.File;

public class Main {

    // todo: For Windows: Put a warning here and in the readme about root directory names with spaces (inner directory names can have spaces).

    // Test filepath for runtime config:
    // C:\dev\demos\RandomFilePicker\Test_Directory -r

    public static void main(String[] args) {

        if(!OSDetector.isWindows()) {
            throw new IllegalArgumentException("Program currently only supports Windows. Mac coming soon.");
        }

        if(args.length != 1 && args.length != 2) {
            throw new IllegalArgumentException("Invalid number of arguments received; must be 1 or 2.");
        }

        // Fail if unable to verify existence of chosen directory.
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

        // Create the RandomFilePicker object and put it to work.
        RandomFilePicker rfp = new RandomFilePicker(directory, recursive);
        rfp.openRandomFile();
    }
}
