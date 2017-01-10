package com.gmail.at.irotech.concurrent._01_StartingThreads;

/**
 * Extending Threads class
 * <br><br>
 *
 * Available at
 * <a href="https://www.udemy.com/java-multithreading">
 *     <em>https://www.udemy.com/java-multithreading</em>
 * </a>
 *
 * Created by vcandela on 08/01/2016.
 */
public class AppExtends {

    public static void main(String[] args) {
        RunnerThread runner1 = new RunnerThread();
        runner1.start();
        RunnerThread runner2 = new RunnerThread();
        runner2.start();
    }

}

class RunnerThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Hello: " + i + " Thread: " + Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
