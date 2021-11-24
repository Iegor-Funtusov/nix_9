package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.BookFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Book;
import ua.com.alevel.service.BookService;
import ua.com.alevel.util.ImageUtil;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.request.BookRequestDto;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.BookResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookFacadeImpl implements BookFacade {

    private final BookService bookService;

    public BookFacadeImpl(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void create(BookRequestDto bookRequestDto) {
        Book book = new Book();
        book.setBookName(bookRequestDto.getBookName());
        book.setPublicationDate(bookRequestDto.getPublicationDate());
        book.setDescription(bookRequestDto.getDescription());
        book.setPageSize(bookRequestDto.getPageSize());
        book.setImageUrl(ImageUtil.uploadAndGetImageUrl(bookRequestDto.getBookImage()));
        bookService.create(book);
    }

    @Override
    public void update(BookRequestDto bookRequestDto, Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public BookResponseDto findById(Long id) {
        return null;
    }

    @Override
    public PageData<BookResponseDto> findAll(WebRequest request) {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);

        DataTableRequest dataTableRequest = new DataTableRequest();
        dataTableRequest.setOrder(sortData.getOrder());
        dataTableRequest.setSort(sortData.getSort());
        dataTableRequest.setCurrentPage(pageAndSizeData.getPage());
        dataTableRequest.setPageSize(dataTableRequest.getPageSize());

        DataTableResponse<Book> all = bookService.findAll(dataTableRequest);

        PageData<BookResponseDto> pageData = new PageData<>();
        List<BookResponseDto> items = all.getItems().stream().map(BookResponseDto::new).collect(Collectors.toList());
        pageData.setItems(items);

        return pageData;
    }
}
