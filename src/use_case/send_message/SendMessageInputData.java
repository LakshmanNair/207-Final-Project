package use_case.send_message;
import data_access.APIAccessObject;
import entity.User;

import javax.jms.JMSException;
import javax.jms.QueueConnection;
import java.time.LocalDateTime;

public class SendMessageInputData {
    // existing fields
    private QueueConnection queueConnection;

    public QueueConnection getQueueConnection() {
        return queueConnection;
    }

    public void setQueueConnection(QueueConnection queueConnection) {
        this.queueConnection = queueConnection;
    }
//    Get message data from controller
    private String content;

    public User sender;
    private LocalDateTime timestamp;

    public SendMessageInputData(String content, User user, LocalDateTime timestamp) throws JMSException {
        this.content = content;
        this.timestamp = timestamp;
        this.sender = user;
    }


    private User getUser() {
        return sender;
    }
    private String getContent() {
        return content;
    }
}

