package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.AppContext;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    private final AppContext context = AppContext.getInstance();

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("accounts", context.getAccounts());
        return "pages/account/account_all";
    }

    @GetMapping("/user/{id}")
    public String findAllByUserId(@PathVariable Long id, Model model) {
        model.addAttribute("accounts", context.getAccountsByUserId(id));
        return "pages/account/account_all";
    }

    @GetMapping("/details/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("account", context.findAccountResDtoById(id));
        return "pages/account/account_details";
    }
}
