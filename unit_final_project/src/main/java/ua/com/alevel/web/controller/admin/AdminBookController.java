package ua.com.alevel.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.com.alevel.facade.BookFacade;
import ua.com.alevel.web.controller.AbstractController;
import ua.com.alevel.web.dto.request.BookRequestDto;
import ua.com.alevel.web.dto.response.BookResponseDto;
import ua.com.alevel.web.dto.response.PageData;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Validated
@Controller
@RequestMapping("/admin/books")
public class AdminBookController extends AbstractController {

    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#", null, null),
            new HeaderName("image", null, null),
            new HeaderName("book name", "bookName", "book_name"),
            new HeaderName("page size", "pageSize", "page_size"),
            new HeaderName("publication", "publicationDate", "publication_date"),
            new HeaderName("created", "created", "created"),
            new HeaderName("price", "price", "price"),
            new HeaderName("quantity", "quantity", "quantity"),
            new HeaderName("details", null, null),
            new HeaderName("delete", null, null)
    };

    private final BookFacade bookFacade;

    public AdminBookController(BookFacade bookFacade) {
        this.bookFacade = bookFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<BookResponseDto> response = bookFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/books/all");
        model.addAttribute("createNew", "/admin/books/new");
        model.addAttribute("cardHeader", "All Books");
        return "pages/admin/book/book_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "books");
    }

    @GetMapping("/new")
    public String redirectToNewBookPage(Model model) {
        model.addAttribute("book", new BookRequestDto());
        return "pages/admin/book/book_new";
    }

    @PostMapping("/create")
    public String createNewDepartment(RedirectAttributes attributes, @ModelAttribute("book") BookRequestDto dto, @RequestParam("file") MultipartFile file) {
        bookFacade.create(dto);
        return "redirect:/admin/books";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable @Min(value = 1, message = "id can not be zero") Long id) {
        bookFacade.delete(id);
        return "redirect:/admin/books";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable @NotBlank(message = "id can not be empty") String id, Model model) {
        try {
            Long bookId = Long.parseLong(id);
            BookResponseDto dto = bookFacade.findById(bookId);
            model.addAttribute("book", dto);
            return "pages/admin/book/book_details";
        } catch (NumberFormatException e) {
            throw new NumberFormatException("incorrect value id");
        }
    }
}
