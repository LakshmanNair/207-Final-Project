package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import app.Application;
import entity.User;
import interface_adapter.login.MenuState;

public class MenuScreen extends JPanel implements ActionListener, PropertyChangeListener {
    // Buttons as instance variables
    public final String viewName = "MenuScreen";
    private JButton privateChatButton;
    private JButton groupChatButton;

    private JLabel usernameLabel;
    private final String username;

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

        // Adding action listeners to buttons
        privateChatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Private Chat Button Clicked");
                Application.showPrivateChat(username);
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

//        // Setting the frame visibility
//        frame.setVisible(true);
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

