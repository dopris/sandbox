package com.od.sandbox.socionet.command;


/**
 * Class representing finishing of SocioNet application
 * <p/>
 * Author: DorinO
 */
public class QuitCommand implements Command {

    @Override
    public void execute() {
        System.exit(0);
    }
}
