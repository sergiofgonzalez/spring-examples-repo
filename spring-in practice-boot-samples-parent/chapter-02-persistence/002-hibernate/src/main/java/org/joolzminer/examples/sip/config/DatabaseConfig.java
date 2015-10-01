package org.joolzminer.examples.sip.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.Assert;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseConfig.class);
	
	@Value("${application.dataSource.url}")
	private String dataSourceUrl;
	
	@Value("${application.dataSource.username}")
	private String dbUser;
	
	@Value("${application.dataSource.password}")
	private String dbPassword;
	
	@Value("${application.dataSource.initializer.enabled:false}")
	private boolean isDataSourceInitializerEnabled;
	
	@Value("${application.dataSource.initializer.scripts.drop}")
	private String sqlDropScript;
	
	@Value("${application.dataSource.initializer.scripts.create}")
	private String sqlCreateScript;
	
	@Value("${application.dataSource.initializer.scripts.insert}")
	private String sqlInsertScript;
	
	@Value("${application.dataSource.initializer.separator:;}")
	private String sqlSeparator;
		
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		LOGGER.debug("Configuring Datasource");
		Assert.hasText(dataSourceUrl, "The datasource URL cannot be empty");
		Assert.hasText(dbUser, "The datasource username cannot be empty");
		Assert.notNull(dbPassword, "The datasource password cannot be null");
		
		
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(dataSourceUrl);
		config.setUsername(dbUser);
		config.setPassword(dbPassword);
		config.setAutoCommit(true);
						
		return new HikariDataSource(config);
	}
	
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Bean
	public DataSourceInitializer customDataSourceInitializer(DataSource dataSource) {
		if (isDataSourceInitializerEnabled) {
			LOGGER.warn("Initializing Datasource with configured SQL Scripts: NOT INTENDED FOR PRODUCTION!!!");
			ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
			resourceDatabasePopulator.addScripts(
					new ClassPathResource(sqlDropScript),
					new ClassPathResource(sqlCreateScript), 
					new ClassPathResource(sqlInsertScript)
					);
			resourceDatabasePopulator.setSeparator(sqlSeparator);
			resourceDatabasePopulator.setContinueOnError(false);
			DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
			dataSourceInitializer.setDataSource(dataSource);
			dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
			
			dataSourceInitializer.setEnabled(true);			
			
			return dataSourceInitializer;
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("serial")
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan("org.joolzminer.examples.sip.domain");
		sessionFactory.setHibernateProperties(new Properties() {{
			put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
			put("hibernate.show_sql", false);
			put("hibernate.hbm2ddl.auto", false);
		}});
		
		return sessionFactory;
	}
}
