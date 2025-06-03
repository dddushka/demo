package com.example.demo.service.impl;

import com.example.demo.model.entity.Grade;
import com.example.demo.model.entity.Lesson;
import com.example.demo.model.entity.Schoolchild;
import com.example.demo.model.repository.GradeRepository;
import com.example.demo.service.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;

    @Override
    public List<Grade> findAll() {
        return gradeRepository.findAll();
    }

    @Override
    public Optional<Grade> findByLessonAndSchoolchild(Lesson lesson, Schoolchild schoolchild) {
        return gradeRepository.findByLessonAndSchoolchild(lesson, schoolchild);
    }

    @Override
    public List<Grade> findBySchoolchild(Schoolchild schoolchild) {
        return gradeRepository.findBySchoolchild(schoolchild);
    }

    @Override
    public void save(Grade grade) {
        gradeRepository.save(grade);
    }

    @Override
    public Optional<Grade> findByLessonIdAndSchoolchildId(Integer lessonId, Integer schoolchildId) {
        return gradeRepository.findByLessonIdAndSchoolchildId(lessonId, schoolchildId);
    }
}
