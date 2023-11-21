package use_case.send_message;

// UIOutputBoundary.java

public interface SendMessageOutputBoundary {
    void onMessageSent(String messageText);
    void onError(String error);
}