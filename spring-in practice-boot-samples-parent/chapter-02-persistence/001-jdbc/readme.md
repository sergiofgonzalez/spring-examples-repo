# 001-list-detail-app

This is the first example of SiP Chapter 3 - Building Web Applications with Spring Web MVC
	List-Detail Roster Application

It has been migrated to Spring Boot and Java Config.		
				
## Functionality:
The application consists of a main page in which a list of members are listed, and when the user clicks on one member a details page is displayed.

The application depends heavily on conventions but not as much as the SiP book does (for instance, the controller features explicit RequestMapping annotations).
		
## Components:
		
### Application.java:
The Spring Boot application runner, that also serves as the main configuration class.

The class is annotated with @EnableAutoConfiguration, along with @Configuration and @ComponentScan. The application is started as a main which invokes the SpringApplication.run().
		
### WebMvcConfig.java:
The configuration class for Spring MVC. It extends the class WebConfigurerAdapter to include an automatic controllers used to redirect users from "/" to "/roster/list"

The class includes an @Configuration annotation to identify the class as a 
JavaConfig class.

### Member.java
The model (domain model) class that represents a member of the roster.
				
### RosterController:
The controller for the /roster endpoints.
It has three methods:
* ctor: which initializes an arraylist with some dummy members, so that we don't have to go to the database to retrieve information.
* list: which is configured to map /roster/list endpoint. It simply adds the members list as an attribute in the model. As per convention, the members ArrayList will be available as "memberList" for the views.
Typically, you would prefer using a Map<String,Object> and give an appropriate name to the attribute.
Also, because of convention, the method returns void, so the view that will handle the response will be matched to "list", which ultimately will be mapped to /WEB-INF/jsp/roster/list.jsp. 
* member: which is configured to map /roster/member. It will read the id parameter of the HTTP request and will add the member[id] to the model so that the correct member is displayed on the page.
The method also returns void, so the view that will be presented will be member.
				
### application.yml:
Configures and tailors the view prefix and suffix to /WEB-INF/jsp/xxx.jsp.
It is written in YAML syntax.
		
### roster/list.jsp:
The list view. It receives the parameter "memberList" and performs a JSP for-each putting each member on a bulleted list.

### roster/member.jsp:
The detail view. It receives the parameter "member" and displays it. It also features a back button to return to the list.

### pom.xml:
The POM for this project which includes:
* packaging as a WAR
* Additional required dependencies besides Spring Boot's:
	- Tomcat and Tomcat Embedded
