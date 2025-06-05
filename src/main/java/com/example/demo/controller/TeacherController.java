package com.example.demo.controller;

import com.example.demo.dto.SchoolClassDto;
import com.example.demo.dto.SubjectDto;
import com.example.demo.dto.TeacherDto;
import com.example.demo.dto.TeacherEditDto;
import com.example.demo.dto.mapper.TeacherMapper;
import com.example.demo.model.entity.Subject;
import com.example.demo.model.entity.Teacher;
import com.example.demo.model.entity.User;
import com.example.demo.service.ScheduleService;
import com.example.demo.service.SubjectService;
import com.example.demo.service.TeacherService;
import com.example.demo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;
    private final ScheduleService scheduleService;
    private final UserService userService;
    private final SubjectService subjectService;
    private final TeacherMapper teacherMapper;

    //get request to list of teachers page
    @GetMapping("/teachers")
    public String getTeachers(Model model, @AuthenticationPrincipal User user) {
        List<Teacher> teachers = teacherService.findBySchool(user.getSchool());
        List<TeacherDto> teacherListDto = teacherMapper.toDtoList(teachers);

        model.addAttribute("teachers", teacherListDto);
        model.addAttribute("newTeacher", new TeacherDto());

        return "teachers";
    }

    //get request to teacher edit form
    @GetMapping("/teacher/edit/{teacherId}")
    public String editTeacher(@AuthenticationPrincipal User user, Model model, @PathVariable Integer teacherId) {
        Optional<Teacher> teacherOpt = teacherService.findById(teacherId);

        if (teacherOpt.isPresent()) {
            Teacher teacher = teacherOpt.get();
            List<User> availableUsers = userService.findUnlinkedTeacherUsers(user.getSchool());
            if (teacher.getUser() != null && !availableUsers.contains(teacher.getUser())) {
                availableUsers.add(teacher.getUser());
            }

            List<Subject> allSubjects = subjectService.findAll();

            TeacherEditDto dto = teacherMapper.toDto(teacher, availableUsers, allSubjects);

            model.addAttribute("teacherEditDto", dto);
            return "teacher-edit";
        } else {
            return "redirect:/error";
        }
    }

    //post request to save teacher changes
    @PostMapping("/teacher/edit/{teacherId}")
    public String editTeacher(@PathVariable Integer teacherId,
                              @ModelAttribute @Valid TeacherEditDto teacherEditDto,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { //dto validation
            return "teacher-edit";
        }

        Teacher teacher = teacherEditDto.getTeacher();
        teacherService.update(teacherId, teacher);
        return "redirect:/teachers";
    }

    //get request to teacher main page
    @GetMapping("/teacher/dashboard")
    public String getTeacherDashboard(Model model, @AuthenticationPrincipal User user) {
        Optional<Teacher> teacherOpt = teacherService.findByUser(user);
        if (teacherOpt.isPresent()) {
            Teacher teacher = teacherOpt.get();

            Map<SubjectDto, List<SchoolClassDto>> classesBySubjectsDto = scheduleService.getSubjectsAndSchoolClassesByTeacher(teacher);
            model.addAttribute("classesBySubjects", classesBySubjectsDto);

            return "teacher-dashboard";
        } else {
            return "redirect:/error";
        }
    }
}
