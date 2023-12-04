package use_case.groupChat;

import entity.User;
import interface_adapter.groupChat.GroupChatState;
import interface_adapter.groupChat.GroupChatViewModel;
import org.junit.jupiter.api.Test;
import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInputData;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroupChatInteractorTest {

    private static class GroupChatViewModelStub extends GroupChatViewModel {
        private GroupChatState state;

        public GroupChatViewModelStub() {
            super(new GroupChatState(new User("user", "123")));
        }

        @Override
        public void setState(GroupChatState state) {
            this.state = state;
        }

        @Override
        public GroupChatState getState() {
            return state;
        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener listener) {
            // Do nothing for the stub
        }
    }

    private static class SendMessageInputBoundaryStub implements SendMessageInputBoundary {
        private SendMessageInputData inputData;

        @Override
        public void sendMessage(SendMessageInputData inputData) {
            this.inputData = inputData;
        }

        public SendMessageInputData getInputData() {
            return inputData;
        }
    }

    @Test
    void sendMessage() {
        // Arrange
        GroupChatViewModelStub viewModelStub = new GroupChatViewModelStub();
        SendMessageInputBoundaryStub sendMessageInteractorStub = new SendMessageInputBoundaryStub();
        GroupChatInteractor interactor = new GroupChatInteractor(viewModelStub, sendMessageInteractorStub);

        User senderUser = new User("SenderUser", "123");
        viewModelStub.setState(new GroupChatState(senderUser));
        User user1 = new User("User1", "456");
        User user2 = new User("User2", "789");
        viewModelStub.getUsers().add(user1);
        viewModelStub.getUsers().add(user2);

        GroupChatInputData inputData = new GroupChatInputData("User","Hello, everyone!");

        // Act
        interactor.sendMessage(inputData);

        // Assert

        SendMessageInputData sentInputData = sendMessageInteractorStub.getInputData();
        assertEquals("Hello, everyone!", sentInputData.getContent());
        assertEquals(senderUser, sentInputData.getSender());
    }
}