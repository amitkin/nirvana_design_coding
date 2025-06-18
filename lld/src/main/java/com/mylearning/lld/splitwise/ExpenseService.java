package com.mylearning.lld.splitwise;

import com.mylearning.lld.splitwise.settlement.PaymentGraph;
import com.mylearning.lld.splitwise.model.Expense;
import com.mylearning.lld.splitwise.model.Group;
import com.mylearning.lld.splitwise.model.User;

import java.util.List;

public interface ExpenseService {

    List<Expense> getGroupExpenses(String groupID);

    PaymentGraph getGroupPaymentGraph(String groupID);

    void addUser(User user);

    User getUser(String userID);

    List<User> getUsersInGroup(String groupID);

    String addExpense(String groupID, String userID, int amount, String currency, SplitStrategyType type, String title, String imageURI);

    void editExpense(String expenseID, String userID, int amount, String currency);

    void settleExpense(String expenseID);

    String createGroup(List<User> users, String name, String imageURI, String description);

    Group getGroup(String groupID);

    Expense getExpense(String expenseID);
}
