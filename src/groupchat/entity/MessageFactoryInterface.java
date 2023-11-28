package entity;

import java.time.LocalDateTime;

public interface MessageFactoryInterface {
    Message createMessage(String content, User user, LocalDateTime timestamp);
}
