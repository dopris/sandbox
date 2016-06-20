package com.od.sandbox.socionet.model;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Class representing a SocioNet user
 * <p/>
 * Author: DorinO
 */
public class User {
    private final String userName;
    private final SortedSet<Message> personalMessages;

    public User(String userName) {
        this.userName = userName;
        personalMessages = new TreeSet<>();
    }

    public String getUserName() {
        return userName;
    }

    public SortedSet<Message> getPersonalMessages() {
        return personalMessages;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
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
        User otherMessage = (User) obj;
        if (otherMessage.getUserName() == null)
            return userName == null;
        return otherMessage.getUserName().equals(userName);
    }
}
