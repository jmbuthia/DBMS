-- phpMyAdmin SQL Dump
-- version 4.4.15.10
-- https://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 28, 2020 at 07:31 PM
-- Server version: 10.3.22-MariaDB
-- PHP Version: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbms`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `accountNo` varchar(50) NOT NULL,
  `accountType` varchar(50) NOT NULL,
  `accountMode` varchar(50) NOT NULL,
  `amount` double NOT NULL DEFAULT 0,
  `isFreezed` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`accountNo`, `accountType`, `accountMode`, `amount`, `isFreezed`) VALUES
('00000000', 'DBMS', 'DBMS', 0, 'false'),
('01234567', 'Salary', 'rider', 0, 'false'),
('11111111', 'Salary', 'owner/rider', 0, 'false'),
('11223344', 'Salary', 'owner/rider', 0, 'false'),
('12341234', 'Salary', 'owner', 0, 'false'),
('12345', 'Salary', '', 0, 'false'),
('12345699', 'Salary', 'owner', 0, 'false'),
('25798025', 'Salary', 'owner/rider', 0, 'false'),
('28272625', 'Salary', 'owner', 0, 'false'),
('28338442', 'Salary', 'owner', 0, 'false'),
('29008594', 'Salary', 'rider', 0, 'false'),
('29198594', 'Salary', 'owner/rider', 0, 'false'),
('31750081', 'Salary', 'owner/rider', 0, 'false'),
('32323232', 'Salary', 'owner', 0, 'false'),
('55555555', 'Salary', 'rider', 0, 'false'),
('56565656', 'Salary', '', 0, 'false'),
('66666667', 'Salary', 'rider', 0, 'false'),
('77665544', 'Salary', 'owner', 0, 'false'),
('77776666', 'Salary', 'owner/rider', 0, 'false'),
('99887766', 'Salary', 'owner/rider', 0, 'false'),
('99999990', 'Salary', 'owner', 0, 'false'),
('jjjjjj', 'Salary', 'owner/rider', 0, 'false');

-- --------------------------------------------------------

--
-- Table structure for table `changePasswordHistory`
--

CREATE TABLE IF NOT EXISTS `changePasswordHistory` (
  `idNo` varchar(8) NOT NULL,
  `oldPassword` text NOT NULL,
  `newPassword` text NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `changePasswordHistory`
--

INSERT INTO `changePasswordHistory` (`idNo`, `oldPassword`, `newPassword`, `date`) VALUES
('11111111', '7U8Ejnnqjo/atkVwHfEOjA==', '7U8Ejnnqjo/atkVwHfEOjA==', '2016-06-18 19:30:55'),
('11111111', 'KQ0LPyBzqvuNoFQFjtzMpw==', '7U8Ejnnqjo/atkVwHfEOjA==', '2016-06-18 19:40:55'),
('11111111', '7U8Ejnnqjo/atkVwHfEOjA==', 'KQ0LPyBzqvuNoFQFjtzMpw==', '2016-06-18 19:45:56'),
('11111111', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'KQ0LPyBzqvuNoFQFjtzMpw==', '2016-06-18 19:58:38'),
('12345678', 'KQ0LPyBzqvuNoFQFjtzMpw==', '7U8Ejnnqjo/atkVwHfEOjA==', '2016-06-17 08:51:41'),
('25252525', 'Vl3+c8A7gqTK1OcN7K8q/Q==', 'KQ0LPyBzqvuNoFQFjtzMpw==', '2016-07-11 18:58:28'),
('25252525', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'KQ0LPyBzqvuNoFQFjtzMpw==', '2016-07-11 18:58:58'),
('25798025', '2PKvn06hm7Q7S0GrejwVTQ==', 'aXcFBpTg84JXp+GjIvMEbw==', '2016-08-18 13:56:31'),
('29198594', '5qk9j1ziZbZCdG1sRnFs2Q==', 'OvPXxaFTArWlH1dkSCEb9g==', '2016-07-08 14:11:11'),
('33333333', '7U8Ejnnqjo/atkVwHfEOjA==', 'JEbyypSihArZLNCeTyWNqg==', '2016-06-11 01:12:53'),
('66666666', 'KQ0LPyBzqvuNoFQFjtzMpw==', '7U8Ejnnqjo/atkVwHfEOjA==', '2016-06-17 04:26:02');

-- --------------------------------------------------------

--
-- Table structure for table `drivingLicenseDetails`
--

CREATE TABLE IF NOT EXISTS `drivingLicenseDetails` (
  `idNo` varchar(8) NOT NULL,
  `drivingLicenseNo` varchar(8) NOT NULL,
  `referenceNo` varchar(20) NOT NULL,
  `drivingLicenseExpiryDate` date NOT NULL,
  `isAssigned` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `drivingLicenseDetails`
--

INSERT INTO `drivingLicenseDetails` (`idNo`, `drivingLicenseNo`, `referenceNo`, `drivingLicenseExpiryDate`, `isAssigned`) VALUES
('32323232', '1111111', '1456895(GHJ-456)', '2016-09-07', 'true'),
('29198594', '1452348', '1931768(QNC-167)', '2016-07-08', 'true'),
('31750081', '1655845', '5698716(SNJ-656)', '2016-10-04', 'true'),
('01234567', '2254568', '5468975(JHU-125)', '2016-08-31', 'false'),
('25798025', '2908384', '3890049(SYU-994)', '2017-08-01', 'true'),
('29008594', '3456789', '1094747(FDG-665)', '2018-08-16', 'true'),
('99887766', '4555889', '2546897(KLJ-456)', '2016-07-25', 'true'),
('66666667', '4568795', '6589524(LKJ-546)', '2016-09-06', 'true'),
('11111111', '4586322', '5862545(HJU-456)', '2016-07-30', 'true'),
('11223344', '5468792', '0989737(SJN-048)', '2016-09-06', 'true'),
('jjjjjj', '5558966', '0989737(SJN-008)', '2019-07-07', 'true'),
('77776666', '8795264', '5469875(FGH-458)', '2016-09-21', 'false'),
('55555555', '8975462', '4578965(JUY-589)', '2016-07-24', 'false');

-- --------------------------------------------------------

--
-- Table structure for table `forgotPassword`
--

CREATE TABLE IF NOT EXISTS `forgotPassword` (
  `idNo` varchar(8) NOT NULL,
  `confirmationCode` varchar(10) NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `forgotPassword`
--

INSERT INTO `forgotPassword` (`idNo`, `confirmationCode`, `date`) VALUES
('25733094', '7281111', '2016-07-25 13:57:56'),
('26906926', '3368384', '2016-07-25 13:58:14'),
('31750081', '7555260', '2016-07-25 13:54:11'),
('32003697', '8783065', '2016-07-25 13:13:07');

-- --------------------------------------------------------

--
-- Table structure for table `forgotPasswordHistory`
--

CREATE TABLE IF NOT EXISTS `forgotPasswordHistory` (
  `idNo` varchar(8) NOT NULL,
  `confirmationCode` varchar(10) NOT NULL,
  `newPassword` text NOT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `forgotPasswordHistory`
--

INSERT INTO `forgotPasswordHistory` (`idNo`, `confirmationCode`, `newPassword`, `date`) VALUES
('11111111', '4065948', 'KQ0LPyBzqvuNoFQFjtzMpw==', '2016-06-18 19:32:48'),
('25733094', '6200356', '1cPk82qAAYiWPvuWiLOzmA==', '2016-06-24 13:05:28'),
('25798025', '4744054', '2PKvn06hm7Q7S0GrejwVTQ==', '2016-08-18 14:00:54'),
('28024582', '2829910', 'iGOoE3B+blyOrf5uAlJuMw==', '2016-06-21 06:50:45'),
('28024582', '3624953', 'PkOVa89cGeQ0VbDOJF7CHQ==', '2016-06-30 13:53:07'),
('28024582', '3418850', 'jd1tVrqhYTM2Mbv0arXf8g==', '2016-07-25 10:16:12'),
('28338442', '9780453', '9o70FDpJVaQ4WisMVKBy6w==', '2016-07-04 12:18:03'),
('29198594', '4474624', 'mg/y3+aSY/uWTNsCAQS9vg==', '2016-07-08 14:15:27'),
('31750081', '5264037', '8AIZT7wC9D8LXkHgI0n5iQ==', '2016-07-01 14:42:48'),
('32003697', '3771528', 'ZWbK+Lboj1+FE593hKsJaA==', '2016-06-22 15:00:37'),
('66666666', '6835971', 'Eu4mzybpOCZCnON6OYAAJQ==', '2016-06-16 19:52:28'),
('66666666', '9269235', 'KQ0LPyBzqvuNoFQFjtzMpw==', '2016-06-16 20:19:55'),
('66666666', '8169634', 'KQ0LPyBzqvuNoFQFjtzMpw==', '2016-06-17 04:32:14'),
('66666666', '5252665', 'KQ0LPyBzqvuNoFQFjtzMpw==', '2016-06-17 05:15:41');

-- --------------------------------------------------------

--
-- Table structure for table `helpAllVisitors`
--

CREATE TABLE IF NOT EXISTS `helpAllVisitors` (
  `name` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `message` text NOT NULL,
  `time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `helpAllVisitors`
