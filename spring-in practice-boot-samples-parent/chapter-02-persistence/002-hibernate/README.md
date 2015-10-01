# 002-hibernate

This is the third example of SiP Chapter 3 - Building Web Applications with Spring Web MVC
	Object relational mapping and transactions via Hibernate

It has been migrated to Spring Boot and Java Config.		
				
## Functionality
The project features a Contact domain entity and a native Hibernate based repository. On top of the repository there is service layer that exposes the repository operations and control transactionality. The service is tested using JUnit.


## Components

### Application.java
The Spring Boot application runner, that also serves as the main configuration class. It's useless for this application (it only initializes the database).
		
### DatabaseConfig.java
The configuration of the database related resources: datasource, connection pool and Hibernate's SessionFactory. It includes a custom database initializer.

### Contact.java
The domain model class that represents a contact. It includes persistence, validation and Hibernate validation annotations.
				
### ContactRepository.java
The Repository for the Contact domain model class. It's completely based on Hibernate and does not feature any transactional related operations.

### ContactService.java
The Service for the Contact repository. It exposes the operations defined in the repository and adds transactional annotations.

### application.yml:
Configures and tailors the database related resources.
		

### pom.xml:
The POM for this project which includes:
* packaging as a JAR
* Additional required dependencies besides Spring Boot's:
	- MySQL connector
    - HikaryCP connection pool
    - Hibernate
    - Hibernate Validator
    - javax-EL as required by Spring
