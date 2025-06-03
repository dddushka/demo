package com.example.demo.controller;

import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.School;
import com.example.demo.model.entity.User;
import com.example.demo.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;

    @GetMapping(value="/school/info")
    public String getSchoolInfo(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("school", user.getSchool());
        model.addAttribute("isAdmin", user.getRoles().contains(Role.ROLE_ADMIN));
        model.addAttribute("editing", false);

        return "school-info";
    }

    @GetMapping("/school/info/edit")
    public String editSchoolInfo(@AuthenticationPrincipal User user, Model model) {
        if (!user.getRoles().contains(Role.ROLE_ADMIN)) {
            return "redirect:/school/info";
        }

        model.addAttribute("school", user.getSchool());
        model.addAttribute("isAdmin", true);
        model.addAttribute("editing", true);

        return "school-info";
    }

    @PostMapping("/school/info/update")
    public String updateSchoolInfo(@AuthenticationPrincipal User user, @ModelAttribute("school") School school) {
        School updated = schoolService.update(school);
        user.setSchool(updated);
        return "redirect:/school/info";
    }
}
