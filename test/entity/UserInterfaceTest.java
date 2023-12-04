package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {

    @Test
    public void getUserID_ShouldReturnUserID() {
        // Arrange
        User user = new User("JohnDoe", "password");

        // Act
        String userID = user.getUserID();

        // Assert
        assertEquals("1", userID);
    }

    @Test
    public void getUsername_ShouldReturnUsername() {
        // Arrange
        User user = new User("JaneDoe", "password");

        // Act
        String username = user.getUsername();

        // Assert
        assertEquals("JaneDoe", username);
    }

    @Test
    public void getPassword_ShouldReturnPassword() {
        // Arrange
        User user = new User( "Alice", "secret123");

        // Act
        String password = user.getPassword();

        // Assert
        assertEquals("secret123", password);
    }
}