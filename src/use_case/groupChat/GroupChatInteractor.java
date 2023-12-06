package use_case.groupChat;

import entity.User;
import interface_adapter.groupChat.GroupChatViewModel;
import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInputData;

import java.util.ArrayList;
import java.util.List;

public class GroupChatInteractor implements GroupChatInputBoundary {
    final GroupChatViewModel model;
    private final SendMessageInputBoundary sendMessageInteractor;

    public GroupChatInteractor(
            GroupChatViewModel model,
            SendMessageInputBoundary sendMessageInteractor) {

        this.sendMessageInteractor = sendMessageInteractor;
        this.model = model;
    }

    @Override
    public void sendMessage(GroupChatInputData groupChatInputData) {

        StringBuilder sb=new StringBuilder();
        sb
        .append(groupChatInputData.getTime())
        .append("\n")
        .append(groupChatInputData.getUsername())
        .append(": ")
        .append(groupChatInputData.getMessage())
        .append("\n");


        this.model.addHistory(sb.toString());
        for (User user : model.getUsers()) {
            SendMessageInputData inputData = new SendMessageInputData(groupChatInputData.getMessage(), model.getState().getUser(), user);
            sendMessageInteractor.sendMessage(inputData);
        }
    }



}
