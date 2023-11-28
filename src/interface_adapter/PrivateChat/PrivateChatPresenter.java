package interface_adapter.PrivateChat;

import use_case.send_message.SendMessageInputData;
import use_case.send_message.SendMessageOutputBoundary;
import use_case.send_message.SendMessageOutputData;
import view.PrivateChatView;
//import use_case.send_message.SendMessageOutputData;

//public class PrivateChatPresenter implements SendMessageOutputBoundary {
//    // Reference to your UI components, e.g., a JFrame or JPanel
//    private final PrivateChatView view;
//
//    public PrivateChatPresenter(PrivateChatView view) {
//        this.view = view;
//    }
//
//    @Override
//    public void presentMessageSendingResult(SendMessageOutputData sendMessageOutputData) {
//        if (sendMessageOutputData.isSuccess()) {
//            view.displayMessage("Message sent successfully.");
//            // Update the chat UI with the new message
//            // view.updateChat(sendMessageOutputData.getMessageContent(), sendMessageOutputData.getSender());
//        } else {
//            view.displayError("Failed to send message: " + sendMessageOutputData.getMessage());
//        }
//    }
//}
public class PrivateChatPresenter implements SendMessageOutputBoundary {
    private final PrivateChatView view;

    public PrivateChatPresenter(PrivateChatView view) {
        this.view = view;
    }

    @Override
    public void presentMessageSendingResult(SendMessageOutputData outputData) {
        if (outputData.isSuccess()) {
            // Assuming you have a method in your view to display the actual sent message
            view.displayMessage(outputData.getMessage()); // Update this line to display the actual message content
        } else {
            view.displayError("Failed to send message: " + outputData.getMessage());
        }
    }
}
