package com.example.demo.controller;

import com.example.demo.model.entity.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    @GetMapping("/admin/dashboard")
    public String getUsers(@RequestParam(required = false) String search, Model model, @AuthenticationPrincipal User user) {
        List<User> users;
        if (search != null && !search.isEmpty()) {
            users = userService.findByUsername(search, user.getSchool());
        } else {
            users = userService.findBySchool(user.getSchool());
        }

        model.addAttribute("users", users);
        model.addAttribute("search", search);
        return "admin-dashboard";
    }

}
