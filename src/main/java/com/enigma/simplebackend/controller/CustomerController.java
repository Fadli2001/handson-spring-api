package com.enigma.simplebackend.controller;


import com.enigma.simplebackend.entity.Customer;
import com.enigma.simplebackend.service.CustomerService;
import com.enigma.simplebackend.util.PageResponse;
import com.enigma.simplebackend.util.ValidationUtil;
import com.enigma.simplebackend.util.WebResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.Validation;

@RestController
@RequestMapping("customers")
//@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    ValidationUtil validationUtil;

    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE,
            },
            produces = "application/json"
    )
    public ResponseEntity<WebResponse<Customer>> createCustomer(
            @RequestPart(name = "file",required = false) MultipartFile multipartFile,
            @RequestPart(name = "body",required = true) String customer
    ) {
        return ResponseEntity.ok().body(new WebResponse<>("Successfully Create a new customers", customerService.create(customer,multipartFile)));
    }

    @GetMapping
    public ResponseEntity<WebResponse<PageResponse<Customer>>> getAllCustomer(
            @RequestParam(name = "page", defaultValue = "0") Integer pageOf,
            @RequestParam(name = "size", defaultValue = "10") Integer sizeOf,
            @RequestParam(name = "sortBy", defaultValue = "createdAt") String sortBy,
            @RequestParam(name = "direction", defaultValue = "DESC") String direction
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(pageOf, sizeOf, sort);
        Page<Customer> customerPage = customerService.getAllWithPage(pageable);

        PageResponse<Customer> pageResponse = new PageResponse<>(
                customerPage.getContent(),
                customerPage.getTotalElements(),
                customerPage.getTotalPages(),
                pageOf,
                sizeOf
        );
        return ResponseEntity.ok().body(new WebResponse<>("Success Get All Customers", pageResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WebResponse<Customer>> getCustomerById(@PathVariable String id) {
        Customer customer = customerService.getById(id);
        return ResponseEntity.ok().body(new WebResponse<>("Successfully Get Customer", customer));
    }

    @PutMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.MULTIPART_FORM_DATA_VALUE,
            },
            produces = "application/json"
    )
    public ResponseEntity<WebResponse<Customer>> updateCustomerById(

            @RequestPart(name = "file",required = false) MultipartFile multipartFile,
            @RequestPart(name = "body",required = true) String requestCustomer
    ) throws JsonProcessingException {
        return ResponseEntity.ok().body(new WebResponse<>("Successfully Update Customer", customerService.create(requestCustomer,multipartFile)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WebResponse<String>> deleteCustomerById(@PathVariable String id) {
        customerService.deleteById(id);
        return ResponseEntity.ok().body(new WebResponse("SuccessFully Delete Customer with id = " + id + "", null));
    }


}
