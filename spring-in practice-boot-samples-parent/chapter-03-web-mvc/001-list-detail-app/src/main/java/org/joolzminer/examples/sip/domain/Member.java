package org.joolzminer.examples.sip.domain;

public class Member {
	private String firstName;
	private String lastName;
	
	protected Member() {		
	}
	
	public Member(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return lastName + ", " + firstName;
	}	
}
