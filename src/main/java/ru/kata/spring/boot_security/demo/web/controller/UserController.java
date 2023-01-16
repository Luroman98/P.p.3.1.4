package ru.kata.spring.boot_security.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.web.service.UserService;
import ru.kata.spring.boot_security.demo.web.service.UserServiceImp;

import java.security.Principal;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String printUsers(ModelMap model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "user/user";
    }
}
