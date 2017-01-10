package com.gmail.at.irotech.concurrent._01_StartingThreads;

/**
 * Thread constructor with anonymous classes
 * <br><br>
 *
 * Available at
 * <a href="https://www.udemy.com/java-multithreading">
 *     <em>https://www.udemy.com/java-multithreading</em>
 * </a>
 *
 * Created by vcandela on 08/01/2016.
 */
public class AppAnonymous {

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    System.out.println("Hello: " + i + " Thread: " + Thread.currentThread().getName());

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ignored) {}
                }
            }
        });
        thread1.start();
    }

}
