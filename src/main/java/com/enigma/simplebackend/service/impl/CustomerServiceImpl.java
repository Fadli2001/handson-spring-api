package com.enigma.simplebackend.service.impl;

import com.enigma.simplebackend.entity.Customer;
import com.enigma.simplebackend.repository.CustomerRepository;
import com.enigma.simplebackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }
}
