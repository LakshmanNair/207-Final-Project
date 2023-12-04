package interface_adapter.editAccountInfo;

import use_case.edit_account_information.EditInputData;
import use_case.edit_account_information.EditInputBoundary;

public class EditAccountInfoController {
    final EditInputBoundary editAccountInfoUseCaseInteractor;

    public EditAccountInfoController(EditInputBoundary editAccountInfoUseCaseInteractor) {
        this.editAccountInfoUseCaseInteractor = editAccountInfoUseCaseInteractor;
    }

    public void execute(String currentUsername, String currentPassword,
                        String newUsername, String newPassword) {
        EditInputData editInputData = new EditInputData(currentUsername, currentPassword,
                                                        newUsername, newPassword);
        editAccountInfoUseCaseInteractor.execute(editInputData);
    }
}
