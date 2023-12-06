package interface_adapter.groupChat;

import data_access.APIAccessObject;
import data_access.ReceiveMessageWorker;
import entity.User;
import use_case.groupChat.GroupChatInputBoundary;
import use_case.groupChat.GroupChatInputData;
import view.GroupChatView;

public class GroupChatController {
    final GroupChatViewModel model;
    final GroupChatInputBoundary groupChatInputInteractor;
    final APIAccessObject apiAccessObject;
    public GroupChatView view;

    public GroupChatController(GroupChatViewModel model, GroupChatInputBoundary groupChatInputBoundary, APIAccessObject apiAccessObject) {
        this.model = model;
        this.groupChatInputInteractor = groupChatInputBoundary;
        this.apiAccessObject=apiAccessObject;
    }

    public void sendMessage(String message){
        GroupChatInputData groupChatInputData=new GroupChatInputData(model.getState().getUsername(), message);
        groupChatInputInteractor.sendMessage(groupChatInputData);
    }
    public void setView(GroupChatView view){this.view=view;}
    public void startChatSession() {
        try {
            for (User receiver : model.getUsers()) {
                String sender=model.getState().getUsername();
                if(!apiAccessObject.contain(sender,receiver.getUsername())){
                    apiAccessObject.createChatSession(model.getState().getUser(), receiver);
                    ReceiveMessageWorker messageWorker = new ReceiveMessageWorker(apiAccessObject, receiver.getUsername(), view);
                    messageWorker.execute();
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}
