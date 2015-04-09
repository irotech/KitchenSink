package com.gmail.at.irotech.client;

public class Main {

    public static void main(String[] args) {
        // producing some messages
        for (int i = 1; i < 6; i++) {
            final String message = "This is message numero " + i;
            Producer producer = new Producer(message);
            new Thread(producer).start();
        }

        Thread consumerThread = new Thread(new Consumer());
        consumerThread.start();
    }

}
