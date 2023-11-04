-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: qlttnn
-- ------------------------------------------------------
-- Server version	8.0.32
CREATE DATABASE qlttnn;

USE qlttnn;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `giangvien`
--

DROP TABLE IF EXISTS `giangvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giangvien` (
  `maGiangVien` char(8) NOT NULL,
  `hoten` varchar(45) NOT NULL,
  `gioitinh` char(1) NOT NULL,
  `ngaysinh` date NOT NULL,
  `diachi` varchar(50) NOT NULL,
  `sdt` char(10) NOT NULL,
  `email` varchar(45) NOT NULL,
  `chuyennganh` varchar(45) NOT NULL,
  `kinhnghiemgd` int NOT NULL,
  `matkhau` varchar(45) NOT NULL,
  `ngaybatdau` date NOT NULL,
  `ngayketthuc` date DEFAULT NULL,
  `avatar` varchar(100) NOT NULL,
  `is_admin` tinyint NOT NULL,
  `trangthai` varchar(20) NOT NULL,
  PRIMARY KEY (`maGiangVien`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giangvien`
--

LOCK TABLES `giangvien` WRITE;
/*!40000 ALTER TABLE `giangvien` DISABLE KEYS */;
INSERT INTO `giangvien` VALUES ('A1234567','Nguyễn Văn A','M','1989-04-20','Hưng Lợi, Ninh Kiều, CT','0893462781','nva@ctu.edu.vn','CNTT',5,'123','2012-02-12',NULL,'abc',1,'đang hoạt động');
/*!40000 ALTER TABLE `giangvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hocvien`
--

DROP TABLE IF EXISTS `hocvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hocvien` (
  `maHocVien` char(8) NOT NULL,
  `hoten` varchar(45) NOT NULL,
  `gioitinh` char(1) NOT NULL,
  `ngaysinh` date NOT NULL,
  `sdt` char(10) NOT NULL,
  `email` varchar(45) NOT NULL,
  `diachi` varchar(50) NOT NULL,
  `ngaydangky` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `trangthai` varchar(20) NOT NULL,
  `avatar` varchar(100) NOT NULL,
  `maLop` char(8) NOT NULL,
  PRIMARY KEY (`maHocVien`),
  KEY `maLop_idx` (`maLop`),
  CONSTRAINT `maLop` FOREIGN KEY (`maLop`) REFERENCES `lop` (`maLop`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hocvien`
--

LOCK TABLES `hocvien` WRITE;
/*!40000 ALTER TABLE `hocvien` DISABLE KEYS */;
INSERT INTO `hocvien` VALUES ('B2014802','Nguyễn Thanh Trúc','F','2002-04-28','0832748451','trucb2014802@student.ctu.edu.vn','Hưng Lợi, Ninh Kiều, CT','2023-10-21 14:19:16','đã bắt đầu học','acv','pre123');
/*!40000 ALTER TABLE `hocvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `khoahoc`
--

DROP TABLE IF EXISTS `khoahoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `khoahoc` (
  `maKhoaHoc` char(8) NOT NULL,
  `ten` varchar(45) NOT NULL,
  `mota` varchar(100) DEFAULT NULL,
  `thoigianhoc` int NOT NULL,
  `hocphi` float NOT NULL,
  `ngaybatdau` date NOT NULL,
  `ngayketthuc` date NOT NULL,
  `soluonghv_toida` int NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `trangthai` varchar(20) NOT NULL,
  PRIMARY KEY (`maKhoaHoc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `khoahoc`
--

LOCK TABLES `khoahoc` WRITE;
/*!40000 ALTER TABLE `khoahoc` DISABLE KEYS */;
INSERT INTO `khoahoc` VALUES ('pre0123','Lớp căn bản AV','Dạy căn bản tiếng anh',2,1200000,'2023-09-10','2023-11-10',40,'2023-10-21 14:13:04','đã bắt đầu');
/*!40000 ALTER TABLE `khoahoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lichhoc`
--

DROP TABLE IF EXISTS `lichhoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lichhoc` (
  `maPhongHoc` char(8) NOT NULL,
  `Thu` char(8) NOT NULL,
  `maLop` char(8) NOT NULL,
  `thoigian` varchar(20) NOT NULL,
  KEY `maPhongHoc_idx` (`maPhongHoc`),
  KEY `Thu_idx` (`Thu`),
  KEY `maLop_idx` (`maLop`),
  CONSTRAINT `cua_lop` FOREIGN KEY (`maLop`) REFERENCES `lop` (`maLop`),
  CONSTRAINT `maPhongHoc` FOREIGN KEY (`maPhongHoc`) REFERENCES `phonghoc` (`maPhongHoc`),
  CONSTRAINT `Thu` FOREIGN KEY (`Thu`) REFERENCES `thutrongtuan` (`Thu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lichhoc`
--

LOCK TABLES `lichhoc` WRITE;
/*!40000 ALTER TABLE `lichhoc` DISABLE KEYS */;
INSERT INTO `lichhoc` VALUES ('P102','Mon','pre123','7:00-8:30am');
/*!40000 ALTER TABLE `lichhoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loaitailieu`
--

DROP TABLE IF EXISTS `loaitailieu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loaitailieu` (
  `maLoaiTaiLieu` char(8) NOT NULL,
  `ten` varchar(45) NOT NULL,
  PRIMARY KEY (`maLoaiTaiLieu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loaitailieu`
--

LOCK TABLES `loaitailieu` WRITE;
/*!40000 ALTER TABLE `loaitailieu` DISABLE KEYS */;
INSERT INTO `loaitailieu` VALUES ('DOCX','dạng word'),('PDF','dạng PDF');
/*!40000 ALTER TABLE `loaitailieu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lop`
--

DROP TABLE IF EXISTS `lop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lop` (
  `maLop` char(8) NOT NULL,
  `trangthai` varchar(20) NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `maGiangVien` char(8) NOT NULL,
  `maKhoaHoc` char(8) NOT NULL,
  PRIMARY KEY (`maLop`),
  KEY `maKhoaHoc_idx` (`maKhoaHoc`),
  KEY `maGiangVien_idx` (`maGiangVien`),
  CONSTRAINT `maGiangVien` FOREIGN KEY (`maGiangVien`) REFERENCES `giangvien` (`maGiangVien`),
  CONSTRAINT `maKhoaHoc` FOREIGN KEY (`maKhoaHoc`) REFERENCES `khoahoc` (`maKhoaHoc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lop`
--

LOCK TABLES `lop` WRITE;
/*!40000 ALTER TABLE `lop` DISABLE KEYS */;
INSERT INTO `lop` VALUES ('pre123','đang hoạt động','2023-10-21 14:17:20','A1234567','pre0123');
/*!40000 ALTER TABLE `lop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phonghoc`
--

DROP TABLE IF EXISTS `phonghoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phonghoc` (
  `maPhongHoc` char(8) NOT NULL,
  `trangthai` varchar(20) NOT NULL,
  PRIMARY KEY (`maPhongHoc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phonghoc`
--

LOCK TABLES `phonghoc` WRITE;
/*!40000 ALTER TABLE `phonghoc` DISABLE KEYS */;
INSERT INTO `phonghoc` VALUES ('P101','trống'),('P102','trống'),('P103','trống');
/*!40000 ALTER TABLE `phonghoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tailieu`
--

DROP TABLE IF EXISTS `tailieu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tailieu` (
  `maTaiLieu` char(8) NOT NULL,
  `ten` varchar(45) NOT NULL,
  `gia` float NOT NULL,
  `nguon` varchar(100) NOT NULL,
  `mota` varchar(100) DEFAULT NULL,
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `maLoaiTaiLieu` char(8) NOT NULL,
  PRIMARY KEY (`maTaiLieu`),
  KEY `maLoaiTaiLieu_idx` (`maLoaiTaiLieu`),
  CONSTRAINT `maLoaiTaiLieu` FOREIGN KEY (`maLoaiTaiLieu`) REFERENCES `loaitailieu` (`maLoaiTaiLieu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tailieu`
--

LOCK TABLES `tailieu` WRITE;
/*!40000 ALTER TABLE `tailieu` DISABLE KEYS */;
INSERT INTO `tailieu` VALUES ('PDF123','Giáo trình nhập môn',20000,'Trung tâm NN',NULL,'2023-10-21 14:21:08','2023-10-21 14:21:08','PDF');
/*!40000 ALTER TABLE `tailieu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tailieu_khoahoc`
--

DROP TABLE IF EXISTS `tailieu_khoahoc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tailieu_khoahoc` (
  `create_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ghichu` varchar(100) DEFAULT NULL,
  `maTaiLieu` char(8) NOT NULL,
  `maKhoaHoc` char(8) NOT NULL,
  KEY `maTaiLieu_idx` (`maTaiLieu`),
  KEY `maKhoaHoc_idx` (`maKhoaHoc`),
  CONSTRAINT `maTaiLieu` FOREIGN KEY (`maTaiLieu`) REFERENCES `tailieu` (`maTaiLieu`),
  CONSTRAINT `Thuoc` FOREIGN KEY (`maKhoaHoc`) REFERENCES `khoahoc` (`maKhoaHoc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tailieu_khoahoc`
--

LOCK TABLES `tailieu_khoahoc` WRITE;
/*!40000 ALTER TABLE `tailieu_khoahoc` DISABLE KEYS */;
INSERT INTO `tailieu_khoahoc` VALUES ('2023-10-21 14:25:23',NULL,'PDF123','pre0123');
/*!40000 ALTER TABLE `tailieu_khoahoc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `thutrongtuan`
--

DROP TABLE IF EXISTS `thutrongtuan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `thutrongtuan` (
  `Thu` char(8) NOT NULL,
  PRIMARY KEY (`Thu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `thutrongtuan`
--

LOCK TABLES `thutrongtuan` WRITE;
/*!40000 ALTER TABLE `thutrongtuan` DISABLE KEYS */;
INSERT INTO `thutrongtuan` VALUES ('Fri'),('Mon'),('Sat'),('Sun'),('Thu'),('Tue'),('Wed');
/*!40000 ALTER TABLE `thutrongtuan` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-10-21 21:34:38
