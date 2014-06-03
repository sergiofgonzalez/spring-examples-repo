# 008-auto-authentication-after-registration

This is the eighth example of SiP Chapter 6 - Authenticating Users:
    Auto-authenticating the user after a successful registration.

It has been migrated to Spring Boot and Java Config.			

## Functionality
Based on 007-, this project demonstrates how to automatically authenticate the user after a successful new user registration. That is, after a successful registration, the user can click on the home link and see the protected content.

## Components (added/changed over 005-)

### SecurityConfig.java
The Security Configuration class is modified to include a method that exposes the AuthenticationManager bean.

### AccountController.java
The method processRegistrationForm() that manages the processing of the registration form is modified to programmatically authenticate the user after the form has been successfully processed.
The procedure is relatively simple:
* A new AuthRequest is constructed as a UsernamePasswordAuthenticationToken with the username and password from the form.
* After that, the AuthenticationManager is used to try to authenticate the user.
* Then, the SecurityContext authentication is set to the result of that authentication.
