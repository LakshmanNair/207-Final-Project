package use_case.create_account;

public class CreateAccountInputData {

    final private String username;

    final private String password;

    final private String repeatPassword;

    public CreateAccountInputData(String username, String password, String repeatPassword) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    String getUsername(){
        return username;
    }

    String getPassword(){
        return password;
    }

    String getRepeatPassword(){
        return repeatPassword;
    }
}
