package ua.com.alevel.view.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.alevel.facade.BookFacade;
import ua.com.alevel.view.dto.request.BookRequestDto;
import ua.com.alevel.view.dto.response.BookResponseDto;
import ua.com.alevel.view.dto.response.PageData;

@Controller
@RequestMapping("/books")
public class BookController extends AbstractController {

    private final BookFacade bookFacade;

    public BookController(BookFacade bookFacade) {
        this.bookFacade = bookFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<BookResponseDto> response = bookFacade.findAll(request);
        System.out.println("response = " + response);
        model.addAttribute("pageData", response);
        return "pages/book/book_all";
    }

    @GetMapping("/new")
    public String redirectToNewBookPage(Model model) {
        model.addAttribute("book", new BookRequestDto());
        return "pages/book/book_new";
    }

    @PostMapping("/create")
    public String createNewDepartment(RedirectAttributes attributes, @ModelAttribute("book") BookRequestDto dto, @RequestParam("file") MultipartFile file) {
        if (StringUtils.isBlank(file.getOriginalFilename())) {
            showError(attributes, "image can not be empty");
            return "redirect:/books/new";
        }
        dto.setBookImage(file);
        bookFacade.create(dto);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        bookFacade.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        BookResponseDto dto = bookFacade.findById(id);
        model.addAttribute("book", dto);
        return "pages/book/book_details";
    }
}
