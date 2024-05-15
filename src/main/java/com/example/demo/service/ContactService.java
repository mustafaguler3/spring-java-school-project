package com.example.demo.service;

import com.example.demo.domain.Contact;
import com.example.demo.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    private static final Logger log = LoggerFactory.getLogger(ContactService.class);

    public boolean saveMessage(Contact contact){
        boolean isSaved = true;
        contact.setStatus("Open");
        contact.setCreatedBy("Anonymous");
        contact.setCreatedAt(LocalDateTime.now());

        contactRepository.save(contact);

        /*if (result > 0){
            isSaved = true;
        }*/

        return isSaved;
    }

    public boolean updateMsgStatus(int contactId){
        boolean isUpdated = false;
        Optional<Contact> contact = contactRepository.findById(contactId);

        contact.ifPresent(c -> {
            c.setStatus("Close");
        });

        Contact updatedContact = contactRepository.save(contact.get());

        if (updatedContact != null && updatedContact.getUpdatedBy() != null){
            isUpdated = true;
        }
        return isUpdated;
    }

    public List<Contact> findMsgWithOpenStatus(){
        List<Contact> contactMsg = contactRepository.findByStatus("Open");
        return contactMsg;
    }
}

























