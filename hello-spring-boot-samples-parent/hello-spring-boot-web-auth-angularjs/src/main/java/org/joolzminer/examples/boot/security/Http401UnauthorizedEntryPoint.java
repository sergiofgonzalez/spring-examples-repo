package org.joolzminer.examples.boot.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class Http401UnauthorizedEntryPoint implements AuthenticationEntryPoint {
	private static final Logger LOGGER = LoggerFactory.getLogger(Http401UnauthorizedEntryPoint.class);

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, 
			AuthenticationException authException)	throws IOException, ServletException {
		LOGGER.debug("Secured entry point called before successful authentication. Access Denied");
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
	}	
}
