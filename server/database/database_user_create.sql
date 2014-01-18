DROP DATABASE `cloud_team_manager`;
CREATE DATABASE `cloud_team_manager` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `cloud_team_manager`;

DROP USER 'ctm_user'@'localhost';
CREATE USER 'ctm_user'@'localhost' IDENTIFIED BY 'asfa43fs42rtwsd';
GRANT ALL ON cloud_team_manager.* TO 'ctm_user'@'%';