package com.example.demo.controller;

import com.example.demo.dto.ScheduleDto;
import com.example.demo.dto.SchoolClassDto;
import com.example.demo.dto.SubjectDto;
import com.example.demo.dto.TeacherDto;
import com.example.demo.dto.mapper.ScheduleMapper;
import com.example.demo.dto.mapper.SchoolClassMapper;
import com.example.demo.dto.mapper.SubjectMapper;
import com.example.demo.dto.mapper.TeacherMapper;
import com.example.demo.model.entity.*;
import com.example.demo.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    private final TeacherMapper teacherMapper;
    private final SubjectMapper subjectMapper;
    private final SchoolClassMapper schoolClassMapper;

    //get request to schedules page
    @GetMapping("/schedules")
    public String getSchedules(@AuthenticationPrincipal User user, Model model) {
        List<SchoolClass> schoolClasses = schoolClassService.findBySchool(user.getSchool());
        List<SchoolClassDto> schoolClassListDto = schoolClassMapper.toDtoList(schoolClasses);
        model.addAttribute("schoolClasses", schoolClassListDto);
        return "schedules";
    }

    //get request to school class schedule page
    @GetMapping("/schedule/common/{schoolClassId}")
    public String getSchoolClassSchedule(@PathVariable Integer schoolClassId, Model model) {
        Optional<SchoolClass> schoolClassOpt = schoolClassService.findById(schoolClassId);
        if (schoolClassOpt.isEmpty()) {
            model.addAttribute("message", "School class not found");
            return "error";
        }

        SchoolClass schoolClass = schoolClassOpt.get();
        SchoolClassDto schoolClassDto = schoolClassMapper.toDto(schoolClass);

        Map<String, List<ScheduleDto>> groupedSchedules = scheduleService.getGroupedSchedulesBySchoolClass(schoolClass);
        List<SubjectDto> subjectListDto = subjectMapper.toDtoList(subjectService.findAll());
        List<TeacherDto> teacherListDto = teacherMapper.toDtoList(teacherService.findAll());

        model.addAttribute("schoolClass", schoolClassDto);
        model.addAttribute("groupedSchedules", groupedSchedules);
        model.addAttribute("scheduleForm", new ScheduleDto());
        model.addAttribute("subjects", subjectListDto);
        model.addAttribute("teachers", teacherListDto);
        model.addAttribute("dayOfWeek", DayOfWeek.values());

        return "common-schedule";
    }

    //post request to save schedule changes
    @PostMapping("/schedule/common/{schoolClassId}/save")
    public String saveSchoolClassSchedule(@PathVariable Integer schoolClassId,
                                          @ModelAttribute("scheduleForm") @Valid ScheduleDto scheduleDto,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { //dto validation
            return "common-schedule";
        }

        SchoolClass schoolClass = schoolClassService.findById(schoolClassId).orElseThrow();
        Subject subject = subjectService.findById(scheduleDto.getSubjectId()).orElseThrow();
        Teacher teacher = teacherService.findById(scheduleDto.getTeacherId()).orElseThrow();
        Schedule schedule = scheduleMapper.toEntity(scheduleDto, subject, teacher, schoolClass);
        scheduleService.save(schedule);

        return "redirect:/schedule/common/" + schoolClassId;
    }

    //get request to schedule change page
    @GetMapping("/schedule/common/{scheduleId}/edit")
    public String editSchoolClassSchedule(@PathVariable Integer scheduleId, Model model) {
        Schedule schedule = scheduleService.findById(scheduleId)
                .orElseThrow(() -> new RuntimeException("Schedule not found"));

        ScheduleDto dto = scheduleMapper.toDto(schedule);
        List<SubjectDto> subjectListDto = subjectMapper.toDtoList(subjectService.findAll());
        List<TeacherDto> teacherListDto = teacherMapper.toDtoList(teacherService.findAll());

        model.addAttribute("scheduleForm", dto);
        model.addAttribute("schoolClassId", schedule.getSchoolClass().getId());
        model.addAttribute("subjects", subjectListDto);
        model.addAttribute("teachers", teacherListDto);
        model.addAttribute("dayOfWeek", DayOfWeek.values());

        return "schedule-edit";
    }

    //post request to save schedule changes
    @PostMapping("/schedule/common/{scheduleId}/edit")
    public String editSchoolClassSchedule(@PathVariable Integer scheduleId,
                                          @ModelAttribute("scheduleForm") @Valid ScheduleDto scheduleDto,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { //dto validation
            return "schedule-edit";
        }

        Schedule existing = scheduleService.findById(scheduleId).orElseThrow(() -> new RuntimeException("Schedule not found"));
        SchoolClass schoolClass = existing.getSchoolClass();

        Subject subject = subjectService.findById(scheduleDto.getSubjectId()).orElseThrow();
        Teacher teacher = teacherService.findById(scheduleDto.getTeacherId()).orElseThrow();

        Schedule updated = scheduleMapper.toEntity(scheduleDto, subject, teacher, schoolClass);
        scheduleService.update(scheduleId, updated);

        return "redirect:/schedule/common/" + schoolClass.getId();
    }

    //post request to delete schedule
    @PostMapping("/schedule/common/{scheduleId}/delete")
    public String deleteSchoolClassSchedule(@PathVariable Integer scheduleId) {
        Schedule schedule = scheduleService.findById(scheduleId).orElseThrow(() -> new RuntimeException("Schedule not found"));
        SchoolClass schoolClass = schedule.getSchoolClass();
        Integer schoolClassId = schoolClass.getId();
        scheduleService.delete(scheduleId);
        return "redirect:/schedule/common/" + schoolClassId;
    }
}
