package com.example.demo.model.repository;

import com.example.demo.model.entity.School;
import com.example.demo.model.entity.Teacher;
import com.example.demo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Optional<Teacher> findByUser(User user);

    List<Teacher> findByUser_School(School userSchool);
}
