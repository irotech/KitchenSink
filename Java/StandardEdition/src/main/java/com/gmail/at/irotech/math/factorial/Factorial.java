package com.gmail.at.irotech.math.factorial;

public class Factorial {

    protected Double factorialRetVal = 0.0;

    protected Double factorial(int factor) {
        if(factor==0) {
            return 1.0;
        }
        return (factor * factorial(factor-1));
    }

    public static void main(String... args) {
        System.out.printf("Factorial: " + new Factorial().factorial(6));
    }

}
