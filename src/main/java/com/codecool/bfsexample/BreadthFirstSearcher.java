package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;

import java.util.HashSet;
import java.util.Set;

public class BreadthFirstSearcher {
    private Set<UserNode> searched;
    private Set<UserNode> queue = new HashSet<>();
    private GraphPlotter graphPlotter;

    public BreadthFirstSearcher(GraphPlotter graphPlotter) {
        this.graphPlotter = graphPlotter;
    }

    private void updateQueue(UserNode selectedUser) {
        for (UserNode user :
                selectedUser.getFriends()) {
            if (!user.isSearched()) {
                queue.add(user);
                user.setSearched(false);
            }
        }
    }

    public int distance(UserNode firstUser, UserNode lastUser) {
        int distance = 0;

        firstUser.setSearched(true);
        updateQueue(firstUser);

        graphPlotter.highlightNodes(queue, firstUser);
        while (true) {
            distance++;
            if (queue.contains(lastUser)) {
                return distance;
            } else {
                nextBreadth();
                graphPlotter.highlightNodes(queue, lastUser);
            }
        }
    }

    private void nextBreadth() {
        searched = new HashSet<>(queue);
        queue.clear();
        for (UserNode user :
                searched) {
            updateQueue(user);
        }
        searched.clear();
    }

    public Set<UserNode> friendsOfFriends(UserNode firstUser, int distance) {
        Set<UserNode> results = new HashSet<>();

        firstUser.setSearched(true);
        updateQueue(firstUser);

        for (int i = 0; i < distance; i++) {
            nextBreadth();
        }

        return null;
    }

    // bonus
    public Set<UserNode> shortestPaths() {
        return null;
    }
}
