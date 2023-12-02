package app;

import data_access.APIAccessObject;
import entity.User;
import entity.UserFactory;
import interface_adapter.CreateAccount.CreateAccountViewModel;
import interface_adapter.PrivateChat.PrivateChatController;
import interface_adapter.PrivateChat.PrivateChatPresenter;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import org.apache.activemq.ActiveMQConnection;
import use_case.login.LoginUserDataAccessInterface;
import use_case.send_message.SendMessageInteractor;
import view.*;
import data_access.CreateAccountDataAccessObject;

import javax.jms.JMSException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class main {
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

        CreateAccountView createAccountView = CreateAccountUseCaseFactory.create(viewManagerModel, createAccountViewModel, userDataAccessObject);
        views.add(createAccountView, createAccountView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        viewManagerModel.setActiveView(createAccountView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MenuScreen menuScreen = new MenuScreen();
                menuScreen.setPrivateChatButtonListener(e -> {
                    APIAccessObject apiAccessObject = null;
                    try {
                        apiAccessObject = new APIAccessObject(ActiveMQConnection.DEFAULT_BROKER_URL);
                    } catch (JMSException ex) {
                        throw new RuntimeException(ex);
                    }

                    // Initialize the interactor with the data access object
                    SendMessageInteractor sendMessageInteractor = new SendMessageInteractor(apiAccessObject);

                    // Initialize the view
                    PrivateChatView chatView = new PrivateChatView();

                    // Initialize the presenter with the view
                    PrivateChatPresenter chatPresenter = new PrivateChatPresenter(chatView);

                    // Initialize the controller with the interactor and view
                    PrivateChatController chatController = new PrivateChatController(sendMessageInteractor, new User("test", "pass"), apiAccessObject, chatView);

                    // Set the presenter in the interactor
                    sendMessageInteractor.setOutputBoundary(chatPresenter);

                    // Set the controller in the view
                    chatView.setController(chatController);

                    // Start the application (e.g., make the main window visible)
                    chatView.setVisible(true);                });
                menuScreen.setGroupChatButtonListener(e -> {
                    // Logic to open group chat
                });
            }
        });
    }
}

