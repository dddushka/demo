package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;

@Data
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private Integer lessonNumber;

    private Integer classroom;

    @ManyToOne
    @JoinColumn(name="schoolclass_id")
    private SchoolClass schoolClass;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Teacher teacher;
}
