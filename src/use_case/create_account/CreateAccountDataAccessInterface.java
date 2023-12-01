package use_case.create_account;

import entity.User;

public interface CreateAccountDataAccessInterface {
    User get(String username);

    boolean existsByName(String username);

    void save(User user);
}
