package use_case.create_account;

import entity.User;
import entity.UserFactoryInterface;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreateAccountInteractorTest {

    private static class CreateAccountDataAccessStub implements CreateAccountDataAccessInterface {
        private boolean userExists;

        public CreateAccountDataAccessStub(boolean userExists) {
            this.userExists = userExists;
        }

        @Override
        public User get(String username) {
            return null;
        }

        @Override
        public boolean existsByName(String username) {
            return userExists;
        }

        @Override
        public void save(User user) {
            // Do nothing for the stub
        }
    }

    private static class CreateAccountOutputBoundaryStub implements CreateAccountOutputBoundary {
        private String errorMessage;
        private CreateAccountOutputData successData;

        @Override
        public void prepareSuccessView(CreateAccountOutputData user) {
            this.successData = user;
        }

        @Override
        public void prepareFailView(String error) {
            this.errorMessage = error;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public CreateAccountOutputData getSuccessData() {
            return successData;
        }
    }

    private static class UserFactoryStub implements UserFactoryInterface {
        @Override
        public User createUser(String username, String password) {
            return new User(username, password);
        }
    }

    @Test
    void execute_UserExists() {
        // Arrange
        CreateAccountDataAccessStub dataAccessStub = new CreateAccountDataAccessStub(true);
        CreateAccountOutputBoundaryStub presenterStub = new CreateAccountOutputBoundaryStub();
        UserFactoryStub userFactoryStub = new UserFactoryStub();

        CreateAccountInteractor interactor = new CreateAccountInteractor(dataAccessStub, presenterStub, userFactoryStub);
        CreateAccountInputData input = new CreateAccountInputData("existingUser", "password", "password");

        // Act
        interactor.execute(input);

        // Assert
        assertEquals("User already exists.", presenterStub.getErrorMessage());
        assertNull(presenterStub.getSuccessData());
    }

    @Test
    void execute_PasswordsDoNotMatch() {
        // Arrange
        CreateAccountDataAccessStub dataAccessStub = new CreateAccountDataAccessStub(false);
        CreateAccountOutputBoundaryStub presenterStub = new CreateAccountOutputBoundaryStub();
        UserFactoryStub userFactoryStub = new UserFactoryStub();

        CreateAccountInteractor interactor = new CreateAccountInteractor(dataAccessStub, presenterStub, userFactoryStub);
        CreateAccountInputData input = new CreateAccountInputData("newUser", "password1", "password2");

        // Act
        interactor.execute(input);

        // Assert
        assertEquals("Passwords don't match.", presenterStub.getErrorMessage());
        assertNull(presenterStub.getSuccessData());
    }

    @Test
    void execute_Success() {
        // Arrange
        CreateAccountDataAccessStub dataAccessStub = new CreateAccountDataAccessStub(false);
        CreateAccountOutputBoundaryStub presenterStub = new CreateAccountOutputBoundaryStub();
        UserFactoryStub userFactoryStub = new UserFactoryStub();

        CreateAccountInteractor interactor = new CreateAccountInteractor(dataAccessStub, presenterStub, userFactoryStub);
        CreateAccountInputData input = new CreateAccountInputData("newUser", "password", "password");

        // Act
        interactor.execute(input);

        // Assert
        assertNull(presenterStub.getErrorMessage());
        assertNotNull(presenterStub.getSuccessData());
    }
}