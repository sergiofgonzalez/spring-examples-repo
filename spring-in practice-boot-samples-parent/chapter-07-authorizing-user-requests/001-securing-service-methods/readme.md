# 001-securing-service-methods

This is the first example of SiP Chapter 7 - Authorizing User Requests:
    Authorizing Java Methods using authentication levels, roles and permissions.
    
It has been migrated to Spring Boot and Java Config.
        
See readme.md for the complete details.				

# Progress

* 2014-06-11: Created the scaffolding. Home page and login page. Users can login/logout. Password Encoder has been disabled.

# ToDO

* (med)  Clean all the urls (make variables for them) in JSPs
* (med)  Get the messages ordered by date, Forums by Name
* (med)  Add some JavaScript to enable some dynamic behavior in jsps
* (med)  Externalize all strings into resource bundle properties files
* (med)  Integrate the forum functionality
* (low)  Create a Registration form and re-enable the password encoder


## Functionality
Based on 006-, demonstrates how to use password salting and hashing to prevent anybody from viewing user passwords in the database.

In this example, the user can register accounts independently of whether it has been authenticated or not.

## Components (added/changed over 005-)

### SecurityConfig.java
The Security Configuration class has been updated to include the password encoder that performs the hashing and salting of the password. First, the PasswordEncoder bean is defined as an instance of StandardPasswordEncoder, which generates a 80-bytes length hashed and salted password.
Then, the password encoder is configured into the Authentication Manager.

### AccountRepositoryImpl.java
The AccountRepositoryImpl is updated to perform the hashing and salting of the password prior to storing it in the database.

### sql/create-schema.sq
The SQL script for the creation of the tables is modified to allow 80-bytes length in the password field of the acount table.

### sql/insert-data.sq
The insertion of users and passwords has been removed, as the password has to be encoded using the StandardPasswordEncoder algorithm.

### navbar.jspf
Slightly modified to allow the registration of new accounts when the user is not authenticated.
