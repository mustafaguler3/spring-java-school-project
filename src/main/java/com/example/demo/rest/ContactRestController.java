package com.example.demo.rest;

import com.example.demo.domain.Contact;
import com.example.demo.repository.ContactRepository;
import com.example.demo.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ContactRestController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/getMessageByStatus")
    @ResponseBody
    public List<Contact> getMessageByStatus(@RequestParam(name = "status") String status){
        return contactRepository.findByStatus(status);
    }
    @GetMapping("/getAllMsgByStatus")
    @ResponseBody
    public List<Contact> getAllMsgByStatus(@RequestBody Contact contact){
        if (contact != null && contact.getStatus() != null){
            return contactRepository.findByStatus(contact.getStatus());
        }else {
            return List.of();
        }
    }

    @PostMapping("/saveMsg")
    public ResponseEntity<ApiResponse> saveMsg(@RequestHeader("invocationFrom") String invocation,
                                               @Valid @RequestBody Contact contact){

        contactRepository.save(contact);
        ApiResponse response = new ApiResponse();
        response.setStatusCode(200);
        response.setMessage("Message saved successfully");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("isMsgSaved","true")
                .body(response);
    }
}



















