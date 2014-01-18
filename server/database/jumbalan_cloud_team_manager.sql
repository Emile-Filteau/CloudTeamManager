-- phpMyAdmin SQL Dump
-- version 3.4.11.1
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Sam 18 Janvier 2014 à 12:30
-- Version du serveur: 5.5.35
-- Version de PHP: 5.2.17

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `jumbalan_cloud_team_manager`
--

-- --------------------------------------------------------

--
-- Structure de la table `calendar_events`
--

DROP TABLE IF EXISTS `calendar_events`;
CREATE TABLE IF NOT EXISTS `calendar_events` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `event_type` int(11) DEFAULT NULL,
  `START_DATE` bigint(40) DEFAULT NULL,
  `END_DATE` bigint(40) DEFAULT NULL,
  `name` text NOT NULL,
  `description` text NOT NULL,
  `priority` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `event_type` (`event_type`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=97 ;

--
-- Contenu de la table `calendar_events`
--

INSERT INTO `calendar_events` (`id`, `user_id`, `event_type`, `START_DATE`, `END_DATE`, `name`, `description`, `priority`) VALUES
(89, 10, 0, 1390037366790, 1390044566790, 'Soupe chez bob', '', 0),
(90, 10, 0, 1390037864756, 1390045064755, 'Soupe2 chez bob', '', 0),
(91, 10, 0, 1390089600000, 1390176000000, 'Allooooo', '', 0),
(92, 10, 0, 1390395600000, 1390399200000, 'Prout prout', '', 0),
(93, 10, 0, 1390482000000, 1390485600000, 'Allooooo emileeeee', '', 0),
(94, 10, 0, 1391173200000, 1391176800000, 'Yfsgjk', '', 0),
(95, 10, 0, 1391778000000, 1391781600000, 'Ifssssssssss', '', 0),
(96, 10, 0, 1391817600000, 1391904000000, 'Hfgkkkkkkkk', '', 0);

-- --------------------------------------------------------

--
-- Structure de la table `event_type`
--

DROP TABLE IF EXISTS `event_type`;
CREATE TABLE IF NOT EXISTS `event_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

-- --------------------------------------------------------

--
-- Structure de la table `meetings`
--

DROP TABLE IF EXISTS `meetings`;
CREATE TABLE IF NOT EXISTS `meetings` (
  `id` int(11) NOT NULL,
  `team_id` int(11) NOT NULL,
  `start_date` int(11) NOT NULL,
  `end_date` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `team_id` (`team_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `task`
--

DROP TABLE IF EXISTS `task`;
CREATE TABLE IF NOT EXISTS `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `team_id` int(11) NOT NULL,
  `member_id` int(11) DEFAULT NULL,
  `estimated_time` int(11) DEFAULT NULL,
  `took_time` int(11) DEFAULT NULL,
  `name` text NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `member_id` (`member_id`),
  KEY `team_id` (`team_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

--
-- Contenu de la table `task`
--

INSERT INTO `task` (`id`, `team_id`, `member_id`, `estimated_time`, `took_time`, `name`, `status`) VALUES
(12, 7, 10, 14, 0, 'manger des pommes', 1),
(13, 7, 7, 8, 0, 'googler maman dion', 1),
(16, 7, 7, 14, 0, 'tedt', 1),
(17, 7, 8, 2, 0, 'allo', 1),
(18, 19, 7, 3, 0, 'Faire rapport TP 2', 1),
(19, 19, 10, 1, 0, 'Coder module X', 1),
(20, 20, 7, 5, 0, 'TP1 - Code', 1),
(21, 20, 11, 4, 0, 'TP1 - Rapport', 1),
(22, 21, 7, 20, 0, 'Backend', 1);

-- --------------------------------------------------------

--
-- Structure de la table `teams`
--

DROP TABLE IF EXISTS `teams`;
CREATE TABLE IF NOT EXISTS `teams` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Contenu de la table `teams`
--

INSERT INTO `teams` (`id`, `name`) VALUES
(7, 'A'),
(19, 'LOG330'),
(20, 'LOG430'),
(21, 'Applet Mobile Challenge');

-- --------------------------------------------------------

--
-- Structure de la table `team_members`
--

DROP TABLE IF EXISTS `team_members`;
CREATE TABLE IF NOT EXISTS `team_members` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `team_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `team_id` (`team_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=41 ;

--
-- Contenu de la table `team_members`
--

INSERT INTO `team_members` (`id`, `user_id`, `team_id`) VALUES
(7, 7, 7),
(9, 7, 7),
(11, 11, 7),
(22, 10, 7),
(26, 11, 7),
(28, 8, 7),
(29, 8, 7),
(30, 8, 7),
(35, 10, 19),
(36, 7, 19),
(37, 7, 20),
(38, 10, 20),
(39, 11, 20),
(40, 7, 21);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` text NOT NULL,
  `password` text NOT NULL,
  `email` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=17 ;

--
-- Contenu de la table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`) VALUES
(7, 'Emile', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 'emile.filteau@gmail.com'),
(8, 'Bob', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 'aaa@aaa.com'),
(10, 'Ark', 'a253ae6edd2b702a96ee29f15b31d422ffc5e7b5', 'ma_paquin@hotmail.com'),
(11, 'Adam', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 'test'),
(12, 'Gino', '40bd001563085fc35165329ea1ff5c5ecbdbbeef', 'ginochouinard@bbq.net'),
(15, 'test', 'a94a8fe5ccb19ba61c4c0873d391e987982fbbd3', 'test');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `calendar_events`
--
ALTER TABLE `calendar_events`
  ADD CONSTRAINT `calendar_events_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `task_ibfk_1` FOREIGN KEY (`member_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `task_ibfk_2` FOREIGN KEY (`team_id`) REFERENCES `teams` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `team_members`
--
ALTER TABLE `team_members`
  ADD CONSTRAINT `team_members_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `team_members_ibfk_2` FOREIGN KEY (`team_id`) REFERENCES `teams` (`id`) ON DELETE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
