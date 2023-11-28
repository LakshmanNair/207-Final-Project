package app;

import data_access.APIAccessObject;
import entity.User;
import interface_adapter.PrivateChat.PrivateChatController;
import interface_adapter.PrivateChat.PrivateChatPresenter;


import org.apache.activemq.ActiveMQConnection;
import use_case.send_message.SendMessageInteractor;
import view.PrivateChatView;

import javax.jms.JMSException;

public class main {
    public static void main(String[] args) throws JMSException {
        // Set up the necessary components for the application

        // Example: Initialize the APIAccessObject or similar data access object
        APIAccessObject apiAccessObject = new APIAccessObject(ActiveMQConnection.DEFAULT_BROKER_URL);

        // Initialize the interactor with the data access object
        SendMessageInteractor sendMessageInteractor = new SendMessageInteractor(apiAccessObject);

        // Initialize the view
        PrivateChatView chatView = new PrivateChatView();

        // Initialize the presenter with the view
        PrivateChatPresenter chatPresenter = new PrivateChatPresenter(chatView);

        // Initialize the controller with the interactor and view
        PrivateChatController chatController = new PrivateChatController(sendMessageInteractor, new User("test", "pass"));

        // Set the presenter in the interactor
        sendMessageInteractor.setOutputBoundary(chatPresenter);

        // Set the controller in the view
        chatView.setController(chatController);

        // Start the application (e.g., make the main window visible)
        chatView.setVisible(true);
    }
}
