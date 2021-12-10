package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.AppContext;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final AppContext context = AppContext.getInstance();

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", context.getUsers());
        return "pages/user/user_all";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("user", context.findByUserResDtoId(id));
        return "pages/user/user_details";
    }
}
