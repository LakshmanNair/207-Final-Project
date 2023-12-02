package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

public class LoginController {

    private final LoginInputBoundary loginUseCaseInteractor;
    private final ViewManagerModel viewManager;

    public LoginController(LoginInputBoundary loginUseCaseInteractor, ViewManagerModel viewManager) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
        this.viewManager = viewManager;
    }


    public void execute(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(
                username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }
    public void switchToSignup() {
        viewManager.setActiveView("SignupView");
    }

}
