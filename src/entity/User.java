package entity;

import interface_adapter.PrivateChat.CreateUserid;

public class User implements UserInterface{
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setUsername(String new_username) {
        this.username = new_username;
    }
    public void setPassword(String new_password) {
        this.password = new_password;
    }
}
