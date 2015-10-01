package org.joolzminer.examples.sip.repositories;

import java.util.List;

import org.joolzminer.examples.sip.domain.Contact;


public interface ContactDao extends Dao<Contact> {
	List<Contact> findByEmail(String email);
}
