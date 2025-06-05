package com.example.demo.dto;

import com.example.demo.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AdminDashboardDto {
    private List<User> users;
    private String search;
}
