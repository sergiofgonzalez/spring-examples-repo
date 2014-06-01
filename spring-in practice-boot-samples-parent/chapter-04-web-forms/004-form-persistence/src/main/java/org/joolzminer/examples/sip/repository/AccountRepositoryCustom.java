package org.joolzminer.examples.sip.repository;

import org.joolzminer.examples.sip.domain.Account;

public interface AccountRepositoryCustom {
	Account save(Account account, String password);
}
