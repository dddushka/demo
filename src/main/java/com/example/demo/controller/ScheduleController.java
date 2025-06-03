package com.example.demo.controller;

import com.example.demo.dto.ScheduleDto;
import com.example.demo.dto.mapper.ScheduleMapper;
import com.example.demo.model.entity.*;
import com.example.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final SchoolClassService schoolClassService;
    private final SubjectService subjectService;
    private final TeacherService teacherService;
    private final ScheduleMapper scheduleMapper;

    @GetMapping("/schedules")
    public String getSchedules(@AuthenticationPrincipal User user, Model model) {
        List<SchoolClass> schoolClasses = schoolClassService.findBySchool(user.getSchool());
        model.addAttribute("schoolClasses", schoolClasses);
        return "schedules";
    }

    @GetMapping("/schedule/common/{schoolClassId}")
    public String getSchoolClassSchedule(@PathVariable Integer schoolClassId, Model model) {
        Optional<SchoolClass> schoolClassOpt = schoolClassService.findById(schoolClassId);
        if (schoolClassOpt.isEmpty()) {
            model.addAttribute("message", "Класс не найден");
            return "error";
        }

        SchoolClass schoolClass = schoolClassOpt.get();

        Map<String, List<ScheduleDto>> groupedSchedules = scheduleService.getGroupedSchedulesBySchoolClass(schoolClass);

        model.addAttribute("schoolClass", schoolClass);
        model.addAttribute("groupedSchedules", groupedSchedules);
        model.addAttribute("scheduleForm", new ScheduleDto());
        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("teachers", teacherService.findAll());
        model.addAttribute("dayOfWeek", DayOfWeek.values());

        return "common-schedule";
    }

    @PostMapping("/schedule/common/{schoolClassId}/save")
    public String saveSchoolClassSchedule(@PathVariable Integer schoolClassId,
                                          @ModelAttribute("scheduleForm") ScheduleDto scheduleDto) {

        SchoolClass schoolClass = schoolClassService.findById(schoolClassId).orElseThrow();
        Subject subject = subjectService.findById(scheduleDto.getSubjectId()).orElseThrow();
        Teacher teacher = teacherService.findById(scheduleDto.getTeacherId()).orElseThrow();
        Schedule schedule = scheduleMapper.toEntity(scheduleDto, subject, teacher, schoolClass);
        scheduleService.save(schedule);

        return "redirect:/schedule/common/" + schoolClassId;
    }


    @GetMapping("/schedule/common/{scheduleId}/edit")
    public String editSchoolClassSchedule(@PathVariable Integer scheduleId, Model model) {
        Schedule schedule = scheduleService.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Занятие не найдено"));

        ScheduleDto dto = scheduleMapper.toDto(schedule);
        model.addAttribute("scheduleForm", dto);
        model.addAttribute("schoolClassId", schedule.getSchoolClass().getId());
        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("teachers", teacherService.findAll());
        model.addAttribute("dayOfWeek", DayOfWeek.values());

        return "schedule-edit";
    }

    @PostMapping("/schedule/common/{scheduleId}/edit")
    public String editSchoolClassSchedule(@PathVariable Integer scheduleId,
                                          @ModelAttribute("scheduleForm") ScheduleDto scheduleDto) {
        Schedule existing = scheduleService.findById(scheduleId).orElseThrow(() -> new RuntimeException("Занятие не найдено"));
        SchoolClass schoolClass = existing.getSchoolClass();

        Subject subject = subjectService.findById(scheduleDto.getSubjectId()).orElseThrow();
        Teacher teacher = teacherService.findById(scheduleDto.getTeacherId()).orElseThrow();

        Schedule updated = scheduleMapper.toEntity(scheduleDto, subject, teacher, schoolClass);
        scheduleService.update(scheduleId, updated);

        return "redirect:/schedule/common/" + schoolClass.getId();
    }

    @PostMapping("/schedule/common/{scheduleId}/delete")
    public String deleteSchoolClassSchedule(@PathVariable Integer scheduleId,
                                @ModelAttribute("scheduleForm") Schedule scheduleForm) {
        Schedule schedule = scheduleService.findById(scheduleId).orElseThrow(() -> new RuntimeException("Занятие не найдено"));
        SchoolClass schoolClass = schedule.getSchoolClass();
        Integer schoolClassId = schoolClass.getId();
        scheduleService.delete(scheduleId);
        return "redirect:/schedule/common/" + schoolClassId;
    }
}
