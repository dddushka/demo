package com.example.demo.model.repository;

import com.example.demo.model.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolRepository extends JpaRepository<School, Integer> {
    Optional<School> findByTitle(String title);
    Optional<School> findByEmail(String email);
}
