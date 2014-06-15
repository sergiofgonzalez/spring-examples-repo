package org.joolzminer.examples.sip.repository;

import org.joolzminer.examples.sip.domain.Forum;
import org.joolzminer.examples.sip.domain.Message;
import org.springframework.data.repository.Repository;

public interface MessageRepository extends Repository<Message, Long>{
	Message findOne(Long id);
	Message save(Message message);
	Message findByForumAndSubject(Forum forum, String messageSubject);
}
