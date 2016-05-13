package com.gmail.at.irotech.junit.categories;


import com.gmail.at.irotech.junit.suite.IntegrationTests;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(IntegrationTests.class)
public class IntegrationTest2 {

    @Test
    public void integrationTest2() {
        System.out.println("Running the integrationTest2...");
    }

}
