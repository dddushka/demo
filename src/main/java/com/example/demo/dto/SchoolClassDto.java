package com.example.demo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SchoolClassDto {
    private Integer id;
    @Min(1) @Max(11)
    private Integer gradeLevel;
    @Size(min = 1, max = 1)
    private String letter;
    private Boolean enabled;
}
