package entity;

public class UserFactory implements UserFactoryInterface {
    public static User createUser(String username, String password) {
        return new User(username, password);
    }
}
