package org.joolzminer.examples.logback.runners;

import org.joolzminer.examples.logback.services.HelloWorldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationRunner.class);
	
	@Autowired
	private HelloWorldService helloWorldService;
	
	@Override
	public void run(String... args) throws Exception {
		LOGGER.debug("About to call HelloWorldService.getHelloMessage()...");
		System.out.println(helloWorldService.getHelloMessage());
	}

}
