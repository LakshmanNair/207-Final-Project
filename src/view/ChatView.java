package view;

//
//public class PrivateChatView extends JFrame {
//    private JTextArea chatArea;
//    private JTextField messageField;
//    private JButton sendButton;
//    private PrivateChatPresenter presenter;
//    private PrivateChatViewModel viewModel;
//
//    public PrivateChatView(PrivateChatPresenter presenter, PrivateChatViewModel viewModel) {
//        this.presenter = presenter;
//        this.viewModel = viewModel;
//
//        setTitle("Private Chat");
//        setSize(400, 500);
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        chatArea = new JTextArea();
//        chatArea.setEditable(false);
//        add(new JScrollPane(chatArea), BorderLayout.CENTER);
//
//        JPanel bottomPanel = new JPanel();
//        bottomPanel.setLayout(new BorderLayout());
//
//        messageField = new JTextField();
//        bottomPanel.add(messageField, BorderLayout.CENTER);
//
//        sendButton = new JButton("Send");
//        bottomPanel.add(sendButton, BorderLayout.EAST);
//
//        add(bottomPanel, BorderLayout.SOUTH);
//
//        setupActions();
//    }
//
//    private void setupActions() {
//        sendButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String message = messageField.getText();
//                if (!message.isEmpty()) {
//                    User sender = new User("bob", "pass" ); // this should be the actual sender User object
//                    LocalDateTime timestamp = LocalDateTime.now();
//
//                    String confirmation = null;
//                    SendMessageInputData inputData;
//                    try {
//                        inputData = new SendMessageInputData(message, sender, timestamp);
//                    } catch (JMSException ex) {
//                        throw new RuntimeException(ex);
//                    }
//                    // Call presenter method to handle sending message
//                    presenter.onMessageSent(null);
//                    // Reset the message field
//                    messageField.setText("");
//                }
//            }
//        });
//
//        // Example of using Observer pattern (if viewModel is observable)
//        // viewModel.addObserver((o, arg) -> updateChatArea());
//    }
//
//    private void updateChatArea() {
//        String status = viewModel.getMessageStatus();
//        chatArea.append(status + "\n");
//    }
//}
public interface ChatView {
    void displayMessage(String message);
    void displayError(String error);
//    void updateChat(String messageContent, User sender); // Uncomment if needed
}
