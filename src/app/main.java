package app;

import data_access.APIAccessObject;
import entity.User;
import entity.UserFactory;
import interface_adapter.CreateAccount.CreateAccountViewModel;
import interface_adapter.PrivateChat.PrivateChatController;
import interface_adapter.PrivateChat.PrivateChatPresenter;
import interface_adapter.ViewManagerModel;
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
        JFrame application = new JFrame("Chat System");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        LoginViewModel loginViewModel = new LoginViewModel();
        CreateAccountViewModel createAccountViewModel = new CreateAccountViewModel();

        CreateAccountDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new CreateAccountDataAccessObject("./users.csv", new UserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        CreateAccountView createAccountView = CreateAccountUseCaseFactory.create(viewManagerModel, createAccountViewModel, userDataAccessObject);
        views.add(createAccountView, createAccountView.viewName);

        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, userDataAccessObject);
        views.add(loginView, loginView.viewName);

        MenuScreen menuScreen = new MenuScreen();
        views.add(menuScreen, "MenuScreen");

        // TODO: fix why private chat view is popping up first
//        PrivateChatView privateChatView = new PrivateChatView();
//        views.add(privateChatView, "PrivateChatView");

        viewManagerModel.setActiveView(createAccountView.viewName);
        viewManagerModel.firePropertyChanged();

//        public void LoginButtonListener(ActionListener listener) {
//            privateChatButton.addActionListener(listener);
//        }


        application.pack();
        application.setVisible(true);
    }
}
