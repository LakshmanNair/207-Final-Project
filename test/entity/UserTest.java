package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void getUsername_ShouldReturnUsername() {
        // Arrange
        User user = new User("jane_doe", "password");

        // Act
        String username = user.getUsername();

        // Assert
        assertEquals("jane_doe", username);
    }

    @Test
    public void getPassword_ShouldReturnPassword() {
        // Arrange
        User user = new User("alice", "secret123");

        // Act
        String password = user.getPassword();

        // Assert
        assertEquals("secret123", password);
    }

//    @Test
//    public void setUserID_ShouldSetNewUserID() {
//        // Arrange
//        User user = new User("bob", "p@ssw0rd");
//
//        // Act
//        String oldUserID = user.getUserID();
//        String newUserID = user.setUserID();
//
//        // Assert
//        assertNotEquals(oldUserID, newUserID);
//    }

    @Test
    public void setUsername_ShouldSetNewUsername() {
        // Arrange
        User user = new User("charlie", "qwerty");

        // Act
        user.setUsername("charlie_new");

        // Assert
        assertEquals("charlie_new", user.getUsername());
    }

    @Test
    public void setPassword_ShouldSetNewPassword() {
        // Arrange
        User user = new User("diana", "old_password");

        // Act
        user.setPassword("new_password");

        // Assert
        assertEquals("new_password", user.getPassword());
    }
}