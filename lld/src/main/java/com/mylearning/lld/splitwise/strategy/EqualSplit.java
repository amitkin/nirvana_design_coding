package com.mylearning.lld.splitwise.strategy;

import com.mylearning.lld.splitwise.model.Balance;
import com.mylearning.lld.splitwise.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualSplit implements SplitStrategy {

    @Override
    public Map<User, Balance> split(int amount, String currency, List<User> users, String payerID) {
        Map<User, Balance> balances = new HashMap<>();
        int share = amount / users.size(); // Equal share

        for (User user : users) {
            balances.put(user, new Balance(currency, user.getUserID().equals(payerID) ? amount - share : -share));
        }
        System.out.println("Splitting " + amount + " equally.");
        return balances;
    }
}
