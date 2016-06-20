package com.od.sandbox.socionet.service;

import com.od.sandbox.socionet.format.MessageFormatter;
import com.od.sandbox.socionet.model.Message;
import com.od.sandbox.socionet.model.SocioGraph;
import com.od.sandbox.socionet.model.User;

import java.util.Collections;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Class representing a SocioNet service implementation
 * <p/>
 * Author: DorinO
 */
public class SocioNetServiceImpl implements SocioNetService {

    private SocioGraph socioGraph;

    private MessageFormatter formatter;

    public SocioNetServiceImpl() {
        socioGraph = new SocioGraph();
        formatter = new MessageFormatter();
    }

    public SocioGraph getSocioGraph() {
        return socioGraph;
    }

    public void setSocioGraph(SocioGraph socioGraph) {
        this.socioGraph = socioGraph;
    }

    public MessageFormatter getFormatter() {
        return formatter;
    }

    public void setFormatter(MessageFormatter formatter) {
        this.formatter = formatter;
    }

    @Override
    public boolean postMessage(User user, Message message) {
        User registeredUser = socioGraph.getUserByName(user.getUserName());
        if (registeredUser != null) {
            registeredUser.getPersonalMessages().add(message);
            return true;
        } else {
            user.getPersonalMessages().add(message);
            return socioGraph.addUser(user);
        }
    }

    @Override
    public void displayUserTimeline(User user) {
        User registeredUser = socioGraph.getUserByName(user.getUserName());
        if (registeredUser != null) {
            SortedSet<Message> unmodifiableMessages = Collections.unmodifiableSortedSet(registeredUser.getPersonalMessages());
            unmodifiableMessages.stream().forEach(message -> displayMessage(message));
        }
    }

    @Override
    public boolean followUser(User followingUser, User followedUser) {
        return socioGraph.addFollowedUser(followingUser, followedUser);
    }

    @Override
    public void displayUserWall(User user) {
        User registeredUser = socioGraph.getUserByName(user.getUserName());
        if (registeredUser != null) {
            //Add user personal messages
            Set<Message> wallMessages = new TreeSet<>(registeredUser.getPersonalMessages());

            //Add followed users messages
            Set<User> followedUsers = socioGraph.getFollowedUsers(registeredUser);
            Set<Message> followedMessages = followedUsers.stream()
                    .map(followedUser -> followedUser.getPersonalMessages())
                    .flatMap(messages -> messages.stream()).collect(Collectors.toSet());

            wallMessages.addAll(followedMessages);

            //Display wall messages
            wallMessages.stream().forEach(message -> displayMessageWithUserPrefix(message));
        }
    }

    private void displayMessageWithUserPrefix(Message message) {
        String messageWithUserPrefix = message.getUserName() + " - " + message.getMessage();
        String formattedMessage = formatter.formatMessage(messageWithUserPrefix, message.getCreatedTime());
        formatter.displayFormattedMessage(formattedMessage);
    }

    private void displayMessage(Message message) {
        String formattedMessage = formatter.formatMessage(message.getMessage(), message.getCreatedTime());
        formatter.displayFormattedMessage(formattedMessage);
    }
}
