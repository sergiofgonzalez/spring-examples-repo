SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0//
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0//

DROP TABLE IF EXISTS `account`//

CREATE TABLE `account` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`username` VARCHAR(50) NOT NULL,
	`first_name` VARCHAR(50) NOT NULL,
	`last_name` VARCHAR(50) NOT NULL,
	`email` VARCHAR(50) NOT NULL,
	`password` VARCHAR(80) NULL DEFAULT NULL,
	`marketing_ok` TINYINT(1) NOT NULL,
	`accept_terms` TINYINT(1) NOT NULL,
	`enabled` TINYINT(1) NOT NULL,
	`date_created` TIMESTAMP NOT NULL DEFAULT 0,
	`date_modified` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `username` (`username`),
	UNIQUE INDEX `account_idx_1` (`username`),
	UNIQUE INDEX `account_idx_2` (`email`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB//

DROP TABLE IF EXISTS `role`//

CREATE TABLE `role` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `name` (`name`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB//

DROP TABLE IF EXISTS `permission`//

CREATE TABLE `permission` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `name` (`name`)	
)
COLLATE='utf8_bin'
ENGINE=InnoDB//

DROP TABLE IF EXISTS `account_role`//

CREATE TABLE `account_role` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`account_id` BIGINT(20) UNSIGNED NOT NULL,
	`role_id` BIGINT(20) UNSIGNED NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `account_role_idx_1` (`account_id`, `role_id`),
	INDEX `role_id` (`role_id`),
	CONSTRAINT `account_role_fk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
	CONSTRAINT `account_role_fk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB//

DROP TABLE IF EXISTS `role_permission`//

CREATE TABLE `role_permission` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`role_id` BIGINT(20) UNSIGNED NOT NULL,
	`permission_id` BIGINT(20) UNSIGNED NOT NULL,
	PRIMARY KEY (`id`),
	UNIQUE INDEX `role_permission_idx_1` (`role_id`, `permission_id`),
	CONSTRAINT `role_permission_fk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
	CONSTRAINT `role_permission_fk_2` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB//

DROP TABLE IF EXISTS `forum`//

CREATE TABLE `forum` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` varchar(250) NOT NULL,
    `owner_id` BIGINT(20) UNSIGNED NOT NULL,
    `date_created` TIMESTAMP DEFAULT 0,
    `date_modified` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `forum_idx_1` (`name`),
    CONSTRAINT `forum_fk_1` FOREIGN KEY (`owner_id`) REFERENCES `account` (`id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB//

DROP TABLE IF EXISTS `message`//

CREATE TABLE `message` (
	`id` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,
	`forum_id` BIGINT(20) UNSIGNED NOT NULL,
	`subject` varchar(250) NOT NULL,
    `author_id` BIGINT(20) UNSIGNED NOT NULL,
    `text` TEXT(8000) NOT NULL,
    `visible` TINYINT NOT NULL DEFAULT 1,
    `date_created` TIMESTAMP DEFAULT 0,
    `date_modified` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    INDEX `message_idx_1` (`date_created`),
    CONSTRAINT `message_fk_1` FOREIGN KEY (`forum_id`) REFERENCES `forum` (`id`),
    CONSTRAINT `message_fk_2` FOREIGN KEY (`author_id`) REFERENCES `account` (`id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB//

-- ---------------------------
-- ACL tables               --
-- (http://docs.spring.io/spring-security/site/docs/3.2.4.RELEASE/reference/htmlsingle/#dbschema-acl)
-- ---------------------------

DROP TABLE IF EXISTS acl_sid//

CREATE TABLE acl_sid (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    principal BOOLEAN NOT NULL,
    sid VARCHAR(100) NOT NULL,
    UNIQUE KEY unique_acl_sid (sid, principal)
)
COLLATE='utf8_bin'
ENGINE=InnoDB//

DROP TABLE IF EXISTS acl_class//

CREATE TABLE acl_class (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    class VARCHAR(100) NOT NULL,
    UNIQUE KEY uk_acl_class (class)
)
COLLATE='utf8_bin'
ENGINE=InnoDB//

DROP TABLE IF EXISTS acl_object_identity//

CREATE TABLE acl_object_identity (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    object_id_class BIGINT UNSIGNED NOT NULL,
    object_id_identity BIGINT NOT NULL,
    parent_object BIGINT UNSIGNED,
    owner_sid BIGINT UNSIGNED,
    entries_inheriting BOOLEAN NOT NULL,
    UNIQUE KEY uk_acl_object_identity (object_id_class, object_id_identity),
    CONSTRAINT fk_acl_object_identity_parent FOREIGN KEY (parent_object) REFERENCES acl_object_identity (id),
    CONSTRAINT fk_acl_object_identity_class FOREIGN KEY (object_id_class) REFERENCES acl_class (id),
    CONSTRAINT fk_acl_object_identity_owner FOREIGN KEY (owner_sid) REFERENCES acl_sid (id)
)
COLLATE='utf8_bin'
ENGINE=InnoDB//

DROP TABLE IF EXISTS acl_entry//

CREATE TABLE acl_entry (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    acl_object_identity BIGINT UNSIGNED NOT NULL,
    ace_order INTEGER NOT NULL,
    sid BIGINT UNSIGNED NOT NULL,
    mask INTEGER UNSIGNED NOT NULL,
    granting BOOLEAN NOT NULL,
    audit_success BOOLEAN NOT NULL,
    audit_failure BOOLEAN NOT NULL,
    UNIQUE KEY unique_acl_entry (acl_object_identity, ace_order),
    CONSTRAINT fk_acl_entry_object FOREIGN KEY (acl_object_identity) REFERENCES acl_object_identity (id),
    CONSTRAINT fk_acl_entry_acl FOREIGN KEY (sid) REFERENCES acl_sid (id)
)
COLLATE='utf8_bin'
ENGINE=InnoDB//

-- ---------------------------
-- Helper Stored Procedures --
-- ---------------------------

DROP PROCEDURE IF EXISTS `createAccount`//
CREATE PROCEDURE createAccount($name varchar(50), $first_name varchar(50), $last_name varchar(50), $email varchar(50), out $id BIGINT)
BEGIN
	INSERT INTO `account` 
  		(`username`, `first_name`, `last_name`, `email`, `password`, `marketing_ok`, `accept_terms`, `enabled`, `date_created`) 
	VALUES
  		($name, $first_name, $last_name, $email, 'p@ssword', 1, 1, 1, CURRENT_TIMESTAMP);
  	SET $id := last_insert_id();
    INSERT INTO acl_sid (principal, sid) VALUES (1, $name);
END//

DROP PROCEDURE IF EXISTS `createRole`//
CREATE PROCEDURE createRole($name varchar(50), out $id BIGINT) 
BEGIN
	INSERT INTO `role` (`name`) VALUES ($name);
	SET $id := last_insert_id();
    INSERT INTO acl_sid (principal, sid) VALUES (0, $name);
END//

DROP PROCEDURE IF EXISTS `createPermission`//
CREATE PROCEDURE createPermission($name varchar(50)) 
BEGIN
	INSERT INTO `permission` (`name`) VALUES ($name);	
END//

DROP PROCEDURE IF EXISTS `assignPermissionToRole`//
CREATE PROCEDURE `assignPermissionToRole`($permission_name varchar(50), $role_id BIGINT)
BEGIN
	DECLARE PERMISSION_ID BIGINT;
	SELECT `id` INTO PERMISSION_ID FROM `permission` WHERE `name` = $permission_name;
	INSERT INTO `role_permission` (`role_id`, `permission_id`) VALUES ($role_id, PERMISSION_ID);
END//

DROP PROCEDURE IF EXISTS `assignRoleToUser`//
CREATE PROCEDURE `assignRoleToUser`($role_id BIGINT, $account_id BIGINT)
BEGIN
	INSERT INTO `account_role` (`role_id`, `account_id`) VALUES ($role_id, $account_id);
END//

DROP PROCEDURE IF EXISTS `createForum`//
CREATE PROCEDURE createForum($name VARCHAR(250), $owner_id BIGINT, out $id BIGINT)
BEGIN
    DECLARE _SITE_OID BIGINT;
    DECLARE _OWNER_SID BIGINT;
    DECLARE _FORUM_OID BIGINT;

	INSERT INTO `forum` (`name`, `owner_id`) VALUES ($name, $owner_id);
	set $id := last_insert_id();

    SELECT s.id
      INTO _OWNER_SID
    FROM   account a, acl_sid s 
    WHERE  a.id = $owner_id and a.username = s.sid;

    INSERT INTO acl_object_identity 
        (object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting)
    VALUES
        (@forum_class, $id, @site_oid, _OWNER_SID, 1);

    SET _FORUM_OID := last_insert_id();

    INSERT INTO acl_entry (acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure)
    VALUES
        (_FORUM_OID, 0, _OWNER_SID, 1, 1, 0, 0),  -- MODERATOR can READ
        (_FORUM_OID, 1, _OWNER_SID, 2, 1, 0, 0),  -- MODERATOR can WRITE
        (_FORUM_OID, 2, _OWNER_SID, 4, 1, 0, 0),  -- MODERATOR can CREATE
        (_FORUM_OID, 3, _OWNER_SID, 8, 1, 0, 0),  -- MODERATOR can DELETE
        (_FORUM_OID, 4, _OWNER_SID, 16, 1, 0, 0); -- MODERATOR can ADMIN

END//

DROP PROCEDURE IF EXISTS `createMessage`//
CREATE PROCEDURE createMessage($forum_id BIGINT, $author_id BIGINT, $create_date TIMESTAMP, $subject VARCHAR(250))
BEGIN
    DECLARE _ID BIGINT;
    DECLARE _FORUM_OID BIGINT;
    DECLARE _MESSAGE_OID BIGINT;
    DECLARE _AUTHOR_SID BIGINT;

	INSERT INTO `message`
		(`forum_id`, `subject`, `author_id`, `date_created`, `text`)
	VALUES
		($forum_id, $subject, $author_id, $create_date, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris in odio ligula. Aliquam massa magna, auctor eget viverra eget, euismod nec dolor. Quisque suscipit feugiat ipsum a porttitor. Fusce dolor lectus, accumsan ut faucibus et, elementum eget leo. Curabitur sodales dui fringilla mi pretium faucibus. Praesent nulla dolor, iaculis vel tempus eu, venenatis consequat ipsum. Nunc eros lorem, interdum non fringilla eu, lobortis at nulla. Vivamus eu ligula at quam adipiscing pellentesque. Praesent vitae erat sit amet felis eleifend egestas ut vel leo. Phasellus ultrices dui ut odio condimentum tristique. Sed ultricies justo at turpis tempus semper. Nulla consequat libero ut nunc facilisis viverra. Fusce molestie pulvinar varius. Vestibulum luctus nisl urna. Nam bibendum feugiat enim, faucibus mollis elit vehicula fermentum.</p><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris in odio ligula. Aliquam massa magna, auctor eget viverra eget, euismod nec dolor. Quisque suscipit feugiat ipsum a porttitor. Fusce dolor lectus, accumsan ut faucibus et, elementum eget leo. Curabitur sodales dui fringilla mi pretium faucibus. Praesent nulla dolor, iaculis vel tempus eu, venenatis consequat ipsum. Nunc eros lorem, interdum non fringilla eu, lobortis at nulla. Vivamus eu ligula at quam adipiscing pellentesque. Praesent vitae erat sit amet felis eleifend egestas ut vel leo. Phasellus ultrices dui ut odio condimentum tristique. Sed ultricies justo at turpis tempus semper. Nulla consequat libero ut nunc facilisis viverra. Fusce molestie pulvinar varius. Vestibulum luctus nisl urna. Nam bibendum feugiat enim, faucibus mollis elit vehicula fermentum');
    SET _ID := last_insert_id();        

    SELECT id 
      INTO _FORUM_OID
      FROM acl_object_identity
    WHERE   object_id_class = @forum_class and object_id_identity = $forum_id;

    SELECT s.id
    INTO   _AUTHOR_SID
    FROM   account a, acl_sid s
    WHERE  a.id = $author_id and a.username = s.sid;

    SET _MESSAGE_OID := last_insert_id();
    INSERT INTO acl_object_identity (object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting)
    VALUES (@message_class, _ID, _FORUM_OID, _AUTHOR_SID, 1);
    SET _MESSAGE_OID := last_insert_id();

    INSERT INTO acl_entry (acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure)
    VALUES (_MESSAGE_OID, 0, _AUTHOR_SID, 2, 1, 0, 0); -- AUTHOR can WRITE        
END//

DROP PROCEDURE IF EXISTS createSite//
CREATE PROCEDURE createSite(out $site_oid INT)
BEGIN
    DECLARE _ROLE_ADMIN_SID BIGINT;
    DECLARE _ROLE_USER_SID BIGINT;

    SELECT id
      INTO _ROLE_ADMIN_SID
      FROM acl_sid
    WHERE  sid = 'ROLE_ADMIN';

    SELECT id
      INTO _ROLE_USER_SID
      FROM acl_sid
    WHERE  sid = 'ROLE_USER';

    INSERT INTO acl_object_identity 
        (object_id_class, object_id_identity, owner_sid, entries_inheriting)
    VALUES
        (@site_class, 1, _ROLE_ADMIN_SID, 0);
    SET $site_oid := last_insert_id();

    INSERT INTO acl_entry (acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure)
    VALUES  
        ($site_oid, 0, _ROLE_USER_SID, 1, 1, 0, 0),   -- USER  can READ
        ($site_oid, 1, _ROLE_ADMIN_SID, 1, 1, 0, 0),  -- ADMIN can READ
        ($site_oid, 2, _ROLE_ADMIN_SID, 2, 1, 0, 0),  -- ADMIN can WRITE
        ($site_oid, 3, _ROLE_ADMIN_SID, 4, 1, 0, 0),  -- ADMIN can CREATE
        ($site_oid, 4, _ROLE_ADMIN_SID, 8, 1, 0, 0),  -- ADMIN can DELETE
        ($site_oid, 5, _ROLE_ADMIN_SID, 16, 1, 0, 0); -- ADMIN can ADMIN
END//

DROP PROCEDURE IF EXISTS createAclClass//
CREATE PROCEDURE createAclClass($name VARCHAR(100), out $id BIGINT)
BEGIN
    INSERT INTO acl_class (class) VALUES ($name);
    set $id := last_insert_id();
END//

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS//
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS//