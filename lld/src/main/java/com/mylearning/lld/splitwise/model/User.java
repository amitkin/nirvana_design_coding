package com.mylearning.lld.splitwise.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    String userID;
    String imageURI;
    String bio;

    public User(String userID, String imageURI, String bio) {
        this.userID = userID;
        this.imageURI = imageURI;
        this.bio = bio;
    }
}
