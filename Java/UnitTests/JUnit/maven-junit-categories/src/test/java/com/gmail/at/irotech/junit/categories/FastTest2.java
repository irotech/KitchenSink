package com.gmail.at.irotech.junit.categories;

import com.gmail.at.irotech.junit.category.annotation.FastTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(FastTest.class)
public class FastTest2 {

    @Test
    public void fastTest2() {
        System.out.println("Running the fastTest2...");
    }

}
