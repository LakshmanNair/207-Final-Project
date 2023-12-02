//package entity;
//
//import interface_adapter.PrivateChat.MessageHistory;
//
//import java.util.ArrayList;
//
//public class PrivateChat {
//
//    private final User user1;
//    private final User user2;
//    private MessageHistory messages;
//
//    public PrivateChat(User user1, User user2, MessageHistory messages){
//        this.user1 = user1;
//        this.user2 = user2;
//        this.messages = messages;
//    }
//    private ArrayList<User> getUsers(){
//        ArrayList<User> users = new ArrayList<User>();
//        users.add(user1);
//        users.add(user2);
//        return users;
//    }
//    private MessageHistory getMessages(){
//        return messages;
//    }
//    private void updateMessages(MessageHistory updated_messages){
//        this.messages = updated_messages;
//    }
//}
