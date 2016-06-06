package com.jason;

/**
 * Class to detect the user's operating system.
 *
 * Created by Jason Herrboldt (intothefuture@gmail.com) on 6/6/2016.
 */
public class OSDetector {

    private static boolean isWindows = false;
    // private static boolean isLinux = false;
    private static boolean isMac = false;

    static
    {
        String os = System.getProperty("os.name").toLowerCase();
        isWindows = os.contains("win");
        // isLinux = os.contains("nux") || os.contains("nix");
        isMac = os.contains("mac");
    }

    /**
     * @return true if OS is Windows, false otherwise.
     */
    public static boolean isWindows() {
        return isWindows;
    }

//    /**
//     * @return true if OS is Linux, false otherwise.
//     */
//    public static boolean isLinux() {
//        return isLinux;
//    }

    /**
     * @return true if OS is Mac, false otherwise.
     */
    public static boolean isMac() {
        return isMac;
    };
}
