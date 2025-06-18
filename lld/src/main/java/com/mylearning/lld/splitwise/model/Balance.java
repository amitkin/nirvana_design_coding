package com.mylearning.lld.splitwise.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Balance {
    String currency;
    int amount;

    public Balance(String currency, int amount) {
        this.currency = currency;
        this.amount = amount;
    }
}