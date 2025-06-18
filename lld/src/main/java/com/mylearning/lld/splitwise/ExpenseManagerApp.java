package com.mylearning.lld.splitwise;

import com.mylearning.lld.splitwise.settlement.PaymentGraph;
import com.mylearning.lld.splitwise.settlement.PaymentNode;
import com.mylearning.lld.splitwise.model.Balance;
import com.mylearning.lld.splitwise.model.Expense;
import com.mylearning.lld.splitwise.model.User;

import java.util.*;

public class ExpenseManagerApp {
    public static void main(String[] args) {

        ExpenseService expenseService = new ExpenseServiceImpl();

        // Create Users
        User alice = new User("U1", "alice.jpg", "Alice's bio");
        User bob = new User("U2", "bob.jpg", "Bob's bio");
        User charlie = new User("U3", "charlie.jpg", "Charlie’s bio");

        expenseService.addUser(alice);
        expenseService.addUser(bob);
        expenseService.addUser(charlie);

        // Create Group
        List<User> users = Arrays.asList(alice, bob, charlie);
        String groupID = expenseService.createGroup(users, "Trip to Bali", "bali.jpg", "Vacation expenses");
        System.out.println("Created group with ID: " + groupID);

        // Add Expenses
        String expense1 = expenseService.addExpense(groupID, alice.getUserID(), 300, "USD", SplitStrategyType.EQUAL, "", "");
        String expense2 = expenseService.addExpense(groupID, bob.getUserID(), 200, "USD", SplitStrategyType.EQUAL, "", "");

        System.out.println("Added Expenses:");
        System.out.println("Expense 1 ID: " + expense1);
        System.out.println("Expense 2 ID: " + expense2);

        // Fetch and Print Expenses
        List<Expense> expenses = expenseService.getGroupExpenses(groupID);
        System.out.println("\nExpenses in Group:");
        for (Expense expense : expenses) {
            System.out.println("Expense ID: " + expense.getExpenseID() + ", Amounts:");
            for (Map.Entry<User, Balance> entry : expense.getUserBalances().entrySet()) {
                System.out.println(entry.getKey().getUserID() + " -> " + entry.getValue().getAmount() + " " + entry.getValue().getCurrency());
            }
        }

        // Generate and Print Payment Graph
        PaymentGraph paymentGraph = expenseService.getGroupPaymentGraph(groupID);
        System.out.println("\nSettlement Transactions:");
        for (PaymentNode transaction : paymentGraph.getTransactions()) {
            System.out.println(transaction.getFromUserID() + " pays " + transaction.getAmount() + " to " + transaction.getToUserID());
        }

        // Settle an Expense
        expenseService.settleExpense(expense1);
        System.out.println("\nSettled Expense ID: " + expense1);

        // Fetch and Print Updated Expense
        Expense settledExpense = expenseService.getExpense(expense1);
        System.out.println("Is Expense Settled? " + settledExpense.isSettled());
    }
}
