/*
Navicat MySQL Data Transfer

Source Server         : cb
Source Server Version : 50546
Source Host           : 139.129.11.18:3306
Source Database       : suitang

Target Server Type    : MYSQL
Target Server Version : 50546
File Encoding         : 65001

Date: 2017-04-06 22:01:47
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `cid` varchar(15) NOT NULL COMMENT '课程id',
  `c_name` varchar(20) NOT NULL COMMENT '课程名字',
  `c_address` varchar(20) NOT NULL COMMENT '上课地点',
  `c_time` varchar(30) NOT NULL COMMENT '上课时间',
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------

-- ----------------------------
-- Table structure for `schedule`
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `uid` int(15) NOT NULL,
  `cid` varchar(15) NOT NULL,
  `course_term` varchar(20) NOT NULL COMMENT '选课的学期',
  PRIMARY KEY (`uid`,`cid`),
  KEY `t8` (`cid`),
  CONSTRAINT `t8` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t7` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schedule
-- ----------------------------

-- ----------------------------
-- Table structure for `sign`
-- ----------------------------
DROP TABLE IF EXISTS `sign`;
CREATE TABLE `sign` (
  `sign_id` int(15) NOT NULL AUTO_INCREMENT,
  `uid` int(15) NOT NULL,
  `cid` varchar(15) NOT NULL,
  `sign_time` bigint(20) NOT NULL,
  `token` varchar(25) NOT NULL,
  `invalid_time` bigint(20) NOT NULL,
  PRIMARY KEY (`sign_id`),
  KEY `t2` (`uid`),
  KEY `t3` (`cid`),
  CONSTRAINT `t3` FOREIGN KEY (`cid`) REFERENCES `course` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t2` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign
-- ----------------------------

-- ----------------------------
-- Table structure for `sign_history`
-- ----------------------------
DROP TABLE IF EXISTS `sign_history`;
CREATE TABLE `sign_history` (
  `uid` int(15) NOT NULL,
  `sign_id` int(15) NOT NULL,
  `sign_time` bigint(20) NOT NULL,
  `sign_late` tinyint(4) DEFAULT '0' COMMENT '是否补签 0-否 1-是',
  `late_reason` varchar(120) DEFAULT NULL,
  `late_state` int(11) DEFAULT NULL COMMENT '补签状态 审核中、已同意、已拒绝',
  PRIMARY KEY (`uid`,`sign_id`),
  KEY `t5` (`sign_id`),
  CONSTRAINT `t5` FOREIGN KEY (`sign_id`) REFERENCES `sign` (`sign_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t4` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sign_history
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `uid` int(15) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `nickname` varchar(20) NOT NULL COMMENT '用户昵称',
  `avatar` varchar(1024) NOT NULL COMMENT '用户头像',
  `sex` int(1) DEFAULT '0' COMMENT '性别',
  `rank` int(1) DEFAULT '1' COMMENT '职位0-学生 1-老师',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for `user_local_auths`
-- ----------------------------
DROP TABLE IF EXISTS `user_local_auths`;
CREATE TABLE `user_local_auths` (
  `uid` int(11) NOT NULL,
  `password` varchar(20) NOT NULL,
  `phone_no` varchar(20) NOT NULL,
  `email` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_local_auths
-- ----------------------------
INSERT INTO `user_local_auths` VALUES ('1', '123456', '123456789', '584686739@qq.com');
INSERT INTO `user_local_auths` VALUES ('2', '123456', '123456789', '584686739@qq.com');
INSERT INTO `user_local_auths` VALUES ('3', '123456', '123456789', '584686739@qq.com');
INSERT INTO `user_local_auths` VALUES ('4', 'xiaojiahao1997', '18222626942', '584686739@qq.com');
INSERT INTO `user_local_auths` VALUES ('5', 'xiaojiahao1997', '18222626942', '584686739@qq.com');
INSERT INTO `user_local_auths` VALUES ('6', 'xiaojiahao1997', '18222626942', '584686739@qq.com');
INSERT INTO `user_local_auths` VALUES ('7', 'mimamima', '987654321', '123456789@qq.com');
INSERT INTO `user_local_auths` VALUES ('8', 'mimamima', '987654321', '123456789@qq.com');
INSERT INTO `user_local_auths` VALUES ('9', 'xiaojiahao1997', '18222626942', '584686739@qq.com');
INSERT INTO `user_local_auths` VALUES ('10', 'mimamima', '987654321', '123456789@qq.com');
INSERT INTO `user_local_auths` VALUES ('11', 'mimamima', '987654321', '123456789@qq.com');
INSERT INTO `user_local_auths` VALUES ('12', 'mimamima', '987654321', '123456789@qq.com');
INSERT INTO `user_local_auths` VALUES ('13', '456', '123', '789');
INSERT INTO `user_local_auths` VALUES ('14', '123456', '123456789', '584686739@qq.com');
INSERT INTO `user_local_auths` VALUES ('15', '123456', '123456789', '584686739@qq.com');

-- ----------------------------
-- Table structure for `user_login_record`
-- ----------------------------
DROP TABLE IF EXISTS `user_login_record`;
CREATE TABLE `user_login_record` (
  `uid` int(15) NOT NULL,
  `first_login_time` bigint(20) NOT NULL COMMENT '首次登录时间',
  `last_login_time` bigint(20) NOT NULL COMMENT '上次登录时间',
  `last_login_device` varchar(255) DEFAULT NULL COMMENT '上次登录设备名称',
  `last_login_device_id` varchar(255) NOT NULL COMMENT '登录设备id',
  PRIMARY KEY (`uid`),
  CONSTRAINT `t9` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_login_record
-- ----------------------------

-- ----------------------------
-- Table structure for `user_other_auths`
-- ----------------------------
DROP TABLE IF EXISTS `user_other_auths`;
CREATE TABLE `user_other_auths` (
  `uid` int(15) NOT NULL,
  `identity_type` varchar(10) NOT NULL,
  `identifier` varchar(25) NOT NULL,
  `token` varchar(25) NOT NULL,
  `invalid_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  CONSTRAINT `t6` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_other_auths
-- ----------------------------
