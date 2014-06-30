package org.joolzminer.examples.sip.repository;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.joolzminer.examples.sip.domain.Forum;
import org.joolzminer.examples.sip.domain.utils.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ForumsRepositoryImpl implements ForumsRepositoryCustom {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(ForumsRepositoryImpl.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Forum> findForumsWithStats() {
		Query query = entityManager.createNamedQuery("findForumsWithStats");
		List<Object[]> results = query.getResultList();
		
		List<Forum> forums = new ArrayList<>();
		for (Object[] result : results) {
			Forum forum = (Forum) result[0];
			forum.setCalculateMessageStats(false);
			forum.setNumVisibleMessages(NumberUtils.asInt((Long)result[1]));
			forum.setLastVisibleMessageDate((Date) result[2]);
			forums.add(forum);
		}
		
		return forums;
	}
	
}
