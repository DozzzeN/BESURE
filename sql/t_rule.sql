/*
 Navicat MySQL Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : esp

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 24/12/2019 19:31:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_rule
-- ----------------------------
DROP TABLE IF EXISTS `t_rule`;
CREATE TABLE `t_rule`  (
  `uid` int(10) NOT NULL,
  `fid` int(10) NOT NULL,
  `operate` enum('own','read','write') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '拥有：可以转让、读、写；读：可以下载；写：可以下载，可以编辑后上传',
  PRIMARY KEY (`uid`, `fid`, `operate`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
