package interface_adapter.search_messages;

import use_case.search_messages.SearchInputBoundary;
import use_case.search_messages.SearchInputData;

public class SearchMessagesController {
    private final SearchInputBoundary interactor;

    public SearchMessagesController(SearchInputBoundary interactor) {
        this.interactor = interactor;
    }

//    public void onSearchRequest(String chatId, String searchContent) {
    public void onSearchRequest(SearchInputData searchInputData) {
        // Called by the view when the user initiates a search
        interactor.searchMessages(searchInputData);
    }
}
