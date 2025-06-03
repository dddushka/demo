package com.example.demo.model.repository;

import com.example.demo.model.entity.School;
import com.example.demo.model.entity.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Integer> {
    List<SchoolClass> findBySchool(School school);
}
