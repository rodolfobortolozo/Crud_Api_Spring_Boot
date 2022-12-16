package com.crud.crud_api_rest.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crud.crud_api_rest.Models.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{

    @Query(value = "SELECT * FROM Contact c WHERE c.Name like %?%", nativeQuery = true)
    List<Contact> findName(String name);
    
}
