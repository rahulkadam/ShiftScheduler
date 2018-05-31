package com.astro.shiftscheduler.bussiness;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiftGeneratorTest {

    @Test
    public void shouldGenerateShifts() {
        Assert.assertTrue(true);
    }

    @Test
    public void shouldFailedgenerateShifts() {
        Assert.assertTrue(false);
    }
}
