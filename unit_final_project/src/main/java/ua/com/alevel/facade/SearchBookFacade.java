package ua.com.alevel.facade;

import java.util.List;

public interface SearchBookFacade {

    List<String> fetchSuggestions(String query);
}
