package interface_adapter.groupChat;

import entity.User;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class GroupChatViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Group Chat View";
    public static final String UPLEVEl_LABEL = "<";

    private GroupChatState state = new GroupChatState();

    public static final String SEND_BUTTON_LABEL = "Send";
    private String chatHistory;
    private String groupChatUser;

    final private List<User> users;

    public GroupChatViewModel() {
        super("group chat");
        users=new ArrayList<>();
    }

    public void addUser(User u){
        this.users.add(u);
    }
    public List<User> getUsers(){
        return users;
    }

    public void setState(GroupChatState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Login Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public GroupChatState getState() {
        return state;
    }


    public String getGroupChatUser() {
        return groupChatUser;
    }

    public void setGroupChatUser(String groupChatUser) {
        this.groupChatUser = groupChatUser;
    }


    public String getChatHistory() {
        return chatHistory;
    }
    public void setChatHistory(String history) {
        chatHistory=history;
    }

}
