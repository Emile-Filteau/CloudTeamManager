DROP DATABASE `cloud_team_manager`;
CREATE DATABASE `cloud_team_manager` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `cloud_team_manager`;

DROP USER 'cloud_team_manager_user'@'localhost';
CREATE USER 'cloud_team_manager_user'@'localhost' IDENTIFIED BY 'asfa43fs42rtwsd';
GRANT ALL ON cloud_team_manager.* TO 'cloud_team_manager_user'@'%';