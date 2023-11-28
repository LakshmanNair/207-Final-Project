package use_case.send_message;

import java.time.LocalDateTime;
import entity.User;

//public class SendMessageOutputData {
//    private boolean success;
//    private String messageContent;
//    private User sender;
//    private LocalDateTime timestamp;
//    private String additionalInfo;
//
//    public SendMessageOutputData(boolean success, String messageContent, User sender, LocalDateTime timestamp) {
//        this.success = success;
//        this.messageContent = messageContent;
//        this.sender = sender;
//        this.timestamp = timestamp;
//        this.additionalInfo = "";
//    }
//
//    // Getter and Setter for 'success'
//    public boolean isSuccess() {
//        return success;
//    }
//
//    public void setSuccess(boolean success) {
//        this.success = success;
//    }
//
//    // Getter and Setter for 'messageContent'
//    public String getMessageContent() {
//        return messageContent;
//    }
//
//    public void setMessageContent(String messageContent) {
//        this.messageContent = messageContent;
//    }
//
//    // Getter and Setter for 'sender'
//    public User getSender() {
//        return sender;
//    }
//
//    public void setSender(User sender) {
//        this.sender = sender;
//    }
//
//    // Getter and Setter for 'timestamp'
//    public LocalDateTime getTimestamp() {
//        return timestamp;
//    }
//
//    public void setTimestamp(LocalDateTime timestamp) {
//        this.timestamp = timestamp;
//    }
//
//    // Getter and Setter for 'additionalInfo'
//    public String getAdditionalInfo() {
//        return additionalInfo;
//    }
//
//    public void setAdditionalInfo(String additionalInfo) {
//        this.additionalInfo = additionalInfo;
//    }
//}
public class SendMessageOutputData {
    private final boolean success;
    private final String message;

    public SendMessageOutputData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}