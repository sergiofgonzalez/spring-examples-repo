# 004-method-authorization-using-acls

This is the fourth example of SiP Chapter 7 - Authorizing User Requests:
    Authorizing method invocations based on ACLs.
    
It has been migrated to Spring Boot and Java Config.

## Functionality
This example is based on 003-securing-web-resources and adds ACL functionality. Using ACLs you can implement fine-grained authorization capabilities using domain object model.
In this first ACL example we're implementing the following requirements:

Allow
    the author
    the forum moderator
    the site admin       to edit a message after it's been posted
and
    No one else.


## Components (added/modified)

### About ACL Schema

ACL Functionality is based on four tables:
* ACL_SID: table that stores the actors and roles so that they can be referenced in the other tables.
* ACL_CLASS: the domain object types to which authorization will be applied.
* ACL_OBJECT_IDENTITY: the domain object instances. This table includes inheritance capabilities so that you don't have to include all the domain objects. For example, we define a "site" from which Forums inherit that will be used to set the basic authorization rules.
* ACL_ENTRY: the authorization rules to apply.

### AclSecurityConfig.java
This class handles the creation of all the beans needed to enable ACL configuration.
First of all, this class defines an EhCache CacheManager which creates a cache region named `aclCache`.

Then, it is created an EhCacheBasedAclCache that is used to handle ACL resolution using EhCache.

After that, it is created an AclAuthorizationStrategy and the AclAutorizationStrategyImpl is used as the implementation, with the ROLE_ADMIN identified as the authority that can manage the ACLs.

Then, the LookUpStrategy bean is defined based to configure how the ACLs will be resolved. In our case, they will be based on the four tables discussed above with an EhCaching strategy.

For changing the ACL rules, we define the MutableAclService that will be implemented using JDBC.

Then, the strategy used to evaluate permissions is defined using PermissionEvaluator.

The final required bean is the MethodSecurityExpressionHandler.

### SecurityConfig.java
The GlobalSecurityConfiguration static class has been modified to include an override of the createExpressionHandler() method. 

### ForumsService.java
This class is updated to include all the ACL authorization functionality.
* getForums(): it is included a @PostFilter annotation to control that the forums are not displayed for users that do not have the read permissions on forums.
* getForum(): it is included a @PostFilter annotation to control that a particular Forum is not displayed for a user that do not have read permission on that forum.
* getMessage(): It is included a @PostFilter annotation to filter out messages based on ACL rules. In particular, the following rules is applied: A message is displayed if the message has READ permission for the user and the message is visible, or if the user has admin permissions.
* createMessage(): after the message has been saved, the ACL entries are updated by creating a rule for the newly created message.
* updateMessageSubjectAndText(): the @PreAuthorize condition has been updated to leverage the ACLs. This method will be authorized in the user has WRITE permissions for that message, and the message is visible, or if the user has ADMIN privileges over that message. Then, inside the method when the message is updated the ACLs are also updated to reflect that.
* updateMessageVisibility(): the @PreAuthorize condition has been updated to leverage ACLs. After the message has been updated, the ACLs are also updated to reflect the change.
* deleteMessage(): the @PreAuthorize condition has been updated to leverage ACLs. After the message has been deleted, the ACLs are also deleted to reflect the change.

The class also creates several helper methods to facilitate the creation, update and deletion of ACLs.

### home.jsp
This view has been modified to include what basic tests can be performed.

### pom.xml
The POM has been modified to include dependency with Eh Cache.