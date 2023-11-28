package use_case.groupChat;

import interface_adapter.groupChat.GroupChatViewModel;

import java.util.ArrayList;
import java.util.List;

public class GroupChatInteractor implements GroupChatInputBoundary {
    //final GroupChatUserDataAccessInterface userDataAccessObject;
    //final GroupChatOutputBoundary userPresenter;
    final GroupChatViewModel model;
    final List<GroupChatInputData> messageHistory;

    public GroupChatInteractor(
            //GroupChatUserDataAccessInterface userDataAccessObject,
                               //GroupChatOutputBoundary userPresenter,
                               GroupChatViewModel model) {
        //this.userDataAccessObject = userDataAccessObject;
        //this.userPresenter = userPresenter;
        this.model = model;
        this.messageHistory = new ArrayList<>();
    }

    @Override
    public void sendMessage(GroupChatInputData groupChatInputData) {
        this.messageHistory.add(groupChatInputData);
        StringBuilder sb=new StringBuilder();
        for (GroupChatInputData chatInputData : this.messageHistory) {
            sb.append(chatInputData.getTime()+"\n")
                    .append(chatInputData.getUsername()+": ")
                    .append(chatInputData.getMessage()+"\n");
        }
        this.model.setChatHistory(sb.toString());
        // todo message notice

        // todo save message to DAO
    }



}
