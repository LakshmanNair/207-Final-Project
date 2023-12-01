package interface_adapter.CreateAccount;

import use_case.create_account.CreateAccountInputBoundary;
import use_case.create_account.CreateAccountInputData;

public class CreateAccountController {
    final CreateAccountInputBoundary userCreateAccountUseCaseInteractor;
    public CreateAccountController(CreateAccountInputBoundary userCreateAccountUseCaseInteractor) {
        this.userCreateAccountUseCaseInteractor = userCreateAccountUseCaseInteractor;
    }

    public void execute(String username, String password1, String password2) {
        CreateAccountInputData signupInputData = new CreateAccountInputData(
                username, password1, password2);

        userCreateAccountUseCaseInteractor.execute(signupInputData);
    }
}

