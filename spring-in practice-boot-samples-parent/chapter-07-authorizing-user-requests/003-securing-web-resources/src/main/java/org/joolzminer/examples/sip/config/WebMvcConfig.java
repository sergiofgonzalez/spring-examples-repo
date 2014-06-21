package org.joolzminer.examples.sip.config;

import java.util.Date;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/admin").setViewName("admin");
	}
	
	@Bean
	public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver smer = new CustomSimpleMappingExceptionResolver();
		smer.setDefaultErrorView("error");
		return smer;
	}
	
	private static class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {

		@Autowired
		private MessageSource messageSource;
		
		@Override
		protected ModelAndView doResolveException(HttpServletRequest request,
				HttpServletResponse response, Object handler, Exception ex) {
			ModelAndView mav =  super.doResolveException(request, response, handler, ex);
			loadErrorInfo(mav, request, ex);
			return mav;
		}
		
		private void loadErrorInfo(ModelAndView mav, HttpServletRequest request, Exception ex) {
			if (!mav.getModel().containsKey("timestamp")) {
				mav.addObject("timestamp", new Date());
			}
			
			if (!mav.getModel().containsKey("error")) {
				Locale locale = LocaleContextHolder.getLocale();
				String message = messageSource.getMessage("error.internal", null, locale); 
				mav.addObject("error", message);
			}
			
			if (!mav.getModel().containsKey("exception")) {
				mav.addObject("exception", ex.getClass().getSimpleName());
			}
			
			if (!mav.getModel().containsKey("message")) {
				mav.addObject("message", ex.getMessage());
			}
			
			if (!mav.getModel().containsKey("trace")) {
				mav.addObject("trace", ex.getStackTrace());
			}
			
			if (!mav.getModel().containsKey("path")) {
				mav.addObject("path", request.getRequestURI().toString());
			}
			
			if (!mav.getModel().containsKey("method")) {
				mav.addObject("method", request.getMethod());
			}			
		}
	}
}
