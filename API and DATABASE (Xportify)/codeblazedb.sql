-- phpMyAdmin SQL Dump
-- version 4.0.10.18
-- https://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Apr 22, 2017 at 11:53 PM
-- Server version: 5.6.35-cll-lve
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `codeblazedb`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_country`
--

CREATE TABLE IF NOT EXISTS `tbl_country` (
  `Code` varchar(10) NOT NULL,
  `CurrencyName` varchar(32) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tbl_orderproducts`
--

CREATE TABLE IF NOT EXISTS `tbl_orderproducts` (
  `orderno` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `username` varchar(32) NOT NULL,
  `first_name` varchar(32) NOT NULL,
  `middle_name` varchar(32) NOT NULL,
  `last_name` varchar(32) NOT NULL,
  `contactno` int(11) NOT NULL,
  `productname` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(32) NOT NULL,
  `country` varchar(32) NOT NULL,
  PRIMARY KEY (`orderno`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `tbl_orderproducts`
--

INSERT INTO `tbl_orderproducts` (`orderno`, `user_id`, `product_id`, `username`, `first_name`, `middle_name`, `last_name`, `contactno`, `productname`, `quantity`, `price`, `address`, `city`, `country`) VALUES
(1, 1, 2, 'fdsfads', 'fsdfasd', 'fasdf', 'fdsad', 0, '1fdasfsd', 2, 3, 'fdasfadsfad', 'fadsfas', 'fadsfas'),
(2, 2, 1, 'alex', 'christian', 'alex', 'nocum', 0, 'Dried', 23, 2222, 'caloocan', 'manila', 'philippines'),
(3, 0, 0, '', '', '', '', 0, '', 0, 0, '', '', ''),
(4, 0, 0, '', '', '', '', 0, '', 0, 0, '', '', ''),
(5, 1, 1, 'alex', 'christian', 'alex', 'nocum', 2147483647, 'Dried Mango', 2, 100, 'dagat dagatan caloocan', 'caloocan', 'philippines'),
(6, 2, 1, 'alex', 'christian', 'alex', 'nocum', 2147483647, 'Dried Mais', 1, 2222, 'dagat dagatan caloocan', 'caloocan', 'philippines'),
(7, 2, 1, 'alex', 'christian', 'alex', 'nocum', 2147483647, 'Dried Mais', 1, 2222, 'dagat dagatan caloocan', 'caloocan', 'philippines');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_products_distributor`
--

CREATE TABLE IF NOT EXISTS `tbl_products_distributor` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `productname` varchar(32) NOT NULL,
  `productdesc` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `imgpath` varchar(32) NOT NULL,
  `country` varchar(32) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `tbl_products_distributor`
--

INSERT INTO `tbl_products_distributor` (`product_id`, `user_id`, `productname`, `productdesc`, `quantity`, `price`, `imgpath`, `country`) VALUES
(1, 1, 'Dried Mango', 'Dried Mango from Guimaras, Iloilo', 1000, 50, '', 'philippines'),
(4, 3, 'Strawberry Jam', 'Sweet Strawberry Jam from Baguio City', 23, 150, '', 'philippines');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_products_seller`
--

CREATE TABLE IF NOT EXISTS `tbl_products_seller` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `productname` varchar(32) NOT NULL,
  `productdesc` varchar(32) NOT NULL,
  `quantity` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `imgpath` varchar(32) NOT NULL,
  `country` varchar(32) NOT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `tbl_products_seller`
--

INSERT INTO `tbl_products_seller` (`product_id`, `user_id`, `productname`, `productdesc`, `quantity`, `price`, `imgpath`, `country`) VALUES
(1, 1, 'Peanut Brittle 100g', 'Peanut Brittle from Baguio City', 123, 200, '', 'malaysia');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user_buyer`
--

CREATE TABLE IF NOT EXISTS `tbl_user_buyer` (
  `user_id` int(255) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(32) NOT NULL,
  `middle_name` varchar(32) NOT NULL,
  `last_name` varchar(32) NOT NULL,
  `contactno` int(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `country_code` varchar(255) NOT NULL,
  `profile_img` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email_add` varchar(32) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Dumping data for table `tbl_user_buyer`
--

INSERT INTO `tbl_user_buyer` (`user_id`, `first_name`, `middle_name`, `last_name`, `contactno`, `address`, `city`, `country`, `country_code`, `profile_img`, `username`, `password`, `email_add`) VALUES
(13, 'christian', 'alex', 'nocum', 2147483647, 'dagat dagatan caloocan', 'caloocan', 'philippines', '002', '', 'alex', 'alex', ''),
(18, 'chris', 'chris', 'chris', 0, 'chris', 'chris', 'chris', 'chris', '', 'chris', 'chris', 'chris'),
(19, 'hahahaha', 'hahahaha', 'hahahaha', 915377983, 'hahahaha', 'caloocan', 'caloocan', '2222', '', 'chris2', 'hahaha', 'hahhaahaha'),
(20, 'hahahaha', 'hahahaha', 'hahahaha', 915377983, 'hahahaha', 'caloocan', 'caloocan', '2222', '', 'chris2', 'hahaha', 'hahhaahaha'),
(21, 'hahahaha', 'hahahaha', 'hahahaha', 915377983, 'hahahaha', 'caloocan', 'caloocan', '2222', '', 'chris2w', 'hahaha', 'hahhaahaha');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user_seller`
--

CREATE TABLE IF NOT EXISTS `tbl_user_seller` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(32) NOT NULL,
  `middle_name` varchar(32) NOT NULL,
  `last_name` varchar(32) NOT NULL,
  `contactno` int(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(32) NOT NULL,
  `country` varchar(32) NOT NULL,
  `country_code` varchar(32) NOT NULL,
  `profile_img` varchar(255) NOT NULL,
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email_add` varchar(222) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `tbl_user_seller`
--

INSERT INTO `tbl_user_seller` (`user_id`, `first_name`, `middle_name`, `last_name`, `contactno`, `address`, `city`, `country`, `country_code`, `profile_img`, `username`, `password`, `email_add`) VALUES
(1, 'Christian Alex', 'H', 'Nocum', 2147483647, 'Block 37 G Lot 13 Phase 3 F1', 'Caloocan', 'philippines', '123', '', 'alex', 'alex', ''),
(3, 'Christian Alex', 'Alex', 'Alex', 2147483647, 'Block 37 G Lot 13', 'Caloocan ', 'philippines', '123', '', 'alexpogi', 'alexpogi', ''),
(4, 'Christian Alex', 'Alex', 'Alex', 2147483647, 'Block 37 G Lot 13', 'Caloocan ', 'philippines', '123', '', 'alexpogi', 'alexpogi', '');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
