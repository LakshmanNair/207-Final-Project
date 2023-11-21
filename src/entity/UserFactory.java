package entity;

import java.time.LocalDateTime;

public class UserFactory {
    public User create(String username, String password, LocalDateTime now) {
        return new User(username, password);
    }
}
