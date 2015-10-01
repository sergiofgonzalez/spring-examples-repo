package org.joolzminer.examples.sip.repositories;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.joolzminer.examples.sip.domain.Contact;
import org.springframework.stereotype.Repository;

@Repository
public class ContactDaoJpaImpl implements ContactDao {
	
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> findByEmail(String email) {
		return entityManager
					.createNamedQuery("findContactsByEmail")
					.setParameter("email", "%" + email + "%")
					.getResultList();
	}

	@Override
	public void create(Contact contact) {
		entityManager.persist(contact);
	}

	@Override
	public Contact get(Serializable id) {
		return entityManager.find(Contact.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getAll() {
		return (List<Contact>) entityManager.createQuery("from Contact").getResultList();
	}

	@Override
	public void update(Contact contact) {
		entityManager.merge(contact);
	}

	@Override
	public void delete(Contact contact) {
		entityManager.remove(contact);
	}

	@Override
	public void deleteById(Serializable id) {
		entityManager.remove(get(id));
	}

	@Override
	public void deleteAll() {
		entityManager.createQuery("delete from Contact").executeUpdate();
	}

	@Override
	public long count() {
		return (long) entityManager.createQuery("select count(*) from Contact").getSingleResult(); 
	}

	@Override
	public boolean exists(Serializable id) {
		return get(id) != null;
	}
}
