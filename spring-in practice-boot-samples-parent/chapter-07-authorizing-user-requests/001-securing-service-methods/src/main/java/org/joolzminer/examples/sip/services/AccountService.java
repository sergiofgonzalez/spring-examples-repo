package org.joolzminer.examples.sip.services;

import org.joolzminer.examples.sip.domain.Account;
import org.joolzminer.examples.sip.repository.AccountRepository;
import org.joolzminer.examples.sip.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

@Service
@Transactional(readOnly = true)
public class AccountService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Transactional(readOnly = false)
	public boolean registerAccount(Account account, String password, Errors errors) {
		LOGGER.debug("Attempting to Register account for {}", account.getUsername());
		validateEmail(account.getEmail(), errors);
		validateUsername(account.getUsername(), errors);
		boolean valid = !errors.hasErrors();
		if (valid) {
			account.add(roleRepository.findByName("user"));			
			accountRepository.save(account, password);
		}
		return valid;
	}
	
	private void validateUsername(String username, Errors errors) {
		if (accountRepository.findByUsername(username) != null) {
			errors.rejectValue("username", "error.duplicate", new String[] { username }, null);
		}
	}
	
	private void validateEmail(String email, Errors errors) {
		if (accountRepository.findByEmail(email) != null) {
			errors.rejectValue("email", "error.duplicate", new String[] { email }, null);
		}
	}
}
