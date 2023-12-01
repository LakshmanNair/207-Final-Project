package use_case.search_messages;

public class SearchInputData {

    private final int chatID;
    private final String searchContent;

    public SearchInputData(int chatID, String searchContent) {
        this.chatID = chatID;
        this.searchContent = searchContent;
    }

    int getChatID() {
        return chatID;
    }

    String getSearchContent() {
        return searchContent;
    }

}