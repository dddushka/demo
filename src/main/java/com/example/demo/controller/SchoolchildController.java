package com.example.demo.controller;

import com.example.demo.dto.DiaryDataDto;
import com.example.demo.dto.SchoolchildFormDto;
import com.example.demo.model.entity.*;
import com.example.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class SchoolchildController {
    private final SchoolchildService schoolchildService;
    private final SchoolClassService schoolClassService;
    private final UserService userService;
    private final DiaryService diaryService;

    @GetMapping(value="/schoolchildren/{schoolClassId}")
    public String getSchoolchildrenForSchoolClass(@PathVariable Integer schoolClassId, Model model) {
        SchoolClass schoolClass = schoolClassService.findById(schoolClassId).orElseThrow();
        List<Schoolchild> schoolchildren = schoolchildService.findBySchoolClassesContaining(schoolClass);

        SchoolchildFormDto formDto = new SchoolchildFormDto();
        formDto.setSchoolchild(new Schoolchild());
        formDto.setSchoolClassId(schoolClassId);

        model.addAttribute("newSchoolchildForm", formDto);
        model.addAttribute("schoolClass", schoolClass);
        model.addAttribute("schoolchildren", schoolchildren);
        model.addAttribute("newSchoolchildForm", formDto);

        return "schoolchildren";
    }

    @PostMapping("/schoolchild/add")
    public String addSchoolchild(@ModelAttribute SchoolchildFormDto formDto) {
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
        model.addAttribute("schoolchild", schoolchild);
        model.addAttribute("allClasses", schoolClassService.findBySchool(user.getSchool()));

        List<User> availableUsers = userService.findUnlinkedSchoolchildUsers(user.getSchool());
        if (schoolchild.getUser() != null && !availableUsers.contains(schoolchild.getUser())) {
            availableUsers.add(schoolchild.getUser());
        }

        model.addAttribute("allUsers", availableUsers);
        return "schoolchild-edit";
    }

    @PostMapping("/schoolchild/edit/{schoolchildId}")
    public String editSchoolchild(@PathVariable Integer schoolchildId,
                                  @ModelAttribute Schoolchild schoolchild,
                                  @RequestParam("schoolClasses[0]") Integer schoolClassId) {
        schoolchildService.update(schoolchildId, schoolchild);
        return "redirect:/schoolchildren/" + schoolClassId;
    }

    @GetMapping("/schoolchild/diary")
    public String getSchoolchildDiary(Model model, @AuthenticationPrincipal User user, @RequestParam(defaultValue = "0") int weekOffset) {
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
