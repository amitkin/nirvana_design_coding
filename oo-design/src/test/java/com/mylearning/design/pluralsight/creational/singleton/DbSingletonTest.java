package com.mylearning.design.pluralsight.creational.singleton;

import com.mylearning.design.patterns.pluralsight.creational.singleton.DbSingleton;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DbSingletonTest {

    final private static Logger LOGGER = LoggerFactory.getLogger(DbSingletonTest.class);

    @Rule
    public TestName testName = new TestName();

    public DbSingletonTest() {
    }

    @Before()
    public void beforeTest() {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Running Test: " + testName.getMethodName());
        }
    }

    @Test()
    public void testGetInstanceReturnsSameInstance() {
        DbSingleton instanceOne = DbSingleton.getInstance();
        DbSingleton instanceTwo = DbSingleton.getInstance();

        assertEquals(instanceOne.toString(), instanceTwo.toString());
    }

    @Test()
    public void testGetDbConnectionReturnsSameInstanceAndPerformsOnSubsequentCalls() {
        DbSingleton instanceOne = DbSingleton.getInstance();
        DbSingleton instanceTwo = DbSingleton.getInstance();

        long before = System.currentTimeMillis();
        Connection connectionOne = instanceOne.getConnection();
        long after = System.currentTimeMillis();
        long diff1 = after - before;

        before = System.currentTimeMillis();
        Connection connectionTwo = instanceTwo.getConnection();
        after  = System.currentTimeMillis();
        long diff2 = after - before;

        assertEquals(connectionOne.toString(), connectionTwo.toString());
        assertTrue(diff1 > diff2);
    }
}
