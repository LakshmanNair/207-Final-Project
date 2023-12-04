package interface_adapter.groupChat;

import entity.User;

public class GroupChatState {
    private User user;

    public GroupChatState(GroupChatState copy) {
        user = copy.user;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public GroupChatState() {}
    public User getUser(){return user;}
    public String getUsername() {
        return user.getUsername();
    }
    public void setUsername(String username) {user.setUsername(username);}
}
