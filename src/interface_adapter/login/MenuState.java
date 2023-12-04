package interface_adapter.login;

public class MenuState {
    private static String username;

    public static void setUsername(String newUsername) {
        username = newUsername;
    }
    public static String getUsername() {
        return username;
    }
}