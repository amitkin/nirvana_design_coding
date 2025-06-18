package com.mylearning.lld.splitwise.settlement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {
    String userID;
    int finalBalance;

    public Node(String userID, int finalBalance) {
        this.userID = userID;
        this.finalBalance = finalBalance;
    }
}
