//package use_case.send_message;
//import data_access.APIAccessObject;
//
//import javax.jms.QueueConnection;
//
//public class SendMessageInteractor implements SendMessageInputBoundary {
//    final APIAccessObject apiAccessObject;
//    final SendMessageOutputBoundary sendMessageOutputBoundary;
//    final MessageFactory messageFactory;
//
//    public SendMessageInteractor(APIAccessObject apiAccessObject, SendMessageOutputBoundary sendMessageOutputBoundary,
//                                 MessageFactory messageFactory) {
//        this.apiAccessObject = apiAccessObject;
//        this.sendMessageOutputBoundary = sendMessageOutputBoundary;
//        this.messageFactory = messageFactory;
//    }
//
//    @Override
//    public void execute(SendMessageInputData sendMessageInputData, QueueConnection queue) {
//
//
//
//    }
//}

package use_case.send_message;

        import data_access.IMessageSender;

        import javax.jms.JMSException;

public class SendMessageInteractor implements SendMessageInputBoundary {
    private final IMessageSender messageSender;
    private final SendMessageOutputBoundary sendMessageOutputBoundary;
    private final MessageFactory messageFactory;

    public SendMessageInteractor(IMessageSender messageSender,
                                 SendMessageOutputBoundary sendMessageOutputBoundary,
                                 MessageFactory messageFactory) {
        this.messageSender = messageSender;
        this.sendMessageOutputBoundary = sendMessageOutputBoundary;
        this.messageFactory = messageFactory;
    }

    @Override
    public void execute(SendMessageInputData sendMessageInputData) {
        try {
            // Use messageFactory to create a proper message object from the input data
            String messageText = messageFactory.createMessage(sendMessageInputData);

            // Validate the message before sending
            if (messageText == null || messageText.trim().isEmpty()) {
                sendMessageOutputBoundary.onError("Message text is empty");
                return;
            }

            // Use messageSender to send the message
            messageSender.sendMessage(messageText);

            // If sending was successful, notify the output boundary
            sendMessageOutputBoundary.onMessageSent(messageText);
        } catch (JMSException e) {
            // If there was an error, notify the output boundary
            sendMessageOutputBoundary.onError(e.getMessage());
            // Consider logging the exception here as well
        }
    }
}

//send message data (content, user) to apiaccessobject, apiaccessobject takes data and connects it to output data
//        This means output data would take apiaccessobject as a parameter -> output boundary -> presenter which prepares
//        views -> viewmodel and is outputted on screen