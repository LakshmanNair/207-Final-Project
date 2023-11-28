package interface_adapter.groupChat;

import interface_adapter.logged_in.LoggedInState;

public class GroupChatState {
    private String username = "";

    public GroupChatState(GroupChatState copy) {
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public GroupChatState() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
