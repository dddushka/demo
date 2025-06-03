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

    private String title;

    private String address;

    private String phone;

    private String email;

    private String directorName;

    @OneToMany
    @ToString.Exclude
    @JsonIgnore
    private List<SchoolClass> schoolClasses;
}
