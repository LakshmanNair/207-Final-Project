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
    private final SendMessageInputData inputData; // Username of the user receiving messages
    private final Map<String, PrivateChatView> openChatWindows;

    public ReceiveMessageWorker(APIAccessObject apiAccessObject, SendMessageInputData inputData) {
        this.apiAccessObject = apiAccessObject;
        this.inputData = inputData;
        this.openChatWindows = new HashMap<>();
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (!isCancelled()) {
            String message = apiAccessObject.receiveMessage(inputData.getRecipient().getUsername());
            if (message != null) {
                SwingUtilities.invokeLater(() -> {
                    SendMessageInputData input = inputData; // Method to get sender's username from your system
                    if (!isChatWindowOpenForSender(input)) {
                        openChatWindowForSender(input);
                    }
                    updateChatWindowWithMessage(input);
                });
            }
            Thread.sleep(1000); // Polling interval
        }
        return null;
    }

    private boolean isChatWindowOpenForSender(SendMessageInputData inputData) {
        return openChatWindows.containsKey(inputData.getSender().getUsername());
    }

    private void openChatWindowForSender(SendMessageInputData inputData) {
        if (!openChatWindows.containsKey(inputData.getSender().getUsername())) {
            PrivateChatView chatView = new PrivateChatView();
            User sender = new User(inputData.getSender().getUsername(), ""); // Placeholder for sender user
            User recipient = new User(inputData.getRecipient().getUsername(), ""); // Placeholder for recipient user

            SendMessageInteractor sendMessageInteractor = new SendMessageInteractor(apiAccessObject);
            PrivateChatController chatController = new PrivateChatController(sendMessageInteractor, recipient, sender, apiAccessObject, chatView);

            chatView.setController(chatController);
            chatView.setVisible(true); // Show the chat window
            openChatWindows.put(inputData.getSender().getUsername(), chatView); // Keep track of the open window
        }
    }

    private void updateChatWindowWithMessage(SendMessageInputData input) {
        PrivateChatView chatView = openChatWindows.get(input.getSender().getUsername());
        if (chatView != null) {
            chatView.displayMessage(input.getContent());
        }
    }

}
