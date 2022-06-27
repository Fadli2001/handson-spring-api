package com.enigma.simplebackend.repository;

import com.enigma.simplebackend.entity.PhotoProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoProfileRepository extends JpaRepository<PhotoProfile,String> {
}
