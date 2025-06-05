package com.example.demo.controller;

import com.example.demo.dto.SchoolClassDto;
import com.example.demo.dto.mapper.SchoolClassMapper;
import com.example.demo.model.entity.SchoolClass;
import com.example.demo.model.entity.User;
import com.example.demo.service.SchoolClassService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SchoolClassController {
    private final SchoolClassService schoolClassService;
    private final SchoolClassMapper schoolClassMapper;

    @GetMapping(value="/schoolclasses")
    public String getSchoolClasses(@AuthenticationPrincipal User user, Model model) {
        List<SchoolClass> schoolClasses = schoolClassService.findBySchool(user.getSchool());
        List<SchoolClassDto> schoolClassListDto = schoolClassMapper.toDtoList(schoolClasses);
        model.addAttribute("schoolClasses", schoolClassListDto);
        return "schoolclasses";
    }

    @GetMapping("/schoolclass/create")
    public String getCreateSchoolClassForm(Model model) {
        model.addAttribute("schoolClass", new SchoolClassDto());
        return "create-schoolclass";
    }

    @PostMapping("/schoolclass/create")
    public String createSchoolClass(@ModelAttribute @Valid SchoolClassDto schoolClassDto,
                                    BindingResult bindingResult,
                                    @AuthenticationPrincipal User user) {
        if (bindingResult.hasErrors()) {
            return "create-schoolclass";
        }

        SchoolClass schoolClass = schoolClassMapper.toEntity(schoolClassDto);
        schoolClass.setSchool(user.getSchool());
        schoolClassService.save(schoolClass);
        return "redirect:/schoolclasses";
    }

    @GetMapping("/schoolclass/edit/{schoolClassId}")
    public String getEditSchoolClassForm(@PathVariable Integer schoolClassId, Model model) {
        SchoolClass schoolClass = schoolClassService.findById(schoolClassId).orElseThrow();
        SchoolClassDto schoolClassDto = schoolClassMapper.toDto(schoolClass);
        model.addAttribute("schoolClass", schoolClassDto);
        return "schoolclass-edit";
    }

    @PostMapping("/schoolclass/edit/{schoolClassId}")
    public String editSchoolClass(@ModelAttribute @Valid SchoolClassDto schoolClassDto,
                                  BindingResult bindingResult,
                                  @PathVariable Integer schoolClassId) {
        if (bindingResult.hasErrors()) {
            return "schoolclass-edit";
        }

        SchoolClass schoolClass = schoolClassMapper.toEntity(schoolClassDto);
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
