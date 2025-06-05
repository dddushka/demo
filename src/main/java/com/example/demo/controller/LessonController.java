package com.example.demo.controller;

import com.example.demo.dto.SchoolClassDto;
import com.example.demo.dto.mapper.SchoolClassMapper;
import com.example.demo.model.entity.SchoolClass;
import com.example.demo.model.entity.User;
import com.example.demo.service.LessonService;
import com.example.demo.service.SchoolClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class LessonController {
    private final LessonService lessonService;
    private final SchoolClassService schoolClassService;
    private final SchoolClassMapper schoolClassMapper;

    //get request to lesson generator
    @GetMapping("/lessons/generate")
    public String showLessonGenerationForm(@AuthenticationPrincipal User user, Model model) {
        List<SchoolClass> schoolClasses = schoolClassService.findBySchool(user.getSchool());
        List<SchoolClassDto> schoolClassListDto = schoolClassMapper.toDtoList(schoolClasses);
        model.addAttribute("schoolClasses", schoolClassListDto);
        return "lesson-generator";
    }

    //post request to generate lessons
    @PostMapping("/lessons/generate")
    public String generateLessons(@RequestParam Integer classId,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        lessonService.generateLessonsFromSchedule(classId, startDate, endDate);
        return "redirect:/schedules";
    }

}
