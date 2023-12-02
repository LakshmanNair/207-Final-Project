package app;

import data_access.APIAccessObject;
import entity.User;
import interface_adapter.PrivateChat.PrivateChatController;
import interface_adapter.PrivateChat.PrivateChatPresenter;


import org.apache.activemq.ActiveMQConnection;
import use_case.send_message.SendMessageInteractor;
import view.MenuScreen;
import view.PrivateChatView;

import javax.jms.JMSException;
import javax.swing.*;

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


public class Application {
    public static void showChat() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MenuScreen menuScreen = new MenuScreen();
                menuScreen.setPrivateChatButtonListener(e -> {
                    try {
                        APIAccessObject apiAccessObject = new APIAccessObject(ActiveMQConnection.DEFAULT_BROKER_URL);
                        SendMessageInteractor sendMessageInteractor = new SendMessageInteractor(apiAccessObject);
                        PrivateChatView chatView = new PrivateChatView();
                        PrivateChatPresenter chatPresenter = new PrivateChatPresenter(chatView);
                        PrivateChatController chatController = new PrivateChatController(sendMessageInteractor, new User("test", "pass"), apiAccessObject, chatView);

                        sendMessageInteractor.setOutputBoundary(chatPresenter);
                        chatView.setController(chatController);
                        chatView.setVisible(true);
                    } catch (JMSException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                menuScreen.setGroupChatButtonListener(e -> {
                    // Logic to open group chat
                });

                // Show the MenuScreen
//                menuScreen.createAndShowGUI();
            }
        });
    }

    // Main method or other methods...
}

