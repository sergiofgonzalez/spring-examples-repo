# 004-form-persistence

This is the fourth example of SiP Chapter 4 - Basic Web Forms:
	Saving Form Data
It has been migrated to Spring Boot and Java Config.		
				
## Functionality
It is based on project 004- but now the data is being persisted on the database when the form is being processed.
Besides, to enhance the security aspects of the application, a special technique is being used:
  The Account domain class do not include a password field. As a consequence, it can be used within the application without compromising the password. When a new Account is created, firstly it will be persisted using a regular JPA repository (no password, as it is not defined as a field), and then a special JDBC method will be used to update the password field.
The application uses JPA + JDBC transactions.

## Components (added/changed over 003-)

### DatabaseConfig.java
This class will hold all the Database configuration responsibilities.
First of all, it defines the DataSource using values extracted from the application.properties using @Value(${"key"}), and then configures a Hikari Connection Pool.
After that, it is also created a custom DataSourceInitializer that if enabled, will submit for execution a script to create the tables and script to load data.
This class also declares a NamedParametersJdbcTemplate so it can be used from the JDBC repositories.

### AbstractEntity.java
The superclass of all persistent entities.
Defines the entity id as a Long field, and also declares the hashCode() and equals() based on the id field.

### Account.java
The Account domain model entity.
JPA and JSR303 annotations are used on the fields to produce a more dynamic entity. The no-args constructor is declared as protected and the only public constructor receives all the fields of the entity.
It must be noted that this entity does not have a password field. By doing that, you can pass Account objects around without worrying about incidentally printing or changing the password.

### AccountRepository.java
The SpringData repository which defines the save(), findByUsername() and findByEmail() methods. The interface extends from Repository but also from AccountRepositoryCustom as suggested by SpringData whenever you want to create custom methods for SpringData repositories.

### AccountRepositoryCustom.java
A custom Repository interface that defines the method save(Account account, String password) and that will be used to create a new Account and update the password in the same transaction.
According to the SpringData documentation, when you want to enhance a SpringData repository you have to use this naming.

### AccountRepositoryImpl.java
The implementation of AccountRepositoryCustom. The class is autowired with the AccountRepository and the JdbcTemplate and in the save method, first of all the Account is created, and only after that the password is updated. By doing that, you can pass Account objects around without worrying about incidentally printing or changing the password.

### AccountService.java
Implements the Service layer for the Account. It only features a single public method registerAccount() that validates that the Email and the Username aren't already taken. If everything is ok, it uses the AccountRepository to save the Account and the password.
If there is an error, it uses the Errors object it receives to add information about the source of the error.
In particular, if an error is found in the Email (duplicate Email) an error is added to the Errors object, associated to the Email field so that it is necessary to define an entry error.duplicate.account.email in the application.properties. The same with the username field.
It must be noted also that both username and email duplicate errors will include a parameter.

### AccountController.java
The processRegistrationForm() method is updated to use the AccountService to save the form data received. The service will be called even when AccountForm features validation errors because in that way we can inform the user in a single trip of all the errors found. 
If there are no errors, the registrationOk view will be displayed. Otherwise, the registration form with all the errors will be displayed.

### i18n/messages.properties
Added entries for the Spring Managed errors:
* error.duplicate.account.username: message to be displayed when the username is a duplicate value
* error.duplicate.account.email: message to be displayed when the email is a duplicate value
Both messages use argument variables, that are tagges using curlies {0}, {1}...

### sql/create-schema.sql
The SQL script for the creation of the account table which is like the Account entity but with the password field also included.
This script will be submitted to execution if the DataSource initializer is enabled (application.dataSource.initializer.enabled=true)

### sql/insert-data.sq
The SQL script for the insertion of data.
This script will be submitted to execution after the create-schema.sql if the DataSource initializer is enabled (application.dataSource.initializer.enabled=true)

### application.yml
The properties file is heavily modified to include everything related to JPA, dataSource and DataSource initializer configuration.

### pom.xml
Added dependencies for:
* Spring Boot JPA
* Spring Boot JDBC
* HikariCP
* mysql-connector