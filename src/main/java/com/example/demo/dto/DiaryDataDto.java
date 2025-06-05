package com.example.demo.dto;

import com.example.demo.model.entity.Grade;
import com.example.demo.model.entity.Lesson;
import com.example.demo.model.entity.Schoolchild;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
public class DiaryDataDto {
    private Schoolchild schoolchild;
    private Map<LocalDate, List<Lesson>> lessonsByDate;
    private Map<Integer, Grade> gradesMap;
    private LocalDate weekStart;
    private LocalDate weekEnd;
    private Integer weekOffset;
}
