package com.mylearning.design.pluralsight.creational.builder;

import com.mylearning.design.patterns.pluralsight.creational.builder.LunchOrder;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;

public class LunchOrderTest {

    final private static Logger LOGGER = LoggerFactory.getLogger(LunchOrderTest.class);

    @Rule
    public TestName testName = new TestName();

    public LunchOrderTest() {
    }

    @Before()
    public void beforeTest() {
        if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Running Test: " + testName.getMethodName());
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testLunchOrderBuilder() {
        final String meat = "angus beef";
        final String veggies = "lettuce, tomato";
        final String condiments = "mayo, mustard";
        final String bread = "pretzel bun";

        // No public constructor
//        LunchOrder lunchOrder = new LunchOrder();

        // Forced to use an available builder constructor
        LunchOrder.Builder builder = new LunchOrder.Builder(meat);
        builder.veggies(veggies).condiments(condiments).bread(bread);

        LunchOrder lunchOrder = builder.build();

        // Immutable; no setters available
//        lunchOrder.set

        assertEquals(meat, lunchOrder.getMeat());
        assertEquals(veggies, lunchOrder.getVeggies());
        assertEquals(condiments, lunchOrder.getCondiments());
        assertEquals(bread, lunchOrder.getBread());
    }

    @Test(expected = NullPointerException.class)
    public void testLunchOrderBuilderOneArgConstructorThrowsNull() {
        LunchOrder.Builder builder = new LunchOrder.Builder(null);
    }

    @Test(expected = NullPointerException.class)
    public void testLunchOrderBuilderTwoArgConstructorThrowsNull() {
        LunchOrder.Builder builder = new LunchOrder.Builder(null, null);
    }
}