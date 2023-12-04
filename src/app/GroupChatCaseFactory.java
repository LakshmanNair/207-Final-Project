package app;

import entity.User;
import interface_adapter.ViewManagerModel;
import interface_adapter.groupChat.GroupChatController;
import interface_adapter.groupChat.GroupChatState;
import interface_adapter.groupChat.GroupChatViewModel;
import use_case.groupChat.GroupChatInputBoundary;
import use_case.groupChat.GroupChatInteractor;
import use_case.groupChat.GroupChatUserDataAccessInterface;
import use_case.send_message.SendMessageInputBoundary;
import view.GroupChatView;

import javax.swing.*;
import java.io.IOException;
import java.util.List;

public class GroupChatCaseFactory {
    private GroupChatCaseFactory(){}

    public static GroupChatView create(
            GroupChatViewModel groupChatViewModel,
            SendMessageInputBoundary sendMessageInputBoundary) {

        GroupChatController controller = createGroupChatUseCase(
                groupChatViewModel,
                sendMessageInputBoundary);
        return new GroupChatView(groupChatViewModel, controller);
    }

    private static GroupChatController createGroupChatUseCase(
            GroupChatViewModel groupChatViewModel,
            SendMessageInputBoundary sendMessageInputBoundary){

        GroupChatInputBoundary groupChatInteractor = new GroupChatInteractor(
                groupChatViewModel,
                sendMessageInputBoundary);

        return new GroupChatController(groupChatViewModel, groupChatInteractor);
    }
}
