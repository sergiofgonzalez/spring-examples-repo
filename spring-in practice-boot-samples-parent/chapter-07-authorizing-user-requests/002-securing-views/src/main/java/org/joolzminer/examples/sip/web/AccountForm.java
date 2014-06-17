package org.joolzminer.examples.sip.web;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.ScriptAssert;
import org.springframework.core.style.ToStringCreator;

@ScriptAssert(
		lang = "javascript",
		script = "_this.confirmPassword.equals(_this.password)",
		message = "account.password.mismatch.message")
public class AccountForm {
	@NotNull
	@Size(min = 1, max = 50)
	private String username;
	
	@NotNull
	@Size(min = 6, max = 50)
	private String password;
	
	@NotNull
	@Size(min = 6, max = 50)
	private String confirmPassword;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String firstName;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String lastName;
	
	@NotNull
	@Size(min = 6, max = 50)
	@Email
	private String email;
	
	private boolean marketingOk = true;
	
	@AssertTrue(message = "{account.acceptTerms.assertTrue.message}")
	private boolean acceptTerms = false;
	
	public AccountForm() {		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isMarketingOk() {
		return marketingOk;
	}

	public void setMarketingOk(boolean marketingOk) {
		this.marketingOk = marketingOk;
	}

	public boolean isAcceptTerms() {
		return acceptTerms;
	}

	public void setAcceptTerms(boolean acceptTerms) {
		this.acceptTerms = acceptTerms;
	}
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
			.append("username", username)
			.append("firstName", firstName)
			.append("lastName", lastName)
			.append("email", email)
			.append("marketingOk", marketingOk)
			.append("acceptTerms", acceptTerms)
			.toString();
	}
}
