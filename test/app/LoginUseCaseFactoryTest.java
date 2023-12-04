package app;

import entity.User;
import interface_adapter.ViewManagerModel;
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
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, userDataAccessObject);

        // Assert
        assertNotNull(loginView);
    }
}