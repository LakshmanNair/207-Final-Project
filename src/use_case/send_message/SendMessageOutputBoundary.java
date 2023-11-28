package use_case.send_message;

// UIOutputBoundary.java

//public interface SendMessageOutputBoundary {
//    void onMessageSent(String outputmessage); // gotta make this a string
//    void onError(String error);
//}
public interface SendMessageOutputBoundary {
    void presentMessageSendingResult(SendMessageOutputData sendMessageOutputData);
}
