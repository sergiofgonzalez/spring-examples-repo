package org.joolzminer.examples.sip.repository;

import java.util.List;

import org.joolzminer.examples.sip.domain.Forum;

public interface ForumsRepositoryCustom {
	List<Forum> findForumsWithStats();
}
