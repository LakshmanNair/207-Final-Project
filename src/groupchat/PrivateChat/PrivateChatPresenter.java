package interface_adapter.PrivateChat;

import use_case.send_message.SendMessageInputData;
import use_case.send_message.SendMessageOutputBoundary;

public class PrivateChatPresenter implements SendMessageOutputBoundary {
    private PrivateChatViewModel viewModel;
    private SendMessageInputData sendMessageInputData;

    public PrivateChatPresenter(PrivateChatViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void onMessageSent(String messageText) {
        // Update the ViewModel with success information
        viewModel.setMessageStatus("Message sent successfully.");
        viewModel.setAdditionalInfo(sendMessageInputData);
        // Notify the view to update, if necessary
        // e.g., viewModel.notifyObservers() or similar mechanism
    }

    @Override
    public void onError(String error) {
        // Update the ViewModel with error information
        viewModel.setMessageStatus("Error sending message: " + error);
        // Notify the view to update
        // e.g., viewModel.notifyObservers()
    }
}
