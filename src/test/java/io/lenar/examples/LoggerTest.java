package io.lenar.examples;

import io.lenar.examples.model.Universe;

import org.junit.Assert;
import org.junit.Test;

public class LoggerTest {

    @Test
    public void createUniverseTest() {
        Universe universe = new Universe(3, 3);
        Assert.assertNotNull(universe);
        Assert.assertTrue(universe.getBlackHoles().size() == 3);
        Assert.assertTrue(universe.getStars().size() == 3);
    }

    @Test
    public void bigBoomTest() {
        Universe universe = new Universe(3, 3);
        universe.bigBang(2, 2);
        Assert.assertNotNull(universe);
        Assert.assertTrue(universe.getBlackHoles().size() == 2);
        Assert.assertTrue(universe.getStars().size() == 2);
    }

}
