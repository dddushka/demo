package com.example.demo.controller;

import com.example.demo.dto.SchoolDto;
import com.example.demo.dto.mapper.SchoolMapper;
import com.example.demo.model.entity.School;
import com.example.demo.model.entity.User;
import com.example.demo.service.SchoolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;
    private final SchoolMapper schoolMapper;

    //get request to school information page
    @GetMapping(value="/school/info")
    public String getSchoolInfo(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("role", user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst().orElse("ROLE_USER"));

        SchoolDto schoolDto = schoolMapper.toDto(user.getSchool());

        model.addAttribute("school", schoolDto);
        model.addAttribute("editing", false);

        return "school-info";
    }

    //get request to school information edit form (only for ROLE_ADMIN)
    @GetMapping("/school/info/edit")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editSchoolInfo(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("role", user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst().orElse("ROLE_USER"));

        SchoolDto schoolDto = schoolMapper.toDto(user.getSchool());

        model.addAttribute("school", schoolDto);
        model.addAttribute("editing", true);

        return "school-info";
    }

    //post request to save school information changes (only for ROLE_ADMIN)
    @PostMapping("/school/info/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateSchoolInfo(@AuthenticationPrincipal User user,
                                   @ModelAttribute("school") @Valid SchoolDto schoolDto,
                                   BindingResult bindingResult,
                                   Model model) {
        model.addAttribute("role", user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst().orElse("ROLE_USER"));

        if (bindingResult.hasErrors()) { //dto validation
            return "school-info";
        }

        School school = schoolMapper.toEntity(schoolDto);
        try {
            School updated = schoolService.update(school);
            user.setSchool(updated);

            return "redirect:/school/info";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("school", schoolDto);
            model.addAttribute("editing", true);
            return "school-info";
        }
    }
}
