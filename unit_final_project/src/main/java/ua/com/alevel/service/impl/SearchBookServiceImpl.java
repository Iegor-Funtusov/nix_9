package ua.com.alevel.service.impl;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import ua.com.alevel.elastic.index.BookIndex;
import ua.com.alevel.service.SearchBookService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchBookServiceImpl implements SearchBookService {

    private static final String BOOK_INDEX = "bookindex";

    private final ElasticsearchOperations elasticsearchOperations;

    public SearchBookServiceImpl(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Override
    public List<String> fetchSuggestions(String query) {
        QueryBuilder queryBuilder = QueryBuilders
                .wildcardQuery("name", query + "*");
        Query searchQuery = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .withPageable(PageRequest.of(0, 5))
                .build();
        SearchHits<BookIndex> searchSuggestions =
                elasticsearchOperations.search(searchQuery,
                        BookIndex.class,
                        IndexCoordinates.of(BOOK_INDEX));
        final List<String> suggestions = new ArrayList<>();
        searchSuggestions.getSearchHits().forEach(searchHit-> suggestions.add(searchHit.getContent().getName()));
        return suggestions;
    }
}
