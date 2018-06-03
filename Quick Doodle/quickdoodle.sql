-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 03 Jun 2018 pada 09.35
-- Versi Server: 10.1.28-MariaDB
-- PHP Version: 7.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quickdoodle`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `label`
--

CREATE TABLE `label` (
  `Label_ID` int(10) NOT NULL,
  `Label_Name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `label`
--

INSERT INTO `label` (`Label_ID`, `Label_Name`) VALUES
(1, 'kucing'),
(2, 'kupu kupu'),
(3, 'Nama'),
(4, 'farhan');

-- --------------------------------------------------------

--
-- Struktur dari tabel `model`
--

CREATE TABLE `model` (
  `Model_ID` int(10) NOT NULL,
  `Date` date NOT NULL,
  `Description` text NOT NULL,
  `Model_Data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pixel_value`
--

CREATE TABLE `pixel_value` (
  `Value` text NOT NULL,
  `Pixel_ID` int(10) NOT NULL,
  `LabelID` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `pixel_value`
--

INSERT INTO `pixel_value` (`Value`, `Pixel_ID`, `LabelID`) VALUES
(',01,01,0,10101', 2, 1),
('1234567893732', 3, 2),
('12334123456789', 4, 3),
('123456789023456789023456789', 5, 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `label`
--
ALTER TABLE `label`
  ADD PRIMARY KEY (`Label_ID`);

--
-- Indexes for table `model`
--
ALTER TABLE `model`
  ADD PRIMARY KEY (`Model_ID`);

--
-- Indexes for table `pixel_value`
--
ALTER TABLE `pixel_value`
  ADD PRIMARY KEY (`Pixel_ID`),
  ADD UNIQUE KEY `LabelID` (`LabelID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `label`
--
ALTER TABLE `label`
  MODIFY `Label_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `pixel_value`
--
ALTER TABLE `pixel_value`
  MODIFY `Pixel_ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `pixel_value`
--
ALTER TABLE `pixel_value`
  ADD CONSTRAINT `pixel_value_ibfk_1` FOREIGN KEY (`LabelID`) REFERENCES `label` (`Label_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
