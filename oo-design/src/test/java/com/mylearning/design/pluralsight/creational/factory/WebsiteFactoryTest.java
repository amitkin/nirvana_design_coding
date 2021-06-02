package com.mylearning.design.pluralsight.creational.factory;

import com.mylearning.design.patterns.pluralsight.creational.factory.Blog;
import com.mylearning.design.patterns.pluralsight.creational.factory.Shop;
import com.mylearning.design.patterns.pluralsight.creational.factory.Website;
import com.mylearning.design.patterns.pluralsight.creational.factory.WebsiteFactory;
import com.mylearning.design.patterns.pluralsight.creational.factory.WebsiteType;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class WebsiteFactoryTest {

    final private static Logger LOGGER = LoggerFactory.getLogger(WebsiteFactoryTest.class);

    @Rule
    public TestName testName = new TestName();

    public WebsiteFactoryTest() {
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
    public void testGetWebsiteBlog() {
        Website site = WebsiteFactory.getWebsite(WebsiteType.BLOG);
        assertTrue(site instanceof Blog);
    }

    @Test
    public void testGetWebsiteShop() {
        Website site = WebsiteFactory.getWebsite(WebsiteType.SHOP);
        assertTrue(site instanceof Shop);
    }

    @Test
    public void testGetWebsiteNewInstances() {
        Website site = WebsiteFactory.getWebsite(WebsiteType.SHOP);
        Website site2 = WebsiteFactory.getWebsite(WebsiteType.SHOP);

        assertNotEquals(site.toString(), site2.toString());
    }
}