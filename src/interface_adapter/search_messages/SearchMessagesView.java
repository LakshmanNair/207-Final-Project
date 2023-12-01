package interface_adapter.search_messages;

import javax.swing.*;
import interface_adapter.search_messages.SearchMessagesController;
import interface_adapter.search_messages.SearchViewModel;

public class SearchMessagesView {
    private final SearchMessagesController controller;

    public SearchMessagesView(SearchMessagesController controller) {
        this.controller = controller;
        // TODO: Initialize Swing components and layout
        // Setup action listeners to call controller methods
    }

    public void displaySearchResults(SearchViewModel viewModel) {
        // TODO: Update UI components with the results from viewModel
    }
}