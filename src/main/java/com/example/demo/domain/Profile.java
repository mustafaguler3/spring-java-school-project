package com.example.demo.domain;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class Profile {

    @NotBlank(message = "name must not be blank")
    @Size(min = 3,message = "Name must be at least 5 characters long")
    private String name;
    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String mobileNumber;
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;
    @NotBlank(message = "Address1 must not be blank")
    @Size(min = 5,message = "Address1 must be at least 5 characters long")
    private String address1;
    @NotBlank(message = "Address2 must not be blank")
    @Size(min = 5,message = "Address2 must be at least 5 characters long")
    private String address2;
    @NotBlank(message = "City must not be blank")
    @Size(min = 5,message = "City must be at least 5 characters long")
    private String city;
    @NotBlank(message = "State must not be blank")
    @Size(min = 5,message = "State must be at least 5 characters long")
    private String state;
    @NotBlank(message = "Zip code must not be blank")
    @Pattern(regexp = "(^$|[0-9]{5})",message = "Zip code must be 5 digits")
    private int zipCode;
}
