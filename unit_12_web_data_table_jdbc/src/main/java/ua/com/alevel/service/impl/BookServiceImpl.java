package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.BookDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Book;
import ua.com.alevel.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    public BookServiceImpl(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void create(Book entity) {
        bookDao.create(entity);
    }

    @Override
    public void update(Book entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Book findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<Book> findAll(DataTableRequest request) {
        return bookDao.findAll(request);
    }
}
