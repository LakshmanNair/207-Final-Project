package interface_adapter.editAccountInfo;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class EditAccountInfoViewModel extends ViewModel {

    public final String TITLE_LABEL = "Edit Account Info";
    public final String CURRENT_USERNAME_LABEL = "Current Username";
    public final String CURRENT_PASSWORD_LABEL = "Current Password";
    public final String NEW_USERNAME_LABEL = "New Username";
    public final String NEW_PASSWORD_LABEL = "New Password";

    public final String CONFIRM_BUTTON_LABEL = "Confirm";
    public final String CANCEL_BUTTON_LABEL = "Cancel";

    private EditAccountInfoState state = new EditAccountInfoState();

    public EditAccountInfoViewModel() {
        super("Edit Account Info");
    }

    public void setState(EditAccountInfoState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public EditAccountInfoState getState() {
        return state;
    }
}
