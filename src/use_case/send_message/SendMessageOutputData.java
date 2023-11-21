package use_case.send_message;

public class SendMessageOutputData {
    private boolean success;
    private String message;
    private String additionalInfo; // such as timestamp

    public SendMessageOutputData(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.additionalInfo = "";
    }

    // Getter and Setter for 'success'
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    // Getter and Setter for 'message'
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Getter and Setter for 'additionalInfo'
    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
