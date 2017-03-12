/*
Navicat MySQL Data Transfer

Source Server         : CKGL
Source Server Version : 50613
Source Host           : localhost:3306
Source Database       : cangkuguanli

Target Server Type    : MYSQL
Target Server Version : 50613
File Encoding         : 65001

Date: 2016-09-01 17:42:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `mima`
-- ----------------------------
DROP TABLE IF EXISTS `mima`;
CREATE TABLE `mima` (
  `user` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mima
-- ----------------------------
INSERT INTO `mima` VALUES ('', '');
INSERT INTO `mima` VALUES ('123', '123');
INSERT INTO `mima` VALUES ('admin', '123');

-- ----------------------------
-- Table structure for `productinfo`
-- ----------------------------
DROP TABLE IF EXISTS `productinfo`;
CREATE TABLE `productinfo` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` int(255) DEFAULT NULL,
  `inver` varchar(255) DEFAULT NULL,
  `class` varchar(255) DEFAULT NULL,
  `supplier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of productinfo
-- ----------------------------
INSERT INTO `productinfo` VALUES ('001', '哇哈哈', '3', '389', '饮料', '河南大学');
INSERT INTO `productinfo` VALUES ('002', '营养快线', '4', '414', '饮料', '河南科技大学');
INSERT INTO `productinfo` VALUES ('003', '饼干', '5', '389', '零食', '河南大学');
INSERT INTO `productinfo` VALUES ('004', '袜子', '3', '389', '生活用品', '河南大学');
INSERT INTO `productinfo` VALUES ('005', '加多宝', '4', '389', '饮料', '河南大学');

-- ----------------------------
-- Table structure for `supplier`
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `supplier` varchar(255) NOT NULL,
  PRIMARY KEY (`supplier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of supplier
-- ----------------------------
INSERT INTO `supplier` VALUES ('');
INSERT INTO `supplier` VALUES ('河南大学');
INSERT INTO `supplier` VALUES ('河南科技大学');
INSERT INTO `supplier` VALUES ('清华大学');
INSERT INTO `supplier` VALUES ('郑州大学');
INSERT INTO `supplier` VALUES ('郑州轻工业学院');

-- ----------------------------
-- Table structure for `supplierinfo`
-- ----------------------------
DROP TABLE IF EXISTS `supplierinfo`;
CREATE TABLE `supplierinfo` (
  `name` varchar(255) NOT NULL,
  `tradename` varchar(255) DEFAULT NULL,
  `tradeprice` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of supplierinfo
-- ----------------------------
INSERT INTO `supplierinfo` VALUES ('河南大学', '加多宝', '4', '12355355', '焦作');
INSERT INTO `supplierinfo` VALUES ('河南科技大学', '饼干', '5', '4125412034', '洛阳');
INSERT INTO `supplierinfo` VALUES ('清华大学', '袜子', '3', '1532654564', '北京');
INSERT INTO `supplierinfo` VALUES ('郑州大学', '营养快线', '4', '15234515456', '中原区');
INSERT INTO `supplierinfo` VALUES ('郑州轻工业学院', '哇哈哈', '3', '156354154', '科学大道');
