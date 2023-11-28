package use_case.edit_account_information;

public class EditOutputData {
    private final String username;
    private final String userid;

    public EditOutputData(String username, String userid) {
        this.username = username;
        this.userid = userid;
    }

    String getUsername() {
        return username;
    }
    String getUserid() {
        return userid;
    }

}
