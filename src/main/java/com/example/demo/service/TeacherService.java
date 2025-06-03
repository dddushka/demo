package com.example.demo.service;

import com.example.demo.model.entity.School;
import com.example.demo.model.entity.Teacher;
import com.example.demo.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface TeacherService {
    List<Teacher> findAll();
    Optional<Teacher> findByUser(User user);
    List<Teacher> findBySchool(School school);
    Teacher save(Teacher teacher);
    Optional<Teacher> findById(Integer teacherId);
    void update(Integer id, Teacher teacher);
}
