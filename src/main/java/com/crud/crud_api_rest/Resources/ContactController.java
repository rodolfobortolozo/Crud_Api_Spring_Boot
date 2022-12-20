package com.crud.crud_api_rest.Resources;


import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crud.crud_api_rest.Models.Contact;
import com.crud.crud_api_rest.Services.ContactService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/contacts/v1")

public class ContactController {

    private ContactService contactService;

    ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getAllContact() {
        return this.contactService.getAllContact();
    }

    @PostMapping
    public ResponseEntity<Contact> postContact(@Valid @RequestBody Contact contact) {
        return this.contactService.postContact(contact);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Contact>> getContactById(@PathVariable Long id) {
        return this.contactService.getContactById(id);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return this.contactService.deleteById(id);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Contact> updateById(@PathVariable Long id, @RequestBody Contact newContact) {
        return this.contactService.updateById(id, newContact);
    }

    @GetMapping(path = "/searchname/{name}")
    public ResponseEntity<List<Contact>> findByName(@PathVariable String name) {
       return this.contactService.findByName(name);
    }

    @GetMapping(path = "/searchname")
    public ResponseEntity<List<Contact>> findByNameParam(@RequestParam String name) {
        return this.contactService.findByNameParam(name);
    }

}
