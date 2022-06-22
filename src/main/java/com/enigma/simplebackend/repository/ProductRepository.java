package com.enigma.simplebackend.repository;

import com.enigma.simplebackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {
}
