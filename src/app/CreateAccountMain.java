package app;

import entity.UserFactory;
import interface_adapter.CreateAccount.CreateAccountViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import view.CreateAccountView;
import view.ViewManager;
import data_access.CreateAccountDataAccessObject;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CreateAccountMain {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Create Account");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        LoginViewModel loginViewModel = new LoginViewModel();
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        CreateAccountViewModel createAccountViewModel = new CreateAccountViewModel();

        CreateAccountDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new CreateAccountDataAccessObject("./users.csv", new UserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CreateAccountView createAccountView = CreateAccountUseCaseFactory.create(viewManagerModel,loginViewModel, createAccountViewModel, userDataAccessObject);
        views.add(createAccountView, createAccountView.viewName);


        viewManagerModel.setActiveView(createAccountView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}

