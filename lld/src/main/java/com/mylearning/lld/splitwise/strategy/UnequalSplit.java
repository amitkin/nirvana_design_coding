package com.mylearning.lld.splitwise.strategy;

import com.mylearning.lld.splitwise.model.Balance;
import com.mylearning.lld.splitwise.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class UnequalSplit implements SplitStrategy {

    @Override
    public Map<User, Balance> split(int amount, String currency, List<User> users, String payerID) {
        Map<User, Balance> balances = new HashMap<>();
        Random rand = new Random();
        for (User user : users) {
            int share = rand.nextInt(amount / users.size()) + 1;  // Random split
            balances.put(user, new Balance("USD", share));
        }
        System.out.println("Splitting " + amount + " unequally.");
        return balances;
    }
}
