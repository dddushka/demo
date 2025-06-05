package com.example.demo.dto;

import com.example.demo.model.entity.Schoolchild;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SchoolchildFormDto {
    private Schoolchild schoolchild;
    private Integer schoolClassId;
}
