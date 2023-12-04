package app;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginViewModel;
import org.junit.jupiter.api.Test;
import use_case.login.LoginUserDataAccessInterface;
import view.LoginView;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class LoginUseCaseFactoryTest {

    @Test
    public void create() throws IOException {
        // Arrange
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        LoginUserDataAccessInterface userDataAccessObject = new LoginUserDataAccessInterface() {
            @Override
            public boolean existsByName(String identifier) {
                return false;
            }

            @Override
            public void save(User user) {
            }

            @Override
            public User get(String username) {
                return null;
            }
        };

        // Act
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);

        // Assert
        assertNotNull(loginView);
    }
}