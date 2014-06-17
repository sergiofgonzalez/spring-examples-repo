SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0//
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0//

-- Create Accounts
call createAccount('paula', 'Paula', 'Cazares', 'paula.cazares@example.com', @paula)//
call createAccount('juan', 'Juan', 'Cazares', 'juan.cazares@example.com', @juan)//
call createAccount('elvira', 'Elvira', 'Cazares', 'elvira.cazares@example.com', @elvira)//
call createAccount('daniel', 'Daniel', 'Cazares', 'daniel.cazares@example.com', @daniel)//
call createAccount('julia', 'Julia', 'Cazares', 'julia.cazares@example.com', @julia)//

-- Create Roles
call createRole('user', @role_user)//
call createRole('admin', @role_admin)//
call createRole('student', @role_student)//
call createRole('faculty', @role_faculty)//
  

-- Create Permissions
call createPermission('PERM_CREATE_ACCOUNTS')//
call createPermission('PERM_READ_ACCOUNTS')//
call createPermission('PERM_UPDATE_ACCOUNTS')//
call createPermission('PERM_DELETE_ACCOUNTS')//
call createPermission('PERM_ADMIN_ACCOUNTS')//

call createPermission('PERM_CREATE_FORUMS')//
call createPermission('PERM_READ_FORUMS')//
call createPermission('PERM_UPDATE_FORUMS')//
call createPermission('PERM_DELETE_FORUMS')//
call createPermission('PERM_ADMIN_FORUMS')//

call createPermission('PERM_CREATE_MESSAGES')//
call createPermission('PERM_READ_MESSAGES')//
call createPermission('PERM_UPDATE_MESSAGES')//
call createPermission('PERM_DELETE_MESSAGES')//
call createPermission('PERM_ADMIN_MESSAGES')//

-- Assign Permissions to Roles
call assignPermissionToRole('PERM_READ_ACCOUNTS', @role_user)//
call assignPermissionToRole('PERM_READ_FORUMS', @role_user)//
call assignPermissionToRole('PERM_CREATE_MESSAGES', @role_user)//
call assignPermissionToRole('PERM_READ_MESSAGES', @role_user)//

call assignPermissionToRole('PERM_UPDATE_MESSAGES', @role_admin)//
call assignPermissionToRole('PERM_DELETE_MESSAGES', @role_admin)//
call assignPermissionToRole('PERM_ADMIN_MESSAGES', @role_admin)//


-- Assign Roles to User
call assignRoleToUser(@role_user, @paula)//

call assignRoleToUser(@role_user, @daniel)//
call assignRoleToUser(@role_student, @daniel)//

call assignRoleToUser(@role_user, @julia)//
call assignRoleToUser(@role_faculty, @paula)//

call assignRoleToUser(@role_user, @elvira)//
call assignRoleToUser(@role_student, @elvira)//
call assignRoleToUser(@role_faculty, @elvira)//

call assignRoleToUser(@role_user, @juan)//
call assignRoleToUser(@role_admin, @juan)//

-- ----------------
-- BUSINESS DATA --
-- ----------------
call createForum('Algebra I', @julia, @forum)//
call createMessage(@forum, @julia, '2013-09-28 12:34:03', 'What *is* a variable?')//

call createForum('Algebra II/Trigonometry', @julia, @forum)//
call createMessage(@forum, @julia, '2013-09-29 04:01:39', 'now i know how tall that pyramid is!')//

call createForum('Calculus II', @elvira, @forum)//
call createMessage(@forum, @elvira, '2013-09-27 12:34:56', 'Relationship between differentiation and integration')//
call createMessage(@forum, @daniel, '2013-09-30 12:43:45', 'Integrating a volume')//

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS//
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS//