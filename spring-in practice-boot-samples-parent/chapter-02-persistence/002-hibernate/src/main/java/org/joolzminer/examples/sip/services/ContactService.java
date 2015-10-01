package org.joolzminer.examples.sip.services;

import java.util.List;

import org.joolzminer.examples.sip.domain.Contact;
import org.joolzminer.examples.sip.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ContactService {
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Transactional(readOnly = false)
	public void create(Contact contact) {
		contactRepository.createContact(contact);
	}
	
	public List<Contact> getContacts() {
		return contactRepository.getContacts();
	}
		
	public List<Contact> getContactsByEmail(String email) {
		return contactRepository.getContactsByEmail(email);
	}
	

	public Contact getContact(long id) {
		return contactRepository.getContact(id);
	}
	
	@Transactional(readOnly = false)
	public void updateContact(Contact contact) {
		contactRepository.updateContact(contact);
	}
	
	@Transactional(readOnly = false)
	public void deleteContact(Contact contact) {
		contactRepository.delete(contact.getId());
	}
		
}
