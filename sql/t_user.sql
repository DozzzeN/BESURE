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

 Date: 24/12/2019 19:31:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `uid` int(255) NOT NULL AUTO_INCREMENT,
  `uname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` enum('creator','editor','auditor') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `PID` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '可以为空，后面进行分配',
  PRIMARY KEY (`uid`, `role`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'owner', 'owner', 'creator', '�8庉<b擀宏嶌P�8ㄛ+H妤5[bH珲/u�h復�\n:U萂u;ん帐 #5让$L郯_�*:x鞼�E*豳!P');
INSERT INTO `t_user` VALUES (2, 'editor', 'editor', 'creator', '.\ZR矼y�9/孅nf=覧=u<;蜬s呤恫-�娏x%鯴�$芽嘛:t74	F矅�暦Ij鄽gm渤\r|a韃');
INSERT INTO `t_user` VALUES (3, 'auditor', 'auditor', 'auditor', '疒褘曨n8d譠禐o堅n9葖1蠤:廠隐\n堠踕B陓�愍鴛领柒疿�)H�1瘺雯�?�茚!毞:nk柬A');

SET FOREIGN_KEY_CHECKS = 1;
