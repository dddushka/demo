package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer value;

    @ManyToOne
    private Lesson lesson;

    @ManyToOne
    private Schoolchild schoolchild;
}
