package org.joolzminer.examples.sip.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class UserDetailsAdapter implements UserDetails {

	private Account account;
	private String password;
		
	public UserDetailsAdapter(Account account) {
		this.account = account;
	}
	
	public Long getId() {
		return account.getId();
	}
	
	public String getFirstName() {
		return account.getFirstName();
	}
	
	public String getLastName() {
		return account.getLastName();
	}
	
	public String getFullName() {
		return account.getFullName();
	}
	
	public String getEmail() {
		return account.getEmail();
	}

	@Override
	public String getUsername() {
		return account.getUsername();
	}

	@Override
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return account.isEnabled();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<>();
		for (Role role : account.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getName()));
		}
		return authorities;
	}
}
