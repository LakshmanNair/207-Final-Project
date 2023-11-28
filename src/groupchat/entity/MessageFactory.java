package entity;

import use_case.send_message.SendMessageInputData;

import java.time.LocalDateTime;

public class MessageFactory {

    public Message createMessage(SendMessageInputData sendMessageInputData) {
        String content = sendMessageInputData.getContent();
        User user = sendMessageInputData.getSender();
        LocalDateTime timestamp = LocalDateTime.now();

        return new Message(content, user, timestamp);
    }
}
