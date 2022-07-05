package com.enigma.simplebackend;

import com.enigma.simplebackend.entity.Customer;
import com.enigma.simplebackend.entity.Product;
import com.enigma.simplebackend.repository.CustomerRepository;
import com.enigma.simplebackend.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class SimpleBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleBackendApplication.class, args);
    }

    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;
    @Autowired
    public SimpleBackendApplication(CustomerRepository customerRepository,ProductRepository productRepository){
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }
    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();

    }

    @EventListener
    public void seederData(ApplicationReadyEvent event) {
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
    }


}
