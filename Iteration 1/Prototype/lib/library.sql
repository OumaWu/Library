-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: 2018-05-14 10:33:55
-- 服务器版本： 5.7.17-log
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Table `library`
--

DROP TABLE IF EXISTS `administrators`;
DROP TABLE IF EXISTS `book_reservations`;
DROP TABLE IF EXISTS `books`;
DROP TABLE IF EXISTS `customers`;

-- --------------------------------------------------------

--
-- Table `administrators`
--

CREATE TABLE `administrators` (
  `login` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Insert `books`
--
INSERT INTO `administrators` VALUES ('axel','caliari'),
('emmanuel', 'gay'),
('jiahong', 'wu'),
('victor', 'wang');



--
-- Table `books`
--

CREATE TABLE `books` (
  `id` varchar(5) NOT NULL,
  `title` varchar(30) NOT NULL,
  `author` varchar(30) NOT NULL,
  `category` varchar(30) NOT NULL,
  `availability` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Insert `books`
--

INSERT INTO `books` (`id`, `title`, `author`, `category`, `availability`) VALUES
('00001', 'The 17th subject', 'James Patterson', 'Novel', 1),
('00002', 'The Fallen', 'David Baldacci', 'Novel', 0),
('00003', 'People', 'Meredith Corporation', 'Magazine', 0),
('00004', 'Agriculture Manual', 'James Franco', 'Manuel', 1),
('00005', 'Sports Illustrated', 'Time Inc.', 'Magazine', 1),
('00006', 'Twisted Prey', 'John Sandford', 'Novel', 1),
('00007', 'The Mars Room : A Novel', 'Rachel Kushner', 'Novel', 1),
('00008', 'The Forgotten Road', 'Richard Paul Evans', 'Novel', 1),
('00009', 'Little Fires Everywhere', 'Celeste Ng', 'Novel', 1),
('00010', 'War on Peace', 'Ronan Farrow', 'Novel', 1),
('00011', 'Cooking for beginners', 'Jiahong Wu', 'Manuel', 1),
('00013', 'The truth about the lie', 'George.W Bush', 1);

-- --------------------------------------------------------

--
-- Table `book_reservations`
--

CREATE TABLE `book_reservations` (
  `id` varchar(5) NOT NULL,
  `book_id` varchar(30) NOT NULL,
  `customer_id` varchar(30) NOT NULL,
  `book_date` date NOT NULL,
  `return_date` date DEFAULT NULL,
  `returned` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Insert `book_reservations`
--

INSERT INTO `book_reservations` (`id`, `book_id`, `customer_id`, `book_date`, `return_date`, `returned`) VALUES
('00001', '00001', '00001', '2018-06-14', '2018-06-22', 1),
('00002', '00002', '00003', '2018-05-06', '2018-06-15', 0),
('00003', '00003', '00004', '2018-04-10', '2018-07-09', 0),
('00004', '00004', '00006', '2018-03-05', '2018-04-11', 1),
('00005', '00005', '00002', '2018-04-18', '2018-07-14', 1);

--
-- Constraints `book_reservations`
--
DELIMITER $$
CREATE TRIGGER `CheckBookAvailability` BEFORE INSERT ON `book_reservations` FOR EACH ROW if EXISTS (SELECT * FROM books b
WHERE b.id = NEW.book_id AND b.availability = false)
then
SIGNAL sqlstate '45001' set message_text = "Can't borrow the book, it's not available!";
end if
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `CheckDateValidityOnCreate` BEFORE INSERT ON `book_reservations` FOR EACH ROW if NEW.book_date > NEW.return_date
then
SIGNAL sqlstate '45002' set message_text = "Can't create a reservation, the return date is invalid!";
end if
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `CheckDateValidityOnUpdate` BEFORE UPDATE ON `book_reservations` FOR EACH ROW if NEW.book_date > NEW.return_date
then
SIGNAL sqlstate '45002' set message_text = "Can't update the reservation, the return date is invalid!";
end if
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `CloseReservation` AFTER UPDATE ON `book_reservations` FOR EACH ROW UPDATE books
SET books.`availability` = NEW.`returned`
WHERE books.`id` =  NEW.`book_id`
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `CreateReservation` AFTER INSERT ON `book_reservations` FOR EACH ROW UPDATE books
SET books.`availability` = NEW.`returned`
WHERE books.`id` =  NEW.`book_id`
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table `customers`
--

CREATE TABLE `customers` (
  `id` varchar(5) NOT NULL,
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Insert `customers`
--

INSERT INTO `customers` (`id`, `firstname`, `lastname`) VALUES
('00001', 'Ciara', 'Chichester'),
('00002', 'Randi', 'Renz'),
('00003', 'Evon', 'Espana'),
('00004', 'Justin', 'Bieber'),
('00005', 'Bryan', 'Bertrand'),
('00006', 'Cristiano', 'Ronaldo');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `administrators`
--
ALTER TABLE `administrators`
  ADD PRIMARY KEY (`login`);

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `book_reservations`
--
ALTER TABLE `book_reservations`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_b_id` (`book_id`),
  ADD KEY `fk_c_id` (`customer_id`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- 限制导出的表
--

--
-- Indexes for  `book_reservations`
--
ALTER TABLE `book_reservations`
  ADD CONSTRAINT `fk_b_id` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  ADD CONSTRAINT `fk_c_id` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
