SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

INSERT INTO `account` 
  (`username`, `first_name`, `last_name`, `email`, `password`, `marketing_ok`, `accept_terms`, `enabled`) 
VALUES
  ('juan', 'Juan', 'Cazares', 'juan.cazares@example.com', 'p@ssword', 1, 1, 1),
  ('elvira', 'Elvira', 'Cazares', 'elvira.cazares@example.com', 'p@ssword', 1, 1, 1);

INSERT INTO `role`
  (`name`)
VALUES
  ('user'),
  ('admin');
  
INSERT INTO `account_role`
  (`account_id`, `role_id`)
VALUES
  ((SELECT `id` FROM `account` WHERE `username` = 'juan'), (SELECT `id` FROM `role` WHERE `name` = 'user')),
  ((SELECT `id` FROM `account` WHERE `username` = 'juan'), (SELECT `id` FROM `role` WHERE `name` = 'admin')),
  ((SELECT `id` FROM `account` WHERE `username` = 'elvira'), (SELECT `id` FROM `role` WHERE `name` = 'user'));

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;