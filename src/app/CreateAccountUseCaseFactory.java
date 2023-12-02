package app;

import entity.UserFactory;
import entity.UserFactoryInterface;
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
            try {
                throw e; // Propagate the exception further
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        //return null;
    }

    private static CreateAccountController createUserSignupUseCase(ViewManagerModel viewManagerModel, CreateAccountViewModel createAccountViewModel, LoginViewModel loginViewModel, CreateAccountDataAccessInterface userDataAccessObject) throws IOException {

        CreateAccountOutputBoundary createAccountOutputBoundary = new CreateAccountPresenter(viewManagerModel, createAccountViewModel, loginViewModel);

        UserFactoryInterface userFactoryInterface = new UserFactory();

        CreateAccountInputBoundary userSignupInteractor = new CreateAccountInteractor(
                userDataAccessObject, createAccountOutputBoundary, userFactoryInterface);
        return new CreateAccountController(userSignupInteractor);
    }
}
