package com.example.demo.service;

import com.example.demo.model.entity.Subject;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    List<Subject> findAll();
    Optional<Subject> findById(Integer id);
}
