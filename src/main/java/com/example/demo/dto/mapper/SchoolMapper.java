package com.example.demo.dto.mapper;

import com.example.demo.dto.SchoolDto;
import com.example.demo.model.entity.School;
import org.springframework.stereotype.Component;

@Component
public class SchoolMapper {
    public SchoolDto toDto(School school) {
        SchoolDto dto = new SchoolDto();
        dto.setId(school.getId());
        dto.setTitle(school.getTitle());
        dto.setAddress(school.getAddress());
        dto.setPhone(school.getPhone());
        dto.setEmail(school.getEmail());
        dto.setDirectorName(school.getDirectorName());
        return dto;
    }

    public School toEntity(SchoolDto dto) {
        School school = new School();
        school.setId(dto.getId());
        school.setTitle(dto.getTitle());
        school.setAddress(dto.getAddress());
        school.setPhone(dto.getPhone());
        school.setEmail(dto.getEmail());
        school.setDirectorName(dto.getDirectorName());
        return school;
    }
}
