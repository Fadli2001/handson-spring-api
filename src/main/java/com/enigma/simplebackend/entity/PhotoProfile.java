package com.enigma.simplebackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PhotoProfile {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    @Column(name = "file_id",nullable = false)
    private String fileId;
    private String name;
    private String type;

    @Lob
    private byte[] data;

    @CreatedDate
    @Column(updatable = false)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss a", timezone = "Asia/Jakarta")
    private Date createdAt;

    @LastModifiedDate
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss a", timezone = "Asia/Jakarta")
    private Date updatedAt;

    public PhotoProfile(String fileId, String name, String type, byte[] data, Date createdAt, Date updatedAt) {
        this.fileId = fileId;
        this.name = name;
        this.type = type;
        this.data = data;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public PhotoProfile(){};

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
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
}