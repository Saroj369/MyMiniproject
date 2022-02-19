package com.google.phonebook.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.google.phonebook.model.Contact;
import com.google.phonebook.repository.ContactRepository;

import lombok.AllArgsConstructor;


@AllArgsConstructor
/*here we are using this annotation to creat constructor injection using line no 16 code
 * */
 
@Service
public class ContactServiceImpl implements ContactService {
	
	private ContactRepository contactRepo;
	
	@Override
	public boolean saveContact(Contact contact) {
		Contact savedContact = contactRepo.save(contact);
		return savedContact.getContactId() != null;		
	}

	@Override
	public List<Contact> getAllContacts() {
		return contactRepo.findAll();
	}

	@Override
	public Contact getContactById(Integer contactId) {
		Optional<Contact> contact = contactRepo.findById(contactId);
		if(contact.isPresent()) {
			return contact.get();
		} else {
			return null;
		}		
	}

	@Override
	public boolean deleteContact(Integer contactId) {
		try {
			contactRepo.deleteById(contactId);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
