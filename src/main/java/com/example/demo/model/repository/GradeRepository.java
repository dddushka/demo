package com.example.demo.model.repository;

import com.example.demo.model.entity.Grade;
import com.example.demo.model.entity.Lesson;
import com.example.demo.model.entity.Schoolchild;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Integer> {
    Optional<Grade> findByLessonAndSchoolchild(Lesson lesson, Schoolchild schoolchild);
    List<Grade> findBySchoolchild(Schoolchild schoolchild);
    Optional<Grade> findByLessonIdAndSchoolchildId(Integer lessonId, Integer schoolchildId);
}
