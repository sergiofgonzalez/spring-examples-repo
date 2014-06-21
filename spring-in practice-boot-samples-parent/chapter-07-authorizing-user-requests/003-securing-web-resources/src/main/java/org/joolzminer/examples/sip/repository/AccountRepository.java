package org.joolzminer.examples.sip.repository;

import org.joolzminer.examples.sip.domain.Account;
import org.springframework.data.repository.Repository;

public interface AccountRepository extends Repository<Account, Long>, AccountRepositoryCustom {
	Account save(Account account);
	Account findByUsername(String username);
	Account findByEmail(String email);
}
