package com.example.demo.dto;

import com.example.demo.model.entity.Schoolchild;
import lombok.Data;

@Data
public class SchoolchildFormDto {
    private Schoolchild schoolchild;
    private Integer schoolClassId;
}
