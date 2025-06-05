package com.example.demo.dto.mapper;

import com.example.demo.dto.ScheduleDto;
import com.example.demo.model.entity.Schedule;
import com.example.demo.model.entity.SchoolClass;
import com.example.demo.model.entity.Subject;
import com.example.demo.model.entity.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;

@Component
public class ScheduleMapper {

    public ScheduleDto toDto(Schedule schedule) {
        ScheduleDto dto = new ScheduleDto();
        dto.setId(schedule.getId());
        dto.setDayOfWeek(schedule.getDayOfWeek().name());
        dto.setLessonNumber(schedule.getLessonNumber());
        dto.setClassroom(schedule.getClassroom());
        dto.setSubjectId(schedule.getSubject().getId());
        dto.setSubjectName(schedule.getSubject().getSubjectName());
        dto.setTeacherId(schedule.getTeacher().getId());
        dto.setTeacherName(schedule.getTeacher().getName());
        return dto;
    }

    public Schedule toEntity(ScheduleDto dto, Subject subject, Teacher teacher, SchoolClass schoolClass) {
        Schedule schedule = new Schedule();
        schedule.setId(dto.getId());
        schedule.setDayOfWeek(DayOfWeek.valueOf(dto.getDayOfWeek()));
        schedule.setLessonNumber(dto.getLessonNumber());
        schedule.setClassroom(dto.getClassroom());
        schedule.setSubject(subject);
        schedule.setTeacher(teacher);
        schedule.setSchoolClass(schoolClass);
        return schedule;
    }
}

