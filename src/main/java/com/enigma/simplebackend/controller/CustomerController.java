package com.enigma.simplebackend.controller;


import com.enigma.simplebackend.entity.Customer;
import com.enigma.simplebackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.create(customer);
    }
}
