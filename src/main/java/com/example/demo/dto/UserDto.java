package com.example.demo.dto;

import com.example.demo.model.entity.Role;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private Integer id;
    private String username;
    private List<Role> roles;
    private Boolean enabled;
}
