package com.example.demo.dto;

import lombok.Data;

import java.util.List;

@Data
public class GroupedScheduleDto {
    private String dayOfWeek;
    private List<ScheduleDto> schedules;
}
