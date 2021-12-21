package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.BookFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.book.Book;
import ua.com.alevel.service.BookService;
import ua.com.alevel.util.WebUtil;
import ua.com.alevel.web.dto.request.BookRequestDto;
import ua.com.alevel.web.dto.response.BookResponseDto;
import ua.com.alevel.web.dto.response.PageData;

import java.util.List;
import java.util.Optional;
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
        book.setImageUrl(bookRequestDto.getBookImage());
        bookService.create(book);
    }

    @Override
    public void update(BookRequestDto bookRequestDto, Long id) {
        Optional<Book> optionalBook = bookService.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setBookName(bookRequestDto.getBookName());
            book.setPublicationDate(bookRequestDto.getPublicationDate());
            book.setDescription(bookRequestDto.getDescription());
            book.setPageSize(bookRequestDto.getPageSize());
            book.setImageUrl(bookRequestDto.getBookImage());
            bookService.update(book);
        }
    }

    @Override
    public void delete(Long id) {
        bookService.delete(id);
    }

    @Override
    public BookResponseDto findById(Long id) {
        Book book = bookService.findById(id).get();
        return new BookResponseDto(book);
    }

    @Override
    public PageData<BookResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebUtil.generateDataTableRequestByWebRequest(request);
        DataTableResponse<Book> tableResponse = bookService.findAll(dataTableRequest);
        List<BookResponseDto> books = tableResponse.getItems().stream().
                map(BookResponseDto::new).
                collect(Collectors.toList());

        PageData<BookResponseDto> pageData = (PageData<BookResponseDto>) WebUtil.initPageData(tableResponse);
        pageData.setItems(books);
        return pageData;
    }
}
