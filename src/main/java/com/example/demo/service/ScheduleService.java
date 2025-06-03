package com.example.demo.service;

import com.example.demo.dto.ScheduleDto;
import com.example.demo.model.entity.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ScheduleService {
    Optional<Schedule> findById(Integer id);
    Map<Subject, List<SchoolClass>> getSubjectsAndSchoolClassesByTeacher(Teacher teacher);
    List<Schedule> findByTeacherAndSubjectAndSchoolClass(Teacher teacher, Subject subject, SchoolClass schoolClass);
    List<Schedule> findBySchoolClass(SchoolClass schoolClass);
    void save(Schedule schedule);
    void update(Integer id, Schedule newSchedule);
    void delete(Integer id);
    Map<String, List<ScheduleDto>> getGroupedSchedulesBySchoolClass(SchoolClass schoolClass);
}
