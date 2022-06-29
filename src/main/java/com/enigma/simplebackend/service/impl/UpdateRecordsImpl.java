package com.enigma.simplebackend.service.impl;

import com.enigma.simplebackend.entity.Customer;
import com.enigma.simplebackend.entity.Product;
import com.enigma.simplebackend.repository.CustomerRepository;
import com.enigma.simplebackend.repository.ProductRepository;
import com.enigma.simplebackend.service.UpdateRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@Service
public class UpdateRecordsImpl implements UpdateRecords {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public String setUp() {

        customerRepository.deleteAll();
        productRepository.deleteAll();

        List<Customer> customers = Arrays.asList(
                new Customer("Rendi", "rendi@gmail.com", Date.valueOf("2001-09-11"), "Bandung", "085129819329"),
                new Customer("Nayla Kasantri", "nayla@gmail.com", Date.valueOf("1998-01-22"), "Sukabumi", "089978182901"),
                new Customer("Rahman Fauzan", "fauzan@gmail.com", Date.valueOf("2003-05-01"), "Jakarta", "085774567289")
        );

        List<Product> products = Arrays.asList(
                new Product("Nabati",3000,50),
                new Product("Kue Coklat",5000,60),
                new Product("krupuk kulit",10000,100)
        );

        customerRepository.saveAll(customers);
        productRepository.saveAll(products);

        return "Success Delete All Records";
    }
}
