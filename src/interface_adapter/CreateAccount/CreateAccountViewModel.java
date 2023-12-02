package interface_adapter.CreateAccount;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateAccountViewModel extends ViewModel
{
    public static final String TITLE_LABEL = "Sign Up View";
    public static final String USERNAME_LABEL = "Choose username";
    public static final String PASSWORD_LABEL = "Choose password";
    public static final String REPEAT_PASSWORD_LABEL = "Enter password again";

    public static final String CANCEL_BUTTON_LABEL = "Cancel";
    public static final String CREATE_ACCOUNT_BUTTON_LABEL = "Create Account";

    private CreateAccountState state = new CreateAccountState();

    public CreateAccountViewModel() {
        super("create account");
    }
    public void setState(CreateAccountState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CreateAccountState getState() {
        return state;
    }
}
