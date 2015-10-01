package org.joolzminer.examples.sip.repositories;

import java.util.List;

import org.joolzminer.examples.sip.domain.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
	List<Contact> findByEmailLike(String email);
}
