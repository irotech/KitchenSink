package com.gmail.at.irotech.math.factorial;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FactorialTest {

    private Factorial factorial;

    @Before
    public void setUp() {
        factorial = new Factorial();
    }

    @After
    public void tearDown() {
        factorial = null;
    }

    @Test
    public void factorial1() throws Exception {
        assertEquals(1.0, factorial.factorial(1), 0.0);
    }

    @Test
    public void factorial2() throws Exception {
        assertEquals(2.0, factorial.factorial(2), 0.0);
    }

    @Test
    public void factorial3() throws Exception {
        assertEquals(6.0, factorial.factorial(3), 0.0);
    }

    @Test
    public void factorial4() throws Exception {
        assertEquals(24.0, factorial.factorial(4), 0.0);
    }

    @Test
    public void factorial5() throws Exception {
        assertEquals(120.0, factorial.factorial(5), 0.0);
    }

    @Test
    public void factorial6() throws Exception {
        assertEquals(720.0, factorial.factorial(6), 0.0);
    }

    @Test
    public void factorial7() throws Exception {
        assertEquals(5040.0, factorial.factorial(7), 0.0);
    }

    @Test
    public void factorial12() throws Exception {
        assertEquals(479001600.0, factorial.factorial(12), 0.0);
    }

}