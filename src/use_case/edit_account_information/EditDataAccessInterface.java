package use_case.edit_account_information;

import java.io.IOException;

public interface EditDataAccessInterface {
    boolean existsByNameAndPassword(String username, String password);
    void save(EditInputData editInputData);
}
