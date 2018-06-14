package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;
import java.util.List;

public class BFSExample {

    private static void populateDB() {

        RandomDataGenerator generator = new RandomDataGenerator();
        List<UserNode> users = generator.generate();

        GraphPlotter graphPlotter = new GraphPlotter(users);
        
        System.out.println("Done!");
    }

    public static void main(String[] args) {
        populateDB();
    }
}
