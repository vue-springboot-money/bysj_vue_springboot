-- MySQL dump 10.13  Distrib 5.7.19, for Win64 (x86_64)
--
-- Host: localhost    Database: wpc_dys
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
-- Table structure for table `tb_actor`
--

DROP TABLE IF EXISTS `tb_actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_actor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(100) DEFAULT NULL COMMENT '演员名',
  `img` varchar(100) DEFAULT NULL COMMENT '图片',
  `information` varchar(100) DEFAULT NULL COMMENT '描述信息',
  `address` varchar(100) DEFAULT NULL COMMENT '代表作',
  `tid` bigint(20) DEFAULT NULL COMMENT '队伍外键',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `tb_actor_tb_team_fk` (`tid`),
  CONSTRAINT `tb_actor_tb_team_fk` FOREIGN KEY (`tid`) REFERENCES `tb_team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='演员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_actor`
--

LOCK TABLES `tb_actor` WRITE;
/*!40000 ALTER TABLE `tb_actor` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_actor` ENABLE KEYS */;
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
  `uid` bigint(20) DEFAULT NULL COMMENT '用户表外键',
  `tid` bigint(20) DEFAULT NULL COMMENT '门票表外键',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `tb_order_tb_user_fk` (`uid`),
  KEY `tb_order_tb_ticket_fk` (`tid`),
  CONSTRAINT `tb_order_tb_ticket_fk` FOREIGN KEY (`tid`) REFERENCES `tb_ticket` (`id`),
  CONSTRAINT `tb_order_tb_user_fk` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='门票表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_order`
--

LOCK TABLES `tb_order` WRITE;
/*!40000 ALTER TABLE `tb_order` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_program`
--

DROP TABLE IF EXISTS `tb_program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_program` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `tid` bigint(20) DEFAULT NULL COMMENT '剧场外键',
  `content` varchar(100) DEFAULT NULL COMMENT '节目单内容',
  `date` date DEFAULT NULL COMMENT '演出日期',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `tb_program_tb_theater_fk` (`tid`),
  CONSTRAINT `tb_program_tb_theater_fk` FOREIGN KEY (`tid`) REFERENCES `tb_theater` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='节目单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_program`
--

LOCK TABLES `tb_program` WRITE;
/*!40000 ALTER TABLE `tb_program` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_program` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_session`
--

DROP TABLE IF EXISTS `tb_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_session` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `starring` bigint(20) DEFAULT NULL COMMENT '主演',
  `assistant` bigint(20) DEFAULT NULL COMMENT '助演',
  `tid` bigint(20) DEFAULT NULL COMMENT '剧场外键',
  `date` date DEFAULT NULL COMMENT '演出日期',
  `img` varchar(100) DEFAULT NULL COMMENT '图片路径',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `tb_session_tb_actor_fk_1` (`starring`),
  KEY `tb_session_tb_theater_fk` (`tid`),
  KEY `tb_session_tb_actor_fk_2` (`assistant`),
  CONSTRAINT `tb_session_tb_actor_fk_1` FOREIGN KEY (`starring`) REFERENCES `tb_actor` (`id`),
  CONSTRAINT `tb_session_tb_actor_fk_2` FOREIGN KEY (`assistant`) REFERENCES `tb_actor` (`id`),
  CONSTRAINT `tb_session_tb_theater_fk` FOREIGN KEY (`tid`) REFERENCES `tb_theater` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='专场表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_session`
--

LOCK TABLES `tb_session` WRITE;
/*!40000 ALTER TABLE `tb_session` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_team`
--

DROP TABLE IF EXISTS `tb_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_team` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(100) DEFAULT NULL COMMENT '队伍名',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='队伍表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_team`
--

LOCK TABLES `tb_team` WRITE;
/*!40000 ALTER TABLE `tb_team` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_theater`
--

DROP TABLE IF EXISTS `tb_theater`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_theater` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(100) DEFAULT NULL COMMENT '剧场名',
  `video` varchar(100) DEFAULT NULL COMMENT 'VCR路径',
  `information` varchar(100) DEFAULT NULL COMMENT '描述信息',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `tel` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='剧场表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_theater`
--

LOCK TABLES `tb_theater` WRITE;
/*!40000 ALTER TABLE `tb_theater` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_theater` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_ticket`
--

DROP TABLE IF EXISTS `tb_ticket`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_ticket` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `pid` bigint(20) DEFAULT NULL COMMENT '节目单表外键',
  `sid` bigint(20) DEFAULT NULL COMMENT '专场表外键',
  `price` float DEFAULT NULL COMMENT '价格',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `version` int(11) DEFAULT NULL COMMENT '乐观锁',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `tb_ticket_tb_program_fk` (`pid`),
  KEY `tb_ticket_tb_session_fk` (`sid`),
  CONSTRAINT `tb_ticket_tb_program_fk` FOREIGN KEY (`pid`) REFERENCES `tb_program` (`id`),
  CONSTRAINT `tb_ticket_tb_session_fk` FOREIGN KEY (`sid`) REFERENCES `tb_session` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='门票表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_ticket`
--

LOCK TABLES `tb_ticket` WRITE;
/*!40000 ALTER TABLE `tb_ticket` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_ticket` ENABLE KEYS */;
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
  `tel` varchar(20) DEFAULT NULL COMMENT '电话',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '类别（用户：0，演员：1，管理员：2）',
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
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'wpc_dys'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-27 21:08:29
