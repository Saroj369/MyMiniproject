package com.google.phonebook.service;

import java.util.List;

import com.google.phonebook.model.Contact;

public interface ContactService {

	public boolean saveContact(Contact contact);
	public List<Contact> getAllContacts();
	public Contact getContactById(Integer contactId);
	public boolean deleteContact(Integer contactId);
}
