package entity;

import interface_adapter.PrivateChat.CreateUserid;

public class User implements UserInterface{
    private String userid;
    private String username;
    private String password;

    public User(String username, String password) {
        this.userid = setUserID();// the next one available. store list of user ids.;
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUserID() {
        return userid;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String setUserID() {
        return CreateUserid.NewUserID().toString();// next unused ID or token
    }
    public void setUsername(String new_username) {
        this.username = new_username;
    }
    public void setPassword(String new_password) {
        this.password = new_password;
    }
    //public void setUserid(String new_userid) {this.userid = new_userid;}

}
