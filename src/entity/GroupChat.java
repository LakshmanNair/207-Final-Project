package entity;

import interface_adapter.PrivateChat.MessageHistory;

import java.util.ArrayList;
import java.util.List;

public class GroupChat{
    private final List<User> users;
    private final User admin;
    private MessageHistory messages;
    public GroupChat(User user,MessageHistory messages){
        this.users=new ArrayList<>();
        this.admin=user;
        this.users.add(user);
        this.messages=messages;
    }
    public GroupChat addUser(User user){
        if(!users.contains(user))
            users.add(user);

        return this;
    }
    public GroupChat removeUser(User admin,User user){
        if(this.admin.equals(admin)){
            users.remove(user);
        }
        return this;
    }
    public List<User> getUsers(){
        return users;
    }
    public MessageHistory getMessages(){
        return messages;
    }
    public void updateMessages(MessageHistory updated_messages){
        this.messages = updated_messages;
    }

}
