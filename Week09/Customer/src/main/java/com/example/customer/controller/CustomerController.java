package com.example.customer.controller;


import com.example.customer.service.CustomerService;
import io.github.bucket4j.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class CustomerController {
    @Autowired
    private CustomerService service;

    @Autowired
    private Bucket bucket;

    @GetMapping("/hello")
    public String hello() {
        return service.sayHello();
    }

    @GetMapping("/rate")
    public String rateLimiter() {
        if(bucket.tryConsume(1)) {
            return service.sayHello();
        }else {
            return "Rate limit request";
        }
    }
}
