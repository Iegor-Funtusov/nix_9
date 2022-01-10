package ua.com.alevel.web.controller.open;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.alevel.facade.PLPFacade;
import ua.com.alevel.facade.SearchBookFacade;

import java.util.List;

@Controller
@RequestMapping("/books")
public class OpenBookController {

    private final PLPFacade plpFacade;
    private final SearchBookFacade searchBookFacade;

    public OpenBookController(PLPFacade plpFacade, SearchBookFacade searchBookFacade) {
        this.plpFacade = plpFacade;
        this.searchBookFacade = searchBookFacade;
    }

    @GetMapping
    private String allBooks(Model model, WebRequest webRequest) {
        model.addAttribute("bookList", plpFacade.search(webRequest));
        return "pages/open/plp";
    }

    @PostMapping("/search")
    private String allBooksSearch(
            RedirectAttributes redirectAttributes, @RequestParam String bookSearch) {
        redirectAttributes.addAttribute("bookSearch", bookSearch);
        return "redirect:/books";
    }

    @GetMapping("/suggestions")
    @ResponseBody
    public List<String> fetchSuggestions(@RequestParam(value = "q", required = false) String query) {
        System.out.println("OpenBookController.fetchSuggestions: " + query);
        return searchBookFacade.fetchSuggestions(query);
    }
}
