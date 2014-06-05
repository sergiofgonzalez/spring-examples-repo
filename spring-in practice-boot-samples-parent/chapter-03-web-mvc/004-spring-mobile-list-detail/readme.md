# 004-spring-mobile-list-detail

This is the fourth example of SiP Chapter 3 - Building Web Applications with Spring Web MVC:
    Spring Mobile

It has been migrated to Spring Boot and Java Config.		
				
## Functionality:
The application demonstrates how you can use Spring to redirect the user to a customized set of views and css depending on the device he/she is using to connect to the application. The application displays a contact list with links in which the user can click on to view the contact details page.

The application tries to leverage as much as possible Spring Boot documentation, but the lack of proper Spring Boot auto configuration with the Spring Mobile project has forced me to include some beans that may be dups of what Spring Boot already provides.

In any case, I haven't performed further investigation because it seems that the current trend is creating a responsive view design that do not separate the normal and mobile sites, but rather responsively adapts its UI according to the size of the view port, etc.

	
## Components

### Application.java
Spring Boot application class runner. The class is annotated with @EnableAutoConfiguration, along with @Configuration and @ComponentScan. The application is started as a main which invokes the SpringApplication.run().

### WebMvcConfig.java
The WebMvc and Spring Mobile Configuration class.
First of all it defines an automatic controller for the "/" endpoint to redirect it to /contact/list.
Then, the bean in charge of selecting the appropriate set of views is defined. Here, the values of the application properties are injected to configure how the resolution is performed: first of all, the "global" view resolver, and then, the prefix that is added when the device is a mobile or it is a normal device.
Then, a few other beans are defined as listed on Spring Mobile examples.

### Contact
The domain model class that represents a contact in our contact list. It implements the comparable interface so that it can be sorted.

### ContactController
The controller for the /contact endpoint.
It defines a constructor that builds a list of hardcoded contacts, and a method that handles the '/list' and '/detail' operations. There are no dependencies to Spring Mobile in the controller (as it should be).

### application.yml:
The application properties written in YAML syntax.
It declares some properties that are injected in the WebMvcConfig. 

### jsp/mobile/contact/list.jsp
The JSP page for the Contacts List intended to be used for mobile devices.
The interesting thing can be seen at the end of the source, where it is checked the user preferences so that the user can use the mobile or normal site.

### jsp/mobile/contact/detail.jsp
The JSP page for the Contact Detail intended to be used for mobile devices.

### jsp/mobile/navbar.jspf
The JSP fragment for the navigation bar on mobile devices, which includes some text to identify whether we are in the normal or mobile site.

### jsp/normal/contact/list.jsp
The JSP page for the Contacts List intended to be used for non-mobile devices.
The interesting thing can be seen at the end of the source, where it is checked the user preferences so that the user can use the mobile or normal site.

### jsp/normal/contact/detail.jsp
The JSP page for the Contact Detail intended to be used for non-mobile devices.

### jsp/normal/navbar.jspf
The JSP fragment for the navigation bar on non-mobile devices, which includes some text to identify whether we are in the normal or mobile site.

### pom.xml
POM for the project which includes spring-boot-starter-mobile dependencies.