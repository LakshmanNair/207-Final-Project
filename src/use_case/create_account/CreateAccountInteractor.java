package use_case.create_account;

import entity.User;
import entity.UserFactoryInterface;

public class CreateAccountInteractor implements CreateAccountInputBoundary {

    final CreateAccountDataAccessInterface userDataAccessObject;
    final CreateAccountOutputBoundary userPresenter;
    final UserFactoryInterface userFactoryInterface;

    public CreateAccountInteractor(CreateAccountDataAccessInterface createAccountDataAccessInterface,
                                   CreateAccountOutputBoundary createAccountOutputBoundary,
                                   UserFactoryInterface userFactoryInterface) {
        this.userDataAccessObject = createAccountDataAccessInterface;
        this.userPresenter = createAccountOutputBoundary;
        this.userFactoryInterface = userFactoryInterface;
    }

    @Override
    public void execute(CreateAccountInputData createAccountInputData) {
        if (userDataAccessObject.existsByName(createAccountInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!createAccountInputData.getPassword().equals(createAccountInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {

//            LocalDateTime now = LocalDateTime.now();
            User user = userFactoryInterface.createUser(createAccountInputData.getUsername(), createAccountInputData.getPassword());
            userDataAccessObject.save(user);

            CreateAccountOutputData createAccountOutputData = new CreateAccountOutputData(user.getUsername(), false);
            userPresenter.prepareSuccessView(createAccountOutputData);
//            return true;
        }
//        return false;
    }
}

