package interface_adapter.editAccountInfo;

import interface_adapter.ViewManagerModel;
import use_case.edit_account_information.EditOutputBoundary;
import use_case.edit_account_information.EditOutputData;
import view.EditAccountInfoView;

public class EditAccountInfoPresenter implements EditOutputBoundary {

    private final EditAccountInfoView editAccountInfoView;

    public EditAccountInfoPresenter(EditAccountInfoView editAccountInfoView) {
        this.editAccountInfoView = editAccountInfoView;
    }

    @Override
    public void prepareSuccessView(EditOutputData editOutputData) {
        editAccountInfoView.success();
    }

    @Override
    public void prepareFailView(String error) {
        editAccountInfoView.failure(error);
    }
}
