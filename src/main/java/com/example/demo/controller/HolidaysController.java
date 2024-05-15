package com.example.demo.controller;

import com.example.demo.domain.Holiday;
import com.example.demo.repository.HolidayRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Controller
public class HolidaysController {

    private HolidayRepository holidayRepository;

    @Autowired
    public HolidaysController(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    @GetMapping("/holidays")
    public String displayHolidays(@RequestParam(required = false) boolean festival,@RequestParam(required = false) boolean federal, Model model){

        model.addAttribute("festival",festival);
        model.addAttribute("federal",federal);

        List<Holiday> holidays = Arrays.asList(
                new Holiday("23 April","national sovereignty and children's day", Holiday.Type.FEDERAL),
                new Holiday("15 June","Eid al-Adha", Holiday.Type.FEDERAL),
                new Holiday("30 August","victory feast", Holiday.Type.FEDERAL),
                new Holiday("23 Oct","republic holiday", Holiday.Type.FEDERAL),
                new Holiday("19 August","victory feast 2", Holiday.Type.FESTIVAL),
                new Holiday("24 Oct","republic holiday 2", Holiday.Type.FESTIVAL)
        );

        Holiday.Type[] types = Holiday.Type.values();

        for (Holiday.Type type : types){
            model.addAttribute(type.toString(),holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList()));
        }

        return "holidays.html";
    }

    @GetMapping("/holidays/{display}")
    public String displayHolidays(@PathVariable String display,Model model){

        if (display != null && display.equals("all")){
            model.addAttribute("festival",true);
            model.addAttribute("federal",true);
        } else if (display != null && display.equals("federal")) {
            model.addAttribute("federal",true);
        } else if (display != null && display.equals("festival")) {
            model.addAttribute("festival",true);
        }
        Iterable<Holiday> holidays = holidayRepository.findAll();
        List<Holiday> holidayList = StreamSupport.stream(holidays.spliterator(),false).collect(Collectors.toList());

        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types){
            model.addAttribute(type.toString(),holidayList.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList()));
        }

        return "holidays.html";
    }
}
















