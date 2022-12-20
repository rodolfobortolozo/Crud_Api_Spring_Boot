package com.crud.crud_api_rest.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.crud.crud_api_rest.Models.Contact;
import com.crud.crud_api_rest.Repositories.ContactRepository;

@Service
public class ContactService {
    
    private ContactRepository contactRepository;

    ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    
    public ResponseEntity<List<Contact>> getAllContact() {
        try {
            List<Contact> contacts = new ArrayList<>();
            contacts = contactRepository.findAll();

            if (contacts.isEmpty() || contacts.size() == 0) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(contacts, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Contact> postContact(Contact contact) {
        try {
            contact.setId(null); //Para Garantir que esse método só vai inserir no Banco de Dados
            contactRepository.save(contact);
            return new ResponseEntity<>(contact, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Optional<Contact>> getContactById(Long id) {

        Optional<Contact> contact = contactRepository.findById(id);

        if (contact.isPresent()) {
            return new ResponseEntity<Optional<Contact>>(contact, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> deleteById(Long id) {

        return contactRepository.findById(id)
                .map(record -> {
                    contactRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());

    }

    public ResponseEntity<Contact> updateById(Long id, Contact newContact) {

        return contactRepository.findById(id)
                .map(contact -> {
                    contact.setName(newContact.getName());
                    contact.setEmail(newContact.getEmail());
                    contact.setPhone(newContact.getPhone());
                    Contact updateContact = contactRepository.save(contact);
                    return ResponseEntity.ok().body(updateContact);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Contact>> findByName(String name) {
        List<Contact> contacts = new ArrayList<>();
        contacts = contactRepository.findByNameContaining(name);

        if (!contacts.isEmpty()) {
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Contact>> findByNameParam(String name) {
        List<Contact> contacts = new ArrayList<>();
        contacts = contactRepository.findByNameContaining(name.toUpperCase());

        if (!contacts.isEmpty()) {
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
