package com.od.sandbox.socionet.command;

import com.od.sandbox.socionet.model.User;
import com.od.sandbox.socionet.service.SocioNetService;

/**
 * Class representing a user wall command
 *
 * Author: DorinO
 */
public class UserWallCommand implements Command{

    private final User user;
    private final SocioNetService service;

    public static UserWallCommand getInstance(String userName, SocioNetService service){
       return new UserWallCommand(new User(userName), service);
    }

    private UserWallCommand(User user, SocioNetService service) {
        this.user = user;
        this.service = service;
    }

    @Override
    public void execute() {
        service.displayUserWall(user);
    }
}
