package com.jason;

import sun.misc.JavaIOAccess;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        if(args.length != 1) {
            throw new IllegalArgumentException("Invalid number of arguments received; must be exactly one -- the filepath of the file to open.");
        }
        String filename = args[0];
        File file = new File(filename);
        System.out.println("Trying to open file " + file.getName());
        try {
            Runtime.getRuntime().exec(new String[] {"rundll32", "url.dll,FileProtocolHandler", file.getAbsolutePath()});
        } catch (IOException e) {
            System.out.println("Error opening file " + file.getName());
        }
    }
}
