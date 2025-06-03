package com.example.demo.model.repository;

import com.example.demo.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SchoolchildRepository extends JpaRepository<Schoolchild, Integer> {
    List<Schoolchild> findBySchoolClassesContaining(SchoolClass schoolClass);
    Optional<Schoolchild> findByUser(User user);
}
