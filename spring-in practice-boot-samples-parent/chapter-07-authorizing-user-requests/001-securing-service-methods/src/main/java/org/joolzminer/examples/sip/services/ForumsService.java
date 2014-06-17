package org.joolzminer.examples.sip.services;

import java.util.List;
import org.joolzminer.examples.sip.domain.Forum;
import org.joolzminer.examples.sip.domain.Message;
import org.joolzminer.examples.sip.repository.ForumsRepository;
import org.joolzminer.examples.sip.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

@Service
@Transactional(readOnly = true)
@PreAuthorize("denyAll")
public class ForumsService {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(ForumsService.class);
	
	@Autowired
	private ForumsRepository forumsRepository;

	@Autowired
	private MessageRepository messageRepository;

	@PreAuthorize("hasRole('PERM_READ_FORUMS')")
	public List<Forum> getForums() {
		return forumsRepository.findForumsWithStats();
	}

	@PreAuthorize("hasRole('PERM_READ_FORUMS')")
	public Forum getForum(long id, boolean initMessages) {
		Forum forum = null;
		if (initMessages) {
			forum = forumsRepository.findOneAndFetchMessagesEagerly(id);
			return forum;
		} else {
			return forumsRepository.findOne(id);
		}
	}

	@PreAuthorize("hasRole('PERM_READ_FORUMS')")
	public Message getMessage(long id) {
		return messageRepository.findOne(id);
	}

	@Transactional(readOnly = false)
	@PreAuthorize("hasRole('PERM_CREATE_MESSAGES')")
	public void createMessage(Message message, Errors errors) {
		validateMessage(message, errors);
		if (!errors.hasErrors()) {
			messageRepository.save(message);
		}
	}

	@Transactional(readOnly = false)
	@PreAuthorize("hasRole('PERM_UPDATE_MESSAGES')")
	public void updateMessageSubjectAndText(Message message) {
		Message savedMessage = messageRepository.findOne(message.getId());
		savedMessage.setSubject(message.getSubject());
		savedMessage.setText(message.getText());
	}
	
	@Transactional(readOnly = false)
	@PreAuthorize("hasRole('PERM_ADMIN_MESSAGES')")
	public void updateMessageVisibility(Message message) {
		Message savedMessage = messageRepository.findOne(message.getId());
		savedMessage.setVisible(message.isVisible());
	}
	
	@Transactional(readOnly = false)
	@PreAuthorize("hasRole('PERM_DELETE_MESSAGES')")
	public void deleteMessage(Message message) {
		messageRepository.delete(message);
	}
	
	
	private void validateMessage(Message message, Errors errors) {
		if (messageRepository.findByForumAndSubject(message.getForum(),
				message.getSubject()) != null) {
			errors.rejectValue("subject", "error.duplicate", new String[] {
					message.getForum().getName(), message.getSubject() }, null);
		}
	}
}
