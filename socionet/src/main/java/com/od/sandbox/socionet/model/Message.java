package com.od.sandbox.socionet.model;

import java.time.Instant;

/**
 * Class representing a SocioNet message
 * <p/>
 * Author: DorinO
 */
public class Message implements Comparable<Message> {
    private final Instant createdTime = Instant.now();

    private final String userName;

    private final String message;
    public Message(String message, String userName) {
        this.message = message;
        this.userName = userName;
    }

    public Instant getCreatedTime() {
        return createdTime;
    }

    public String getMessage() {
        return message;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public int compareTo(Message otherMessage) {
        if (otherMessage.getCreatedTime().equals(createdTime)) {
            return otherMessage.getMessage().compareTo(message);
        } else {
            return otherMessage.getCreatedTime().compareTo(createdTime);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + createdTime.hashCode();
        result = prime * result + ((message != null) ? message.hashCode() : 0);
        result = prime * result + ((userName != null) ? userName.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Message otherMessage = (Message) obj;
        if (otherMessage.getMessage() == null)
            return message == null;
        if (otherMessage.getUserName() == null)
                    return userName == null;
        return otherMessage.getCreatedTime().equals(createdTime)
                && otherMessage.getMessage().equals(message)
                && otherMessage.getUserName().equals(userName);
    }
}
