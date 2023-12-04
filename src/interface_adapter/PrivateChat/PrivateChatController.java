package interface_adapter.PrivateChat;

import data_access.APIAccessObject;
import data_access.ReceiveMessageWorker;
import entity.User;
import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInputData;
import view.PrivateChatView;

public class PrivateChatController {
    private final SendMessageInputBoundary sendMessageInteractor;
    private final User currentUser; // The current user
    private User recipient;    // The recipient user
    private final APIAccessObject apiAccessObject;
    private final PrivateChatView chatView;
    private SendMessageInputData sendMessageInputData;

    public PrivateChatController(SendMessageInputBoundary sendMessageInteractor, User currentUser, User recipient, APIAccessObject apiAccessObject, PrivateChatView chatView) {
        this.sendMessageInteractor = sendMessageInteractor;
        this.currentUser = currentUser;
        this.recipient = recipient;
        this.apiAccessObject = apiAccessObject;
        this.chatView = chatView;


        // Start listening for messages for this session
        startChatSession();
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
        startChatSession();
    }

    public void onSendMessage(String messageContent) {
        if (recipient == null) {
            chatView.displayError("Recipient not set.");
            return;
        }

        SendMessageInputData inputData = new SendMessageInputData(messageContent, currentUser, recipient);
        sendMessageInteractor.sendMessage(inputData);
    }

    private void startChatSession() {
        // Start ReceiveMessageWorker to listen for messages from the recipient
        ReceiveMessageWorker messageWorker = new ReceiveMessageWorker(apiAccessObject, sendMessageInputData);
        messageWorker.execute();
    }
}
