package use_case.create_account;

import entity.User;
import entity.UserFactory;

import java.time.LocalDateTime;

public class CreateAccountInteractor implements CreateAccountInputBoundary{

   final  CreateAccountDataAccessInterface userDataAccessObject;
   final CreateAccountOutputBoundary userPresenter;
   final UserFactory userFactory;

    public CreateAccountInteractor(CreateAccountDataAccessInterface createAccountDataAccessInterface,
                            CreateAccountOutputBoundary createAccountOutputBoundary,
                            UserFactory userFactory) {
        this.userDataAccessObject = createAccountDataAccessInterface;
        this.userPresenter = createAccountOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(CreateAccountInputData createAccountInputData) {
        if (userDataAccessObject.existsByName(createAccountInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!createAccountInputData.getPassword().equals(createAccountInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {

            LocalDateTime now = LocalDateTime.now();
            User user = userFactory.create(createAccountInputData.getUsername(), createAccountInputData.getPassword(), now);
            userDataAccessObject.save(user);

            CreateAccountOutputData createAccountOutputData = new CreateAccountOutputData(user.getUserID(), now.toString(), false);
            userPresenter.prepareSuccessView(createAccountOutputData);
        }
    }
}
