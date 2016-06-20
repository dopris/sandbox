package com.od.sandbox.socionet.exception;

/**
 * Custom checked exception for command
 * <p/>
 * Author: DorinO
 */
public class CommandException extends Exception {
    private static final long serialVersionUID = 2337753363232807002L;

    public CommandException(){
        super("Command not supported.");
    }
    public CommandException(String message) {
        super(String.format("Command '%s' is not supported.", message));
    }

}
