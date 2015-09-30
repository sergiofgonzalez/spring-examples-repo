ISF Picture Worker
==================

The ISF Picture Worker is a Java application hosted on an EC2 machine that acts as a Java wrapper for the Automatic Recognition Tool.

The application is built on top of Spring Boot as a *command line runner* and leverages the Cloud Storage system and Distributed Queue infrastructure provided by Amazon through `asw-cloud` dependency.


# Usage Notes

To run the application use:
`mvn spring-boot:run`