package com.mylearning.lld.splitwise.strategy;

import com.mylearning.lld.splitwise.model.Balance;
import com.mylearning.lld.splitwise.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PercentageSplit implements SplitStrategy {

    public PercentageSplit() {

    }

    @Override
    public Map<User, Balance> split(int amount, String currency, List<User> users, String payerID) {
        Map<User, Balance> balances = new HashMap<>();
        int total = 0;
        for (int i = 0; i < users.size(); i++) {
            int percentage = (i + 1) * 10;  // Just an example: increasing percentage
            int share = (amount * percentage) / 100;
            total += share;
            balances.put(users.get(i), new Balance("USD", share));
        }
        System.out.println("Splitting " + amount + " by percentage.");
        return balances;
    }
}
