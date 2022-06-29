package com.enigma.simplebackend.entity;

import com.enigma.simplebackend.payload.response.photoprofile.PhotoProfileResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NotBlank(message = "Name is Mandatory")
    @NotEmpty(message = "Name is Required")
    @Size(min=4, max = 50,message = "Name size must be between 4 and 50")
    private String name;
    @Column(unique = true)
    @NotBlank(message = "Email is Mandatory")
    @NotEmpty(message = "Email is Required")
    @Email(message = "Email is Not Valid")
    @Size(min=11, max = 50, message = "Email size must be between 11 and 50")
    private String email;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDate;

    @NotBlank(message = "Address is Mandatory")
    @NotEmpty(message = "Address is Required")
    @Size(min=4, max = 50, message = "Address size must be between 6 and 50")
    private String address;

    @Size(min=9, max = 15, message = "Phone Number size must be between 9 and 15")
    private String phoneNumber;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPhotoProfile(PhotoProfile photoProfile) {
        this.photoProfile = photoProfile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    @OneToOne(targetEntity = PhotoProfile.class,fetch = FetchType.EAGER,cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JoinColumn(name = "file_id")
    @JsonIgnore
    private PhotoProfile photoProfile;

    private String photoUrl;

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @CreatedDate
    @Column(updatable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss a", timezone = "Asia/Jakarta")
    private Date createdAt;

    @LastModifiedDate
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss a", timezone = "Asia/Jakarta")
    private Date updatedAt;

    public Customer(String id, String name, String email, Date birthDate, String address, String phoneNumber, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Customer(String name, String email, Date birthDate, String address, String phoneNumber, PhotoProfile photoProfile) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.photoProfile = photoProfile;
    }

    public Customer(String name, String email, Date birthDate, String address, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Customer() {
    }

    public PhotoProfile getPhotoProfile() {
        return photoProfile;
    }

    @PrePersist
    private void createdDate() {
        if (createdAt == null) createdAt = new Date();
        if (createdAt == null) updatedAt = new Date();
    }

    @PreUpdate
    private void updatedDate() {
        updatedAt = new Date();
    }


    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getId() {
        return id;
    }
}
