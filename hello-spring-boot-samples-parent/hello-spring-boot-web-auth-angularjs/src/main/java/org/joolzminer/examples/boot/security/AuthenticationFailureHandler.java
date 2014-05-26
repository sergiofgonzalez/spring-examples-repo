package org.joolzminer.examples.boot.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFailureHandler.class);

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,	HttpServletResponse response, 
					AuthenticationException exception) throws IOException, ServletException {
		LOGGER.debug("Authentication failed for user {}: {}", request.getParameter("j_username").trim(), exception.getMessage());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed");
	}	
}
