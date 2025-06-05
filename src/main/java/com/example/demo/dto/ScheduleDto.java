package com.example.demo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Data
public class ScheduleDto {
    private Integer id;
    private String dayOfWeek;
    @Min(1) @Max(8)
    private Integer lessonNumber;
    @Min(1)
    private Integer classroom;
    private Integer subjectId;
    private String subjectName;
    private Integer teacherId;
    private String teacherName;
}
