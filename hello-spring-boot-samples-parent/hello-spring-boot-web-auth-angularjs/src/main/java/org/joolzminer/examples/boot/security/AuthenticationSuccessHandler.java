package org.joolzminer.examples.boot.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationSuccessHandler.class);

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,	HttpServletResponse response, 
					Authentication authentication) throws IOException, ServletException {
		LOGGER.debug("Successful authentication for user {}", ((UserDetails)authentication.getPrincipal()).getUsername());
		response.setStatus(HttpServletResponse.SC_OK);
	}	
}
