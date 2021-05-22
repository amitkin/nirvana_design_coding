package com.mylearning.design.solid.examples.avoiding_if_else_using_command_pattern.bad;

import static com.mylearning.design.solid.examples.avoiding_if_else_using_command_pattern.bad.TestHelpers.getTestBalanceCommand;
import static com.mylearning.design.solid.examples.avoiding_if_else_using_command_pattern.bad.TestHelpers.getTestUser;
import static org.junit.Assert.assertEquals;

import com.google.common.collect.ImmutableList;
import com.mylearning.design.solid.examples.avoiding_if_else_using_command_pattern.good.CommandRunnerGood;
import com.mylearning.design.solid.examples.avoiding_if_else_using_command_pattern.good.executors.BalanceCommandExecutor;
import com.mylearning.design.solid.examples.avoiding_if_else_using_command_pattern.good.executors.CommandExecutor;
import com.mylearning.design.solid.examples.avoiding_if_else_using_command_pattern.good.executors.RechargeCommandExecutor;
import org.junit.Before;
import org.junit.Test;

public class CommandRunnerGoodTest {

    CommandRunnerGood commandRunnerGood;

    @Before
    public void setUp() {

        ImmutableList<CommandExecutor> commandExecutors = ImmutableList.of(
                new BalanceCommandExecutor(TestHelpers.getTestDatabase()),
                new RechargeCommandExecutor(TestHelpers.getTestDatabase(), TestHelpers.getTestRechargeProvider())
        );

        commandRunnerGood = new CommandRunnerGood(commandExecutors);
    }

    @Test
    public void testBalanceCheck() {
        String balance = commandRunnerGood.runCommand(getTestBalanceCommand(ImmutableList.of(getTestUser())));
        assertEquals("1000", balance);
    }


}