package com.example.demo.controller;

import com.example.demo.domain.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private PersonService personService;

    @GetMapping("/register")
    public String displayRegisterPage(Model model){
        model.addAttribute("person",new Person());
        return "register.html";
    }
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String createUser(@Valid @ModelAttribute("person") Person person, Errors errors){
        if (errors.hasErrors()){
            return "register.html";
        }
        return "redirect:/login?register=true";
    }
}


















