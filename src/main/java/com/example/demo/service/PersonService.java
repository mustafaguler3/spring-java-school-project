package com.example.demo.service;

import com.example.demo.domain.Person;
import com.example.demo.domain.Role;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    private PersonRepository personRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PersonService(PersonRepository personRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean createPerson(Person person){
        boolean isSaved = false;
        Role role = roleRepository.getByRoleName("Student");
        person.setRole(role);
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        person = personRepository.save(person);

        if (person != null && person.getId() > 0){
            isSaved = true;
        }
        return isSaved;
    }
}
















