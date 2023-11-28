package use_case.send_message;

import javax.jms.QueueConnection;

public interface SendMessageInputBoundary {
    void sendMessage(SendMessageInputData inputData);
}

