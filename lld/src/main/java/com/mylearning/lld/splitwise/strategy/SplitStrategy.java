package com.mylearning.lld.splitwise.strategy;

import com.mylearning.lld.splitwise.model.Balance;
import com.mylearning.lld.splitwise.model.User;

import java.util.List;
import java.util.Map;

public interface SplitStrategy {
    Map<User, Balance> split(int amount, String currency, List<User> users, String payerID);
}
