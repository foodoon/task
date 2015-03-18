/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : task

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2014-12-29 13:09:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `freeze` bigint(20) NOT NULL,
  `gmt_modified` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', '1', '0', '0', '2014-12-27 23:38:35');
INSERT INTO `account` VALUES ('2', '2', '100', '0', '2014-12-27 23:38:45');
INSERT INTO `account` VALUES ('3', '3', '1500', '0', '2014-12-28 00:22:36');
INSERT INTO `account` VALUES ('4', '4', '97968', '2032', '2014-12-27 23:39:07');

-- ----------------------------
-- Table structure for `account_detail`
-- ----------------------------
DROP TABLE IF EXISTS `account_detail`;
CREATE TABLE `account_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_id` bigint(20) NOT NULL,
  `direction` int(11) NOT NULL,
  `amount` bigint(20) NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `task_id` bigint(20) DEFAULT NULL,
  `account_from` bigint(20) DEFAULT NULL,
  `gmt_created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account_detail
-- ----------------------------
INSERT INTO `account_detail` VALUES ('1', '3', '1', '500', null, '1', '4', '2014-12-28 00:23:40');
INSERT INTO `account_detail` VALUES ('2', '3', '1', '1000', null, '2', '4', '2014-12-28 00:23:44');
INSERT INTO `account_detail` VALUES ('3', '4', '3', '2032', null, '3', '4', '2014-12-29 12:44:10');

-- ----------------------------
-- Table structure for `bank`
-- ----------------------------
DROP TABLE IF EXISTS `bank`;
CREATE TABLE `bank` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `bank_type` varchar(16) NOT NULL,
  `bank_name` varchar(16) NOT NULL,
  `bank_no` varchar(32) NOT NULL,
  `gmt_created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bank
-- ----------------------------

-- ----------------------------
-- Table structure for `operate_log`
-- ----------------------------
DROP TABLE IF EXISTS `operate_log`;
CREATE TABLE `operate_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `operate` varchar(512) DEFAULT NULL,
  `operate_data` varchar(1024) DEFAULT NULL,
  `gmt_created` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of operate_log
-- ----------------------------

-- ----------------------------
-- Table structure for `taobao_item`
-- ----------------------------
DROP TABLE IF EXISTS `taobao_item`;
CREATE TABLE `taobao_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `taobao_user_id` varchar(64) NOT NULL,
  `cid` bigint(20) DEFAULT NULL,
  `seller_cids` varchar(64) DEFAULT NULL,
  `pic_url` varchar(512) DEFAULT NULL,
  `item_url` varchar(1024) DEFAULT NULL,
  `num` bigint(20) DEFAULT NULL,
  `list_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `delist_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `price` bigint(20) DEFAULT NULL,
  `has_discount` int(11) DEFAULT NULL,
  `has_showcase` int(11) DEFAULT NULL,
  `approve_status` varchar(32) DEFAULT NULL,
  `is_virtual` int(11) DEFAULT NULL,
  `num_iid` bigint(20) DEFAULT NULL,
  `title` varchar(256) DEFAULT NULL,
  `nick` varchar(64) DEFAULT NULL,
  `type` varchar(16) DEFAULT NULL,
  `valid_thru` bigint(20) DEFAULT NULL,
  `sold_quantity` bigint(20) DEFAULT NULL,
  `violation` int(11) DEFAULT NULL COMMENT '是否违规',
  `gmt_created` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taobao_item
-- ----------------------------
INSERT INTO `taobao_item` VALUES ('1', '12123', null, null, 'http://gi1.md.alicdn.com/imgextra/i1/2020696429/TB2QhNOaVXXXXXoXpXXXXXXXXXX_!!2020696429.jpg_430x430q90.jpg', null, '11', '2014-12-27 18:59:21', '2014-12-27 18:59:21', '212', null, '1', null, '1', '111', '测试商品1', null, 'b', null, '111', '0', '2014-12-27 18:59:21');
INSERT INTO `taobao_item` VALUES ('2', '12123', null, null, 'http://gi1.md.alicdn.com/imgextra/i1/2020696429/TB2QhNOaVXXXXXoXpXXXXXXXXXX_!!2020696429.jpg_430x430q90.jpg', null, '11', '2014-12-27 18:59:19', '2014-12-27 18:59:19', '232', null, '0', null, '0', null, '测试商品223', null, null, null, '33', '0', '2014-12-27 18:59:19');
INSERT INTO `taobao_item` VALUES ('4', '12123', null, null, 'http://gi4.md.alicdn.com/imgextra/i4/201688881/T23RH_XdRbXXXXXXXX_!!201688881.jpg_430x430q90.jpg', 'http://detail.tmall.com/item.htm?id=16845097999&spm=a220o.1000855.w4023-6694578511.34.eWEQpw', null, '2014-12-29 10:34:33', '2014-12-29 10:34:33', '340000', null, null, null, null, null, '测试商品343', null, null, null, null, null, '2014-12-29 10:34:33');

