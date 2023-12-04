package app;

import data_access.APIAccessObject;
import data_access.AccountFileDataAccessObject;
import entity.User;
import interface_adapter.PrivateChat.PrivateChatController;
import interface_adapter.PrivateChat.PrivateChatPresenter;
import use_case.send_message.SendMessageInputData;

import interface_adapter.ViewManagerModel;
import interface_adapter.editAccountInfo.EditAccountInfoController;
import interface_adapter.editAccountInfo.EditAccountInfoPresenter;
import interface_adapter.editAccountInfo.EditAccountInfoViewModel;
import interface_adapter.groupChat.GroupChatState;
import interface_adapter.groupChat.GroupChatViewModel;
import org.apache.activemq.ActiveMQConnection;
import use_case.edit_account_information.EditInteractor;
import use_case.send_message.SendMessageInteractor;
import view.GroupChatView;
import view.MenuScreen;
import view.PrivateChatView;
import view.EditAccountInfoView;

import javax.jms.JMSException;
import javax.swing.*;
import java.awt.*;

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

// Daniel's
//public class Application extends JPanel {
//    public static void showPrivateChat() {
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    APIAccessObject apiAccessObject = new APIAccessObject(ActiveMQConnection.DEFAULT_BROKER_URL);
//                    SendMessageInteractor sendMessageInteractor = new SendMessageInteractor(apiAccessObject);
//                    PrivateChatView chatView = new PrivateChatView();
//                    PrivateChatPresenter chatPresenter = new PrivateChatPresenter(chatView);
//                    PrivateChatController chatController = new PrivateChatController(sendMessageInteractor, new User("test", "pass"), apiAccessObject, chatView);
//
//                    sendMessageInteractor.setOutputBoundary(chatPresenter);
//                    chatView.setController(chatController);
//                    chatView.setVisible(true);
//                } catch (JMSException ex) {
//                    throw new RuntimeException(ex);
//                }
public class Application {
    public static void showChat(String username) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MenuScreen menuScreen = new MenuScreen();
                menuScreen.setPrivateChatButtonListener(e -> {
                    try {
                        APIAccessObject apiAccessObject = new APIAccessObject(ActiveMQConnection.DEFAULT_BROKER_URL);
                        SendMessageInteractor sendMessageInteractor = new SendMessageInteractor(apiAccessObject);
                        PrivateChatView chatView = new PrivateChatView();
                        PrivateChatPresenter chatPresenter = new PrivateChatPresenter(chatView);
                        PrivateChatController chatController = new PrivateChatController(sendMessageInteractor, new User("bb", "pass"), new User("xx", "pass"), apiAccessObject, chatView);

                        sendMessageInteractor.setOutputBoundary(chatPresenter);
                        chatView.setController(chatController);
                        chatView.setVisible(true);
                    } catch (JMSException ex) {
                        throw new RuntimeException(ex);
                    }
                });

                menuScreen.setGroupChatButtonListener(e -> {
                    try {
                        APIAccessObject apiAccessObject = new APIAccessObject(ActiveMQConnection.DEFAULT_BROKER_URL);
                        SendMessageInteractor sendMessageInteractor = new SendMessageInteractor(apiAccessObject);

                        GroupChatState groupChatState=new GroupChatState(new User(username,""));
                        GroupChatViewModel groupChatViewModel=new GroupChatViewModel(groupChatState);
                        GroupChatView groupChatView=GroupChatCaseFactory.create(groupChatViewModel,sendMessageInteractor);
                        groupChatView.setVisible(true);
                    } catch (JMSException ex) {
                        throw new RuntimeException(ex);
                    }
                    // Logic to open group chat
                });

                menuScreen.setEditAccountInfoButtonListener(e -> {
                    AccountFileDataAccessObject editAccountInfoDataAccessObject = new AccountFileDataAccessObject("./users.csv");
                    EditAccountInfoViewModel editAccountInfoViewModel = new EditAccountInfoViewModel();
                    EditInteractor editInteractor = new EditInteractor(editAccountInfoDataAccessObject);
                    EditAccountInfoView editAccountInfoView = new EditAccountInfoView(editAccountInfoViewModel);
                    EditAccountInfoController editAccountInfoController = new EditAccountInfoController(editInteractor);
                    EditAccountInfoPresenter editPresenter = new EditAccountInfoPresenter(editAccountInfoView);

                    editInteractor.setOutputBoundary(editPresenter);
                    editAccountInfoView.setController(editAccountInfoController);
                    editAccountInfoView.pack();
                    editAccountInfoView.setVisible(true);
                });

                // Show the MenuScreen
//                menuScreen.createAndShowGUI();
            }
        });
    }
    public static void showGroupChat() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Logic to open group chat
            };
        });
    };
}


