package com.mylearning.lld.splitwise.model;

import com.mylearning.lld.splitwise.strategy.SplitStrategy;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Expense {

    private String expenseID;
    private boolean isSettled;
    private Map<User, Balance> userBalances;
    private String groupID;
    private String title;
    private long timestamp;
    private String imageURI;

    private SplitStrategy splitStrategy;

    public Expense(String expenseID, boolean isSettled, Map<User, Balance> userBalances,
                   String groupID, String title, long timestamp, String imageURI, SplitStrategy strategy) {
        this.expenseID = expenseID;
        this.isSettled = isSettled;
        this.userBalances = userBalances;
        this.groupID = groupID;
        this.title = title;
        this.timestamp = timestamp;
        this.imageURI = imageURI;
        this.splitStrategy = strategy;
    }

    public void addUser(User user, Balance balance) {
        userBalances.put(user, balance);
    }

    public void splitExpense(int amount, String currency, List<User> users, String payerID) {
        userBalances = splitStrategy.split(amount, currency, users, payerID);
    }
}