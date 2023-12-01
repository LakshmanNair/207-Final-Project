package use_case.login;

public interface LoginOutputBoundary {
    void prepareSuccessView(LoginOutputData response);

    void prepareFailView(String error);

    void loginSuccess(LoginOutputData user);

    void loginFail(String error);
}