package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.AppContext;

@Controller
@RequestMapping("/operations")
public class OperationController {

    private final AppContext context = AppContext.getInstance();

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("operations", context.getOperations());
        return "pages/operation/operation_all";
    }

    @GetMapping("/account/{accId}")
    public String findAllByAccountId(@PathVariable Long accId, Model model) {
        model.addAttribute("operations", context.getOperationsByAccountId(accId));
        return "pages/operation/operation_all";
    }

    @GetMapping("/account/new/{accId}")
    public String redirectToNewOperation() {
        return "";
    }
}
