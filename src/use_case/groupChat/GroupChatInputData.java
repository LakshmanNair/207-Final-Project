package use_case.groupChat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GroupChatInputData {
    final private String username;
    private final LocalDateTime timestamp;
    final private String message;

    public GroupChatInputData(String username, String message) {
        this.username = username;
        this.message = message;
        timestamp=LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }
    public String getUsername(){
        return username;
    }
    public String getTime(){
        return timestamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}

