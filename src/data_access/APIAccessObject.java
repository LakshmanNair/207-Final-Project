package data_access;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

public class APIAccessObject {
    private final Session session; // Session for creating producers
    private MessageProducer producer; // Producer for sending messages

    public APIAccessObject(String queueName) throws JMSException {
        // Assign the queue name to the instance variable
        // The queue name for sending messages

        // Create a connection factory to the ActiveMQ broker
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        // Create and start a connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Create a session
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create a destination queue
        Destination destination = session.createQueue(queueName);

        // Create a producer from the session to the queue
        producer = session.createProducer(destination);
    }

    public void sendMessage(String text) throws JMSException {
        // Create a text message
        TextMessage message = session.createTextMessage(text);

        // Send the message to the queue
        producer.send(message);
    }


    public void close() throws JMSException {
        // Close the producer and session resources
        producer.close();
        session.close();
    }
}
    // Main method to test the MessageSender class
//    public static void main(String[] args) {
//        try {
//            // Create a MessageSender instance for the queue "ClientBQueue"
//            APIAccessObject sender = new APIAccessObject("ClientBQueue");
//
//            // Send a message
//            sender.sendMessage("Hello, Client B!");
//
//            // Close the sender to release resources
//            sender.close();
//        } catch (JMSException e) {
//            e.printStackTrace();
//        }
//    }
//}