-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)
--
-- Host: localhost    Database: wxt_bmt
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
-- Table structure for table `tb_category`
--

DROP TABLE IF EXISTS `tb_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(100) NOT NULL COMMENT '类别名',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='类别表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_category`
--

LOCK TABLES `tb_category` WRITE;
/*!40000 ALTER TABLE `tb_category` DISABLE KEYS */;
INSERT INTO `tb_category` VALUES (1,'炒菜','2019-12-03 14:38:10'),(3,'凉菜','2019-12-03 14:39:45');
/*!40000 ALTER TABLE `tb_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_desk`
--

DROP TABLE IF EXISTS `tb_desk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_desk` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(100) NOT NULL COMMENT '台号名',
  `state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0：空闲，1：在用）',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='台号表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_desk`
--

LOCK TABLES `tb_desk` WRITE;
/*!40000 ALTER TABLE `tb_desk` DISABLE KEYS */;
INSERT INTO `tb_desk` VALUES (1,'1桌',0,'2019-12-03 14:08:16'),(2,'2桌',0,'2019-12-03 14:09:09');
/*!40000 ALTER TABLE `tb_desk` ENABLE KEYS */;
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
  `state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0：未上架，1：已上架）',
  `cid` bigint(20) DEFAULT NULL COMMENT '分类外键',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `tb_menu_tb_category_fk` (`cid`),
  CONSTRAINT `tb_menu_tb_category_fk` FOREIGN KEY (`cid`) REFERENCES `tb_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='菜品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_menu`
--

LOCK TABLES `tb_menu` WRITE;
/*!40000 ALTER TABLE `tb_menu` DISABLE KEYS */;
INSERT INTO `tb_menu` VALUES (1,'宫保鸡丁','http://localhost:8081/img/cde95318-b03f-42a5-9067-29ffca81599b.png',221,0,3,'2019-12-03 14:26:51');
/*!40000 ALTER TABLE `tb_menu` ENABLE KEYS */;
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
  `did` bigint(20) NOT NULL COMMENT '台号表主键',
  `price` float NOT NULL COMMENT '价格',
  `state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0：未结账，1：已结账）',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `tb_order_tb_desk_fk` (`did`),
  KEY `no` (`no`),
  CONSTRAINT `tb_order_tb_desk_fk` FOREIGN KEY (`did`) REFERENCES `tb_desk` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='订单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_order`
--

LOCK TABLES `tb_order` WRITE;
/*!40000 ALTER TABLE `tb_order` DISABLE KEYS */;
INSERT INTO `tb_order` VALUES (1,'a8134d4ff6b84733a630907809e2a51e',2,221,1,'2019-12-04 12:56:17'),(2,'5de083e8c79f4bbeba1825937ab95051',2,0,0,'2019-12-04 13:24:40');
/*!40000 ALTER TABLE `tb_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_orderitem`
--

DROP TABLE IF EXISTS `tb_orderitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_orderitem` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `no` varchar(100) NOT NULL COMMENT '订单编号',
  `mid` bigint(20) NOT NULL COMMENT '菜单表主键',
  `amount` int(2) NOT NULL COMMENT '数量',
  `price` float NOT NULL COMMENT '总价',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `fk_mid` (`mid`),
  KEY `fk_no` (`no`),
  CONSTRAINT `fk_mid` FOREIGN KEY (`mid`) REFERENCES `tb_menu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='订单详情表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_orderitem`
--

LOCK TABLES `tb_orderitem` WRITE;
/*!40000 ALTER TABLE `tb_orderitem` DISABLE KEYS */;
INSERT INTO `tb_orderitem` VALUES (1,'a8134d4ff6b84733a630907809e2a51e',1,1,221,'2019-12-04 12:56:17');
/*!40000 ALTER TABLE `tb_orderitem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ordertemp`
--

DROP TABLE IF EXISTS `tb_ordertemp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ordertemp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `did` bigint(20) NOT NULL COMMENT '台号表主键',
  `mid` bigint(20) NOT NULL COMMENT '菜单表主键',
  `amount` int(2) NOT NULL COMMENT '数量',
  `state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0：未签单，1：已签单）',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `tb_temp_order_tb_desk_fk` (`did`),
  KEY `tb_temp_order_tb_menu_fk` (`mid`),
  CONSTRAINT `tb_temp_order_tb_desk_fk` FOREIGN KEY (`did`) REFERENCES `tb_desk` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `tb_temp_order_tb_menu_fk` FOREIGN KEY (`mid`) REFERENCES `tb_menu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='点菜表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ordertemp`
--

LOCK TABLES `tb_ordertemp` WRITE;
/*!40000 ALTER TABLE `tb_ordertemp` DISABLE KEYS */;
INSERT INTO `tb_ordertemp` VALUES (9,1,1,1,1,'2019-12-04 11:35:02');
/*!40000 ALTER TABLE `tb_ordertemp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_shift`
--

DROP TABLE IF EXISTS `tb_shift`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_shift` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `uid` bigint(100) NOT NULL COMMENT '用户表主键',
  `start` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '接班时间',
  `end` timestamp NULL DEFAULT NULL COMMENT '交班时间',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `tb_shift_tb_user_fk` (`uid`),
  CONSTRAINT `tb_shift_tb_user_fk` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='交接班表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_shift`
--

LOCK TABLES `tb_shift` WRITE;
/*!40000 ALTER TABLE `tb_shift` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_shift` ENABLE KEYS */;
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
  `password` varchar(100) NOT NULL COMMENT '密码（MD5加密）',
  `nickname` varchar(100) NOT NULL COMMENT '昵称(默认为用户名)',
  `sex` tinyint(1) NOT NULL DEFAULT '1' COMMENT '性别（男: 1,女: 0）',
  `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '类别（管理员：0，超级管理员：1）',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (1,'admin','e10adc3949ba59abbe56e057f20f883e','admin',1,0,'2019-12-03 14:00:25'),(2,'super_admin','e10adc3949ba59abbe56e057f20f883e','super_admin',1,1,'2019-12-03 14:01:00');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'wxt_bmt'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-04 22:44:26
