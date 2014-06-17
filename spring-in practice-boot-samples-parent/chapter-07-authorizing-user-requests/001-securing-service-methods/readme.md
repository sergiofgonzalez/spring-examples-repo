# 001-securing-service-methods

This is the first example of SiP Chapter 7 - Authorizing User Requests:
    Authorizing Java Methods using authentication levels, roles and permissions.
    
It has been migrated to Spring Boot and Java Config.
        

## Functionality
This example implements a web application with forums in which users can post their messages, edit them, etc. The application leverages Spring Security to protect the execution of services.
Therefore, an underlying structure of accounts-roles-permissions is established.
An account is given a role, which inherits a set of fine-grained business permissions.

For example:
* The User role is given permissions to read and create messages, but cannot delete them or edit them.

The securization of methods is implemented at service level, using annotations, and there is no control established on the views as a whole. Therefore, a user can access the edit message page, although it will fail with an ugly error message when user clicks on "Save Message". 

## Known Gaps
* There is no custom page for errors: therefore, expect some ugly page when a user without the proper authorization tries to perform a protected action.
* There is no JavaScript in place: some interesting alternatives have been used to solve situations such as the deletion confirmation. The original AJAX controllers have been changed for regular controllers.
* Only some strings have been taken to the i18n folder properties file.