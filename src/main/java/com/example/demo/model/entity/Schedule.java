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
    @Column(nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(nullable = false)
    private Integer lessonNumber;

    @Column(nullable = false)
    private Integer classroom;

    @ManyToOne
    @JoinColumn(name="schoolclass_id", nullable = false)
    private SchoolClass schoolClass;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;
}
