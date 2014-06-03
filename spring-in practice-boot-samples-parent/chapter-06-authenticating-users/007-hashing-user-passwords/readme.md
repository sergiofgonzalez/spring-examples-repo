# 007-hashing-user-passwords

This is the seventh example of SiP Chapter 6 - Authenticating Users:
    Secure user passwords in the database.
It has been migrated to Spring Boot and Java Config.				

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
