package view;

import interface_adapter.groupChat.GroupChatController;
import interface_adapter.groupChat.GroupChatViewModel;
import entity.User;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class GroupChatView extends JFrame implements ChatView {
    public final String viewName = GroupChatViewModel.TITLE_LABEL;
    private JTextArea receivers;

    private final GroupChatViewModel model;
    private final GroupChatController controller;

    private JTextArea chatArea;
    private JTextField messageField;

    public GroupChatView(GroupChatViewModel model, GroupChatController controller) {
        // 设置窗口标题
        super(GroupChatViewModel.TITLE_LABEL);
        this.model = model;
        this.controller = controller;
        init_windows();
    }
    public void init_windows(){
        // 设置布局为BorderLayout
        setLayout(new BorderLayout());

        //创建接收人列表
        JLabel label=new JLabel("To:");
        receivers=new HintTextField("example: user1;uesr2;user3");
        JPanel p=new JPanel(new BorderLayout());
        p.add(label, BorderLayout.WEST);
        p.add(receivers, BorderLayout.CENTER);
        add(p, BorderLayout.NORTH);

        // 创建聊天区域
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(chatArea);
        add(scrollPane, BorderLayout.CENTER);

        // 创建消息输入框和发送按钮
        messageField = new JTextField();
        JButton sendButton = new JButton(GroupChatViewModel.SEND_BUTTON_LABEL);

        // 创建底部面板，包含消息输入框和发送按钮
        JPanel bottomPanel = new JPanel(new BorderLayout());
            bottomPanel.add(messageField, BorderLayout.CENTER);
            bottomPanel.add(sendButton, BorderLayout.EAST);

        // 添加底部面板到窗口底部
        add(bottomPanel, BorderLayout.SOUTH);

        // 注册发送按钮的点击事件监听器
            sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!receivers.getText().isEmpty() && !messageField.getText().isEmpty()){
                    model.clearUsers();
                    for (String user : receivers.getText().split(";")) {
                        model.addUser(new User(user,""));
                    }

                    controller.sendMessage(messageField.getText());
                    chatArea.setText(model.getChatHistory());
                    messageField.setText(""); // 清空消息输入框

                }

            }
        });

        // 设置窗口属性
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 将窗口置于屏幕中央
    }

    @Override
    public void displayMessage(String message) {
        SwingUtilities.invokeLater(() -> model.setChatHistory(model.getChatHistory()+message + "\n"));
    }

    @Override
    public void displayError(String error) {

    }
}

class HintTextField extends JTextArea implements FocusListener {

    private final String hint;
    private boolean showingHint;

    public HintTextField(final String hint) {
        super(hint);
        this.hint = hint;
        this.showingHint = true;
        super.addFocusListener(this);
    }

    @Override
    public void focusGained(FocusEvent e) {
        if(this.getText().isEmpty()) {
            super.setText("");
            super.setForeground(Color.BLACK);
            showingHint = false;
        }
    }
    @Override
    public void focusLost(FocusEvent e) {
        if(this.getText().isEmpty()) {
            super.setText(hint);
            super.setForeground(Color.GRAY);
            showingHint = true;
        }
    }

    @Override
    public String getText() {
        return showingHint ?"" : super.getText();
    }
}
