package data_access;
import view.PrivateChatView;

import javax.swing.*;

public class ReceiveMessageWorker extends SwingWorker<Void, Void> {
    private final APIAccessObject apiAccessObject;
    private final String user;
    private final PrivateChatView chatView;

    public ReceiveMessageWorker(APIAccessObject apiAccessObject, String user, PrivateChatView chatView) {
        this.apiAccessObject = apiAccessObject;
        this.user = user;
        this.chatView = chatView;
    }

    @Override
    protected Void doInBackground() throws Exception {
        while (!isCancelled()) {
            String message = apiAccessObject.receiveMessage(user);
            if (message != null) {
                SwingUtilities.invokeLater(() -> chatView.displayMessage(message));
            }
            Thread.sleep(1000); // Polling interval
        }
        return null;
    }
}
