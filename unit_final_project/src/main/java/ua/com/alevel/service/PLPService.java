package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.book.Book;

import java.util.List;
import java.util.Map;

public interface PLPService {

    List<Book> search(Map<String, Object> queryMap);
}
