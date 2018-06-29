package io.lenar.examples;

import io.lenar.examples.model.Universal;

import org.junit.Assert;
import org.junit.Test;

public class LoggerTest {

    @Test
    public void createUniversalTest() {
        Universal universal = new Universal(3, 3);
        Assert.assertNotNull(universal);
        Assert.assertTrue(universal.getBlackHoles().size() == 3);
        Assert.assertTrue(universal.getStars().size() == 3);
    }

    @Test
    public void bigBoomTest() {
        Universal universal = new Universal(3, 3);
        universal.bigBang(2, 2);
        Assert.assertNotNull(universal);
        Assert.assertTrue(universal.getBlackHoles().size() == 2);
        Assert.assertTrue(universal.getStars().size() == 2);
    }

}
