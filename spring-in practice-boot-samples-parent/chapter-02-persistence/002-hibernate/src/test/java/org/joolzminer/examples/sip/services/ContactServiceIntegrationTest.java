package org.joolzminer.examples.sip.services;

import java.util.List;

import org.joolzminer.examples.sip.Application;
import org.joolzminer.examples.sip.domain.Contact;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/*
 * This is not a comprehensive test, only to shakedown some of the repository methods
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ContactServiceIntegrationTest {

	@Autowired
	private ContactService contactService;
	
	@Test
	@Sql({"/sql/clean-tables.sql", "/sql/insert-single-contact.sql"})
	public void testGetContactsWithSingleEntry() {
		List<Contact> contacts = contactService.getContacts();
		
		assertThat(contacts.size(), is(equalTo(1)));
		assertThat(contacts.get(0).getFirstName(), is(equalTo("Sergio")));
		assertThat(contacts.get(0).getMiddleInitial(), is(equalTo("F")));
		assertThat(contacts.get(0).getLastName(), is(equalTo("Gonzalez")));
		assertThat(contacts.get(0).getEmail(), is(equalTo("sergio.f.gonzalez@gmail.com")));
	}
	
	@Test
	@Sql({"/sql/clean-tables.sql", "/sql/insert-several-contacts.sql"})
	public void testGetContactsWithSeveralEntries() {
		List<Contact> contacts = contactService.getContacts();
		
		assertThat(contacts.size(), is(equalTo(3)));
	}
	
	@Test
	@Sql({"/sql/clean-tables.sql", "/sql/insert-single-contact.sql"})
	public void testGetContactsByEmailSingleEntry() {
		List<Contact> contacts = contactService.getContactsByEmail("sergio.f.gonzalez@gmail.com");
		
		assertThat(contacts.size(), is(equalTo(1)));
	}
	
	@Test
	@Sql({"/sql/clean-tables.sql", "/sql/insert-several-contacts.sql"})
	public void testGetContactsByEmailSeveralEntriesGetOne() {
		List<Contact> contacts = contactService.getContactsByEmail("sergio.f.gonzalez@gmail.com");
		
		assertThat(contacts.size(), is(equalTo(1)));
	}
	
	@Test
	@Sql({"/sql/clean-tables.sql", "/sql/insert-several-contacts-2.sql"})
	public void testGetContactsByEmailSeveralEntriesGetSeveral() {
		List<Contact> contacts = contactService.getContactsByEmail("family@gmail.com");
		
		assertThat(contacts.size(), is(equalTo(3)));
	}
	
	
	
	@Test
	@Sql("/sql/clean-tables.sql")
	public void testCreateContact() {
		Contact contact = new Contact("Sergio", "F", "Gonzalez", "sergio.f.gonzalez@gmail.com");
		contactService.create(contact);
		
		assertThat(contact.getId(), is(notNullValue(Long.class)));		
	}
		
}
