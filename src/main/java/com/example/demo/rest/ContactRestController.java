package com.example.demo.rest;

import com.example.demo.domain.Contact;
import com.example.demo.proxy.ContactProxy;
import com.example.demo.repository.ContactRepository;
import com.example.demo.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactRestController {

    @Autowired
    ContactProxy contactProxy;
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/getMessages")
    //@ResponseBody
    public List<Contact> getMessageByStatus(@RequestParam(name = "status") String status){
        return contactProxy.getMessageByStatus(status);
    }

    @PostMapping("/saveMsg")
    public ResponseEntity<ApiResponse> saveMsg(
            @RequestBody Contact contact){

        String uri = "http://localhost:8080/api/contact/saveMsg";
        HttpHeaders headers = new HttpHeaders();
        headers.add("invocationFrom","RestTemplate");
        HttpEntity<Contact> httpEntity = new HttpEntity<>(contact,headers);

        ResponseEntity<ApiResponse> responseEntity = restTemplate.exchange(uri, HttpMethod.POST,httpEntity, ApiResponse.class);

        return responseEntity;
    }


}



















