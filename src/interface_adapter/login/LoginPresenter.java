package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;
import view.MenuScreen;

import javax.swing.*;

public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void loginSuccess(LoginOutputData outputData) {

        this.viewManagerModel.setActiveView("MenuScreen");
        this.viewManagerModel.firePropertyChanged();
        MenuState.setUsername(outputData.getUsername());
    }

    @Override
    public void loginFail(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
        JOptionPane.showMessageDialog(null, error, "Login Error", JOptionPane.ERROR_MESSAGE);
    }
}
