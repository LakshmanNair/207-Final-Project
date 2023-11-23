package use_case.edit_account_information;

public class EditInputData {
    private final String userid;
    private final String currentUsername;
    private final String currentPassword;
    private final String newUsername;
    private final String newPassword;

    public EditInputData(String userid, String currentUsername, String currentPassword,
                         String newUsername, String newPassword) {
        this.userid = userid;
        this.currentUsername = currentUsername;
        this.currentPassword = currentPassword;
        this.newUsername = newUsername;
        this.newPassword = newPassword;
    }
    public String getUserid() {
        return userid;
    }
    public String getCurrentUsername() {
        return currentUsername;
    }
    String getCurrentPassword() {
        return currentPassword;
    }
    public String getNewUsername() {
        return newUsername;
    }
    public String getNewPassword() {
        return newPassword;
    }
}