-- ----------------------------
-- Table structure for `taobao_order`
-- ----------------------------
DROP TABLE IF EXISTS `taobao_order`;
CREATE TABLE `taobao_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `taobao_user_id` varchar(32) NOT NULL,
  `seller_nick` varchar(32) DEFAULT NULL,
  `pic_path` varchar(512) DEFAULT NULL,
  `payment` varchar(32) DEFAULT NULL,
  `receiver_name` varchar(16) DEFAULT NULL,
  `receiver_state` varchar(16) DEFAULT NULL,
  `receiver_address` varchar(256) DEFAULT NULL,
  `receiver_zip` varchar(16) DEFAULT NULL,
  `receiver_mobile` varchar(16) DEFAULT NULL,
  `receiver_phone` varchar(16) DEFAULT NULL,
  `consign_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `received_payment` varchar(32) DEFAULT NULL,
  `tid` bigint(20) DEFAULT NULL,
  `num` bigint(20) DEFAULT NULL,
  `num_iid` bigint(20) DEFAULT NULL,
  `status` varchar(32) DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL,
  `price` varchar(32) DEFAULT NULL,
  `discount_fee` varchar(32) DEFAULT NULL,
  `point_fee` bigint(20) DEFAULT NULL,
  `total_fee` varchar(32) DEFAULT NULL,
  `created` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `pay_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modified` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `end_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `alipay_id` bigint(20) DEFAULT NULL,
  `alipay_no` varchar(255) DEFAULT NULL,
  `gmt_created` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taobao_order
-- ----------------------------

-- ----------------------------
-- Table structure for `taobao_seller`
-- ----------------------------
DROP TABLE IF EXISTS `taobao_seller`;
CREATE TABLE `taobao_seller` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `nick` varchar(32) DEFAULT NULL,
  `sex` varchar(4) DEFAULT NULL,
  `level` bigint(20) DEFAULT NULL,
  `score` bigint(20) DEFAULT NULL,
  `total_num` bigint(20) DEFAULT NULL,
  `good_num` bigint(20) DEFAULT NULL,
  `type` varchar(4) DEFAULT NULL,
  `promoted_type` varchar(16) DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL,
  `consumer_protection` int(11) DEFAULT NULL,
  `is_golden_seller` int(11) DEFAULT NULL,
  `gmt_created` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taobao_seller
-- ----------------------------

-- ----------------------------
-- Table structure for `taobao_shop`
-- ----------------------------
DROP TABLE IF EXISTS `taobao_shop`;
CREATE TABLE `taobao_shop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `taobao_user_id` varchar(32) NOT NULL,
  `sid` bigint(20) DEFAULT NULL,
  `shop_url` varchar(256) DEFAULT NULL,
  `cid` bigint(20) DEFAULT NULL,
  `nick` varchar(32) DEFAULT NULL,
  `title` varchar(64) DEFAULT NULL,
  `descript` varchar(512) DEFAULT NULL,
  `created` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `item_score` varchar(32) DEFAULT NULL,
  `service_score` varchar(32) DEFAULT NULL,
  `delivery_score` varchar(32) DEFAULT NULL,
  `gmt_created` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taobao_shop
-- ----------------------------

-- ----------------------------
-- Table structure for `taobao_shop_cat`
-- ----------------------------
DROP TABLE IF EXISTS `taobao_shop_cat`;
CREATE TABLE `taobao_shop_cat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `shop_sid` bigint(20) NOT NULL,
  `cid` bigint(20) NOT NULL,
  `parent_cid` bigint(20) NOT NULL,
  `name` varchar(64) DEFAULT NULL,
  `is_parent` int(11) DEFAULT NULL,
  `gmt_created` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taobao_shop_cat
-- ----------------------------

-- ----------------------------
-- Table structure for `taobao_user`
-- ----------------------------
DROP TABLE IF EXISTS `taobao_user`;
CREATE TABLE `taobao_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `taobao_user_id` varchar(32) DEFAULT NULL,
  `taobao_user_nick` varchar(32) DEFAULT NULL,
  `token_type` varchar(16) DEFAULT NULL,
  `access_token` varchar(64) DEFAULT NULL,
  `expires_in` bigint(20) DEFAULT NULL,
  `r1_expires_in` bigint(20) DEFAULT NULL,
  `r2_expires_in` bigint(20) DEFAULT NULL,
  `w1_expires_in` bigint(20) DEFAULT NULL,
  `w2_expires_in` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of taobao_user
-- ----------------------------

-- ----------------------------
-- Table structure for `task_accept`
-- ----------------------------
DROP TABLE IF EXISTS `task_accept`;
CREATE TABLE `task_accept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `status` int(11) NOT NULL,
  `taobao_trade_no` varchar(64) DEFAULT NULL,
  `gmt_created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_accept
