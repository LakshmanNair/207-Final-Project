package entity;

public class User {
    private final String userid;
    private String username;
    private String password;

    User(String username, String password) {
        this.userid = int // the next one available. store list of user ids.;
        this.username = username;
        this.password = password;
    }

    public String getUserID() {
        return userid;
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUserID() {
        this.userid = // next unused ID or token
    }
    public void setUsername(String new_username) {
        this.username = new_username;
    }
    public void setPassword(String new_password) {
        this.password = new_password;
    }

}
