package entity;

import use_case.send_message.SendMessageInputData;

import java.time.LocalDateTime;

public interface MessageFactoryInterface {
    Message createMessage(SendMessageInputData sendMessageInputData);
}
