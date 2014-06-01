package org.joolzminer.examples.sip.domain;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.core.style.ToStringCreator;

@Entity
public class Account extends AbstractEntity {
	@NotNull
	@Size(min = 1, max = 50)
	private String username;
	
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
	
	private boolean marketingOk;
	
	@AssertTrue(message = "{account.acceptTerms.assertTrue.message}")
	private boolean acceptTerms = false;
	
	private boolean enabled = true;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;
	
	protected Account() {		
	}
	
	public Account(String username, String firstName, String lastName, String email, boolean marketingOk, boolean acceptTerms, boolean enabled) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.marketingOk = marketingOk;
		this.acceptTerms = acceptTerms;
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	public boolean isMarketingOk() {
		return marketingOk;
	}

	public boolean isAcceptTerms() {
		return acceptTerms;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public Date getDateCreated() {
		return dateCreated == null ? null : (Date) dateCreated.clone();
	}

	@Override
	public String toString() {
		return new ToStringCreator(this)
				.append("id", getId())
				.append("username", username)
				.append("firstName", firstName)
				.append("lastName", lastName)
				.append("email", email)
				.append("marketingOk", marketingOk)
				.append("acceptTerms", acceptTerms)
				.append("enabled", enabled)
				.append("dateCreated", dateCreated)
				.toString();
	}
	
	
}
