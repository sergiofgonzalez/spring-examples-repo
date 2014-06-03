package org.joolzminer.examples.sip.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.core.style.ToStringCreator;

@Entity
public class Role extends AbstractEntity {
	@Column(length = 50, nullable = false, unique = true)
	private String name;
	
	protected Role() {		
	}
	
	public Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return new ToStringCreator(this)
					.append("name", name)
					.toString();
	}	
}
