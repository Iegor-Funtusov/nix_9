package ua.com.alevel.persistence.repository.book;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.book.Book;
import ua.com.alevel.persistence.repository.BaseRepository;

@Repository
public interface BookRepository extends BaseRepository<Book> { }
