package entity;

import java.time.LocalDateTime;

public class MessageFactory {
    public Message create(String content, User user, LocalDateTime timestamp) {
        return new Message(content, user, timestamp);
    }
}