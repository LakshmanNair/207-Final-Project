package use_case.send_message;

// UIOutputBoundary.java

public interface SendMessageOutputBoundary {
    void onMessageSent(SendMessageOutputData outputData);
    void onError(String error);
}