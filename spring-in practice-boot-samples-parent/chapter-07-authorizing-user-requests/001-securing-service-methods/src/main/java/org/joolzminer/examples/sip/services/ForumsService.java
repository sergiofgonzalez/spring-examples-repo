package org.joolzminer.examples.sip.services;

import java.util.List;

import org.joolzminer.examples.sip.domain.Forum;
import org.joolzminer.examples.sip.repository.ForumsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@PreAuthorize("denyAll")
public class ForumsService {
	
	@Autowired
	private ForumsRepository forumsRepository;
	
	@PreAuthorize("hasRole('PERM_READ_FORUMS')")
	public List<Forum> getForums() {
		return forumsRepository.findForumsWithStats();
	}
}
