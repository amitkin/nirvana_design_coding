package com.mylearning.design.pluralsight.creational.prototype;

import com.mylearning.design.patterns.pluralsight.creational.prototype.Movie;
import com.mylearning.design.patterns.pluralsight.creational.prototype.Registry;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RegistryTest {

    final private static Logger LOGGER = LoggerFactory.getLogger(RegistryTest.class);

    @Rule
    public TestName testName = new TestName();

    public RegistryTest() {
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
    public void testItemsNotSame() {
        Registry registry = new Registry();

        Movie movieOne = (Movie) registry.createItem("movie");
        movieOne.setTitle("matrix");

        Movie movieTwo = (Movie) registry.createItem("movie");
        movieTwo.setTitle("lock stock");

        assertNotEquals(movieOne.toString(), movieTwo.toString());
    }

    @Test
    public void testItemsCopiedFromSameOriginal() {
        double epsilon = 0.001;

        Registry registry = new Registry();

        Movie movieOne = (Movie) registry.createItem("movie");
        movieOne.setTitle("matrix");

        Movie movieTwo = (Movie) registry.createItem("movie");
        movieTwo.setTitle("lock stock");

        assertEquals(Registry.MOVIE_DEFAULT_PRICE, movieOne.getPrice(), epsilon);
        assertEquals(Registry.MOVIE_DEFAULT_PRICE, movieTwo.getPrice(), epsilon);
    }

    @Test
    public void testMovieDeepClone() {
        Registry registry = new Registry();

        Movie movieOne = (Movie) registry.createItem("movie");
        movieOne.getProducer().setName("bart");

        Movie movieTwo = (Movie) registry.createItem("movie");

        assertNotEquals(movieOne.getProducer(), movieTwo.getProducer());
        assertEquals("bart", movieOne.getProducer().getName());
        assertEquals("homer", movieTwo.getProducer().getName());
    }
}