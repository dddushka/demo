package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SchoolchildDto {
    private Integer id;
    private String name;
    private Boolean enabled;
}
