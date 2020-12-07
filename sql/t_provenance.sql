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

 Date: 24/12/2019 19:31:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_provenance
-- ----------------------------
DROP TABLE IF EXISTS `t_provenance`;
CREATE TABLE `t_provenance`  (
  `pid` int(10) NOT NULL AUTO_INCREMENT,
  `fid` int(10) NOT NULL,
  `fileStage` int(10) NOT NULL,
  `blockHash` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `txHash` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Provenance` varchar(9999) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `stateLength` int(10) DEFAULT NULL,
  `PIDLength` int(10) DEFAULT NULL,
  `blockHashLength` int(10) DEFAULT NULL,
  `signatureLength` int(100) DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
