package ua.com.alevel.elastic.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import ua.com.alevel.elastic.index.BookIndex;

@Repository
public interface BookIndexRepository extends ElasticsearchRepository<BookIndex, String> { }
