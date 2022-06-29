package com.enigma.simplebackend.repository;

import com.enigma.simplebackend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
    @Query("DELETE FROM Customer c WHERE c.name = ?1")
    void deleteByName(String name);
//    @Query( "DELETE FROM Customer c WHERE c.id IN (SELECT id FROM Customer ORDER BY CREATED_AT LIMIT 1)")
//    void deleteLifo();
}
