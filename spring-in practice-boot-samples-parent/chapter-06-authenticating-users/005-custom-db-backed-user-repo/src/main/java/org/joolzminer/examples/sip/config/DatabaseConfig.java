package org.joolzminer.examples.sip.config;



import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.Assert;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfig.class);
	
	@Value("${application.dataSource.driverClassName}")
	private String driverClassName;
	
	@Value("${application.dataSource.url}")
	private String dataSourceUrl;
	
	@Value("${application.dataSource.username}")
	private String dbUser;
	
	@Value("${application.dataSource.password}")
	private String dbPassword;
	
	@Value("${application.dataSource.scripts.create}")
	private String sqlCreateScript;
		
	@Value("${application.dataSource.scripts.insert}")
	private String sqlInsertScript;

	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		LOGGER.debug("Configuring Datasource");
		Assert.hasText(driverClassName, "The datasource driver class name cannot be empty");
		Assert.hasText(dataSourceUrl, "The datasource URL cannot be empty");
		Assert.hasText(dbUser, "The datasource username cannot be empty");
		Assert.notNull(dbPassword, "The datasource password cannot be null");
		
		
		HikariConfig config = new HikariConfig();
		config.setDataSourceClassName(driverClassName);
		config.addDataSourceProperty("url", dataSourceUrl);
		config.addDataSourceProperty("user", dbUser);
		config.addDataSourceProperty("password", dbPassword);
		
		return new HikariDataSource(config);
	}
	
	@Bean
	public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
		LOGGER.debug("Initializing Datasource with SQL Scripts");
		ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
		resourceDatabasePopulator.addScripts(new ClassPathResource(sqlCreateScript), new ClassPathResource(sqlInsertScript));
		DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
		dataSourceInitializer.setDataSource(dataSource);
		dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
		
		return dataSourceInitializer;
	}
}
