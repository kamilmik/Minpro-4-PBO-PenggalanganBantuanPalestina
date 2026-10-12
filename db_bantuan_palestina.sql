-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 12, 2026 at 03:35 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_bantuan_palestina`
--

-- --------------------------------------------------------

--
-- Table structure for table `dana`
--

CREATE TABLE `dana` (
  `id_bantuan` int NOT NULL,
  `donatur` varchar(100) DEFAULT NULL,
  `nominal` double DEFAULT NULL,
  `id_lembaga` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `dana`
--

INSERT INTO `dana` (`id_bantuan`, `donatur`, `nominal`, `id_lembaga`) VALUES
(101, 'Hamba Allah', 500000, 1),
(102, 'Fikri Abiyu', 1000000, 4),
(103, 'ahdasuki', 200000, 3);

-- --------------------------------------------------------

--
-- Table structure for table `lembaga`
--

CREATE TABLE `lembaga` (
  `id_lembaga` int NOT NULL,
  `nama_lembaga` varchar(100) DEFAULT NULL,
  `asal_negara` varchar(100) DEFAULT NULL,
  `kontak` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `lembaga`
--

INSERT INTO `lembaga` (`id_lembaga`, `nama_lembaga`, `asal_negara`, `kontak`) VALUES
(1, 'BAZNAS', 'Indonesia', '11111'),
(2, 'PMI', 'Indonesia', '222222'),
(3, 'UNICEF', 'Internasional', '333333'),
(4, 'INFORSA', 'Indonesia', '444444'),
(5, 'AKSI BERSAMA', 'Nasional', '555555');

-- --------------------------------------------------------

--
-- Table structure for table `logistik`
--

CREATE TABLE `logistik` (
  `id_bantuan` int NOT NULL,
  `donatur` varchar(100) DEFAULT NULL,
  `nama_barang` varchar(100) DEFAULT NULL,
  `berat_kg` int DEFAULT NULL,
  `id_lembaga` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `logistik`
--

INSERT INTO `logistik` (`id_bantuan`, `donatur`, `nama_barang`, `berat_kg`, `id_lembaga`) VALUES
(201, 'Relawan Kaltim', 'Pakaian & Selimut', 50, 5),
(202, 'Ihsan Kamil', 'Tenda', 10, 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `dana`
--
ALTER TABLE `dana`
  ADD PRIMARY KEY (`id_bantuan`),
  ADD KEY `id_lembaga` (`id_lembaga`);

--
-- Indexes for table `lembaga`
--
ALTER TABLE `lembaga`
  ADD PRIMARY KEY (`id_lembaga`);

--
-- Indexes for table `logistik`
--
ALTER TABLE `logistik`
  ADD PRIMARY KEY (`id_bantuan`),
  ADD KEY `id_lembaga` (`id_lembaga`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dana`
--
ALTER TABLE `dana`
  ADD CONSTRAINT `dana_ibfk_1` FOREIGN KEY (`id_lembaga`) REFERENCES `lembaga` (`id_lembaga`);

--
-- Constraints for table `logistik`
--
ALTER TABLE `logistik`
  ADD CONSTRAINT `logistik_ibfk_1` FOREIGN KEY (`id_lembaga`) REFERENCES `lembaga` (`id_lembaga`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
