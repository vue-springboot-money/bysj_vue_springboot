/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50713
Source Host           : localhost:3306
Source Database       : wpc_dys

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2019-12-14 01:01:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_actor
-- ----------------------------
DROP TABLE IF EXISTS `tb_actor`;
CREATE TABLE `tb_actor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(100) DEFAULT NULL COMMENT '演员名',
  `img` varchar(100) DEFAULT NULL COMMENT '图片',
  `information` varchar(100) DEFAULT NULL COMMENT '描述信息',
  `masterpiece` varchar(100) DEFAULT NULL COMMENT '代表作',
  `tid` bigint(20) DEFAULT NULL COMMENT '队伍外键',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `tb_actor_tb_team_fk` (`tid`),
  CONSTRAINT `tb_actor_tb_team_fk` FOREIGN KEY (`tid`) REFERENCES `tb_team` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='演员表';

-- ----------------------------
-- Records of tb_actor
-- ----------------------------
INSERT INTO `tb_actor` VALUES ('14', '岳云鹏', 'https://img.zcool.cn/community/01a92a5a151826a80120518742bb1d.JPG', '有一批', 'q\'we\'q\'w', '14', '2019-12-13 20:49:10');

-- ----------------------------
-- Table structure for tb_news
-- ----------------------------
DROP TABLE IF EXISTS `tb_news`;
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

-- ----------------------------
-- Records of tb_news
-- ----------------------------

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `uid` bigint(20) DEFAULT NULL COMMENT '用户表外键',
  `tid` bigint(20) DEFAULT NULL COMMENT '门票表外键',
  `state` tinyint(4) DEFAULT '0' COMMENT '状态（0：未完成，1：已完成，2：已退款）',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `tb_order_tb_user_fk` (`uid`),
  KEY `tb_order_tb_ticket_fk` (`tid`),
  CONSTRAINT `tb_order_tb_ticket_fk` FOREIGN KEY (`tid`) REFERENCES `tb_ticket` (`id`),
  CONSTRAINT `tb_order_tb_user_fk` FOREIGN KEY (`uid`) REFERENCES `tb_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='门票表';

-- ----------------------------
-- Records of tb_order
-- ----------------------------

-- ----------------------------
-- Table structure for tb_program
-- ----------------------------
DROP TABLE IF EXISTS `tb_program`;
CREATE TABLE `tb_program` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `tid` bigint(20) DEFAULT NULL COMMENT '剧场外键',
  `content` varchar(100) DEFAULT NULL COMMENT '节目单内容',
  `date` date DEFAULT NULL COMMENT '演出日期',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `tb_program_tb_theater_fk` (`tid`),
  CONSTRAINT `tb_program_tb_theater_fk` FOREIGN KEY (`tid`) REFERENCES `tb_theater` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='节目单表';

-- ----------------------------
-- Records of tb_program
-- ----------------------------
INSERT INTO `tb_program` VALUES ('14', '14', '士大夫士大夫大师傅似的发士大夫撒旦发射点发射点发射点发', '2019-12-14', '2019-12-13 20:32:04');

-- ----------------------------
-- Table structure for tb_session
-- ----------------------------
DROP TABLE IF EXISTS `tb_session`;
CREATE TABLE `tb_session` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `starring1` bigint(20) DEFAULT NULL COMMENT '主演1',
  `starring2` bigint(20) DEFAULT NULL COMMENT '主演2',
  `assistant1` bigint(20) DEFAULT NULL COMMENT '助演1',
  `assistant2` bigint(20) DEFAULT NULL COMMENT '助演2',
  `assistant3` bigint(20) DEFAULT NULL COMMENT '助演3',
  `assistant4` bigint(20) DEFAULT NULL COMMENT '助演4',
  `tid` bigint(20) DEFAULT NULL COMMENT '剧场外键',
  `date` date DEFAULT NULL COMMENT '演出日期',
  `img` varchar(100) DEFAULT NULL COMMENT '图片路径',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `tb_session_tb_actor_fk_1` (`starring1`),
  KEY `tb_session_tb_theater_fk` (`tid`),
  KEY `tb_session_tb_actor_fk_2` (`assistant1`),
  CONSTRAINT `tb_session_tb_actor_fk_1` FOREIGN KEY (`starring1`) REFERENCES `tb_actor` (`id`),
  CONSTRAINT `tb_session_tb_actor_fk_2` FOREIGN KEY (`assistant1`) REFERENCES `tb_actor` (`id`),
  CONSTRAINT `tb_session_tb_theater_fk` FOREIGN KEY (`tid`) REFERENCES `tb_theater` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='专场表';

