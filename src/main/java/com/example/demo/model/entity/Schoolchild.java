package com.example.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import java.util.List;

@Data
@Entity
public class Schoolchild {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @Column(nullable = false)
    private Boolean enabled = true;

    @ManyToMany
    @JoinTable(name = "schoolchild_schoolclass",
            joinColumns = @JoinColumn(name = "schoolchild_id"),
            inverseJoinColumns = @JoinColumn(name = "schoolclass_id"))
    @ToString.Exclude
    @JsonIgnore
    private List<SchoolClass> schoolClasses;
}
