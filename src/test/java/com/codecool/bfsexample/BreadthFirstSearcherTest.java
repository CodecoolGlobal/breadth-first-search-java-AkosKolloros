package com.codecool.bfsexample;


import com.codecool.bfsexample.model.UserNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BreadthFirstSearcherTest {

    @Test
    public void testDistance() {
        List<UserNode> users = getUserNodes();

        GraphPlotter graphPlotter = new GraphPlotter(users);

        BreadthFirstSearcher BFS = new BreadthFirstSearcher(graphPlotter);

        UserNode firstUser = users.get(0);

        int distance = BFS.distance(firstUser, users.get(20));
        assertEquals(3, distance);
    }

    private List<UserNode> getUserNodes() {
        List<UserNode> users;
        RandomDataGenerator generator = new RandomDataGenerator();
        users = generator.generate();
        return users;
    }
}