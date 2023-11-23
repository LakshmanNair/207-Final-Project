package entity;

public class UserFactory {
    public static User create(String username, String password) {
        return new User(username, password);
    }
}
