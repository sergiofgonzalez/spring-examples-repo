# 003-form-redirect-after-post

This is the third example of SiP Chapter 3 - Building Web Applications with Spring Web MVC
	Serving and Processing Forms with redirect-after-post-behavior

It has been migrated to Spring Boot and Java Config.		
				
## Functionality:
The application builds on 002- and adds the redirect-after-post behavior to minimize the likelihood of a double submit, prevent browsing warning when clicking reload, to allow page bookmarking, etc.

The implementation is very simple: you just have to prepend redirect: to the logical view name, but it also brings some additional issues that must be handled:
* An automatic controller to handle the redirection of the view must be added.
* The model attributes do not survive a redirect, so you have to include anything that you want to pass to the final view in FlashAttributes.

The example also features form-binding whitelisting using @InitBinder to prevent unexpected request parameters from being bound to model attributes.
	
## Components (added/modified):

### WebMvcConfig.java:
Added a new automatic controller for the /nominee/thanks logical view. If you don't include this, the redirect view is not properly mapped to the thanks.jsp page.
				
### NomineeController:
The controller for the /nominee endpoints.
The processing form method is added the RedirectAttributes so that information included survives the redirection. If you don't do that, the member received after the redirection will be empty.
Besides, it is included a method that explicitly whitelists the expected HTTP request parameters.

### application.yml:
The  thanks view is modified to include the 'redirect:' prefix.
