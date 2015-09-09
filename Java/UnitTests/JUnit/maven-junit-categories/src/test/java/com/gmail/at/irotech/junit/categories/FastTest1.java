package com.gmail.at.irotech.junit.categories;

import com.gmail.at.irotech.junit.category.annotation.FastTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(FastTest.class)
public class FastTest1 {

    @Test
    public void fastTest1() {
        System.out.println("Running the fastTest1...");
    }

}
