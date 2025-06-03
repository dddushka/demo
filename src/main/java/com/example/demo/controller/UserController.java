package com.example.demo.controller;

import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import static com.example.demo.model.entity.Role.ROLE_TEACHER;
import static com.example.demo.model.entity.Role.ROLE_USER;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/registration")
    public String getRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute UserRegistrationDto dto,
                               Model model) {
        Optional<User> existingUser = userService.findByUsername(dto.getUsername());

        if (existingUser.isPresent()) {
            model.addAttribute("error", "Логин уже занят");
            return "registration";
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setRoles(List.of(ROLE_USER));

        userService.save(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getAuthorizationForm() {
        return "login";
    }

    @GetMapping("/post_login")
    public String redirectAfterLogin(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new NoSuchElementException("Пользователь не найден"));

        if (user.getRoles().contains(Role.ROLE_ADMIN)) {
            return "redirect:/admin/dashboard";
        } else if (user.getRoles().contains(ROLE_TEACHER)) {
            return "redirect:/teacher/dashboard";
        } else if (user.getRoles().contains(Role.ROLE_SCHOOLCHILD)) {
            return "redirect:/schoolchild/diary";
        } else  if (user.getRoles().contains(Role.ROLE_USER)) {
            return "redirect:/user/dashboard";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable Integer id, Model model) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user-view";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable Integer id, Model model) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            model.addAttribute("roles", Role.values());
            return "user-edit";
        } else {
            return "redirect:/error";
        }
    }

    @PostMapping("/users/edit/{id}")
    public String editUser(@PathVariable Integer id, @RequestParam("role") Role role) {
        userService.update(id, role);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/users/enable/{id}")
    public String enableUser(@PathVariable Integer id) {
        userService.enable(id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/users/disable/{id}")
    public String disableUser(@PathVariable Integer id) {
        userService.disable(id);
        return "redirect:/admin/dashboard";
    }
}

