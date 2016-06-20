package com.od.sandbox.socionet.command;

import com.od.sandbox.socionet.exception.CommandException;

/**
 * Class representing a non supported command
 *
 * Author: DorinO
 */
public class NullCommand implements Command{

    @Override
    public void execute() throws CommandException {
        throw new CommandException();
    }
}
