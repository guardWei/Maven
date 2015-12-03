/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2015-12-03 19:48:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `course_name` varchar(20) DEFAULT NULL COMMENT '课程名',
  `course_teacher` varchar(20) DEFAULT NULL COMMENT '课程老师',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1', '语文', '张老师');
INSERT INTO `course` VALUES ('2', '数学', '朱老师');
INSERT INTO `course` VALUES ('3', '数学', '刘老师');
INSERT INTO `course` VALUES ('4', '历史', '吴老师');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `birthday` date DEFAULT NULL COMMENT '生日',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '张魏魏', '24', '1992-09-04');
INSERT INTO `user` VALUES ('2', '李欢', '23', '1993-03-01');

-- ----------------------------
-- Table structure for `user_course`
-- ----------------------------
DROP TABLE IF EXISTS `user_course`;
CREATE TABLE `user_course` (
  `id` varchar(20) NOT NULL,
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户id',
  `course_id` varchar(20) DEFAULT NULL COMMENT '课程id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_course
-- ----------------------------
INSERT INTO `user_course` VALUES ('1', '1', '1');
INSERT INTO `user_course` VALUES ('2', '1', '2');
INSERT INTO `user_course` VALUES ('3', '2', '2');
INSERT INTO `user_course` VALUES ('4', '1', '3');
INSERT INTO `user_course` VALUES ('5', '1', '4');
INSERT INTO `user_course` VALUES ('6', '2', '4');
