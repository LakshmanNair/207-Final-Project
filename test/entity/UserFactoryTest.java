package entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserFactoryTest {

    @Test
    public void createUser_ShouldCreateUserWithGivenUsernameAndPassword() {
        // Arrange
        UserFactory userFactory = new UserFactory();

        // Act
        User user = userFactory.createUser("alex", "password123");

        // Assert
        assertNotNull(user);
        assertEquals("alex", user.getUsername());
        assertEquals("password123", user.getPassword());
    }
}