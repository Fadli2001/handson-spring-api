package com.enigma.simplebackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    @Column(nullable = false)
    private String id;
    private String fullName;
    private String email;
    private Date birthDate;
    private String address;
    private String phoneNumber;

    @CreatedDate
    @Column(updatable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss a",timezone = "Asia/Jakarta")
    private Date createdAt;

    @LastModifiedDate
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss a",timezone = "Asia/Jakarta")
    private Date updatedAt;

    @PrePersist
    private void createdDate(){
        if(getCreatedAt() == null) setCreatedAt(new Date());
        if(getUpdatedAt() == null) setUpdatedAt(new Date());
    }

    @PreUpdate
    private void updatedDate(){
        this.setUpdatedAt(new Date());
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Customer(String fullName, String email, Date birthDate, String address, String phoneNumber, Date createdAt, Date updatedAt) {
        this.fullName = fullName;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Customer(){};

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
