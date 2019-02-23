-- phpMyAdmin SQL Dump
-- version 4.7.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 23, 2019 at 11:52 PM
-- Server version: 5.6.35
-- PHP Version: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Database: `Computers`
--
CREATE DATABASE IF NOT EXISTS `Computers` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `Computers`;

-- --------------------------------------------------------

--
-- Table structure for table `laptop`
--

CREATE TABLE `laptop` (
  `model` char(4) DEFAULT NULL,
  `speed` double DEFAULT NULL,
  `ram` int(11) DEFAULT NULL,
  `hd` int(11) DEFAULT NULL,
  `screen` decimal(3,1) DEFAULT NULL,
  `price` decimal(4,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `laptop`
--

INSERT INTO `laptop` (`model`, `speed`, `ram`, `hd`, `screen`, `price`) VALUES
('2001', 2, 2048, 240, '20.1', '3673'),
('2002', 1.73, 1024, 80, '17.0', '949'),
('2003', 1.8, 512, 60, '15.4', '549'),
('2004', 2, 512, 60, '13.3', '1150'),
('2005', 2.16, 1024, 120, '17.0', '2500'),
('2006', 2, 2018, 60, '15.4', '1700'),
('2007', 1.83, 1024, 120, '13.3', '1429'),
('2008', 1.6, 1024, 100, '15.4', '900'),
('2009', 1.6, 512, 80, '14.1', '680'),
('2010', 2, 2048, 160, '15.4', '2300');

-- --------------------------------------------------------

--
-- Table structure for table `pc`
--

CREATE TABLE `pc` (
  `model` char(4) DEFAULT NULL,
  `speed` float DEFAULT NULL,
  `ram` int(11) DEFAULT NULL,
  `hd` int(11) DEFAULT NULL,
  `price` decimal(4,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pc`
--

INSERT INTO `pc` (`model`, `speed`, `ram`, `hd`, `price`) VALUES
('1001', 2.66, 1024, 250, '2114'),
('1002', 2.1, 512, 250, '995'),
('1003', 1.42, 512, 80, '478'),
('1004', 2.8, 1024, 250, '649'),
('1005', 3.2, 512, 250, '630'),
('1006', 3.2, 1024, 320, '1049'),
('1007', 2.2, 1024, 200, '510'),
('1008', 2.2, 2048, 250, '770'),
('1009', 2, 1024, 250, '650'),
('1010', 2.8, 2048, 300, '770'),
('1011', 1.86, 2048, 160, '959'),
('1012', 2.8, 1024, 160, '649'),
('1013', 3.06, 512, 80, '529');

-- --------------------------------------------------------

--
-- Table structure for table `printer`
--

CREATE TABLE `printer` (
  `model` char(4) DEFAULT NULL,
  `color` varchar(5) DEFAULT NULL,
  `type` varchar(8) DEFAULT NULL,
  `price` decimal(4,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `printer`
--

INSERT INTO `printer` (`model`, `color`, `type`, `price`) VALUES
('3001', 'true', 'ink-jet', '99'),
('3002', 'false', 'laser', '239'),
('3003', 'true', 'laser', '899'),
('3004', 'true', 'ink-jet', '120'),
('3005', 'false', 'laser', '120'),
('3006', 'true', 'ink-jet', '100'),
('3007', 'true', 'laser', '200');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `maker` char(1) DEFAULT NULL,
  `model` char(4) DEFAULT NULL,
  `type` varchar(8) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`maker`, `model`, `type`) VALUES
('A', '1001', 'pc'),
('A', '1002', 'pc'),
('A', '1003', 'pc'),
('A', '2004', 'laptop'),
('A', '2005', 'laptop'),
('A', '2006', 'laptop'),
('B', '1004', 'pc'),
('B', '1005', 'pc'),
('B', '1006', 'pc'),
('B', '2007', 'laptop'),
('C', '1007', 'pc'),
('D', '1008', 'pc'),
('D', '1009', 'pc'),
('D', '1010', 'pc'),
('D', '3004', 'printer'),
('D', '3005', 'printer'),
('E', '1011', 'pc'),
('E', '1012', 'pc'),
('E', '1013', 'pc'),
('E', '2001', 'laptop'),
('E', '2002', 'laptop'),
('E', '2003', 'laptop'),
('E', '3001', 'printer'),
('E', '3002', 'printer'),
('E', '3003', 'printer'),
('F', '2008', 'laptop'),
('F', '2009', 'laptop'),
('G', '2010', 'laptop'),
('H', '3006', 'printer'),
('H', '3007', 'printer');
--
-- Database: `Football_2009-2018`
--
CREATE DATABASE IF NOT EXISTS `Football_2009-2018` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `Football_2009-2018`;
--
-- Database: `Ships`
--
CREATE DATABASE IF NOT EXISTS `Ships` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `Ships`;

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
--
-- Database: `TamuDriver`
--
CREATE DATABASE IF NOT EXISTS `TamuDriver` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `TamuDriver`;

-- --------------------------------------------------------

--
-- Table structure for table `DriverData`
--

CREATE TABLE `DriverData` (
  `entryNumber` int(11) NOT NULL COMMENT 'key',
  `location` varchar(30) NOT NULL,
  `InOrOut` tinyint(4) NOT NULL,
  `weekDay` int(11) NOT NULL,
  `entryTime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `DriverData`
--
ALTER TABLE `DriverData`
  ADD PRIMARY KEY (`entryNumber`),
  ADD UNIQUE KEY `DriverData_unique` (`location`,`InOrOut`,`weekDay`,`entryTime`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `DriverData`
--
ALTER TABLE `DriverData`
  MODIFY `entryNumber` int(11) NOT NULL AUTO_INCREMENT COMMENT 'key';--
-- Database: `WalkerProject`
--
CREATE DATABASE IF NOT EXISTS `WalkerProject` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `WalkerProject`;

-- --------------------------------------------------------

--
-- Table structure for table `WalkerData`
--

CREATE TABLE `WalkerData` (
  `WalkerNumber` int(11) NOT NULL,
  `Location` varchar(30) NOT NULL,
  `InOrOut` tinyint(1) NOT NULL,
  `WeekDay` tinyint(4) NOT NULL,
  `DateTime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `WalkerData`
--

INSERT INTO `WalkerData` (`WalkerNumber`, `Location`, `InOrOut`, `WeekDay`, `DateTime`) VALUES
(8, 'Student Recreation Center', 0, 3, '2018-02-13 13:59:43'),
(4, 'Student Recreation Center', 0, 5, '2018-01-25 13:57:43'),
(5, 'Student Recreation Center', 0, 5, '2018-01-27 13:57:43'),
(6, 'Student Recreation Center', 0, 5, '2018-02-01 13:57:43'),
(1, 'Student Recreation Center', 0, 5, '2018-02-02 01:53:43'),
(2, 'Student Recreation Center', 0, 5, '2018-02-02 13:54:43'),
(3, 'Student Recreation Center', 0, 5, '2018-02-02 13:57:43'),
(7, 'Student Recreation Center', 0, 5, '2018-02-13 13:57:43'),
(19, 'Student Recreation Center', 1, 1, '2018-03-04 18:14:50'),
(20, 'Student Recreation Center', 1, 1, '2018-03-04 18:21:49'),
(22, 'Student Recreation Center', 1, 1, '2018-03-04 18:21:50'),
(23, 'Student Recreation Center', 1, 1, '2018-03-04 18:21:51'),
(24, 'Student Recreation Center', 1, 1, '2018-03-04 18:21:52'),
(25, 'Student Recreation Center', 1, 1, '2018-03-04 18:21:53'),
(26, 'Student Recreation Center', 1, 1, '2018-03-04 18:21:54'),
(27, 'Student Recreation Center', 1, 1, '2018-03-04 18:21:55'),
(28, 'Student Recreation Center', 1, 1, '2018-03-04 18:21:56'),
(29, 'Student Recreation Center', 1, 1, '2018-03-04 18:21:58'),
(15, 'Student Recreation Center', 1, 3, '0000-00-00 00:00:00'),
(17, 'Student Recreation Center', 1, 3, '2018-02-20 12:01:09'),
(18, 'Student Recreation Center', 1, 3, '2018-02-20 12:01:42'),
(30, 'Student Recreation Center', 1, 3, '2018-03-06 10:16:39'),
(9, 'Student Recreation Center', 1, 5, '2018-02-15 14:59:43'),
(10, 'Student Recreation Center', 1, 5, '2018-02-15 15:40:43'),
(11, 'Student Recreation Center', 1, 5, '2018-02-15 15:45:43'),
(12, 'Student Recreation Center', 1, 5, '2018-02-15 15:47:43'),
(13, 'Student Recreation Center', 1, 5, '2018-02-15 19:47:43'),
(14, 'Student Recreation Center', 1, 5, '2018-02-15 19:49:43');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `WalkerData`
--
ALTER TABLE `WalkerData`
  ADD PRIMARY KEY (`WalkerNumber`),
  ADD UNIQUE KEY `WalkerData_unique` (`Location`,`InOrOut`,`WeekDay`,`DateTime`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `WalkerData`
--
ALTER TABLE `WalkerData`
  MODIFY `WalkerNumber` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;
