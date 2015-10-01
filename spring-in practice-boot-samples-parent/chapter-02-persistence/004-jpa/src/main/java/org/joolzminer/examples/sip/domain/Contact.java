package org.joolzminer.examples.sip.domain;

import java.beans.Transient;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.core.style.ToStringCreator;

@Entity
@Table(name = "Contact", uniqueConstraints = @UniqueConstraint(columnNames = { "last_name", "first_name", "id" }))
@NamedQuery(name = "findContactsByEmail", query = "from Contact where email like :email")
public class Contact {
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Length(min = 1, max = 40)
	@Column(name = "last_name")
	private String lastName;
	
	@NotNull
	@Length(min = 1, max = 40)
	@Column(name = "first_name")
	private String firstName;
	
	@Length(max = 1)
	@Column(name = "mi")
	private String middleInitial;
	
	@Email
	private String email;
	
	protected Contact() {		
	}
	
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

	@Transient
	public String getFullName() {
		String fullName = lastName + ", " + firstName;

		boolean significantMiddleInitial = !(middleInitial != null && "".equals(middleInitial.trim())); 
		
		if (significantMiddleInitial) {
			fullName = fullName + " " + middleInitial + "."; 
		}

		return fullName;
	}
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
			.append("id", id)
			.append("firstName", firstName)
			.append("lastName", lastName)
			.append("middleInitial", middleInitial)
			.append("email", email)
			.append("fullName", getFullName())
			.toString();
	}		
}
