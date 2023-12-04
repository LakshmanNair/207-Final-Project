package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserFactoryInterfaceTest {

    @Test
    public void createUser_ShouldCreateUserWithGivenUsernameAndPassword() {
        // Arrange
        UserFactoryInterface userFactory = new UserFactory();

        // Act
        User user = userFactory.createUser("john_doe", "password123");

        // Assert
        assertNotNull(user);
        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
    }
}