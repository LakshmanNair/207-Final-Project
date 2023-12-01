package use_case.search_messages;

import interface_adapter.search_messages.SearchViewModel;
public interface SearchOutputBoundary {
    void presentSearchResults(SearchViewModel viewModel);
}
