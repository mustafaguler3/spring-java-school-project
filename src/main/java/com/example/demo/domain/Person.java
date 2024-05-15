package com.example.demo.domain;

import com.example.demo.annotation.FieldsValueMatch;
import com.example.demo.annotation.PasswordValidator;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@FieldsValueMatch.List({
        @FieldsValueMatch(
                field = "pwd",
                fieldMatch = "confirmPwd",
                message = "Password do not match!"
        ),
        @FieldsValueMatch(
                field = "email",
                fieldMatch = "confirmEmail",
                message = "Email addresses do not match!"
        )
})
@NoArgsConstructor
public class Person extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Name must not be blank")
    private String name;
    @NotBlank(message = "Mobile Number must not be blank")
    private String mobileNumber;
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;
    @NotBlank(message = "Confirm email must not be blank")
    @Email(message = "Please provide a confirm valid email address")
    @Transient
    private String confirmEmail;
    @NotBlank(message = "Password must not be blank")
    @PasswordValidator
    @Size(min = 5,message = "Password must be at least 5 characters long")
    private String pwd;
    @NotBlank(message = "Confirm password must not be blank")
    @Transient
    @Size(min = 5,message = "Confirm password must be at least 5 characters long")
    private String confirmPwd;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id",referencedColumnName = "roleId")
    private Role role;
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
}



















