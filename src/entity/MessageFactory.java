package entity;

import java.time.LocalDateTime;

public class MessageFactory implements MessageFactoryInterface {
    public Message createMessage(String content, User user, LocalDateTime timestamp) {
        return new Message(content, user, timestamp);
    }
}