package com.enigma.simplebackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NotBlank(message = "Product Name is Mandatory")
    @NotEmpty(message = "Product Name is Required")
    @Size(min=3, max = 50,message = "Product Name size must be between 4 and 50")
    private String productName;

    @Min(value = 1,message = "Minimal stock is 1")
    private Integer price;

    @Min(value = 1,message = "Minimal stock is 1")
    private Integer stock;

    @CreatedDate
    @Column(updatable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss a", timezone = "Asia/Jakarta")
    private Date createdAt;

    @LastModifiedDate
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss a", timezone = "Asia/Jakarta")
    private Date updatedAt;

    public Product(String id, String productName, Integer price, Integer stock, Date createdAt, Date updatedAt) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.stock = stock;
            this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Product(){};

    @PrePersist
    private void createdDate() {
        if (createdAt == null) createdAt = new Date();
        if (createdAt == null) updatedAt = new Date();
    }

    @PreUpdate
    private void updatedDate() {
        updatedAt = new Date();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

        public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }
}
