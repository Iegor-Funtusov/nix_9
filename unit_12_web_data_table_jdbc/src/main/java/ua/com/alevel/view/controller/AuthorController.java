package ua.com.alevel.view.controller;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ua.com.alevel.facade.AuthorFacade;
import ua.com.alevel.view.dto.request.AuthorRequestDto;
import ua.com.alevel.view.dto.response.AuthorResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ua.com.alevel.util.WebRequestUtil.DEFAULT_ORDER_PARAM_VALUE;

@Controller
@RequestMapping("/authors")
public class AuthorController extends AbstractController {

    private final AuthorFacade authorFacade;

    public AuthorController(AuthorFacade authorFacade) {
        this.authorFacade = authorFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest webRequest) {
        HeaderName[] columnNames = new HeaderName[] {
                new HeaderName("#", null, null),
                new HeaderName("first name", "firstName", "first_name"),
                new HeaderName("last name", "lastName", "last_name"),
                new HeaderName("book count", "bookCount", "bookCount"),
                new HeaderName("details", null, null),
                new HeaderName("delete", null, null)
        };
        PageData<AuthorResponseDto> response = authorFacade.findAll(webRequest);
        response.initPaginationState(response.getCurrentPage());
        List<HeaderData> headerDataList = new ArrayList<>();
        for (HeaderName headerName : columnNames) {
            HeaderData data = new HeaderData();
            data.setHeaderName(headerName.getColumnName());
            if (StringUtils.isBlank(headerName.getTableName())) {
                data.setSortable(false);
            } else {
                data.setSortable(true);
                data.setSort(headerName.getDbName());
                if (response.getSort().equals(headerName.getTableName())) {
                    data.setActive(true);
                    data.setOrder(response.getOrder());
                } else {
                    data.setActive(false);
                    data.setOrder(DEFAULT_ORDER_PARAM_VALUE);
                }
            }
            headerDataList.add(data);
        }

        model.addAttribute("headerDataList", headerDataList);
        model.addAttribute("createUrl", "/authors/all");
        model.addAttribute("pageData", response);
        model.addAttribute("cardHeader", "All Authors");
        return "pages/author/author_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        if (MapUtils.isNotEmpty(parameterMap)) {
            parameterMap.forEach(model::addAttribute);
        }
        return new ModelAndView("redirect:/authors", model);
    }

    @GetMapping("/new")
    public String redirectToNewAuthorPage(Model model) {
        model.addAttribute("author", new AuthorRequestDto());
        return "pages/author/author_new";
    }

    @PostMapping("/create")
    public String createNewDepartment(RedirectAttributes attributes, @ModelAttribute("author") AuthorRequestDto dto) {
        authorFacade.create(dto);
        return "redirect:/authors";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        authorFacade.delete(id);
        return "redirect:/authors";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        AuthorResponseDto dto = authorFacade.findById(id);
        model.addAttribute("author", dto);
        return "pages/author/author_details";
    }
}
