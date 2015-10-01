package org.joolzminer.examples.sip.repositories;

import java.util.List;

import org.joolzminer.examples.sip.domain.Contact;

public class ContactDaoHibernateImpl extends AbstractDaoHibernateImpl<Contact> implements ContactDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> findByEmail(String email) {
		return getSession()
					.getNamedQuery("findContactsByEmail")
					.setString("email", "%" + email + "%")
					.list();
	}

}