--

INSERT INTO `helpAllVisitors` (`name`, `phone`, `message`, `time`) VALUES
('TestReply', '+254715949519', 'Can i register with PDL?', '2016-07-02 07:04:52'),
('ten', '+254715949519', 'help me pliz', '2016-07-07 02:17:35'),
('john', '+254715949519', 'test two', '2016-07-30 09:15:59');

-- --------------------------------------------------------

--
-- Table structure for table `helpAllVisitorsHistory`
--

CREATE TABLE IF NOT EXISTS `helpAllVisitorsHistory` (
  `name` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `message` text NOT NULL,
  `repliedMessage` text DEFAULT NULL,
  `status` varchar(50) NOT NULL,
  `time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `helpAllVisitorsHistory`
--

INSERT INTO `helpAllVisitorsHistory` (`name`, `phone`, `message`, `repliedMessage`, `status`, `time`) VALUES
('lynder', '+254707210913', 'welcome', 'karibu mteja mpendwa, tusaidie kuhakikisha uekezaji wako ume dhibitiwa kwa ustadi. ', 'replied By johnson idNO 1', '2016-07-15 10:33:37'),
('lynder', '+254707210913', 'what if someone does not know to read or write?<br>', 'unaweza tutembelea ofisini ili upate maelezo moja kwa moja. pia tuko na video za maelezo kuhuhu unavyo weza kusfaidi kwakuwekeza nasi', 'replied By johnson idNO 1', '2016-07-15 10:41:41'),
('lynder', '+254707210913', 'where are your offices located?<br>', 'jengo la bawani ofisi kwa jina "lab 3". pia unaweza piga namba ya ofisi kwa maelekezo "0717925741" kati ya saa mbili asubuhi hadi saa kumi na moja jioni  siku ya wiki ', 'replied By johnson idNO 1', '2016-07-15 11:00:30'),
('nbj', '+254709632222', 'denniu', '', 'Deleted By Admin', '2016-07-01 12:28:50'),
('john', '+254715949519', '<img src="http://localhost:8080/dbms/javax.faces.resource/images/boda/boda1.jpg.xhtml">from dbms<br>', 'hakuna Shida', 'replied By johnson idNO 1', '2016-07-02 05:18:15'),
('johnjohn', '+254715949519', '<div style="font-family: Arial, Verdana; text-align: center;"><span style="font-size: 10pt;">john mbuthia</span></div><div style="text-align: left;"><ul><li><span style="font-size: 10pt; font-family: Arial, Verdana;">j<img src="http://" style="font-size: 10pt;"></span><span style="font-size: 10pt; font-family: Arial, Verdana;">juuuuuuu</span><span style="font-size: 10pt; font-family: Narrow;">yyyyyyyyyyyyyi9uugyftft</span></li><li><span style="font-size: 10pt; font-family: Narrow;">khhuhuhuhuhuhuh</span></li><li><span style="font-size: 10pt; font-family: Narrow;">knnjjnj</span></li></ul></div>', '', 'Deleted By johnson idNO 1', '2016-07-02 05:20:26'),
('johnjohn', '+254715949519', 'uhuhuhuhuhuhuh<div style="font-style: normal; font-weight: normal;"><span style="text-decoration: underline;">jhuhuhuuuuuuuuu</span></div><div style="font-style: normal; font-weight: normal;"><span style="text-decoration: underline;">kjj</span>hhhhh <span style="color: rgb(204, 0, 0);">uugugyyftft jjjjjjjjjjjjj</span>jjjh</div><div><span style="font-style: normal; font-weight: bold;">jhhygygygy</span>kjj<span style="font-style: italic;">mjjjjjjjj</span></div><div><span style="font-style: italic; text-decoration: line-through;">jyyyyyyyy</span></div><div><span style="font-style: italic;">jm<span style="vertical-align: super;">2</span></span></div>', 'Thanks.', 'replied By johnson idNO 1', '2016-07-02 05:28:21'),
('johnson', '+254715949519', 'Nisaidie', 'utasaidiwa', 'replied By johnson idNO 1', '2016-07-02 09:45:06'),
('johnson', '+254717925741', 'my message', '', 'Deleted By Admin', '2016-07-01 17:14:25'),
('johnjohn', '+254717925741', 'ijijuhuhuyyyuhuuhuh', 'johnson', 'replied By johnson idNO 1', '2016-07-02 05:11:05'),
('mbuthia', '+254717925741', 'jhuhgyftftff', '', 'Deleted By johnson idNO 1', '2016-07-02 05:27:52'),
('njoroge', '+254727643607', '<div style="text-align: center;">si na kipande lakini mum alininunulia piki nnaeza ingia aje kwa hii sacco<br></div>', 'tuna wahimiwa wateja wetu wajisajili na serikali yakitaifa iliwaweze kupata cheti cha mwenendo mwema "good conduct" na pia usajiliwe na idara ya kutoza ushuru yaani "tax pin". kwasasa tunachoweza ni kuiandikisha pikipiki yako kwa jina la mzazi wako. ', 'replied By johnson idNO 1', '2016-07-15 10:54:03'),
('null', 'null', 'null', 'Habari ya asubuhi', 'replied By johnson idNO 1', '2016-07-02 05:01:54');

-- --------------------------------------------------------

--
-- Table structure for table `helpRegisteredUsers`
--

CREATE TABLE IF NOT EXISTS `helpRegisteredUsers` (
  `idNo` varchar(8) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `message` text NOT NULL,
  `time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `helpRegisteredUsers`
--

INSERT INTO `helpRegisteredUsers` (`idNo`, `phone`, `message`, `time`) VALUES
('25733094', '+254728583177', 'governer', '2016-06-24 12:31:54'),
('29008594', '+254700742923', 'WHAT ARE THE QUALIFICATION OF A RIDER CONCERNING THE EXPRIENCE YEARS<br>', '2016-08-11 10:38:56'),
('5', '+254717925741', 'am rider<br>', '2016-06-23 20:08:06'),
('5', '+254717925741', 'kjjhgyftft.<br>nbhvgvgggg.<br><br>', '2016-06-23 20:10:28'),
('5', '+254717925741', 'hae', '2016-07-07 03:00:20'),
('6', '+254717925741', 'Hi administrator?<br>', '2016-06-23 18:37:50');

-- --------------------------------------------------------

--
-- Table structure for table `helpRegisteredUsersHistory`
--

CREATE TABLE IF NOT EXISTS `helpRegisteredUsersHistory` (
  `idNo` varchar(50) NOT NULL,
  `phone` varchar(50) NOT NULL,
  `message` text NOT NULL,
  `repliedMessage` text DEFAULT NULL,
  `status` varchar(50) NOT NULL,
  `time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `helpRegisteredUsersHistory`
--

INSERT INTO `helpRegisteredUsersHistory` (`idNo`, `phone`, `message`, `repliedMessage`, `status`, `time`) VALUES
('1', '0717925741', 'mimi ni admin.....', '', 'Deleted By johnson idNO 1', '2016-07-02 09:55:33'),
('11223344', '+254715949519', 'Can someone register with 7 digit id number????', 'unaweza', 'replied By johnson idNO 1', '2016-07-02 09:53:21'),
('25798025', '+254726017622', 'Hey Johnson.<div><br></div><div>I cannot login as a rider.</div>', 'Hello faith.\r\nPlease check the video help.', 'replied By johnson idNO 1', '2016-08-18 14:06:05'),
('29198594', '+254780742923', 'how to remove my boda boda from your list<br><br>', 'Do you want to remove from the Sacco or?\r\n ', 'replied By johnson idNO 1', '2016-07-08 14:07:00'),
('31750081', '+254719677197', 'How do i <span style="font-weight: bold;">logout</span>.<br>', 'You can logout by clicking on your profile picture then logout.\r\n ', 'replied By johnson idNO 1', '2016-07-08 14:05:25');

-- --------------------------------------------------------

--
-- Table structure for table `motorbikeAndOwner`
--

CREATE TABLE IF NOT EXISTS `motorbikeAndOwner` (
  `idNo` varchar(50) NOT NULL,
  `numberPlate` varchar(50) NOT NULL,
  `amount` double NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `motorbikeAndOwner`
--

INSERT INTO `motorbikeAndOwner` (`idNo`, `numberPlate`, `amount`) VALUES
('11111111', 'KMCJ 567Y', 0),
('11111111', 'KMVG 125I', 5121.233),
('11223344', 'KMCV 125O', 3327.1289328),
('12345', 'KMCV 125U', 0),
('12345', 'KMCV 125Y', 0),
('12345699', 'KMCF 135Q', 201),
('12345699', 'KMCH 123K', 0),
('25798025', 'KMGB 108L', 3500),
('28272625', 'KMCH 127D', 300),
('28338442', 'KMCF 675R', 0),
('29198594', 'KMJH 154C', 0),
('31750081', 'KMCQ 147S', 6401.009022333334),
('32323232', 'KMCJ 567H', 0),
('56565656', 'KMCV 108U', 0),
('56565656', 'KMCV 128U', 0),
('66666667', 'KMCP 123J', 0),
('77665544', 'KMCV 109U', 0),
('77665544', 'KMCV 129I', 0),
('77776666', 'KMCP 123U', 0),
('99887766', 'KMCV 125M', 0),
('99887766', 'KMCV 125P', 0),
('99887766', 'KMCV 129U', 0),
('99999990', 'KMTI 334O', 500),
('jjjjjj', 'KMCP 123O', 0);

-- --------------------------------------------------------

--
-- Table structure for table `motorcycleDetails`
--

CREATE TABLE IF NOT EXISTS `motorcycleDetails` (
  `numberPlate` varchar(15) NOT NULL,
  `engineNo` varchar(50) NOT NULL,
  `frameNo` varchar(50) NOT NULL,
  `model` varchar(50) NOT NULL,
  `insuranceExpiryDate` date NOT NULL,
  `isAssigned` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `motorcycleDetails`
--

INSERT INTO `motorcycleDetails` (`numberPlate`, `engineNo`, `frameNo`, `model`, `insuranceExpiryDate`, `isAssigned`) VALUES
('KMCF 135Q', 'HYGT67890IU7', 'JH87UY67YT5678IJ8', 'TVS', '2016-07-30', 'true'),
('KMCF 675R', 'EE4RF65TGYU7', 'XD34ESD2341WQASW2', 'BOXER', '2017-01-01', 'false'),
('KMCH 123K', 'JHKIJHUYGTFR', 'JHGYT65RED432WSER', 'HAOJUE', '2016-08-13', 'true'),
('KMCH 127D', 'FG7GT2347890', 'MH2A54GH8UGT43456', 'SKYGO', '2016-08-10', 'true'),
('KMCJ 567H', 'JHGFDR45678', 'HGJYI876YT657YU8I', 'HAOJUE', '2016-07-30', 'false'),
('KMCJ 567Y', 'JHGODR45678', 'HGJYI876YT657YUKK', 'KINGBIRD', '2016-07-30', 'false'),
('KMCP 123J', 'JHKLOIU7654', 'HGFTYJHUYHGFRTDF5', 'YAMAHA', '2016-07-05', 'false'),
('KMCP 123O', 'LKHHGJUOI8UYP', 'NJUI8UY6T54RE432K', 'TVS', '2016-07-12', 'true'),
('KMCP 123U', 'JHFLKIUTJ456', 'FGHJRT546678IUJKO', 'LIFAN', '2016-07-06', 'false'),
('KMCQ 147S', 'FF5DF1868535', 'MD2A6595955615616', 'DUCATI', '2017-07-20', 'true'),
('KMCV 108U', 'JUHY7K8906K', 'LNHY65KRDE457890J', 'SUZUKI', '2016-07-24', 'false'),
('KMCV 109U', 'JUHY7689098', 'LNHY654RDE45789UU', 'YAMAHA', '2016-07-24', 'false'),
('KMCV 125M', 'KJU78IUY6TR9', 'JHU76YT5R4EWSDFG7', 'TVS', '2016-07-25', 'true'),
('KMCV 125O', 'KJU78IUY6TRP', 'JHU76YT5R4EWSDFG9', 'TVS', '2016-07-21', 'true'),
('KMCV 125P', 'JUHY7689065', 'LNHY654RDE457890O', 'YAMAHA', '2016-07-24', 'false'),
('KMCV 125U', 'KJU78IUY6TRI', 'JHU76YT5R4EWSDFGJ', 'TVS', '2017-07-05', 'false'),
('KMCV 125Y', 'KJU78IUY6TRU', 'JHU76YT5R4EWSDFGO', 'TVS', '2017-07-05', 'false'),
('KMCV 128U', 'JUHY768906K', 'LNHY654RDE457890J', 'SUZUKI', '2016-07-24', 'false'),
('KMCV 129I', 'JUHY768906O', 'LNHY654RDE457890L', 'YAMAHA', '2016-07-25', 'false'),
('KMCV 129U', 'JUHY76890O5', 'LNHY654RDE457890L', 'SUZUKI', '2016-07-27', 'false'),
('KMGB 108L', '567484888825', '897989040089SSSBB', 'BMW', '2017-08-03', 'true'),
('KMJH 154C', 'NJUYT7865MKU', 'BGTRFDJU78654NJUY', 'SKYGO', '2016-07-08', 'true'),
('KMTI 334O', 'WBA121214TU', 'WBA121214TTYTY671', 'BMW', '2016-08-10', 'false'),
('KMVG 125I', 'JHNBGFTYUIOK', 'NBHY7890OIUHY765L', 'YAMAHA', '2016-08-13', 'false');

-- --------------------------------------------------------

--
-- Table structure for table `registrationDetails`
--

CREATE TABLE IF NOT EXISTS `registrationDetails` (
  `idNo` varchar(8) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `middleName` varchar(20) DEFAULT NULL,
  `lastName` varchar(20) NOT NULL,
  `dob` date NOT NULL,
  `phone` varchar(20) NOT NULL,
  `password` text NOT NULL,
  `category` varchar(20) NOT NULL,
  `profilePicture` text NOT NULL,
  `gender` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `registrationDetails`
--

INSERT INTO `registrationDetails` (`idNo`, `firstName`, `middleName`, `lastName`, `dob`, `phone`, `password`, `category`, `profilePicture`, `gender`) VALUES
('00000000', 'johnson', NULL, 'mbuthia', '2016-01-01', '0715949519', 'john', 'dbms', 'dbms', 'dbms'),
('00901357', 'Johnson', '', 'Mbuthia', '2002-02-01', '+254717925741', 'MTLY9HyeG/Ok2I9HlHz3yg==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('01234567', 'Johnson', '', 'Mbuthia', '1998-08-08', '+254715949519', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'rider', 'images/profile/01234567.jpg', 'Male'),
('1', 'johnson', '', 'mbuthia', '1900-01-01', '0717925741', 'MTLY9HyeG/Ok2I9HlHz3yg==', 'admin', 'images/profile/1.jpg', 'Male'),
('11111111', 'Johnson', '', 'Mbuthia', '1972-04-06', '+254715949519', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'owner/rider', 'images/profile/11111111.jpg', 'Male'),
('11223344', 'Johnson', '', 'Mbuthia', '1990-09-01', '+254715949519', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'owner/rider', 'images/profile/11223344.png', 'Male'),
('12121212', 'First', 'Middle', 'Last', '1987-06-09', '0711111111', 'rPjr/bN1tcAO7lAe19ijeQ==', 'general', 'images/profile/12121212.jpg', 'Male'),
('12341234', 'Johnson', '', 'Mbuthia', '1998-07-01', '+254715949519', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'owner', 'images/profile/12341234.png', 'Male'),
('12345', 'jhgf', 'dfgfh', 'sfdgfh', '1990-01-01', 'dffhhg', 'KQ0LPyBzqvuNoFQFjtzMpw==', '', 'images/profile/12345.jpg', 'Female'),
('123455', 'gggg', 'thj', '123', '1998-06-02', '123', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Female'),
('123455g', 'gggg', 'thj', '123', '1998-06-01', '123', 'hodKjZg6AskDb+WJkTMDqg==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('123455gk', 'gggg', '123', 'jjkk', '1998-06-01', '123', '0eYIDWnRpuEnT5idYcTkjw==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('123455h', '123', '123', 'jjkk', '1998-06-01', '123', 'hodKjZg6AskDb+WJkTMDqg==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('123455l', '123', 'thj', 'jjkk', '1998-06-01', '123', 'LpMJCTLnZwE4J3aSd6goRw==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Female'),
('12345673', 'des', 'sdw', 'swd', '1998-06-01', '+254730278164', '5Owirg326MmyJucgOh9sng==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('12345678', 'johnson', 'maina', 'mbuthia', '2016-06-01', '0717925741', '7U8Ejnnqjo/atkVwHfEOjA==', 'admin', 'images/profile/12345678.png', 'male'),
('12345699', 'Johnson', '', 'Mbuthia', '1998-07-01', '+254715949519', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'owner', 'images/profile/12345699.jpg', 'Male'),
('123565', 'gggg', '123', '123', '1998-06-02', '123', 'hodKjZg6AskDb+WJkTMDqg==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Female'),
('1235655', 'gggg', '123', '123', '1998-06-02', '123', 'hodKjZg6AskDb+WJkTMDqg==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Female'),
('123hg', '123', '123', 'eefett', '1998-06-01', '123', '0eYIDWnRpuEnT5idYcTkjw==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('123u', 'gggg', 'thj', '123', '1998-06-02', '123', 'hodKjZg6AskDb+WJkTMDqg==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Female'),
('17925741', 'john', 'john', 'john', '1998-06-01', '+254717925741', 'lhk6EvRUZKL3j6Eb6Y9SfA==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('2', 'johnson', '', 'mbuthia', '1900-01-01', '0715949519', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'teller', 'images/profile/2.jpg', 'Male'),
('22222222', 'des', 'mwas', 'kim', '1998-06-03', '+25430278164_', 'dUfCKwYCZhOPxRecyqIxUQ==', 'general', 'images/profile/22222222.png', 'Female'),
('24074264', 'naphtaly', 'njoroge', 'nduati', '1985-02-11', '+254727643607', 'M29sP0OCE4XKZmSGDZXeOxCj/gBIlUKJEr2bM9yf3NY=', 'general', 'images/profile/24074264.jpg', 'Male'),
('25252525', 'Johnson', 'johnson', 'mbuthia', '1998-07-01', '+254715949519', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('25733035', 'Stephen', 'Mwangi', 'Thiga', '1998-06-01', '+254715949519', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('25733094', 'dennis', 'kariuki', 'mwangi', '1998-04-20', '+254728583177', '1cPk82qAAYiWPvuWiLOzmA==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('25798025', 'faith', 'jepkorir', 'lelgo', '1995-06-22', '+254726017622', '2PKvn06hm7Q7S0GrejwVTQ==', 'owner/rider', 'images/profile/25798025.jpg', 'Female'),
('26902669', 'kipngetich', 'leonard', 'towett', '1986-05-15', '+254728065590', '68k+4+FaYdtsT4m3ZAeZDw==', 'general', 'images/profile/26902669.jpg', 'Male'),
('26906926', 'leonard', 'kipngetich', 'towett', '1988-02-29', '+254728065590', 'VtoZVzIICy6sb/dXRei9JA==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('27400003', 'DUNCAN', 'KIMUTAI', 'KEMBOI', '1988-11-09', '+254721742700', 'HYZ8uwMOhautlD+JbcOU4A==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('28024582', 'EDITH', 'MWANGI', 'WANJA', '1991-01-01', '+254714166821', 'jd1tVrqhYTM2Mbv0arXf8g==', 'general', 'images/profile/28024582.JPG', 'Female'),
('28111245', 'johnson', '', 'mbuthia', '1963-01-30', '0717925741', 'rPjr/bN1tcAO7lAe19ijeQ==', 'general', 'images/profile/28111245.gif', 'Male'),
('28272625', 'charles', 'ng''ong''o', 'kibutu', '1990-08-03', '+254705114688', '5qk9j1ziZbZCdG1sRnFs2Q==', 'owner', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('28338442', 'raphael', 'maingi', 'wangari', '1989-01-03', '+254714671365', '9o70FDpJVaQ4WisMVKBy6w==', 'owner', 'images/profile/28338442.jpg', 'Male'),
('29008594', 'hannah', 'kibutu', 'wanjiku', '1992-09-17', '+254700742923', '5qk9j1ziZbZCdG1sRnFs2Q==', 'rider', 'images/profile/29008594.jpg', 'Female'),
('29198594', 'sammy', 'kibutu', 'ngo''ng''o', '1990-05-17', '+254780742923', 'mg/y3+aSY/uWTNsCAQS9vg==', 'owner/rider', 'images/profile/29198594.jpg', 'Male'),
('29630951', 'clara', 'nashipai', 'kimeiwua', '1993-04-11', '+254706743707', '+mbCSR/lGmSQzbod6KFZjA==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Female'),
('29948881', 'lynder', 'kandagor', 'morogit', '1994-07-12', '+254707210913', 'Ck4msiDUIvEFJbMAthvtKw==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Female'),
('3', 'johnson', '', 'mbuthia', '1900-01-01', '0717925741', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'owner/rider', 'images/profile/3.jpg', 'Female'),
('30067104', 'kip', 'kip', 'kip', '1998-07-15', '+254719513646', 'IEQHqno0MYlcc5xGvIPc8A==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('31750081', 'Kennedy', '', 'Kori', '1995-01-04', '+254719677197', '8AIZT7wC9D8LXkHgI0n5iQ==', 'owner/rider', 'images/profile/31750081.jpg', 'Male'),
('32003697', 'Stephen', 'Mwangi', 'Thiga', '1995-04-13', '+254712577777', 'ZWbK+Lboj1+FE593hKsJaA==', 'general', 'images/profile/32003697.jpg', 'Male'),
('32323232', 'Johnson', '', 'Mbuthia', '1990-09-01', '+254715949519', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'owner/rider', 'images/profile/32323232.jpg', 'Male'),
('32347245', 'brian', 'kariuki', 'wanjohi', '1994-08-10', '+254700599284', 'tBKu6L6Y5aYbn0U9hOxoyQ==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('33333333', 'Johnson', '', 'johnson', '1998-06-01', '+254717925741', 'JEbyypSihArZLNCeTyWNqg==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('37373737', 'Kamau', 'NJOROGE', 'WAHOME', '1998-06-11', '+254714644636', 'G+F5vxvQ+RWAXk8syKAgTw==', 'general', 'images/profile/37373737.jpg', 'Male'),
('4', 'johnson', '', 'mbuthia', '1998-06-02', '+254717925741', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'owner', 'images/profile/4.jpg', 'Male'),
('44444444', 'dddd', 'dddd', 'dddd', '1998-01-01', '+254717925741', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('5', 'Johnson', '', 'mbuthia', '1998-06-14', '+254717925741', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'rider', 'images/profile/5.jpg', 'Male'),
('55555555', 'Johnson', '', 'Mbuthia', '1998-07-01', '+254715949519', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'rider', 'images/profile/55555555.jpg', 'Male'),
('56565656', 'Johnson', '', 'Mbuthia', '1998-07-08', '+254715949519', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'owner/rider', 'images/profile/56565656.jpg', 'Male'),
('6', 'johnson', '', 'mbuthia', '1998-06-01', '+254717925741', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'general', 'images/profile/6.jpg', 'Male'),
('63215847', 'Johnson', '', 'Mbuthia', '1998-07-01', '+254715949519', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('66666666', 'Johnson', 'johnson', 'johnson', '1990-09-01', '+254717925741', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('66666667', 'Johnson', '', 'Mbuthia', '1998-07-01', '+254715949519', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'owner/rider', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('76543221', 'gavana', 'wa', 'gavana', '1998-06-02', '30278164', 'Yja/1gKOAh7U7pmrvN2WYQ==', 'general', 'images/profile/76543221.png', 'Male'),
('77665544', 'Johnson', '', 'Mbuthia', '1998-07-08', '+254715949519', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'owner', 'images/profile/77665544.jpg', 'Male'),
('77776666', 'Johnson', '', 'Mbuthia', '1998-07-01', '+254715949519', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'owner/rider', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('77777777', 'john', '', 'mbuthia', '1998-06-01', '+254717925741', 'lMgxMBGujeBfONUnZFhqaw==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('8765432', '12345', 'john', '12345', '1980-01-25', 'ghj', '12345', 'general', 'images/profile/defaultprofilepicture.jpg', 'Female'),
('87654321', 'john', 'john', 'john', '1990-01-01', '0715949519', '123', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('88888888', 'Johnson', '', 'Mbuthia', '1998-06-01', '+254717925741', 'sZRpNg6MttWfdu8Epd9Ntw==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('98765412', 'Johnson', 'johnson', 'Johnson', '1998-06-01', '+254717925741', 'i74W2mLVHbYncwMlO0KFig==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('98765432', 'khugy', '', 'jbgygyft', '1998-06-01', '+254715949519', 'WS9jGoUI7zs/3mzcCSuVYQ==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('9955', 'okojhh', 'hugygy', 'hugyyf', '1990-09-30', '0715949519', '123', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('99887766', 'Johnson', '', 'MPOLE', '1990-09-01', '+254715949519', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'owner/rider', 'images/profile/99887766.jpg', 'Male'),
('99999990', 'john', 'mwangi', 'kennedy', '1997-08-05', '+254790878690', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'owner', 'images/profile/defaultprofilepicture.jpg', 'Female'),
('99999999', 'John', 'John', 'John', '1998-06-02', '+254717925741', '7U8Ejnnqjo/atkVwHfEOjA==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('fhhj', 'gffh', 'rrggjj', 'eefett', '1998-06-02', '123', 'hodKjZg6AskDb+WJkTMDqg==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Female'),
('fihihu', 'ihihihi', 'ihihihi', 'ihihihi', '1998-06-05', 'uggygy', 'By6BPoYg4ifiQifRh5IzRg==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('hiihuyu', 'ljojojo', 'oojihihi', 'ihihihi', '1998-06-02', '65659659', 'hodKjZg6AskDb+WJkTMDqg==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Male'),
('j65659', 'gggg', '123', '123', '1998-06-02', '123', 'hodKjZg6AskDb+WJkTMDqg==', 'general', 'images/profile/defaultprofilepicture.jpg', 'Female'),
('jjjjjj', 'gggg', '123', '123', '1998-06-02', '123', 'KQ0LPyBzqvuNoFQFjtzMpw==', 'owner/rider', 'images/profile/defaultprofilepicture.jpg', 'Male');

-- --------------------------------------------------------

--
-- Table structure for table `requestAddBoda`
--

CREATE TABLE IF NOT EXISTS `requestAddBoda` (
  `idNo` varchar(50) NOT NULL,
  `numberPlate` varchar(50) NOT NULL,
  `engineNo` varchar(50) NOT NULL,
  `frameNo` varchar(50) NOT NULL,
  `model` varchar(50) NOT NULL,
  `insuranceExpiryDate` date NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `requestAddBoda`
--

INSERT INTO `requestAddBoda` (`idNo`, `numberPlate`, `engineNo`, `frameNo`, `model`, `insuranceExpiryDate`, `status`) VALUES
('32003697', 'KMCA 130B', 'FF4RD5467887', 'MF3E456C4DWF65778', 'BOXER', '2016-08-19', 'Pending'),
('12345699', 'KMCF 135Q', 'HYGT67890IU7', 'JH87UY67YT5678IJ8', 'TVS', '2016-07-30', 'Approved'),
('28338442', 'KMCF 675R', 'EE4RF65TGYU7', 'XD34ESD2341WQASW2', 'BOXER', '2017-01-01', 'Approved'),
('29008594', 'KMCH 120Q', 'AB5EG1027852', 'MH3ZA21EF7DPF7578', 'SKYGO', '2016-08-31', 'Pending'),
('12345699', 'KMCH 123K', 'JHKIJHUYGTFR', 'JHGYT65RED432WSER', 'HAOJUE', '2016-08-13', 'Approved'),
('28272625', 'KMCH 127D', 'FG7GT2347890', 'MH2A54GH8UGT43456', 'SKYGO', '2016-08-10', 'Approved'),
('32323232', 'KMCJ 567H', 'JHGFDR45678', 'HGJYI876YT657YU8I', 'HAOJUE', '2016-07-30', 'Approved'),
('11111111', 'KMCJ 567Y', 'JHGODR45678', 'HGJYI876YT657YUKK', 'KINGBIRD', '2016-07-30', 'Approved'),
('66666667', 'KMCP 123J', 'JHKLOIU7654', 'HGFTYJHUYHGFRTDF5', 'YAMAHA', '2016-07-05', 'Approved'),
('32323232', 'KMCP 123K', '09JHUGTRESO', 'GHFRTYUIOKJ87OGFR', 'HONDA', '2016-07-06', 'Approved'),
('32323232', 'KMCP 123L', '09JHUGTRESW', 'GHFRTYUIOKJ876GFR', 'HONDA', '2016-07-06', 'Approved'),
('jjjjjj', 'KMCP 123O', 'LKHHGJUOI8UYP', 'NJUI8UY6T54RE432K', 'TVS', '2016-07-12', 'Approved'),
('77776666', 'KMCP 123U', 'JHFLKIUTJ456', 'FGHJRT546678IUJKO', 'LIFAN', '2016-07-06', 'Approved'),
('11223344', 'KMCQ 120S', 'JO9I8U7Y6TGHK', 'KLOI90OKLP098UY6T', 'TVS', '2016-07-20', 'Approved'),
('31750081', 'KMCQ 147S', 'FF5DF1868535', 'MD2A6595955615616', 'DUCATI', '2017-07-20', 'Approved'),
('12341234', 'KMCQ 220S', 'KJHUYIOKJNH', 'LJIUOLPJHYGT56789', 'KINGBIRD', '2016-07-05', 'Approved'),
('12341234', 'KMCQ 320S', 'KJHUYIOKJNH', 'LJIUOLPJHYGT56786', 'KINGBIRD', '2016-07-05', 'Approved'),
('56565656', 'KMCV 108U', 'JUHY7K8906K', 'LNHY65KRDE457890J', 'SUZUKI', '2016-07-24', 'Approved'),
('77665544', 'KMCV 109U', 'JUHY7689098', 'LNHY654RDE45789UU', 'YAMAHA', '2016-07-24', 'Approved'),
('99887766', 'KMCV 125M', 'KJU78IUY6TR9', 'JHU76YT5R4EWSDFG7', 'TVS', '2016-07-21', 'Approved'),
('11223344', 'KMCV 125O', 'KJU78IUY6TRP', 'JHU76YT5R4EWSDFG9', 'TVS', '2016-07-21', 'Approved'),
('99887766', 'KMCV 125P', 'JUHY7689065', 'LNHY654RDE457890O', 'YAMAHA', '2016-07-24', 'Approved'),
('12345', 'KMCV 125U', 'KJU78IUY6TRI', 'JHU76YT5R4EWSDFGJ', 'TVS', '2017-07-05', 'Approved'),
('12345', 'KMCV 125Y', 'KJU78IUY6TRU', 'JHU76YT5R4EWSDFGO', 'TVS', '2017-07-05', 'Approved'),
('56565656', 'KMCV 128U', 'JUHY768906K', 'LNHY654RDE457890J', 'SUZUKI', '2016-07-24', 'Approved'),
('77665544', 'KMCV 129I', 'JUHY768906O', 'LNHY654RDE457890L', 'YAMAHA', '2016-07-25', 'Approved'),
('99887766', 'KMCV 129U', 'JUHY76890O5', 'LNHY654RDE457890L', 'SUZUKI', '2016-07-24', 'Approved'),
('27400003', 'KMDC 120M', 'SFDF1234567', 'BKHJHVHJG12345672', 'BOXER', '2016-08-12', 'Pending'),
('25798025', 'KMGB 108L', '567484888825', '897989040089SSSBB', 'BMW', '2017-08-03', 'Approved'),
('29198594', 'KMJH 154C', 'NJUYT7865MKU', 'BGTRFDJU78654NJUY', 'SKYGO', '2016-07-08', 'Approved'),
('29198594', 'KMTH 154C', 'KJUYT2345GTR', 'BGTRFDJU78654NJUY', 'SKYGO', '2016-07-08', 'Approved'),
('99999990', 'KMTI 334O', 'WBA121214TU', 'WBA121214TTYTY671', 'BMW', '2016-08-10', 'Approved'),
('28024582', 'KMVF 876U', '123456789123', '12345678901234567', 'TVS', '2016-08-08', 'Pending'),
('11111111', 'KMVG 125I', 'JHNBGFTYUIOK', 'NBHY7890OIUHY765L', 'YAMAHA', '2016-08-13', 'Approved');

-- --------------------------------------------------------

--
-- Table structure for table `requestAddDrivingLicense`
--

CREATE TABLE IF NOT EXISTS `requestAddDrivingLicense` (
  `idNo` varchar(50) NOT NULL,
  `drivingLicenseNo` text NOT NULL,
  `referenceNo` varchar(50) NOT NULL,
  `drivingLicenseExpiryDate` date NOT NULL,
  `isAssigned` varchar(50) NOT NULL,
  `status` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `requestAddDrivingLicense`
--

INSERT INTO `requestAddDrivingLicense` (`idNo`, `drivingLicenseNo`, `referenceNo`, `drivingLicenseExpiryDate`, `isAssigned`, `status`) VALUES
('32323232', '1111111', '1456895(GHJ-456)', '2016-09-07', 'false', 'Approved'),
('29198594', '1452348', '1931768(QNC-167)', '2016-07-08', 'false', 'Approved'),
('31750081', '1655845', '5698716(SNJ-656)', '2016-10-04', 'false', 'Approved'),
('01234567', '2254568', '5468975(JHU-125)', '2016-08-31', 'false', 'Approved'),
('25798025', '2908384', '3890049(SYU-994)', '2017-08-01', 'false', 'Approved'),
('29008594', '3456789', '1094747(FDG-665)', '2018-08-16', 'false', 'Approved'),
('99887766', '4555889', '2546897(KLJ-456)', '2016-07-21', 'false', 'Approved'),
('66666667', '4568795', '6589524(LKJ-546)', '2016-09-06', 'false', 'Approved'),
('11111111', '4586322', '5862545(HJU-456)', '2016-07-30', 'false', 'Approved'),
('11223344', '5468792', '0989737(SJN-048)', '2016-09-06', 'false', 'Approved'),
('jjjjjj', '5558966', '0989737(SJN-008)', '2019-07-07', 'false', 'Approved'),
('77776666', '8795264', '5469875(FGH-458)', '2016-09-21', 'false', 'Approved'),
('55555555', '8975462', '4578965(JUY-589)', '2016-07-29', 'false', 'Approved');

-- --------------------------------------------------------

--
-- Table structure for table `riderMotorbikeAndOwner`
--

CREATE TABLE IF NOT EXISTS `riderMotorbikeAndOwner` (
  `riderIdNo` varchar(50) NOT NULL,
  `motorbikeNumberPlate` varchar(50) NOT NULL,
  `ownerIdNumber` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `riderMotorbikeAndOwner`
--

INSERT INTO `riderMotorbikeAndOwner` (`riderIdNo`, `motorbikeNumberPlate`, `ownerIdNumber`) VALUES
('11111111', 'KMCF 135Q', '12345699'),
('jjjjjj', 'KMCH 123K', '12345699'),
('29008594', 'KMCH 127D', '28272625'),
('99887766', 'KMCP 123O', 'jjjjjj'),
('31750081', 'KMCQ 147S', '31750081'),
('11223344', 'KMCV 125O', '11223344'),
('25798025', 'KMGB 108L', '25798025'),
('29198594', 'KMJH 154C', '29198594');

-- --------------------------------------------------------

--
-- Table structure for table `riderMotorbikeAndOwnerHistory`
--

CREATE TABLE IF NOT EXISTS `riderMotorbikeAndOwnerHistory` (
  `riderIdNo` varchar(50) NOT NULL,
  `motorbikeNumberPlate` varchar(50) NOT NULL,
  `ownerIdNumber` varchar(50) NOT NULL,
  `timeAssigned` datetime NOT NULL DEFAULT current_timestamp(),
  `timeUnAssigned` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `riderMotorbikeAndOwnerHistory`
--

INSERT INTO `riderMotorbikeAndOwnerHistory` (`riderIdNo`, `motorbikeNumberPlate`, `ownerIdNumber`, `timeAssigned`, `timeUnAssigned`) VALUES
('11111111', 'KMCF 135Q', '12345699', '2016-07-29 10:26:22', NULL),
('77776666', 'KMCH 123K', '12345699', '2016-08-06 19:19:51', '2016-08-06 19:28:47'),
('jjjjjj', 'KMCH 123K', '12345699', '2016-08-06 19:29:49', NULL),
('31750081', 'KMCH 127D', '28272625', '2016-07-21 10:53:18', '2016-08-06 19:43:13'),
('29008594', 'KMCH 127D', '28272625', '2016-08-11 13:46:33', NULL),
('55555555', 'KMCJ 567Y', '11111111', '2016-07-25 16:12:34', '2016-08-05 08:36:24'),
('99887766', 'KMCP 123J', '66666667', '2016-07-20 23:47:03', '2016-07-20 23:49:26'),
('11223344', 'KMCP 123K', '32323232', '2016-07-16 14:10:54', NULL),
('77776666', 'KMCP 123O', 'jjjjjj', '2016-07-20 23:45:44', '2016-07-20 23:49:35'),
('99887766', 'KMCP 123O', 'jjjjjj', '2016-07-20 23:51:23', NULL),
('32323232', 'KMCQ 120S', '11223344', '2016-07-17 02:33:40', '2016-07-20 23:22:00'),
('01234567', 'KMCQ 147S', '31750081', '2016-08-08 21:33:56', '2016-08-12 12:51:54'),
('31750081', 'KMCQ 147S', '31750081', '2016-08-12 12:52:13', NULL),
('jjjjjj', 'KMCQ 320S', '12341234', '2016-07-16 14:03:51', '2016-07-20 23:22:12'),
('jjjjjj', 'KMCQ 320S', '12341234', '2016-07-17 02:42:28', '2016-07-20 23:46:45'),
('66666667', 'KMCQ 320S', '12341234', '2016-07-20 22:52:23', NULL),
('99887766', 'KMCQ 320S', '12341234', '2016-07-20 23:46:26', NULL),
('99887766', 'KMCV 125M', '99887766', '2016-07-20 23:29:07', '2016-07-20 23:30:27'),
('32323232', 'KMCV 125M', '99887766', '2016-07-20 23:30:55', NULL),
('77776666', 'KMCV 125O', '11223344', '2016-07-20 23:19:17', '2016-07-20 23:37:34'),
('77776666', 'KMCV 125O', '11223344', '2016-07-20 23:50:41', '2016-07-25 16:13:07'),
('77776666', 'KMCV 125O', '11223344', '2016-07-25 16:54:12', '2016-07-29 15:55:47'),
('55555555', 'KMCV 125O', '11223344', '2016-08-05 16:09:29', '2016-08-13 12:15:53'),
('55555555', 'KMCV 125O', '11223344', '2016-08-13 12:16:36', '2016-08-13 12:30:18'),
('11223344', 'KMCV 125O', '11223344', '2016-08-13 12:30:46', NULL),
('jjjjjj', 'KMCV 125U', '12345', '2016-07-20 23:38:00', '2016-07-20 23:49:43'),
('jjjjjj', 'KMCV 125U', '12345', '2016-07-20 23:51:08', '2016-08-06 19:29:09'),
('11111111', 'KMCV 125Y', '12345', '2016-07-21 10:51:02', '2016-07-29 10:24:18'),
('25798025', 'KMGB 108L', '25798025', '2016-08-18 16:41:22', NULL),
('29198594', 'KMJH 154C', '29198594', '2016-07-21 10:52:07', NULL),
('25798025', 'KMTI 334O', '99999990', '2016-08-18 16:26:56', '2016-08-18 16:40:57'),
('31750081', 'KMVG 125I', '11111111', '2016-08-06 19:43:30', '2016-08-12 12:48:29');

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE IF NOT EXISTS `transactions` (
  `transactionId` varchar(50) NOT NULL,
  `dateDeposited` date NOT NULL,
  `nameOfDepositor` varchar(50) NOT NULL,
  `idNoOfDepositor` varchar(8) NOT NULL,
  `numberPlate` varchar(10) NOT NULL,
  `amount` double NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`transactionId`, `dateDeposited`, `nameOfDepositor`, `idNoOfDepositor`, `numberPlate`, `amount`, `timestamp`) VALUES
('154poi89um', '2016-08-05', 'Johnson Mbuthia', '55555555', 'KMCV 125O', 127, '2016-08-06 14:42:54'),
('29008594sam', '2016-08-11', 'hannah wanjiku', '29008594', 'KMCH 127D', 300, '2016-08-11 10:52:49'),
('312531253125', '2016-08-10', 'Kennedy Kori', '31750081', 'KMVG 125I', 5000, '2016-08-10 14:02:30'),
('312531253126', '2016-08-12', 'Kennedy Kori', '31750081', 'KMVG 125I', 11.1, '2016-08-12 09:26:51'),
('312531253127', '2016-08-12', 'Kennedy Kori', '31750081', 'KMVG 125I', 110.133, '2016-08-12 09:41:33'),
('3225322529', '2016-08-15', 'Kennedy Kori', '31750081', 'KMCQ 147S', 55.666666666666664, '2016-08-15 12:23:22'),
('322532253220', '2016-08-15', 'Kennedy Kori', '31750081', 'KMCQ 147S', 56.656666666666666, '2016-08-15 12:26:19'),
('322532253227', '2016-08-15', 'Kennedy Kori', '31750081', 'KMCQ 147S', 655.36, '2016-08-15 12:20:03'),
('322532253228', '2016-08-15', 'Kennedy Kori', '31750081', 'KMCQ 147S', 2564.01, '2016-08-15 12:21:47'),
('322532253229', '2016-08-15', 'Kennedy Kori', '31750081', 'KMCQ 147S', 2564.1, '2016-08-15 12:21:59'),
('BFDR87JHY890', '2016-08-12', 'Kennedy Kori', '31750081', 'KMCQ 147S', 251.215689, '2016-08-13 10:21:06'),
('cdftyhju76', '2016-08-06', 'Johnson Mbuthia', '55555555', 'KMCV 125O', 20, '2016-08-06 11:46:51'),
('ddfrew2345', '2016-08-13', 'Johnson Mbuthia', '11223344', 'KMCV 125O', 125.1025988, '2016-08-13 09:33:26'),
('frtyhgh654', '2016-08-05', 'JohnsonMbuthia', '55555555', 'KMCV 125O', 280, '2016-08-06 11:43:48'),
('gfre3245tg', '2016-08-07', 'Johnson Mbuthia', '55555555', 'KMCV 125O', 123.14589, '2016-08-13 08:56:52'),
('hgf876ty5rs', '2016-07-13', 'Johnson Mbuthia', '11223344', 'KMCV 125O', 25.254, '2016-08-13 14:54:58'),
('hgytnjhuyt', '2016-08-06', 'Johnson Mbuthia', '55555555', 'KMCV 125O', 256, '2016-08-06 14:49:37'),
('hjuikop987', '2016-08-10', 'Johnson Mbuthia', '11223344', 'KMCV 125O', 153.4589, '2016-08-13 14:22:18'),
('hkhlkfhuduui455', '2016-08-18', 'faith lelgo', '25798025', 'KMGB 108L', 2000, '2016-08-18 13:45:58'),
('hsfty34kji', '2016-08-13', 'Johnson Mbuthia', '11223344', 'KMCV 125O', 45.124584, '2016-08-13 09:35:46'),
('huloiu8765', '2016-08-03', 'Johnson Mbuthia', '11223344', 'KMCV 125O', 125.3698, '2016-08-13 14:37:38'),
('jhiuyt6543', '2016-08-04', 'Johnson Mbuthia', '11223344', 'KMCV 125O', 55.3254, '2016-08-13 14:51:53'),
('jhugytrfde', '2016-08-12', 'Johnson Mbuthia', '11223344', 'KMCV 125O', 254.32569, '2016-08-13 14:40:13'),
('jhuyt65r4e', '2016-08-03', 'Johnson Mbuthia', '11223344', 'KMCV 125O', 253.6589, '2016-08-13 14:28:37'),
('jhuytfr6789', '2016-08-03', 'Johnson Mbuthia', '11223344', 'KMCV 125O', 128.3256, '2016-08-13 14:46:30'),
('jhuytre456', '2016-08-01', 'Johnson Mbuthia', '11223344', 'KMCV 125O', 253.32568, '2016-08-13 14:34:34'),
('jihuytgfrt', '2016-07-29', 'Johnson Mbuthia', '11111111', 'KMCF 135Q', 201, '2016-08-06 15:27:46'),
('kjiuoiu789', '2016-08-10', 'Johnson Mbuthia', '11223344', 'KMCV 125O', 123.457, '2016-08-13 13:07:56'),
('kjiuyt76r5', '2016-08-05', 'Johnson Mbuthia', '55555555', 'KMCV 125O', 147, '2016-08-06 14:47:06'),
('lkiojhy765', '2016-08-06', 'Johnson Mbuthia', '55555555', 'KMCV 125O', 320, '2016-08-06 14:39:14'),
('mjhgyt6543', '2016-08-11', 'Johnson Mbuthia', '55555555', 'KMCV 125O', 511.25489, '2016-08-13 08:42:02'),
('QWEDFGT456', '2016-08-08', 'Johnson Mbuthia', '01234567', 'KMCQ 147S', 254, '2016-08-08 18:46:23'),
('sgdii373838088', '2016-08-18', 'faith lelgo', '25798025', 'KMTI 334O', 500, '2016-08-18 13:32:38'),
('sjhhgriughjfgj', '2016-08-18', 'faith lelgo', '25798025', 'KMGB 108L', 1500, '2016-08-18 13:44:21');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`accountNo`);

--
-- Indexes for table `changePasswordHistory`
--
ALTER TABLE `changePasswordHistory`
  ADD PRIMARY KEY (`idNo`,`date`);

--
-- Indexes for table `drivingLicenseDetails`
--
ALTER TABLE `drivingLicenseDetails`
  ADD PRIMARY KEY (`drivingLicenseNo`),
  ADD UNIQUE KEY `referenceNo` (`referenceNo`);

--
-- Indexes for table `forgotPassword`
--
ALTER TABLE `forgotPassword`
  ADD PRIMARY KEY (`idNo`);

--
-- Indexes for table `forgotPasswordHistory`
--
ALTER TABLE `forgotPasswordHistory`
  ADD PRIMARY KEY (`idNo`,`date`);

--
-- Indexes for table `helpAllVisitors`
--
ALTER TABLE `helpAllVisitors`
  ADD PRIMARY KEY (`phone`,`time`);

--
-- Indexes for table `helpAllVisitorsHistory`
--
ALTER TABLE `helpAllVisitorsHistory`
  ADD PRIMARY KEY (`phone`,`time`);

--
-- Indexes for table `helpRegisteredUsers`
--
ALTER TABLE `helpRegisteredUsers`
  ADD PRIMARY KEY (`idNo`,`time`);

--
-- Indexes for table `helpRegisteredUsersHistory`
--
ALTER TABLE `helpRegisteredUsersHistory`
  ADD PRIMARY KEY (`idNo`,`time`);

--
-- Indexes for table `motorbikeAndOwner`
--
ALTER TABLE `motorbikeAndOwner`
  ADD PRIMARY KEY (`idNo`,`numberPlate`);

--
-- Indexes for table `motorcycleDetails`
--
ALTER TABLE `motorcycleDetails`
  ADD PRIMARY KEY (`numberPlate`),
  ADD UNIQUE KEY `UNIQUE` (`engineNo`,`frameNo`);

--
-- Indexes for table `registrationDetails`
--
ALTER TABLE `registrationDetails`
  ADD PRIMARY KEY (`idNo`);

--
-- Indexes for table `requestAddBoda`
--
ALTER TABLE `requestAddBoda`
  ADD PRIMARY KEY (`numberPlate`),
  ADD UNIQUE KEY `engineNo` (`engineNo`,`frameNo`);

--
-- Indexes for table `requestAddDrivingLicense`
--
ALTER TABLE `requestAddDrivingLicense`
  ADD PRIMARY KEY (`drivingLicenseNo`(50)),
  ADD UNIQUE KEY `referenceNo` (`referenceNo`);

--
-- Indexes for table `riderMotorbikeAndOwner`
--
ALTER TABLE `riderMotorbikeAndOwner`
  ADD PRIMARY KEY (`riderIdNo`,`ownerIdNumber`),
  ADD UNIQUE KEY `motorbikeNumberPlate` (`motorbikeNumberPlate`);

--
-- Indexes for table `riderMotorbikeAndOwnerHistory`
--
ALTER TABLE `riderMotorbikeAndOwnerHistory`
  ADD PRIMARY KEY (`motorbikeNumberPlate`,`timeAssigned`);

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`transactionId`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
