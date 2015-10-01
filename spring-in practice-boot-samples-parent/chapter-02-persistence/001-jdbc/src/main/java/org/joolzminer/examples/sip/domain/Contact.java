package org.joolzminer.examples.sip.domain;

import org.springframework.core.style.ToStringCreator;

public class Contact {
	private Long id;
	private String lastName;
	private String firstName;
	private String middleInitial;
	private String email;
	
	public Contact(String firstName, String middleInitial, String lastName, String email) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.middleInitial = middleInitial;
		this.email = email;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleInitial() {
		return middleInitial;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return new ToStringCreator(this)
			.append("id", id)
			.append("firstName", firstName)
			.append("middleInitial", middleInitial)
			.append("lastName", lastName)
			.append("email", email)
			.toString();
	}		
}
