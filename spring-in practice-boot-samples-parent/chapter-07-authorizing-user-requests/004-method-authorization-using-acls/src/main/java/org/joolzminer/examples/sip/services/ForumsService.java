package org.joolzminer.examples.sip.services;

import java.util.List;

import org.joolzminer.examples.sip.domain.Forum;
import org.joolzminer.examples.sip.domain.Message;
import org.joolzminer.examples.sip.repository.ForumsRepository;
import org.joolzminer.examples.sip.repository.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.Sid;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

@Service
@Transactional(readOnly = true)
@PreAuthorize("denyAll")
public class ForumsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ForumsService.class);
	
	@Autowired
	private ForumsRepository forumsRepository;

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private MutableAclService mutableAclService;

	@PreAuthorize("hasRole('PERM_READ_FORUMS')")
	@PostFilter("hasPermission(filterObject, read)")
	public List<Forum> getForums() {
		return forumsRepository.findForumsWithStats();
	}

	@PreAuthorize("hasRole('PERM_READ_FORUMS')")
	@PostAuthorize("hasPermission(returnObject, read)")
	public Forum getForum(long id, boolean initMessages) {
		Forum forum = null;
		if (initMessages) {
			forum = forumsRepository.findOneAndFetchMessagesEagerly(id);
			// No messages in this forum?
			if (forum == null) {
				forum = forumsRepository.findOne(id);
			}
			return forum;
		} else {
			return forumsRepository.findOne(id);
		}
	}

	@PreAuthorize("hasRole('PERM_READ_FORUMS')")
	@PostAuthorize("(hasPermission(returnObject, read) and returnObject.visible) or hasPermission(returnObject, admin)")
	public Message getMessage(long id) {
		return messageRepository.findOne(id);
	}

	@Transactional(readOnly = false)
	@PreAuthorize("hasRole('PERM_CREATE_MESSAGES')")
	public void createMessage(Message message, Errors errors) {
		validateMessage(message, errors);
		if (!errors.hasErrors()) {
			messageRepository.save(message);
			createAcl(message);
		}
	}

	@Transactional(readOnly = false)
	@PreAuthorize("(hasPermission(#message, write) and #message.visible) or hasPermission(#message, admin)")
	public void updateMessageSubjectAndText(Message message) {
		Message savedMessage = messageRepository.findOne(message.getId());
		savedMessage.setSubject(message.getSubject());
		savedMessage.setText(message.getText());
		updateAcl(savedMessage);
	}
	
	@Transactional(readOnly = false)
	@PreAuthorize("hasPermission(#message, admin)")
	public void updateMessageVisibility(Message message) {
		Message savedMessage = messageRepository.findOne(message.getId());
		savedMessage.setVisible(message.isVisible());
		updateAcl(savedMessage);
	}
	
	@Transactional(readOnly = false)
	@PreAuthorize("hasPermission(#message, delete)")
	public void deleteMessage(Message message) {
		messageRepository.delete(message);
		deleteAcl(message);
	}
	
	
	private void validateMessage(Message message, Errors errors) {
		if (messageRepository.findByForumAndSubject(message.getForum(),
				message.getSubject()) != null) {
			errors.rejectValue("subject", "error.duplicate", new String[] {
					message.getForum().getName(), message.getSubject() }, null);
		}
	}
	
	private void createAcl(Message message) {
		ObjectIdentity parentOid = new ObjectIdentityImpl(Forum.class, message.getForum().getId());
		LOGGER.debug("Loading ACL for forum OID: {}", parentOid);
		
		MutableAcl parentAcl = (MutableAcl) mutableAclService.readAclById(parentOid);
		LOGGER.debug("Loaded forum ACL: {}", parentAcl);
		
		ObjectIdentity oid = new ObjectIdentityImpl(Message.class, message.getId());
		LOGGER.debug("Creating ACL for message OID: {}", oid);
		
		MutableAcl acl = mutableAclService.createAcl(oid);
		
		Sid author = new PrincipalSid(message.getAuthor().getUsername());
		LOGGER.debug("Setting message owner: {}", author);
		
		acl.setParent(parentAcl);
		if (message.isVisible()) {
			acl.insertAce(0, BasePermission.WRITE, author, true);
		}
		
		acl.setOwner(author);
		mutableAclService.updateAcl(acl);
	}
	
	private void deleteAcl(Message message) {		
		ObjectIdentity oid = new ObjectIdentityImpl(Message.class, message.getId());
		LOGGER.debug("Deleting message OID: {}", oid);
		mutableAclService.deleteAcl(oid, true);
	}
	
	private void updateAcl(Message message) {
		deleteAcl(message);
		createAcl(message);
	}
}
