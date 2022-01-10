package ua.com.alevel.persistence.repository.book;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.book.Book;
import ua.com.alevel.persistence.entity.publisher.Publisher;
import ua.com.alevel.persistence.repository.BaseRepository;

import java.util.List;

@Repository
public interface BookRepository extends BaseRepository<Book> {

    List<Book> findByPublisher(Publisher publisher);
    List<Book> findByBookNameContaining(String bookName);
}
