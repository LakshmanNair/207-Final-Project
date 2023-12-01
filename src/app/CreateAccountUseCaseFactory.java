package app;

import entity.UserFactory;
import interface_adapter.CreateAccount.CreateAccountController;
import interface_adapter.CreateAccount.CreateAccountPresenter;
import interface_adapter.CreateAccount.CreateAccountViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import use_case.create_account.CreateAccountDataAccessInterface;
import use_case.create_account.CreateAccountInputBoundary;
import use_case.create_account.CreateAccountInteractor;
import use_case.create_account.CreateAccountOutputBoundary;
import view.CreateAccountView;

import javax.swing.*;
import java.io.IOException;

public class CreateAccountUseCaseFactory {
    /** Prevent instantiation. */
    private CreateAccountUseCaseFactory() {}

    public static CreateAccountView create(
            ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, CreateAccountViewModel createAccountViewModel, CreateAccountDataAccessInterface userDataAccessObject) {

        try {
            CreateAccountController createAccountController = createUserSignupUseCase(viewManagerModel, createAccountViewModel, loginViewModel, userDataAccessObject);
            return new CreateAccountView(createAccountController, createAccountViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static CreateAccountController createUserSignupUseCase(ViewManagerModel viewManagerModel, CreateAccountViewModel createAccountViewModel, LoginViewModel loginViewModel, CreateAccountDataAccessInterface userDataAccessObject) throws IOException {

        CreateAccountOutputBoundary signupOutputBoundary = new CreateAccountPresenter(viewManagerModel, createAccountViewModel, loginViewModel);

        UserFactory userFactory = new UserFactory();

        CreateAccountInputBoundary userSignupInteractor = new CreateAccountInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new CreateAccountController(userSignupInteractor);
    }
}
