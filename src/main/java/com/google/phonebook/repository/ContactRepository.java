package com.google.phonebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.google.phonebook.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

}
