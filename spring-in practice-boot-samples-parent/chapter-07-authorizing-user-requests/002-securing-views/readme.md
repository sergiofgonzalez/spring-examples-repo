# 002-securing-views

This is the second example of SiP Chapter 7 - Authorizing User Requests:
    Authorizing JSP views using authentication levels, roles and permissions.
    
It has been migrated to Spring Boot and Java Config.
       

## Functionality
This example is based on 001-securing-service-methods and adds more control on view display based on roles and permissions.
Certains parts of the application are displayed or not using <security:authorize> tags with the following variants:
* access="hasRole('PERM_*')" to check for permissions
* access="hasRole('ROLE_*')" to check for roles (admin, user, etc.)
* access="isAuthenticated()"/"isAnonymous()" to check whether the user has been authenticated or not
