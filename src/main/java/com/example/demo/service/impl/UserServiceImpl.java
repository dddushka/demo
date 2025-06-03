package com.example.demo.service.impl;

import com.example.demo.model.entity.*;
import com.example.demo.model.repository.UserRepository;
import com.example.demo.service.SchoolchildService;
import com.example.demo.service.TeacherService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TeacherService teacherService;
    private final SchoolchildService schoolchildService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

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
    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
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

    public List<User> findByUsername(String username, School school) {
        return userRepository.findByUsernameContainingAndSchool(username, school);
    }
}
