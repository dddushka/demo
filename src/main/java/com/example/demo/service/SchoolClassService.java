package com.example.demo.service;

import com.example.demo.model.entity.School;
import com.example.demo.model.entity.SchoolClass;

import java.util.List;
import java.util.Optional;

public interface SchoolClassService {
    List<SchoolClass> findBySchool(School school);
    Optional<SchoolClass> findById(Integer id);
    void save(SchoolClass schoolClass);
    void update(Integer id, SchoolClass schoolClass);
    void enable(Integer id);
    void disable(Integer id);
}
