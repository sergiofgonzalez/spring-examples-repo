package org.joolzminer.examples.sip.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/loginRequired").setViewName("loginRequired");
		registry.addViewController("/loginFailed").setViewName("loginFailed");
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/accounts/registrationOk").setViewName("/accounts/registrationOk");		
	}
}
