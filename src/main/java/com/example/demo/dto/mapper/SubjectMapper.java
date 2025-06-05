package com.example.demo.dto.mapper;

import com.example.demo.dto.SubjectDto;
import com.example.demo.model.entity.Subject;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SubjectMapper {
    public SubjectDto toDto (Subject subject) {
        SubjectDto dto = new SubjectDto();
        dto.setId(subject.getId());
        dto.setSubjectName(subject.getSubjectName());
        return dto;
    }

    public List<SubjectDto> toDtoList(List<Subject> subjects) {
        return subjects.stream().map(this::toDto).toList();
    }
}
