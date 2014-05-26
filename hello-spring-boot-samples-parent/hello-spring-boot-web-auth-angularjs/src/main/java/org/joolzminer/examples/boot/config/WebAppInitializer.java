package org.joolzminer.examples.boot.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.joolzminer.examples.boot.web.filters.CustomCorsFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;

@Configuration
public class WebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		FilterRegistration.Dynamic corsFilter = servletContext.addFilter("corsFilter", CustomCorsFilter.class);
		corsFilter.addMappingForServletNames(null, false, "/*");
	}
}
