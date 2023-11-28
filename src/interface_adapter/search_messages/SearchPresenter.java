package interface_adapter.search_messages;

import use_case.search_messages.SearchOutputBoundary;

public class SearchPresenter implements SearchOutputBoundary {
    @Override
    private final SearchMessagesView searchMessagesView;

    public SearchPresenter(SearchMessagesView searchMessagesView) {
        this.searchMessagesView = searchMessagesView;
    }

    @Override
    public void presentSearchResults(SearchViewModel viewModel) {
        // Here you would transform the viewModel if needed and then update the view
        searchMessagesView.displaySearchResults(viewModel);
    }
}
