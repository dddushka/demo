package com.example.demo.service;

import com.example.demo.model.entity.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LessonService {
    List<Lesson> findAll();
    Optional<Lesson> findById(Integer lessonId);
    void generateLessonsFromSchedule(Integer classId, LocalDate startDate, LocalDate endDate);
    List<Lesson> findBySchedule(List<Schedule> schedule);
    void save (Lesson lesson);
    List<Lesson> findBySchoolClass(SchoolClass schoolClass);
}
