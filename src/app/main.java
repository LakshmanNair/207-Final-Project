package app;

import data_access.APIAccessObject;
import data_access.CreateAccountDataAccessObject;
import entity.User;
import entity.UserFactory;
import interface_adapter.CreateAccount.CreateAccountViewModel;
import interface_adapter.PrivateChat.PrivateChatController;
import interface_adapter.PrivateChat.PrivateChatPresenter;


import org.apache.activemq.ActiveMQConnection;
import use_case.send_message.SendMessageInteractor;
import view.MenuScreen;
import view.PrivateChatView;

import javax.jms.JMSException;
import javax.swing.*;
import view.CreateAccountView;
import view.PrivateChatView;

import javax.jms.JMSException;
import java.io.IOException;

//public class main {
//    public static void main(String[] args) throws JMSException {
//        // Set up the necessary components for the application
//
//        MenuScreen.createAndShowGUI(); // structure the code w conditionals
////
////// In your main application setup
////        APIAccessObject apiAccessObject = new APIAccessObject(/* broker URL or other config */);
////        User recipient = /* get or create the User instance */;
////        PrivateChatView chatView = new PrivateChatView();
////
////// Link everything together
////        PrivateChatController chatController = new PrivateChatController(apiAccessObject, chatView, recipient);
////        chatView.setController(chatController);
////
////// Now your chatView and controller are properly set up
////        chatView.setVisible(true);
////
//
//        // Example: Initialize the APIAccessObject or similar data access object
//        APIAccessObject apiAccessObject = new APIAccessObject(ActiveMQConnection.DEFAULT_BROKER_URL);
//
//        // Initialize the interactor with the data access object
//        SendMessageInteractor sendMessageInteractor = new SendMessageInteractor(apiAccessObject);
//
//        // Initialize the view
//        PrivateChatView chatView = new PrivateChatView();
//
//        // Initialize the presenter with the view
//        PrivateChatPresenter chatPresenter = new PrivateChatPresenter(chatView);
//
//        // Initialize the controller with the interactor and view
//        PrivateChatController chatController = new PrivateChatController(sendMessageInteractor, new User("test", "pass"), apiAccessObject, chatView);
//
//        // Set the presenter in the interactor
//        sendMessageInteractor.setOutputBoundary(chatPresenter);
//
//        // Set the controller in the view
//        chatView.setController(chatController);
//
//        // Start the application (e.g., make the main window visible)
//        chatView.setVisible(true);
//    }
//}

public class main {
    public static void main(String[] args) {
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
