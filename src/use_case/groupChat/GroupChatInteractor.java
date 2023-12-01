package use_case.groupChat;

import entity.User;
import interface_adapter.groupChat.GroupChatViewModel;
import use_case.send_message.SendMessageInputBoundary;
import use_case.send_message.SendMessageInputData;

import java.util.ArrayList;
import java.util.List;

public class GroupChatInteractor implements GroupChatInputBoundary {
    //final GroupChatUserDataAccessInterface userDataAccessObject;
    //final GroupChatOutputBoundary userPresenter;
    final GroupChatViewModel model;
    private final SendMessageInputBoundary sendMessageInteractor;

    final List<GroupChatInputData> messageHistory;

    public GroupChatInteractor(
            //GroupChatUserDataAccessInterface userDataAccessObject,
            //GroupChatOutputBoundary userPresenter,
            GroupChatViewModel model,
            SendMessageInputBoundary sendMessageInteractor) {
        //this.userDataAccessObject = userDataAccessObject;
        //this.userPresenter = userPresenter;
        this.sendMessageInteractor = sendMessageInteractor;
        this.model = model;
        this.messageHistory = new ArrayList<>();
    }

    @Override
    public void sendMessage(GroupChatInputData groupChatInputData) {
        this.messageHistory.add(groupChatInputData);
        StringBuilder sb=new StringBuilder();
        for (GroupChatInputData chatInputData : this.messageHistory) {
            sb
            .append(chatInputData.getTime())
            .append("\n")
            .append(chatInputData.getUsername())
            .append(": ")
            .append(chatInputData.getMessage())
            .append("\n");
        }
        this.model.setChatHistory(sb.toString());
        // todo 通知其它人发送了消息
        for (User user : model.getUsers()) {
            SendMessageInputData inputData = new SendMessageInputData(groupChatInputData.getMessage(), model.getState().getUser(), user);
            sendMessageInteractor.sendMessage(inputData);
        }


        // todo 保存消息到DAO
    }



}
