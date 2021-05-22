package com.mylearning.design.solid.examples.avoiding_if_else_using_command_pattern.bad;

import static com.mylearning.design.solid.examples.avoiding_if_else_using_command_pattern.bad.TestHelpers.getTestBalanceCommand;
import static com.mylearning.design.solid.examples.avoiding_if_else_using_command_pattern.bad.TestHelpers.getTestUser;
import static org.junit.Assert.assertEquals;

import com.google.common.collect.ImmutableList;
import com.mylearning.design.solid.examples.avoiding_if_else_using_command_pattern.other.Database;
import com.mylearning.design.solid.examples.avoiding_if_else_using_command_pattern.other.RechargeProvider;
import org.junit.Before;
import org.junit.Test;

public class CommandRunnerBadTest {

    CommandRunnerBad commandRunnerBad;

    @Before
    public void setUp() {
        commandRunnerBad = new CommandRunnerBad(new Database(), new RechargeProvider());
    }

    @Test
    public void testBalanceCheck() {
        String balance = commandRunnerBad.runCommand(getTestBalanceCommand(ImmutableList.of(getTestUser())));
        assertEquals("1000", balance);
    }


}