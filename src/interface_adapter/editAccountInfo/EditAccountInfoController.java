package interface_adapter.editAccountInfo;

import use_case.edit_account_information.EditInputData;
import use_case.edit_account_information.EditInputBoundary;

import interface_adapter.ViewManagerModel;

public class EditAccountInfoController {
    final EditInputBoundary editAccountInfoUseCaseInteractor;
    private final ViewManagerModel viewManager;

    public EditAccountInfoController(EditInputBoundary editAccountInfoUseCaseInteractor,
                                     ViewManagerModel viewManager) {
        this.editAccountInfoUseCaseInteractor = editAccountInfoUseCaseInteractor;
        this.viewManager = viewManager;
    }

    public void execute(String currentUsername, String currentPassword,
                        String newUsername, String newPassword) {
        EditInputData editInputData = new EditInputData(currentUsername, currentPassword,
                                                        newUsername, newPassword);
        editAccountInfoUseCaseInteractor.execute(editInputData);
    }

    public void switchToLoggedIn() {
        viewManager.setActiveView("logged in");
    }
}
