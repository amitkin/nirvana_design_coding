package com.mylearning.design.solid.examples.avoiding_if_else_using_command_pattern.good.executors;

import com.mylearning.design.solid.examples.avoiding_if_else_using_command_pattern.other.Database;
import com.mylearning.design.solid.examples.avoiding_if_else_using_command_pattern.other.model.Command;

public class BalanceCommandExecutor extends CommandExecutor {

    public static final String BALANCE = "balance";

    public BalanceCommandExecutor(Database database) {
        super(database);
    }

    public Boolean isApplicable(Command command) {
        return command.getName().equals(BALANCE);
    }

    protected Boolean isValid(Command command) {
        return command.getParams().size() == 1;
    }

    protected String executeValidCommand(Command command) {
        String user = command.getParams().get(0);
        return database.getUserBalance(user).toString();
    }
}
