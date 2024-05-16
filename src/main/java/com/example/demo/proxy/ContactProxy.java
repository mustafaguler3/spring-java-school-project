package com.example.demo.proxy;

import com.example.demo.consume.AppConfig;
import com.example.demo.domain.Contact;
import feign.Headers;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "contact",url = "http://localhost:8080/api/contact",configuration = AppConfig.class)
public interface ContactProxy {

    @RequestMapping(method = RequestMethod.GET,value = "/getMessagesByStatus")
    @Headers(value = "Content-Type: application/json")
    public List<Contact> getMessageByStatus(@RequestParam("status") String status);
}


















