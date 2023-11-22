package ru.kata.spring.bootstrap.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.bootstrap.Service.RoleService;
import ru.kata.spring.bootstrap.Service.UserService;
import ru.kata.spring.bootstrap.models.User;

import java.security.Principal;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String pageAdmin(Model model, Principal principal) {
        User user = userService.findByUserEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("people", userService.allUsers());
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", roleService.getAllRoles());
        return "/admin";
    }

    @PostMapping("/newUser")
    public String createNewUser(@ModelAttribute("user") User user) {
        getUserRoles(user);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/user_id")
    public String saveUser(@ModelAttribute("user") User user, @RequestParam(value = "nameId") Long id) {
        user.setId(id);
        getUserRoles(user);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete")
    public String delUser(@RequestParam(value = "id") Long id) {
        userService.delUser(id);
        return "redirect:/admin";
    }

    private void getUserRoles(User user) {
        user.setRoles(user.getRoles().stream()
                .map(role -> roleService.getRoleByName(role.getRole()))
                .collect(Collectors.toSet()));
    }
}
