# 006-custom-user-service

This is the sixth example of SiP Chapter 6 - Authenticating Users:
	Using a custom user service and user principal.
	
It has been migrated to Spring Boot and Java Config.		
				
## Functionality
It is based on project 005- and in project 004-form-persistence from chapter04-we-forms. Now instead of saving the default org.springframework.security.core.userdetails.User we are saving a full featured user representation that includes first name, last name, email, address, etc.
In this example, the user can authenticate, and when he/she does he is given the capability of registrating new accounts.
Nevertheless, the idea is not to provide a registration capability, but to demonstrate how to customize the User definition and leverage it in the views.

## Components (added/changed over 005-)

### DatabaseConfig.java
This class is modified according to 004-form-persistence, so that the DataSourceInitializer can be disabled, and that a NamedParameterJdbcTemplate is defined.

### SecurityConfig.java
This class is simplified in the section of the AuthenticationManager configuration. Now, instead of using jdbcAuthentication we use a UserDetailsServiceAdapter.

### WebMvcConfig.java
This class is modified to include a new automatic view controller for the registrationOk page.

### AccountMapper.java
Simple bean that transforms an AccountForm into an Account.

### AbstractEntity.java
The superclass of all persistent entities.
Defines the entity id as a Long field, and also declares the hashCode() and equals() based on the id field.

### Account.java
The Account domain model entity.
JPA and JSR303 annotations are used on the fields to produce a more dynamic entity. The no-args constructor is declared as protected and the only public constructor receives all the fields of the entity.
It must be noted that this entity does not have a password field. By doing that, you can pass Account objects around without worrying about incidentally printing or changing the password.
Besides, this class features a @ManyToMany relationship with the Role entity.

### Role.java
The Role domain model entity. This class represents the same things as the Role table, but with a POJO. This class does not feature any relationship to the Account entity.

### UserDetailsAdapter.java
This class is an adapter for the User that we want to represent, which is a mixin of Account and the Spring User. It is declared to implement a UserDetails object as required by Spring Security, and includes the definition of an Account and the password.

### AccountRepository.java
The SpringData repository which defines the save(), findByUsername() and findByEmail() methods. The interface extends from Repository but also from AccountRepositoryCustom as suggested by SpringData whenever you want to create custom methods for SpringData repositories.

### AccountRepositoryCustom.java
A custom Repository interface that defines the method save(Account account, String password) and the findPasswordByUsername. It will be used to create a new Account and update the password in the same transaction and get the password for a given user.
According to the SpringData documentation, when you want to enhance a SpringData repository you have to use this naming.

### AccountRepositoryImpl.java
The implementation of AccountRepositoryCustom. The class is autowired with the AccountRepository and the JdbcTemplate and in the save method, first of all the Account is created, and only after that the password is updated. By doing that, you can pass Account objects around without worrying about incidentally printing or changing the password.
The findPasswordByUsername queries the account table using JDBC to retrieve the user's password.

### AccountService.java
Implements the Service layer for the Account. It only features a single public method registerAccount() that validates that the Email and the Username aren't already taken. If everything is ok, it uses the AccountRepository to save the Account and the password.
If there is an error, it uses the Errors object it receives to add information about the source of the error.
In particular, if an error is found in the Email (duplicate Email) an error is added to the Errors object, associated to the Email field so that it is necessary to define an entry error.duplicate.account.email in the application.properties. The same with the username field.
It must be noted also that both username and email duplicate errors will include a parameter.

### RoleRepository.java
Spring Data repository for Role entity that defines a single method findByName().

### AccountService.java
This is the AccountService from 004-form-persistence but with some modifications. When the form data has been validated and no error has been found, the user is given the "user" role. After that, the AccountRepository.save() is called to create a new Account with the associated password.

### UserDetailsServiceAdapter.java
This is the UserDetailsService implementation that will be given to the Security Configuration to handle user authentication. The interface features a single method loadUserByUsername() whose responsibility is to return a UserDetails instance, which in our case is implemented by the UserDetailsAdapter class.
To construct the UserDetails that is returned it is leveraged the AccountRepository to first of all find the Account entity data and then bind the password using the findPasswordByUsername() method.

### AccountController.java
The processRegistrationForm() method is updated to use the AccountService to save the form data received. The service will be called even when AccountForm features validation errors because in that way we can inform the user in a single trip of all the errors found. 
If there are no errors, the registrationOk view will be displayed. Otherwise, the registration form with all the errors will be displayed.

### AccountForm.java
The form class for the registration of a new account.

### i18n/messages.properties
The file that contain the externalized strings and Spring managed errors.

### sql/insert-data.sq
The SQL script for the insertion of data.
It has been modified to include the creation date for the users, as the '0000-00-00 00:00:00' gave some problems to Hibernate.

### application.yml
The properties file is heavily modified to include everything related to JPA, dataSource DataSource initializer and messages configuration.

### ValidationMessages.properties
The resource bundle for JSR303 error messages.

### accounts/registrationForm.jsp
The view for the registration form. This is taken from 004-form-persistence but modified to include the CSRF token as part of the form data so that Spring Security does not complain.

### accounts/registrationOk.jsp
The view for the registration confirmation.

### home.jsp
Modified to reference the principal.fullName instead of username.

### inline-login-form.jsp
Modified to reference the principal.fullName instead of username.

### navbar.jspf
Slightly modified to include the capability to define new users for authenticated users.

### pom.xml
Added hibernate.version property to 4.3.2.Final to prevent the HikariCP warning to be seen (trying to execute SQL sentance on closed connection).

Added dependencies for:
* Hibernate Validator
* Spring Boot JPA
