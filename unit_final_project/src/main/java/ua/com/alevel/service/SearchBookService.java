package ua.com.alevel.service;

import java.util.List;

public interface SearchBookService {

    List<String> fetchSuggestions(String query);
}
