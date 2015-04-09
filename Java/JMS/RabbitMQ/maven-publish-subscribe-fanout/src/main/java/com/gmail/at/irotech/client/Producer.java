package com.gmail.at.irotech.client;

import com.gmail.at.irotech.configuration.Configuration;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;

public class Producer implements Runnable {

    private final String message;
    private static final String EXCHANGE_NAME = "test";
    private static final String ROUTING_KEY = "test";

    public Producer(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername(Configuration.USERNAME);
        factory.setPassword(Configuration.PASSWORD);
        factory.setHost(Configuration.HOSTNAME);
        factory.setPort(Configuration.PORT);
        Connection conn;
        try {
            conn = factory.newConnection();
            Channel channel = conn.createChannel();

            channel.exchangeDeclare(EXCHANGE_NAME, "direct", true);
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, EXCHANGE_NAME, ROUTING_KEY);
            System.out.println("Producing message: " + message + " in thread: " + Thread.currentThread().getName());
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, message.getBytes());

            channel.close();
            conn.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
