# 003-hibernate-data-access-layer

This is the sixth example of SiP Chapter 3 - Building Web Applications with Spring Web MVC
	Spring Data JPA overview

It has been migrated to Spring Boot and Java Config.		
				
## Functionality
The project features a Contact domain entity and JPA based custom DAO.

		
## Components
		
### Application.java
The Spring Boot application runner, that also serves as the main configuration class. It's useless for this application (it only initializes the database).
        
### DatabaseConfig.java
The configuration of the database related resources: datasource, connection pool and Hibernate's SessionFactory. It includes a custom database initializer.

### Contact.java
The domain model class that represents a contact. It includes persistence, validation and Hibernate validation annotations.

### ContactRepository.java
The interface for the Contact Repository using Spring Data. It provides typical operations by extending `CrudRepository` and adds a custom method `findByEmailLike`.

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
