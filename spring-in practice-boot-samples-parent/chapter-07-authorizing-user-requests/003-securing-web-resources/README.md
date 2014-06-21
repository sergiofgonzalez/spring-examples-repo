# 003-securing-web-resources

This is the third example of SiP Chapter 7 - Authorizing User Requests:
    Authorizing web resources using authentication levels, roles and permissions.
    
It has been migrated to Spring Boot and Java Config.
       

## Functionality
This example is based on 002-securing-service-methods and adds control over web resources (i.e. urls), so that users cannot directly type a web url to gain access to a particular page.
Additionally, it has been improved the serving of static resources by explicitly adding rules that control what can be accessed and what cannot be accessed.
Finally, it has been added a custom error page so that now whenever a problem occurs a custom page with detailed error information is shown to the user.

These are the details.

## Components (added/modified)

### SecurityConfig.java
Here's where most of the action about web resources authorization control is introduced. First of all, the login and logout functionality is given the permitAll() authorization.
Then, a block with authorizeRequests is introduced to finely grained control what pages can be accessed, by whom and using what methods. 
For example, the `/admin` resource will be accessed only by users with the ADMIN role, and the `/forums` page only by users how have authenticated themselves. Therefore, if an authenticated users try to access `/forums` by typing that on the address bar he/she will get an access denied error or be automatically redirected to the login page.

This class also includes the configuration of the resources that will be ignored by the authorization rules.

### WebMvcConfig.java
This class has been heavily modified to provide the custom error page functionality.

First of all, it is defined a private static class that extends from SimpleMappingExceptionResolver. This class will be used when an exception occurs, and the idea is to include as much information as possible in the model so that it can be used to understand the error.
The set of fields kinda stick to the fields defined by Spring Boot internal Whitelabel error page but those classes and interfaces are not used:
* timestamp
* error message (taken from i18n using a MessageSource)
* exception
* exception message
* stack trace
* path (url where the error ocurred)
* method (HTTP method)

The logic is quite simple, the model received from Spring Web MVC after the error is examined. If it already contains a field by the name of `timestamp` it will not be overwritten; if it doesn't then it will be added.

Then, this class is registered as a Spring Bean and the default error view is set to `error` so that the custom error page will be `WEB-INF/jsp/error.jsp`.

Note that this class will be only activated for application internal errors. For example, Security related errors will still be handled by Spring Boot error handling. Thus, some fields such as the method or the stack trace of the error may not be available in such cases.

### messages.properties
The error.internal error key is defined.

### application.yml
The whitelabel error page provided by Spring Boot is disabled by setting `error.whitelabel.enabled` to `false`.

### fonts folder
It has been added a pixelated font named `LCD_Solid.ttf` downloaded from http://www.fontspace.com/lcd-solid/lcd-solid (kudos to the author). The font was converted also to woff.

### images
The bomb from old Mac OS system error messages (https://en.wikipedia.org/wiki/Bomb_(symbol)#mediaviewer/File:MacOs_Syserror.png) has been cropped and added.

### error.jsp
The custom error page that replaces the whitelabel one provided by Spring Boot. This page contains three important pieces:
* The visual Mac OS type system error message: using Bootstrap Jumbotron, the pixelated font and a little bit of CSS it is added a panel that resembles somehow the error message from the good old MacOS. Apart from the hardcoded message: Sorry, an error occurred it also includes the message and message description to the user.
* A table with the general errors: A table with some of the fields that may be interesting to the final user, such as the path where the error occured, the timestamp, etc.
* An embedded comment within the HTML in which the complete stack trace is given, so that a support person could investigate the problem. This part will only be visible if the user clicks on `view page source` option.