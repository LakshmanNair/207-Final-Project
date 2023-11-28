package data_access;
//
//import javax.jms.*;
//import org.apache.activemq.ActiveMQConnectionFactory;
//
//public class APIAccessObject implements IMessageSender {
//    private Connection connection;
//    private Session session;
//    private MessageProducer producer;
//
//    public APIAccessObject(String brokerUrl, String queueName) throws JMSException {
//        // Create a connection factory to the ActiveMQ broker
//        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
//
//        // Create and start a connection
//        this.connection = connectionFactory.createConnection();
//        this.connection.start();
//
//        // Create a session
//        this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//
//        // Create a destination queue
//        Destination destination = this.session.createQueue(queueName);
//
//        // Create a producer from the session to the queue
//        this.producer = this.session.createProducer(destination);
//    }
//
//    @Override
//    public void sendMessage(String text) throws JMSException {
//        // Create a text message
//        TextMessage message = this.session.createTextMessage(text);
//
//        // Send the message to the queue
//        this.producer.send(message);
//    }
//
//    @Override
//    public void close() throws JMSException {
//        // Close the producer and session resources
//        if (this.producer != null) {
//            this.producer.close();
//        }
//        if (this.session != null) {
//            this.session.close();
//        }
//        if (this.connection != null) {
//            this.connection.close();
//        }
//    }
//}

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import java.util.HashMap;
import java.util.Map;

public class APIAccessObject {
    private Connection connection;
    private Session session;
    private Map<String, MessageConsumer> consumerMap;
    private Map<String, MessageProducer> producerMap;

    public APIAccessObject(String brokerUrl) throws JMSException {
        // Initialize connection and session
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        this.connection = connectionFactory.createConnection();
        this.connection.start();
        this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Initialize maps for consumers and producers
        consumerMap = new HashMap<>();
        producerMap = new HashMap<>();
    }

    // Method to create a chat session between two users
    public void createChatSession(String user1, String user2) throws JMSException {
        String chatQueueName = user1 + "_" + user2;
        Destination destination = this.session.createQueue(chatQueueName);

        // Create producer and consumer for both users
        producerMap.put(user1, this.session.createProducer(destination));
        consumerMap.put(user1, this.session.createConsumer(destination));

        producerMap.put(user2, this.session.createProducer(destination));
        consumerMap.put(user2, this.session.createConsumer(destination));
    }

    // Method to send a message from a user
    public void sendMessage(String user, String text) throws JMSException {
        MessageProducer producer = producerMap.get(user);
        if (producer != null) {
            TextMessage message = this.session.createTextMessage(text);
            producer.send(message);
        }
    }

    // Method to receive messages for a user
    public String receiveMessage(String user) throws JMSException {
        MessageConsumer consumer = consumerMap.get(user);
        if (consumer != null) {
            Message message = consumer.receive(1000); // Wait for 1 second
            if (message instanceof TextMessage) {
                return ((TextMessage) message).getText();
            }
        }
        return null;
    }

    // Clean-up method
    public void close() throws JMSException {
        for (MessageProducer producer : producerMap.values()) {
            producer.close();
        }
        for (MessageConsumer consumer : consumerMap.values()) {
            consumer.close();
        }
        if (this.session != null) {
            this.session.close();
        }
        if (this.connection != null) {
            this.connection.close();
        }
    }
}
