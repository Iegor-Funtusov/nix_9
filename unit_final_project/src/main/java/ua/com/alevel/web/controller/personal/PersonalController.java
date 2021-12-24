package ua.com.alevel.web.controller.personal;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.com.alevel.validated.ValidId;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Validated
@Controller
@RequestMapping("/personal/dashboard")
public class PersonalController {

    @GetMapping
    public String dashboard() {
        return "pages/personal/dashboard";
    }

    @GetMapping("/{id}")
    public String dashboard(@PathVariable @ValidId(message = "id must be more than zero") Long id) {
        System.out.println("id = " + id);
        return "pages/personal/dashboard";
    }
}
