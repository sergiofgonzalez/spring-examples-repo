# 002-form-externalized-strings

This is the second example of SiP Chapter 4 - Basic Web Forms:
	Externalizing strings in the view
It has been migrated to Spring Boot and Java Config.		
				
## Functionality
The examples is based on 001, but externalizing the strings that appears in the JSPs so they can be managed centrally.
The user is presented with a registration form with text input and some checkboxes.	The user can clicked on Submit to send the entered information to the backend. After that, a simple "Thank you for registering" page is displayed.
When the user accesses the "/" it is redirected to the registration form.		

		
## Components (added/changed over 001-)
					
### application.properties
Configures the location of the messages files in the i18n folder. Note that the project leverages the MessageSource provided by Spring Boot autoconfiguration. 

### i18n/messages.properties
The messages file that contains all the strings that will be used in the JSPs.

### accounts/registrationForm.jsp
The registration form with the strings externalized in the i18n/messages.properties file.
			
### accounts/registrationOk.jsp
The registrationOk page with the strings externalized in the i18n/messages.properties file.
