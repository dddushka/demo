package com.example.demo.dto;

import lombok.Data;

@Data
public class ScheduleDto {
    private Integer id;
    private String dayOfWeek;
    private Integer lessonNumber;
    private Integer classroom;
    private Integer subjectId;
    private String subjectName;
    private Integer teacherId;
    private String teacherName;
}
