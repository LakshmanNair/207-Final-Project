package interface_adapter.PrivateChat;

import use_case.send_message.SendMessageInputData;

public class PrivateChatViewModel {
    private String messageStatus;
    private SendMessageInputData sendMessageInputData;

    public String getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(String messageStatus) {
        this.messageStatus = messageStatus;
    }

    public SendMessageInputData getSendMessageInputData() {
        return sendMessageInputData;
    }

    public void setAdditionalInfo(SendMessageInputData inputData) {
        this.sendMessageInputData = inputData;
    }

    // Additional methods to notify observers (if implementing Observer pattern)
    // public void notifyObservers() {...}
}
