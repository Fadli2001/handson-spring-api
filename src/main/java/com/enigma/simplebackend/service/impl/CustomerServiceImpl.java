package com.enigma.simplebackend.service.impl;

import com.enigma.simplebackend.entity.Customer;
import com.enigma.simplebackend.exception.NotFoundException;
import com.enigma.simplebackend.repository.CustomerRepository;
import com.enigma.simplebackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer create(Customer customer) {
        if (customer.getId() != null) getById(customer.getId());
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getById(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new NotFoundException("Customer is not Found"));
    }

    @Override
    public String deleteById(String id) {
        Customer customer = getById(id);
        customerRepository.delete(customer);
        return id;
    }

    @Override
    public Page<Customer> getAllWithPage(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

}
