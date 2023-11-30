package data_access;

import interface_adapter.PrivateChat.MessageHistory;
import use_case.search_messages.SearchDataAccessInterface;
import use_case.search_messages.SearchInputData;

import java.util.List;

public class SearchDataAccess implements SearchDataAccessInterface {
    @Override
    public List<MessageHistory> searchMessagesInChat(SearchInputData searchInputData) {
        // TODO : Return the list of MessageHistory that match the searchContent
        return null;
    }
}
