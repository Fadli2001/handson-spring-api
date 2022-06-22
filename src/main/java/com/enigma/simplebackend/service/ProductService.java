package com.enigma.simplebackend.service;

import com.enigma.simplebackend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Product create(Product product);
    Page<Product> getAllWithPage(Pageable pageable);
    Product getById(String id);
    String deleteById(String id);
}
