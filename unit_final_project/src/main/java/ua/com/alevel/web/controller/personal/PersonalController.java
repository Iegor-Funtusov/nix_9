package ua.com.alevel.web.controller.personal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/personal/dashboard")
public class PersonalController {

    @GetMapping
    public String dashboard() {
        return "pages/personal/dashboard";
    }
}
