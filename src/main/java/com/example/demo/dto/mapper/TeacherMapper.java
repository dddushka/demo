package com.example.demo.dto.mapper;

import com.example.demo.dto.TeacherEditDto;
import com.example.demo.dto.TeacherDto;
import com.example.demo.model.entity.Subject;
import com.example.demo.model.entity.Teacher;
import com.example.demo.model.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherMapper {
    public TeacherEditDto toDto(Teacher teacher, List<User> availableUsers, List<Subject> allSubjects) {
        TeacherEditDto dto = new TeacherEditDto();
        dto.setTeacher(teacher);
        dto.setAvailableUsers(availableUsers);
        dto.setAllSubjects(allSubjects);
        return dto;
    }

    public TeacherDto toDto(Teacher teacher) {
        TeacherDto dto = new TeacherDto();
        dto.setId(teacher.getId());
        dto.setName(teacher.getName());
        dto.setEnabled(teacher.getEnabled());
        return dto;
    }

    public List<TeacherDto> toDtoList(List<Teacher> teachers) {
        return teachers.stream().map(this::toDto).toList();
    }
}
