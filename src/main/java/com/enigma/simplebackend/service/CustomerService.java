package com.enigma.simplebackend.service;

import com.enigma.simplebackend.entity.Customer;
import com.enigma.simplebackend.exception.NotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerService {

    Customer create(String customer,MultipartFile multipartFile);

    List<Customer> findAll();

    Customer getById(String id) throws NotFoundException;

    String deleteById(String id);

    Page<Customer> getAllWithPage(Pageable pageable);

    Customer update(String createRequest, MultipartFile multipartFile) throws JsonProcessingException;

}
