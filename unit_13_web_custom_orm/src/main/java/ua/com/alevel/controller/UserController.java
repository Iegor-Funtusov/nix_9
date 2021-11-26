package ua.com.alevel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ua.com.alevel.dao.UserDao;
import ua.com.alevel.entity.User;

@Controller
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("users", userDao.findAll());
        return "user_all";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Integer id, Model model) {
        model.addAttribute("user", userDao.findById(id));
        return "user_details";
    }

    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable Integer id) {
        userDao.delete(id);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String redirectToCreatePage(Model model) {
        model.addAttribute("user", new User());
        return "user_new";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user") User user) {
        userDao.create(user);
        return "redirect:/";
    }
}
