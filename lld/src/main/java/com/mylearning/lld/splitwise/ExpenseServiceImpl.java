package com.mylearning.lld.splitwise;

import com.mylearning.lld.splitwise.settlement.ExpenseSettlementAlgorithm;
import com.mylearning.lld.splitwise.settlement.PaymentGraph;
import com.mylearning.lld.splitwise.model.Balance;
import com.mylearning.lld.splitwise.model.Expense;
import com.mylearning.lld.splitwise.model.Group;
import com.mylearning.lld.splitwise.model.User;

import java.util.*;

public class ExpenseServiceImpl implements ExpenseService {

    private final Map<String, Expense> expenses = new HashMap<>();
    private final Map<String, Group> groups = new HashMap<>();
    private final Map<String, User> users = new HashMap<>();

    @Override
    public List<Expense> getGroupExpenses(String groupID) {
        List<Expense> result = new ArrayList<>();
        for (Expense expense : expenses.values()) {
            if (expense.getGroupID().equals(groupID)) {
                result.add(expense);
            }
        }
        return result;
    }

    @Override
    public PaymentGraph getGroupPaymentGraph(String groupID) {
        return ExpenseSettlementAlgorithm.computeSettlement(getGroupExpenses(groupID));
    }

    @Override
    public void addUser(User user) {
        users.put(user.getUserID(), user);
    }

    @Override
    public User getUser(String userID) {
        return users.get(userID);
    }

    @Override
    public List<User> getUsersInGroup(String groupID) {
        Group group = groups.get(groupID);
        return group != null ? group.getUsers() : Collections.emptyList();
    }

    @Override
    public String addExpense(String groupID, String userID, int amount, String currency, SplitStrategyType type, String title, String imageURI) {
        String expenseID = UUID.randomUUID().toString();
        long timestamp = System.currentTimeMillis();

        // Retrieve the group
        Group group = groups.get(groupID);
        if (group == null) {
            throw new IllegalArgumentException("Group not found");
        }

        // Retrieve the user
        User payer = users.get(userID);
        if (payer == null) {
            throw new IllegalArgumentException("User not found");
        }

        // Create expense using Factory Pattern
        Expense expense = ExpenseFactory.createExpense(type, expenseID, groupID, title, timestamp, imageURI);

        // Perform splitting using Strategy Pattern
        expense.splitExpense(amount, currency, group.getUsers(), userID);

        // Save expense in database or in-memory storage
        expenses.put(expenseID, expense);

        return expenseID;
    }

    @Override
    public void editExpense(String expenseID, String userID, int amount, String currency) {
        Expense expense = expenses.get(expenseID);
        if (expense != null) {
            expense.getUserBalances().put(new User(userID, "", ""), new Balance(currency, amount));
        }
    }

    @Override
    public void settleExpense(String expenseID) {
        Expense expense = expenses.get(expenseID);
        if (expense != null) {
            expense.setSettled(true);
        }
    }

    @Override
    public String createGroup(List<User> users, String name, String imageURI, String description) {
        String groupID = UUID.randomUUID().toString();
        Group group = new Group(groupID, users, imageURI, name, description);
        groups.put(groupID, group);
        return groupID;
    }

    @Override
    public Group getGroup(String groupID) {
        return groups.get(groupID);
    }

    @Override
    public Expense getExpense(String expenseID) {
        return expenses.get(expenseID);
    }
}
