package org.joolzminer.examples.sip.repository;

import org.joolzminer.examples.sip.domain.Forum;
import org.springframework.data.repository.Repository;





public interface ForumsRepository extends Repository<Forum, Long>, ForumsRepositoryCustom {
}
