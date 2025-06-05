package com.example.demo.controller;

import com.example.demo.dto.AdminDashboardDto;
import com.example.demo.model.entity.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;

    //get request to admin main page
    @GetMapping("/admin/dashboard")
    public String getUsers(@RequestParam(required = false) String search, Model model, @AuthenticationPrincipal User user) {
        AdminDashboardDto dashboardData = userService.getDashboardData(search, user.getSchool());

        model.addAttribute("dashboardData", dashboardData);

        return "admin-dashboard";
    }
}
