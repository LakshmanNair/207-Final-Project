package app;

import entity.User;
import interface_adapter.CreateAccount.CreateAccountController;
import interface_adapter.CreateAccount.CreateAccountViewModel;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;
import use_case.create_account.CreateAccountDataAccessInterface;
import view.CreateAccountView;

import static org.junit.jupiter.api.Assertions.*;

class CreateAccountUseCaseFactoryTest {

    @Test
    public void create() {
        // Arrange
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        CreateAccountViewModel createAccountViewModel = new CreateAccountViewModel();
        CreateAccountDataAccessInterface userDataAccessObject = new CreateAccountDataAccessInterface() {
            @Override
            public User get(String username) {
                return null;
            }

            @Override
            public boolean existsByName(String username) {
                return false;
            }

            @Override
            public void save(User user) {

            }
        };

        // Act
        CreateAccountView createAccountView = CreateAccountUseCaseFactory.create(viewManagerModel, createAccountViewModel, userDataAccessObject);

        // Assert
        assertNotNull(createAccountView);
    }
}