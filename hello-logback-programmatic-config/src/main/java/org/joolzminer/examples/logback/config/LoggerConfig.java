package org.joolzminer.examples.logback.config;


import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.FileAppender;

@Configuration
public class LoggerConfig {
	
	@Bean
	public Logger rootLogger() {
		final Logger rootLogger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		final LoggerContext loggerContext = rootLogger.getLoggerContext();
		loggerContext.reset();
		
		final PatternLayoutEncoder encoder = new PatternLayoutEncoder();
		encoder.setContext(loggerContext);
		encoder.setPattern("%date{HH:mm:ss.SSS} |-%-5level [%thread] [%logger{0}] %msg%n");
		encoder.setOutputPatternAsHeader(true);
		encoder.start();
		
		final FileAppender fileAppender = new FileAppender();
		fileAppender.setContext(loggerContext);
		fileAppender.setFile("hello-logback-programmatic-config.log");
		fileAppender.setEncoder(encoder);
		fileAppender.start();
		
		rootLogger.addAppender(fileAppender);
		
		
		rootLogger.setLevel(Level.INFO);
		
		rootLogger.info("patternlayout encoder info");	
		
		return rootLogger;
	}
}
