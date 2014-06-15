package org.joolzminer.examples.sip.repository;

import org.joolzminer.examples.sip.domain.Forum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;





public interface ForumsRepository extends Repository<Forum, Long>, ForumsRepositoryCustom {
	Forum findOne(Long id);
	
	@Query("SELECT f from Forum f JOIN FETCH f.messages WHERE f.id = :id")
	Forum findOneAndFetchMessagesEagerly(@Param("id") Long id);
}
