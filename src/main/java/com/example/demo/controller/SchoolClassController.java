package com.example.demo.controller;

import com.example.demo.model.entity.SchoolClass;
import com.example.demo.model.entity.User;
import com.example.demo.service.SchoolClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SchoolClassController {
    private final SchoolClassService schoolClassService;

    @GetMapping(value="/schoolclasses")
    public String getSchoolClasses(@AuthenticationPrincipal User user, Model model) {
        List<SchoolClass> schoolClasses = schoolClassService.findBySchool(user.getSchool());
        model.addAttribute("schoolClasses", schoolClasses);
        return "schoolclasses";
    }

    @GetMapping("/schoolclass/create")
    public String getCreateSchoolClassForm(Model model) {
        model.addAttribute("schoolClass", new SchoolClass());
        return "create-schoolclass";
    }

    @PostMapping("/schoolclass/create")
    public String createSchoolClass(@ModelAttribute SchoolClass schoolClass,
                                    @AuthenticationPrincipal User user) {
        schoolClass.setSchool(user.getSchool());
        schoolClassService.save(schoolClass);
        return "redirect:/schoolclasses";
    }

    @GetMapping("/schoolclass/edit/{schoolClassId}")
    public String getEditSchoolClassForm(@PathVariable Integer schoolClassId, Model model) {
        SchoolClass schoolClass = schoolClassService.findById(schoolClassId).orElseThrow();
        model.addAttribute("schoolClass", schoolClass);
        return "schoolclass-edit";
    }

    @PostMapping("/schoolclass/edit/{schoolClassId}")
    public String editSchoolClass(@ModelAttribute SchoolClass schoolClass,
                                  @PathVariable Integer schoolClassId) {
        schoolClassService.update(schoolClassId, schoolClass);
        return "redirect:/schoolclasses";
    }

    @GetMapping("/schoolclass/enable/{schoolClassId}")
    public String enableSchoolClass(@PathVariable Integer schoolClassId) {
        schoolClassService.enable(schoolClassId);
        return "redirect:/schoolclasses";
    }

    @GetMapping("/schoolclass/disable/{schoolClassId}")
    public String disableSchoolClass(@PathVariable Integer schoolClassId) {
        schoolClassService.disable(schoolClassId);
        return "redirect:/schoolclasses";
    }
}
