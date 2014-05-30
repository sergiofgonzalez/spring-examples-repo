package org.joolzminer.examples.sip.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.util.Assert;

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
		
	@Value("${application.security.usersByUsernameQuery}")
	private String usersByUsernameQuery;
	
	@Value("${application.security.authoritiesByUsernameQuery}")
	private String authoritiesByUsernameQuery;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)	throws Exception {
		Assert.hasText(usersByUsernameQuery, "The query to retrieve users must be defined in the application properties");
		Assert.hasText(authoritiesByUsernameQuery, "The query to retrieve authorities must be defined in the application properties");
		
		auth
			.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery(usersByUsernameQuery)			
				.authoritiesByUsernameQuery(authoritiesByUsernameQuery);
	}	
}
