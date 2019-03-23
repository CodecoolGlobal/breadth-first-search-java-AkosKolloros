package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;
import java.util.List;

public class BFSExample {

    private static void populateDB() {

        RandomDataGenerator generator = new RandomDataGenerator();
        List<UserNode> users = generator.generate();

        GraphPlotter graphPlotter = new GraphPlotter(users);

        BreadthFirstSearcher BFS = new BreadthFirstSearcher(graphPlotter);

        int distance = BFS.distance(users.get(1), users.get(120));
        System.out.println(distance);
        System.out.println("Done!");
    }

    public static void main(String[] args) {
        populateDB();
    }
}
