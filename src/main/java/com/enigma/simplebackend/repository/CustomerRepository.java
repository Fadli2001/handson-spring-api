package com.enigma.simplebackend.repository;

import com.enigma.simplebackend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,String> {
}
