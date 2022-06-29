package com.enigma.simplebackend.service.impl;

import com.enigma.simplebackend.entity.Customer;
import com.enigma.simplebackend.entity.Product;
import com.enigma.simplebackend.exception.DuplicateException;
import com.enigma.simplebackend.exception.NotFoundException;
import com.enigma.simplebackend.repository.ProductRepository;
import com.enigma.simplebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.dao.TransientDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        if (product.getId() != null) getById(product.getId());
        return productRepository.save(product);
    }

    @Override
    public Page<Product> getAllWithPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product getById(String id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product is not Found"));
    }

    @Override
    public String deleteById(String id) {
        Product product = getById(id);
        productRepository.delete(product);
        return id;
    }
}
