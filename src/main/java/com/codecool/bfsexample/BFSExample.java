package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;
import java.util.List;
import java.util.Set;

public class BFSExample {

    private static List<UserNode> users;

    private static void populateDB() {

        RandomDataGenerator generator = new RandomDataGenerator();
        users = generator.generate();

        System.out.println("Done!");
    }

    public static void main(String[] args) {
        populateDB();

        GraphPlotter graphPlotter = new GraphPlotter(users);

        BreadthFirstSearcher BFS = new BreadthFirstSearcher(graphPlotter);

        UserNode firstUser = users.get(0);

        int distance = BFS.distance(firstUser, users.get(30));
        System.out.println(distance);

        Set<UserNode> friendsOfFriends = BFS.friendsOfFriends(firstUser, 2);

//        graphPlotter.highlightNodes(friendsOfFriends, firstUser);
    }
}
