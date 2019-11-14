-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)
--
-- Host: localhost    Database: tiantian_canteen
-- ------------------------------------------------------
-- Server version	5.7.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tb_code`
--

DROP TABLE IF EXISTS `tb_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_code` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `no` varchar(100) NOT NULL COMMENT '订单编号',
  `code` varchar(6) NOT NULL COMMENT '取餐码(六位纯数字)',
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态（0：未使用，1：已使用）',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='取餐码表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_code`
--

LOCK TABLES `tb_code` WRITE;
/*!40000 ALTER TABLE `tb_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_code` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_menu`
--

DROP TABLE IF EXISTS `tb_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(100) NOT NULL COMMENT '菜单名',
  `img` varchar(100) NOT NULL COMMENT '菜品图片路径',
  `price` float NOT NULL DEFAULT '0' COMMENT '菜品价格',
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态（0：未上架，1：已上架）',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='菜品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_menu`
--

LOCK TABLES `tb_menu` WRITE;
/*!40000 ALTER TABLE `tb_menu` DISABLE KEYS */;
INSERT INTO `tb_menu` VALUES (1,'锅包肉','https://img.zcool.cn/community/01a92a5a151826a80120518742bb1d.JPG',32,0,'2019-10-31 13:14:49'),(2,'宫保鸡丁','https://img.zcool.cn/community/01a92a5a151826a80120518742bb1d.JPG',24,0,'2019-10-31 13:22:43'),(3,'123','http://localhost:8081/img/9a579bb0-443a-4988-8115-1df04a4adf19.jpg',29.9,0,'2019-11-06 09:03:23');
/*!40000 ALTER TABLE `tb_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_news`
--

DROP TABLE IF EXISTS `tb_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `title` varchar(100) NOT NULL COMMENT '新闻标题',
  `img` varchar(100) DEFAULT NULL COMMENT '新闻配图路径',
  `content` varchar(255) DEFAULT NULL COMMENT '内容',
  `author` varchar(100) NOT NULL COMMENT '作者署名',
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态（0：未发布，1：已发布）',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='新闻表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_news`
--

LOCK TABLES `tb_news` WRITE;
/*!40000 ALTER TABLE `tb_news` DISABLE KEYS */;
INSERT INTO `tb_news` VALUES (1,'开业了','',NULL,'管理员',0,'2019-11-14 07:51:43');
/*!40000 ALTER TABLE `tb_news` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_order`
--

DROP TABLE IF EXISTS `tb_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `no` varchar(100) NOT NULL COMMENT '订单编号',
  `uid` bigint(20) NOT NULL COMMENT '用户表主键',
  `price` float NOT NULL COMMENT '价格',
  `comment` varchar(100) DEFAULT NULL COMMENT '评价（订单状态为1后可进行评价）',
  `state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0：进行中，1：已完成）',
  `code` varchar(6) NOT NULL COMMENT '取餐码',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `tb_order_tb_user_fk` (`uid`),
  KEY `no` (`no`),
  CONSTRAINT `tb_order_tb_user_fk` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_order`
--

LOCK TABLES `tb_order` WRITE;
/*!40000 ALTER TABLE `tb_order` DISABLE KEYS */;
INSERT INTO `tb_order` VALUES (2,'2a0be9d1cc4b475a8aeab272df5986ac',3,59,'',0,'349352','2019-10-31 13:23:05');
/*!40000 ALTER TABLE `tb_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_orderitem`
--

DROP TABLE IF EXISTS `tb_orderitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_orderitem` (
  `id` bigint(13) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `no` varchar(100) NOT NULL COMMENT '订单编号',
  `mid` bigint(20) NOT NULL COMMENT '菜谱id',
  `amount` int(11) NOT NULL COMMENT '数量',
  `price` float NOT NULL COMMENT '总价',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `fk_mid` (`mid`),
  KEY `fk_no` (`no`),
  CONSTRAINT `fk_mid` FOREIGN KEY (`mid`) REFERENCES `tb_menu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='订单详情表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_orderitem`
--

LOCK TABLES `tb_orderitem` WRITE;
/*!40000 ALTER TABLE `tb_orderitem` DISABLE KEYS */;
INSERT INTO `tb_orderitem` VALUES (4,'2a0be9d1cc4b475a8aeab272df5986ac',1,2,64,'2019-10-31 13:23:05'),(5,'2a0be9d1cc4b475a8aeab272df5986ac',2,1,24,'2019-10-31 13:23:05');
/*!40000 ALTER TABLE `tb_orderitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `username` varchar(100) NOT NULL COMMENT '用户名（学号/工号）',
  `passwrod` varchar(100) NOT NULL COMMENT '密码（MD5加密）',
  `nickname` varchar(100) NOT NULL COMMENT '昵称(默认为用户名)',
  `sex` tinyint(4) NOT NULL DEFAULT '1' COMMENT '性别（男: 1,女: 0）',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '类别（教师：0，学生：1，管理员：2）',
  `balance` float NOT NULL DEFAULT '0' COMMENT '账户余额',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (1,'admin','21232f297a57a5a743894a0e4a801fc3','ADMIN',1,2,0,'2019-10-29 09:56:47'),(2,'TT000','e10adc3949ba59abbe56e057f20f883e','TT000_教师',0,0,0,'2019-10-29 13:04:31'),(3,'TT001','e10adc3949ba59abbe56e057f20f883e','TT001_学生',0,1,0,'2019-10-29 13:04:48'),(4,'TEST_01','e10adc3949ba59abbe56e057f20f883e','TEST_01',1,1,0,'2019-11-06 04:29:55'),(5,'TEST_02','e10adc3949ba59abbe56e057f20f883e','TEST_02',1,1,0,'2019-11-06 04:30:03'),(6,'TEST_03','e10adc3949ba59abbe56e057f20f883e','TEST_03',1,1,0,'2019-11-06 04:30:11'),(7,'TEST_04','e10adc3949ba59abbe56e057f20f883e','TEST_05',1,1,0,'2019-11-06 04:30:20'),(8,'TEST_05','e10adc3949ba59abbe56e057f20f883e','TEST_05',1,1,0,'2019-11-06 04:30:26'),(9,'TEST_06','e10adc3949ba59abbe56e057f20f883e','TEST_06',1,1,0,'2019-11-06 04:30:41'),(10,'TEST_07','e10adc3949ba59abbe56e057f20f883e','TEST_07',1,1,0,'2019-11-06 04:30:50'),(11,'TEST_08','e10adc3949ba59abbe56e057f20f883e','TEST_08',1,1,0,'2019-11-06 04:30:58'),(12,'TEST_09','e10adc3949ba59abbe56e057f20f883e','TEST_09',1,1,0,'2019-11-06 05:15:12'),(13,'TEST_10','e10adc3949ba59abbe56e057f20f883e','TEST_10',1,1,0,'2019-11-06 05:15:20');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'tiantian_canteen'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-14 15:56:21
