package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.AuthorDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Author;
import ua.com.alevel.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public void create(Author entity) {

    }

    @Override
    public void update(Author entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Author findById(Long id) {
        return null;
    }

    @Override
    public DataTableResponse<Author> findAll(DataTableRequest request) {
        DataTableResponse<Author> dataTableResponse = authorDao.findAll(request);
        long count = authorDao.count();
        dataTableResponse.setItemsSize(count);
        return dataTableResponse;
    }
}
