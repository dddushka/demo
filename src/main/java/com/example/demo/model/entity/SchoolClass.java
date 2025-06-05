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

    @Column(nullable = false)
    private Integer gradeLevel;

    @Column(nullable = false)
    private String letter;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

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
