package view;

import interface_adapter.groupChat.GroupChatController;
import interface_adapter.groupChat.GroupChatViewModel;
import interface_adapter.logged_in.LoggedInState;
import use_case.groupChat.GroupChatInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GroupChatView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = GroupChatViewModel.TITLE_LABEL;
    private JTextArea chatTextArea;
    private JTextField messageTextField;
    private JButton sendButton;
    private JButton uplevelButton;

    private final GroupChatViewModel model;
    private final GroupChatController controller;

    public GroupChatView(GroupChatViewModel model, GroupChatController controller) {
        this.model = model;
        this.model.addPropertyChangeListener(this);
        this.controller=controller;
        initUI();
    }

    private void initUI() {

        setSize(400, 500);


        JLabel title = new JLabel("Group Chat");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        chatTextArea = new JTextArea(15,30);
        chatTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatTextArea);


        messageTextField = new JTextField(10);

        sendButton = new JButton(GroupChatViewModel.SEND_BUTTON_LABEL);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = messageTextField.getText();
                if (!message.isEmpty()) {
                    controller.sendMessage(message);
                    chatTextArea.setText(model.getChatHistory());
                    messageTextField.setText("");
                }
            }
        });

        uplevelButton=new JButton(GroupChatViewModel.UPLEVEl_LABEL);
        uplevelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //todo 切换到上一层
            }
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(title);
        add(scrollPane);
        JPanel inputPanel = new JPanel();
        inputPanel.add(uplevelButton);
        inputPanel.add(messageTextField);
        inputPanel.add(sendButton);
        add(inputPanel);
    }
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        model.setGroupChatUser(state.getUsername());
    }
}

