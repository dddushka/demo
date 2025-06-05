package com.example.demo.model.repository;

import com.example.demo.model.entity.Schedule;
import com.example.demo.model.entity.SchoolClass;
import com.example.demo.model.entity.Subject;
import com.example.demo.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByTeacher(Teacher teacher);
    List<Schedule> findByTeacherAndSubjectAndSchoolClass(Teacher teacher, Subject subject, SchoolClass schoolClass);
    List<Schedule> findBySchoolClass(SchoolClass schoolClass);
    List<Schedule> findBySchoolClassId(Integer schoolClassId);
}
