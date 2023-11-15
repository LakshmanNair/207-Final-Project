package entity;

public class UserFactory {
    public User create(String username, String password) {
        return new User(username, password);
    }
}
