package com.example.demo.service.impl;

import com.example.demo.model.entity.Lesson;
import com.example.demo.model.entity.Schedule;
import com.example.demo.model.entity.SchoolClass;
import com.example.demo.model.entity.Teacher;
import com.example.demo.model.repository.LessonRepository;
import com.example.demo.model.repository.ScheduleRepository;
import com.example.demo.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    private final ScheduleRepository scheduleRepository;

    @Override
    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    @Override
    public Optional<Lesson> findById(Integer lessonId) {
        return lessonRepository.findById(lessonId);
    }

    @Override
    public List<Lesson> findBySchedule(List<Schedule> schedule) {
        return lessonRepository.findBySchedule(schedule);
    }

    @Override
    public void generateLessonsFromSchedule(Integer classId, LocalDate startDate, LocalDate endDate) {
        List<Schedule> schedules = scheduleRepository.findBySchoolClassId(classId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Lesson> lessons = new ArrayList<>();

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            DayOfWeek currentDay = date.getDayOfWeek();

            for (Schedule schedule : schedules) {
                if (schedule.getDayOfWeek() == currentDay) {
                    Lesson lesson = new Lesson();
                    lesson.setDate(date.format(formatter));
                    lesson.setTopic(null);
                    lesson.setHomework(null);
                    lesson.setSchedule(schedule);

                    lessons.add(lesson);
                }
            }
        }

        lessonRepository.saveAll(lessons);
    }

    @Override
    public void save(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    @Override
    public List<Lesson> findBySchoolClass(SchoolClass schoolClass) {
        return lessonRepository.findAllBySchedule_SchoolClass(schoolClass);
    }

}
