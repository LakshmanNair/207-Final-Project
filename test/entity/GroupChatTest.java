package entity;

import interface_adapter.PrivateChat.MessageHistory;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroupChatTest {

    @Test
    public void addUser_ShouldAddUserToList() {
        // Arrange
        User user1 = new User("user1", "123");
        MessageHistory messages = new MessageHistory();
        GroupChat groupChat = new GroupChat(user1, messages);

        // Act
        User user2 = new User("user2", "456");
        groupChat.addUser(user2);

        // Assert
        List<User> users = groupChat.getUsers();
        assertTrue(users.contains(user2));
    }

    @Test
    public void addUser_ShouldNotAddDuplicateUser() {
        // Arrange
        User user1 = new User("user1", "123");
        MessageHistory messages = new MessageHistory();
        GroupChat groupChat = new GroupChat(user1, messages);

        // Act
        groupChat.addUser(user1);

        // Assert
        List<User> users = groupChat.getUsers();
        assertEquals(1, users.size());
    }

    @Test
    public void removeUser_AdminCanRemoveUser() {
        // Arrange
        User admin = new User("admin", "123");
        User userToRemove = new User("userToRemove", "789");
        MessageHistory messages = new MessageHistory();
        GroupChat groupChat = new GroupChat(admin, messages);
        groupChat.addUser(userToRemove);

        // Act
        groupChat.removeUser(admin, userToRemove);

        // Assert
        List<User> users = groupChat.getUsers();
        assertFalse(users.contains(userToRemove));
    }

    @Test
    public void removeUser_NonAdminCannotRemoveUser() {
        // Arrange
        User admin = new User("admin", "123");
        User nonAdmin = new User("nonAdmin", "123");
        User userToRemove = new User("userToRemove", "123");
        MessageHistory messages = new MessageHistory();
        GroupChat groupChat = new GroupChat(admin, messages);
        groupChat.addUser(userToRemove);

        // Act
        groupChat.removeUser(nonAdmin, userToRemove);

        // Assert
        List<User> users = groupChat.getUsers();
        assertTrue(users.contains(userToRemove)); // Ensure that the user list remains unchanged
    }


    @Test
    public void getUsers_ShouldReturnListOfUsers() {
        // Arrange
        User user1 = new User("user1", "123");
        User user2 = new User("user2", "567");
        MessageHistory messages = new MessageHistory();
        GroupChat groupChat = new GroupChat(user1, messages);
        groupChat.addUser(user2);

        // Act
        List<User> users = groupChat.getUsers();

        // Assert
        assertEquals(2, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }

    @Test
    public void getMessages_ShouldReturnMessageHistory() {
        // Arrange
        User user = new User("user", "123");
        MessageHistory messages = new MessageHistory();
        GroupChat groupChat = new GroupChat(user, messages);

        // Act
        MessageHistory retrievedMessages = groupChat.getMessages();

        // Assert
        assertEquals(messages, retrievedMessages);
    }

    @Test
    public void updateMessages_ShouldUpdateMessages() {
        // Arrange
        User user = new User("user", "123");
        MessageHistory initialMessages = new MessageHistory();
        GroupChat groupChat = new GroupChat(user, initialMessages);

        // Act
        MessageHistory updatedMessages = new MessageHistory();
        groupChat.updateMessages(updatedMessages);

        // Assert
        assertEquals(updatedMessages, groupChat.getMessages());
    }
}