package use_case.edit_account_information;

public class EditInputData {
    private final String userid;
    private final String newUsername;
    private final String newPassword;

    public EditInputData(String userid, String newUsername, String newPassword) {
        this.userid = userid;
        this.newUsername = newUsername;
        this.newPassword = newPassword;
    }

    String getUserid() {
        return userid;
    }
    String getNewUsername() {
        return newUsername;
    }
    String getNewPassword() {
        return newPassword;
    }
}
