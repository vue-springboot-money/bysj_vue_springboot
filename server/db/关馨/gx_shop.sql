-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)
--
-- Host: localhost    Database: gx_shop
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
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `name` varchar(100) NOT NULL COMMENT '类别名称',
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态（0：未启用，1：已启用）',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品类别表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_category`
--

LOCK TABLES `tb_category` WRITE;
/*!40000 ALTER TABLE `tb_category` DISABLE KEYS */;
INSERT INTO `tb_category` VALUES (1,'口红',1,'2019-12-02 11:34:35'),(2,'粉底',0,'2019-12-02 11:36:28');
/*!40000 ALTER TABLE `tb_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_good`
--

DROP TABLE IF EXISTS `tb_good`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_good` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(100) NOT NULL COMMENT '商品名',
  `img` varchar(100) NOT NULL COMMENT '商品图片路径',
  `price` float NOT NULL DEFAULT '0' COMMENT '商品价格',
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态（0：未上架，1：已上架）',
  `cid` bigint(20) DEFAULT NULL COMMENT '分类',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='商品表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_good`
--

LOCK TABLES `tb_good` WRITE;
/*!40000 ALTER TABLE `tb_good` DISABLE KEYS */;
INSERT INTO `tb_good` VALUES (2,'口红','http://localhost:8081/img/febe7373-d753-424d-ae08-222d721e99a7.jpg',2222,1,2,'2019-12-02 11:43:32'),(3,'123','http://localhost:8081/img/0cb1f036-ea60-418f-8840-a942d8be4fa9.jpg',99,0,2,'2019-12-02 12:07:06'),(4,'321','http://img.zcool.cn/community/01a92a5a151826a80120518742bb1d.JPG',111,0,1,'2019-12-02 12:08:58');
/*!40000 ALTER TABLE `tb_good` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_logistics`
--

DROP TABLE IF EXISTS `tb_logistics`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_logistics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `oid` bigint(20) NOT NULL COMMENT '订单id',
  `uid` bigint(20) NOT NULL COMMENT '用户id',
  `state` tinyint(4) NOT NULL COMMENT '状态（0：未取件，1：送货中，2：完成）',
  `content` varchar(100) DEFAULT NULL COMMENT '备注',
  `createtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `tb_logistics_tb_order_fk` (`oid`),
  KEY `tb_logistics_tb_user_fk` (`uid`),
  CONSTRAINT `tb_logistics_tb_order_fk` FOREIGN KEY (`oid`) REFERENCES `tb_order` (`id`),
  CONSTRAINT `tb_logistics_tb_user_fk` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='物流信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_logistics`
--

LOCK TABLES `tb_logistics` WRITE;
/*!40000 ALTER TABLE `tb_logistics` DISABLE KEYS */;
INSERT INTO `tb_logistics` VALUES (1,2,3,0,NULL,'2019-12-02 13:04:59');
/*!40000 ALTER TABLE `tb_logistics` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='新闻表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_news`
--

LOCK TABLES `tb_news` WRITE;
/*!40000 ALTER TABLE `tb_news` DISABLE KEYS */;
INSERT INTO `tb_news` VALUES (1,'123','http://localhost:8081/img/a7843d67-6488-4c80-99fc-ff1e2f80d6fc.jpg','2321312','123',0,'2019-12-02 11:17:32');
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
INSERT INTO `tb_order` VALUES (2,'11111111111111111111111111111111',2,2222,NULL,0,'2019-12-02 13:04:02');
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
  `gid` bigint(20) NOT NULL COMMENT '商品id',
  `amount` int(11) NOT NULL COMMENT '数量',
  `price` float NOT NULL COMMENT '总价',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `fk_mid` (`gid`),
  KEY `fk_no` (`no`),
  CONSTRAINT `fk_gid` FOREIGN KEY (`gid`) REFERENCES `tb_good` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='订单详情表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_orderitem`
--

LOCK TABLES `tb_orderitem` WRITE;
/*!40000 ALTER TABLE `tb_orderitem` DISABLE KEYS */;
INSERT INTO `tb_orderitem` VALUES (1,'11111111111111111111111111111111',2,1,2222,'2019-12-02 13:04:26');
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
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '类别（用户：0，快递员：1，管理员：2）',
  `address` varchar(225) DEFAULT NULL COMMENT '收货地址',
  `balance` float NOT NULL DEFAULT '0' COMMENT '账户余额',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES (2,'ADMIN','e10adc3949ba59abbe56e057f20f883e','ADMIN',0,0,NULL,200,'2019-12-02 10:54:30'),(3,'BBBBB','e10adc3949ba59abbe56e057f20f883e','BBBBB',1,1,NULL,0,'2019-12-02 10:56:26');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'gx_shop'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-12-02 22:52:56
