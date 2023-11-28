package interface_adapter.PrivateChat;

import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInputData;
import entity.User;

import javax.jms.JMSException;
import java.time.LocalDateTime;

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
public class PrivateChatController {
    private final SendMessageInputBoundary sendMessageInteractor;
    private final User currentUser; // Assuming you have a way to identify the current user

    public PrivateChatController(SendMessageInputBoundary sendMessageInteractor, User currentUser) {
        this.sendMessageInteractor = sendMessageInteractor;
        this.currentUser = currentUser;
    }

    public void onSendMessage(String messageContent) {
        // Create the input data for sending the message
        // Assuming you have a way to identify the recipient
        User recipient = new User("sample", "pass"); // Determine the recipient based on your application logic
        SendMessageInputData inputData = new SendMessageInputData(messageContent, currentUser, recipient);

        // Use the interactor to send the message
        sendMessageInteractor.sendMessage(inputData);
    }
}
