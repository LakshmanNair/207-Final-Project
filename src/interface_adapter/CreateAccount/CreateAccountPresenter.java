package interface_adapter.CreateAccount;

//import interface_adapter.login.LoginState;
//import interface_adapter.login.LoginViewModel;
import interface_adapter.ViewManagerModel;
import use_case.create_account.CreateAccountOutputBoundary;
import use_case.create_account.CreateAccountOutputBoundary;
import use_case.create_account.CreateAccountOutputData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateAccountPresenter implements CreateAccountOutputBoundary{

    private final CreateAccountViewModel createAccountViewModel;
    private final ViewManagerModel viewManagerModel;

    public CreateAccountPresenter(CreateAccountViewModel createAccountViewModel, ViewManagerModel viewManagerModel) {
        this.viewManagerModel = viewManagerModel;
        this.createAccountViewModel = createAccountViewModel;
    }
    @Override
    public void prepareSuccessView(CreateAccountOutputData response) {
//        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
//        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));

        viewManagerModel.setActiveView("LoginView");
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        CreateAccountState createAccountState = createAccountViewModel.getState();
        createAccountState.setUsernameError(error);
        createAccountViewModel.firePropertyChanged();
    }
}
