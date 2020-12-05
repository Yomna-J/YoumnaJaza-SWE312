-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 05, 2020 at 10:49 AM
-- Server version: 10.4.16-MariaDB
-- PHP Version: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `banking_sys`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `account_num` int(6) NOT NULL,
  `user_id` int(8) NOT NULL,
  `name` varchar(40) NOT NULL,
  `balance` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`account_num`, `user_id`, `name`, `balance`) VALUES
(194399, 58175879, 'Ginto Sak', 3015.25),
(361718, 21526154, 'Tazmin Gordon', 6180.25),
(549585, 36809608, 'Louise Odonnell ', 3500.5),
(768039, 13724779, 'Jaymes Frank', 4953),
(770499, 17824852, 'Senku Ohfu', 9501);

-- --------------------------------------------------------

--
-- Table structure for table `beneficiaries`
--

CREATE TABLE `beneficiaries` (
  `account_num` int(6) NOT NULL,
  `name` varchar(40) NOT NULL,
  `beneficiary_account_num` int(6) NOT NULL,
  `beneficiary_name` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `beneficiaries`
--

INSERT INTO `beneficiaries` (`account_num`, `name`, `beneficiary_account_num`, `beneficiary_name`) VALUES
(194399, 'Ginto Sak', 549585, 'Loue'),
(361718, 'Tazmin Gordon', 194399, 'Best Friend Ginto'),
(768039, 'Jaymes Frank', 768039, 'ee');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `transaction_id` int(9) NOT NULL,
  `account_num` int(6) NOT NULL,
  `to_account_num` int(6) NOT NULL,
  `type` varchar(10) NOT NULL,
  `amount` double NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`transaction_id`, `account_num`, `to_account_num`, `type`, `amount`, `date`, `time`) VALUES
(196280298, 768039, 768039, 'DEPOSIT', 365, '2020-12-03', '19:23:37'),
(204945825, 194399, 194399, 'WITHDRAW', 1500, '2020-12-04', '16:16:35'),
(224433520, 361718, 361718, 'DEPOSIT', 2000, '2020-12-02', '17:52:25'),
(255974027, 768039, 768039, 'WITHDRAW', 800, '2020-12-03', '19:27:27'),
(262553756, 361718, 361718, 'WITHDRAW', 500, '2020-12-02', '17:52:13'),
(341343532, 770499, 770499, 'WITHDRAW', 434, '2020-12-04', '00:01:07'),
(419344434, 768039, 768039, 'WITHDRAW', 600, '2020-12-03', '19:26:54'),
(422104916, 768039, 768039, 'DEPOSIT', 100, '2020-12-03', '19:25:05'),
(464354856, 770499, 770499, 'DEPOSIT', 5555, '2020-12-03', '23:32:24'),
(487182502, 770499, 770499, 'DEPOSIT', 500, '2020-12-04', '01:07:07'),
(487781037, 194399, 194399, 'WITHDRAW', 5000, '2020-12-03', '19:30:30'),
(517122763, 194399, 194399, 'WITHDRAW', 70, '2020-12-04', '16:19:27'),
(533040573, 768039, 768039, 'DEPOSIT', 5888, '2020-12-03', '19:22:39'),
(589859925, 770499, 770499, 'DEPOSIT', 3435, '2020-12-04', '00:59:13'),
(652379268, 770499, 770499, 'DEPOSIT', 1000, '2020-12-03', '23:32:34'),
(723788756, 361718, 361718, 'DEPOSIT', 11000, '2020-12-02', '17:51:48'),
(743649223, 361718, 194399, 'TRANSFER', 5329.75, '2020-12-02', '17:52:03'),
(750470814, 770499, 770499, 'WITHDRAW', 555, '2020-12-03', '23:32:55'),
(785038696, 194399, 194399, 'WITHDRAW', 600, '2020-12-03', '19:33:04'),
(803318523, 194399, 194399, 'DEPOSIT', 10000, '2020-12-02', '17:47:46'),
(808293007, 194399, 549585, 'TRANSFER', 3500.5, '2020-12-02', '17:48:43'),
(822480863, 361718, 361718, 'WITHDRAW', 990, '2020-12-02', '17:52:35'),
(846950647, 194399, 194399, 'WITHDRAWAL', 444, '2020-12-04', '17:12:16'),
(971435730, 194399, 194399, 'WITHDRAW', 1200, '2020-12-02', '17:47:58');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(8) NOT NULL,
  `name` varchar(40) NOT NULL,
  `password` varchar(40) NOT NULL,
  `phone_num` varchar(10) NOT NULL,
  `email` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `name`, `password`, `phone_num`, `email`) VALUES
(13724779, 'Jaymes Frank', 'j', '4387437877', 'ksadkdjl@sd.coer'),
(17824852, 'Senku Ohfu', 's', '5478685665', 'fdfggfgf@fdfgfd.cog'),
(21526154, 'Tazmin Gordon', 'TamG', '4234356865', 'TamzminGo@emdsi.caoms'),
(36809608, 'Louise Odonnell ', 'Loui&', '8742334732', 'Odnnell_32@dmksd.cmdk'),
(58175879, 'Ginto Sak', 'gin', '3857236682', 'gin145@emsld.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`account_num`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `beneficiaries`
--
ALTER TABLE `beneficiaries`
  ADD PRIMARY KEY (`account_num`,`beneficiary_account_num`),
  ADD KEY `account_num` (`account_num`),
  ADD KEY `beneficiary_account_num` (`beneficiary_account_num`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`transaction_id`),
  ADD KEY `account_num_fk` (`account_num`),
  ADD KEY `to_account_num` (`to_account_num`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `accounts`
--
ALTER TABLE `accounts`
  ADD CONSTRAINT `accounts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `beneficiaries`
--
ALTER TABLE `beneficiaries`
  ADD CONSTRAINT `beneficiaries_ibfk_1` FOREIGN KEY (`account_num`) REFERENCES `accounts` (`account_num`),
  ADD CONSTRAINT `beneficiaries_ibfk_2` FOREIGN KEY (`beneficiary_account_num`) REFERENCES `accounts` (`account_num`);

--
-- Constraints for table `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`account_num`) REFERENCES `accounts` (`account_num`),
  ADD CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`to_account_num`) REFERENCES `accounts` (`account_num`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
