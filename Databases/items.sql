-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 07, 2024 at 03:32 PM
-- Server version: 10.5.20-MariaDB
-- PHP Version: 7.3.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id22351549_user`
--

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `iditems` int(10) NOT NULL,
  `cpuName` varchar(10) NOT NULL,
  `namaBarang` varchar(255) NOT NULL,
  `hargaBarang` varchar(255) NOT NULL,
  `deskripsiBarang` varchar(255) NOT NULL,
  `gambarBarang` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`iditems`, `cpuName`, `namaBarang`, `hargaBarang`, `deskripsiBarang`, `gambarBarang`) VALUES
(1, 'amd', 'Ryzen 5 5600G', '$120', 'Core 6 / 12\r\nClock 3.9 to 4.4 GHz\r\nSocket AM4\r\nApr 13th, 2021', 'https://images.tokopedia.net/img/cache/500-square/VqbcmM/2021/12/22/3f8ceec4-6cb8-44a3-b6d2-397f756b95d6.jpg'),
(2, 'amd', 'Ryzen 3 3200G', '$250', 'Core 4\r\nClock 3.6 to 4 GHz\r\nSocket AM4\r\nJul 7th, 2019', 'https://images.tokopedia.net/img/cache/700/product-1/2020/1/31/3087186/3087186_60dfc5fe-95a5-43a5-be91-c0880706e7a6_700_700'),
(3, 'amd', 'Ryzen 5 3600', '$200', 'Core 6 / 12\r\nClock 3.6 to 4.2 GHz\r\nSocket AM4\r\nJul 7th, 2019', 'https://images.tokopedia.net/img/cache/700/VqbcmM/2022/3/28/661b68f3-0826-4341-a6e0-bd6e2b9761ec.jpg'),
(4, 'amd', 'Ryzen 5 5600', '$240', 'Core 6 / 12\r\nClock 3.5 to 4.4 GHz\r\nSocket AM4\r\nApr 20th, 2022', 'https://blossomzones.com/wp-content/uploads/2022/04/5500.png'),
(5, 'amd', 'Ryzen 7 7800X3D', '$400', 'Core 8 / 16\r\nClock 4.2 to 5 GHz\r\nSocket AM5\r\nJan 4th, 2023', 'https://www.static-src.com/wcsstore/Indraprastha/images/catalog/full//catalog-image/96/MTA-106834424/amd_amd_ryzen_7_7800x3d_box_ryzen_7_7000_series_8-core_socket_am5_120w_amd_radeon_graphics_desktop_processor_full03_qey2ti1y.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`iditems`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `iditems` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
