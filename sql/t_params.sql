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

 Date: 24/12/2019 19:31:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_params
-- ----------------------------
DROP TABLE IF EXISTS `t_params`;
CREATE TABLE `t_params`  (
  `P` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `P_pub` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `s` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `k` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_params
-- ----------------------------
INSERT INTO `t_params` VALUES ('+¥7Ì&xï­m	ºÎ¢Unì2û}A!=®à{ä¥ÎTGu¬\"hS)ÍR2Ý;Ù¬×(	,\r&gñoÉXj«Ë?&é°Væ8ë®ì«\ZÉ2¯Ç8ò>g\\;ýß&CoJ\\úS~æÄwñª', '01AÂÉÞÃ¡ÂKó¼^ç]AEÐÑ¯Åó1Ãá@>È¢åò¡2Ù;5ÜÖr§@:ä-p¶ØÑáôÚoð§à?ã¶!âù¼\r¢/\nÙ1ðJy%g®Ú+ýÇqL± *1ùÃ÷ØÂbpjºaei¡¹¡QAyÌ', 'GlDÁÞ\\±Þ_=	ñ`¶%3¨÷§HÍê«K;îjgÃTÚ1	WÈÏ%-[2\"iÏædIj+', 'GlDÁÞ\\±Þ_=	ñ`¶%3¨÷§HÍê«K;îjgÃTÚ1	WÈÏ%-[2\"iÏædIj+');

SET FOREIGN_KEY_CHECKS = 1;
