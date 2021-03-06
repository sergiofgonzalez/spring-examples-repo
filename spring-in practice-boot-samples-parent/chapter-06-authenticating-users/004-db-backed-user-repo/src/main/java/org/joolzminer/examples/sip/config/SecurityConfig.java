package org.joolzminer.examples.sip.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.formLogin()
				.usernameParameter("j_username")
				.passwordParameter("j_password")				
				.loginPage("/loginRequired")
				.loginProcessingUrl("/j_spring_security_check")
				.failureUrl("/loginFailed")
				.defaultSuccessUrl("/home")
				.and()
			.logout()
				.logoutSuccessUrl("/home")
				.and()
			.rememberMe();
	}

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)	throws Exception {
		auth
			.jdbcAuthentication()
				.dataSource(dataSource);
	}	
}
