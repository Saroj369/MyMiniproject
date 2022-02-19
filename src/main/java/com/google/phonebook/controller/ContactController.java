package com.google.phonebook.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.phonebook.exception.ContactNotFoundException;
import com.google.phonebook.model.Contact;
import com.google.phonebook.service.ContactService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin; 
@AllArgsConstructor
@RestController
@RequestMapping("/api/contacts")
@Slf4j
@CrossOrigin
public class ContactController {

	private ContactService contactService;
	
	// using ResponsEntity we can send response to client with status code.
	@PostMapping
	public ResponseEntity<String> saveContact(@RequestBody Contact contact){
		boolean saveContact = contactService.saveContact(contact);
		if(saveContact) {
			log.info("contact saved successfully");
			return new ResponseEntity<>("saved success", HttpStatus.CREATED);
		} else {
			log.error("contact not saved");
			return new ResponseEntity<>("contact not saved", HttpStatus.BAD_REQUEST);
		}		
	}
	
	@GetMapping
	public ResponseEntity<List<Contact>> getAllContacts(){
		/*List<Contact> allContacts = contactService.getAllContacts();
		return new ResponseEntity<>(allContacts, HttpStatus.OK);*/
		
		return new ResponseEntity<>(contactService.getAllContacts(), HttpStatus.OK);
	}
	
	@GetMapping("/{contactId}")
	public ResponseEntity<Contact> getContactById(@PathVariable Integer contactId){
		Contact contact = null;
		contact = contactService.getContactById(contactId);
		if(contact == null) {
			throw new ContactNotFoundException("Contact Not Found");
		}
		return new ResponseEntity<>(contact, HttpStatus.OK);		
	}
	
	@PutMapping
	public ResponseEntity<String> updateContact(@RequestBody Contact contact){
		boolean saveContact = contactService.saveContact(contact);
		if(saveContact) {
			log.info("contact saved successfully");
			return new ResponseEntity<>("contact updated sccess", HttpStatus.CREATED);
		} else {
			log.error("contact not saved");
			return new ResponseEntity<>("contact not updated", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{contactId}")
	public ResponseEntity<String> deleteContact(@PathVariable Integer contactId){
		boolean contactDeleted = contactService.deleteContact(contactId);
		if(contactDeleted) {
			return new ResponseEntity<>("Contact deleted successfully", HttpStatus.OK);
		}
		return new ResponseEntity<>("Could not delete", HttpStatus.BAD_REQUEST);
	}
}
