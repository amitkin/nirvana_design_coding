package com.mylearning.design.solid.examples.avoiding_if_else_using_command_pattern.bad;

import java.util.List;

import com.mylearning.design.solid.examples.avoiding_if_else_using_command_pattern.other.Database;
import com.mylearning.design.solid.examples.avoiding_if_else_using_command_pattern.other.RechargeProvider;
import com.mylearning.design.solid.examples.avoiding_if_else_using_command_pattern.other.model.Command;

public class TestHelpers {

    static public Database getTestDatabase() {
        return new Database();
    }

    static public RechargeProvider getTestRechargeProvider() {
        return new RechargeProvider();
    }

    static public String getTestUser() {
        return "test-user";
    }

    static public Command getTestBalanceCommand(List params) {
        return new Command("balance", params);
    }

    static public Command getTestRechargeCommand(List params) {
        return new Command("recharge", params);
    }
}