-- ----------------------------
-- Records of tb_session
-- ----------------------------
INSERT INTO `tb_session` VALUES ('15', '14', '14', '14', '14', '14', '14', '14', '2019-12-13', 'http://localhost:8081/img/11b0df2c-83d6-4e9a-871b-40dd1c399cf3.png', '2019-12-13 21:05:51');

-- ----------------------------
-- Table structure for tb_team
-- ----------------------------
DROP TABLE IF EXISTS `tb_team`;
CREATE TABLE `tb_team` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(100) DEFAULT NULL COMMENT '队伍名',
  `img` varchar(255) DEFAULT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='队伍表';

-- ----------------------------
-- Records of tb_team
-- ----------------------------
INSERT INTO `tb_team` VALUES ('14', '1队', 'http://localhost:8081/img/6519e84d-fd12-4bf7-ba0a-156d4788e27d.png', '123', '2019-12-13 20:28:17');

-- ----------------------------
-- Table structure for tb_theater
-- ----------------------------
DROP TABLE IF EXISTS `tb_theater`;
CREATE TABLE `tb_theater` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(100) DEFAULT NULL COMMENT '剧场名',
  `video` varchar(100) DEFAULT NULL COMMENT 'VCR路径',
  `information` varchar(100) DEFAULT NULL COMMENT '描述信息',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `tel` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='剧场表';

-- ----------------------------
-- Records of tb_theater
-- ----------------------------
INSERT INTO `tb_theater` VALUES ('14', '三里屯', 'http://localhost:8081/img/25cd5d8a-112b-4ff9-b46e-f084324ab96d.png', '夫斯基的开发建设的考虑', '上岛咖啡加快了速度加快立法', '12321312', '2019-12-13 20:31:52');

-- ----------------------------
-- Table structure for tb_ticket
-- ----------------------------
DROP TABLE IF EXISTS `tb_ticket`;
CREATE TABLE `tb_ticket` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `pid` bigint(20) DEFAULT NULL COMMENT '节目单表外键',
  `sid` bigint(20) DEFAULT NULL COMMENT '专场表外键',
  `price` float DEFAULT NULL COMMENT '价格',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `version` int(11) DEFAULT NULL COMMENT '乐观锁',
  `state` tinyint(1) DEFAULT '0' COMMENT '状态（0：未上架，1：已上架）',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `tb_ticket_tb_program_fk` (`pid`),
  KEY `tb_ticket_tb_session_fk` (`sid`),
  CONSTRAINT `tb_ticket_tb_program_fk` FOREIGN KEY (`pid`) REFERENCES `tb_program` (`id`),
  CONSTRAINT `tb_ticket_tb_session_fk` FOREIGN KEY (`sid`) REFERENCES `tb_session` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='门票表';

-- ----------------------------
-- Records of tb_ticket
-- ----------------------------
INSERT INTO `tb_ticket` VALUES ('14', '14', null, '200', '2', '11', '1', '2019-12-13 22:36:22');
INSERT INTO `tb_ticket` VALUES ('15', '14', null, '400', '2', '5', '1', '2019-12-13 22:36:22');
INSERT INTO `tb_ticket` VALUES ('16', '14', null, '600', '2', '5', '1', '2019-12-13 22:36:22');
INSERT INTO `tb_ticket` VALUES ('17', '14', null, '800', '2', '3', '1', '2019-12-13 22:36:22');
INSERT INTO `tb_ticket` VALUES ('18', null, '15', '200', '23', '2', '1', '2019-12-13 22:41:41');
INSERT INTO `tb_ticket` VALUES ('19', null, '15', '400', '22', '1', '1', '2019-12-13 22:41:41');
INSERT INTO `tb_ticket` VALUES ('20', null, '15', '600', '22', '1', '1', '2019-12-13 22:41:41');
INSERT INTO `tb_ticket` VALUES ('21', null, '15', '800', '22', '1', '1', '2019-12-13 22:41:41');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `username` varchar(100) NOT NULL COMMENT '用户名（学号/工号）',
  `password` varchar(100) NOT NULL COMMENT '密码（MD5加密）',
  `nickname` varchar(100) NOT NULL COMMENT '昵称(默认为用户名)',
  `sex` tinyint(4) NOT NULL DEFAULT '1' COMMENT '性别（男: 1,女: 0）',
  `tel` varchar(20) DEFAULT NULL COMMENT '电话',
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '类别（用户：0，演员：1，管理员：2）',
  `balance` float NOT NULL DEFAULT '0' COMMENT '账户余额',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('14', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', '1', '1234567890', '2', '0', '2019-12-14 00:07:54');
INSERT INTO `tb_user` VALUES ('17', 'test', 'e10adc3949ba59abbe56e057f20f883e', 'test', '0', null, '0', '0', '2019-12-14 00:36:55');
