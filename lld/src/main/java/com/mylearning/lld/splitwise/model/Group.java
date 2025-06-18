package com.mylearning.lld.splitwise.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Group {
    String groupID;
    List<User> users;
    String imageURI;
    String title;
    String description;

    public Group(String groupID, List<User> users, String imageURI, String title, String description) {
        this.groupID = groupID;
        this.users = users;
        this.imageURI = imageURI;
        this.title = title;
        this.description = description;
    }

    public void addUser(User user) {
        users.add(user);
    }
}
