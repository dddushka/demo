package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SchoolDto {
    private Integer id;
    private String title;
    private String address;
    private String phone;
    @Email
    private String email;
    private String directorName;
}
