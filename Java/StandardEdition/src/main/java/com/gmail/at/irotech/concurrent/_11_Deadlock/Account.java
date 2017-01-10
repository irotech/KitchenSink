package com.gmail.at.irotech.concurrent._11_Deadlock;

/**
 * <br><br>
 *
 * Available at
 * <a href="https://www.udemy.com/java-multithreading">
 *     <em>https://www.udemy.com/java-multithreading</em>
 * </a>
 *
 * Created by vcandela on 08/01/2016.
 */
class Account {

    private int balance = 10000;

    public void deposit(int amount) {
        balance += amount;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public int getBalance() {
        return balance;
    }

    public static void transfer(Account acc1, Account acc2, int amount) {
        acc1.withdraw(amount);
        acc2.deposit(amount);
    }
}
