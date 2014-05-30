# 001-basic-registration-form
==============================

This is the first example of SiP Chapter 4 - Basic Web Forms:
	Displaying a Web Form
It has been migrated to Spring Boot and Java Config.		
				
## Functionality:
The user is presented with a registration form with text input and some checkboxes.	The user can clicked on Submit to send the entered information to the backend. After that, a simple "Thank you for registering" page is displayed.
When the user accesses the "/" it is redirected to the registration form.		

		
## Components:
		
### Application.java:
		Spring Boot application class runner. The class is annotated with 
		@EnableAutoConfiguration, along with @Configuration and @ComponentScan.
		The application is started as a main which invokes the SpringApplication.run().
		
### WebMvcConfig.java:
The configuration class for Spring MVC. It extends the class WebConfigurerAdapter to include the empty controllers for the "/" and "/accounts/registrationOk" views.
* For the / url, the view consists in a redirection to /accounts/new which will
			  ultimately load the registration form, as if the user would have typed /accounts/new.
* The /accounts/registrationOk controller is registered to display the JSP at
			  /accounts/registrationOk.
		
The class includes an @Configuration annotation to identify the class as a 
JavaConfig class.
				
### AccountController:
The controller for the /accounts endpoints.
It has two methods, the first one will return an empty registration form and the second will print the contents entered by user and will redirect to the accounts/registrationOk view.		
				
### application.properties:
Configures and tailors the view prefix and suffix to /WEB-INF/jsp/xxx.jsp.
It is written in YAML syntax.
		
### accounts/registrationForm.jsp:
The registration form. It uses the Spring Tag Library for forms to let you bind model objects to fields in input, checkboxes, etc.
			
### pom.xml:
The POM for this project which includes:
* packaging as a WAR
* Additional required dependencies besides Spring Boot's:
	- Tomcat and Tomcat Embedded
