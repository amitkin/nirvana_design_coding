package com.mylearning.design.solid.examples.avoiding_if_else_using_command_pattern.good;

import java.util.List;

import com.mylearning.design.solid.examples.avoiding_if_else_using_command_pattern.good.executors.CommandExecutor;
import com.mylearning.design.solid.examples.avoiding_if_else_using_command_pattern.other.model.Command;

public class CommandRunnerGood {

    List<CommandExecutor> commandExecutors;

    public CommandRunnerGood(List<CommandExecutor> commandExecutors) {
        this.commandExecutors = commandExecutors;
    }

    public String runCommand(Command command) {
        for (CommandExecutor commandExecutor: commandExecutors) {
            if (commandExecutor.isApplicable(command)) {
                return commandExecutor.execute(command);
            }
        }

        return "Invalid Command";
    }
}
