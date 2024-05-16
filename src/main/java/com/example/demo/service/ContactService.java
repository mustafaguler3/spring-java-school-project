package com.example.demo.service;

import com.example.demo.domain.Contact;
import com.example.demo.repository.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        //contactRepository.save(contact);

        /*if (result > 0){
            isSaved = true;
        }*/

        return isSaved;
    }

    public boolean updateMsgStatus(int contactId){
        boolean isUpdated = false;
        int rows = contactRepository.updateStatusById("Close",contactId);

        if (rows > 0){
            isUpdated = true;
        }
        return isUpdated;
    }

    public Page<Contact> findMsgWithOpenStatus(int pageNum,String sortField,String sortDir){
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1,pageSize,sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());

        Page<Contact> contactMsg = contactRepository.findByStatus("Open",pageable);

        return contactMsg;
    }
}

























