//package data_access;
//
//import entity.User;
//import use_case.create_account.CreateAccountDataAccessInterface;
//import use_case.login.LoginUserDataAccessInterface;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class LoginDataAccessObject implements LoginUserDataAccessInterface {
//    private final Map<String, User> users = new HashMap<>();
//
//    @Override
//    public User get(String username) {
//        return users.get(username);
//    }
//    /**
//     * @param identifier the user's username
//     * @return whether the user exists
//     */
//    @Override
//    public boolean existsByName(String identifier) {
//        return users.containsKey(identifier);
//    }
//
//    /**
//     * @param user the data to save
//     */
//    @Override
//    public void save(User user) {
//        users.put(user.getUsername(), user);
//    }
//}
