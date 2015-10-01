package org.joolzminer.examples.sip.repositories;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joolzminer.examples.sip.domain.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public Contact createContact(Contact contact) {
		getSession().save(contact);
		return contact;
	}
	
	@SuppressWarnings("unchecked")
	public List<Contact> getContacts() {
		return getSession().createQuery("from Contact").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Contact> getContactsByEmail(String email) {
		return getSession()
					.getNamedQuery("findContactsByEmail")
					.setString("email", "%" + email + "%")
					.list();
	}
	
	public Contact getContact(long id) {
		return (Contact) getSession().get(Contact.class, id);
	}

	public void updateContact(Contact contact) {
		getSession().update(contact);
	}
	
	public void delete(long id) {
		getSession().delete(getContact(id));
	}
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
}
