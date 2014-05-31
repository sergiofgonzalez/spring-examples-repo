package org.joolzminer.examples.sip.web.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.joolzminer.examples.sip.web.AccountForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/accounts")
public class AccountController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	
	private static final String VN_REG_FORM = "accounts/registrationForm";
	private static final String VN_REG_OK   = "redirect:/accounts/registrationOk";
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields(new String[] {
				"username",
				"password",
				"confirmPassword",
				"firstName",
				"lastName",
				"email",
				"marketingOk",
				"acceptTerms"
		});
	}
	
	@RequestMapping(value = "new", method = RequestMethod.GET)
	public String getRegistrationForm(Map<String, Object> model) {
		model.put("account", new AccountForm());
		return VN_REG_FORM;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String processRegistrationForm(@ModelAttribute("account") @Valid AccountForm accountForm, 
			BindingResult result) {
		LOGGER.info("Processing registration form for {}", accountForm);
		convertPasswordError(result);
		return (result.hasErrors() ? VN_REG_FORM : VN_REG_OK);
	}
	
	private void convertPasswordError(BindingResult result) {
		for (ObjectError error : result.getGlobalErrors()) {
			String msg = error.getDefaultMessage();
			if ("account.password.mismatch.message".equals(msg)) {
				if (!result.hasFieldErrors("password")) {
					result.rejectValue("password", "error.mismatch");
				}
			}
		}
	}
}


