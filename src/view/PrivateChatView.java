//package view;
//
//import interface_adapter.PrivateChat.PrivateChatController;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class PrivateChatView extends JFrame implements ChatView {
//    private JTextArea messageArea;
//    private JTextField inputField;
//    private JButton sendButton;
//    private PrivateChatController controller;  // Reference to the controller
//
////    public void setController(PrivateChatController controller) {
////        this.controller = controller;
////    }
////
////    public PrivateChatView(PrivateChatController controller) {
////        this.controller = controller;
////        createUIComponents();
////        initializeFrame();
////    }
//    public PrivateChatView() {
//        createUIComponents();
//        initializeFrame();
//    }
//
//    public void setController(PrivateChatController controller) {
//        this.controller = controller;
//        // You might also want to set up action listeners here that depend on the controller
//    }
//
//    private void createUIComponents() {
//        // Set up the main frame
//        setTitle("Private Chat");
//        setSize(400, 300);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Text area for displaying messages
//        messageArea = new JTextArea();
//        messageArea.setEditable(false);
//        add(new JScrollPane(messageArea), BorderLayout.CENTER);
//
//        // Panel at the bottom for input field and send button
//        JPanel inputPanel = new JPanel(new BorderLayout());
//        inputField = new JTextField();
//        sendButton = new JButton("Send");
//        inputPanel.add(inputField, BorderLayout.CENTER);
//        inputPanel.add(sendButton, BorderLayout.EAST);
//        add(inputPanel, BorderLayout.SOUTH);
//
//        // Send button action listener
//        sendButton.addActionListener(e -> sendMessage());
//    }
//
//    private void initializeFrame() {
//        // Additional frame setup, if needed
//        setLocationRelativeTo(null); // Center the frame
//        setVisible(true);
//    }
//
//    private void sendMessage() {
//        String message = inputField.getText();
//        if (!message.isEmpty()) {
//            controller.onSendMessage(message);
//            inputField.setText(""); // Clear the input field after sending
//        }
//    }
//
//    @Override
//    public void displayMessage(String message) {
//        SwingUtilities.invokeLater(() -> {
//            messageArea.append(message + "\n");
//        });
//    }
//
//    @Override
//    public void displayError(String error) {
//        SwingUtilities.invokeLater(() -> {
//            JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
//        });
//
//    sendButton.addActionListener(e -> {
//        String message = inputField.getText();
//        if (!message.isEmpty()) {
//            controller.onSendMessage(message);
//            inputField.setText(""); // Clear the input field after sending
//        }
//    });
//    }
//}
package view;

import entity.User;
import interface_adapter.PrivateChat.PrivateChatController;

import javax.swing.*;
import java.awt.*;

public class PrivateChatView extends JFrame implements ChatView {
    private JTextArea messageArea;
    private JTextField inputField; // For typing the message
    private JTextField recipientField; // For entering the recipient's username
    private JButton sendButton;
    private PrivateChatController controller;

    public PrivateChatView() {
        createUIComponents();
        initializeFrame();
    }

    public void setController(PrivateChatController controller) {
        this.controller = controller;
        // You might also want to set up action listeners here that depend on the controller
    }

    private void createUIComponents() {
        setTitle("Private Chat");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout for the entire frame
        setLayout(new BorderLayout());

        // Panel for recipient input
        JPanel recipientPanel = new JPanel(new BorderLayout());
        recipientField = new JTextField();
        recipientPanel.add(new JLabel("To:"), BorderLayout.WEST);
        recipientPanel.add(recipientField, BorderLayout.CENTER);
        add(recipientPanel, BorderLayout.NORTH);

        // Text area for displaying messages
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        add(new JScrollPane(messageArea), BorderLayout.CENTER);

        // Panel at the bottom for message input field and send button
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        sendButton = new JButton("Send");
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);

        // Action listener for the send button
        sendButton.addActionListener(e -> sendMessage());
    }

    private void initializeFrame() {
        setLocationRelativeTo(null); // Center the frame
        setVisible(true);
    }

    private void sendMessage() {
        String recipient = recipientField.getText();
        String message = inputField.getText();
        if (!recipient.isEmpty() && !message.isEmpty()) {
            // Set the recipient in the controller
            controller.setRecipient(new User(recipient, "")); // Assuming User constructor accepts username and password
            controller.onSendMessage(message);
            inputField.setText(""); // Clear the message input field after sending
        } else {
            // Handle the case where recipient or message is empty
            displayError("Recipient and message cannot be empty.");
        }
    }

    @Override
    public void displayMessage(String message) {
        SwingUtilities.invokeLater(() -> messageArea.append(message + "\n"));
    }

    @Override
    public void displayError(String error) {
        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE));
    }
}
