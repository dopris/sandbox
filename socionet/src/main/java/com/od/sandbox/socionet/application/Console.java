package com.od.sandbox.socionet.application;

import com.od.sandbox.socionet.exception.CommandException;
import com.od.sandbox.socionet.util.Util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * Main class of SocioNet application
 * <p/>
 * Author: DorinO
 */
public class Console {
    private final static String UTF8 = Charset.forName("UTF-8").name();
    private final static String HELP_TEXT = "help/intro.txt";
    private final static String APP_PROMPT = ">";

    public static void main(String[] args) {
        Console console = new Console();
        console.displayHelp();
        console.run();
    }

    /**
     * Runs application console
     */
    private void run() {
        SocioNet socioNet = new SocioNet();
        try (Scanner input = new Scanner(System.in, UTF8)) {
            Util.inline(APP_PROMPT);
            while (input.hasNextLine()) {
                try {
                    socioNet.runCommand(input.nextLine());
                } catch (CommandException e) {
                    Util.report(e);
                } finally {
                    Util.inline(APP_PROMPT);
                }
            }
        }
    }

    /**
     * Displays a short application help
     */
    private void displayHelp() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream(HELP_TEXT)) {
            try (Scanner helpInput = new Scanner(is)) {
                while (helpInput.hasNextLine()) {
                    Util.output(helpInput.nextLine());
                }
            }
        } catch (IOException e) {
            Util.report(e);
        }
    }
}
