package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping(value = {"/","home"})
    public String home(Model model){
        model.addAttribute("username","Mustafa Güler");
        return "home.html";
    }
}
