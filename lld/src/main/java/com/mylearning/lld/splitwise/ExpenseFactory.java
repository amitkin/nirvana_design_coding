package com.mylearning.lld.splitwise;

import com.mylearning.lld.splitwise.model.Expense;
import com.mylearning.lld.splitwise.strategy.EqualSplit;
import com.mylearning.lld.splitwise.strategy.PercentageSplit;
import com.mylearning.lld.splitwise.strategy.SplitStrategy;
import com.mylearning.lld.splitwise.strategy.UnequalSplit;

import java.util.HashMap;

public class ExpenseFactory {

    public static Expense createExpense(SplitStrategyType type, String expenseID, String groupID,
                                        String title, long timestamp, String imageURI) {
        SplitStrategy strategy;
        switch (type) {
            case EQUAL:
                strategy = new EqualSplit();
                break;
            case UNEQUAL:
                strategy = new UnequalSplit();
                break;
            case PERCENTAGE:
                strategy = new PercentageSplit();
                break;
            default:
                throw new IllegalArgumentException("Invalid expense type");
        }
        return new Expense(expenseID, false, new HashMap<>(), groupID, title, timestamp, imageURI, strategy);
    }

}
