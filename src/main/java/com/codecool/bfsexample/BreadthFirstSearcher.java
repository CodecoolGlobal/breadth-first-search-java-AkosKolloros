package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;

public class BreadthFirstSearcher {
    private Set<UserNode> searched = new HashSet<>();
    private Queue<UserNode> queue = new LinkedBlockingDeque<>();
    private GraphPlotter graphPlotter;

    public BreadthFirstSearcher(GraphPlotter graphPlotter) {
        this.graphPlotter = graphPlotter;
    }

    private void updateQueue(UserNode selectedUser) {
        for (UserNode user :
                selectedUser.getFriends()) {
            if (!searched.contains(user) || !queue.contains(user)) {
                queue.add(user);
            }
        }
    }

    private void nextBreadth() {
        List<UserNode> breadth = new LinkedList<>(queue);
        queue.clear();

        for (UserNode user :
                breadth) {
            searched.add(user);
            updateQueue(user);
        }
    }

    public int distance(UserNode firstUser, UserNode lastUser) {
        int distance = 0;
        graphPlotter.highlightNodes(searched, lastUser);

        updateQueue(firstUser);

        while (!queue.isEmpty()) {
            distance++;
            if (queue.contains(lastUser)) {
                graphPlotter.highlightNodes(searched, firstUser);
                return distance;
            } else {
                nextBreadth();
            }
        }
        return 0;
    }

    public Set<UserNode> friendsOfFriends(UserNode firstUser, int distance) {
        updateQueue(firstUser);

        for (int i = 0; i < distance; i++) {
            nextBreadth();
        }

        return searched;
    }

    // bonus
    public Set<UserNode> shortestPaths() {
        return null;
    }
}
