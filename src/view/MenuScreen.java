package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//public class MenuScreen {
//
//    public static void createAndShowGUI() {
//        // Creating the frame
//        JFrame frame = new JFrame("Chat Application");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 200);
//
//        // Creating the panel
//        JPanel panel = new JPanel();
//        frame.add(panel);
//
//        // Layout manager
//        panel.setLayout(new FlowLayout());
//
//        // Creating buttons
//        JButton privateChatButton = new JButton("Private Chat");
//        JButton groupChatButton = new JButton("Group Chat");
//
//        // Adding action listeners to buttons
//        privateChatButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Logic to open private chat
//                JOptionPane.showMessageDialog(frame, "Private Chat button clicked");
//            }
//        });
//
//        groupChatButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // Logic to open group chat
//                JOptionPane.showMessageDialog(frame, "Group Chat button clicked");
//            }
//        });
//
//        // Adding buttons to the panel
//        panel.add(privateChatButton);
//        panel.add(groupChatButton);
//
//        // Setting the frame visibility
//        frame.setVisible(true);
//    }
//
//    public static void main(String[] args) {
//        // Scheduling this for the event dispatch thread (EDT)
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }
//
//}
//

public class MenuScreen {
    // Buttons as instance variables
    private JButton privateChatButton;
    private JButton groupChatButton;

    public MenuScreen() {
        createAndShowGUI();
    }

    public void createAndShowGUI() {
        // Creating the frame
        JFrame frame = new JFrame("Chat Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // Creating the panel
        JPanel panel = new JPanel();
        frame.add(panel);

        // Layout manager
        panel.setLayout(new FlowLayout());

        // Creating buttons
        privateChatButton = new JButton("Private Chat");
        groupChatButton = new JButton("Group Chat");

        // Adding buttons to the panel
        panel.add(privateChatButton);
        panel.add(groupChatButton);

        // Setting the frame visibility
        frame.setVisible(true);
    }

    public void setPrivateChatButtonListener(ActionListener listener) {
        privateChatButton.addActionListener(listener);
    }

    public void setGroupChatButtonListener(ActionListener listener) {
        groupChatButton.addActionListener(listener);
    }
}
