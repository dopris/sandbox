package com.od.sandbox.socionet.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class representing a directed graph of a social network
 * <p/>
 * Author: DorinO
 */
public class SocioGraph extends Graph<User> {

    private final Map<String, User> mapUserByName = new HashMap<>();

    public User getUserByName(String userName){
        return mapUserByName.get(userName);
    }

    public boolean addUser(User user) {
        if(mapUserByName.get(user.getUserName()) == null) {
            mapUserByName.put(user.getUserName(), user);
            return addNode(user);
        }
        return false;
    }

    public boolean addFollowedUser(User followingUser, User followedUser) {
        //Add users first
        addUser(followingUser);
        addUser(followedUser);
        User registeredFollowingUser = mapUserByName.get(followingUser.getUserName());
        User registeredFollowedUser = mapUserByName.get(followedUser.getUserName());

        return addEdge(registeredFollowingUser, registeredFollowedUser);
    }

    public Set<User> getFollowedUsers(User user){
        return getAdjacentSet(user);
    }
}