-- ----------------------------
INSERT INTO `task_accept` VALUES ('1', '1', '3', '99', '32423432', '2014-12-26 13:03:21', '2014-12-26 13:03:21');
INSERT INTO `task_accept` VALUES ('2', '2', '3', '99', 'qq', '2014-12-26 13:03:22', '2014-12-26 13:03:22');

-- ----------------------------
-- Table structure for `task_list`
-- ----------------------------
DROP TABLE IF EXISTS `task_list`;
CREATE TABLE `task_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `seller_id` bigint(20) NOT NULL,
  `seller_name` varchar(32) DEFAULT NULL,
  `amount_pay` bigint(20) NOT NULL,
  `amount_fee` bigint(20) NOT NULL,
  `item_id` varchar(64) NOT NULL,
  `item_name` varchar(256) DEFAULT NULL,
  `item_url` varchar(256) DEFAULT NULL,
  `item_pic` varchar(256) DEFAULT NULL,
  `descript` varchar(2048) DEFAULT NULL,
  `comment` varchar(2048) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `scored` int(11) DEFAULT NULL,
  `gmt_created` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_list
-- ----------------------------
INSERT INTO `task_list` VALUES ('1', '4', '卖家昵称', '212', '1100', '111', '测试商品1', 'http://detail.tmall.com/item.htm?id=111', 'http://gi1.md.alicdn.com/imgextra/i1/2020696429/TB2QhNOaVXXXXXoXpXXXXXXXXXX_!!2020696429.jpg_430x430q90.jpg', '1212e1', '31323', '99', '1', '2014-12-27 23:28:59');
INSERT INTO `task_list` VALUES ('2', '4', '卖家昵称', '212', '1100', '111', '测试商品1', 'http://detail.tmall.com/item.htm?id=111', 'http://gi1.md.alicdn.com/imgextra/i1/2020696429/TB2QhNOaVXXXXXoXpXXXXXXXXXX_!!2020696429.jpg_430x430q90.jpg', '放大放大', '费大幅', '99', null, '2014-12-27 23:29:01');
INSERT INTO `task_list` VALUES ('3', '4', '卖家昵称', '212', '1200', '111', '测试商品1', 'http://detail.tmall.com/item.htm?id=111', 'http://gi1.md.alicdn.com/imgextra/i1/2020696429/TB2QhNOaVXXXXXoXpXXXXXXXXXX_!!2020696429.jpg_430x430q90.jpg', 'd\\第三个fewafe', 'd\\第三个fdafda', '1', null, '2014-12-27 23:29:01');
INSERT INTO `task_list` VALUES ('4', '4', '卖家昵称', '212', '11', '111', '测试商品1', 'http://detail.tmall.com/item.htm?id=111', 'http://gi1.md.alicdn.com/imgextra/i1/2020696429/TB2QhNOaVXXXXXoXpXXXXXXXXXX_!!2020696429.jpg_430x430q90.jpg', '放大放大', '费大幅', '0', null, '2014-12-27 23:29:02');
INSERT INTO `task_list` VALUES ('5', '4', null, '212', '1100', '111', '测试商品1', 'http://detail.tmall.com/item.htm?id=111', 'http://gi1.md.alicdn.com/imgextra/i1/2020696429/TB2QhNOaVXXXXXoXpXXXXXXXXXX_!!2020696429.jpg_430x430q90.jpg', '1212e1', '31323', '0', null, '2014-12-27 23:29:04');
INSERT INTO `task_list` VALUES ('6', '4', '卖家昵称', '340000', '12200', '$item.numIid', '测试商品343', 'http://item.taobao.com/item.htm?id=null', 'http://gi4.md.alicdn.com/imgextra/i4/201688881/T23RH_XdRbXXXXXXXX_!!201688881.jpg_430x430q90.jpg', '1）货比三家，假聊', '备注备注', '0', null, '2014-12-29 10:36:28');

-- ----------------------------
-- Table structure for `task_props`
-- ----------------------------
DROP TABLE IF EXISTS `task_props`;
CREATE TABLE `task_props` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `task_id` bigint(20) NOT NULL,
  `require_key` varchar(64) NOT NULL,
  `require_value` varchar(512) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_props
-- ----------------------------
INSERT INTO `task_props` VALUES ('1', '1', 'SEARCH_KEY', '11');
INSERT INTO `task_props` VALUES ('2', '1', 'SEARCH_URL', 'http://taobao.com');
INSERT INTO `task_props` VALUES ('3', '1', 'SEARCH_RESULT_PAGE', '12');
INSERT INTO `task_props` VALUES ('4', '1', 'SEARCH_RESULT_ROW', '1');
INSERT INTO `task_props` VALUES ('5', '1', 'SEARCH_RESULT_COL', '3');
INSERT INTO `task_props` VALUES ('6', '2', 'SEARCH_KEY', '打发');
INSERT INTO `task_props` VALUES ('7', '2', 'SEARCH_RESULT_PAGE', '12');
INSERT INTO `task_props` VALUES ('8', '2', 'SEARCH_RESULT_ROW', '1');
INSERT INTO `task_props` VALUES ('9', '2', 'SEARCH_RESULT_COL', '3');
INSERT INTO `task_props` VALUES ('25', '5', 'SEARCH_KEY', '11');
INSERT INTO `task_props` VALUES ('26', '5', 'SEARCH_URL', 'http://taobao.com');
INSERT INTO `task_props` VALUES ('27', '5', 'SEARCH_RESULT_PAGE', '12');
INSERT INTO `task_props` VALUES ('28', '5', 'SEARCH_RESULT_ROW', '1');
INSERT INTO `task_props` VALUES ('29', '5', 'SEARCH_RESULT_COL', '3');
INSERT INTO `task_props` VALUES ('35', '4', 'SEARCH_KEY', '打发333');
INSERT INTO `task_props` VALUES ('36', '4', 'SEARCH_URL', 'http://tmall.com');
INSERT INTO `task_props` VALUES ('37', '4', 'SEARCH_RESULT_PAGE', '12');
INSERT INTO `task_props` VALUES ('38', '4', 'SEARCH_RESULT_ROW', '1');
INSERT INTO `task_props` VALUES ('39', '4', 'SEARCH_RESULT_COL', '3');
INSERT INTO `task_props` VALUES ('45', '3', 'SEARCH_KEY', '11');
INSERT INTO `task_props` VALUES ('46', '3', 'SEARCH_URL', 'http://taobao.com');
INSERT INTO `task_props` VALUES ('47', '3', 'SEARCH_RESULT_PAGE', '12');
INSERT INTO `task_props` VALUES ('48', '3', 'SEARCH_RESULT_ROW', '1');
INSERT INTO `task_props` VALUES ('49', '3', 'SEARCH_RESULT_COL', '3');
INSERT INTO `task_props` VALUES ('50', '6', 'SEARCH_KEY', '佳能相机');
INSERT INTO `task_props` VALUES ('51', '6', 'SEARCH_URL', 'http://tmall.com');
INSERT INTO `task_props` VALUES ('52', '6', 'SEARCH_RESULT_PAGE', '2');
INSERT INTO `task_props` VALUES ('53', '6', 'SEARCH_RESULT_ROW', '1');
INSERT INTO `task_props` VALUES ('54', '6', 'SEARCH_RESULT_COL', '2');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(32) NOT NULL,
  `password` varchar(64) NOT NULL,
  `qq` varchar(32) NOT NULL,
  `wangwang` varchar(32) NOT NULL,
  `mail` varchar(64) DEFAULT NULL,
  `phone` varchar(16) DEFAULT NULL,
  `user_type` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `last_login` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `shop_url` varchar(512) DEFAULT NULL,
  `taobao_user_id` varchar(32) DEFAULT NULL,
  `taobao_user_nick` varchar(64) DEFAULT NULL,
  `recommend_qq` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('2', 'admin', '0d24d52111e60694', '1', '1', 'foodoon@qq.com', '1', '2', '1', '2014-12-27 13:42:25', null, null, null, '11');
INSERT INTO `user` VALUES ('3', 'buyer', '0d24d52111e60694', '1111', '买家唉', '111@qq.com', '17098158159', '0', '1', '2014-12-27 13:42:27', 'tt', null, null, '3');
INSERT INTO `user` VALUES ('4', 'seller', '0d24d52111e60694', '1111', '卖家旺旺', '111f@qq.com', '111', '1', '1', '2014-12-27 13:42:29', '111', '12123', '卖家昵称', '3');

-- ----------------------------
-- Table structure for `user_score`
-- ----------------------------
DROP TABLE IF EXISTS `user_score`;
CREATE TABLE `user_score` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `task_id` bigint(20) NOT NULL,
  `score` int(11) NOT NULL,
  `remark` varchar(2048) DEFAULT NULL,
  `gmt_created` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_score
-- ----------------------------
INSERT INTO `user_score` VALUES ('1', '3', '1', '4', '发发', '2014-12-28 13:06:21');

-- ----------------------------
-- Table structure for `user_score_stat`
-- ----------------------------
DROP TABLE IF EXISTS `user_score_stat`;
CREATE TABLE `user_score_stat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `score` int(20) NOT NULL,
  `score_num` int(20) NOT NULL,
  `gmt_modified` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_score_stat
-- ----------------------------
INSERT INTO `user_score_stat` VALUES ('1', '3', '4', '1', '2014-12-28 13:06:21');
