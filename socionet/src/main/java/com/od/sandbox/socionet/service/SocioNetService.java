package com.od.sandbox.socionet.service;

import com.od.sandbox.socionet.model.Message;
import com.od.sandbox.socionet.model.User;

/**
 * Interface representing SocioNet provided services
 *
 * Author: DorinO
 */
public interface SocioNetService {
    boolean postMessage(User user, Message message);
    boolean followUser(User followingUser, User followedUser);
    void displayUserTimeline(User user);
    void displayUserWall(User user);
}
