package com.od.sandbox.socionet.model;

import java.util.*;

/**
 * A generic class representing a directed graph
 * Internal representation uses adjacency lists
 * <p/>
 * Author: DorinO
 */
public class Graph<N> implements Iterable<N> {

    /*A map of nodes to its adjacent set of nodes.
    */
    private final Map<N, Set<N>> graph = new HashMap<>();

    public boolean addNode(N node) {
        if (containsNode(node)) {
            return false;
        }
        graph.put(node, new HashSet<N>());
        return true;
    }

    public boolean addEdge(N node1, N node2) {
        if (!containsNode(node1) || !containsNode(node2)) {
            return false;
        }
        //Add the edge from node1 to node2.
        graph.get(node1).add(node2);
        return true;
    }

    public boolean removeEdge(N node1, N node2) {
        if (!containsNode(node1) || !containsNode(node2)) {
            return false;
        }
        // Remove the edge from node1 to node2.
        graph.get(node1).remove(node2);
        return true;
    }

    public Set<N> getAdjacentSet(N node) {
        if (!containsNode(node)) {
            return new HashSet<N>();
        }
        Set<N> adjacentSet = graph.get(node);
        return Collections.unmodifiableSet(adjacentSet);
    }

    public boolean containsNode(N node) {
        return graph.containsKey(node);
    }

    @Override
    public Iterator<N> iterator() {
        return graph.keySet().iterator();
    }

    public int size() {
        return graph.size();
    }

}

