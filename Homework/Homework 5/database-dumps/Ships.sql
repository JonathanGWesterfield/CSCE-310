-- phpMyAdmin SQL Dump
-- version 4.7.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 23, 2019 at 11:55 PM
-- Server version: 5.6.35
-- PHP Version: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `Ships`
--

-- --------------------------------------------------------

--
-- Table structure for table `battles`
--

CREATE TABLE `battles` (
  `name` varchar(20) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `battles`
--

INSERT INTO `battles` (`name`, `date`) VALUES
('North Atlantic', '5/24-27/41'),
('Guadalcanal', '11/15/42'),
('North Cape', '12/16/43'),
('Surigao Strait', '10/25/44');

-- --------------------------------------------------------

--
-- Table structure for table `classes`
--

CREATE TABLE `classes` (
  `class` varchar(20) DEFAULT NULL,
  `type` varchar(2) DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  `numGuns` int(11) DEFAULT NULL,
  `bore` int(11) DEFAULT NULL,
  `disp` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `classes`
--

INSERT INTO `classes` (`class`, `type`, `country`, `numGuns`, `bore`, `disp`) VALUES
('Bismark', 'bb', 'Germany', 8, 15, 42000),
('Iowa', 'bb', 'USA', 9, 16, 46000),
('Kongo', 'bc', 'Japan', 8, 14, 32000),
('North Carolina', 'bb', 'USA', 9, 16, 37000),
('Renown', 'bc', 'Gt. Britian', 6, 15, 32000),
('Revenge', 'bb', 'Gt. Britian', 8, 15, 29000),
('Tennessee', 'bb', 'USA', 12, 14, 32000),
('Yamato', 'bb', 'Japan', 9, 18, 65000);

-- --------------------------------------------------------

--
-- Table structure for table `outcomes`
--

CREATE TABLE `outcomes` (
  `ship` varchar(20) DEFAULT NULL,
  `battle` varchar(20) DEFAULT NULL,
  `result` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `outcomes`
--

INSERT INTO `outcomes` (`ship`, `battle`, `result`) VALUES
('Bismark', 'North Atlantic', 'sunk'),
('California', 'Surigao Strait', 'ok'),
('Duke of York', 'North Cape', 'ok'),
('Fuso', 'Surigao Strait', 'sunk'),
('Hood', 'North Atlantic', 'sunk'),
('King George V', 'North Atlantic', 'ok'),
('Kirishima', 'Guadalcanal', 'sunk'),
('Prince of Wales', 'North Atlantic', 'damaged'),
('Rodney', 'North Atlantic', 'ok'),
('Scharnhorst', 'North Cape', 'sunk'),
('South Dakota', 'Guadalcanal', 'damaged'),
('Tennessee', 'Surigao Strait', 'ok'),
('Washington', 'Guadalcanal', 'ok'),
('West Virginia', 'Surigao Strait', 'ok'),
('Yamashiro', 'Surigao Strait', 'sunk');

-- --------------------------------------------------------

--
-- Table structure for table `ships`
--

CREATE TABLE `ships` (
  `name` varchar(20) DEFAULT NULL,
  `class` varchar(20) DEFAULT NULL,
  `launched` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `ships`
--

INSERT INTO `ships` (`name`, `class`, `launched`) VALUES
('California', 'Tennessee', 1921),
('Haruna', 'Kongo', 1915),
('Hiei', 'Kongo', 1914),
('Iowa', 'Iowa', 1943),
('Kirishima', 'Kongo', 1915),
('Kongo', 'Kongo', 1913),
('Missouri', 'Iowa', 1944),
('Musashi', 'Yamato', 1942),
('New Jersey', 'Iowa', 1943),
('North Carolina', 'North Carolina', 1941),
('Ramilies', 'Revenge', 1917),
('Renown', 'Renown', 1916),
('Repulse', 'Renown', 1916),
('Resolution', 'Revenge', 1916),
('Revenge', 'Revenge', 1916),
('Royal Oak', 'Revenge', 1916),
('Royal Sovereign', 'Revenge', 1916),
('Tennessee', 'Tennessee', 1920),
('Washington', 'North Carolina', 1941),
('Wisconsin', 'Iowa', 1944),
('Yamato', 'Yamato', 1941);

