package com.mylearning.lld.splitwise.settlement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentNode {
    String fromUserID;
    String toUserID;
    int amount;

    //Transaction between two users
    public PaymentNode(String fromUserID, String toUserID, int amount) {
        this.fromUserID = fromUserID;
        this.toUserID = toUserID;
        this.amount = amount;
    }
}
