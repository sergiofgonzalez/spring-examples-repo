# 003-hibernate-data-access-layer

This is the fourth example of SiP Chapter 3 - Building Web Applications with Spring Web MVC
	Creating a data access layer

It has been migrated to Spring Boot and Java Config.		
				
## Functionality
The project features a Contact domain entity and Hibernate based data access layer, similar to what Spring Data provides, but custom built.

		
## Components
		
### Application.java
The Spring Boot application runner, that also serves as the main configuration class. It's useless for this application (it only initializes the database).
        
### DatabaseConfig.java
The configuration of the database related resources: datasource, connection pool and Hibernate's SessionFactory. It includes a custom database initializer.

### Contact.java
The domain model class that represents a contact. It includes persistence, validation and Hibernate validation annotations.

### Dao.java
The parameterized interface for an abstract DAO. It provides typical operations such as get, update, delete, get all, etc.

### AbstractDaoHibernateImpl.java
The Hibernate based implementation of the abstract DAO interface.
                
### ContactDao.java
The specialization of the Dao interface for the Contact domain model entity. It includes a custom method `findByEmail` as the abstract Dao does not feature (obviously) such a method.

### ContactDaoHibernateImpl.java
The implementation of the ContactDao interface. As it extends from Dao, it only provides the implementation for the `findByEmail`method.

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
