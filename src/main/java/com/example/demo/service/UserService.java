package com.example.demo.service;

import com.example.demo.dto.AdminDashboardDto;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.School;
import com.example.demo.model.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findById(Integer id);
    Optional<User> findByUsername(String username);
    List<User> findBySchool(School school);
    void register(UserRegistrationDto dto);
    void update(Integer userId, Role role);
    void enable(Integer id);
    void disable(Integer id);
    List<User> findUnlinkedSchoolchildUsers(School school);
    List<User> findUnlinkedTeacherUsers(School school);
    List<User> findByUsername(String username, School school);
    AdminDashboardDto getDashboardData(String search, School school);
}
