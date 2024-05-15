package com.example.demo.controller;

import com.example.demo.domain.Contact;
import com.example.demo.domain.Course;
import com.example.demo.domain.MgClass;
import com.example.demo.domain.Person;
import com.example.demo.repository.ClassRepository;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/displayClasses")
    public ModelAndView displayClasses(Model model){
        ModelAndView modelAndView = new ModelAndView("classes.html");
        return modelAndView;
    }
    @RequestMapping("/displayStudents")
    public ModelAndView addStudent(Model model, @ModelAttribute("person") Person person, HttpSession session){

        ModelAndView modelAndView = new ModelAndView();
        MgClass mgClass = (MgClass) session.getAttribute("mgClass");
        Person personEntity = personRepository.readByEmail(person.getEmail());

        if (personEntity == null || !(personEntity.getPersonId() > 0)){
            modelAndView.setViewName("redirect:/admin/displayStudents?classId="+mgClass.getClassId()+"&error=true");
            return modelAndView;
        }
        personEntity.setMgClass(mgClass);
        personRepository.save(personEntity);
        mgClass.getPeople().add(personEntity);

        modelAndView.setViewName("redirect:/admin/displayStudents?classId="+mgClass.getClassId());

        return modelAndView;
    }

    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(Model model,
                                        @RequestParam int classId,
                                        HttpSession session,
                                        @RequestParam(value = "error",required = false) String error){
        ModelAndView modelAndView = new ModelAndView("students.html");
        String errorMessage = null;
        Optional<MgClass> mgClass = classRepository.findById(classId);
        modelAndView.addObject("mgClass",mgClass.get());
        modelAndView.addObject("person",new Person());
        session.setAttribute("mgClass",mgClass.get());

        if (error != null){
            errorMessage = "Invalid Email entered";
            modelAndView.addObject("errorMessage",errorMessage);
        }

        return modelAndView;
    }

    @GetMapping("/deleteClass")
    public ModelAndView deleteClass(Model model, @RequestParam int id){
        Optional<MgClass> mgClass = classRepository.findById(id);
        for (Person person : mgClass.get().getPeople()){
            person.setMgClass(null);
            personRepository.save(person);
        }
        classRepository.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @ModelAttribute("mgClass") MgClass mgClass){
        classRepository.save(mgClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(Model model,
                                      @RequestParam int personId,HttpSession session){
        MgClass mgClass = (MgClass) session.getAttribute("mgClass");
        Optional<Person> person = personRepository.findById(personId);
        person.get().setMgClass(null);
        mgClass.getPeople().remove(person.get());
        classRepository.save(mgClass);

        session.setAttribute("mgClass",classRepository.save(mgClass));

        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId="+mgClass.getClass());

        return modelAndView;
    }

    @GetMapping("/displayCourses")
    public ModelAndView displayCourses(Model model){
        List<Course> courses = courseRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("courses_secure.html");
        modelAndView.addObject("courses",courses);
        modelAndView.addObject("course",new Course());

        return modelAndView;
    }
    @PostMapping("/addNewCourse")
    public ModelAndView addNewCourse(Model model,@ModelAttribute("course") Course course){
        ModelAndView modelAndView = new ModelAndView();
        courseRepository.save(course);
        modelAndView.setViewName("redirect:/admin/displayCourses");

        return modelAndView;
    }

    @GetMapping("/viewStudents")
    public ModelAndView viewStudents(Model model,@RequestParam int id){
        ModelAndView modelAndView = new ModelAndView("course_students.html");
        Optional<Course> courses = courseRepository.findById(id);
        modelAndView.addObject("courses",courses.get());
        modelAndView.addObject("person",new Person());

        return modelAndView;
    }
}



















