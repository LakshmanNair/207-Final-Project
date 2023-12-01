package use_case.search_messages;
import interface_adapter.PrivateChat.MessageHistory;

import java.util.List;
public interface SearchDataAccessInterface {
    List<MessageHistory> searchMessagesInChat(SearchInputData searchInputData);
}
