package data_access;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class APIAccessObject implements IMessageSender {
    private Connection connection;
    private Session session;
    private MessageProducer producer;

    public APIAccessObject(String brokerUrl, String queueName) throws JMSException {
        // Create a connection factory to the ActiveMQ broker
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerUrl);

        // Create and start a connection
        this.connection = connectionFactory.createConnection();
        this.connection.start();

        // Create a session
        this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create a destination queue
        Destination destination = this.session.createQueue(queueName);

        // Create a producer from the session to the queue
        this.producer = this.session.createProducer(destination);
    }

    @Override
    public void sendMessage(String text) throws JMSException {
        // Create a text message
        TextMessage message = this.session.createTextMessage(text);

        // Send the message to the queue
        this.producer.send(message);
    }

    @Override
    public void close() throws JMSException {
        // Close the producer and session resources
        if (this.producer != null) {
            this.producer.close();
        }
        if (this.session != null) {
            this.session.close();
        }
        if (this.connection != null) {
            this.connection.close();
        }
    }
}

