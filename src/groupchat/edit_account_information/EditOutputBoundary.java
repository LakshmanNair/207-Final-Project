package use_case.edit_account_information;

public interface EditOutputBoundary {
    void prepareSuccessView(EditOutputData editOutputData);
    void prepareFailView(String error);
}
