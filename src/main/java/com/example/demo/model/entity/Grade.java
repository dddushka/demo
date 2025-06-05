package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (nullable = false)
    private Integer value;

    @ManyToOne
    @JoinColumn (name = "lesson_id", nullable = false)
    private Lesson lesson;

    @ManyToOne
    @JoinColumn (name = "schoolchild_id", nullable = false)
    private Schoolchild schoolchild;
}
