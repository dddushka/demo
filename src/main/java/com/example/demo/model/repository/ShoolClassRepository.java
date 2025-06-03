package com.example.demo.model.repository;

import com.example.demo.model.entity.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoolClassRepository extends JpaRepository<SchoolClass, Integer> {
}
