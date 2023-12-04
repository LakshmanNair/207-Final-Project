package use_case.login;

public interface LoginOutputBoundary {

    void loginSuccess(LoginOutputData user);

    void loginFail(String error);
}