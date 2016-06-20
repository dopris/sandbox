package com.od.sandbox.socionet.command;

import com.od.sandbox.socionet.exception.CommandException;

/**
 * Interface representing a SocioNet command
 *
 * Author: DorinO
 */
public interface Command {
    void execute() throws CommandException;
}
