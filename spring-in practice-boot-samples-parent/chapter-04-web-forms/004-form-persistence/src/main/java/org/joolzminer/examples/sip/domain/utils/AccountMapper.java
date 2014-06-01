package org.joolzminer.examples.sip.domain.utils;

import org.joolzminer.examples.sip.domain.Account;
import org.joolzminer.examples.sip.web.AccountForm;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
	public Account toAccount(AccountForm accountForm) {
		return new Account(accountForm.getUsername(),
				accountForm.getFirstName(), 
				accountForm.getLastName(),
				accountForm.getEmail(), 
				accountForm.isMarketingOk(),
				accountForm.isAcceptTerms(),
				true);
	}
}
