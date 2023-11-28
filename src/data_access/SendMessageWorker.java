package data_access;
import data_access.APIAccessObject;
import javax.swing.SwingWorker;

public class SendMessageWorker extends SwingWorker<Void, Void> {
    private final APIAccessObject apiAccessObject;
    private final String user;
    private final String message;

    public SendMessageWorker(APIAccessObject apiAccessObject, String user, String message) {
        this.apiAccessObject = apiAccessObject;
        this.user = user;
        this.message = message;
    }

    @Override
    protected Void doInBackground() throws Exception {
        apiAccessObject.sendMessage(user, message);
        return null;
    }

    @Override
    protected void done() {
        // Optionally, update the UI or handle completion
        try {
            get();  // Call get to handle any exceptions occurred during doInBackground
        } catch (Exception e) {
            // Handle exceptions
        }
    }
}