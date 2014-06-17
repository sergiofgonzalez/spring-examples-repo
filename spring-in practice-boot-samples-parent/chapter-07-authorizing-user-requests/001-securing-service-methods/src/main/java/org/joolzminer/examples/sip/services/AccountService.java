package org.joolzminer.examples.sip.services;

import org.joolzminer.examples.sip.domain.Account;
import org.joolzminer.examples.sip.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@PreAuthorize("denyAll")
public class AccountService {
	
	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	private AccountRepository accountRepository;

	@PreAuthorize("hasRole('PERM_READ_ACCOUNTS')")
	public Account getAccountByUsername(String username) {
		return accountRepository.findByUsername(username);
	}
	
}
