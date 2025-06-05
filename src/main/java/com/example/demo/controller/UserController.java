package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.dto.mapper.UserMapper;
import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.User;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import static com.example.demo.model.entity.Role.ROLE_TEACHER;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/registration")
    public String getRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute @Valid UserRegistrationDto dto,
                               BindingResult bindingResult,
                               Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        try {
            userService.register(dto);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", dto);
            return "registration";
        }
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
        Optional<User> userOpt = userService.findByUsername(userDetails.getUsername());
        if (userOpt.isEmpty()) {
            return "redirect:/login";
        }
        User user = userOpt.get();

        if (user.getRoles().contains(Role.ROLE_ADMIN)) {
            return "redirect:/admin/dashboard";
        } else if (user.getRoles().contains(ROLE_TEACHER)) {
            return "redirect:/teacher/dashboard";
        } else if (user.getRoles().contains(Role.ROLE_SCHOOLCHILD)) {
            return "redirect:/schoolchild/diary";
        } else  if (user.getRoles().contains(Role.ROLE_USER)) {
            return "redirect:/user/dashboard";
        } else {
            return "error";
        }
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable Integer id, Model model) {
        Optional<User> user = userService.findById(id);

        if (user.isPresent()) {
            UserDto userDto = userMapper.toDto(user.get());
            model.addAttribute("user", userDto);
            return "user-view";
        } else {
            return "error";
        }
    }

    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable Integer id, Model model) {
        Optional<User> user = userService.findById(id);

        if (user.isPresent()) {
            UserDto userDto = userMapper.toDto(user.get());
            model.addAttribute("user", userDto);
            model.addAttribute("roles", Role.values());
            return "user-edit";
        } else {
            return "error";
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

