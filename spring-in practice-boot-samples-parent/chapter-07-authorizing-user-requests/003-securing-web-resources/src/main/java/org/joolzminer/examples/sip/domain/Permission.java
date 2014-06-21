package org.joolzminer.examples.sip.domain;

import javax.persistence.Entity;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
@Entity
public class Permission extends AbstractEntity implements GrantedAuthority {

	private String name;
		
	public String getName() {
		return name;
	}

	@Override
	public String getAuthority() {
		return name;
	}

	@Override
	public int hashCode() {
		return getAuthority().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof GrantedAuthority) {
			GrantedAuthority gaObj = (GrantedAuthority)obj;
			return (getAuthority().equals(gaObj.getAuthority()));
		} else {
			return false;
		}
	}	
}
