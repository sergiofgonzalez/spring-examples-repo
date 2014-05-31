package org.joolzminer.examples.sip.web.controllers;

import java.util.Map;

import org.joolzminer.examples.sip.web.AccountForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/accounts")
public class AccountController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String getRegistrationForm(Map<String, Object> model) {
		model.put("account", new AccountForm());
		return "accounts/registrationForm";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processRegistrationForm(AccountForm accountForm) {
		LOGGER.info("Processing registration form for {}", accountForm);
		return "redirect:/accounts/registrationOk";
	}
}


