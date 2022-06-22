package com.enigma.simplebackend.controller;

import com.enigma.simplebackend.entity.Customer;
import com.enigma.simplebackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customer")
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.create(customer);
    }

    @GetMapping("/hello")
    public String sayHello(){
        return "Hello World!";
    }
}
