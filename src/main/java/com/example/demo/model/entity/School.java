package com.example.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.util.List;

@Data
@Entity
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String directorName;

    @OneToMany
    @ToString.Exclude
    @JsonIgnore
    private List<SchoolClass> schoolClasses;
}
