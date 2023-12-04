package data_access;

import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CreateAccountDataAccessObjectTest {

    private static final String TEST_CSV_PATH = "test_users.csv";
    private CreateAccountDataAccessObject dataAccessObject;

    @BeforeEach
    public void setUp() throws IOException {
        // Initialize the data access object with a test CSV file and a mock UserFactory
        dataAccessObject = new CreateAccountDataAccessObject(TEST_CSV_PATH, new UserFactory());
    }

    @Test
    public void save_ShouldSaveUserToAccounts() {
        // Arrange
        User testUser = new User("test_user", "test_password");

        // Act
        dataAccessObject.save(testUser);

        // Assert
        assertTrue(dataAccessObject.existsByName("test_user"));
    }

    @Test
    public void get_ShouldRetrieveUserByUsername() {
        // Arrange
        User testUser = new User("test_user", "test_password");
        dataAccessObject.save(testUser);

        // Act
        User retrievedUser = dataAccessObject.get("test_user");

        // Assert
        assertNotNull(retrievedUser);
        assertEquals("test_user", retrievedUser.getUsername());
        assertEquals("test_password", retrievedUser.getPassword());
    }

    @Test
    public void existsByName_ShouldReturnTrueForExistingUser() {
        // Arrange
        User testUser = new User("test_user", "test_password");
        dataAccessObject.save(testUser);

        // Act
        boolean exists = dataAccessObject.existsByName("test_user");

        // Assert
        assertTrue(exists);
    }

    @Test
    public void existsByName_ShouldReturnFalseForNonExistingUser() {
        // Act
        boolean exists = dataAccessObject.existsByName("non_existing_user");

        // Assert
        assertFalse(exists);

    }
}