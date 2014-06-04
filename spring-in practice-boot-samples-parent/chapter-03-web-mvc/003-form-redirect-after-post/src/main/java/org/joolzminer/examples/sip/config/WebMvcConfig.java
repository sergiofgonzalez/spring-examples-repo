package org.joolzminer.examples.sip.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(WebMvcConfig.class);
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		LOGGER.debug("Customizing MVC Configuration");
		registry.addViewController("/").setViewName("redirect:/roster/list");
		registry.addViewController("/nominee/thanks").setViewName("/nominee/thanks");
	}
}
