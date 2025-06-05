package com.example.demo.dto;

import com.example.demo.model.entity.SchoolClass;
import com.example.demo.model.entity.Schoolchild;
import com.example.demo.model.entity.User;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SchoolchildEditDto {
    private Schoolchild schoolchild;
    List<User> availableUsers;
    List<SchoolClass> allSchoolClasses;

}
