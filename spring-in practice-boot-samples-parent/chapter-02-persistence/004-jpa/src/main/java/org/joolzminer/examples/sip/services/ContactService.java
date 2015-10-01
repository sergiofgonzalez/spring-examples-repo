package org.joolzminer.examples.sip.services;

import java.util.Arrays;
import java.util.List;

import org.joolzminer.examples.sip.domain.Contact;
import org.joolzminer.examples.sip.repositories.ContactDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ContactService {
	
	@Autowired
	private ContactDao contactDao;
	
	@Transactional(readOnly = false)
	public void create(Contact contact) {
		contactDao.create(contact);
	}
	
	public List<Contact> getContacts() {
		return contactDao.getAll();
	}
		
	public List<Contact> getContactsByEmail(String email) {
		return contactDao.findByEmail(email);
	}
	
	public Contact getContact(long id) {
		return contactDao.get(id);
	}
	
	@Transactional(readOnly = false)
	public void updateContact(Contact contact) {
		contactDao.update(contact);
	}
	
	@Transactional(readOnly = false)
	public void deleteContact(Contact contact) {
		contactDao.delete(contact);
	}
		
	
	@Transactional(readOnly = false)
	public void createSeveral(Contact... contacts) {
		Arrays.stream(contacts).forEach(contact -> create(contact));
	}
}
