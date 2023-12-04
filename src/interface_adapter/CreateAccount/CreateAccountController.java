package interface_adapter.CreateAccount;

import interface_adapter.ViewManagerModel;
import use_case.create_account.CreateAccountInputBoundary;
import use_case.create_account.CreateAccountInputData;

public class CreateAccountController {
    private final CreateAccountInputBoundary userCreateAccountUseCaseInteractor;
    private final ViewManagerModel viewManager;
    public CreateAccountController(CreateAccountInputBoundary userCreateAccountUseCaseInteractor, ViewManagerModel viewManager) {
        this.userCreateAccountUseCaseInteractor = userCreateAccountUseCaseInteractor;
        this.viewManager = viewManager;
    }

    public void execute(String username, String password1, String password2) {
        CreateAccountInputData signupInputData = new CreateAccountInputData(
                username, password1, password2);

        userCreateAccountUseCaseInteractor.execute(signupInputData);
//        if (userCreateAccountUseCaseInteractor.execute(signupInputData)) {
//            viewManager.switchToLoginView();
//        }
    }
    public void switchToLogin() {
        viewManager.setActiveView("LoginView");
    }
}


