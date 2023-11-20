package use_case.edit_account_information;

public class EditInteractor implements EditInputBoundary{
    final EditDataAccessInterface editDataAccessObject;
    final EditOutputBoundary editPresenter;

    public EditInteractor(EditDataAccessInterface editDataAccessObject,
                          EditOutputBoundary editPresenter) {
        this.editDataAccessObject = editDataAccessObject;
        this.editPresenter = editPresenter;
    }

    @Override
    public void execute(EditInputData editInputData) {
        if (editDataAccessObject.existsByID(editInputData.getUserid())) {
            editDataAccessObject.save(editInputData);
            EditOutputData editOutputData = new EditOutputData(editInputData.getNewUsername(), editInputData.getUserid());
            editPresenter.prepareSuccessView(editOutputData);
        } else {
            editPresenter.prepareFailView("There is no record of a user with this ID");
        }
    }
}
