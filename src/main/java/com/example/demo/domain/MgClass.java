package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Data
@Table(name = "class")
public class MgClass extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int classId;
    @NotBlank(message = "Name must not be blank")
    @Size(min = 3,message = "Name must be at least 3 characters long")
    private String name;
    @OneToMany(mappedBy = "mgClass",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Person> people;
}
