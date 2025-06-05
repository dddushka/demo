package com.example.demo.service.impl;

import com.example.demo.dto.DiaryDataDto;
import com.example.demo.dto.mapper.DiaryMapper;
import com.example.demo.model.entity.*;
import com.example.demo.service.DiaryService;
import com.example.demo.service.GradeService;
import com.example.demo.service.LessonService;
import com.example.demo.service.SchoolchildService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DiaryServiceImpl implements DiaryService {
    private final SchoolchildService schoolchildService;
    private final LessonService lessonService;
    private final GradeService gradeService;
    private final DiaryMapper diaryMapper;

    public DiaryDataDto getDiaryData(User user, Integer weekOffset) {
        Schoolchild schoolchild = schoolchildService.findByUser(user).orElseThrow(() -> new RuntimeException("Schoolchild not found"));

        List<SchoolClass> schoolClasses = schoolchild.getSchoolClasses();
        SchoolClass schoolClass = schoolClasses.get(0);
        List<Lesson> allLessons = lessonService.findBySchoolClass(schoolClass);

        Map<LocalDate, List<Lesson>> lessonsByDate = new HashMap<>();
        for (Lesson lesson : allLessons) {
            LocalDate date = LocalDate.parse(lesson.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            lessonsByDate.computeIfAbsent(date, k -> new ArrayList<>()).add(lesson);
        }

        LocalDate today = LocalDate.now().plusWeeks(weekOffset);
        LocalDate weekStart = today.with(DayOfWeek.MONDAY);
        LocalDate weekEnd = today.with(DayOfWeek.SUNDAY);

        Map<LocalDate, List<Lesson>> currentWeekLessons = new TreeMap<>();
        for (Map.Entry<LocalDate, List<Lesson>> entry : lessonsByDate.entrySet()) {
            if (!entry.getKey().isBefore(weekStart) && !entry.getKey().isAfter(weekEnd)) {
                currentWeekLessons.put(entry.getKey(), entry.getValue());
            }
        }

        List<Grade> grades = gradeService.findBySchoolchild(schoolchild);
        Map<Integer, Grade> gradesMap = new HashMap<>();
        for (Grade grade : grades) {
            if (grade.getLesson() != null) {
                gradesMap.put(grade.getLesson().getId(), grade);
            }
        }

        return diaryMapper.toDto(schoolchild, currentWeekLessons, gradesMap, weekOffset, weekStart, weekEnd);
    }
}
