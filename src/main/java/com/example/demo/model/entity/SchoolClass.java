package com.example.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name="schoolclass")
public class SchoolClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    private Integer gradeLevel;

    private String letter;

    @Column(nullable = false)
    private Boolean enabled = true;

    @ManyToMany
    @JoinTable (name = "schoolchild_schoolclass",
            joinColumns = @JoinColumn(name = "schoolclass_id"),
            inverseJoinColumns = @JoinColumn(name = "schoolchild_id"))
    @ToString.Exclude
    @JsonIgnore
    private List<Schoolchild> schoolchildren;
}
