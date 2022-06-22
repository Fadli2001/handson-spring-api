package com.enigma.simplebackend.service;

import com.enigma.simplebackend.entity.Customer;
import com.enigma.simplebackend.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    Customer create(Customer customer);

    List<Customer> findAll();

    Customer getById(String id) throws NotFoundException;

    String deleteById(String id);

    Page<Customer> getAllWithPage(Pageable pageable);

}
