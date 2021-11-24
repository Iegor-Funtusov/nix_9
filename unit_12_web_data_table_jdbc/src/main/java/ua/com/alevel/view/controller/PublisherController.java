package ua.com.alevel.view.controller;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.alevel.facade.PublisherFacade;
import ua.com.alevel.view.dto.request.PublisherRequestDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.PublisherResponseDto;

@Controller
@RequestMapping("/publishers")
public class PublisherController extends AbstractController {

    private final PublisherFacade publisherFacade;

    public PublisherController(PublisherFacade publisherFacade) {
        this.publisherFacade = publisherFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<PublisherResponseDto> response = publisherFacade.findAll(request);
        System.out.println("response = " + response);
        model.addAttribute("pageData", response);
        return "pages/publisher/publisher_all";
    }

    @GetMapping("/new")
    public String redirectToNewPublisherPage(Model model) {
        model.addAttribute("publisher", new PublisherRequestDto());
        model.addAttribute("countries", CountryCode.values());
        return "pages/publisher/publisher_new";
    }

    @PostMapping("/create")
    public String createNewDepartment(RedirectAttributes attributes, @ModelAttribute("publisher") PublisherRequestDto dto) {
        publisherFacade.create(dto);
        return "redirect:/publishers";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        publisherFacade.delete(id);
        return "redirect:/publishers";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        PublisherResponseDto dto = publisherFacade.findById(id);
        model.addAttribute("publisher", dto);
        return "pages/publisher/publisher_details";
    }
}
