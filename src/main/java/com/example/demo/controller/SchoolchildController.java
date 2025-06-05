package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.dto.mapper.SchoolClassMapper;
import com.example.demo.dto.mapper.SchoolchildMapper;
import com.example.demo.model.entity.*;
import com.example.demo.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class SchoolchildController {
    private final SchoolchildService schoolchildService;
    private final SchoolClassService schoolClassService;
    private final UserService userService;
    private final DiaryService diaryService;
    private final SchoolchildMapper schoolchildMapper;
    private final SchoolClassMapper schoolClassMapper;

    @GetMapping(value="/schoolchildren/{schoolClassId}")
    public String getSchoolchildrenForSchoolClass(@PathVariable Integer schoolClassId, Model model) {
        SchoolClass schoolClass = schoolClassService.findById(schoolClassId).orElseThrow();
        List<Schoolchild> schoolchildren = schoolchildService.findBySchoolClassesContaining(schoolClass);

        SchoolchildFormDto formDto = schoolchildMapper.toDto(schoolClassId);
        SchoolClassDto schoolClassDto = schoolClassMapper.toDto(schoolClass);
        List<SchoolchildDto> schoolchildListDto = schoolchildMapper.toDtoList(schoolchildren);

        model.addAttribute("newSchoolchildForm", formDto);
        model.addAttribute("schoolClass", schoolClassDto);
        model.addAttribute("schoolchildren", schoolchildListDto);

        return "schoolchildren";
    }

    @PostMapping("/schoolchild/add")
    public String addSchoolchild(@ModelAttribute @Valid SchoolchildFormDto formDto,
                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "schoolchildren";
        }

        SchoolClass schoolClass = schoolClassService.findById(formDto.getSchoolClassId())
                .orElseThrow(() -> new IllegalArgumentException("Класс не найден"));

        Schoolchild schoolchild = formDto.getSchoolchild();
        schoolchild.setSchoolClasses(Collections.singletonList(schoolClass));
        schoolchildService.save(schoolchild);

        return "redirect:/schoolchildren/" + formDto.getSchoolClassId();
    }

    @GetMapping("/schoolchild/edit/{schoolchildId}")
    public String editSchoolchild(@PathVariable Integer schoolchildId,
                                  Model model,
                                  @AuthenticationPrincipal User user) {
        Schoolchild schoolchild = schoolchildService.findById(schoolchildId).orElseThrow();

        List<SchoolClass> allSchoolClasses = schoolClassService.findBySchool(user.getSchool());

        List<User> availableUsers = userService.findUnlinkedSchoolchildUsers(user.getSchool());
        if (schoolchild.getUser() != null && !availableUsers.contains(schoolchild.getUser())) {
            availableUsers.add(schoolchild.getUser());
        }

        SchoolchildEditDto schoolchildEditDto = schoolchildMapper.toDto(schoolchild, availableUsers, allSchoolClasses);

        model.addAttribute("schoolchildEditDto", schoolchildEditDto);

        return "schoolchild-edit";
    }

    @PostMapping("/schoolchild/edit/{schoolchildId}")
    public String editSchoolchild(@PathVariable Integer schoolchildId,
                                  @ModelAttribute @Valid SchoolchildEditDto schoolchildEditDto,
                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "schoolchild-edit";
        }

        Schoolchild schoolchild = schoolchildEditDto.getSchoolchild();
        schoolchildService.update(schoolchildId, schoolchild);
        return "redirect:/schoolchildren/" + schoolchild.getSchoolClasses().getFirst().getId();
    }

    @GetMapping("/schoolchild/diary")
    public String getSchoolchildDiary(Model model,
                                      @AuthenticationPrincipal User user,
                                      @RequestParam(defaultValue = "0") Integer weekOffset) {
        DiaryDataDto diaryDataDto = diaryService.getDiaryData(user, weekOffset);

        model.addAttribute("schoolchild", diaryDataDto.getSchoolchild());
        model.addAttribute("lessonsByDate", diaryDataDto.getLessonsByDate());
        model.addAttribute("gradesMap", diaryDataDto.getGradesMap());
        model.addAttribute("weekOffset", diaryDataDto.getWeekOffset());
        model.addAttribute("weekStart", diaryDataDto.getWeekStart());
        model.addAttribute("weekEnd", diaryDataDto.getWeekEnd());

        return "diary";
    }
}
