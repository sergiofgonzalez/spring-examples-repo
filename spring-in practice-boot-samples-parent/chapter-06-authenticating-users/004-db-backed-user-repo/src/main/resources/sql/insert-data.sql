SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('juan', 'p@ssword', 1);
INSERT INTO `users` (`username`, `password`, `enabled`) VALUES ('elvira', 'p@ssword', 1);

INSERT INTO `authorities` (`username`, `authority`) VALUES ('juan', 'user');
INSERT INTO `authorities` (`username`, `authority`) VALUES ('juan', 'admin');
INSERT INTO `authorities` (`username`, `authority`) VALUES ('elvira', 'user');

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;