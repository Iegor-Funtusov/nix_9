package ua.com.alevel.web.controller.open;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.book.Book;
import ua.com.alevel.persistence.repository.book.BookRepository;
import ua.com.alevel.web.dto.response.BookPLPDto;

import java.util.List;

@Controller
@RequestMapping("/open/books")
public class OpenBookController {

    private final BookRepository bookRepository;

    public OpenBookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    private String allBooks(Model model) {
        List<Book> books = bookRepository.findAll();
        List<BookPLPDto> bookPLPDtos = books.stream().map(BookPLPDto::new).toList();
        model.addAttribute("bookList", bookPLPDtos);
        return "pages/book/plp";
    }
}
