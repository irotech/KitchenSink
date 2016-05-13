package com.gmail.at.irotech.junit.categories;

import com.gmail.at.irotech.junit.suite.FastTests;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(FastTests.class)
public class FastTest1 {

    @Test
    public void fastTest1() {
        System.out.println("Running the fastTest1...");
    }

}
