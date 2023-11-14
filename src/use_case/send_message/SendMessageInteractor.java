package use_case.send_message;
import data_access.APIAccessObject;
import entity.Message;

import javax.jms.QueueConnection;

public class SendMessageInteractor implements SendMessageInputBoundary {
    final APIAccessObject apiAccessObject;
    final SendMessageOutputBoundary sendMessageOutputBoundary;
    final MessageFactory messageFactory;

    public SendMessageInteractor(APIAccessObject apiAccessObject, SendMessageOutputBoundary sendMessageOutputBoundary,
                                 MessageFactory messageFactory) {
        this.apiAccessObject = apiAccessObject;
        this.sendMessageOutputBoundary = sendMessageOutputBoundary;
        this.messageFactory = messageFactory;
    }

    public void execute(SendMessageInputData sendMessageInputData, QueueConnection queue) {
//send message data (content, user) to apiaccessobject, apiaccessobject takes data and connects it to output data
//        This means output data would take apiaccessobject as a parameter -> output boundary -> presenter which prepares
//        views -> viewmodel and is outputted on screen


    }
}
