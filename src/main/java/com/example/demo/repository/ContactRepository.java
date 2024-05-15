package com.example.demo.repository;

import com.example.demo.domain.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepository extends PagingAndSortingRepository<Contact,Integer> {
    //List<Contact> findByStatus(String status);
    Page<Contact> findByStatus(String status, Pageable pageable);
}








































