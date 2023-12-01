package interface_adapter.groupChat;

import use_case.groupChat.GroupChatInputBoundary;
import use_case.groupChat.GroupChatInputData;

public class GroupChatController {
    final GroupChatViewModel model;
    final GroupChatInputBoundary groupChatInputInteractor;

    public GroupChatController(GroupChatViewModel model, GroupChatInputBoundary groupChatInputBoundary) {
        this.model = model;
        this.groupChatInputInteractor = groupChatInputBoundary;
    }

    public void sendMessage(String message){
        GroupChatInputData groupChatInputData=new GroupChatInputData(model.getState().getUsername(), message);
        groupChatInputInteractor.sendMessage(groupChatInputData);
    }

}
