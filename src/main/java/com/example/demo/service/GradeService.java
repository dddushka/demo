package com.example.demo.service;

import com.example.demo.model.entity.Grade;
import com.example.demo.model.entity.Lesson;
import com.example.demo.model.entity.Schoolchild;
import com.example.demo.model.repository.GradeRepository;

import java.util.List;
import java.util.Optional;

public interface GradeService {
    Optional<Grade> findByLessonAndSchoolchild(Lesson lesson, Schoolchild schoolchild);
    List<Grade> findBySchoolchild(Schoolchild schoolchild);
    void save(Grade grade);
    Optional<Grade> findByLessonIdAndSchoolchildId(Integer lessonId, Integer schoolchildId);
}
