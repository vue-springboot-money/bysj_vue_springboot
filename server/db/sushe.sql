/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : sushe

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 11/08/2019 10:13:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_class
-- ----------------------------
DROP TABLE IF EXISTS `tb_class`;
CREATE TABLE `tb_class` (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `name` varchar(20) NOT NULL COMMENT '班级名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='班级表';

-- ----------------------------
-- Table structure for tb_college
-- ----------------------------
DROP TABLE IF EXISTS `tb_college`;
CREATE TABLE `tb_college` (
  `id` bigint(15) NOT NULL COMMENT '自增id',
  `name` varchar(20) NOT NULL COMMENT '学院名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学院表';

-- ----------------------------
-- Table structure for tb_college_major
-- ----------------------------
DROP TABLE IF EXISTS `tb_college_major`;
CREATE TABLE `tb_college_major` (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `college_id` bigint(15) NOT NULL COMMENT '学院表id',
  `major_id` bigint(15) NOT NULL COMMENT '专业表id',
  PRIMARY KEY (`id`),
  KEY `fk_cm_college_id` (`college_id`),
  KEY `fk_cm_major_id` (`major_id`),
  CONSTRAINT `fk_cm_college_id` FOREIGN KEY (`college_id`) REFERENCES `tb_college` (`id`),
  CONSTRAINT `fk_cm_major_id` FOREIGN KEY (`major_id`) REFERENCES `tb_major` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='学院专业关系表';

-- ----------------------------
-- Table structure for tb_major
-- ----------------------------
DROP TABLE IF EXISTS `tb_major`;
CREATE TABLE `tb_major` (
  `id` bigint(15) NOT NULL COMMENT '自增id',
  `name` varchar(20) NOT NULL COMMENT '专业名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专业表';

-- ----------------------------
-- Table structure for tb_major_class
-- ----------------------------
DROP TABLE IF EXISTS `tb_major_class`;
CREATE TABLE `tb_major_class` (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `major_id` bigint(15) NOT NULL COMMENT '专业表id',
  `class_id` bigint(15) NOT NULL COMMENT '班级表id',
  PRIMARY KEY (`id`),
  KEY `fk_mc_major_id` (`class_id`),
  KEY `fk_mc_class_id` (`major_id`),
  CONSTRAINT `fk_mc_class_id` FOREIGN KEY (`major_id`) REFERENCES `tb_major` (`id`),
  CONSTRAINT `fk_mc_major_id` FOREIGN KEY (`class_id`) REFERENCES `tb_class` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='专业班级关系表';

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(15) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `username` varchar(10) NOT NULL COMMENT '用户名（学号）',
  `password` varchar(32) NOT NULL COMMENT '密码（MD5加密）',
  `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '用户类型（0: 管理员, 1: 学生, 2: 教师）',
  `name` varchar(0) DEFAULT NULL COMMENT '姓名',
  `sex` char(1) DEFAULT NULL COMMENT '性别',
  `tel` varchar(13) DEFAULT NULL COMMENT '电话号',
  `mail` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `college` bigint(15) DEFAULT NULL COMMENT '学院',
  `major` bigint(15) DEFAULT NULL COMMENT '专业',
  `class` bigint(15) DEFAULT NULL COMMENT '班级',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES (1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_user` VALUES (2, 'student', 'cd73502828457d15655bbd7a63fb0bc8', 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tb_user` VALUES (3, 'teacher', '8d788385431273d11e8b43bb78f3aa41', 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
