package com.example.demo.service;

import com.example.demo.model.entity.SchoolClass;
import com.example.demo.model.entity.Schoolchild;
import com.example.demo.model.entity.Teacher;
import com.example.demo.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface SchoolchildService {
    Optional<Schoolchild> findById(Integer id);
    List<Schoolchild> findBySchoolClassesContaining(SchoolClass schoolClass);
    Schoolchild save(Schoolchild schoolchild);
    Optional<Schoolchild> findByUser(User user);
    void update(Integer id, Schoolchild schoolchild);
}
