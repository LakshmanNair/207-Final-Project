package use_case.send_message;

import javax.jms.QueueConnection;

public interface SendMessageInputBoundary {
    void execute(SendMessageInputData sendMessageInputData);
}

