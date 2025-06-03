package com.example.demo.service;

import com.example.demo.model.entity.Role;
import com.example.demo.model.entity.School;
import com.example.demo.model.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Integer id);
    Optional<User> findByUsername(String username);
    List<User> findBySchool(School school);
    User save(User user);
    void update(Integer userId, Role role);
    void enable(Integer id);
    void disable(Integer id);
    List<User> findUnlinkedSchoolchildUsers(School school);
    List<User> findUnlinkedTeacherUsers(School school);
    List<User> findByUsername(String username, School school);
}
