package com.example.demo.service.impl;

import com.example.demo.dto.JournalDataDto;
import com.example.demo.dto.JournalSaveDto;
import com.example.demo.dto.mapper.JournalMapper;
import com.example.demo.model.entity.*;
import com.example.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class JournalServiceImpl implements JournalService {
    private final TeacherService teacherService;
    private final SubjectService subjectService;
    private final SchoolClassService schoolClassService;
    private final SchoolchildService schoolchildService;
    private final ScheduleService scheduleService;
    private final GradeService gradeService;
    private final LessonService lessonService;
    private final JournalMapper journalMapper;

    @Override
    public JournalDataDto fillJournal(User user, Integer subjectId, Integer schoolClassId) {
        Teacher teacher = teacherService.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        Subject subject = subjectService.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        SchoolClass schoolClass = schoolClassService.findById(schoolClassId)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        List<Schoolchild> schoolchildren = schoolchildService.findBySchoolClassesContaining(schoolClass);

        List<Schedule> schedule = scheduleService.findByTeacherAndSubjectAndSchoolClass(teacher, subject, schoolClass);

        List<Lesson> lessons = lessonService.findBySchedule(schedule);

        Map<Integer, Map<Integer, Integer>> grades = new HashMap<>();
        for (Schoolchild schoolchild : schoolchildren) {
            Map<Integer, Integer> lessonGrades = new HashMap<>();
            for (Lesson lesson : lessons) {
                Optional<Grade> gradeOpt = gradeService.findByLessonAndSchoolchild(lesson, schoolchild);
                gradeOpt.ifPresent(grade -> lessonGrades.put(lesson.getId(), grade.getValue()));
            }
            grades.put(schoolchild.getId(), lessonGrades);
        }
        return journalMapper.toDto(subject, schoolClass, schoolchildren, lessons, grades);
    }

    @Override
    public void processAndSaveJournal(JournalSaveDto dto, Integer schoolClassId, Integer subjectId) {
        saveJournalChanges(dto.getGrades(), dto.getTopics(), dto.getHomeworks(), schoolClassId, subjectId);

    }

    @Override
    public void saveJournalChanges(Map<Integer, Map<Integer, String>> grades,
                                   Map<Integer, String> topics,
                                   Map<Integer, String> homeworks,
                                   Integer schoolClassId,
                                   Integer subjectId) {
        if (topics == null) topics = Collections.emptyMap();
        if (homeworks == null) homeworks = Collections.emptyMap();
        if (grades == null) grades = Collections.emptyMap();

        for (Map.Entry<Integer, String> entry : topics.entrySet()) {
            Integer lessonId = entry.getKey();
            String topic = entry.getValue();

            lessonService.findById(lessonId).ifPresent(lesson -> {
                lesson.setTopic(topic);
                lessonService.save(lesson);
            });
        }

        for (Map.Entry<Integer, String> entry : homeworks.entrySet()) {
            Integer lessonId = entry.getKey();
            String homework = entry.getValue();

            lessonService.findById(lessonId).ifPresent(lesson -> {
                lesson.setHomework(homework);
                lessonService.save(lesson);
            });
        }

        for (Map.Entry<Integer, Map<Integer, String>> schoolchildEntry : grades.entrySet()) {
            Integer schoolchildId = schoolchildEntry.getKey();
            Map<Integer, String> lessonGrades = schoolchildEntry.getValue();

            for (Map.Entry<Integer, String> lessonEntry : lessonGrades.entrySet()) {
                Integer lessonId = lessonEntry.getKey();
                String value = lessonEntry.getValue();

                if (value == null || value.trim().isEmpty()) continue;

                try {
                    int gradeValue = Integer.parseInt(value.trim());

                    Optional<Grade> existingGrade = gradeService.findByLessonIdAndSchoolchildId(lessonId, schoolchildId);
                    if (existingGrade.isPresent()) {
                        Grade grade = existingGrade.get();
                        grade.setValue(gradeValue);
                        gradeService.save(grade);
                    } else {
                        Grade newGrade = new Grade();
                        newGrade.setValue(gradeValue);
                        newGrade.setLesson(lessonService.findById(lessonId).orElse(null));
                        newGrade.setSchoolchild(schoolchildService.findById(schoolchildId).orElse(null));
                        gradeService.save(newGrade);
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Ошибка при разборе оценки для schoolchildId=" + schoolchildId + ", lessonId=" + lessonId + ": " + value);
                    e.printStackTrace();
                }
            }
        }
    }
}
