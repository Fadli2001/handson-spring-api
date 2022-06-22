package com.enigma.simplebackend.controller;


import com.enigma.simplebackend.entity.Customer;
import com.enigma.simplebackend.service.CustomerService;
import com.enigma.simplebackend.util.PageResponse;
import com.enigma.simplebackend.util.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<WebResponse<Customer>> createCustomer(@Valid @RequestBody Customer customer) {
        return ResponseEntity.ok().body(new WebResponse<>("Successfully Create a new customers", customerService.create(customer)));
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

    @PutMapping
    public ResponseEntity<WebResponse<Customer>> updateCustomerById(@RequestBody Customer customer) {
        return ResponseEntity.ok().body(new WebResponse<>("Successfully Update Customer", customerService.create(customer)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WebResponse<String>> deleteCustomerById(@PathVariable String id) {
        customerService.deleteById(id);
        return ResponseEntity.ok().body(new WebResponse("SuccessFully Delete Customer with id = " + id + "", null));
    }


}
