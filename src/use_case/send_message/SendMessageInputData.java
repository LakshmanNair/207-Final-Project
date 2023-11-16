package use_case.send_message;
import data_access.APIAccessObject;
import entity.User;

import javax.jms.JMSException;
import java.time.LocalDateTime;

public class SendMessageInputData {

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

