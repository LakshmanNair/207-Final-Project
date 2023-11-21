package use_case.edit_account_information;

public interface EditDataAccessInterface {
    boolean existsByID(String userid);
    void save(EditInputData editInputData);
}
