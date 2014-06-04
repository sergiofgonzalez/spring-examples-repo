# 002-form-serving-and-processing

This is the second example of SiP Chapter 3 - Building Web Applications with Spring Web MVC
	Serving and Processing Forms

It has been migrated to Spring Boot and Java Config.		
				
## Functionality:
The application builds on the basis of 001- and adds the capability of casting a vote for an award, to demonstrate how serving and processing forms work on Spring MVC. Besides, it features some other techniques for Controller methods that were not shown on 001-.

	
## Components:
		
### Member.java
The model (domain model) class that represents a member of the roster is modified to include a public constructor and the setters for the properties, as in this example we need two-way data-binding.
It must be noted that two-way data binding is based on setters, and therefore, form processing will not work if you don't provide those!!!
				
### NomineeController:
The controller for the /nominee endpoints.
The class is annotated with @Controller to indicate that this is an HTTP controller and @RequestMapping("/nominee") to indicate that it will handle all /nominee requests.

The controller declares a String that will be loaded from the application properties and that will be used to decouple the thanks view.

Then, the form() method is declared to serve the form when the /nominee URL is requested through a GET. It is declared to return ModelAndView object that let us do just that: return a view name and put an object in the model. Note that the attribute name is configured with a different name from the convention (it is set to nominee).

The processFormData() method is declared to serve the form processing, that is, when /nominee is accessed through a POST. It simply prints the contents of the model attribute and returns the view configured through the application properties.
Note that the @ModelAttribute is used to let Spring know that we expect the attribute to be named 'nominee' and not 'member'.
				
### application.yml:
Configures and tailors the view prefix and suffix to /WEB-INF/jsp/xxx.jsp.
It is modified to include the thanks view that is injected into the NomineeController.
Note that it is not prefixed with a redirect, meaning that if the user clicks on the reload button after casting the vote, the vote will be sent and processed again.
This can be fixed by using a redirect-after-post behavior, as seen on 003-

### navbar.jspf:
The navigation bar that includes the roster's functionality along with the nominee serving and processing. It is included in all the views.

### nominee/form.jsp
The Nomination form. It declares a form that is bound to the nominee model attribute. It declares a couple of text fields so the user can submit any name.

### nominee/thanks.jsp
The thanks view that simply prints the name of the nominee. Note that it does not need any type of explicit binding, it just uses ${nominee} to retrieve the model attribute.

### roster/list.jsp:
Modified to include the navigation bar.

### roster/member.jsp:
Modified to include the navigation bar.

### pom.xml:
The POM for this project which includes:
* packaging as a WAR
* Additional required dependencies besides Spring Boot's:
	- Tomcat and Tomcat Embedded
