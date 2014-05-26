package org.joolzminer.examples.boot.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationLogoutSuccessHandler implements LogoutSuccessHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationLogoutSuccessHandler.class);
	
	@Override
	public void onLogoutSuccess(HttpServletRequest request,	HttpServletResponse response, Authentication authentication)
						throws IOException, ServletException {
		LOGGER.debug("Successful logout for user {}", authentication.getPrincipal());
		response.setStatus(HttpServletResponse.SC_OK);
	}
}
