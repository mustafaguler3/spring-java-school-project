package com.example.demo.repository;

import com.example.demo.domain.Contact;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ContactRepository extends PagingAndSortingRepository<Contact,Integer> {
    List<Contact> findByStatus(String status);

    @Query("select c from Contact c where c.status =: status")
    Page<Contact> findByStatus(String status, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Contact c Set c.status =?1 where c.contactId =?2")
    int updateStatusById(String status,int id);
}








































