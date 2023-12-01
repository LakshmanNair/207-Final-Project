package interface_adapter.groupChat;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_in.LoggedInViewModel;
import use_case.groupChat.GroupChatOutputBoundary;

public class GroupChatPresenter implements GroupChatOutputBoundary {
    private final LoggedInViewModel loggedInViewModel;
    private final GroupChatViewModel groupChatViewModel;
    private ViewManagerModel viewManagerModel;

    public GroupChatPresenter(ViewManagerModel viewManagerModel,
                              GroupChatViewModel groupChatViewModel,
                              LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.groupChatViewModel = groupChatViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }
    @Override
    public void prepareSuccessView() {
        viewManagerModel.setActiveView(loggedInViewModel.getViewName());
    }

    @Override
    public void prepareFailView(String error) {

    }

}
