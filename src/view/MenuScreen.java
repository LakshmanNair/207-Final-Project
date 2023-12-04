package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import app.Application;
import data_access.APIAccessObject;
import data_access.ReceiveMessageWorker;
import entity.User;
import interface_adapter.PrivateChat.PrivateChatController;
import interface_adapter.login.MenuState;
import use_case.send_message.SendMessageInputData;

public class MenuScreen extends JPanel implements ActionListener, PropertyChangeListener {
    // Buttons as instance variables
    public final String viewName = "MenuScreen";
    private JButton privateChatButton;
    private JButton groupChatButton;
    private JButton receiveMessageButton;
    private JLabel usernameLabel;
    private final String username;
    private APIAccessObject apiAccessObject;
    private PrivateChatView chatView;
    private SendMessageInputData sendMessageInputData;

    public MenuScreen() {
        this.username = MenuState.getUsername();
        createAndShowGUI();
    }

    public void createAndShowGUI() {

        // Layout manager
        this.setLayout(new FlowLayout());

        // Updating username label
        usernameLabel = new JLabel();
        usernameLabel.setText(this.username);

        // Creating buttons
        privateChatButton = new JButton("Private Chat");
        groupChatButton = new JButton("Group Chat");
        receiveMessageButton = new JButton("Receive Message");

        // Adding action listeners to buttons
        privateChatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Private Chat Button Clicked");
                Application.showPrivateChat(username);
            }
        });

        // Adding action listener to the new button
        receiveMessageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Receive Message Button Clicked");
                ReceiveMessageWorker messageWorker = new ReceiveMessageWorker(apiAccessObject, username, chatView);
                messageWorker.execute();
            }
        });

        groupChatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Group Chat Button Clicked");
//                Application.showGroupChat();
            }
        });

        // Adding buttons to the panel
        this.add(privateChatButton);
        this.add(groupChatButton);
        this.add(receiveMessageButton);

    }
    public void setPrivateChatButtonListener(ActionListener listener) {
        privateChatButton.addActionListener(listener);
    }
    public void setGroupChatButtonListener(ActionListener listener) {
        groupChatButton.addActionListener(listener);
    }

    public void actionPerformed(ActionEvent evt) {
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        LoggedInState state = (LoggedInState) evt.getNewValue();
//        username.setText(state.getUsername());
    }
}

