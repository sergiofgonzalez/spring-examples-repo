package org.joolzminer.examples.sip.repositories;

import java.io.Serializable;
import java.util.List;

import org.joolzminer.examples.sip.domain.Contact;


public interface ContactDao {
	List<Contact> findByEmail(String email);
	void create(Contact contact);
	Contact get(Serializable id);
	List<Contact> getAll();	
	void update(Contact contact);
	void delete(Contact contact);
	void deleteById(Serializable id);
	void deleteAll();
	long count();
	boolean exists(Serializable id);
}
