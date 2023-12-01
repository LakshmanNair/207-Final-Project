package use_case.edit_account_information;

public class EditOutputData {
    private final String newUsername;
    private final String newPassword;

    public EditOutputData(String newUsername, String newPassword) {
        this.newUsername = newUsername;
        this.newPassword = newPassword;
    }

    String getNewUsername() {
        return newUsername;
    }
    String getNewPassword() {
        return newPassword;
    }

}
