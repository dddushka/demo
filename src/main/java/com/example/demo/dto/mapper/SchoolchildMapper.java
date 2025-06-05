package com.example.demo.dto.mapper;

import com.example.demo.dto.*;
import com.example.demo.model.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SchoolchildMapper {
    public SchoolchildFormDto toDto(Integer schoolClassId) {
        SchoolchildFormDto dto = new SchoolchildFormDto();
        dto.setSchoolchild(new Schoolchild());
        dto.setSchoolClassId(schoolClassId);
        return dto;
    }

    public SchoolchildEditDto toDto(Schoolchild schoolchild, List<User> availableUsers, List<SchoolClass> allSchoolClasses) {
        SchoolchildEditDto dto = new SchoolchildEditDto();
        dto.setSchoolchild(schoolchild);
        dto.setAvailableUsers(availableUsers);
        dto.setAllSchoolClasses(allSchoolClasses);
        return dto;
    }

    public SchoolchildDto toDto(Schoolchild schoolchild) {
        SchoolchildDto dto = new SchoolchildDto();
        dto.setId(schoolchild.getId());
        dto.setName(schoolchild.getName());
        dto.setEnabled(schoolchild.getEnabled());
        return dto;
    }

    public Schoolchild toEntity(SchoolchildDto dto) {
        Schoolchild schoolchild = new Schoolchild();
        schoolchild.setId(dto.getId());
        schoolchild.setName(dto.getName());
        schoolchild.setEnabled(dto.getEnabled());
        return schoolchild;
    }

    public List<SchoolchildDto> toDtoList(List<Schoolchild> schoolchildren) {
        return schoolchildren.stream().map(this::toDto).toList();
    }
}
