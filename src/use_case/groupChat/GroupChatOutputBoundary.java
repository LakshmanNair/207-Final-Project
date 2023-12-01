package use_case.groupChat;

public interface GroupChatOutputBoundary {
    void prepareSuccessView();

    void prepareFailView(String error);
}
