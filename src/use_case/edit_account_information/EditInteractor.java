package use_case.edit_account_information;

public class EditInteractor implements EditInputBoundary{
    private final EditDataAccessInterface editDataAccessObject;
    private EditOutputBoundary editPresenter;

    public EditInteractor(EditDataAccessInterface editDataAccessObject) {
        this.editDataAccessObject = editDataAccessObject;
    }

    public void setOutputBoundary(EditOutputBoundary editPresenter) {
        this.editPresenter = editPresenter;
    }

    @Override
    public void execute(EditInputData editInputData) {
        if (editDataAccessObject.existsByNameAndPassword(editInputData.getCurrentUsername(),
                editInputData.getCurrentPassword())) {
            editDataAccessObject.save(editInputData);
            EditOutputData editOutputData = new EditOutputData(editInputData.getNewUsername(), editInputData.getNewPassword());
            editPresenter.prepareSuccessView(editOutputData);
        } else {
            editPresenter.prepareFailView("There is no record of a user with this ID");
        }
    }
}
