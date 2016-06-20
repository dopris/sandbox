package com.od.sandbox.socionet.command;

import com.od.sandbox.socionet.model.Message;
import com.od.sandbox.socionet.model.User;
import com.od.sandbox.socionet.service.SocioNetService;

/**
 * Class representing a post message command
 * <p/>
 * Author: DorinO
 */
public class PostMessageCommand implements Command {

    private final User user;
    private final Message message;
    private final SocioNetService service;

    public static PostMessageCommand getInstance(String userName, String message, SocioNetService service) {
        return new PostMessageCommand(new User(userName), new Message(message, userName), service);
    }

    private PostMessageCommand(User user, Message message, SocioNetService service) {
        this.user = user;
        this.message = message;
        this.service = service;
    }

    @Override
    public void execute() {
        service.postMessage(user, message);
    }
}
