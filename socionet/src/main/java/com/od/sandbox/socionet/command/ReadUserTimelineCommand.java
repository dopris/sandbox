package com.od.sandbox.socionet.command;

import com.od.sandbox.socionet.model.User;
import com.od.sandbox.socionet.service.SocioNetService;

/**
 * Class representing a read user personal timeline command
 *
 * Author: DorinO
 */
public class ReadUserTimelineCommand implements Command{

    private final User user;
    private final SocioNetService service;

    public static ReadUserTimelineCommand getInstance(String userName, SocioNetService service){
       return new ReadUserTimelineCommand(new User(userName), service);
    }

    private ReadUserTimelineCommand(User user, SocioNetService service) {
        this.user = user;
        this.service = service;
    }

    @Override
    public void execute() {
        service.displayUserTimeline(user);
    }
}
