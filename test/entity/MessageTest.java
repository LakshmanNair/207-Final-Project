package entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

//    public void getContent_ShouldReturnContent() {
//        // Arrange
//        User sender = new User("JohnDoe", "123");
//        LocalDateTime timestamp = LocalDateTime.now();
//        Message message = new Message("Hello, World!", sender, timestamp);
//
//        // Act
//        String content = message.getContent();
//
//        // Assert
//        assertEquals("Hello, World!", content);
//    }

    @Test
    public void getUser_ShouldReturnSender() {
        // Arrange
        User sender = new User("JohnDoe", "123");
        LocalDateTime timestamp = LocalDateTime.now();
        Message message = new Message("How are you?", sender, timestamp);

        // Act
        User user = message.getUser();

        // Assert
        assertEquals(sender, user);
    }

    @Test
    public void constructor_ShouldSetContentAndSenderAndTimestamp() {
        // Arrange
        User sender = new User("JohnDoe", "123");
        LocalDateTime timestamp = LocalDateTime.now();

        // Act
        Message message = new Message("This is a test message.", sender, timestamp);

        // Assert
        assertEquals("This is a test message.", message.getContent());
        assertEquals(sender, message.getUser());
    }
}