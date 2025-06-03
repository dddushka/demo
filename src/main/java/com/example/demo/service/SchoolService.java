package com.example.demo.service;

import com.example.demo.model.entity.School;
import java.util.List;

public interface SchoolService {
    List<School> findAll();
    School update(School school);
}
