-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 18, 2022 at 06:37 AM
-- Server version: 10.4.19-MariaDB
-- PHP Version: 8.0.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `clay's music store`
--

-- --------------------------------------------------------

--
-- Table structure for table `history_detail`
--

CREATE TABLE `history_detail` (
  `historyid` int(50) NOT NULL,
  `musicname` varchar(50) NOT NULL,
  `musicartist` varchar(50) NOT NULL,
  `musicprice` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `history_detail`
--

INSERT INTO `history_detail` (`historyid`, `musicname`, `musicartist`, `musicprice`) VALUES
(1, 'Halodd', 'asdasdsad', 0);

-- --------------------------------------------------------

--
-- Table structure for table `history_header`
--

CREATE TABLE `history_header` (
  `id` int(50) NOT NULL,
  `totalPurchase` varchar(50) NOT NULL,
  `datePurchase` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `history_header`
--

INSERT INTO `history_header` (`id`, `totalPurchase`, `datePurchase`) VALUES
(1, '0', '2022-01-18');

-- --------------------------------------------------------

--
-- Table structure for table `musics`
--

CREATE TABLE `musics` (
  `id` int(11) NOT NULL,
  `music_name` varchar(255) NOT NULL,
  `music_genre` varchar(255) NOT NULL,
  `music_price` varchar(255) NOT NULL,
  `artist_name` varchar(255) NOT NULL,
  `release_date` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `musics`
--

INSERT INTO `musics` (`id`, `music_name`, `music_genre`, `music_price`, `artist_name`, `release_date`) VALUES
(4, 'Halodd', 'Pop', '0', 'asdasdsad', '17-02-2002');

-- --------------------------------------------------------

--
-- Table structure for table `music_genres`
--

CREATE TABLE `music_genres` (
  `id` int(25) NOT NULL,
  `genre_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `music_genres`
--

INSERT INTO `music_genres` (`id`, `genre_name`) VALUES
(21, 'halo'),
(24, 'tws');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(25) NOT NULL,
  `username` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(25) NOT NULL,
  `gender` varchar(6) NOT NULL,
  `roles` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `email`, `password`, `gender`, `roles`) VALUES
(1, 'Test One', 'one@gmail.xyz', '1', 'Female', 'Admin'),
(6, 'Test two', 'two@yahoo.aci', '2', 'Male', 'User');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `history_detail`
--
ALTER TABLE `history_detail`
  ADD PRIMARY KEY (`historyid`);

--
-- Indexes for table `history_header`
--
ALTER TABLE `history_header`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `musics`
--
ALTER TABLE `musics`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `music_genres`
--
ALTER TABLE `music_genres`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `history_detail`
--
ALTER TABLE `history_detail`
  MODIFY `historyid` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `history_header`
--
ALTER TABLE `history_header`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `musics`
--
ALTER TABLE `musics`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `music_genres`
--
ALTER TABLE `music_genres`
  MODIFY `id` int(25) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(25) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
