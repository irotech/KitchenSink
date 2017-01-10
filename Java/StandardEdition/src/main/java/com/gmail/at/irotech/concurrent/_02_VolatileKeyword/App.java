package com.gmail.at.irotech.concurrent._02_VolatileKeyword;

/**
 * Volatile Keyword, <em>“… the volatile modifier guarantees that any thread that
 * reads a field will see the most recently written value.”</em> - Josh Bloch
 * <br><br>
 *
 * Available at
 * <a href="https://www.udemy.com/java-multithreading">
 *     <em>https://www.udemy.com/java-multithreading</em>
 * </a>
 *
 * Created by vcandela on 08/01/2016.
 */
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Processor processor = new Processor();
        processor.start();
        // Wait for the enter key
        System.out.println("Enter key to stop the endless running thread: ");
        new Scanner(System.in).nextLine();
        processor.shutdown();
    }
}

class Processor extends Thread {

    private volatile boolean running = true;

    public void run() {
        int i = 0;
        while (running) {
            System.out.println("Running " + i++);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
    }

}
