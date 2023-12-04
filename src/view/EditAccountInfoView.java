package view;

import interface_adapter.PrivateChat.PrivateChatController;
import interface_adapter.editAccountInfo.EditAccountInfoController;
import interface_adapter.editAccountInfo.EditAccountInfoState;
import interface_adapter.editAccountInfo.EditAccountInfoViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EditAccountInfoView extends JFrame implements ActionListener, PropertyChangeListener {

    public final String viewName = "Edit Account Info";

    private final EditAccountInfoViewModel editAccountInfoViewModel;
    private final JTextField currentUsernameInputField = new JTextField(15);
    private final JPasswordField currentPasswordInputField = new JPasswordField(15);
    private final JTextField newUsernameInputField = new JTextField(15);
    private final JPasswordField newPasswordInputField = new JPasswordField(15);
    private EditAccountInfoController controller;

    public JLabel confirmedMessage = new JLabel("No changes made");

    private final JButton confirm;

    public EditAccountInfoView(EditAccountInfoViewModel editAccountInfoViewModel) {

        this.editAccountInfoViewModel = editAccountInfoViewModel;
        editAccountInfoViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(editAccountInfoViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel currentUsernameInfo = new LabelTextPanel(
                new JLabel(editAccountInfoViewModel.CURRENT_USERNAME_LABEL), currentUsernameInputField);
        LabelTextPanel currentPasswordInfo = new LabelTextPanel(
                new JLabel(editAccountInfoViewModel.CURRENT_PASSWORD_LABEL), currentPasswordInputField);
        LabelTextPanel newUsernameInfo = new LabelTextPanel(
                new JLabel(editAccountInfoViewModel.NEW_USERNAME_LABEL), newUsernameInputField);
        LabelTextPanel newPasswordInfo = new LabelTextPanel(
                new JLabel(editAccountInfoViewModel.NEW_PASSWORD_LABEL), newPasswordInputField);

        confirmedMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        confirm = new JButton(editAccountInfoViewModel.CONFIRM_BUTTON_LABEL);
        buttons.add(confirm);

        confirm.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(confirm)) {
                            controller.execute(currentUsernameInputField.getText(),
                                    String.valueOf(currentPasswordInputField.getPassword()),
                                    newUsernameInputField.getText(),
                                    String.valueOf(newPasswordInputField.getPassword()));
                        }
                    }
                }
        );

        currentUsernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EditAccountInfoState currentState = editAccountInfoViewModel.getState();
                        currentState.setNewUsername(currentUsernameInputField.getText() + e.getKeyChar());
                        editAccountInfoViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        currentPasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EditAccountInfoState currentState = editAccountInfoViewModel.getState();
                        currentState.setNewUsername(String.valueOf(currentPasswordInputField.getPassword()) + e.getKeyChar());
                        editAccountInfoViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        newUsernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EditAccountInfoState currentState = editAccountInfoViewModel.getState();
                        currentState.setNewUsername(newUsernameInputField.getText() + e.getKeyChar());
                        editAccountInfoViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        newPasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        EditAccountInfoState currentState = editAccountInfoViewModel.getState();
                        currentState.setNewUsername(String.valueOf(newPasswordInputField.getPassword()) + e.getKeyChar());
                        editAccountInfoViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });


        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        this.add(title);
        this.add(currentUsernameInfo);
        this.add(currentPasswordInfo);
        this.add(newUsernameInfo);
        this.add(newPasswordInfo);
        this.add(confirmedMessage);
        this.add(buttons);

        this.setTitle("Edit Account Info");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public void setController(EditAccountInfoController controller) {
        this.controller = controller;
        // You might also want to set up action listeners here that depend on the controller
    }

    public void success() {
        confirmedMessage.setText(editAccountInfoViewModel.COMPLETE_LABEL);
    }

    public void failure(String error) {
        confirmedMessage.setText(error);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("This shouldn't even be possible");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        EditAccountInfoState state = (EditAccountInfoState) evt.getNewValue();
        if (state.getError() != null) {
            JOptionPane.showMessageDialog(this, state.getError());
        }
    }
}
