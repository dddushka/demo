package com.example.demo.dto.mapper;

import com.example.demo.dto.JournalDataDto;
import com.example.demo.model.entity.*;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component
public class JournalMapper {
    public JournalDataDto toDto(Subject subject,
                                SchoolClass schoolClass,
                                List<Schoolchild> schoolchildren,
                                List<Lesson> lessons,
                                Map<Integer, Map<Integer, Integer>> grades) {
        return new JournalDataDto(subject, schoolClass, schoolchildren, lessons, grades);
    }
}
