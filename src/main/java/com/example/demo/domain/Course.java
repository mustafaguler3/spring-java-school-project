package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int courseId;
    private String name;
    private String fees;

    @ManyToMany(mappedBy = "courses",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Person> people = new HashSet<>();
}



















