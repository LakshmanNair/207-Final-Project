package interface_adapter.editAccountInfo;

public class EditAccountInfoState {
    private String currentUsername;
    private String currentPassword;
    private String newUsername;
    private String newPassword;
    private String error;

    public EditAccountInfoState(EditAccountInfoState copy) {
        currentUsername = copy.currentUsername;
        currentPassword = copy.currentPassword;
        newUsername = copy.newUsername;
        newPassword = copy.newPassword;
        error = copy.error;
    }

    public EditAccountInfoState() {}

    public String getCurrentUsername() {
        return currentUsername;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getError() {
        return error;
    }

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public void setNewUsername(String newUsername) {
        this.newUsername = newUsername;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setError(String error) {
        this.error = error;
    }
}
