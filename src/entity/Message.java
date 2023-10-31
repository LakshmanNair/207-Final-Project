package entity;
import java.time.LocalDateTime;
import entity.User;

public class Message {
    private final String content;

    public User sender;
    private final LocalDateTime timestamp;

    public Message(String content, User user, LocalDateTime timestamp) {
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
