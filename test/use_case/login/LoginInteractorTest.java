package use_case.login;

import entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginInteractorTest {

    private static class LoginUserDataAccessStub implements LoginUserDataAccessInterface {
        private final User existingUser;

        public LoginUserDataAccessStub(User existingUser) {
            this.existingUser = existingUser;
        }

        @Override
        public User get(String username) {
            return existingUser;
        }

        @Override
        public boolean existsByName(String username) {
            return existingUser != null && existingUser.getUsername().equals(username);
        }

        @Override
        public void save(User user) {

        }
    }

    private static class LoginOutputBoundaryStub implements LoginOutputBoundary {
        private LoginOutputData outputData;

        @Override
        public void loginSuccess(LoginOutputData outputData) {
            this.outputData = outputData;
        }

        @Override
        public void loginFail(String errorMessage) {
            this.outputData = new LoginOutputData(errorMessage, true);
        }

        public LoginOutputData getOutputData() {
            return outputData;
        }
    }

    @Test
    void execute_SuccessfulLogin() {
        // Arrange
        User existingUser = new User("testUser", "testPassword");
        LoginUserDataAccessStub userDataAccessStub = new LoginUserDataAccessStub(existingUser);
        LoginOutputBoundaryStub outputBoundaryStub = new LoginOutputBoundaryStub();

        LoginInteractor interactor = new LoginInteractor(userDataAccessStub, outputBoundaryStub);
        LoginInputData inputData = new LoginInputData("testUser", "testPassword");

        // Act
        interactor.execute(inputData);

        // Assert
        assertEquals("testUser", outputBoundaryStub.getOutputData().getUsername());
    }

    @Test
    void execute_IncorrectUsername() {
        // Arrange
        User existingUser = new User("testUser", "testPassword");
        LoginUserDataAccessStub userDataAccessStub = new LoginUserDataAccessStub(existingUser);
        LoginOutputBoundaryStub outputBoundaryStub = new LoginOutputBoundaryStub();

        LoginInteractor interactor = new LoginInteractor(userDataAccessStub, outputBoundaryStub);
        LoginInputData inputData = new LoginInputData("wrongUser", "testPassword");

        // Act
        interactor.execute(inputData);

        // Assert
        assertEquals("Incorrect username.",outputBoundaryStub.getOutputData().getUsername());
    }

    @Test
    void execute_IncorrectPassword() {
        // Arrange
        User existingUser = new User("testUser", "testPassword");
        LoginUserDataAccessStub userDataAccessStub = new LoginUserDataAccessStub(existingUser);
        LoginOutputBoundaryStub outputBoundaryStub = new LoginOutputBoundaryStub();

        LoginInteractor interactor = new LoginInteractor(userDataAccessStub, outputBoundaryStub);
        LoginInputData inputData = new LoginInputData("testUser", "wrongPassword");

        // Act
        interactor.execute(inputData);

        // Assert
        assertEquals("Incorrect password.", outputBoundaryStub.getOutputData().getUsername());
    }
}