package com.codecool.bfsexample.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="Friendship",
            joinColumns=@JoinColumn(name="UserNode"),
            inverseJoinColumns=@JoinColumn(name="FriendNode"))
    private Set<UserNode> friends = new HashSet<>();

    public UserNode() {}

    public UserNode(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<UserNode> getFriends() {return friends;}

    public void addFriend(UserNode friend) {
        friends.add(friend);
        friend.friends.add(this);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
