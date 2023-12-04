package app;
import data_access.APIAccessObject;
import data_access.ReceiveMessageWorker;
import entity.User;
import interface_adapter.PrivateChat.PrivateChatController;
import org.apache.activemq.ActiveMQConnection;
import use_case.send_message.SendMessageInteractor;
import view.MenuScreen;
import view.PrivateChatView;

import javax.jms.JMSException;
import javax.swing.*;

public class UItesting {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // The main application window.
            JFrame application = new JFrame("Chat Application");
            application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Directly show the MenuScreen
            MenuScreen menuScreen = new MenuScreen();
            menuScreen.setPrivateChatButtonListener(e -> {
                try {
                    onPrivateChatButtonClicked();
                } catch (JMSException ex) {
                    throw new RuntimeException(ex);
                }
            });
            menuScreen.setGroupChatButtonListener(e -> {
                // Logic for opening group chat
            });
        });
    }

    private static void onPrivateChatButtonClicked() throws JMSException {
        // ... (other initializations)

        // Initialize the chat view
        PrivateChatView chatView = new PrivateChatView();

        // Initialize the controller
        APIAccessObject apiAccessObject = new APIAccessObject(ActiveMQConnection.DEFAULT_BROKER_URL);
        SendMessageInteractor sendMessageInteractor = new SendMessageInteractor(apiAccessObject);
        User currentUser = new User("a", "a");
        User recipient = new User("b", "b");
        PrivateChatController chatController = new PrivateChatController(sendMessageInteractor, currentUser, recipient, apiAccessObject, chatView);

        // Set the controller in the view
        chatView.setController(chatController);

        // Instantiate and execute ReceiveMessageWorker
        ReceiveMessageWorker messageWorker = new ReceiveMessageWorker(apiAccessObject, currentUser.getUsername(), chatView);
        messageWorker.execute();

        // Show the chat view
        chatView.setVisible(true);
    }
}
