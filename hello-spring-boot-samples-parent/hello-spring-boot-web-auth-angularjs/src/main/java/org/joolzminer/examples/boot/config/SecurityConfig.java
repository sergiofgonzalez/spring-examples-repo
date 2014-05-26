package org.joolzminer.examples.boot.config;

import org.joolzminer.examples.boot.security.AuthenticationLogoutSuccessHandler;
import org.joolzminer.examples.boot.security.Http401UnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Http401UnauthorizedEntryPoint http401UnauthorizedEntryPoint;
	
	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	private AuthenticationLogoutSuccessHandler authenticationLogoutSuccessHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)	throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("admin")
					.password("admin")
					.roles("ADMIN", "USER")
					.and()
				.withUser("user")
					.password("user")
					.roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.exceptionHandling()
				.authenticationEntryPoint(http401UnauthorizedEntryPoint)
				.and()
			.formLogin()
				.loginProcessingUrl("/sip/users/login")
				.successHandler(authenticationSuccessHandler)
				.failureHandler(authenticationFailureHandler)
				.usernameParameter("j_username")
				.passwordParameter("j_password")
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/sip/users/logout")
				.logoutSuccessHandler(authenticationLogoutSuccessHandler)
				.deleteCookies("JSESSIONID")
				.permitAll()
				.and()
			.csrf()
				.disable()
			.authorizeRequests()
				.antMatchers("/sip/users/login").permitAll()
				.antMatchers("/sip/users/logout").permitAll();
	}	
}
