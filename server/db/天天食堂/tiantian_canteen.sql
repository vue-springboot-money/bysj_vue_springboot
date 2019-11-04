/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : tiantian_canteen

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2019-11-03 23:30:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(100) NOT NULL COMMENT '菜单名',
  `img` varchar(100) NOT NULL COMMENT '菜品图片路径',
  `price` float NOT NULL DEFAULT '0' COMMENT '菜品价格',
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态（0：未上架，1：已上架）',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('1', '锅包肉', 'http://img.zcool.cn/community/01a92a5a151826a80120518742bb1d.JPG', '32', '1', '2019-10-31 21:14:49');
INSERT INTO `tb_menu` VALUES ('2', '宫保鸡丁', 'http://img.zcool.cn/community/01a92a5a151826a80120518742bb1d.JPG', '24', '1', '2019-10-31 21:22:43');

-- ----------------------------
-- Table structure for tb_news
-- ----------------------------
DROP TABLE IF EXISTS `tb_news`;
CREATE TABLE `tb_news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `title` varchar(100) NOT NULL COMMENT '新闻标题',
  `img` varchar(100) DEFAULT NULL COMMENT '新闻配图路径',
  `content` varchar(255) NOT NULL COMMENT '内容',
  `author` varchar(100) NOT NULL COMMENT '作者署名',
  `state` tinyint(4) NOT NULL DEFAULT '0' COMMENT '状态（0：未发布，1：已发布）',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_news
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
INSERT INTO `tb_order` VALUES ('2', '2a0be9d1cc4b475a8aeab272df5986ac', '3', '59', '', '0', '349352', '2019-10-31 21:23:05');

-- ----------------------------
-- Table structure for tb_orderitem
-- ----------------------------
DROP TABLE IF EXISTS `tb_orderitem`;
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
  CONSTRAINT `fk_mid` FOREIGN KEY (`mid`) REFERENCES `tb_menu` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_no` FOREIGN KEY (`no`) REFERENCES `tb_order` (`no`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_orderitem
-- ----------------------------
INSERT INTO `tb_orderitem` VALUES ('4', '2a0be9d1cc4b475a8aeab272df5986ac', '1', '2', '64', '2019-10-31 21:23:05');
INSERT INTO `tb_orderitem` VALUES ('5', '2a0be9d1cc4b475a8aeab272df5986ac', '2', '1', '24', '2019-10-31 21:23:05');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', '管理员', '1', '2', '0', '2019-10-29 17:56:47');
INSERT INTO `tb_user` VALUES ('2', 'TT000', 'e10adc3949ba59abbe56e057f20f883e', 'TT000_教师', '0', '1', '0', '2019-10-29 21:04:31');
INSERT INTO `tb_user` VALUES ('3', 'TT001', 'e10adc3949ba59abbe56e057f20f883e', 'TT001_学生', '0', '1', '0', '2019-10-29 21:04:48');
