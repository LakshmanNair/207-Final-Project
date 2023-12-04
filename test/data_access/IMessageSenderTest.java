package data_access;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.jms.JMSException;

import static org.junit.jupiter.api.Assertions.*;

class IMessageSenderTest {

    private IMessageSender messageSender;

    @BeforeEach
    void setUp() {
        // Initialize your implementation of IMessageSender, e.g., MessageSender
        messageSender = new IMessageSender(/* Pass necessary dependencies */) {
            @Override
            public void sendMessage(String text) throws JMSException {

            }

            @Override
            public void close() throws JMSException {

            }
        };
    }

    @AfterEach
    void tearDown() throws JMSException {
        // Close resources after each test
        if (messageSender != null) {
            messageSender.close();
        }
    }

    @Test
    void sendMessage_ShouldNotThrowException() {
        // Ensure that sendMessage does not throw an exception
        assertDoesNotThrow(() -> messageSender.sendMessage("Hello, world!"));
    }

    @Test
    void close_ShouldNotThrowException() {
        // Ensure that close does not throw an exception
        assertDoesNotThrow(() -> messageSender.close());
    }
}