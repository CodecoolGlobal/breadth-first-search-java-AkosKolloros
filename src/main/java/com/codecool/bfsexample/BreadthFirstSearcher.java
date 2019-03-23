package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;

import java.util.HashSet;
import java.util.Set;

public class BreadthFirstSearcher {
    private Set<UserNode> searched;
    private Set<UserNode> queue = new HashSet<>();
    private GraphPlotter graphPlotter;

    public int distance(UserNode user1, UserNode user2) {
        int distance = 0;
        user1.setSearched(false);

        updateQueue(user1);

        while (true) {
            distance++;
            if (queue.contains(user2)) {
                return distance;
            } else {
                searched = new HashSet<>(queue);
                queue.clear();
                for (UserNode user :
                        searched) {
                    updateQueue(user);
                }
                searched.clear();
            }
        }
    }

    private void updateQueue(UserNode selectedUser) {
        for (UserNode user :
                selectedUser.getFriends()) {
            if (!user.isSearched()) {
                queue.add(user);
                user.setSearched(false);
            }
        }
        graphPlotter.highlightNodes(queue, selectedUser);
    }

    public BreadthFirstSearcher(GraphPlotter graphPlotter) {
        this.graphPlotter = graphPlotter;
    }

    public Set<UserNode> friendsOfFriends(int distance) {
        Set<UserNode> result = new HashSet<>();

        for (int i = 0; i < distance; i++) {

        }

        return null;
    }

    // bonus
    public Set<UserNode> shortestPaths() {
        return null;
    }
}
