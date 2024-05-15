package com.example.demo.controller;

import com.example.demo.domain.MgClass;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/displayClasses")
    public ModelAndView displayClasses(Model model){
        ModelAndView modelAndView = new ModelAndView("classes.html");
        return modelAndView;
    }

    public ModelAndView addNewClass(Model model, @ModelAttribute("mgClass") MgClass mgClass){

    }
}



















