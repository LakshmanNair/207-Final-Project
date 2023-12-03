package interface_adapter.editAccountInfo;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.edit_account_information.EditOutputBoundary;
import use_case.edit_account_information.EditOutputData;

public class EditAccountInfoPresenter implements EditOutputBoundary {

    private final EditAccountInfoViewModel editAccountInfoViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public EditAccountInfoPresenter(EditAccountInfoViewModel editAccountInfoViewModel,
                                    LoggedInViewModel loggedInViewModel,
                                    ViewManagerModel viewManagerModel) {
        this.editAccountInfoViewModel = editAccountInfoViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(EditOutputData editOutputData) {
        EditAccountInfoState editAccountInfoState = editAccountInfoViewModel.getState();
        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername(editOutputData.getNewUsername());
        this.loggedInViewModel.setState(loggedInState);
        loggedInViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        EditAccountInfoState editAccountInfoState = editAccountInfoViewModel.getState();
        editAccountInfoState.setError(error);
        editAccountInfoViewModel.firePropertyChanged();
    }
}
