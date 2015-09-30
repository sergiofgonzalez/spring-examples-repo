package org.joolzminer.examples.logback.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HelloWorldService {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldService.class);
	
	@Value("${name:World}")
	private String name;
	
	public String getHelloMessage() {
		LOGGER.warn("Writing greeting for name={}", name);		
		return "Hello, " + name + "!!!";
	}
}
