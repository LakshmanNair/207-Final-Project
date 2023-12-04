package use_case.edit_account_information;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EditInteractorTest {

    private static class EditDataAccessStub implements EditDataAccessInterface {
        private boolean exists;

        public EditDataAccessStub(boolean exists) {
            this.exists = exists;
        }

        @Override
        public boolean existsByNameAndPassword(String username, String password) {
            return exists;
        }

        @Override
        public void save(EditInputData editInputData) {
        }
    }

    private static class EditOutputBoundaryStub implements EditOutputBoundary {
        private String errorMessage;
        private EditOutputData successData;

        @Override
        public void prepareSuccessView(EditOutputData editOutputData) {
            this.successData = editOutputData;
        }

        @Override
        public void prepareFailView(String error) {
            this.errorMessage = error;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public EditOutputData getSuccessData() {
            return successData;
        }
    }

    @Test
    void setOutputBoundary() {
        // Arrange
        EditDataAccessStub dataAccessStub = new EditDataAccessStub(true);
        EditInteractor interactor = new EditInteractor(dataAccessStub);
        EditOutputBoundaryStub outputBoundaryStub = new EditOutputBoundaryStub();

        // Act
        interactor.setOutputBoundary(outputBoundaryStub);

        // Assert
        assertNotNull(outputBoundaryStub);
    }

    @Test
    void execute_Success() {
        // Arrange
        EditDataAccessStub dataAccessStub = new EditDataAccessStub(true);
        EditInteractor interactor = new EditInteractor(dataAccessStub);
        EditOutputBoundaryStub outputBoundaryStub = new EditOutputBoundaryStub();
        interactor.setOutputBoundary(outputBoundaryStub);

        EditInputData input = new EditInputData("currentUsername", "currentPassword", "newUsername", "newPassword");

        // Act
        interactor.execute(input);

        // Assert
        assertNull(outputBoundaryStub.getErrorMessage());
        assertNotNull(outputBoundaryStub.getSuccessData());
        assertEquals("newUsername", outputBoundaryStub.getSuccessData().getNewUsername());
        assertEquals("newPassword", outputBoundaryStub.getSuccessData().getNewPassword());
    }

    @Test
    void execute_Fail() {
        // Arrange
        EditDataAccessStub dataAccessStub = new EditDataAccessStub(false);
        EditInteractor interactor = new EditInteractor(dataAccessStub);
        EditOutputBoundaryStub outputBoundaryStub = new EditOutputBoundaryStub();
        interactor.setOutputBoundary(outputBoundaryStub);

        EditInputData input = new EditInputData("nonExistentUsername", "password", "newUsername", "newPassword");

        // Act
        interactor.execute(input);

        // Assert
        assertEquals("There is no record of a user with this ID", outputBoundaryStub.getErrorMessage());
        assertNull(outputBoundaryStub.getSuccessData());
    }
}