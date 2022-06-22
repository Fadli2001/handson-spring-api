package com.enigma.simplebackend.controller;


import com.enigma.simplebackend.entity.Product;
import com.enigma.simplebackend.service.ProductService;
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
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<WebResponse<Product>> createProduct(@Valid @RequestBody Product product){
        return ResponseEntity.ok().body(new WebResponse<>("Successfully Create a new Product",productService.create(product)));
    }


    @GetMapping
    public ResponseEntity<WebResponse<PageResponse<Product>>> getAllProduct(
            @RequestParam(name = "page", defaultValue = "0") Integer pageOf,
            @RequestParam(name = "size", defaultValue = "10") Integer sizeOf,
            @RequestParam(name = "sortBy", defaultValue = "createdAt") String sortBy,
            @RequestParam(name = "direction", defaultValue = "DESC") String direction
    ) {
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(pageOf, sizeOf, sort);
        Page<Product> productPage = productService.getAllWithPage(pageable);

        PageResponse<Product> pageResponse = new PageResponse<>(
                productPage.getContent(),
                productPage.getTotalElements(),
                productPage.getTotalPages(),
                pageOf,
                sizeOf
        );
        return ResponseEntity.ok().body(new WebResponse<>("Success Get All Product", pageResponse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<WebResponse<Product>> getProductById(@PathVariable String id) {
        Product product = productService.getById(id);
        return ResponseEntity.ok().body(new WebResponse<>("Successfully Get Product", product));
    }

    @PutMapping
    public ResponseEntity<WebResponse<Product>> updateProductById(@Valid @RequestBody Product product) {
        return ResponseEntity.ok().body(new WebResponse<>("Successfully Update Product", productService.create(product)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<WebResponse<String>> deleteProductById(@PathVariable String id) {
        productService.deleteById(id);
        return ResponseEntity.ok().body(new WebResponse("SuccessFully Delete Product with id = " + id + "", null));
    }


}
