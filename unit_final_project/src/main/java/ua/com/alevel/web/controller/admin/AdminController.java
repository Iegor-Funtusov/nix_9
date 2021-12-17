package ua.com.alevel.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/dashboard")
public class AdminController {

    @GetMapping
    public String dashboard() {
        return "pages/admin/dashboard";
    }
}
