package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.entity.book.Book;
import ua.com.alevel.persistence.entity.publisher.Publisher;
import ua.com.alevel.persistence.repository.book.BookRepository;
import ua.com.alevel.persistence.repository.book.PublisherRepository;
import ua.com.alevel.service.PLPService;
import ua.com.alevel.util.WebUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PLPServiceImpl implements PLPService {

    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public PLPServiceImpl(
            BookRepository bookRepository,
            PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<Book> search(Map<String, Object> queryMap) {
        if (queryMap.get(WebUtil.PUBLISHER_PARAM) != null) {
            Long publisherId = (Long) queryMap.get(WebUtil.PUBLISHER_PARAM);
            Optional<Publisher> publisher = publisherRepository.findById(publisherId);
            if (publisher.isPresent()) {
                return bookRepository.findByPublisher(publisher.get());
            }
        }
        return bookRepository.findAll();
    }
}
