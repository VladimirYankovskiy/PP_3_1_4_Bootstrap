package ru.kata.spring.bootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.bootstrap.Service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("user")
    public void pageUser(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUserEmail(principal.getName()));
    }
}
