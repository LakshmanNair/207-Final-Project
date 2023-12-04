package interface_adapter.PrivateChat;

import data_access.APIAccessObject;
import data_access.ReceiveMessageWorker;
import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInputData;
import entity.User;
import view.PrivateChatView;

// Example in a UI controller class
//public class PrivateChatController {
//    private final SendMessageInputBoundary sendMessageInteractor;
//
//    public PrivateChatController(SendMessageInputBoundary sendMessageInteractor) {
//        this.sendMessageInteractor = sendMessageInteractor;
//    }
//
//    public void handleSendMessage(String content, User sender, User recipient) {
//        SendMessageInputData inputData = new SendMessageInputData(content, sender, recipient);
//        sendMessageInteractor.sendMessage(inputData);
//    }
//}
//public class PrivateChatController {
//    private final SendMessageInputBoundary sendMessageInteractor;
//    private final User currentUser; // Assuming I am given the current user
//    private final APIAccessObject apiAccessObject;
//    private final PrivateChatView chatView;
//
//
//    public PrivateChatController(SendMessageInputBoundary sendMessageInteractor, User currentUser, APIAccessObject apiAccessObject, PrivateChatView chatView) {
//        this.sendMessageInteractor = sendMessageInteractor;
//        this.currentUser = currentUser;
//        this.apiAccessObject = apiAccessObject;
//        this.chatView = chatView;
//        // Start the chat session when the controller is initialized
//        startChatSession(recipient.getUsername());
//
//    }
//
//
//    public void onSendMessage(String messageContent) {
//        // Create the input data for sending the message
//        // Assuming you have a way to identify the recipient
//        User recipient = new User("sample", "pass"); // Determine the recipient based on your application logic
//        SendMessageInputData inputData = new SendMessageInputData(messageContent, currentUser, recipient);
//
//        // Use the interactor to send the message
//        sendMessageInteractor.sendMessage(inputData);
//    }
//
//    public void startChatSession(User recipient) {
//        ReceiveMessageWorker messageWorker = new ReceiveMessageWorker(apiAccessObject, recipient.getUsername(), chatView);
//        messageWorker.execute();
//    }
//
//}

public class PrivateChatController {
    private final SendMessageInputBoundary sendMessageInteractor;
    private final User currentUser; // The current user
    private final APIAccessObject apiAccessObject;
    private final PrivateChatView chatView;
    private User recipient; // This will be set dynamically

    public PrivateChatController(SendMessageInputBoundary sendMessageInteractor, User currentUser, APIAccessObject apiAccessObject, PrivateChatView chatView) {
        this.sendMessageInteractor = sendMessageInteractor;
        this.currentUser = currentUser;
        this.apiAccessObject = apiAccessObject;
        this.chatView = chatView;
    }

    public void setRecipient(User newRecipient) {
        if (this.recipient == null || !this.recipient.getUsername().equals(newRecipient.getUsername())) {
            this.recipient = newRecipient;
            startChatSession(newRecipient.getUsername());
        } else {
            this.recipient = newRecipient; // Update recipient details but no need to restart the chat session
        }
    }

    public void onSendMessage(String messageContent) {
        // Check if recipient is set
        if (recipient == null) {
            chatView.displayError("Recipient not set.");
            return;
        }

        SendMessageInputData inputData = new SendMessageInputData(messageContent, currentUser, recipient);
        sendMessageInteractor.sendMessage(inputData);
    }

    public void startChatSession(String username) {
        try {
            apiAccessObject.createChatSession(currentUser.getUsername(), username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ReceiveMessageWorker messageWorker = new ReceiveMessageWorker(apiAccessObject, username, chatView);
        messageWorker.execute();
    }
}
