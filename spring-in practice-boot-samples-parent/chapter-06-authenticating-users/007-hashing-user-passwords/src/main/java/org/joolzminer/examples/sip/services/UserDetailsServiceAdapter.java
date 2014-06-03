package org.joolzminer.examples.sip.services;

import org.joolzminer.examples.sip.domain.Account;
import org.joolzminer.examples.sip.domain.UserDetailsAdapter;
import org.joolzminer.examples.sip.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Transactional(readOnly = true)
public class UserDetailsServiceAdapter implements UserDetailsService {


	@Autowired
	private AccountRepository accountRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountRepository.findByUsername(username);
		if (account == null) {
			throw new UsernameNotFoundException("No such user '" + username + "'");
		} else if (account.getRoles().isEmpty()) {
			throw new UsernameNotFoundException("User '" + username + "' has no authorities");
		}
		
		UserDetailsAdapter user = new UserDetailsAdapter(account);
		user.setPassword(accountRepository.findPasswordByUsername(username));
		return user;
	}

}
