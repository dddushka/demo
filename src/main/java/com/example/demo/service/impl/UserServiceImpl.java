package com.example.demo.service.impl;

import com.example.demo.dto.AdminDashboardDto;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.School;
import com.example.demo.model.entity.User;
import com.example.demo.model.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findBySchool(School school) {
        return userRepository.findBySchool(school);
    }

    @Override
    public void register(UserRegistrationDto dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username is already in use");
        }

        if (dto.getPassword() == null || dto.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password is empty");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRoles(List.of(Role.ROLE_USER));
        userRepository.save(user);
    }

    @Override
    public void update(Integer userId, Role role) {
        User user = userRepository.findById(userId).orElseThrow(NoSuchElementException::new);
        List<Role> newRoles = new ArrayList<>();
        newRoles.add(role);
        user.setRoles(newRoles);
        userRepository.save(user);
    }

    @Override
    public void enable(Integer id) {
        User user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public void disable(Integer id) {
        User user = userRepository.findById(id).orElseThrow(NoSuchElementException::new);
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Override
    public List<User> findUnlinkedSchoolchildUsers(School school) {
        return userRepository.findAvailableUsersWithRole(school.getId(), Role.ROLE_SCHOOLCHILD);
    }

    @Override
    public List<User> findUnlinkedTeacherUsers(School school) {
        return userRepository.findAvailableUsersWithRole(school.getId(), Role.ROLE_TEACHER);
    }

    @Override
    public List<User> findByUsername(String username, School school) {
        return userRepository.findByUsernameContainingAndSchool(username, school);
    }

    @Override
    public AdminDashboardDto getDashboardData(String search, School school) {
        List<User> users;

        if (search != null && !search.isEmpty()) {
            users = findByUsername(search, school);
        } else {
            users = findBySchool(school);
        }

        return new AdminDashboardDto(users, search);
    }
}
