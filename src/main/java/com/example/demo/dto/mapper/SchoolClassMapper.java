package com.example.demo.dto.mapper;

import com.example.demo.dto.SchoolClassDto;
import com.example.demo.dto.TeacherDto;
import com.example.demo.model.entity.SchoolClass;
import com.example.demo.model.entity.Teacher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SchoolClassMapper {
    public SchoolClassDto toDto(SchoolClass schoolClass) {
        SchoolClassDto schoolClassDto = new SchoolClassDto();
        schoolClassDto.setId(schoolClass.getId());
        schoolClassDto.setGradeLevel(schoolClass.getGradeLevel());
        schoolClassDto.setLetter(schoolClass.getLetter());
        schoolClassDto.setEnabled(schoolClass.getEnabled());
        return schoolClassDto;
    }

    public SchoolClass toEntity(SchoolClassDto schoolClassDto) {
        SchoolClass schoolClass = new SchoolClass();
        schoolClass.setId(schoolClassDto.getId());
        schoolClass.setGradeLevel(schoolClassDto.getGradeLevel());
        schoolClass.setLetter(schoolClassDto.getLetter());
        if (schoolClassDto.getEnabled() == null) {
            schoolClass.setEnabled(true);
        } else {
            schoolClass.setEnabled(schoolClassDto.getEnabled());
        }
        return schoolClass;
    }

    public List<SchoolClassDto> toDtoList(List<SchoolClass> schoolClasses) {
        return schoolClasses.stream().map(this::toDto).toList();
    }
}
