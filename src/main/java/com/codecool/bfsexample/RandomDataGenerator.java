package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class RandomDataGenerator {

    private Random rng = new Random(1234);
    private String[] firstNames = {
            "Inez", "Emery", "Virginia", "Charissa", "Tyrone", "Ayanna", "Jena", "Ora",
            "Cooper", "Gareth", "Karleigh", "Aladdin", "Arden", "Pearl", "Mariko", "Hadley",
            "Tanya", "Madeline", "Naomi", "Maggie", "Kerry", "Elmo", "Wylie", "Alec",
            "Axel", "Belle", "Cally", "Theodore", "Emmanuel", "Christopher", "Olympia"};

    private String[] lastNames =  {
            "Winifred", "Tanner", "Rajah", "Cedric", "Tyler", "Nicholas", "Abra", "Aurora",
            "Bryar", "Kibo", "Myles", "Hillary", "Lydia", "Dolan", "Lucian", "Prescott",
            "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson"
    };


    List<UserNode> generate() {
        List<UserNode> users = new ArrayList<>();
        UserNode firstUser = genNewUser();
        users.add(firstUser);
        // first generate and connect users in a star shaped tree
        genTree(firstUser, users, 4);
        // then introduce some loops
        for (int i = 0; i < users.size() - 30; i++) {
            if (i % 4 == 0) {
                users.get(i).addFriend(users.get(i + 30));
            }
        }
        return users;
    }

    private void genTree(UserNode user, List<UserNode> allUsers, int depth) {
        if (depth == 0) {
            return;
        }
        for (int i = 0; i < 3; i++) {
            UserNode newUser = genNewUser();
            user.addFriend(newUser);
            allUsers.add(newUser);
            genTree(newUser, allUsers, depth - 1);
        }
    }

    private UserNode genNewUser() {
        return new UserNode(getRandomElement(firstNames), getRandomElement(lastNames));
    }

    private String getRandomElement(String[] array) {
        return array[rng.nextInt(array.length)];
    }
}
