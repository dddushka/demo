package com.example.demo.service.impl;

import com.example.demo.dto.ScheduleDto;
import com.example.demo.dto.SchoolClassDto;
import com.example.demo.dto.SubjectDto;
import com.example.demo.dto.mapper.ScheduleMapper;
import com.example.demo.dto.mapper.SchoolClassMapper;
import com.example.demo.dto.mapper.SubjectMapper;
import com.example.demo.model.entity.Schedule;
import com.example.demo.model.entity.SchoolClass;
import com.example.demo.model.entity.Subject;
import com.example.demo.model.entity.Teacher;
import com.example.demo.model.repository.ScheduleRepository;
import com.example.demo.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;
    private final SubjectMapper subjectMapper;
    private final SchoolClassMapper schoolClassMapper;

    @Override
    public Optional<Schedule> findById(Integer id) {
        return scheduleRepository.findById(id);
    }

    //grouped school classes by subject
    @Override
    public Map<SubjectDto, List<SchoolClassDto>> getSubjectsAndSchoolClassesByTeacher(Teacher teacher) {
        List<Schedule> schedules = scheduleRepository.findByTeacher(teacher);
        Map<SubjectDto, List<SchoolClassDto>> map = new HashMap<>();
        for (Schedule schedule : schedules) {
            SubjectDto subjectDto = subjectMapper.toDto(schedule.getSubject());
            SchoolClassDto schoolClassDto = schoolClassMapper.toDto(schedule.getSchoolClass());

            List<SchoolClassDto> schoolClassListDto = map.computeIfAbsent(subjectDto, k -> new ArrayList<>());
            if (!schoolClassListDto.contains(schoolClassDto)) {
                schoolClassListDto.add(schoolClassDto);
            }
        }

        return map;
    }

    @Override
    public List<Schedule> findByTeacherAndSubjectAndSchoolClass(Teacher teacher, Subject subject, SchoolClass schoolClass) {
        return scheduleRepository.findByTeacherAndSubjectAndSchoolClass(teacher, subject, schoolClass);
    }

    @Override
    public List<Schedule> findBySchoolClass(SchoolClass schoolClass) {
        return scheduleRepository.findBySchoolClass(schoolClass);
    }

    @Override
    public void save(Schedule schedule) {
        scheduleRepository.save(schedule);
    }

    @Override
    public void update(Integer id, Schedule newSchedule) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        schedule.setDayOfWeek(newSchedule.getDayOfWeek());
        schedule.setLessonNumber(newSchedule.getLessonNumber());
        schedule.setClassroom(newSchedule.getClassroom());
        schedule.setSubject(newSchedule.getSubject());
        schedule.setTeacher(newSchedule.getTeacher());

        scheduleRepository.save(schedule);
    }

    @Override
    public void delete(Integer id) {
        scheduleRepository.deleteById(id);
    }

    //grouped schedule by day of week
    @Override
    public Map<String, List<ScheduleDto>> getGroupedSchedulesBySchoolClass(SchoolClass schoolClass) {
        List<Schedule> schedules = scheduleRepository.findBySchoolClass(schoolClass);

        Map<String, List<ScheduleDto>> grouped = new LinkedHashMap<>();
        for (DayOfWeek day : DayOfWeek.values()) {
            grouped.put(day.name(), new ArrayList<>());
        }
        for (Schedule schedule : schedules) {
            grouped.get(schedule.getDayOfWeek().name()).add(scheduleMapper.toDto(schedule));
        }
        return grouped;
    }

}
