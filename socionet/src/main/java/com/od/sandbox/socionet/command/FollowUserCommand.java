package com.od.sandbox.socionet.command;

import com.od.sandbox.socionet.model.User;
import com.od.sandbox.socionet.service.SocioNetService;

/**
 * Class representing a follow user command
 * <p/>
 * Author: DorinO
 */
public class FollowUserCommand implements Command {

    private final User followingUser;
    private final User followedUser;
    private final SocioNetService service;

    public static FollowUserCommand getInstance(String followingUserName, String followedUserName, SocioNetService service) {
        return new FollowUserCommand(new User(followingUserName), new User(followedUserName), service);
    }

    private FollowUserCommand(User followingUser, User followedUser, SocioNetService service) {
        this.followingUser = followingUser;
        this.followedUser = followedUser;
        this.service = service;
    }

    @Override
    public void execute() {
        service.followUser(followingUser, followedUser);
    }
}
