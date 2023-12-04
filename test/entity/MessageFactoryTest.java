package entity;

import org.junit.jupiter.api.Test;
import use_case.send_message.SendMessageInputData;

import static org.junit.jupiter.api.Assertions.*;

class MessageFactoryTest {

    @Test
    public void createMessage_ShouldCreateMessageWithInputData() {
        // Arrange
        User sender = new User("JohnDoe", "123");
        String content = "Hello, World!";
        User recipient = new User("AlexDoe", "456");
        SendMessageInputData input = new SendMessageInputData(content, sender, recipient);

        // Act
        MessageFactory messageFactory = new MessageFactory();
        Message createdMessage = messageFactory.createMessage(input);

        // Assert
        assertEquals(content, createdMessage.getContent());
        assertEquals(sender, createdMessage.getUser());
        //assertNotNull(createdMessage.getTimestamp()); // Assuming you have a getTimestamp() method in the Message class
    }

//    @Test
//    public void createMessage_ShouldUseCurrentTimestamp() {
//        // Arrange
//        User sender = new User("JaneDoe");
//        String content = "How are you?";
//        SendMessageInputData input = new SendMessageInputData(content, sender);
//
//        // Act
//        MessageFactory messageFactory = new MessageFactory();
//        Message firstMessage = messageFactory.createMessage(input);
//
//        // Create another message after a short delay
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        Message secondMessage = messageFactory.createMessage(input);
//
//        // Assert
//        assertNotEquals(firstMessage.getTimestamp(), secondMessage.getTimestamp());
//    }

    @Test
    public void createMessage_ShouldNotModifyInputData() {
        // Arrange
        User sender = new User("Alice", "910");
        String content = "Testing!";
        User recipient = new User("Dave", "314");
        SendMessageInputData input = new SendMessageInputData(content, sender, recipient);

        // Act
        MessageFactory messageFactory = new MessageFactory();
        messageFactory.createMessage(input);

        // Assert
        assertEquals("Alice", sender.getUsername()); // Ensure that the original input data is not modified
        assertEquals("Testing!", content);
    }
}