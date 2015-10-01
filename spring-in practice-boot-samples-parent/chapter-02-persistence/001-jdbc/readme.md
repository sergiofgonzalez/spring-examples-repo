# 001-jdbc

This is the first example of SiP Chapter 3 - Building Web Applications with Spring Web MVC
	Data Access using JDBC

It has been migrated to Spring Boot and Java Config.		
				
## Functionality
The project features a Contact domain entity and a JDBC based repository. The repository is tested using JUnit.

		
## Components
		
### Application.java
The Spring Boot application runner, that also serves as the main configuration class. It's useless for this application (it only initializes the database).
		
### DatabaseConfig.java
The configuration of the database related resources: datasource & connection pool. It includes a custom database initializer.

### Contact.java
The domain model class that represents a contact.
				
### ContactRepository.java
The Repository for the Contact domain model class. It's completely based on JDBC and does not feature any transactional related operations.
				
### application.yml:
Configures and tailors the database related resources.
		

### pom.xml:
The POM for this project which includes:
* packaging as a JAR
* Additional required dependencies besides Spring Boot's:
	- MySQL connector
    - HikaryCP connection pool
