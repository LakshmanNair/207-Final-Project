package data_access;

import javax.jms.JMSException;

// Interface for the API Access Object
public interface IMessageSender {
    void sendMessage(String text) throws JMSException;

    void close() throws JMSException;
}
