package com.od.sandbox.socionet.util;

/**
 * Helper class for output messages or reporting errors
 * <p/>
 * Author: DorinO
 */
public class Util {
    public static void inline(String message) {
        System.out.print(message);
    }

    public static void output(String message) {
        System.out.println(message);
    }

    public static void report(Exception exception) {
        System.err.println(exception.getMessage());
    }
}
