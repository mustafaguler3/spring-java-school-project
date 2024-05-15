package com.example.demo.controller;

import com.example.demo.domain.Contact;
import com.example.demo.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;
    private static final Logger log = LoggerFactory.getLogger(ContactController.class);

    @GetMapping("/contact")
    public String display(Model model){
        model.addAttribute("contact",new Contact());
        return "contact.html";
    }

    @GetMapping("/displayMessages/page/{pageNum}")
    public ModelAndView displayMessage(Model model,
                                       @PathVariable("pageNum") int pageNum,
                                       @PathVariable("sortField") String sortField,
                                       @RequestParam("sortDir") String sortDir){
        Page<Contact> contactMsg = contactService.findMsgWithOpenStatus(pageNum,sortField,sortDir);
        List<Contact> contactMss = contactMsg.getContent();

        ModelAndView modelAndView = new ModelAndView("messages.html");
        model.addAttribute("currentPage",pageNum);
        model.addAttribute("totalPages",contactMsg.getTotalElements());
        model.addAttribute("totalMsgs",contactMsg.getTotalElements());
        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir",sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("currentPage",pageNum);
        modelAndView.addObject("contactMsg",contactMss);

        return modelAndView;
    }

    @GetMapping("/closeMsg")
    public String closeMsg(@RequestParam int id, Authentication authentication){
        contactService.updateMsgStatus(id);
        return "redirect:/displayMessages";
    }

    @PostMapping("/saveMsg")
    public String saveMessage(@Valid @ModelAttribute("contact")Contact contact, Errors errors){

        if (errors.hasErrors()){
            log.error("Contact form validation failed due to : "+errors.toString());
            return "contact.html";
        }
        contactService.saveMessage(contact);
        return "redirect:/contact";
    }
}
