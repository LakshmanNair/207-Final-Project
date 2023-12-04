package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInterfaceTest {

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