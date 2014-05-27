package org.joolzminer.examples.boot.web.rest.dto;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.springframework.core.style.ToStringCreator;



@SuppressWarnings("serial")
public class UserDTO implements Serializable {
	private String username;
	private List<String> roles;
	
	protected UserDTO() {		
	}
	
	public UserDTO(String username, List<String> roles) {
		this.username = username;
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public List<String> getRoles() {
		return roles != null ? Collections.unmodifiableList(roles) : null;
	}

	@Override
	public String toString() {
		return new ToStringCreator(this)
			.append("username", username)
			.append("roles", roles)
			.toString();
	}	
}
