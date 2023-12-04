package interface_adapter.groupChat;

import interface_adapter.ViewManagerModel;
import use_case.groupChat.GroupChatOutputBoundary;

public class GroupChatPresenter implements GroupChatOutputBoundary {
    private final GroupChatViewModel groupChatViewModel;
    private ViewManagerModel viewManagerModel;

    public GroupChatPresenter(ViewManagerModel viewManagerModel,
                              GroupChatViewModel groupChatViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.groupChatViewModel = groupChatViewModel;
    }
    @Override
    public void prepareSuccessView() {
        viewManagerModel.setActiveView(groupChatViewModel.getViewName());
    }

    @Override
    public void prepareFailView(String error) {

    }

}
