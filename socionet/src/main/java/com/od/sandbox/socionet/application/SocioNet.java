package com.od.sandbox.socionet.application;

import com.od.sandbox.socionet.command.Command;
import com.od.sandbox.socionet.command.CommandFactory;
import com.od.sandbox.socionet.exception.CommandException;
import com.od.sandbox.socionet.service.SocioNetService;
import com.od.sandbox.socionet.service.SocioNetServiceImpl;

/**
 * Class representing SocioNet application
 * <p/>
 * Author: DorinO
 */
public class SocioNet {

    private SocioNetService service;

    public SocioNet() {
        this.service = new SocioNetServiceImpl();
    }

    public SocioNetService getService() {
        return service;
    }

    public void setService(SocioNetService service) {
        this.service = service;
    }

    public void runCommand(String commandLine) throws CommandException {
        if (isNotEmpty(commandLine)) {
            Command command = CommandFactory.getCommandForService(commandLine.trim(), getService());
            command.execute();
        }
    }

    private boolean isNotEmpty(String commandLine) {
        return commandLine != null && !commandLine.trim().isEmpty();
    }
}
