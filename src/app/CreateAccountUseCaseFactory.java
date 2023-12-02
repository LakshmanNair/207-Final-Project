package app;

import entity.UserFactory;
import interface_adapter.CreateAccount.CreateAccountController;
import interface_adapter.CreateAccount.CreateAccountPresenter;
import interface_adapter.CreateAccount.CreateAccountViewModel;
import interface_adapter.ViewManagerModel;
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
            ViewManagerModel viewManagerModel, CreateAccountViewModel createAccountViewModel, CreateAccountDataAccessInterface createAccountDataAccessInterface) {

        try {
            CreateAccountController createAccountController = createUserSignupUseCase(viewManagerModel, createAccountViewModel, createAccountDataAccessInterface);
            return new CreateAccountView(createAccountController, createAccountViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static CreateAccountController createUserSignupUseCase(ViewManagerModel viewManagerModel, CreateAccountViewModel createAccountViewModel, CreateAccountDataAccessInterface userDataAccessObject) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        CreateAccountOutputBoundary signupOutputBoundary = new CreateAccountPresenter(createAccountViewModel);

        UserFactory userFactory = new UserFactory();

        CreateAccountInputBoundary userSignupInteractor = new CreateAccountInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        return new CreateAccountController(userSignupInteractor);
    }
}
