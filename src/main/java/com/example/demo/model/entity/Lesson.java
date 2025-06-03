package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String date;

    private String topic;

    private String homework;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
}
