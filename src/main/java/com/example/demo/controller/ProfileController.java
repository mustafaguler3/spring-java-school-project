package com.example.demo.controller;

import com.example.demo.domain.Address;
import com.example.demo.domain.Person;
import com.example.demo.domain.Profile;
import com.example.demo.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ProfileController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/displayProfile")
    public ModelAndView displayProfile(Model model, HttpSession session){
        Person person = (Person) session.getAttribute("loggedInPerson");
        Profile profile = new Profile();

        profile.setName(person.getName());
        profile.setMobileNumber(person.getMobileNumber());
        person.setEmail(person.getEmail());

        if (person.getAddress() != null && person.getAddress().getId() > 0){
            profile.setAddress1(person.getAddress().getAddress1());
            profile.setAddress2(person.getAddress().getAddress2());
            profile.setCity(person.getAddress().getCity());
            profile.setState(person.getAddress().getState());
            profile.setZipCode(person.getAddress().getZipCode());
        }

        ModelAndView modelAndView = new ModelAndView("profile.html");
        modelAndView.addObject("profile",profile);
        return modelAndView;
    }

    @GetMapping("/updateProfile")
    public String updateProfile(@Valid @ModelAttribute("profile") Profile profile, Errors errors,HttpSession session){
        if (errors.hasErrors()){
            return "profile.html";
        }

        Person person = (Person) session.getAttribute("loggedInPerson");
        person.setName(profile.getName());
        person.setMobileNumber(profile.getMobileNumber());
        person.setEmail(profile.getEmail());

        if (person.getAddress() == null || !(person.getAddress().getId() > 0)){
            person.setAddress(new Address());
        }

        person.getAddress().setAddress1(profile.getAddress1());
        person.getAddress().setAddress2(profile.getAddress2());
        person.getAddress().setCity(profile.getCity());
        person.getAddress().setState(profile.getState());
        person.getAddress().setZipCode(profile.getZipCode());

        personRepository.save(person);

        session.setAttribute("loggedInPerson",person);

        return "redirect:/displayProfile";
    }
}



















