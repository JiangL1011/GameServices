package com.jiangling;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void test1() {
        int[] a = {2, 3, 5, 1, 6, 4};
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
    }

    @Test
    public void test2() {
        System.out.println(29 / 10);
    }

}
