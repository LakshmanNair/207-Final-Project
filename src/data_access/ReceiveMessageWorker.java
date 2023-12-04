package data_access;

import entity.User;
import org.apache.activemq.transport.stomp.Stomp;
import view.PrivateChatView;
import interface_adapter.PrivateChat.PrivateChatController;
import use_case.send_message.SendMessageInteractor;
import use_case.send_message.SendMessageInputData;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class ReceiveMessageWorker extends SwingWorker<Void, Void> {
    private final APIAccessObject apiAccessObject;
    private final String recipientUsername; // Username of the user receiving messages
    private final PrivateChatView chatView; // Chat view associated with the recipient

    public ReceiveMessageWorker(APIAccessObject apiAccessObject, String recipientUsername, PrivateChatView chatView) {
        this.apiAccessObject = apiAccessObject;
        this.recipientUsername = recipientUsername;
        this.chatView = chatView;
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (!isCancelled()) {
            String message = apiAccessObject.receiveMessage(recipientUsername);
            if (message != null) {
                SwingUtilities.invokeLater(() -> chatView.displayMessage(message));
            }
            Thread.sleep(1000); // Polling interval
        }
        return null;
    }

//    private boolean isChatWindowOpenForSender(SendMessageInputData inputData) {
//        return openChatWindows.containsKey(inputData.getSender().getUsername());
//    }
//
//    private void openChatWindowForSender(SendMessageInputData inputData) {
//        if (!openChatWindows.containsKey(inputData.getSender().getUsername())) {
//            PrivateChatView chatView = new PrivateChatView();
//            User sender = new User(inputData.getSender().getUsername(), ""); // Placeholder for sender user
//            User recipient = new User(inputData.getRecipient().getUsername(), ""); // Placeholder for recipient user
//
//            SendMessageInteractor sendMessageInteractor = new SendMessageInteractor(apiAccessObject);
//            PrivateChatController chatController = new PrivateChatController(sendMessageInteractor, recipient, sender, apiAccessObject, chatView);
//
//            chatView.setController(chatController);
//            chatView.setVisible(true); // Show the chat window
//            openChatWindows.put(inputData.getSender().getUsername(), chatView); // Keep track of the open window
//        }
//    }
//
//    private void updateChatWindowWithMessage(SendMessageInputData input) {
//        PrivateChatView chatView = openChatWindows.get(input.getSender().getUsername());
//        if (chatView != null) {
//            chatView.displayMessage(input.getContent());
//        }
//    }

}
