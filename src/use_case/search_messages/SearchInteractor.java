package use_case.search_messages;

import interface_adapter.PrivateChat.MessageHistory;
import interface_adapter.search_messages.SearchViewModel;

import java.util.List;

public class SearchInteractor implements SearchInputBoundary {
    private final SearchDataAccessInterface dataAccess;
    private final SearchOutputBoundary presenter;

    public SearchInteractor(SearchDataAccessInterface dataAccess, SearchOutputBoundary presenter) {
        this.dataAccess = dataAccess;
        this.presenter = presenter;
    }

    @Override
    public void searchMessages(SearchInputData searchInputData) {
        // Business logic to search messages
        // Use dataAccess to retrieve messages and filter based on the query
        List<MessageHistory> searchResults = dataAccess.searchMessagesInChat(searchInputData);
        // Use presenter to format the results for the view
        SearchViewModel viewModel = convertToViewModel(searchResults);
        presenter.presentSearchResults(viewModel);
    }

    private SearchViewModel convertToViewModel(List<MessageHistory> searchResults) {
        // Convert search results into a view model.
        SearchViewModel viewModel = new SearchViewModel();
        // TODO: prepare data for presentation.
//        viewModel.setMessages(searchResults.stream()
//                .map(messageHistory -> /* conversion logic */)
//                .collect(Collectors.toList()));
        return viewModel;
    }
}