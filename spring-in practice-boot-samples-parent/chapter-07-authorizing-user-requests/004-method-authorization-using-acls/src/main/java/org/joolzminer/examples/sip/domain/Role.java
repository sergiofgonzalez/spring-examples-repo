package org.joolzminer.examples.sip.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.core.style.ToStringCreator;
import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
@Entity
public class Role extends AbstractEntity implements GrantedAuthority {
	@Column(length = 50, nullable = false, unique = true)
	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_permission",
			joinColumns = {@JoinColumn(name = "role_id")},
			inverseJoinColumns = {@JoinColumn(name = "permission_id")})
	private Set<Permission> permissions = new HashSet<>();
	
	protected Role() {		
	}
	
	public Role(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public Set<Permission> getPermissions() {
		return Collections.unmodifiableSet(permissions);
	}

	@Override
	public String toString() {
		return new ToStringCreator(this)
					.append("name", name)
					.toString();
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
