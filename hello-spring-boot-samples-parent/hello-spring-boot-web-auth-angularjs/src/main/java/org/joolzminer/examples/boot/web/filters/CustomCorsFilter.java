package org.joolzminer.examples.boot.web.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import static org.joolzminer.examples.boot.security.AllowedOriginsConstants.ALLOWED_ORIGINS;

@Component
public class CustomCorsFilter extends OncePerRequestFilter {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomCorsFilter.class);
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,	HttpServletResponse response, 
				FilterChain filterChain) throws ServletException, IOException {
		
		String originHeaderValue = request.getHeader("Origin");
		if ("OPTIONS".equals(request.getMethod()) && request.getHeader("Access-Control-Request-Method") != null) {
			LOGGER.debug("OPTIONS with Access-Control-Request-Method received");
			if (ALLOWED_ORIGINS.contains(originHeaderValue)) {
				LOGGER.debug("CORS allowed for OPTIONS and origin={}", originHeaderValue);
				response.setHeader("Access-Control-Allow-Origin", originHeaderValue);
				response.setHeader("Access-Control-Allow-Credentials", "true");
				response.setHeader("Access-Control-Allow-Headers", "Content-Type");
			}
		} else if (ALLOWED_ORIGINS.contains(originHeaderValue)) {
			LOGGER.debug("CORS allowed for {} and origin={}", request.getMethod(), originHeaderValue);
			response.addHeader("Access-Control-Allow-Origin", originHeaderValue);
			response.addHeader("Access-Control-Allow-Credentials", "true");
		}
		
		filterChain.doFilter(request, response);
	}
}
