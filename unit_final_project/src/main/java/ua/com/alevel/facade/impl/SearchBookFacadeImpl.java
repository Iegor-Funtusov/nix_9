package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.elastic.index.BookIndex;
import ua.com.alevel.facade.SearchBookFacade;
import ua.com.alevel.service.SearchBookService;

import java.util.List;

@Service
public class SearchBookFacadeImpl implements SearchBookFacade {

    private final SearchBookService searchBookService;

    public SearchBookFacadeImpl(SearchBookService searchBookService) {
        this.searchBookService = searchBookService;
    }

    @Override
    public List<String> fetchSuggestions(String query) {
        return searchBookService.fetchSuggestions(query);
    }
}
