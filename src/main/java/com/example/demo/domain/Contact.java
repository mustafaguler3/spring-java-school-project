package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Data
@Table(name = "contact_msg")
public class Contact extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = " Name must not be blank")
    private String name;
    @NotBlank(message = " Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNum;
    @NotBlank(message = " Email must not be blank")
    @Email
    private String email;
    @NotBlank(message = " Subject must not be blank")
    @Size(min = 5,message = "Subject must be at least 5 characters long")
    private String subject;
    @NotBlank(message = " Message must not be blank")
    @Size(min =25,message = "Message must be at least 25 characters long")
    private String message;
    @NotBlank(message = " Status must not be blank")
    private String status;
}



















