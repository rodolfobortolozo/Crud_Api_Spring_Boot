package com.crud.crud_api_rest.Controllers;

import java.util.ArrayList;
import java.util.List;
// import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
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
import com.crud.crud_api_rest.Repositories.ContactRepository;

@RestController
@RequestMapping(value = "/contacts/v1")

public class ContactController {

    private ContactRepository contactRepository;

    ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getContact() {
        List<Contact> contacts = new ArrayList<>();
        contacts = contactRepository.findAll();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Contact> postContact(@RequestBody Contact contact) {
        contactRepository.save(contact);
        return new ResponseEntity<>(contact, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Contact>> getContactById(@PathVariable Long id) {

        Optional<Contact> contact = contactRepository.findById(id);

        if (contact.isPresent()) {
            return new ResponseEntity<Optional<Contact>>(contact, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {

        if (contactRepository.findById(id).isPresent()) {
            contactRepository.deleteById(id);
            return new ResponseEntity<String>("Registro Deletado", HttpStatus.OK);
        }
        return new ResponseEntity<String>("Registro Não Encontrado", HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Contact> updateById(@PathVariable Long id, @RequestBody Contact newContact) {

        return contactRepository.findById(id)
                .map(contact -> {
                    contact.setName(newContact.getName());
                    contact.setEmail(newContact.getEmail());
                    contact.setPhone(newContact.getPhone());
                    Contact updateContact = contactRepository.save(contact);
                    return ResponseEntity.ok().body(updateContact);
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/searchname/{name}")
    public ResponseEntity<List<Contact>> findByName(@PathVariable String name) {
        List<Contact> contacts = new ArrayList<>();
        contacts = contactRepository.findName(name.toUpperCase());

        if (!contacts.isEmpty()) {
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(path = "/searchname")
    public ResponseEntity<List<Contact>> findByNameParam(@RequestParam String name) {
        List<Contact> contacts = new ArrayList<>();
        contacts = contactRepository.findName(name.toUpperCase());

        if (!contacts.isEmpty()) {
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
