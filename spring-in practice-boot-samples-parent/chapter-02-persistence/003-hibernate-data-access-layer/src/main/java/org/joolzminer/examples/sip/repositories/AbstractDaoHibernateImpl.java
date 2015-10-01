package org.joolzminer.examples.sip.repositories;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

public class AbstractDaoHibernateImpl<T> implements Dao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	
	private Class<T> domainClass;
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void create(T t) {
		Method method = ReflectionUtils.findMethod(getDomainClass(), "setDateCreated", new Class[] { Date.class });
		if (method != null) {
			try {
				method.invoke(t, new Date());
			} catch (Exception e) {
				// we did our best, swallow exception
			}
		}
		
		getSession().save(t);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(Serializable id) {
		return (T) getSession().get(getDomainClass(), id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T load(Serializable id) {
		return (T) getSession().load(getDomainClass(), id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		return getSession().createQuery("from " + getDomainClass()).list();
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}

	@Override
	public void delete(T t) {
		getSession().delete(t);
	}

	@Override
	public void deleteById(Serializable id) {
		delete(load(id));
	}

	@Override
	public void deleteAll() {
		getSession().createQuery("delete from " + getDomainClass()).executeUpdate();
	}

	@Override
	public long count() {
		return (long) getSession()
						.createQuery("SELECT COUNT(*) FROM " + getDomainClassName())
						.uniqueResult();
	}

	@Override
	public boolean exists(Serializable id) {
		return get(id) != null;
	}

	
	@SuppressWarnings("unchecked")
	private Class<T> getDomainClass() {
		if (domainClass == null) {
			ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
			this.domainClass = (Class<T>) thisType.getActualTypeArguments()[0];
		}
		return domainClass;
	}
	
	private String getDomainClassName() {
		return getDomainClass().getName();
	}
}
