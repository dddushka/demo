package com.example.demo.dto;

import com.example.demo.model.entity.Subject;
import com.example.demo.model.entity.Teacher;
import com.example.demo.model.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class TeacherEditDto {
    private Teacher teacher;
    private List<User> availableUsers;
    private List<Subject> allSubjects;
}
