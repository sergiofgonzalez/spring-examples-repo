# 004-method-authorization-using-acls

This is the fourth example of SiP Chapter 7 - Authorizing User Requests:
    Authorizing method invocations based on ACLs.
    
It has been migrated to Spring Boot and Java Config.

## Functionality
This example is based on 004-securing-method-resources and adds view authorization based on domain model objects. For example, in 004 a user can access the Edit Message view (e.g. daniel), but the action is denied after having modified it. In this example, that option is not even enabled if the user lacks that permission.

Allow
    the author
    the forum moderator
    the site admin       to edit a message after it's been posted
and
    No one else.


## Components (added/modified)

### message.jsp
The view has been modified to change the <security:authorize> that handles the message management with <security:accesscontrollist> which allows for protection of JSP sections based on domain model object authorization.
