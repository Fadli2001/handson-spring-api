package com.enigma.simplebackend.service.impl;

import com.enigma.simplebackend.entity.Customer;
import com.enigma.simplebackend.entity.PhotoProfile;
import com.enigma.simplebackend.exception.DuplicateException;
import com.enigma.simplebackend.exception.NotAcceptableException;
import com.enigma.simplebackend.exception.NotFoundException;
import com.enigma.simplebackend.payload.response.photoprofile.PhotoProfileResponse;
import com.enigma.simplebackend.repository.CustomerRepository;
import com.enigma.simplebackend.service.CustomerService;
import com.enigma.simplebackend.service.PhotoProfileService;
import com.enigma.simplebackend.util.ValidationUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.MissingServletRequestPartException;


import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PhotoProfileService photoProfileService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ValidationUtil validationUtil;

    @Override
    public  Customer create(String customer,MultipartFile multipartFile) {
        try{
            Customer customerCreate = objectMapper.readValue(customer,Customer.class);
            validationUtil.validate(customerCreate);
            if(multipartFile != null) storeFile(customerCreate,multipartFile);
            if(customerCreate.getId() != null) getById(customerCreate.getId());

            return customerRepository.save(customerCreate);
        }catch (NonTransientDataAccessException | TransientDataAccessException | JsonProcessingException e){
            throw new DuplicateException("Email already used");
        }
    }

    private void storeFile(Customer customer,MultipartFile multipartFile){
        PhotoProfileResponse photoProfileResponse = photoProfileService.create(multipartFile);
        PhotoProfile photoProfile = photoProfileService.getById(photoProfileResponse.getId());
        customer.setPhotoProfile(photoProfile);
        customer.setPhotoUrl(photoProfileResponse.getUrl());
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
