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
import data_access.APIAccessObject;
import data_access.IMessageSender;
import entity.Message;
import entity.MessageFactory;
import javax.jms.JMSException;
import javax.swing.*;

public class SendMessageInteractor implements SendMessageInputBoundary {
    private final APIAccessObject apiAccessObject;
    private SendMessageOutputBoundary sendMessageOutputBoundary;

    public SendMessageInteractor(APIAccessObject apiAccessObject) {
        this.apiAccessObject = apiAccessObject;
    }

    public void setOutputBoundary(SendMessageOutputBoundary sendMessageOutputBoundary) {
        this.sendMessageOutputBoundary = sendMessageOutputBoundary;
    }

    public void sendMessage(SendMessageInputData inputData) {
        // Use SwingWorker or similar threading mechanism to send the message asynchronously
        new SendMessageWorker(apiAccessObject, inputData, sendMessageOutputBoundary).execute();
    }

    private static class SendMessageWorker extends SwingWorker<Void, Void> {
        private final APIAccessObject apiAccessObject;
        private final SendMessageInputData inputData;
        private final SendMessageOutputBoundary sendMessageOutputBoundary;

        public SendMessageWorker(APIAccessObject apiAccessObject, SendMessageInputData inputData, SendMessageOutputBoundary sendMessageOutputBoundary) {
            this.apiAccessObject = apiAccessObject;
            this.inputData = inputData;
            this.sendMessageOutputBoundary = sendMessageOutputBoundary;
        }

        @Override
        protected Void doInBackground() throws Exception {
            // Use inputData to send the message via APIAccessObject
            apiAccessObject.sendMessage(inputData.getRecipient().getUsername(), inputData.getContent());
            return null;
        }

        @Override
        protected void done() {
            try {
                get(); // Ensure any exception is caught
                Message sentMessage = new MessageFactory().createMessage(inputData);
                SendMessageOutputData outputData = new SendMessageOutputData(true, inputData.getContent());
                sendMessageOutputBoundary.presentMessageSendingResult(outputData);
            } catch (Exception e) {
                Message sentMessage = new MessageFactory().createMessage(inputData);
                SendMessageOutputData outputData = new SendMessageOutputData(false, "Failed to send message: " + e.getMessage());
                sendMessageOutputBoundary.presentMessageSendingResult(outputData);
            }
        }
    }
}
