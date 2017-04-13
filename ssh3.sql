/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : ssh3

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2017-04-13 17:57:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `acl`
-- ----------------------------
DROP TABLE IF EXISTS `acl`;
CREATE TABLE `acl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `acl_id` int(11) DEFAULT NULL,
  `menu` tinyblob,
  `role` tinyblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of acl
-- ----------------------------

-- ----------------------------
-- Table structure for `activity`
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `activity_id` int(11) DEFAULT NULL,
  `content_desc` varchar(255) DEFAULT NULL,
  `merchant` tinyblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of activity
-- ----------------------------

-- ----------------------------
-- Table structure for `activity_member`
-- ----------------------------
DROP TABLE IF EXISTS `activity_member`;
CREATE TABLE `activity_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `activity` tinyblob,
  `activity_member_id` int(11) DEFAULT NULL,
  `apply_time` datetime DEFAULT NULL,
  `approved` int(11) DEFAULT NULL,
  `customer` tinyblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of activity_member
-- ----------------------------

-- ----------------------------
-- Table structure for `advertise`
-- ----------------------------
DROP TABLE IF EXISTS `advertise`;
CREATE TABLE `advertise` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `adv_id` int(11) DEFAULT NULL,
  `detail_html` varchar(255) DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `link_content` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `order_index` int(11) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of advertise
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_account`
-- ----------------------------
DROP TABLE IF EXISTS `alq_account`;
CREATE TABLE `alq_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `account_name` varchar(20) NOT NULL,
  `account_password` varchar(100) NOT NULL,
  `account_real_name` varchar(50) NOT NULL,
  `address` varchar(200) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `is_locked` int(11) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  `tel` varchar(11) NOT NULL,
  `times` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_account_name` (`account_name`),
  KEY `index_mobile` (`mobile`),
  KEY `index_gender` (`gender`),
  KEY `index_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_account
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_area`
-- ----------------------------
DROP TABLE IF EXISTS `alq_area`;
CREATE TABLE `alq_area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `area_id` bigint(20) DEFAULT NULL,
  `letter` varchar(255) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_parent_id` (`parent_id`),
  KEY `index_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_area
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_attachment`
-- ----------------------------
DROP TABLE IF EXISTS `alq_attachment`;
CREATE TABLE `alq_attachment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `address` varchar(200) NOT NULL,
  `field` int(11) DEFAULT NULL,
  `memory` double DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `descs` varchar(255) DEFAULT NULL,
  `isbanner` bit(1) NOT NULL,
  `diary_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_b2e5fj89ebw2nqyj1rxlci3kh` (`diary_id`),
  CONSTRAINT `FK_b2e5fj89ebw2nqyj1rxlci3kh` FOREIGN KEY (`diary_id`) REFERENCES `diary` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_attachment
-- ----------------------------
INSERT INTO `alq_attachment` VALUES ('1', '', '', '2017-04-05 10:40:30', '2017-04-05 10:40:30', 'upload/jianqiao/20170405104029080735929.jpg', null, null, 'timg (3).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('2', '', '', '2017-04-05 10:40:34', '2017-04-05 10:40:34', 'upload/jianqiao/20170405104033668754287.jpg', null, null, 'timg (4).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('3', '', '', '2017-04-05 10:40:37', '2017-04-05 10:40:37', 'upload/jianqiao/20170405104036635043620.jpg', null, null, 'timg (5).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('4', '', '', '2017-04-05 10:40:41', '2017-04-05 10:40:41', 'upload/jianqiao/20170405104040354026315.jpg', null, null, 'timg (6).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('5', '', '', '2017-04-05 10:44:01', '2017-04-05 10:44:01', 'upload/jianqiao/20170405104400642650917.jpg', null, null, 'timg (3).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('6', '', '', '2017-04-05 10:44:04', '2017-04-05 10:44:04', 'upload/jianqiao/20170405104403306805932.jpg', null, null, 'timg (4).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('7', '', '', '2017-04-05 10:44:06', '2017-04-05 10:44:06', 'upload/jianqiao/20170405104405558998755.jpg', null, null, 'timg (5).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('8', '', '', '2017-04-05 10:44:09', '2017-04-05 10:44:09', 'upload/jianqiao/20170405104408661035094.jpg', null, null, 'timg (6).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('9', '', '', '2017-04-05 10:47:06', '2017-04-05 10:47:06', 'upload/jianqiao/20170405104705445401221.jpg', null, null, 'timg (3).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('10', '', '', '2017-04-05 10:47:11', '2017-04-05 10:47:11', 'upload/jianqiao/20170405104710978561645.jpg', null, null, 'timg (4).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('11', '', '', '2017-04-05 10:47:14', '2017-04-05 10:47:14', 'upload/jianqiao/20170405104713145559727.jpg', null, null, 'timg (5).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('12', '', '', '2017-04-05 10:48:02', '2017-04-05 10:48:02', 'upload/jianqiao/20170405104801304135301.jpg', null, null, 'timg (3).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('13', '', '', '2017-04-05 10:48:04', '2017-04-05 10:48:04', 'upload/jianqiao/20170405104803959764672.jpg', null, null, 'timg (6).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('14', '', '', '2017-04-05 10:48:05', '2017-04-05 10:48:05', 'upload/jianqiao/20170405104804022184517.jpg', null, null, 'timg (4).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('15', '', '', '2017-04-05 10:48:47', '2017-04-05 10:48:47', 'upload/jianqiao/20170405104846804126964.jpg', null, null, 'timg (3).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('16', '', '', '2017-04-05 10:48:51', '2017-04-05 10:48:51', 'upload/jianqiao/20170405104850137799421.jpg', null, null, 'timg (4).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('17', '', '', '2017-04-05 10:48:54', '2017-04-05 10:48:54', 'upload/jianqiao/20170405104853882902994.jpg', null, null, 'timg (5).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('18', '', '', '2017-04-05 10:49:00', '2017-04-05 10:49:00', 'upload/jianqiao/20170405104859751090735.jpg', null, null, 'timg (6).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('19', '', '', '2017-04-05 10:50:43', '2017-04-05 10:50:43', 'upload/jianqiao/20170405105042691944186.jpg', null, null, '首页 (2).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('20', '', '', '2017-04-05 10:50:48', '2017-04-05 10:50:48', 'upload/jianqiao/20170405105047752717367.jpg', null, null, '06d11dd929117d14d51160662b914948.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('21', '', '', '2017-04-05 10:50:55', '2017-04-05 10:50:55', 'upload/jianqiao/20170405105054739364955.jpg', null, null, '686950d2427d3c168f7da6a3deb00be0.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('22', '', '', '2017-04-05 10:50:58', '2017-04-05 10:50:58', 'upload/jianqiao/20170405105057725917820.jpg', null, null, '61ef9b3ad198b8356a996626a6a93b44.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('23', '', '', '2017-04-05 10:51:21', '2017-04-05 10:51:21', 'upload/jianqiao/20170405105120925227367.jpg_600.jpg', null, null, '576d1aafc37e6.jpg_600.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('24', '', '', '2017-04-05 10:51:24', '2017-04-05 10:51:24', 'upload/jianqiao/20170405105123858609948.jpg', null, null, '005W6FrQzy6PYvTMaSGf6&690.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('25', '', '', '2017-04-05 10:51:28', '2017-04-05 10:51:28', 'upload/jianqiao/20170405105127259380120.jpg', null, null, '86v58PICwQg.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('26', '', '', '2017-04-05 10:51:31', '2017-04-05 10:51:31', 'upload/jianqiao/20170405105130405988859.jpg', null, null, 'timg.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('27', '', '', '2017-04-05 11:02:22', '2017-04-05 11:02:22', 'upload/jianqiao/20170405110221717870764.jpg', null, null, 'timg (3).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('28', '', '', '2017-04-05 11:12:59', '2017-04-05 11:12:59', 'upload/jianqiao/20170405111257281174231.png', null, null, 'QQ截图20170327111713.png', null, '', null);
INSERT INTO `alq_attachment` VALUES ('29', '', '', '2017-04-05 11:15:40', '2017-04-05 11:15:40', 'upload/jianqiao/20170405111539667619149.jpg_600.jpg', null, null, '576d1aafc37e6.jpg_600.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('30', '', '', '2017-04-05 11:15:42', '2017-04-05 11:15:42', 'upload/jianqiao/20170405111542009741852.jpg', null, null, '005W6FrQzy6PYvTMaSGf6&690.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('31', '', '', '2017-04-05 11:15:45', '2017-04-05 11:15:45', 'upload/jianqiao/20170405111544728488054.jpg', null, null, '86v58PICwQg.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('32', '', '', '2017-04-05 11:15:48', '2017-04-05 11:15:48', 'upload/jianqiao/20170405111547193282888.jpg', null, null, 'timg.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('33', '', '', '2017-04-05 11:24:12', '2017-04-05 11:24:12', 'upload/jianqiao/20170405112411625950974.jpg', null, null, 'timg (3).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('34', '', '', '2017-04-05 11:29:52', '2017-04-05 11:29:52', 'upload/jianqiao/20170405112951180875102.jpg', null, null, '7369b867061cc4f6ea60f6d0e40cdddd.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('35', '', '', '2017-04-05 11:35:46', '2017-04-05 11:35:46', 'upload/jianqiao/20170405113545299062152.jpg', null, null, 'timg (7).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('36', '', '', '2017-04-05 11:43:01', '2017-04-05 11:43:01', 'upload/jianqiao/20170405114259693891657.jpg', null, null, '7369b867061cc4f6ea60f6d0e40cdddd.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('37', '', '', '2017-04-05 11:56:56', '2017-04-05 11:56:56', 'upload/jianqiao/20170405115655497220740.jpg', null, null, '头像.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('38', '', '', '2017-04-05 13:40:30', '2017-04-05 13:40:30', 'upload/jianqiao/20170405134028556789875.jpg', null, null, 'u=1326595458,1507536621&fm=23&gp=0.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('39', '', '', '2017-04-05 13:42:40', '2017-04-05 13:42:40', 'upload/jianqiao/20170405134239384388957.jpg', null, null, '06d11dd929117d14d51160662b914948.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('40', '', '', '2017-04-05 13:42:44', '2017-04-05 13:42:44', 'upload/jianqiao/20170405134243785821079.jpg', null, null, '61ef9b3ad198b8356a996626a6a93b44.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('41', '', '', '2017-04-05 13:42:52', '2017-04-05 13:42:52', 'upload/jianqiao/20170405134251358704270.jpg', null, null, '首页.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('42', '', '', '2017-04-05 13:43:04', '2017-04-05 13:43:04', 'upload/jianqiao/20170405134303291525098.jpg', null, null, '686950d2427d3c168f7da6a3deb00be0.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('43', '', '', '2017-04-05 13:44:48', '2017-04-05 13:44:48', 'upload/jianqiao/20170405134446622343837.png', null, null, 'E9FIFMGM07FV75B2U3S_1NS.png', null, '', null);
INSERT INTO `alq_attachment` VALUES ('44', '', '', '2017-04-05 13:44:51', '2017-04-05 13:44:51', 'upload/jianqiao/20170405134448650934041.png', null, null, 'FR{_@]CHXAQ)X)J0FQZ(I9Y.png', null, '', null);
INSERT INTO `alq_attachment` VALUES ('45', '', '', '2017-04-05 13:44:52', '2017-04-05 13:44:52', 'upload/jianqiao/20170405134450865469031.png', null, null, 'HTBG~KBML59NBOBUW)H2_0A.png', null, '', null);
INSERT INTO `alq_attachment` VALUES ('46', '', '', '2017-04-05 13:44:55', '2017-04-05 13:44:55', 'upload/jianqiao/20170405134452897148872.png', null, null, 'N[9MA2HXDAN[6PJ8D$MZ8)J.png', null, '', null);
INSERT INTO `alq_attachment` VALUES ('47', '', '', '2017-04-05 13:53:22', '2017-04-05 13:53:22', 'upload/jianqiao/20170405135321495386016.jpg', null, null, 'u=1326595458,1507536621&fm=23&gp=0.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('48', '', '', '2017-04-05 14:01:40', '2017-04-05 14:01:40', 'upload/jianqiao/20170405140139647287059.jpg', null, null, 'timg (7).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('49', '', '', '2017-04-05 14:05:06', '2017-04-05 14:05:06', 'upload/jianqiao/20170405140506056991241.jpg', null, null, '血压.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('50', '', '', '2017-04-05 14:05:30', '2017-04-05 14:05:30', 'upload/jianqiao/20170405140529897473808.jpg', null, null, '头像.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('51', '', '', '2017-04-05 14:05:53', '2017-04-05 14:05:53', 'upload/jianqiao/20170405140552226906405.jpg', null, null, '86v58PICwQg.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('52', '', '', '2017-04-05 14:09:37', '2017-04-05 14:09:37', 'upload/jianqiao/20170405140935892492315.jpg', null, null, 'timg (3).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('53', '', '', '2017-04-05 14:10:34', '2017-04-05 14:10:34', 'upload/jianqiao/20170405141033598498910.jpg', null, null, 'timg (7).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('54', '', '', '2017-04-05 14:10:38', '2017-04-05 14:10:38', 'upload/jianqiao/20170405141037534195606.jpg', null, null, 'timg (5).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('55', '', '', '2017-04-05 14:10:41', '2017-04-05 14:10:41', 'upload/jianqiao/20170405141040282217994.jpg', null, null, 'timg (3).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('56', '', '', '2017-04-05 14:10:44', '2017-04-05 14:10:44', 'upload/jianqiao/20170405141043977864868.jpg', null, null, 'timg (4).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('57', '', '', '2017-04-05 14:15:14', '2017-04-05 14:15:14', 'upload/jianqiao/20170405141514160464622.jpg', null, null, 'timg (7).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('58', '', '', '2017-04-05 14:32:34', '2017-04-05 14:32:34', 'upload/jianqiao/20170405143234021457646.jpg', null, null, '39c8c41f4a06256dba30cd283cdafed1.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('59', '', '', '2017-04-05 14:32:38', '2017-04-05 14:32:38', 'upload/jianqiao/20170405143237654955784.jpg', null, null, '7369b867061cc4f6ea60f6d0e40cdddd.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('60', '', '', '2017-04-05 14:32:42', '2017-04-05 14:32:42', 'upload/jianqiao/20170405143241863914570.jpg', null, null, '测试.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('61', '', '', '2017-04-05 14:32:46', '2017-04-05 14:32:46', 'upload/jianqiao/20170405143245231952553.jpg', null, null, '686950d2427d3c168f7da6a3deb00be0.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('62', '', '', '2017-04-05 14:42:17', '2017-04-05 14:42:17', 'upload/jianqiao/20170405144216925314334.jpg', null, null, '06d11dd929117d14d51160662b914948.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('63', '', '', '2017-04-05 14:42:43', '2017-04-05 14:42:43', 'upload/jianqiao/20170405144242310669758.jpg', null, null, '39c8c41f4a06256dba30cd283cdafed1.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('64', '', '', '2017-04-05 14:54:17', '2017-04-05 14:54:17', 'upload/jianqiao/20170405145416727981253.jpg', null, null, 'timg (4).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('65', '', '', '2017-04-05 14:54:20', '2017-04-05 14:54:20', 'upload/jianqiao/20170405145419828316999.jpg', null, null, 'timg (5).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('66', '', '', '2017-04-05 14:54:24', '2017-04-05 14:54:24', 'upload/jianqiao/20170405145423501092673.jpg', null, null, 'timg (6).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('67', '', '', '2017-04-05 14:54:27', '2017-04-05 14:54:27', 'upload/jianqiao/20170405145426789138565.jpg', null, null, 'timg (7).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('68', '', '', '2017-04-05 15:50:06', '2017-04-05 15:50:06', 'upload/jianqiao/20170405155005458546441.jpg', null, null, 'timg (12).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('69', '', '', '2017-04-05 16:08:53', '2017-04-05 16:08:53', 'upload/jianqiao/20170405160852664722378.jpg', null, null, 'timg (8).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('70', '', '', '2017-04-05 16:09:31', '2017-04-05 16:09:31', 'upload/jianqiao/20170405160930464443962.jpg', null, null, 'timg (8).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('71', '', '', '2017-04-05 16:28:21', '2017-04-05 16:28:21', 'upload/jianqiao/20170405162819525270931.png', null, null, 'E9FIFMGM07FV75B2U3S_1NS.png', null, '', null);
INSERT INTO `alq_attachment` VALUES ('72', '', '', '2017-04-05 16:28:24', '2017-04-05 16:28:24', 'upload/jianqiao/20170405162821759954287.png', null, null, 'FR{_@]CHXAQ)X)J0FQZ(I9Y.png', null, '', null);
INSERT INTO `alq_attachment` VALUES ('73', '', '', '2017-04-05 16:28:25', '2017-04-05 16:28:25', 'upload/jianqiao/20170405162823740895077.png', null, null, 'HTBG~KBML59NBOBUW)H2_0A.png', null, '', null);
INSERT INTO `alq_attachment` VALUES ('74', '', '', '2017-04-05 16:28:28', '2017-04-05 16:28:28', 'upload/jianqiao/20170405162825719182276.png', null, null, 'N[9MA2HXDAN[6PJ8D$MZ8)J.png', null, '', null);
INSERT INTO `alq_attachment` VALUES ('75', '', '', '2017-04-05 16:39:41', '2017-04-05 16:39:41', 'upload/jianqiao/20170405163940327991553.jpg', null, null, '61ef9b3ad198b8356a996626a6a93b44.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('76', '', '', '2017-04-05 16:39:45', '2017-04-05 16:39:45', 'upload/jianqiao/20170405163944511884367.jpg', null, null, '06d11dd929117d14d51160662b914948.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('77', '', '', '2017-04-05 16:39:49', '2017-04-05 16:39:49', 'upload/jianqiao/20170405163948925221901.jpg', null, null, '7369b867061cc4f6ea60f6d0e40cdddd.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('78', '', '', '2017-04-05 16:39:54', '2017-04-05 16:39:54', 'upload/jianqiao/20170405163953662181318.jpg', null, null, 'cdc727c2daaa67cfe59d71410534c5ed.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('79', '', '', '2017-04-05 16:40:29', '2017-04-05 16:40:29', 'upload/jianqiao/20170405164028138124708.jpg', null, null, '86v58PICwQg.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('80', '', '', '2017-04-05 16:40:35', '2017-04-05 16:40:35', 'upload/jianqiao/20170405164035049703859.jpg_600.jpg', null, null, '576d1aafc37e6.jpg_600.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('81', '', '', '2017-04-05 16:40:45', '2017-04-05 16:40:45', 'upload/jianqiao/20170405164044815968383.jpg', null, null, '005W6FrQzy6PYvTMaSGf6&690.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('82', '', '', '2017-04-05 16:40:52', '2017-04-05 16:40:52', 'upload/jianqiao/20170405164051673417062.jpg', null, null, 'timg.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('84', '', '', '2017-04-05 16:46:45', '2017-04-05 16:46:45', 'upload/jianqiao/20170405164644959795528.jpg', null, null, '686950d2427d3c168f7da6a3deb00be0.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('85', '', '', '2017-04-05 16:46:58', '2017-04-05 16:46:58', 'upload/jianqiao/20170405164657966181478.jpg', null, null, '首页.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('86', '', '', '2017-04-05 16:47:16', '2017-04-05 16:47:16', 'upload/jianqiao/20170405164715791750367.jpg', null, null, 'timg (7).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('87', '', '', '2017-04-05 16:47:20', '2017-04-05 16:47:20', 'upload/jianqiao/20170405164719568790487.jpg', null, null, 'timg (3).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('88', '', '', '2017-04-05 16:47:24', '2017-04-05 16:47:24', 'upload/jianqiao/20170405164724022601925.jpg', null, null, '首页 (4).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('89', '', '', '2017-04-05 16:47:25', '2017-04-05 16:47:25', 'upload/jianqiao/20170405164724641296792.jpg', null, null, 'timg (4).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('90', '', '', '2017-04-05 16:47:28', '2017-04-05 16:47:28', 'upload/jianqiao/20170405164727952680509.jpg', null, null, 'timg (5).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('91', '', '', '2017-04-05 16:51:06', '2017-04-09 14:34:26', 'upload/jianqiao/20170405165105586055068.jpg', null, null, '2630f84c55269d676cfdfe7f9607c8c9.jpg', '11111', '', null);
INSERT INTO `alq_attachment` VALUES ('92', '', '', '2017-04-05 16:51:30', '2017-04-05 16:51:30', 'upload/jianqiao/20170405165129754570764.jpg', null, null, '01ebe32e3a85350c74a926938d17611b.jpg', '图片', '', null);
INSERT INTO `alq_attachment` VALUES ('94', '', '', '2017-04-05 16:52:06', '2017-04-05 16:52:06', 'upload/jianqiao/20170405165205394749346.jpg', null, null, '0e0e5352d74213ec06eec944effcd9e2.jpg', '测试图片', '', null);
INSERT INTO `alq_attachment` VALUES ('95', '', '', '2017-04-05 17:12:58', '2017-04-05 17:12:58', 'upload/jianqiao/20170405171256827026346.jpg', null, null, '06d11dd929117d14d51160662b914948.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('96', '', '', '2017-04-05 17:13:03', '2017-04-05 17:13:03', 'upload/jianqiao/20170405171302847493303.jpg', null, null, '测试5.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('97', '', '', '2017-04-05 17:13:08', '2017-04-05 17:13:08', 'upload/jianqiao/20170405171307542995867.jpg', null, null, '7369b867061cc4f6ea60f6d0e40cdddd.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('98', '', '', '2017-04-05 17:15:44', '2017-04-05 17:15:44', 'upload/jianqiao/20170405171543683286977.jpg', null, null, 'timg (4).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('99', '', '', '2017-04-05 17:15:49', '2017-04-05 17:15:49', 'upload/jianqiao/20170405171548652453768.jpg', null, null, 'timg (3).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('100', '', '', '2017-04-05 17:15:53', '2017-04-05 17:15:53', 'upload/jianqiao/20170405171552226278704.jpg', null, null, 'timg (5).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('101', '', '', '2017-04-05 17:15:57', '2017-04-05 17:15:57', 'upload/jianqiao/20170405171556869767103.jpg', null, null, 'timg (6).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('102', '', '', '2017-04-05 17:19:00', '2017-04-05 17:19:00', 'upload/jianqiao/20170405171859633668958.jpg', null, null, 'timg (12).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('103', '', '', '2017-04-05 17:29:26', '2017-04-05 17:29:26', 'upload/jianqiao/20170405172925014985570.jpg', null, null, 'timg (3).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('104', '', '', '2017-04-05 17:29:29', '2017-04-05 17:29:29', 'upload/jianqiao/20170405172927873416521.jpg', null, null, 'timg (4).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('105', '', '', '2017-04-05 17:29:31', '2017-04-05 17:29:31', 'upload/jianqiao/20170405172930309520355.jpg', null, null, 'timg (5).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('106', '', '', '2017-04-05 17:29:34', '2017-04-05 17:29:34', 'upload/jianqiao/20170405172934082813730.jpg', null, null, 'timg (6).jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('107', '', '', '2017-04-05 17:45:55', '2017-04-05 17:45:55', 'upload/jianqiao/20170405174554867378332.jpg', null, null, '7369b867061cc4f6ea60f6d0e40cdddd.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('108', '', '', '2017-04-05 17:46:58', '2017-04-05 17:46:58', 'upload/jianqiao/20170405174657632648710.jpg', null, null, '7369b867061cc4f6ea60f6d0e40cdddd.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('110', '', '', '2017-04-06 10:32:07', '2017-04-06 10:32:07', 'upload/jianqiao/20170406103205658465831.png', null, null, 'QQ截图20170327111713.png', null, '', null);
INSERT INTO `alq_attachment` VALUES ('111', '', '', '2017-04-06 11:27:45', '2017-04-06 11:28:11', 'upload/jianqiao/20170406112744872535146.jpg', null, null, '005W6FrQzy6PYvTMaSGf6&690.jpg', null, '', '2');
INSERT INTO `alq_attachment` VALUES ('112', '', '', '2017-04-06 11:29:31', '2017-04-06 11:29:31', 'upload/jianqiao/20170406112930218222883.png', null, null, 'QQ截图20170303113906.png', null, '', null);
INSERT INTO `alq_attachment` VALUES ('113', '', '', '2017-04-06 11:29:33', '2017-04-06 11:37:16', 'upload/jianqiao/20170406112932195841282.png', null, null, 'QQ截图20170303153022.png', null, '', '5');
INSERT INTO `alq_attachment` VALUES ('114', '', '', '2017-04-06 11:37:45', '2017-04-06 11:37:45', 'upload/jianqiao/20170406113743978197079.jpg', null, null, '61.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('115', '', '', '2017-04-06 11:40:09', '2017-04-06 11:40:09', 'upload/jianqiao/20170406114007525458001.png', null, null, 'QQ截图20170303113906.png', null, '', '8');
INSERT INTO `alq_attachment` VALUES ('116', '', '', '2017-04-06 11:49:52', '2017-04-06 11:49:52', 'upload/jianqiao/20170406114951134602875.png', null, null, 'QQ截图20170327111713.png', null, '', null);
INSERT INTO `alq_attachment` VALUES ('117', '', '', '2017-04-09 14:21:14', '2017-04-09 14:21:14', 'upload/jianqiao/20170409142112477150653.jpg', null, null, 'u=2024258536,3334872528&fm=23&gp=0.jpg', 'nihao', '', null);
INSERT INTO `alq_attachment` VALUES ('118', '', '', '2017-04-09 14:23:30', '2017-04-09 14:23:30', 'upload/jianqiao/20170409142329496869311.jpg', null, null, 'u=1882046317,71439704&fm=23&gp=0.jpg', '', '', null);
INSERT INTO `alq_attachment` VALUES ('119', '', '', '2017-04-10 09:00:05', '2017-04-10 09:00:05', 'upload/jianqiao/20170410090003473511402.jpg', null, null, 'u=2024258536,3334872528&fm=23&gp=0.jpg', 'nihh', '', null);
INSERT INTO `alq_attachment` VALUES ('120', '', '', '2017-04-10 09:00:47', '2017-04-10 09:00:47', 'upload/jianqiao/20170410090045645627055.jpg', null, null, 'u=1882046317,71439704&fm=23&gp=0.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('121', '', '', '2017-04-13 14:36:38', '2017-04-13 14:36:38', 'upload/jianqiao/20170413143636767168519.jpg', null, null, 'u=2024258536,3334872528&fm=23&gp=0.jpg', null, '', null);
INSERT INTO `alq_attachment` VALUES ('122', '', '', '2017-04-13 17:29:22', '2017-04-13 17:29:22', 'upload/jianqiao/20170413172920923106602.jpg', null, null, 'head.jpg', null, '', null);

-- ----------------------------
-- Table structure for `alq_chatgroup_account`
-- ----------------------------
DROP TABLE IF EXISTS `alq_chatgroup_account`;
CREATE TABLE `alq_chatgroup_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  `account_name` varchar(100) DEFAULT NULL,
  `chat_group_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_account_id` (`account_id`),
  KEY `index_account_name` (`account_name`),
  KEY `index_chatgroup_id` (`chat_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_chatgroup_account
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_chatgroup_to_record`
-- ----------------------------
DROP TABLE IF EXISTS `alq_chatgroup_to_record`;
CREATE TABLE `alq_chatgroup_to_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  `sms_send_time` datetime DEFAULT NULL,
  `sms_state` int(11) NOT NULL,
  `step_watch` bigint(20) DEFAULT NULL,
  `times` int(11) DEFAULT NULL,
  `unique_key` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bp1ra6kd4mf3p209xwjkamy9k` (`unique_key`),
  KEY `index_unique_key` (`unique_key`),
  KEY `index_sms_state` (`sms_state`),
  KEY `index_sms_send_time` (`sms_send_time`),
  KEY `index_account_id` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_chatgroup_to_record
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_chat_group`
-- ----------------------------
DROP TABLE IF EXISTS `alq_chat_group`;
CREATE TABLE `alq_chat_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `account_num` bigint(20) DEFAULT NULL,
  `chat_group_name` varchar(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_arehd1ts2w6h3jlj1nkm6flhc` (`chat_group_name`),
  KEY `index_chat_group_name` (`chat_group_name`),
  KEY `index_chat_user_id` (`user_id`),
  KEY `index_account_num` (`account_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_chat_group
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_chat_group_content_log`
-- ----------------------------
DROP TABLE IF EXISTS `alq_chat_group_content_log`;
CREATE TABLE `alq_chat_group_content_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `msg_type` int(11) DEFAULT NULL,
  `sms_receive_id` bigint(20) DEFAULT NULL,
  `sms_receive_name` varchar(255) DEFAULT NULL,
  `sms_send_time` datetime DEFAULT NULL,
  `sms_sender_id` bigint(20) DEFAULT NULL,
  `sms_sender_name` varchar(100) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `step_watch` bigint(20) DEFAULT NULL,
  `times` int(11) DEFAULT NULL,
  `unique_key` varchar(255) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_gmyp61417hsr8pecihh9umlun` (`unique_key`),
  KEY `index_sms_sender_name` (`sms_sender_name`),
  KEY `index_sms_receive_name` (`sms_receive_name`),
  KEY `index_year` (`year`),
  KEY `index_month` (`month`),
  KEY `index_day` (`day`),
  KEY `index_state` (`state`),
  KEY `index_sms_sendtime` (`sms_send_time`),
  KEY `index_unique_key` (`unique_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_chat_group_content_log
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_constant_manager`
-- ----------------------------
DROP TABLE IF EXISTS `alq_constant_manager`;
CREATE TABLE `alq_constant_manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `china_name` varchar(30) NOT NULL,
  `constant_value` varchar(50) NOT NULL,
  `english_name` varchar(50) NOT NULL,
  `is_memory` int(11) DEFAULT NULL,
  `unit` varchar(20) DEFAULT NULL,
  `constant_type_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_english_name` (`english_name`),
  KEY `FK_qbebr1rf0ufto1lo0a1ncrcdw` (`constant_type_id`),
  CONSTRAINT `FK_qbebr1rf0ufto1lo0a1ncrcdw` FOREIGN KEY (`constant_type_id`) REFERENCES `alq_constant_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_constant_manager
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_constant_type`
-- ----------------------------
DROP TABLE IF EXISTS `alq_constant_type`;
CREATE TABLE `alq_constant_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `type_description` varchar(255) DEFAULT NULL,
  `type_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_type_name` (`type_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_constant_type
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_email_push`
-- ----------------------------
DROP TABLE IF EXISTS `alq_email_push`;
CREATE TABLE `alq_email_push` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `describer` varchar(255) DEFAULT NULL,
  `email_english_name` varchar(255) DEFAULT NULL,
  `email_receive_id` bigint(20) DEFAULT NULL,
  `email_receive_name` varchar(255) DEFAULT NULL,
  `email_sender_id` bigint(20) DEFAULT NULL,
  `email_sender_name` varchar(255) DEFAULT NULL,
  `email_title` varchar(255) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_vpovxyeqojkaapsi6sf1177k` (`email_english_name`),
  KEY `index_email_sender_name` (`email_sender_name`),
  KEY `index_email_receive_name` (`email_receive_name`),
  KEY `index_year` (`year`),
  KEY `index_month` (`month`),
  KEY `index_day` (`day`),
  KEY `index_state` (`state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_email_push
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_email_push_log`
-- ----------------------------
DROP TABLE IF EXISTS `alq_email_push_log`;
CREATE TABLE `alq_email_push_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `describer` varchar(255) DEFAULT NULL,
  `email_english_name` varchar(255) DEFAULT NULL,
  `email_receive_id` bigint(20) DEFAULT NULL,
  `email_receive_name` varchar(255) DEFAULT NULL,
  `email_sender_id` bigint(20) DEFAULT NULL,
  `email_sender_name` varchar(255) DEFAULT NULL,
  `email_title` varchar(255) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `stepwatch` bigint(20) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_462wgpb8eior7cnrvlo3pj7id` (`email_english_name`),
  KEY `index_email_sender_name` (`email_sender_name`),
  KEY `index_email_receive_name` (`email_receive_name`),
  KEY `index_year` (`year`),
  KEY `index_month` (`month`),
  KEY `index_day` (`day`),
  KEY `index_stepwatch` (`stepwatch`),
  KEY `index_state` (`state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_email_push_log
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_email_template`
-- ----------------------------
DROP TABLE IF EXISTS `alq_email_template`;
CREATE TABLE `alq_email_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `email_content` varchar(255) NOT NULL,
  `email_english_name` varchar(60) NOT NULL,
  `email_info_type_name` varchar(200) NOT NULL,
  `email_title` varchar(100) NOT NULL,
  `email_variable_tags` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6p09xuoq15kygfab0mrg1kfpa` (`email_english_name`),
  KEY `index_email_title` (`email_title`),
  KEY `index_email_english_name` (`email_english_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_email_template
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_feedback`
-- ----------------------------
DROP TABLE IF EXISTS `alq_feedback`;
CREATE TABLE `alq_feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `feed_back_type_id` bigint(20) DEFAULT NULL,
  `feedback_content` longtext,
  `feedback_title` varchar(100) DEFAULT NULL,
  `is_look` int(11) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_feedback_title` (`feedback_title`),
  KEY `index_is_look` (`is_look`),
  KEY `index_feedback_type_id` (`feed_back_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_feedback
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_feedbacktype`
-- ----------------------------
DROP TABLE IF EXISTS `alq_feedbacktype`;
CREATE TABLE `alq_feedbacktype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `type_name` varchar(80) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_type_name` (`type_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_feedbacktype
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_icon`
-- ----------------------------
DROP TABLE IF EXISTS `alq_icon`;
CREATE TABLE `alq_icon` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `typeicon` varchar(20) DEFAULT NULL,
  `urlicon` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_name` (`name`),
  KEY `index_type` (`typeicon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_icon
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_info_type`
-- ----------------------------
DROP TABLE IF EXISTS `alq_info_type`;
CREATE TABLE `alq_info_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `info_type_english_name` varchar(100) NOT NULL,
  `info_type_name` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bhnhn425ypykapwp1acvgp4b` (`info_type_english_name`),
  KEY `index_info_type_name` (`info_type_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_info_type
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_login_false_log`
-- ----------------------------
DROP TABLE IF EXISTS `alq_login_false_log`;
CREATE TABLE `alq_login_false_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `session_id` varchar(255) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_user_name` (`user_name`),
  KEY `index_password` (`password`),
  KEY `index_year` (`year`),
  KEY `index_month` (`month`),
  KEY `index_day` (`day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_login_false_log
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_manager`
-- ----------------------------
DROP TABLE IF EXISTS `alq_manager`;
CREATE TABLE `alq_manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `account` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_manager_account` (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_manager
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_one_menu`
-- ----------------------------
DROP TABLE IF EXISTS `alq_one_menu`;
CREATE TABLE `alq_one_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `image_name` varchar(20) NOT NULL,
  `is_show` int(11) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `attachment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_lpcmh2vw3jgl8g2pjugh00mv8` (`attachment_id`),
  CONSTRAINT `FK_lpcmh2vw3jgl8g2pjugh00mv8` FOREIGN KEY (`attachment_id`) REFERENCES `alq_attachment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_one_menu
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_push`
-- ----------------------------
DROP TABLE IF EXISTS `alq_push`;
CREATE TABLE `alq_push` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `describer` varchar(255) DEFAULT NULL,
  `message_type` int(11) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `push_key` varchar(255) DEFAULT NULL,
  `push_type` int(11) DEFAULT NULL,
  `receive_id` bigint(20) DEFAULT NULL,
  `receive_name` varchar(255) DEFAULT NULL,
  `sender_head` varchar(255) DEFAULT NULL,
  `sender_id` bigint(20) DEFAULT NULL,
  `sender_name` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_push
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_pushgroup_account`
-- ----------------------------
DROP TABLE IF EXISTS `alq_pushgroup_account`;
CREATE TABLE `alq_pushgroup_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  `account_name` varchar(100) DEFAULT NULL,
  `push_group_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_account_id` (`account_id`),
  KEY `index_account_name` (`account_name`),
  KEY `index_pushgroup_id` (`push_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_pushgroup_account
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_pushgroup_to_record`
-- ----------------------------
DROP TABLE IF EXISTS `alq_pushgroup_to_record`;
CREATE TABLE `alq_pushgroup_to_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `account_id` bigint(20) DEFAULT NULL,
  `push_send_time` datetime DEFAULT NULL,
  `push_state` int(11) NOT NULL,
  `push_times` int(11) DEFAULT NULL,
  `step_watch` bigint(20) DEFAULT NULL,
  `unique_key` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rsy5gy5d7rhwj15aedclpjfdd` (`unique_key`),
  KEY `index_unique_key` (`unique_key`),
  KEY `index_push_state` (`push_state`),
  KEY `index_push_send_time` (`push_send_time`),
  KEY `index_account_id` (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_pushgroup_to_record
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_push_content_log`
-- ----------------------------
DROP TABLE IF EXISTS `alq_push_content_log`;
CREATE TABLE `alq_push_content_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `push_receive_id` bigint(20) DEFAULT NULL,
  `push_receive_name` varchar(100) DEFAULT NULL,
  `push_send_time` datetime DEFAULT NULL,
  `push_state` int(11) DEFAULT NULL,
  `push_times` int(11) DEFAULT NULL,
  `push_type` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `step_watch` bigint(20) DEFAULT NULL,
  `unique_key` varchar(100) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_push_receive_name` (`push_receive_name`),
  KEY `index_year` (`year`),
  KEY `index_month` (`month`),
  KEY `index_day` (`day`),
  KEY `index_push_state` (`push_state`),
  KEY `index_state` (`state`),
  KEY `index_push_sendtime` (`push_send_time`),
  KEY `index_unique_key` (`unique_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_push_content_log
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_push_group`
-- ----------------------------
DROP TABLE IF EXISTS `alq_push_group`;
CREATE TABLE `alq_push_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `account_num` bigint(20) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `push_group_name` varchar(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_otdg0g553843versegdle0vfr` (`push_group_name`),
  KEY `index_push_group_name` (`push_group_name`),
  KEY `index_chat_user_id` (`user_id`),
  KEY `index_account_num` (`account_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_push_group
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_push_group_content_log`
-- ----------------------------
DROP TABLE IF EXISTS `alq_push_group_content_log`;
CREATE TABLE `alq_push_group_content_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `push_receive_id` bigint(20) DEFAULT NULL,
  `push_receive_name` varchar(100) DEFAULT NULL,
  `push_send_time` datetime DEFAULT NULL,
  `push_times` int(11) DEFAULT NULL,
  `push_type` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `step_watch` bigint(20) DEFAULT NULL,
  `unique_key` varchar(100) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_82btvcctm7ylffd78toj9dawt` (`unique_key`),
  KEY `index_push_receive_name` (`push_receive_name`),
  KEY `index_year` (`year`),
  KEY `index_month` (`month`),
  KEY `index_day` (`day`),
  KEY `index_state` (`state`),
  KEY `index_push_sendtime` (`push_send_time`),
  KEY `index_unique_key` (`unique_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_push_group_content_log
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_push_log`
-- ----------------------------
DROP TABLE IF EXISTS `alq_push_log`;
CREATE TABLE `alq_push_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `describer` varchar(255) DEFAULT NULL,
  `is_read` int(11) DEFAULT NULL,
  `message_type` int(11) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `push_key` varchar(255) DEFAULT NULL,
  `push_type` int(11) DEFAULT NULL,
  `receive_id` bigint(20) DEFAULT NULL,
  `receive_name` varchar(255) DEFAULT NULL,
  `sender_head` varchar(255) DEFAULT NULL,
  `sender_id` bigint(20) DEFAULT NULL,
  `sender_name` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `stepwatch` bigint(20) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_push_log
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_resource`
-- ----------------------------
DROP TABLE IF EXISTS `alq_resource`;
CREATE TABLE `alq_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_menu` int(11) DEFAULT NULL,
  `one_menu_id` bigint(20) DEFAULT NULL,
  `resource_level` int(11) DEFAULT NULL,
  `resource_name` varchar(50) NOT NULL,
  `resource_order` int(11) DEFAULT NULL,
  `resource_real_name` varchar(20) NOT NULL,
  `resource_url` varchar(200) DEFAULT NULL,
  `icon_id` bigint(20) DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_resource_real_name` (`resource_real_name`),
  KEY `index_resource_name` (`resource_name`),
  KEY `index_resource_level` (`resource_level`),
  KEY `index_resource_url` (`resource_url`),
  KEY `index_is_menu` (`is_menu`),
  KEY `FK_6a4av134ts9uklbqfhgk5ngie` (`icon_id`),
  KEY `FK_f4udafympwpe13k45fntyp8pl` (`resource_id`),
  CONSTRAINT `FK_6a4av134ts9uklbqfhgk5ngie` FOREIGN KEY (`icon_id`) REFERENCES `alq_icon` (`id`),
  CONSTRAINT `FK_f4udafympwpe13k45fntyp8pl` FOREIGN KEY (`resource_id`) REFERENCES `alq_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_resource
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_role`
-- ----------------------------
DROP TABLE IF EXISTS `alq_role`;
CREATE TABLE `alq_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `descript` varchar(200) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_descript` (`descript`),
  KEY `index_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_role
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_role_resource`
-- ----------------------------
DROP TABLE IF EXISTS `alq_role_resource`;
CREATE TABLE `alq_role_resource` (
  `role_id` bigint(20) NOT NULL,
  `resource_id` bigint(20) NOT NULL,
  KEY `FK_stc0k55ngifoa2eassidd8tfd` (`resource_id`),
  KEY `FK_5vhomd64hu3qni17wi8kvoxps` (`role_id`),
  CONSTRAINT `FK_5vhomd64hu3qni17wi8kvoxps` FOREIGN KEY (`role_id`) REFERENCES `alq_role` (`id`),
  CONSTRAINT `FK_stc0k55ngifoa2eassidd8tfd` FOREIGN KEY (`resource_id`) REFERENCES `alq_resource` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_role_resource
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_sensitivity`
-- ----------------------------
DROP TABLE IF EXISTS `alq_sensitivity`;
CREATE TABLE `alq_sensitivity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `index_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_sensitivity
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_sms_content_log`
-- ----------------------------
DROP TABLE IF EXISTS `alq_sms_content_log`;
CREATE TABLE `alq_sms_content_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `msg_type` int(11) DEFAULT NULL,
  `sms_receive_id` bigint(20) DEFAULT NULL,
  `sms_receive_name` varchar(100) DEFAULT NULL,
  `sms_send_time` datetime DEFAULT NULL,
  `sms_sender_id` bigint(20) DEFAULT NULL,
  `sms_sender_name` varchar(100) DEFAULT NULL,
  `sms_state` int(11) NOT NULL,
  `state` int(11) DEFAULT NULL,
  `step_watch` bigint(20) DEFAULT NULL,
  `times` int(11) DEFAULT NULL,
  `unique_key` varchar(100) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kpvo8qceap8pa68npl2442ioh` (`unique_key`),
  KEY `index_sms_sender_name` (`sms_sender_name`),
  KEY `index_sms_receive_name` (`sms_receive_name`),
  KEY `index_year` (`year`),
  KEY `index_month` (`month`),
  KEY `index_day` (`day`),
  KEY `index_sms_state` (`sms_state`),
  KEY `index_state` (`state`),
  KEY `index_sms_sendtime` (`sms_send_time`),
  KEY `index_unique_key` (`unique_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_sms_content_log
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_sms_push`
-- ----------------------------
DROP TABLE IF EXISTS `alq_sms_push`;
CREATE TABLE `alq_sms_push` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `describer` varchar(255) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `sms_english_name` varchar(255) DEFAULT NULL,
  `sms_receive_id` bigint(20) DEFAULT NULL,
  `sms_receive_name` varchar(255) DEFAULT NULL,
  `sms_sender_id` bigint(20) DEFAULT NULL,
  `sms_sender_name` varchar(255) DEFAULT NULL,
  `sms_title` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ppmfacawb15e1swke6ftyg2vu` (`sms_english_name`),
  KEY `index_sms_sender_name` (`sms_sender_name`),
  KEY `index_sms_receive_name` (`sms_receive_name`),
  KEY `index_year` (`year`),
  KEY `index_month` (`month`),
  KEY `index_day` (`day`),
  KEY `index_state` (`state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_sms_push
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_sms_push_log`
-- ----------------------------
DROP TABLE IF EXISTS `alq_sms_push_log`;
CREATE TABLE `alq_sms_push_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `describer` varchar(255) DEFAULT NULL,
  `month` int(11) DEFAULT NULL,
  `sms_english_name` varchar(255) DEFAULT NULL,
  `sms_receive_id` bigint(20) DEFAULT NULL,
  `sms_receive_name` varchar(255) DEFAULT NULL,
  `sms_sender_id` bigint(20) DEFAULT NULL,
  `sms_sender_name` varchar(255) DEFAULT NULL,
  `sms_title` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `stepwatch` bigint(20) DEFAULT NULL,
  `year` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pbqppfsd88b6kt800h6jm80rh` (`sms_english_name`),
  KEY `index_sms_sender_name` (`sms_sender_name`),
  KEY `index_sms_receive_name` (`sms_receive_name`),
  KEY `index_year` (`year`),
  KEY `index_month` (`month`),
  KEY `index_day` (`day`),
  KEY `index_stepwatch` (`stepwatch`),
  KEY `index_state` (`state`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_sms_push_log
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_sms_template`
-- ----------------------------
DROP TABLE IF EXISTS `alq_sms_template`;
CREATE TABLE `alq_sms_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `sms_content` varchar(255) NOT NULL,
  `sms_english_name` varchar(60) NOT NULL,
  `sms_info_type_name` varchar(200) NOT NULL,
  `sms_title` varchar(100) NOT NULL,
  `sms_variable_tags` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_enna41jjuliteb2y53lhu7u2k` (`sms_english_name`),
  KEY `index_sms_title` (`sms_title`),
  KEY `index_sms_english_name` (`sms_english_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_sms_template
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_update`
-- ----------------------------
DROP TABLE IF EXISTS `alq_update`;
CREATE TABLE `alq_update` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_must_to_update` int(11) DEFAULT NULL,
  `title` varchar(20) NOT NULL,
  `type` int(11) DEFAULT NULL,
  `version` varchar(20) NOT NULL,
  `attachment_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_title` (`title`),
  KEY `FK_85hejqsx1jadbf74tdev67swj` (`attachment_id`),
  CONSTRAINT `FK_85hejqsx1jadbf74tdev67swj` FOREIGN KEY (`attachment_id`) REFERENCES `alq_attachment` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_update
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_user`
-- ----------------------------
DROP TABLE IF EXISTS `alq_user`;
CREATE TABLE `alq_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `address` varchar(200) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `is_locked` int(11) DEFAULT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL,
  `real_name` varchar(50) NOT NULL,
  `tel` varchar(11) NOT NULL,
  `times` bigint(20) DEFAULT NULL,
  `user_name` varchar(20) NOT NULL,
  `user_password` varchar(100) NOT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_user_name` (`user_name`),
  KEY `index_email` (`email`),
  KEY `FK_t7tg3nwulm02d0uwib8fc1jro` (`role_id`),
  CONSTRAINT `FK_t7tg3nwulm02d0uwib8fc1jro` FOREIGN KEY (`role_id`) REFERENCES `alq_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_user
-- ----------------------------

-- ----------------------------
-- Table structure for `alq_user_log`
-- ----------------------------
DROP TABLE IF EXISTS `alq_user_log`;
CREATE TABLE `alq_user_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `browser` varchar(50) DEFAULT NULL,
  `login_ip` varchar(50) DEFAULT NULL,
  `login_name` varchar(50) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `logout_time` datetime DEFAULT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  `session_id` varchar(50) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_state` (`state`),
  KEY `index_userId` (`user_id`),
  KEY `index_sessionid` (`session_id`),
  KEY `index_login_time` (`login_time`),
  KEY `index_logout_time` (`logout_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of alq_user_log
-- ----------------------------

-- ----------------------------
-- Table structure for `balance`
-- ----------------------------
DROP TABLE IF EXISTS `balance`;
CREATE TABLE `balance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `balance_remain` bigint(20) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `deposit_fee` bigint(20) DEFAULT NULL,
  `fee` bigint(20) DEFAULT NULL,
  `have_withdrawal_fee` bigint(20) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  `last_update_user` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of balance
-- ----------------------------

-- ----------------------------
-- Table structure for `balance_trans`
-- ----------------------------
DROP TABLE IF EXISTS `balance_trans`;
CREATE TABLE `balance_trans` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `balance_remain` bigint(20) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `defray` bigint(20) DEFAULT NULL,
  `income` bigint(20) DEFAULT NULL,
  `pay_relative_id` varchar(255) DEFAULT NULL,
  `pay_status` int(11) DEFAULT NULL,
  `pay_time` datetime DEFAULT NULL,
  `pay_type` int(11) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of balance_trans
-- ----------------------------

-- ----------------------------
-- Table structure for `cash_receive_station`
-- ----------------------------
DROP TABLE IF EXISTS `cash_receive_station`;
CREATE TABLE `cash_receive_station` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `after_balance` bigint(20) DEFAULT NULL,
  `approve_date` datetime DEFAULT NULL,
  `audit_status` int(11) DEFAULT NULL,
  `audit_time` datetime DEFAULT NULL,
  `bank_abbr` varchar(255) DEFAULT NULL,
  `bank_city` varchar(255) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `bank_prv` varchar(255) DEFAULT NULL,
  `bank_sub` varchar(255) DEFAULT NULL,
  `card_no` varchar(255) DEFAULT NULL,
  `chk_value` varchar(2000) DEFAULT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_user` varchar(255) DEFAULT NULL,
  `created_ip` varchar(255) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `css_id` int(11) DEFAULT NULL,
  `css_type` int(11) DEFAULT NULL,
  `current_balance` bigint(20) DEFAULT NULL,
  `current_describe` varchar(255) DEFAULT NULL,
  `describer` varchar(255) DEFAULT NULL,
  `fee_income` int(11) DEFAULT NULL,
  `from_system` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `mer_date` varchar(255) DEFAULT NULL,
  `mer_seq_id` varchar(255) DEFAULT NULL,
  `money` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `ora_name` varchar(255) DEFAULT NULL,
  `ora_status` varchar(255) DEFAULT NULL,
  `person_name` varchar(255) DEFAULT NULL,
  `person_no` varchar(255) DEFAULT NULL,
  `sign_date` varchar(2000) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `system_role` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `third_pay_type` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `versionid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of cash_receive_station
-- ----------------------------

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `idnumber` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `address_id` int(11) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `balance` tinyblob,
  `bank_status` int(11) DEFAULT NULL,
  `birth_day` varchar(255) DEFAULT NULL,
  `client_type` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `data` varchar(255) DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  `doctor_id` bigint(20) DEFAULT NULL,
  `huanxin_id` varchar(255) DEFAULT NULL,
  `huanxin_password` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `image_code` varchar(255) DEFAULT NULL,
  `is_lock` int(11) DEFAULT NULL,
  `is_merchant` bigint(20) DEFAULT NULL,
  `is_recommender` bigint(20) DEFAULT NULL,
  `last_failed_time` int(11) DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `merchant_id` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `name_authentication` int(11) DEFAULT NULL,
  `new_password` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `pay_password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('1', '', '', '2017-04-05 10:20:40', '2017-04-06 11:21:32', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, 'upload/jianqiao/20170405141514160464622.jpg', null, null, '0', '0', null, '2017-04-06 11:20:48', null, null, null, null, '汪医生', '96e79218965eb72c92a549dd5a330112', null, '13716743890', null, '0', null);
INSERT INTO `customer` VALUES ('2', '', '', '2017-04-05 10:22:03', '2017-04-13 17:29:23', '130281199004282412', null, null, null, null, null, null, null, null, null, null, '0', '2', null, null, 'upload/jianqiao/20170413172920923106602.jpg', null, null, '0', '0', null, '2017-04-13 17:29:07', null, '曹雨征', '1', null, '爱', '96e79218965eb72c92a549dd5a330112', null, '17600298778', null, '1', null);
INSERT INTO `customer` VALUES ('3', '', '', '2017-04-05 10:32:04', '2017-04-05 14:00:24', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, 'upload/jianqiao/20170405110221717870764.jpg', null, null, '0', '0', null, '2017-04-05 14:00:24', null, null, null, null, '孙医生', 'fa1b581e82c66ba41f9e8a98217fc947', null, '14777777777', null, '0', null);
INSERT INTO `customer` VALUES ('5', '', '', '2017-04-05 10:34:14', '2017-04-05 14:15:20', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, 'upload/jianqiao/20170405141514160464622.jpg', null, null, '0', '0', null, '2017-04-05 14:12:02', null, null, null, null, '张医生', '71b596cb42ee254f7416043d184fc970', null, '18111111111', null, '0', null);
INSERT INTO `customer` VALUES ('6', '', '', '2017-04-05 10:35:12', '2017-04-05 10:35:12', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, '0', '0', null, null, null, null, null, null, null, '71b596cb42ee254f7416043d184fc970', null, '15711111111', null, '0', null);
INSERT INTO `customer` VALUES ('7', '', '', '2017-04-05 10:49:30', '2017-04-06 13:55:55', '230183199209285925', null, null, null, null, null, null, null, null, null, null, '0', null, null, null, 'upload/jianqiao/20170405171859633668958.jpg', null, null, '0', '0', null, '2017-04-06 13:55:55', null, '宋国霞', '1', null, '宋宋', '96e79218965eb72c92a549dd5a330112', null, '18404030120', null, '1', null);
INSERT INTO `customer` VALUES ('9', '', '', '2017-04-05 11:28:15', '2017-04-05 13:59:09', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, '0', '0', null, '2017-04-05 13:59:09', null, null, null, null, null, 'e10adc3949ba59abbe56e057f20f883e', null, '13552655237', null, '0', null);
INSERT INTO `customer` VALUES ('10', '', '', '2017-04-05 11:40:57', '2017-04-05 14:37:16', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, '0', '0', null, '2017-04-05 14:37:16', null, null, null, null, null, 'e10adc3949ba59abbe56e057f20f883e', null, '18101357001', null, '0', null);
INSERT INTO `customer` VALUES ('11', '', '', '2017-04-05 13:39:35', '2017-04-05 13:39:35', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, '0', '0', null, null, null, null, null, null, null, '96e79218965eb72c92a549dd5a330112', null, '13722222222', null, '0', null);
INSERT INTO `customer` VALUES ('12', '', '', '2017-04-05 13:43:01', '2017-04-05 16:53:54', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, '0', '0', null, '2017-04-05 16:53:54', null, null, null, null, null, 'e10adc3949ba59abbe56e057f20f883e', null, '15831306633', null, '1', null);
INSERT INTO `customer` VALUES ('13', '', '', '2017-04-05 14:02:52', '2017-04-05 14:02:52', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, '0', '0', null, null, null, null, null, null, null, 'e10adc3949ba59abbe56e057f20f883e', null, '18633494252', null, '0', null);
INSERT INTO `customer` VALUES ('14', '', '', '2017-04-05 14:19:56', '2017-04-05 14:20:39', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, '0', '0', null, '2017-04-05 14:20:39', null, null, null, null, null, '25d55ad283aa400af464c76d713c07ad', null, '18400000003', null, '0', null);
INSERT INTO `customer` VALUES ('15', '', '', '2017-04-05 14:20:25', '2017-04-06 11:25:43', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, '0', '0', null, '2017-04-06 11:25:43', null, null, null, null, null, 'e10adc3949ba59abbe56e057f20f883e', null, '18400000002', null, '0', null);
INSERT INTO `customer` VALUES ('16', '', '', '2017-04-05 14:31:38', '2017-04-05 14:58:51', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, '0', '0', null, '2017-04-05 14:58:51', null, null, null, null, null, 'e10adc3949ba59abbe56e057f20f883e', null, '18515644716', null, '0', null);
INSERT INTO `customer` VALUES ('17', '', '', '2017-04-05 14:35:54', '2017-04-05 14:35:54', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, '0', '0', null, null, null, null, null, null, null, '71b596cb42ee254f7416043d184fc970', null, '18101357001', null, '0', null);
INSERT INTO `customer` VALUES ('18', '', '', '2017-04-05 14:42:53', '2017-04-05 17:51:57', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, '0', '0', null, '2017-04-05 17:51:57', null, null, null, null, null, '29ad0e3fd3db681fb9f8091c756313f7', null, '18801304725', null, '0', null);
INSERT INTO `customer` VALUES ('19', '', '', '2017-04-05 14:51:29', '2017-04-11 17:47:14', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, 'upload/jianqiao/20170405110221717870764.jpg', null, null, '0', '0', null, '2017-04-11 17:47:14', null, null, null, null, '哞医生', '96e79218965eb72c92a549dd5a330112', null, '13714444444', null, '1', null);
INSERT INTO `customer` VALUES ('20', '', '', '2017-04-05 14:52:09', '2017-04-05 14:52:09', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, '0', '0', null, null, null, null, null, null, null, '96e79218965eb72c92a549dd5a330112', null, '13713333333', null, '0', null);
INSERT INTO `customer` VALUES ('21', '', '', '2017-04-05 16:43:24', '2017-04-05 17:26:58', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, 'upload/jianqiao/20170406114951134602875.png', null, null, '0', '0', null, '2017-04-05 17:26:58', null, null, null, null, null, 'b3a2083f5814d4c81406c558552169e8', null, '13715555555', null, '1', null);
INSERT INTO `customer` VALUES ('22', '', '', '2017-04-05 17:14:51', '2017-04-06 10:04:29', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, 'upload/jianqiao/20170405171859633668958.jpg', null, null, '0', '0', null, '2017-04-06 10:04:29', null, null, null, null, '123', 'ef209c9343ca8c715265781876657b18', null, '13717777777', null, '1', null);
INSERT INTO `customer` VALUES ('23', null, null, null, '2017-04-06 11:39:37', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, null, null, null, '2017-04-06 11:39:37', null, null, null, null, null, 'e10adc3949ba59abbe56e057f20f883e', null, '17710268732', null, '0', null);
INSERT INTO `customer` VALUES ('24', '', '', '2017-04-06 11:06:01', '2017-04-06 11:24:48', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, null, null, null, '0', '0', null, '2017-04-06 11:24:48', null, null, null, null, null, '29ad0e3fd3db681fb9f8091c756313f7', null, '18400000006', null, '0', null);
INSERT INTO `customer` VALUES ('25', '', '', '2017-04-06 11:49:17', '2017-04-06 11:56:56', null, null, null, null, null, null, null, null, null, null, null, '0', null, null, null, 'upload/jianqiao/20170406114951134602875.png', null, null, '0', '0', null, '2017-04-06 11:56:56', null, null, null, null, '腾卉', '12a88bdd28a5f929466dabba85944538', null, '18310286118', null, '0', null);

-- ----------------------------
-- Table structure for `customer_address`
-- ----------------------------
DROP TABLE IF EXISTS `customer_address`;
CREATE TABLE `customer_address` (
  `customer_id` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `addressee` varchar(255) DEFAULT NULL,
  `is_default` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of customer_address
-- ----------------------------

-- ----------------------------
-- Table structure for `customer_illness_record`
-- ----------------------------
DROP TABLE IF EXISTS `customer_illness_record`;
CREATE TABLE `customer_illness_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `customer_relative` tinyblob,
  `diagnose` varchar(255) DEFAULT NULL,
  `entering_time` datetime DEFAULT NULL,
  `image1` varchar(255) DEFAULT NULL,
  `image10` varchar(255) DEFAULT NULL,
  `image11` varchar(255) DEFAULT NULL,
  `image12` varchar(255) DEFAULT NULL,
  `image13` varchar(255) DEFAULT NULL,
  `image14` varchar(255) DEFAULT NULL,
  `image15` varchar(255) DEFAULT NULL,
  `image16` varchar(255) DEFAULT NULL,
  `image17` varchar(255) DEFAULT NULL,
  `image18` varchar(255) DEFAULT NULL,
  `image19` varchar(255) DEFAULT NULL,
  `image2` varchar(255) DEFAULT NULL,
  `image20` varchar(255) DEFAULT NULL,
  `image3` varchar(255) DEFAULT NULL,
  `image4` varchar(255) DEFAULT NULL,
  `image5` varchar(255) DEFAULT NULL,
  `image6` varchar(255) DEFAULT NULL,
  `image7` varchar(255) DEFAULT NULL,
  `image8` varchar(255) DEFAULT NULL,
  `image9` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `proce` varchar(255) DEFAULT NULL,
  `relative_id` bigint(20) DEFAULT NULL,
  `relative_name` varchar(255) DEFAULT NULL,
  `symptom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of customer_illness_record
-- ----------------------------

-- ----------------------------
-- Table structure for `customer_relative`
-- ----------------------------
DROP TABLE IF EXISTS `customer_relative`;
CREATE TABLE `customer_relative` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `birth_date` datetime DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `customer` tinyblob,
  `customer_id` int(11) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `prov` varchar(255) DEFAULT NULL,
  `relation_id` int(11) DEFAULT NULL,
  `relative_id` bigint(20) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of customer_relative
-- ----------------------------

-- ----------------------------
-- Table structure for `detail_desc`
-- ----------------------------
DROP TABLE IF EXISTS `detail_desc`;
CREATE TABLE `detail_desc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `desc_content` varchar(255) DEFAULT NULL,
  `desc_for` int(11) DEFAULT NULL,
  `desc_id` int(11) DEFAULT NULL,
  `desc_index` int(11) DEFAULT NULL,
  `desc_type` int(11) DEFAULT NULL,
  `main_id` int(11) DEFAULT NULL,
  `order_no` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of detail_desc
-- ----------------------------

-- ----------------------------
-- Table structure for `diary`
-- ----------------------------
DROP TABLE IF EXISTS `diary`;
CREATE TABLE `diary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `brow_num` int(11) DEFAULT NULL,
  `commenter_num` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `doctor_id` bigint(20) DEFAULT NULL,
  `doctor_name` varchar(255) DEFAULT NULL,
  `merchant_name` varchar(255) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_order_id` bigint(20) DEFAULT NULL,
  `product_type_id` bigint(20) DEFAULT NULL,
  `product_type_name` varchar(255) DEFAULT NULL,
  `diary_book_id` bigint(20) DEFAULT NULL,
  `diary_book_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_43y4xsg6bt171718n2yyusuyb` (`diary_book_id`),
  CONSTRAINT `FK_43y4xsg6bt171718n2yyusuyb` FOREIGN KEY (`diary_book_id`) REFERENCES `diary_book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of diary
-- ----------------------------
INSERT INTO `diary` VALUES ('1', '', '', '2017-04-06 11:27:47', '2017-04-06 11:27:47', null, null, '还好啊', '15', '3', '宋宋', '北京博爱医院', null, '15', '9', '牙齿松动', '2', null);
INSERT INTO `diary` VALUES ('2', '', '', '2017-04-06 11:28:10', '2017-04-06 11:28:10', null, null, '还好啊', '15', '3', '宋宋', '北京博爱医院', null, '15', '9', '牙齿松动', '2', null);
INSERT INTO `diary` VALUES ('3', '', '', '2017-04-06 11:29:34', '2017-04-06 11:29:34', null, null, '测试', '23', '6', '哞哞医生', '北京医院', null, '22', '11', '鼻炎', '3', null);
INSERT INTO `diary` VALUES ('4', '', '', '2017-04-06 11:32:17', '2017-04-06 11:32:17', null, null, '测试', '23', '6', '哞哞医生', '北京医院', null, '22', '11', '鼻炎', '3', null);
INSERT INTO `diary` VALUES ('5', '', '', '2017-04-06 11:37:16', '2017-04-06 11:37:16', null, null, '测试', '23', '6', '哞哞医生', '北京医院', null, '22', '11', '鼻炎', '3', null);
INSERT INTO `diary` VALUES ('6', '', '', '2017-04-06 11:37:45', '2017-04-06 11:37:45', null, null, '123', '22', '4', '哞医生', '北京医院', null, '16', '11', '鼻炎', '1', null);
INSERT INTO `diary` VALUES ('7', '', '', '2017-04-06 11:39:58', '2017-04-06 11:39:58', null, null, '法国红酒', '23', '6', '哞哞医生', '北京医院', null, '22', '11', '鼻炎', '3', null);
INSERT INTO `diary` VALUES ('8', '', '', '2017-04-06 11:40:09', '2017-04-06 11:40:09', null, null, '的风格和', '23', '6', '哞哞医生', '北京医院', null, '22', '11', '鼻炎', '3', null);

-- ----------------------------
-- Table structure for `diary_book`
-- ----------------------------
DROP TABLE IF EXISTS `diary_book`;
CREATE TABLE `diary_book` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `buy_date` datetime DEFAULT NULL,
  `commenter_num` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `dairy_num` int(11) DEFAULT NULL,
  `diary_book_name` varchar(255) DEFAULT NULL,
  `doctor_id` bigint(20) DEFAULT NULL,
  `fabulousval` varchar(255) DEFAULT NULL,
  `merchant_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `product_order_id` bigint(20) DEFAULT NULL,
  `satisfaction` int(11) DEFAULT NULL,
  `steponval` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of diary_book
-- ----------------------------
INSERT INTO `diary_book` VALUES ('1', '', '', '2017-04-06 11:25:20', '2017-04-06 11:25:20', null, null, null, '22', null, '1212', '4', '8,', '1', '9', '16', '3', '2,');
INSERT INTO `diary_book` VALUES ('2', '', '', '2017-04-06 11:26:30', '2017-04-06 11:26:30', null, null, null, '15', null, '口腔修复', '3', '1,', '2', '8', '15', '3', '1,');
INSERT INTO `diary_book` VALUES ('3', '', '', '2017-04-06 11:29:08', '2017-04-06 11:29:08', null, null, null, '23', null, '测试', '6', '1,2,3,4,8,', '1', '11', '22', '4', '');

-- ----------------------------
-- Table structure for `diary_book_praise_tread`
-- ----------------------------
DROP TABLE IF EXISTS `diary_book_praise_tread`;
CREATE TABLE `diary_book_praise_tread` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `dairy_book_id` bigint(20) DEFAULT NULL,
  `satisfaction_praise_tread_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fxggjv31xmaeolkdsyfa9eg8x` (`customer_id`),
  KEY `FK_3fko7fmoiwa9fba2efdhyf56g` (`dairy_book_id`),
  KEY `FK_lug66or1lb96qr49u1t9pooo5` (`satisfaction_praise_tread_id`),
  CONSTRAINT `FK_3fko7fmoiwa9fba2efdhyf56g` FOREIGN KEY (`dairy_book_id`) REFERENCES `diary_book` (`id`),
  CONSTRAINT `FK_fxggjv31xmaeolkdsyfa9eg8x` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_lug66or1lb96qr49u1t9pooo5` FOREIGN KEY (`satisfaction_praise_tread_id`) REFERENCES `satisfaction_praise_tread` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of diary_book_praise_tread
-- ----------------------------

-- ----------------------------
-- Table structure for `diary_comment`
-- ----------------------------
DROP TABLE IF EXISTS `diary_comment`;
CREATE TABLE `diary_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `dairy_id` bigint(20) DEFAULT NULL,
  `anonymous` int(11) DEFAULT NULL,
  `customer_hand_img` varchar(255) DEFAULT NULL,
  `diary_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5q47s1n4dy1a6rqt2dfu080je` (`customer_id`),
  KEY `FK_vrumsdnc26rjdw7ca9n8vsys` (`dairy_id`),
  CONSTRAINT `FK_5q47s1n4dy1a6rqt2dfu080je` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_vrumsdnc26rjdw7ca9n8vsys` FOREIGN KEY (`dairy_id`) REFERENCES `diary` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of diary_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `diary_favour`
-- ----------------------------
DROP TABLE IF EXISTS `diary_favour`;
CREATE TABLE `diary_favour` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `diary_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of diary_favour
-- ----------------------------

-- ----------------------------
-- Table structure for `dic_hospital_type`
-- ----------------------------
DROP TABLE IF EXISTS `dic_hospital_type`;
CREATE TABLE `dic_hospital_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of dic_hospital_type
-- ----------------------------
INSERT INTO `dic_hospital_type` VALUES ('1', '', '', '2017-04-05 10:38:06', '2017-04-05 10:38:06', '一甲医院');
INSERT INTO `dic_hospital_type` VALUES ('2', '', '', '2017-04-05 10:38:54', '2017-04-05 10:38:54', '二乙医院');
INSERT INTO `dic_hospital_type` VALUES ('3', '', '', null, '2017-04-05 11:39:15', '三丙医院');

-- ----------------------------
-- Table structure for `dic_office`
-- ----------------------------
DROP TABLE IF EXISTS `dic_office`;
CREATE TABLE `dic_office` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of dic_office
-- ----------------------------
INSERT INTO `dic_office` VALUES ('1', '', '', '2017-04-05 10:44:40', '2017-04-05 10:44:40', null, '内科');
INSERT INTO `dic_office` VALUES ('2', '', '', '2017-04-05 10:44:48', '2017-04-05 10:44:48', null, '外科');
INSERT INTO `dic_office` VALUES ('3', '', '', '2017-04-05 10:44:55', '2017-04-05 10:44:55', null, '骨科');
INSERT INTO `dic_office` VALUES ('4', '', '', '2017-04-05 10:45:01', '2017-04-05 10:45:01', null, '男科');
INSERT INTO `dic_office` VALUES ('5', '', '', '2017-04-05 10:45:06', '2017-04-05 10:45:06', null, '妇科');
INSERT INTO `dic_office` VALUES ('6', '', '', '2017-04-05 10:45:12', '2017-04-05 10:45:12', null, '儿科');
INSERT INTO `dic_office` VALUES ('7', '', '', '2017-04-05 10:45:47', '2017-04-05 10:45:47', null, '皮肤病科');
INSERT INTO `dic_office` VALUES ('8', '', '', '2017-04-05 10:45:55', '2017-04-05 10:45:55', null, '精神科');
INSERT INTO `dic_office` VALUES ('9', '', '', '2017-04-05 10:46:05', '2017-04-05 10:46:05', null, '眼科');
INSERT INTO `dic_office` VALUES ('10', '', '', '2017-04-05 10:46:15', '2017-04-05 10:46:15', null, '口腔科');
INSERT INTO `dic_office` VALUES ('11', '', '', '2017-04-05 10:46:28', '2017-04-05 10:46:28', null, '中医科');
INSERT INTO `dic_office` VALUES ('12', '', '', '2017-04-05 10:46:40', '2017-04-05 10:46:40', null, '耳鼻喉科');

-- ----------------------------
-- Table structure for `dic_relation`
-- ----------------------------
DROP TABLE IF EXISTS `dic_relation`;
CREATE TABLE `dic_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `relation_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of dic_relation
-- ----------------------------
INSERT INTO `dic_relation` VALUES ('1', '', '', '2017-04-05 10:31:13', '2017-04-05 10:31:13', '父母', null);
INSERT INTO `dic_relation` VALUES ('2', '', '', '2017-04-05 10:31:21', '2017-04-05 10:31:21', '子女', null);
INSERT INTO `dic_relation` VALUES ('3', '', '', '2017-04-05 10:31:26', '2017-04-05 10:31:26', '朋友', null);
INSERT INTO `dic_relation` VALUES ('4', '', '', '2017-04-05 10:31:35', '2017-04-05 10:31:35', '亲戚', null);
INSERT INTO `dic_relation` VALUES ('5', '', '', '2017-04-05 10:31:49', '2017-04-05 10:31:49', '丈夫', null);
INSERT INTO `dic_relation` VALUES ('6', '', '', '2017-04-05 10:31:54', '2017-04-05 10:31:54', '妻子', null);
INSERT INTO `dic_relation` VALUES ('7', '', '', '2017-04-05 10:32:08', '2017-04-05 10:32:08', '兄弟', null);

-- ----------------------------
-- Table structure for `dic_service_type`
-- ----------------------------
DROP TABLE IF EXISTS `dic_service_type`;
CREATE TABLE `dic_service_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `department_name` varchar(255) DEFAULT NULL,
  `service_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of dic_service_type
-- ----------------------------
INSERT INTO `dic_service_type` VALUES ('1', '', '', '2017-04-05 10:27:03', '2017-04-05 10:27:03', '内科', '心脑血管疾病');
INSERT INTO `dic_service_type` VALUES ('2', '', '', '2017-04-05 10:27:22', '2017-04-05 10:27:22', '外科', '软组织挫伤');
INSERT INTO `dic_service_type` VALUES ('3', '', '', '2017-04-05 10:27:34', '2017-04-05 10:27:34', '骨科', '骨裂');
INSERT INTO `dic_service_type` VALUES ('4', '', '', '2017-04-05 10:27:50', '2017-04-05 10:27:50', '精神科', '精神分裂');
INSERT INTO `dic_service_type` VALUES ('5', '', '', '2017-04-05 10:28:03', '2017-04-05 10:28:03', '男科', '普通男科');
INSERT INTO `dic_service_type` VALUES ('6', '', '', '2017-04-05 10:28:12', '2017-04-05 10:28:12', '妇科', '普通妇科');
INSERT INTO `dic_service_type` VALUES ('7', '', '', '2017-04-05 10:29:32', '2017-04-05 10:29:32', '儿科', '儿童感冒');
INSERT INTO `dic_service_type` VALUES ('8', '', '', '2017-04-05 10:29:57', '2017-04-05 10:29:57', '皮肤性病科', '皮肤干裂');
INSERT INTO `dic_service_type` VALUES ('9', '', '', '2017-04-05 10:30:16', '2017-04-05 10:30:16', '口腔科', '牙齿松动');
INSERT INTO `dic_service_type` VALUES ('10', '', '', '2017-04-05 10:30:28', '2017-04-05 10:30:28', '眼科', '青光眼');
INSERT INTO `dic_service_type` VALUES ('11', '', '', '2017-04-05 10:30:40', '2017-04-05 10:30:40', '耳鼻喉科', '鼻炎');
INSERT INTO `dic_service_type` VALUES ('12', '', '', '2017-04-05 10:30:52', '2017-04-05 10:30:52', '中医科', '针灸');

-- ----------------------------
-- Table structure for `dic_spec`
-- ----------------------------
DROP TABLE IF EXISTS `dic_spec`;
CREATE TABLE `dic_spec` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_spec_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of dic_spec
-- ----------------------------
INSERT INTO `dic_spec` VALUES ('1', '', '', '2017-04-05 10:39:45', '2017-04-05 10:39:45', '内科', null);
INSERT INTO `dic_spec` VALUES ('2', '', '', '2017-04-05 10:39:51', '2017-04-05 10:39:51', '外科', null);
INSERT INTO `dic_spec` VALUES ('3', '', '', '2017-04-05 10:40:09', '2017-04-05 10:40:09', '骨科', null);
INSERT INTO `dic_spec` VALUES ('4', '', '', '2017-04-05 10:40:21', '2017-04-05 10:40:21', '精神科', null);
INSERT INTO `dic_spec` VALUES ('5', '', '', '2017-04-05 10:40:29', '2017-04-05 10:40:29', '男科', null);
INSERT INTO `dic_spec` VALUES ('6', '', '', '2017-04-05 10:40:36', '2017-04-05 10:40:36', '妇科', null);
INSERT INTO `dic_spec` VALUES ('7', '', '', '2017-04-05 10:40:45', '2017-04-05 10:40:45', '儿科', null);
INSERT INTO `dic_spec` VALUES ('8', '', '', '2017-04-05 10:40:57', '2017-04-05 10:40:57', '皮肤性病科', null);
INSERT INTO `dic_spec` VALUES ('9', '', '', '2017-04-05 10:41:06', '2017-04-05 10:41:06', '口腔科', null);
INSERT INTO `dic_spec` VALUES ('10', '', '', '2017-04-05 10:41:13', '2017-04-05 10:41:13', '眼科', null);
INSERT INTO `dic_spec` VALUES ('11', '', '', '2017-04-05 10:43:58', '2017-04-05 10:43:58', '耳鼻喉科', null);
INSERT INTO `dic_spec` VALUES ('12', '', '', '2017-04-05 10:44:07', '2017-04-05 10:44:07', '中医科', null);

-- ----------------------------
-- Table structure for `dic_title`
-- ----------------------------
DROP TABLE IF EXISTS `dic_title`;
CREATE TABLE `dic_title` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of dic_title
-- ----------------------------
INSERT INTO `dic_title` VALUES ('1', '', '', '2017-04-05 10:34:14', '2017-04-05 10:34:14', '30年治疗经验', '知名专家');
INSERT INTO `dic_title` VALUES ('2', '', '', '2017-04-05 10:34:39', '2017-04-05 10:34:39', '20年治疗经验', '知名教授');
INSERT INTO `dic_title` VALUES ('3', '', '', '2017-04-05 10:35:06', '2017-04-05 10:35:06', '15年治病经验', '知名副教授');
INSERT INTO `dic_title` VALUES ('4', '', '', '2017-04-05 10:36:44', '2017-04-05 10:36:44', '10年治病经验', '专科医生');
INSERT INTO `dic_title` VALUES ('5', '', '', '2017-04-05 10:37:21', '2017-04-05 10:37:21', '5年治病经验', '专科大夫');

-- ----------------------------
-- Table structure for `doctor`
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `apply_status` int(11) DEFAULT NULL,
  `city_id` bigint(20) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `customer_phone` varchar(255) DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  `hospital` varchar(255) DEFAULT NULL,
  `hospitalype_id` bigint(20) DEFAULT NULL,
  `hospitalype_name` varchar(255) DEFAULT NULL,
  `image_work1` varchar(255) DEFAULT NULL,
  `image_work2` varchar(255) DEFAULT NULL,
  `image_work3` varchar(255) DEFAULT NULL,
  `image_header` varchar(255) DEFAULT NULL,
  `latitude` varchar(255) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `merchant_id` bigint(20) DEFAULT NULL,
  `merchant_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `office_id` bigint(20) DEFAULT NULL,
  `office_name` varchar(255) DEFAULT NULL,
  `online_time` varchar(255) DEFAULT NULL,
  `product_type_id` int(11) DEFAULT NULL,
  `reject_reason` varchar(255) DEFAULT NULL,
  `spec_id` bigint(20) DEFAULT NULL,
  `spec_name` varchar(255) DEFAULT NULL,
  `title_id` bigint(20) DEFAULT NULL,
  `title_name` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `veiw_count` int(11) DEFAULT NULL,
  `verify` int(11) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of doctor
-- ----------------------------
INSERT INTO `doctor` VALUES ('1', '', '', '2017-04-05 16:29:03', '2017-04-05 16:31:44', null, '1', null, '12', '17710268732', '0', '一个真的老中医', null, null, null, 'upload/jianqiao/20170405162825719182276.png', 'upload/jianqiao/20170405162823740895077.png', 'upload/jianqiao/20170405162821759954287.png', 'upload/jianqiao/20170405162819525270931.png', null, null, '1', '北京医院', '王师傅', '1', '内科', null, '7', null, null, '专治不孕不育', '1', '知名专家', null, null, null, null);
INSERT INTO `doctor` VALUES ('2', '', '', '2017-04-05 16:41:10', '2017-04-05 16:55:27', null, '1', null, '2', '17600298778', '0', '由30年口腔治疗经验', null, null, null, 'upload/jianqiao/20170405163953662181318.jpg', 'upload/jianqiao/20170405163948925221901.jpg', 'upload/jianqiao/20170405163944511884367.jpg', 'upload/jianqiao/20170405163940327991553.jpg', null, null, '4', '北京中西医结合医院', '曹雨征', '10', '口腔科', null, '10', null, null, '治疗口腔疾病', '1', '知名专家', null, null, null, null);
INSERT INTO `doctor` VALUES ('3', '', '', '2017-04-05 16:41:58', '2017-04-05 16:46:06', null, '1', null, '7', '18404030120', '0', '口腔修复学  博士后', null, null, null, 'upload/jianqiao/20170405164051673417062.jpg', 'upload/jianqiao/20170405164044815968383.jpg', 'upload/jianqiao/20170405164035049703859.jpg_600.jpg', 'upload/jianqiao/20170405164028138124708.jpg', null, null, '2', '北京博爱医院', '宋宋', '10', '口腔科', null, '8', null, null, '口腔修复', '2', '知名教授', null, null, null, null);
INSERT INTO `doctor` VALUES ('4', '', '', '2017-04-05 16:47:56', '2017-04-06 10:34:04', null, '1', null, '21', '13715555555', '0', '江湖名医！', null, null, null, 'upload/jianqiao/20170405164727952680509.jpg', 'upload/jianqiao/20170405164724641296792.jpg', 'upload/jianqiao/20170405164719568790487.jpg', 'upload/jianqiao/20170405164028138124708.jpg', null, null, '1', '北京医院', '哞医生', '12', '耳鼻喉科', null, '9', null, null, '看病', '1', '知名专家', null, null, null, null);
INSERT INTO `doctor` VALUES ('5', '', '', '2017-04-05 17:16:02', '2017-04-05 17:16:18', null, '1', null, '22', '13716666666', '0', '12', null, null, null, 'upload/jianqiao/20170405171556869767103.jpg', 'upload/jianqiao/20170405171552226278704.jpg', 'upload/jianqiao/20170405171548652453768.jpg', 'upload/jianqiao/20170405171543683286977.jpg', null, null, '1', '北京医院', '测医生', '1', '内科', null, null, null, null, '12', '1', '知名专家', null, null, null, null);
INSERT INTO `doctor` VALUES ('6', '', '', '2017-04-05 17:29:40', '2017-04-05 17:30:55', null, '1', null, '19', '13714444444', '0', '123人', null, null, null, 'upload/jianqiao/20170405172934082813730.jpg', 'upload/jianqiao/20170405172930309520355.jpg', 'upload/jianqiao/20170405172927873416521.jpg', 'upload/jianqiao/20170405172925014985570.jpg', null, null, '1', '北京医院', '哞哞医生', '1', '内科', null, '11', null, null, '123', '1', '知名专家', null, null, null, null);

-- ----------------------------
-- Table structure for `doctor_call_info`
-- ----------------------------
DROP TABLE IF EXISTS `doctor_call_info`;
CREATE TABLE `doctor_call_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `buy_time` int(11) DEFAULT NULL,
  `during_time` int(11) DEFAULT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `remain_time` int(11) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of doctor_call_info
-- ----------------------------

-- ----------------------------
-- Table structure for `doctor_comment`
-- ----------------------------
DROP TABLE IF EXISTS `doctor_comment`;
CREATE TABLE `doctor_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `comment_date` datetime DEFAULT NULL,
  `comment_id` int(11) DEFAULT NULL,
  `customer_age` int(11) DEFAULT NULL,
  `customer_comment` varchar(255) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `customer_phone` varchar(255) DEFAULT NULL,
  `customer_sex` int(11) DEFAULT NULL,
  `doctor_id` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `service_star` int(11) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of doctor_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `doctor_home_service_info`
-- ----------------------------
DROP TABLE IF EXISTS `doctor_home_service_info`;
CREATE TABLE `doctor_home_service_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contact_name` varchar(255) DEFAULT NULL,
  `doctor_service_order` tinyblob,
  `home_doctor_type` int(11) DEFAULT NULL,
  `info_id` int(11) DEFAULT NULL,
  `member_count` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `pay_relative_id` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of doctor_home_service_info
-- ----------------------------

-- ----------------------------
-- Table structure for `doctor_image`
-- ----------------------------
DROP TABLE IF EXISTS `doctor_image`;
CREATE TABLE `doctor_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `attachment_id` bigint(20) DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  `doctor_id` bigint(20) DEFAULT NULL,
  `doctor_name` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of doctor_image
-- ----------------------------

-- ----------------------------
-- Table structure for `doctor_meeting_service_info`
-- ----------------------------
DROP TABLE IF EXISTS `doctor_meeting_service_info`;
CREATE TABLE `doctor_meeting_service_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contact_name` varchar(255) DEFAULT NULL,
  `doctor_service_order` tinyblob,
  `during` int(11) DEFAULT NULL,
  `info_id` int(11) DEFAULT NULL,
  `order_id` int(11) DEFAULT NULL,
  `pay_relative_id` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of doctor_meeting_service_info
-- ----------------------------

-- ----------------------------
-- Table structure for `doctor_recomm`
-- ----------------------------
DROP TABLE IF EXISTS `doctor_recomm`;
CREATE TABLE `doctor_recomm` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `home_doctor_1` int(11) DEFAULT NULL,
  `home_doctor_2` int(11) DEFAULT NULL,
  `home_doctor_3` int(11) DEFAULT NULL,
  `home_doctor_4` int(11) DEFAULT NULL,
  `home_doctor_5` int(11) DEFAULT NULL,
  `reco_id` int(11) DEFAULT NULL,
  `spec_doctor_1` int(11) DEFAULT NULL,
  `spec_doctor_2` int(11) DEFAULT NULL,
  `spec_doctor_3` int(11) DEFAULT NULL,
  `spec_doctor_4` int(11) DEFAULT NULL,
  `spec_doctor_5` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of doctor_recomm
-- ----------------------------

-- ----------------------------
-- Table structure for `doctor_service`
-- ----------------------------
DROP TABLE IF EXISTS `doctor_service`;
CREATE TABLE `doctor_service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `customer_home_time_onoff` int(11) DEFAULT NULL,
  `customer_home_year_onoff` int(11) DEFAULT NULL,
  `customer_meet_onoff` int(11) DEFAULT NULL,
  `customer_online_time_onoff` int(11) DEFAULT NULL,
  `customer_online_year_onoff` int(11) DEFAULT NULL,
  `doctor` tinyblob,
  `doctor_id` int(11) DEFAULT NULL,
  `doctor_service_id` int(11) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `service_home_time_desc` varchar(255) DEFAULT NULL,
  `service_home_time_onoff` int(11) DEFAULT NULL,
  `service_home_time_price` int(11) DEFAULT NULL,
  `service_home_year_desc` varchar(255) DEFAULT NULL,
  `service_home_year_onoff` int(11) DEFAULT NULL,
  `service_home_year_price` int(11) DEFAULT NULL,
  `service_meet_desc` varchar(255) DEFAULT NULL,
  `service_meet_onoff` int(11) DEFAULT NULL,
  `service_meet_price` int(11) DEFAULT NULL,
  `service_online_time_desc` varchar(255) DEFAULT NULL,
  `service_online_time_onoff` int(11) DEFAULT NULL,
  `service_online_time_price` int(11) DEFAULT NULL,
  `service_online_year_desc` varchar(255) DEFAULT NULL,
  `service_online_year_onoff` int(11) DEFAULT NULL,
  `service_online_year_price` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of doctor_service
-- ----------------------------
INSERT INTO `doctor_service` VALUES ('1', '', '', '2017-04-05 13:55:15', '2017-04-05 13:55:15', null, null, null, null, null, null, '3', null, null, null, null, null, null, null, null, null, null, null, null, '0', '10', null, null, null);
INSERT INTO `doctor_service` VALUES ('2', '', '', '2017-04-05 16:49:35', '2017-04-05 16:49:35', null, null, null, null, null, null, '1', null, null, null, null, null, null, null, null, null, null, null, null, '0', '30000', null, null, null);
INSERT INTO `doctor_service` VALUES ('3', '', '', '2017-04-05 17:03:27', '2017-04-05 17:03:27', null, null, null, null, null, null, '4', null, null, null, null, null, null, null, null, null, null, null, null, '0', '1', null, null, null);
INSERT INTO `doctor_service` VALUES ('4', '', '', '2017-04-05 17:40:30', '2017-04-05 17:58:34', null, null, null, null, null, null, '6', null, null, null, null, null, null, null, null, null, null, null, null, '0', '1', null, null, null);

-- ----------------------------
-- Table structure for `doctor_service_order`
-- ----------------------------
DROP TABLE IF EXISTS `doctor_service_order`;
CREATE TABLE `doctor_service_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `approved` int(11) DEFAULT NULL,
  `buy_time` int(11) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `customer_phone` varchar(255) DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  `dic_service_type` tinyblob,
  `doctor_id` int(11) DEFAULT NULL,
  `doctor_phone` varchar(255) DEFAULT NULL,
  `executed` int(11) DEFAULT NULL,
  `order_date` datetime DEFAULT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `pay_status` int(11) DEFAULT NULL,
  `pay_relative_id` varchar(255) DEFAULT NULL,
  `pay_time` datetime DEFAULT NULL,
  `pay_timeout` datetime DEFAULT NULL,
  `pay_type` int(11) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `servcie_time_year` varchar(255) DEFAULT NULL,
  `service_type` int(11) DEFAULT NULL,
  `service_type_id` int(11) DEFAULT NULL,
  `str_customer_name` varchar(255) DEFAULT NULL,
  `str_doctor_name` varchar(255) DEFAULT NULL,
  `str_service_name` varchar(255) DEFAULT NULL,
  `str_service_type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of doctor_service_order
-- ----------------------------
INSERT INTO `doctor_service_order` VALUES ('1', '', '', '2017-04-05 16:32:10', '2017-04-05 16:32:10', null, null, '12', '17710268732', '0', null, '1', '17710268732', '0', '2017-04-05 16:32:10', '201704051632098215', '0', null, null, null, '3', '1', null, null, null, null, null, null, '很厉害的样子');
INSERT INTO `doctor_service_order` VALUES ('2', '', '', '2017-04-05 16:50:30', '2017-04-05 16:51:28', null, null, '7', '18404030120', '0', null, '3', '18404030120', '0', '2017-04-05 16:50:30', '201704051650304408', '1', null, '2017-04-05 16:51:28', '2017-04-12 16:51:28', '3', '10', null, null, null, null, null, null, '口腔修复');
INSERT INTO `doctor_service_order` VALUES ('3', '', '', '2017-04-05 16:58:59', '2017-04-05 16:58:59', null, null, '21', '13715555555', '0', null, '2', '18810435085', '0', '2017-04-05 16:58:59', '201704051658586106', '0', null, null, '2017-04-14 11:38:31', '1', '1', null, null, null, null, null, null, '专治口腔医师');
INSERT INTO `doctor_service_order` VALUES ('4', '', '', '2017-04-05 17:03:46', '2017-04-05 17:04:15', null, null, '21', '13715555555', '0', null, '4', '13715555555', '0', '2017-04-05 17:03:46', '201704051703454584', '1', null, '2017-04-05 17:04:15', '2017-04-12 17:04:15', '3', '1', null, null, null, null, null, null, '专治鼻炎20年');
INSERT INTO `doctor_service_order` VALUES ('5', '', '', '2017-04-05 17:58:43', '2017-04-05 17:58:59', null, null, '19', '13714444444', '0', null, '6', '13714444444', '0', '2017-04-05 17:58:43', '201704051758436348', '1', null, '2017-04-05 17:58:59', '2017-04-12 17:58:59', '3', '1', null, null, null, null, null, null, '哞哞哞哞哞');
INSERT INTO `doctor_service_order` VALUES ('9', '', '', '2017-04-06 11:51:28', '2017-04-06 11:51:28', null, null, '25', '18310286118', '0', null, '2', '18810435085', '0', '2017-04-06 11:51:28', '201704061151273058', '0', null, null, null, '3', '1', null, null, null, null, null, null, '专治口腔医师');
INSERT INTO `doctor_service_order` VALUES ('10', '', '', '2017-04-06 11:59:19', '2017-04-06 11:59:35', null, null, '2', '18810435085', '0', null, '2', '18810435085', '0', '2017-04-06 11:59:19', '201704061159185618', '1', null, '2017-04-06 11:59:35', '2017-04-13 11:59:35', '3', '1', null, null, null, '曹雨征', null, null, '专治口腔医师');
INSERT INTO `doctor_service_order` VALUES ('11', '', '', '2017-04-06 12:55:24', '2017-04-06 12:55:24', null, null, '7', '18404030120', '0', null, '1', '15831306633', '0', '2017-04-06 12:55:24', '201704061255235895', '0', null, null, null, '3', '30000', null, null, null, '宋国霞', null, null, '很厉害的样子');
INSERT INTO `doctor_service_order` VALUES ('12', '', '', '2017-04-06 12:55:38', '2017-04-06 12:55:38', null, null, '7', '18404030120', '0', null, '6', '13714444444', '0', '2017-04-06 12:55:38', '201704061255374699', '0', null, null, null, '3', '1', null, null, null, '宋国霞', null, null, '哞哞哞哞哞');
INSERT INTO `doctor_service_order` VALUES ('13', '', '', '2017-04-06 12:55:55', '2017-04-06 12:55:55', null, null, '7', '18404030120', '0', null, '6', '13714444444', '0', '2017-04-06 12:55:55', '201704061255553673', '0', null, null, null, '3', '1', null, null, null, '宋国霞', null, null, '哞哞哞哞哞');
INSERT INTO `doctor_service_order` VALUES ('14', '', '', '2017-04-06 12:56:55', '2017-04-06 12:56:55', null, null, '7', '18404030120', '0', null, '1', '15831306633', '0', '2017-04-06 12:56:55', '201704061256552203', '0', null, null, null, '3', '30000', null, null, null, '宋国霞', null, null, '很厉害的样子');
INSERT INTO `doctor_service_order` VALUES ('16', '', '', '2017-04-06 13:48:50', '2017-04-06 13:48:50', null, null, '2', '17600298778', '0', null, '1', '15831306633', '0', '2017-04-06 13:48:50', '201704061348502440', '0', null, null, null, '3', '30000', null, null, null, '曹雨征', null, null, '很厉害的样子');
INSERT INTO `doctor_service_order` VALUES ('19', '', '', '2017-04-06 13:56:52', '2017-04-06 13:56:52', null, null, '7', '18404030120', '0', null, '2', '17600298778', '0', '2017-04-06 13:56:52', '201704061356512134', '0', null, null, null, '3', '1', null, null, null, '宋国霞', null, null, '专治口腔医师');
INSERT INTO `doctor_service_order` VALUES ('20', '', '', '2017-04-06 13:57:12', '2017-04-06 13:57:12', null, null, '7', '18404030120', '0', null, '1', '15831306633', '0', '2017-04-06 13:57:12', '201704061357125958', '0', null, null, null, '3', '30000', null, null, null, '宋国霞', null, null, '很厉害的样子');
INSERT INTO `doctor_service_order` VALUES ('21', '', '', '2017-04-06 13:57:25', '2017-04-06 13:57:25', null, null, '7', '18404030120', '0', null, '1', '15831306633', '0', '2017-04-06 13:57:25', '201704061357255307', '0', null, null, null, '3', '30000', null, null, null, '宋国霞', null, null, '很厉害的样子');
INSERT INTO `doctor_service_order` VALUES ('22', '', '', '2017-04-10 15:31:40', '2017-04-10 15:31:40', null, null, '2', '17600298778', '0', null, '1', '15831306633', '0', '2017-04-10 15:31:40', '201704101531393976', '0', null, null, null, '3', '30000', null, null, null, '曹雨征', null, null, '很厉害的样子');
INSERT INTO `doctor_service_order` VALUES ('23', '', '', '2017-04-10 15:31:50', '2017-04-10 15:31:50', null, null, '2', '17600298778', '0', null, '1', '15831306633', '0', '2017-04-10 15:31:50', '201704101531496126', '0', null, null, null, '3', '30000', null, null, null, '曹雨征', null, null, '很厉害的样子');
INSERT INTO `doctor_service_order` VALUES ('24', '', '', '2017-04-11 16:32:56', '2017-04-11 16:32:56', null, null, '2', '17600298778', '0', null, '3', '18404030120', '0', '2017-04-11 16:32:56', '201704111632553913', '0', null, null, null, '3', '10', null, null, null, '曹雨征', null, null, '口腔修复');
INSERT INTO `doctor_service_order` VALUES ('25', '', '', '2017-04-11 16:45:00', '2017-04-11 16:45:00', null, null, '2', '17600298778', '0', null, '6', '13714444444', '0', '2017-04-11 16:45:00', '201704111645009298', '0', null, null, null, '3', '1', null, null, null, '曹雨征', null, null, '哞哞哞哞哞');

-- ----------------------------
-- Table structure for `doctor_service_time`
-- ----------------------------
DROP TABLE IF EXISTS `doctor_service_time`;
CREATE TABLE `doctor_service_time` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `can_consult_endtime` varchar(255) DEFAULT NULL,
  `can_consult_starttime` varchar(255) DEFAULT NULL,
  `doctor_id` bigint(20) DEFAULT NULL,
  `friday` varchar(255) DEFAULT NULL,
  `monday` varchar(255) DEFAULT NULL,
  `saturday` varchar(255) DEFAULT NULL,
  `sunday` varchar(255) DEFAULT NULL,
  `thursday` varchar(255) DEFAULT NULL,
  `tuesday` varchar(255) DEFAULT NULL,
  `wednesday` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of doctor_service_time
-- ----------------------------
INSERT INTO `doctor_service_time` VALUES ('1', '', '', '2017-04-05 13:55:14', '2017-04-05 17:14:20', '22:00', '10:01', '3', '5', '1', '6', null, '4', '2', '3');
INSERT INTO `doctor_service_time` VALUES ('2', '', '', '2017-04-05 16:49:35', '2017-04-05 16:49:35', '16:49', '16:49', '1', '5', '1', '7', null, '4', '2', '3');
INSERT INTO `doctor_service_time` VALUES ('3', '', '', '2017-04-05 17:03:27', '2017-04-05 17:03:27', '', '', '4', '5', '1', '7', null, '4', '2', '3');
INSERT INTO `doctor_service_time` VALUES ('4', '', '', '2017-04-05 17:40:30', '2017-04-05 17:58:33', '', '', '6', '5', '1', '6', null, '4', '2', '3');

-- ----------------------------
-- Table structure for `express_order_info`
-- ----------------------------
DROP TABLE IF EXISTS `express_order_info`;
CREATE TABLE `express_order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `express_id` int(11) DEFAULT NULL,
  `express_name` varchar(255) DEFAULT NULL,
  `express_no` varchar(255) DEFAULT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of express_order_info
-- ----------------------------

-- ----------------------------
-- Table structure for `imlog`
-- ----------------------------
DROP TABLE IF EXISTS `imlog`;
CREATE TABLE `imlog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `customer_phone` varchar(255) DEFAULT NULL,
  `doctor_id` bigint(20) DEFAULT NULL,
  `doctor_phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of imlog
-- ----------------------------
INSERT INTO `imlog` VALUES ('1', null, null, '2017-04-06 16:58:09', null, '2', null, '4', '12121');

-- ----------------------------
-- Table structure for `jq_city`
-- ----------------------------
DROP TABLE IF EXISTS `jq_city`;
CREATE TABLE `jq_city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `cityname` varchar(255) DEFAULT NULL,
  `prov_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of jq_city
-- ----------------------------

-- ----------------------------
-- Table structure for `jq_prov`
-- ----------------------------
DROP TABLE IF EXISTS `jq_prov`;
CREATE TABLE `jq_prov` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `provname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of jq_prov
-- ----------------------------

-- ----------------------------
-- Table structure for `jq_user_bank_card`
-- ----------------------------
DROP TABLE IF EXISTS `jq_user_bank_card`;
CREATE TABLE `jq_user_bank_card` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `card_num` varchar(255) DEFAULT NULL,
  `creat_time` datetime DEFAULT NULL,
  `customer_type` int(11) DEFAULT NULL,
  `is_delete` varchar(255) DEFAULT NULL,
  `own_name` varchar(255) DEFAULT NULL,
  `tel_phone` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of jq_user_bank_card
-- ----------------------------

-- ----------------------------
-- Table structure for `jq_user_identity_card`
-- ----------------------------
DROP TABLE IF EXISTS `jq_user_identity_card`;
CREATE TABLE `jq_user_identity_card` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `creat_time` datetime DEFAULT NULL,
  `customer_type` int(11) DEFAULT NULL,
  `identitynb` varchar(255) DEFAULT NULL,
  `is_delete` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of jq_user_identity_card
-- ----------------------------

-- ----------------------------
-- Table structure for `knowlg_info`
-- ----------------------------
DROP TABLE IF EXISTS `knowlg_info`;
CREATE TABLE `knowlg_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `comment_count` int(11) DEFAULT NULL,
  `content_desc` varchar(255) DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  `image_header` varchar(255) DEFAULT NULL,
  `knowlg_tag` tinyblob,
  `name` varchar(255) DEFAULT NULL,
  `short_desc` varchar(255) DEFAULT NULL,
  `tag_id` int(11) DEFAULT NULL,
  `view_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of knowlg_info
-- ----------------------------

-- ----------------------------
-- Table structure for `knowlg_tag`
-- ----------------------------
DROP TABLE IF EXISTS `knowlg_tag`;
CREATE TABLE `knowlg_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of knowlg_tag
-- ----------------------------

-- ----------------------------
-- Table structure for `loc_city`
-- ----------------------------
DROP TABLE IF EXISTS `loc_city`;
CREATE TABLE `loc_city` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `prov_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3402 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of loc_city
-- ----------------------------
INSERT INTO `loc_city` VALUES ('36', null, null, null, null, '安庆', '3');
INSERT INTO `loc_city` VALUES ('37', null, null, null, null, '蚌埠', '3');
INSERT INTO `loc_city` VALUES ('38', null, null, null, null, '巢湖', '3');
INSERT INTO `loc_city` VALUES ('39', null, null, null, null, '池州', '3');
INSERT INTO `loc_city` VALUES ('40', null, null, null, null, '滁州', '3');
INSERT INTO `loc_city` VALUES ('41', null, null, null, null, '阜阳', '3');
INSERT INTO `loc_city` VALUES ('42', null, null, null, null, '淮北', '3');
INSERT INTO `loc_city` VALUES ('43', null, null, null, null, '淮南', '3');
INSERT INTO `loc_city` VALUES ('44', null, null, null, null, '黄山', '3');
INSERT INTO `loc_city` VALUES ('45', null, null, null, null, '六安', '3');
INSERT INTO `loc_city` VALUES ('46', null, null, null, null, '马鞍山', '3');
INSERT INTO `loc_city` VALUES ('47', null, null, null, null, '宿州', '3');
INSERT INTO `loc_city` VALUES ('48', null, null, null, null, '铜陵', '3');
INSERT INTO `loc_city` VALUES ('49', null, null, null, null, '芜湖', '3');
INSERT INTO `loc_city` VALUES ('50', null, null, null, null, '宣城', '3');
INSERT INTO `loc_city` VALUES ('51', null, null, null, null, '亳州', '3');
INSERT INTO `loc_city` VALUES ('53', null, null, null, null, '福州', '4');
INSERT INTO `loc_city` VALUES ('54', null, null, null, null, '龙岩', '4');
INSERT INTO `loc_city` VALUES ('55', null, null, null, null, '南平', '4');
INSERT INTO `loc_city` VALUES ('56', null, null, null, null, '宁德', '4');
INSERT INTO `loc_city` VALUES ('57', null, null, null, null, '莆田', '4');
INSERT INTO `loc_city` VALUES ('58', null, null, null, null, '泉州', '4');
INSERT INTO `loc_city` VALUES ('59', null, null, null, null, '三明', '4');
INSERT INTO `loc_city` VALUES ('60', null, null, null, null, '厦门', '4');
INSERT INTO `loc_city` VALUES ('61', null, null, null, null, '漳州', '4');
INSERT INTO `loc_city` VALUES ('62', null, null, null, null, '兰州', '5');
INSERT INTO `loc_city` VALUES ('63', null, null, null, null, '白银', '5');
INSERT INTO `loc_city` VALUES ('64', null, null, null, null, '定西', '5');
INSERT INTO `loc_city` VALUES ('65', null, null, null, null, '甘南', '5');
INSERT INTO `loc_city` VALUES ('66', null, null, null, null, '嘉峪关', '5');
INSERT INTO `loc_city` VALUES ('67', null, null, null, null, '金昌', '5');
INSERT INTO `loc_city` VALUES ('68', null, null, null, null, '酒泉', '5');
INSERT INTO `loc_city` VALUES ('69', null, null, null, null, '临夏', '5');
INSERT INTO `loc_city` VALUES ('70', null, null, null, null, '陇南', '5');
INSERT INTO `loc_city` VALUES ('71', null, null, null, null, '平凉', '5');
INSERT INTO `loc_city` VALUES ('72', null, null, null, null, '庆阳', '5');
INSERT INTO `loc_city` VALUES ('73', null, null, null, null, '天水', '5');
INSERT INTO `loc_city` VALUES ('74', null, null, null, null, '武威', '5');
INSERT INTO `loc_city` VALUES ('75', null, null, null, null, '张掖', '5');
INSERT INTO `loc_city` VALUES ('76', null, null, null, null, '广州', '6');
INSERT INTO `loc_city` VALUES ('77', null, null, null, null, '深圳', '6');
INSERT INTO `loc_city` VALUES ('78', null, null, null, null, '潮州', '6');
INSERT INTO `loc_city` VALUES ('79', null, null, null, null, '东莞', '6');
INSERT INTO `loc_city` VALUES ('80', null, null, null, null, '佛山', '6');
INSERT INTO `loc_city` VALUES ('81', null, null, null, null, '河源', '6');
INSERT INTO `loc_city` VALUES ('82', null, null, null, null, '惠州', '6');
INSERT INTO `loc_city` VALUES ('83', null, null, null, null, '江门', '6');
INSERT INTO `loc_city` VALUES ('84', null, null, null, null, '揭阳', '6');
INSERT INTO `loc_city` VALUES ('85', null, null, null, null, '茂名', '6');
INSERT INTO `loc_city` VALUES ('86', null, null, null, null, '梅州', '6');
INSERT INTO `loc_city` VALUES ('87', null, null, null, null, '清远', '6');
INSERT INTO `loc_city` VALUES ('88', null, null, null, null, '汕头', '6');
INSERT INTO `loc_city` VALUES ('89', null, null, null, null, '汕尾', '6');
INSERT INTO `loc_city` VALUES ('90', null, null, null, null, '韶关', '6');
INSERT INTO `loc_city` VALUES ('91', null, null, null, null, '阳江', '6');
INSERT INTO `loc_city` VALUES ('92', null, null, null, null, '云浮', '6');
INSERT INTO `loc_city` VALUES ('93', null, null, null, null, '湛江', '6');
INSERT INTO `loc_city` VALUES ('94', null, null, null, null, '肇庆', '6');
INSERT INTO `loc_city` VALUES ('95', null, null, null, null, '中山', '6');
INSERT INTO `loc_city` VALUES ('96', null, null, null, null, '珠海', '6');
INSERT INTO `loc_city` VALUES ('97', null, null, null, null, '南宁', '7');
INSERT INTO `loc_city` VALUES ('98', null, null, null, null, '桂林', '7');
INSERT INTO `loc_city` VALUES ('99', null, null, null, null, '百色', '7');
INSERT INTO `loc_city` VALUES ('100', null, null, null, null, '北海', '7');
INSERT INTO `loc_city` VALUES ('101', null, null, null, null, '崇左', '7');
INSERT INTO `loc_city` VALUES ('102', null, null, null, null, '防城港', '7');
INSERT INTO `loc_city` VALUES ('103', null, null, null, null, '贵港', '7');
INSERT INTO `loc_city` VALUES ('104', null, null, null, null, '河池', '7');
INSERT INTO `loc_city` VALUES ('105', null, null, null, null, '贺州', '7');
INSERT INTO `loc_city` VALUES ('106', null, null, null, null, '来宾', '7');
INSERT INTO `loc_city` VALUES ('107', null, null, null, null, '柳州', '7');
INSERT INTO `loc_city` VALUES ('108', null, null, null, null, '钦州', '7');
INSERT INTO `loc_city` VALUES ('109', null, null, null, null, '梧州', '7');
INSERT INTO `loc_city` VALUES ('110', null, null, null, null, '玉林', '7');
INSERT INTO `loc_city` VALUES ('111', null, null, null, null, '贵阳', '8');
INSERT INTO `loc_city` VALUES ('112', null, null, null, null, '安顺', '8');
INSERT INTO `loc_city` VALUES ('113', null, null, null, null, '毕节', '8');
INSERT INTO `loc_city` VALUES ('114', null, null, null, null, '六盘水', '8');
INSERT INTO `loc_city` VALUES ('115', null, null, null, null, '黔东南', '8');
INSERT INTO `loc_city` VALUES ('116', null, null, null, null, '黔南', '8');
INSERT INTO `loc_city` VALUES ('117', null, null, null, null, '黔西南', '8');
INSERT INTO `loc_city` VALUES ('118', null, null, null, null, '铜仁', '8');
INSERT INTO `loc_city` VALUES ('119', null, null, null, null, '遵义', '8');
INSERT INTO `loc_city` VALUES ('120', null, null, null, null, '海口', '9');
INSERT INTO `loc_city` VALUES ('121', null, null, null, null, '三亚', '9');
INSERT INTO `loc_city` VALUES ('122', null, null, null, null, '白沙', '9');
INSERT INTO `loc_city` VALUES ('123', null, null, null, null, '保亭', '9');
INSERT INTO `loc_city` VALUES ('124', null, null, null, null, '昌江', '9');
INSERT INTO `loc_city` VALUES ('125', null, null, null, null, '澄迈县', '9');
INSERT INTO `loc_city` VALUES ('126', null, null, null, null, '定安县', '9');
INSERT INTO `loc_city` VALUES ('127', null, null, null, null, '东方', '9');
INSERT INTO `loc_city` VALUES ('128', null, null, null, null, '乐东', '9');
INSERT INTO `loc_city` VALUES ('129', null, null, null, null, '临高县', '9');
INSERT INTO `loc_city` VALUES ('130', null, null, null, null, '陵水', '9');
INSERT INTO `loc_city` VALUES ('131', null, null, null, null, '琼海', '9');
INSERT INTO `loc_city` VALUES ('132', null, null, null, null, '琼中', '9');
INSERT INTO `loc_city` VALUES ('133', null, null, null, null, '屯昌县', '9');
INSERT INTO `loc_city` VALUES ('134', null, null, null, null, '万宁', '9');
INSERT INTO `loc_city` VALUES ('135', null, null, null, null, '文昌', '9');
INSERT INTO `loc_city` VALUES ('136', null, null, null, null, '五指山', '9');
INSERT INTO `loc_city` VALUES ('137', null, null, null, null, '儋州', '9');
INSERT INTO `loc_city` VALUES ('138', null, null, null, null, '石家庄', '10');
INSERT INTO `loc_city` VALUES ('139', null, null, null, null, '保定', '10');
INSERT INTO `loc_city` VALUES ('140', null, null, null, null, '沧州', '10');
INSERT INTO `loc_city` VALUES ('141', null, null, null, null, '承德', '10');
INSERT INTO `loc_city` VALUES ('142', null, null, null, null, '邯郸', '10');
INSERT INTO `loc_city` VALUES ('143', null, null, null, null, '衡水', '10');
INSERT INTO `loc_city` VALUES ('144', null, null, null, null, '廊坊', '10');
INSERT INTO `loc_city` VALUES ('145', null, null, null, null, '秦皇岛', '10');
INSERT INTO `loc_city` VALUES ('146', null, null, null, null, '唐山', '10');
INSERT INTO `loc_city` VALUES ('147', null, null, null, null, '邢台', '10');
INSERT INTO `loc_city` VALUES ('148', null, null, null, null, '张家口', '10');
INSERT INTO `loc_city` VALUES ('149', null, null, null, null, '郑州', '11');
INSERT INTO `loc_city` VALUES ('150', null, null, null, null, '洛阳', '11');
INSERT INTO `loc_city` VALUES ('151', null, null, null, null, '开封', '11');
INSERT INTO `loc_city` VALUES ('152', null, null, null, null, '安阳', '11');
INSERT INTO `loc_city` VALUES ('153', null, null, null, null, '鹤壁', '11');
INSERT INTO `loc_city` VALUES ('154', null, null, null, null, '济源', '11');
INSERT INTO `loc_city` VALUES ('155', null, null, null, null, '焦作', '11');
INSERT INTO `loc_city` VALUES ('156', null, null, null, null, '南阳', '11');
INSERT INTO `loc_city` VALUES ('157', null, null, null, null, '平顶山', '11');
INSERT INTO `loc_city` VALUES ('158', null, null, null, null, '三门峡', '11');
INSERT INTO `loc_city` VALUES ('159', null, null, null, null, '商丘', '11');
INSERT INTO `loc_city` VALUES ('160', null, null, null, null, '新乡', '11');
INSERT INTO `loc_city` VALUES ('161', null, null, null, null, '信阳', '11');
INSERT INTO `loc_city` VALUES ('162', null, null, null, null, '许昌', '11');
INSERT INTO `loc_city` VALUES ('163', null, null, null, null, '周口', '11');
INSERT INTO `loc_city` VALUES ('164', null, null, null, null, '驻马店', '11');
INSERT INTO `loc_city` VALUES ('165', null, null, null, null, '漯河', '11');
INSERT INTO `loc_city` VALUES ('166', null, null, null, null, '濮阳', '11');
INSERT INTO `loc_city` VALUES ('167', null, null, null, null, '哈尔滨', '12');
INSERT INTO `loc_city` VALUES ('168', null, null, null, null, '大庆', '12');
INSERT INTO `loc_city` VALUES ('169', null, null, null, null, '大兴安岭', '12');
INSERT INTO `loc_city` VALUES ('170', null, null, null, null, '鹤岗', '12');
INSERT INTO `loc_city` VALUES ('171', null, null, null, null, '黑河', '12');
INSERT INTO `loc_city` VALUES ('172', null, null, null, null, '鸡西', '12');
INSERT INTO `loc_city` VALUES ('173', null, null, null, null, '佳木斯', '12');
INSERT INTO `loc_city` VALUES ('174', null, null, null, null, '牡丹江', '12');
INSERT INTO `loc_city` VALUES ('175', null, null, null, null, '七台河', '12');
INSERT INTO `loc_city` VALUES ('176', null, null, null, null, '齐齐哈尔', '12');
INSERT INTO `loc_city` VALUES ('177', null, null, null, null, '双鸭山', '12');
INSERT INTO `loc_city` VALUES ('178', null, null, null, null, '绥化', '12');
INSERT INTO `loc_city` VALUES ('179', null, null, null, null, '伊春', '12');
INSERT INTO `loc_city` VALUES ('180', null, null, null, null, '武汉', '13');
INSERT INTO `loc_city` VALUES ('181', null, null, null, null, '仙桃', '13');
INSERT INTO `loc_city` VALUES ('182', null, null, null, null, '鄂州', '13');
INSERT INTO `loc_city` VALUES ('183', null, null, null, null, '黄冈', '13');
INSERT INTO `loc_city` VALUES ('184', null, null, null, null, '黄石', '13');
INSERT INTO `loc_city` VALUES ('185', null, null, null, null, '荆门', '13');
INSERT INTO `loc_city` VALUES ('186', null, null, null, null, '荆州', '13');
INSERT INTO `loc_city` VALUES ('187', null, null, null, null, '潜江', '13');
INSERT INTO `loc_city` VALUES ('188', null, null, null, null, '神农架林区', '13');
INSERT INTO `loc_city` VALUES ('189', null, null, null, null, '十堰', '13');
INSERT INTO `loc_city` VALUES ('190', null, null, null, null, '随州', '13');
INSERT INTO `loc_city` VALUES ('191', null, null, null, null, '天门', '13');
INSERT INTO `loc_city` VALUES ('192', null, null, null, null, '咸宁', '13');
INSERT INTO `loc_city` VALUES ('193', null, null, null, null, '襄樊', '13');
INSERT INTO `loc_city` VALUES ('194', null, null, null, null, '孝感', '13');
INSERT INTO `loc_city` VALUES ('195', null, null, null, null, '宜昌', '13');
INSERT INTO `loc_city` VALUES ('196', null, null, null, null, '恩施', '13');
INSERT INTO `loc_city` VALUES ('197', null, null, null, null, '长沙', '14');
INSERT INTO `loc_city` VALUES ('198', null, null, null, null, '张家界', '14');
INSERT INTO `loc_city` VALUES ('199', null, null, null, null, '常德', '14');
INSERT INTO `loc_city` VALUES ('200', null, null, null, null, '郴州', '14');
INSERT INTO `loc_city` VALUES ('201', null, null, null, null, '衡阳', '14');
INSERT INTO `loc_city` VALUES ('202', null, null, null, null, '怀化', '14');
INSERT INTO `loc_city` VALUES ('203', null, null, null, null, '娄底', '14');
INSERT INTO `loc_city` VALUES ('204', null, null, null, null, '邵阳', '14');
INSERT INTO `loc_city` VALUES ('205', null, null, null, null, '湘潭', '14');
INSERT INTO `loc_city` VALUES ('206', null, null, null, null, '湘西', '14');
INSERT INTO `loc_city` VALUES ('207', null, null, null, null, '益阳', '14');
INSERT INTO `loc_city` VALUES ('208', null, null, null, null, '永州', '14');
INSERT INTO `loc_city` VALUES ('209', null, null, null, null, '岳阳', '14');
INSERT INTO `loc_city` VALUES ('210', null, null, null, null, '株洲', '14');
INSERT INTO `loc_city` VALUES ('211', null, null, null, null, '长春', '15');
INSERT INTO `loc_city` VALUES ('212', null, null, null, null, '吉林', '15');
INSERT INTO `loc_city` VALUES ('213', null, null, null, null, '白城', '15');
INSERT INTO `loc_city` VALUES ('214', null, null, null, null, '白山', '15');
INSERT INTO `loc_city` VALUES ('215', null, null, null, null, '辽源', '15');
INSERT INTO `loc_city` VALUES ('216', null, null, null, null, '四平', '15');
INSERT INTO `loc_city` VALUES ('217', null, null, null, null, '松原', '15');
INSERT INTO `loc_city` VALUES ('218', null, null, null, null, '通化', '15');
INSERT INTO `loc_city` VALUES ('219', null, null, null, null, '延边', '15');
INSERT INTO `loc_city` VALUES ('220', null, null, null, null, '南京', '16');
INSERT INTO `loc_city` VALUES ('221', null, null, null, null, '苏州', '16');
INSERT INTO `loc_city` VALUES ('222', null, null, null, null, '无锡', '16');
INSERT INTO `loc_city` VALUES ('223', null, null, null, null, '常州', '16');
INSERT INTO `loc_city` VALUES ('224', null, null, null, null, '淮安', '16');
INSERT INTO `loc_city` VALUES ('225', null, null, null, null, '连云港', '16');
INSERT INTO `loc_city` VALUES ('226', null, null, null, null, '南通', '16');
INSERT INTO `loc_city` VALUES ('227', null, null, null, null, '宿迁', '16');
INSERT INTO `loc_city` VALUES ('228', null, null, null, null, '泰州', '16');
INSERT INTO `loc_city` VALUES ('229', null, null, null, null, '徐州', '16');
INSERT INTO `loc_city` VALUES ('230', null, null, null, null, '盐城', '16');
INSERT INTO `loc_city` VALUES ('231', null, null, null, null, '扬州', '16');
INSERT INTO `loc_city` VALUES ('232', null, null, null, null, '镇江', '16');
INSERT INTO `loc_city` VALUES ('233', null, null, null, null, '南昌', '17');
INSERT INTO `loc_city` VALUES ('234', null, null, null, null, '抚州', '17');
INSERT INTO `loc_city` VALUES ('235', null, null, null, null, '赣州', '17');
INSERT INTO `loc_city` VALUES ('236', null, null, null, null, '吉安', '17');
INSERT INTO `loc_city` VALUES ('237', null, null, null, null, '景德镇', '17');
INSERT INTO `loc_city` VALUES ('238', null, null, null, null, '九江', '17');
INSERT INTO `loc_city` VALUES ('239', null, null, null, null, '萍乡', '17');
INSERT INTO `loc_city` VALUES ('240', null, null, null, null, '上饶', '17');
INSERT INTO `loc_city` VALUES ('241', null, null, null, null, '新余', '17');
INSERT INTO `loc_city` VALUES ('242', null, null, null, null, '宜春', '17');
INSERT INTO `loc_city` VALUES ('243', null, null, null, null, '鹰潭', '17');
INSERT INTO `loc_city` VALUES ('244', null, null, null, null, '沈阳', '18');
INSERT INTO `loc_city` VALUES ('245', null, null, null, null, '大连', '18');
INSERT INTO `loc_city` VALUES ('246', null, null, null, null, '鞍山', '18');
INSERT INTO `loc_city` VALUES ('247', null, null, null, null, '本溪', '18');
INSERT INTO `loc_city` VALUES ('248', null, null, null, null, '朝阳', '18');
INSERT INTO `loc_city` VALUES ('249', null, null, null, null, '丹东', '18');
INSERT INTO `loc_city` VALUES ('250', null, null, null, null, '抚顺', '18');
INSERT INTO `loc_city` VALUES ('251', null, null, null, null, '阜新', '18');
INSERT INTO `loc_city` VALUES ('252', null, null, null, null, '葫芦岛', '18');
INSERT INTO `loc_city` VALUES ('253', null, null, null, null, '锦州', '18');
INSERT INTO `loc_city` VALUES ('254', null, null, null, null, '辽阳', '18');
INSERT INTO `loc_city` VALUES ('255', null, null, null, null, '盘锦', '18');
INSERT INTO `loc_city` VALUES ('256', null, null, null, null, '铁岭', '18');
INSERT INTO `loc_city` VALUES ('257', null, null, null, null, '营口', '18');
INSERT INTO `loc_city` VALUES ('258', null, null, null, null, '呼和浩特', '19');
INSERT INTO `loc_city` VALUES ('259', null, null, null, null, '阿拉善盟', '19');
INSERT INTO `loc_city` VALUES ('260', null, null, null, null, '巴彦淖尔盟', '19');
INSERT INTO `loc_city` VALUES ('261', null, null, null, null, '包头', '19');
INSERT INTO `loc_city` VALUES ('262', null, null, null, null, '赤峰', '19');
INSERT INTO `loc_city` VALUES ('263', null, null, null, null, '鄂尔多斯', '19');
INSERT INTO `loc_city` VALUES ('264', null, null, null, null, '呼伦贝尔', '19');
INSERT INTO `loc_city` VALUES ('265', null, null, null, null, '通辽', '19');
INSERT INTO `loc_city` VALUES ('266', null, null, null, null, '乌海', '19');
INSERT INTO `loc_city` VALUES ('267', null, null, null, null, '乌兰察布市', '19');
INSERT INTO `loc_city` VALUES ('268', null, null, null, null, '锡林郭勒盟', '19');
INSERT INTO `loc_city` VALUES ('269', null, null, null, null, '兴安盟', '19');
INSERT INTO `loc_city` VALUES ('270', null, null, null, null, '银川', '20');
INSERT INTO `loc_city` VALUES ('271', null, null, null, null, '固原', '20');
INSERT INTO `loc_city` VALUES ('272', null, null, null, null, '石嘴山', '20');
INSERT INTO `loc_city` VALUES ('273', null, null, null, null, '吴忠', '20');
INSERT INTO `loc_city` VALUES ('274', null, null, null, null, '中卫', '20');
INSERT INTO `loc_city` VALUES ('275', null, null, null, null, '西宁', '21');
INSERT INTO `loc_city` VALUES ('276', null, null, null, null, '果洛', '21');
INSERT INTO `loc_city` VALUES ('277', null, null, null, null, '海北', '21');
INSERT INTO `loc_city` VALUES ('278', null, null, null, null, '海东', '21');
INSERT INTO `loc_city` VALUES ('279', null, null, null, null, '海南', '21');
INSERT INTO `loc_city` VALUES ('280', null, null, null, null, '海西', '21');
INSERT INTO `loc_city` VALUES ('281', null, null, null, null, '黄南', '21');
INSERT INTO `loc_city` VALUES ('282', null, null, null, null, '玉树', '21');
INSERT INTO `loc_city` VALUES ('283', null, null, null, null, '济南', '22');
INSERT INTO `loc_city` VALUES ('284', null, null, null, null, '青岛', '22');
INSERT INTO `loc_city` VALUES ('285', null, null, null, null, '滨州', '22');
INSERT INTO `loc_city` VALUES ('286', null, null, null, null, '德州', '22');
INSERT INTO `loc_city` VALUES ('287', null, null, null, null, '东营', '22');
INSERT INTO `loc_city` VALUES ('288', null, null, null, null, '菏泽', '22');
INSERT INTO `loc_city` VALUES ('289', null, null, null, null, '济宁', '22');
INSERT INTO `loc_city` VALUES ('290', null, null, null, null, '莱芜', '22');
INSERT INTO `loc_city` VALUES ('291', null, null, null, null, '聊城', '22');
INSERT INTO `loc_city` VALUES ('292', null, null, null, null, '临沂', '22');
INSERT INTO `loc_city` VALUES ('293', null, null, null, null, '日照', '22');
INSERT INTO `loc_city` VALUES ('294', null, null, null, null, '泰安', '22');
INSERT INTO `loc_city` VALUES ('295', null, null, null, null, '威海', '22');
INSERT INTO `loc_city` VALUES ('296', null, null, null, null, '潍坊', '22');
INSERT INTO `loc_city` VALUES ('297', null, null, null, null, '烟台', '22');
INSERT INTO `loc_city` VALUES ('298', null, null, null, null, '枣庄', '22');
INSERT INTO `loc_city` VALUES ('299', null, null, null, null, '淄博', '22');
INSERT INTO `loc_city` VALUES ('300', null, null, null, null, '太原', '23');
INSERT INTO `loc_city` VALUES ('301', null, null, null, null, '长治', '23');
INSERT INTO `loc_city` VALUES ('302', null, null, null, null, '大同', '23');
INSERT INTO `loc_city` VALUES ('303', null, null, null, null, '晋城', '23');
INSERT INTO `loc_city` VALUES ('304', null, null, null, null, '晋中', '23');
INSERT INTO `loc_city` VALUES ('305', null, null, null, null, '临汾', '23');
INSERT INTO `loc_city` VALUES ('306', null, null, null, null, '吕梁', '23');
INSERT INTO `loc_city` VALUES ('307', null, null, null, null, '朔州', '23');
INSERT INTO `loc_city` VALUES ('308', null, null, null, null, '忻州', '23');
INSERT INTO `loc_city` VALUES ('309', null, null, null, null, '阳泉', '23');
INSERT INTO `loc_city` VALUES ('310', null, null, null, null, '运城', '23');
INSERT INTO `loc_city` VALUES ('311', null, null, null, null, '西安', '24');
INSERT INTO `loc_city` VALUES ('312', null, null, null, null, '安康', '24');
INSERT INTO `loc_city` VALUES ('313', null, null, null, null, '宝鸡', '24');
INSERT INTO `loc_city` VALUES ('314', null, null, null, null, '汉中', '24');
INSERT INTO `loc_city` VALUES ('315', null, null, null, null, '商洛', '24');
INSERT INTO `loc_city` VALUES ('316', null, null, null, null, '铜川', '24');
INSERT INTO `loc_city` VALUES ('317', null, null, null, null, '渭南', '24');
INSERT INTO `loc_city` VALUES ('318', null, null, null, null, '咸阳', '24');
INSERT INTO `loc_city` VALUES ('319', null, null, null, null, '延安', '24');
INSERT INTO `loc_city` VALUES ('320', null, null, null, null, '榆林', '24');
INSERT INTO `loc_city` VALUES ('322', null, null, null, null, '成都', '26');
INSERT INTO `loc_city` VALUES ('323', null, null, null, null, '绵阳', '26');
INSERT INTO `loc_city` VALUES ('324', null, null, null, null, '阿坝', '26');
INSERT INTO `loc_city` VALUES ('325', null, null, null, null, '巴中', '26');
INSERT INTO `loc_city` VALUES ('326', null, null, null, null, '达州', '26');
INSERT INTO `loc_city` VALUES ('327', null, null, null, null, '德阳', '26');
INSERT INTO `loc_city` VALUES ('328', null, null, null, null, '甘孜', '26');
INSERT INTO `loc_city` VALUES ('329', null, null, null, null, '广安', '26');
INSERT INTO `loc_city` VALUES ('330', null, null, null, null, '广元', '26');
INSERT INTO `loc_city` VALUES ('331', null, null, null, null, '乐山', '26');
INSERT INTO `loc_city` VALUES ('332', null, null, null, null, '凉山', '26');
INSERT INTO `loc_city` VALUES ('333', null, null, null, null, '眉山', '26');
INSERT INTO `loc_city` VALUES ('334', null, null, null, null, '南充', '26');
INSERT INTO `loc_city` VALUES ('335', null, null, null, null, '内江', '26');
INSERT INTO `loc_city` VALUES ('336', null, null, null, null, '攀枝花', '26');
INSERT INTO `loc_city` VALUES ('337', null, null, null, null, '遂宁', '26');
INSERT INTO `loc_city` VALUES ('338', null, null, null, null, '雅安', '26');
INSERT INTO `loc_city` VALUES ('339', null, null, null, null, '宜宾', '26');
INSERT INTO `loc_city` VALUES ('340', null, null, null, null, '资阳', '26');
INSERT INTO `loc_city` VALUES ('341', null, null, null, null, '自贡', '26');
INSERT INTO `loc_city` VALUES ('342', null, null, null, null, '泸州', '26');
INSERT INTO `loc_city` VALUES ('344', null, null, null, null, '拉萨', '28');
INSERT INTO `loc_city` VALUES ('345', null, null, null, null, '阿里', '28');
INSERT INTO `loc_city` VALUES ('346', null, null, null, null, '昌都', '28');
INSERT INTO `loc_city` VALUES ('347', null, null, null, null, '林芝', '28');
INSERT INTO `loc_city` VALUES ('348', null, null, null, null, '那曲', '28');
INSERT INTO `loc_city` VALUES ('349', null, null, null, null, '日喀则', '28');
INSERT INTO `loc_city` VALUES ('350', null, null, null, null, '山南', '28');
INSERT INTO `loc_city` VALUES ('351', null, null, null, null, '乌鲁木齐', '29');
INSERT INTO `loc_city` VALUES ('352', null, null, null, null, '阿克苏', '29');
INSERT INTO `loc_city` VALUES ('353', null, null, null, null, '阿拉尔', '29');
INSERT INTO `loc_city` VALUES ('354', null, null, null, null, '巴音郭楞', '29');
INSERT INTO `loc_city` VALUES ('355', null, null, null, null, '博尔塔拉', '29');
INSERT INTO `loc_city` VALUES ('356', null, null, null, null, '昌吉', '29');
INSERT INTO `loc_city` VALUES ('357', null, null, null, null, '哈密', '29');
INSERT INTO `loc_city` VALUES ('358', null, null, null, null, '和田', '29');
INSERT INTO `loc_city` VALUES ('359', null, null, null, null, '喀什', '29');
INSERT INTO `loc_city` VALUES ('360', null, null, null, null, '克拉玛依', '29');
INSERT INTO `loc_city` VALUES ('361', null, null, null, null, '克孜勒苏', '29');
INSERT INTO `loc_city` VALUES ('362', null, null, null, null, '石河子', '29');
INSERT INTO `loc_city` VALUES ('363', null, null, null, null, '图木舒克', '29');
INSERT INTO `loc_city` VALUES ('364', null, null, null, null, '吐鲁番', '29');
INSERT INTO `loc_city` VALUES ('365', null, null, null, null, '五家渠', '29');
INSERT INTO `loc_city` VALUES ('366', null, null, null, null, '伊犁', '29');
INSERT INTO `loc_city` VALUES ('367', null, null, null, null, '昆明', '30');
INSERT INTO `loc_city` VALUES ('368', null, null, null, null, '怒江', '30');
INSERT INTO `loc_city` VALUES ('369', null, null, null, null, '普洱', '30');
INSERT INTO `loc_city` VALUES ('370', null, null, null, null, '丽江', '30');
INSERT INTO `loc_city` VALUES ('371', null, null, null, null, '保山', '30');
INSERT INTO `loc_city` VALUES ('372', null, null, null, null, '楚雄', '30');
INSERT INTO `loc_city` VALUES ('373', null, null, null, null, '大理', '30');
INSERT INTO `loc_city` VALUES ('374', null, null, null, null, '德宏', '30');
INSERT INTO `loc_city` VALUES ('375', null, null, null, null, '迪庆', '30');
INSERT INTO `loc_city` VALUES ('376', null, null, null, null, '红河', '30');
INSERT INTO `loc_city` VALUES ('377', null, null, null, null, '临沧', '30');
INSERT INTO `loc_city` VALUES ('378', null, null, null, null, '曲靖', '30');
INSERT INTO `loc_city` VALUES ('379', null, null, null, null, '文山', '30');
INSERT INTO `loc_city` VALUES ('380', null, null, null, null, '西双版纳', '30');
INSERT INTO `loc_city` VALUES ('381', null, null, null, null, '玉溪', '30');
INSERT INTO `loc_city` VALUES ('382', null, null, null, null, '昭通', '30');
INSERT INTO `loc_city` VALUES ('383', null, null, null, null, '杭州', '31');
INSERT INTO `loc_city` VALUES ('384', null, null, null, null, '湖州', '31');
INSERT INTO `loc_city` VALUES ('385', null, null, null, null, '嘉兴', '31');
INSERT INTO `loc_city` VALUES ('386', null, null, null, null, '金华', '31');
INSERT INTO `loc_city` VALUES ('387', null, null, null, null, '丽水', '31');
INSERT INTO `loc_city` VALUES ('388', null, null, null, null, '宁波', '31');
INSERT INTO `loc_city` VALUES ('389', null, null, null, null, '绍兴', '31');
INSERT INTO `loc_city` VALUES ('390', null, null, null, null, '台州', '31');
INSERT INTO `loc_city` VALUES ('391', null, null, null, null, '温州', '31');
INSERT INTO `loc_city` VALUES ('392', null, null, null, null, '舟山', '31');
INSERT INTO `loc_city` VALUES ('393', null, null, null, null, '衢州', '31');
INSERT INTO `loc_city` VALUES ('395', null, null, null, null, '香港', '33');
INSERT INTO `loc_city` VALUES ('396', null, null, null, null, '澳门', '34');
INSERT INTO `loc_city` VALUES ('397', null, null, null, null, '台湾', '35');
INSERT INTO `loc_city` VALUES ('501', null, null, null, null, '西城区', '2');
INSERT INTO `loc_city` VALUES ('502', null, null, null, null, '海淀区', '2');
INSERT INTO `loc_city` VALUES ('503', null, null, null, null, '朝阳区', '2');
INSERT INTO `loc_city` VALUES ('504', null, null, null, null, '崇文区', '2');
INSERT INTO `loc_city` VALUES ('505', null, null, null, null, '宣武区', '2');
INSERT INTO `loc_city` VALUES ('506', null, null, null, null, '丰台区', '2');
INSERT INTO `loc_city` VALUES ('507', null, null, null, null, '石景山区', '2');
INSERT INTO `loc_city` VALUES ('508', null, null, null, null, '房山区', '2');
INSERT INTO `loc_city` VALUES ('509', null, null, null, null, '门头沟区', '2');
INSERT INTO `loc_city` VALUES ('510', null, null, null, null, '通州区', '2');
INSERT INTO `loc_city` VALUES ('511', null, null, null, null, '顺义区', '2');
INSERT INTO `loc_city` VALUES ('512', null, null, null, null, '昌平区', '2');
INSERT INTO `loc_city` VALUES ('513', null, null, null, null, '怀柔区', '2');
INSERT INTO `loc_city` VALUES ('514', null, null, null, null, '平谷区', '2');
INSERT INTO `loc_city` VALUES ('515', null, null, null, null, '大兴区', '2');
INSERT INTO `loc_city` VALUES ('516', null, null, null, null, '密云县', '2');
INSERT INTO `loc_city` VALUES ('517', null, null, null, null, '延庆县', '2');
INSERT INTO `loc_city` VALUES ('2703', null, null, null, null, '长宁区', '25');
INSERT INTO `loc_city` VALUES ('2704', null, null, null, null, '闸北区', '25');
INSERT INTO `loc_city` VALUES ('2705', null, null, null, null, '闵行区', '25');
INSERT INTO `loc_city` VALUES ('2706', null, null, null, null, '徐汇区', '25');
INSERT INTO `loc_city` VALUES ('2707', null, null, null, null, '浦东新区', '25');
INSERT INTO `loc_city` VALUES ('2708', null, null, null, null, '杨浦区', '25');
INSERT INTO `loc_city` VALUES ('2709', null, null, null, null, '普陀区', '25');
INSERT INTO `loc_city` VALUES ('2710', null, null, null, null, '静安区', '25');
INSERT INTO `loc_city` VALUES ('2711', null, null, null, null, '卢湾区', '25');
INSERT INTO `loc_city` VALUES ('2712', null, null, null, null, '虹口区', '25');
INSERT INTO `loc_city` VALUES ('2713', null, null, null, null, '黄浦区', '25');
INSERT INTO `loc_city` VALUES ('2714', null, null, null, null, '南汇区', '25');
INSERT INTO `loc_city` VALUES ('2715', null, null, null, null, '松江区', '25');
INSERT INTO `loc_city` VALUES ('2716', null, null, null, null, '嘉定区', '25');
INSERT INTO `loc_city` VALUES ('2717', null, null, null, null, '宝山区', '25');
INSERT INTO `loc_city` VALUES ('2718', null, null, null, null, '青浦区', '25');
INSERT INTO `loc_city` VALUES ('2719', null, null, null, null, '金山区', '25');
INSERT INTO `loc_city` VALUES ('2720', null, null, null, null, '奉贤区', '25');
INSERT INTO `loc_city` VALUES ('2721', null, null, null, null, '崇明县', '25');
INSERT INTO `loc_city` VALUES ('2913', null, null, null, null, '河西区', '27');
INSERT INTO `loc_city` VALUES ('2914', null, null, null, null, '南开区', '27');
INSERT INTO `loc_city` VALUES ('2915', null, null, null, null, '河北区', '27');
INSERT INTO `loc_city` VALUES ('2916', null, null, null, null, '河东区', '27');
INSERT INTO `loc_city` VALUES ('2917', null, null, null, null, '红桥区', '27');
INSERT INTO `loc_city` VALUES ('2918', null, null, null, null, '东丽区', '27');
INSERT INTO `loc_city` VALUES ('2919', null, null, null, null, '津南区', '27');
INSERT INTO `loc_city` VALUES ('2920', null, null, null, null, '西青区', '27');
INSERT INTO `loc_city` VALUES ('2921', null, null, null, null, '北辰区', '27');
INSERT INTO `loc_city` VALUES ('2922', null, null, null, null, '塘沽区', '27');
INSERT INTO `loc_city` VALUES ('2923', null, null, null, null, '汉沽区', '27');
INSERT INTO `loc_city` VALUES ('2924', null, null, null, null, '大港区', '27');
INSERT INTO `loc_city` VALUES ('2925', null, null, null, null, '武清区', '27');
INSERT INTO `loc_city` VALUES ('2926', null, null, null, null, '宝坻区', '27');
INSERT INTO `loc_city` VALUES ('2927', null, null, null, null, '经济开发区', '27');
INSERT INTO `loc_city` VALUES ('2928', null, null, null, null, '宁河县', '27');
INSERT INTO `loc_city` VALUES ('2929', null, null, null, null, '静海县', '27');
INSERT INTO `loc_city` VALUES ('2930', null, null, null, null, '蓟县', '27');
INSERT INTO `loc_city` VALUES ('3325', null, null, null, null, '合川区', '32');
INSERT INTO `loc_city` VALUES ('3326', null, null, null, null, '江津区', '32');
INSERT INTO `loc_city` VALUES ('3327', null, null, null, null, '南川区', '32');
INSERT INTO `loc_city` VALUES ('3328', null, null, null, null, '永川区', '32');
INSERT INTO `loc_city` VALUES ('3329', null, null, null, null, '南岸区', '32');
INSERT INTO `loc_city` VALUES ('3330', null, null, null, null, '渝北区', '32');
INSERT INTO `loc_city` VALUES ('3331', null, null, null, null, '万盛区', '32');
INSERT INTO `loc_city` VALUES ('3332', null, null, null, null, '大渡口区', '32');
INSERT INTO `loc_city` VALUES ('3333', null, null, null, null, '万州区', '32');
INSERT INTO `loc_city` VALUES ('3334', null, null, null, null, '北碚区', '32');
INSERT INTO `loc_city` VALUES ('3335', null, null, null, null, '沙坪坝区', '32');
INSERT INTO `loc_city` VALUES ('3336', null, null, null, null, '巴南区', '32');
INSERT INTO `loc_city` VALUES ('3337', null, null, null, null, '涪陵区', '32');
INSERT INTO `loc_city` VALUES ('3338', null, null, null, null, '江北区', '32');
INSERT INTO `loc_city` VALUES ('3339', null, null, null, null, '九龙坡区', '32');
INSERT INTO `loc_city` VALUES ('3340', null, null, null, null, '渝中区', '32');
INSERT INTO `loc_city` VALUES ('3341', null, null, null, null, '黔江开发区', '32');
INSERT INTO `loc_city` VALUES ('3342', null, null, null, null, '长寿区', '32');
INSERT INTO `loc_city` VALUES ('3343', null, null, null, null, '双桥区', '32');
INSERT INTO `loc_city` VALUES ('3344', null, null, null, null, '綦江县', '32');
INSERT INTO `loc_city` VALUES ('3345', null, null, null, null, '潼南县', '32');
INSERT INTO `loc_city` VALUES ('3346', null, null, null, null, '铜梁县', '32');
INSERT INTO `loc_city` VALUES ('3347', null, null, null, null, '大足县', '32');
INSERT INTO `loc_city` VALUES ('3348', null, null, null, null, '荣昌县', '32');
INSERT INTO `loc_city` VALUES ('3349', null, null, null, null, '璧山县', '32');
INSERT INTO `loc_city` VALUES ('3350', null, null, null, null, '垫江县', '32');
INSERT INTO `loc_city` VALUES ('3351', null, null, null, null, '武隆县', '32');
INSERT INTO `loc_city` VALUES ('3352', null, null, null, null, '丰都县', '32');
INSERT INTO `loc_city` VALUES ('3353', null, null, null, null, '城口县', '32');
INSERT INTO `loc_city` VALUES ('3354', null, null, null, null, '梁平县', '32');
INSERT INTO `loc_city` VALUES ('3355', null, null, null, null, '开县', '32');
INSERT INTO `loc_city` VALUES ('3356', null, null, null, null, '巫溪县', '32');
INSERT INTO `loc_city` VALUES ('3357', null, null, null, null, '巫山县', '32');
INSERT INTO `loc_city` VALUES ('3358', null, null, null, null, '奉节县', '32');
INSERT INTO `loc_city` VALUES ('3359', null, null, null, null, '云阳县', '32');
INSERT INTO `loc_city` VALUES ('3360', null, null, null, null, '忠县', '32');
INSERT INTO `loc_city` VALUES ('3361', null, null, null, null, '石柱', '32');
INSERT INTO `loc_city` VALUES ('3362', null, null, null, null, '彭水', '32');
INSERT INTO `loc_city` VALUES ('3363', null, null, null, null, '酉阳', '32');
INSERT INTO `loc_city` VALUES ('3364', null, null, null, null, '秀山', '32');
INSERT INTO `loc_city` VALUES ('3401', null, null, null, null, '合肥', '3');

-- ----------------------------
-- Table structure for `loc_prov`
-- ----------------------------
DROP TABLE IF EXISTS `loc_prov`;
CREATE TABLE `loc_prov` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of loc_prov
-- ----------------------------
INSERT INTO `loc_prov` VALUES ('2', null, null, null, null, '北京');
INSERT INTO `loc_prov` VALUES ('3', null, null, null, null, '安徽');
INSERT INTO `loc_prov` VALUES ('4', null, null, null, null, '福建');
INSERT INTO `loc_prov` VALUES ('5', null, null, null, null, '甘肃');
INSERT INTO `loc_prov` VALUES ('6', null, null, null, null, '广东');
INSERT INTO `loc_prov` VALUES ('7', null, null, null, null, '广西');
INSERT INTO `loc_prov` VALUES ('8', null, null, null, null, '贵州');
INSERT INTO `loc_prov` VALUES ('9', null, null, null, null, '海南');
INSERT INTO `loc_prov` VALUES ('10', null, null, null, null, '河北');
INSERT INTO `loc_prov` VALUES ('11', null, null, null, null, '河南');
INSERT INTO `loc_prov` VALUES ('12', null, null, null, null, '黑龙江');
INSERT INTO `loc_prov` VALUES ('13', null, null, null, null, '湖北');
INSERT INTO `loc_prov` VALUES ('14', null, null, null, null, '湖南');
INSERT INTO `loc_prov` VALUES ('15', null, null, null, null, '吉林');
INSERT INTO `loc_prov` VALUES ('16', null, null, null, null, '江苏');
INSERT INTO `loc_prov` VALUES ('17', null, null, null, null, '江西');
INSERT INTO `loc_prov` VALUES ('18', null, null, null, null, '辽宁');
INSERT INTO `loc_prov` VALUES ('19', null, null, null, null, '内蒙古');
INSERT INTO `loc_prov` VALUES ('20', null, null, null, null, '宁夏');
INSERT INTO `loc_prov` VALUES ('21', null, null, null, null, '青海');
INSERT INTO `loc_prov` VALUES ('22', null, null, null, null, '山东');
INSERT INTO `loc_prov` VALUES ('23', null, null, null, null, '山西');
INSERT INTO `loc_prov` VALUES ('24', null, null, null, null, '陕西');
INSERT INTO `loc_prov` VALUES ('25', null, null, null, null, '上海');
INSERT INTO `loc_prov` VALUES ('26', null, null, null, null, '四川');
INSERT INTO `loc_prov` VALUES ('27', null, null, null, null, '天津');
INSERT INTO `loc_prov` VALUES ('28', null, null, null, null, '西藏');
INSERT INTO `loc_prov` VALUES ('29', null, null, null, null, '新疆');
INSERT INTO `loc_prov` VALUES ('30', null, null, null, null, '云南');
INSERT INTO `loc_prov` VALUES ('31', null, null, null, null, '浙江');
INSERT INTO `loc_prov` VALUES ('32', null, null, null, null, '重庆');
INSERT INTO `loc_prov` VALUES ('33', null, null, null, null, '香港');
INSERT INTO `loc_prov` VALUES ('34', null, null, null, null, '澳门');
INSERT INTO `loc_prov` VALUES ('35', null, null, null, null, '台湾');

-- ----------------------------
-- Table structure for `logging_event`
-- ----------------------------
DROP TABLE IF EXISTS `logging_event`;
CREATE TABLE `logging_event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `arg0` varchar(255) DEFAULT NULL,
  `arg1` varchar(255) DEFAULT NULL,
  `arg2` varchar(255) DEFAULT NULL,
  `arg3` varchar(255) DEFAULT NULL,
  `caller_class` varchar(255) DEFAULT NULL,
  `caller_filename` varchar(255) DEFAULT NULL,
  `caller_line` varchar(255) DEFAULT NULL,
  `caller_method` varchar(255) DEFAULT NULL,
  `formatted_message` varchar(255) DEFAULT NULL,
  `level_string` varchar(255) DEFAULT NULL,
  `logger_name` varchar(255) DEFAULT NULL,
  `reference_flag` smallint(6) DEFAULT NULL,
  `thread_name` varchar(255) DEFAULT NULL,
  `timestmp` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of logging_event
-- ----------------------------

-- ----------------------------
-- Table structure for `logging_event_exception`
-- ----------------------------
DROP TABLE IF EXISTS `logging_event_exception`;
CREATE TABLE `logging_event_exception` (
  `i` smallint(6) NOT NULL,
  `trace_line` varchar(254) DEFAULT NULL,
  `event_id` bigint(20) NOT NULL,
  PRIMARY KEY (`event_id`),
  CONSTRAINT `FK_hjmlc3lmhedtk9jw71kssif9o` FOREIGN KEY (`event_id`) REFERENCES `logging_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of logging_event_exception
-- ----------------------------

-- ----------------------------
-- Table structure for `logging_event_property`
-- ----------------------------
DROP TABLE IF EXISTS `logging_event_property`;
CREATE TABLE `logging_event_property` (
  `mapped_key` varchar(254) DEFAULT NULL,
  `mapped_value` longtext,
  `event_id` bigint(20) NOT NULL,
  PRIMARY KEY (`event_id`),
  CONSTRAINT `FK_t41emp5ddc9lx9x6ir2gaj5rs` FOREIGN KEY (`event_id`) REFERENCES `logging_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of logging_event_property
-- ----------------------------

-- ----------------------------
-- Table structure for `merchant`
-- ----------------------------
DROP TABLE IF EXISTS `merchant`;
CREATE TABLE `merchant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `client_type` varchar(255) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `customer_phone` varchar(255) DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  `image_cred1` varchar(255) DEFAULT NULL,
  `image_cred2` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `reject_reason` varchar(255) DEFAULT NULL,
  `verify` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of merchant
-- ----------------------------
INSERT INTO `merchant` VALUES ('1', '', '', '2017-04-05 10:42:01', '2017-04-05 10:42:01', null, '2', null, '18810435085', '0', '最大型医院', 'upload/merchant/20170405104200511759517.jpg', 'upload/merchant/20170405104200623641185.jpg', '北京医院', null, null, null);
INSERT INTO `merchant` VALUES ('2', '', '', '2017-04-05 11:07:54', '2017-04-05 11:07:54', null, '7', null, '18404030120', '0', '亲民价格', 'upload/merchant/20170405110754439302725.jpg', 'upload/merchant/20170405110754448595150.jpg_600.jpg', '北京博爱医院', null, null, null);
INSERT INTO `merchant` VALUES ('3', '', '', '2017-04-05 11:41:52', '2017-04-05 11:41:52', null, '10', null, '18801304725', '0', '疾病治疗效果好', 'upload/merchant/20170405114152470747834.jpg', 'upload/merchant/20170405114152486830554.jpg', '北京协和医院', null, null, null);
INSERT INTO `merchant` VALUES ('4', '', '', '2017-04-05 14:03:00', '2017-04-05 14:03:00', null, '13', null, '18633494252', '0', '测试', 'upload/merchant/20170405140259537879468.jpg', 'upload/merchant/20170405140259539752473.jpg', '北京中西医结合医院', null, null, null);
INSERT INTO `merchant` VALUES ('5', '', '', '2017-04-05 17:21:38', '2017-04-05 17:21:38', null, '16', null, '18515644716', '0', '测试测试', 'upload/merchant/20170405172137839955622.jpg', 'upload/merchant/20170405172137846783861.jpg', '北京三甲医院', null, null, null);

-- ----------------------------
-- Table structure for `merchant_activity`
-- ----------------------------
DROP TABLE IF EXISTS `merchant_activity`;
CREATE TABLE `merchant_activity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `activity_type` int(11) DEFAULT NULL,
  `approved` int(11) DEFAULT NULL,
  `begin_time` datetime DEFAULT NULL,
  `content_desc` varchar(255) DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `merchant` tinyblob,
  `merchant_activity_id` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `prod_acti_id` int(11) DEFAULT NULL,
  `short_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of merchant_activity
-- ----------------------------

-- ----------------------------
-- Table structure for `message`
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '', '', '2017-04-05 11:06:11', '2017-04-05 16:47:09', 'http://123.206.47.26:5888/pc/view/customer/toIndex', '不要亚健康', 'upload/jianqiao/20170405164644959795528.jpg', '0', '健康分享');
INSERT INTO `message` VALUES ('2', '', '', '2017-04-05 11:13:04', '2017-04-05 11:13:04', 'server', '我是描述', 'upload/jianqiao/20170405111257281174231.png', '0', '我是标题');
INSERT INTO `message` VALUES ('3', '', '', '2017-04-05 11:22:02', '2017-04-05 16:47:13', 'server', '多运动多喝水', 'upload/jianqiao/20170405164657966181478.jpg', '0', '健康大分享');
INSERT INTO `message` VALUES ('4', '', '', '2017-04-05 11:24:15', '2017-04-05 11:24:15', '1234', '1234', 'upload/jianqiao/20170405112411625950974.jpg', '0', '12345');
INSERT INTO `message` VALUES ('5', '', '', '2017-04-05 11:29:56', '2017-04-05 11:29:56', '1234', '123456', 'upload/jianqiao/20170405112951180875102.jpg', '0', '123456');
INSERT INTO `message` VALUES ('6', '', '', '2017-04-05 11:40:25', '2017-04-05 16:48:01', '123.456.com', '最新报道', 'upload/jianqiao/20170405164724022601925.jpg', '0', '资讯查看');
INSERT INTO `message` VALUES ('7', '', '', '2017-04-05 11:43:10', '2017-04-05 11:43:10', '儿童医疗', '不要亚健康', 'upload/jianqiao/20170405114259693891657.jpg', '0', '健桥大健康');
INSERT INTO `message` VALUES ('8', '', '', '2017-04-06 10:09:43', '2017-04-06 10:09:43', 'aaa', 'a', null, '0', 'a');
INSERT INTO `message` VALUES ('9', '', '', '2017-04-06 10:33:04', '2017-04-06 10:33:04', 'aa', 'a', null, '0', 'a');

-- ----------------------------
-- Table structure for `order_record`
-- ----------------------------
DROP TABLE IF EXISTS `order_record`;
CREATE TABLE `order_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `describer` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `patient_order_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_pje4c4uaqyqgywusap57g1wfk` (`patient_order_id`),
  CONSTRAINT `FK_pje4c4uaqyqgywusap57g1wfk` FOREIGN KEY (`patient_order_id`) REFERENCES `patient_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of order_record
-- ----------------------------

-- ----------------------------
-- Table structure for `patient`
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `custmoer_id` bigint(20) DEFAULT NULL,
  `patient_name` varchar(255) DEFAULT NULL,
  `patient_phone` varchar(255) DEFAULT NULL,
  `relation_id` bigint(20) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of patient
-- ----------------------------

-- ----------------------------
-- Table structure for `patient_case`
-- ----------------------------
DROP TABLE IF EXISTS `patient_case`;
CREATE TABLE `patient_case` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `content` longtext,
  `patient_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_de4kv9pvig39hgpfd4lqefj8k` (`patient_id`),
  CONSTRAINT `FK_de4kv9pvig39hgpfd4lqefj8k` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of patient_case
-- ----------------------------

-- ----------------------------
-- Table structure for `patient_disease`
-- ----------------------------
DROP TABLE IF EXISTS `patient_disease`;
CREATE TABLE `patient_disease` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `city_id` int(11) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `customer_image` varchar(255) DEFAULT NULL,
  `disease_desc` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `patient_address` varchar(255) DEFAULT NULL,
  `product_type_id` int(11) DEFAULT NULL,
  `prov_id` int(11) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `tell` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of patient_disease
-- ----------------------------
INSERT INTO `patient_disease` VALUES ('1', '', '', '2017-04-05 11:35:48', '2017-04-05 11:35:48', '12', null, '3', 'upload/jianqiao/20170405110221717870764.jpg', '快死了', '我', '1234', '1', '2', '1', '13999999999');
INSERT INTO `patient_disease` VALUES ('2', '', '', '2017-04-05 13:40:35', '2017-04-05 13:40:35', '17', null, null, null, '牙齿松动   不敢使劲', '小西', '北京朝阳', '9', '2', '1', '13633684653');
INSERT INTO `patient_disease` VALUES ('3', '', '', '2017-04-05 17:45:58', '2017-04-05 17:45:58', '24', null, '2', null, '心脏病', '曹雨征', '嘉里大通中心', '1', '2', '1', '18810435085');

-- ----------------------------
-- Table structure for `patient_history`
-- ----------------------------
DROP TABLE IF EXISTS `patient_history`;
CREATE TABLE `patient_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `content` longtext,
  `patient_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_4uhhj6o499sv4f81lln8kytuq` (`patient_id`),
  CONSTRAINT `FK_4uhhj6o499sv4f81lln8kytuq` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of patient_history
-- ----------------------------

-- ----------------------------
-- Table structure for `patient_image`
-- ----------------------------
DROP TABLE IF EXISTS `patient_image`;
CREATE TABLE `patient_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `address` varchar(200) NOT NULL,
  `field` int(11) DEFAULT NULL,
  `memory` double DEFAULT NULL,
  `name` varchar(50) NOT NULL,
  `patient_id` bigint(20) DEFAULT NULL,
  `patient_disease_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_a4i9giiftjwaahtn3x0hsymv2` (`patient_id`),
  KEY `FK_2wn0ean8smamivvbgq868lsvh` (`patient_disease_id`),
  CONSTRAINT `FK_2wn0ean8smamivvbgq868lsvh` FOREIGN KEY (`patient_disease_id`) REFERENCES `patient_disease` (`id`),
  CONSTRAINT `FK_a4i9giiftjwaahtn3x0hsymv2` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of patient_image
-- ----------------------------
INSERT INTO `patient_image` VALUES ('1', '', '', '2017-04-05 11:35:48', '2017-04-05 11:35:48', 'upload/jianqiao/20170405113545299062152.jpg', null, null, 'timg (7).jpg', null, '1');
INSERT INTO `patient_image` VALUES ('2', '', '', '2017-04-05 13:40:35', '2017-04-05 13:40:35', 'upload/jianqiao/20170405134028556789875.jpg', null, null, 'u=1326595458,1507536621&fm=23&gp=0.jpg', null, '2');
INSERT INTO `patient_image` VALUES ('3', '', '', '2017-04-05 17:45:58', '2017-04-05 17:45:58', 'upload/jianqiao/20170405174554867378332.jpg', null, null, '7369b867061cc4f6ea60f6d0e40cdddd.jpg', null, '3');

-- ----------------------------
-- Table structure for `patient_medication_record`
-- ----------------------------
DROP TABLE IF EXISTS `patient_medication_record`;
CREATE TABLE `patient_medication_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `content` longtext,
  `patient_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_2dyv5paae75gcgpa34jvyi76b` (`patient_id`),
  CONSTRAINT `FK_2dyv5paae75gcgpa34jvyi76b` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of patient_medication_record
-- ----------------------------

-- ----------------------------
-- Table structure for `patient_order`
-- ----------------------------
DROP TABLE IF EXISTS `patient_order`;
CREATE TABLE `patient_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `amount_paid` bigint(20) DEFAULT NULL,
  `contract_address` varchar(255) DEFAULT NULL,
  `divide_paid` int(11) DEFAULT NULL,
  `fenrun_statu` int(11) DEFAULT NULL,
  `merchant_id` int(11) DEFAULT NULL,
  `merchant_name` varchar(255) DEFAULT NULL,
  `money` bigint(20) DEFAULT NULL,
  `order_num` varchar(255) DEFAULT NULL,
  `patient_id` int(11) DEFAULT NULL,
  `patient_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of patient_order
-- ----------------------------

-- ----------------------------
-- Table structure for `platform_info`
-- ----------------------------
DROP TABLE IF EXISTS `platform_info`;
CREATE TABLE `platform_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `fee` int(11) DEFAULT NULL,
  `total_amount_fen_run` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of platform_info
-- ----------------------------

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `appointment` varchar(255) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `customer_phone` varchar(255) DEFAULT NULL,
  `customer_service_id` varchar(255) DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  `deposite_price` bigint(20) DEFAULT NULL,
  `des1` varchar(255) DEFAULT NULL,
  `detail_html` varchar(255) DEFAULT NULL,
  `detail_url` varchar(255) DEFAULT NULL,
  `divide` int(11) DEFAULT NULL,
  `fee1` bigint(20) DEFAULT NULL,
  `fee2` bigint(20) DEFAULT NULL,
  `fee3` bigint(20) DEFAULT NULL,
  `fee4` bigint(20) DEFAULT NULL,
  `fee5` bigint(20) DEFAULT NULL,
  `market_price` bigint(20) DEFAULT NULL,
  `merchant_id` bigint(20) DEFAULT NULL,
  `merchant_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `off` int(11) DEFAULT NULL,
  `product_type_name` varchar(255) DEFAULT NULL,
  `refund_process` varchar(255) DEFAULT NULL,
  `sale_count` bigint(20) DEFAULT NULL,
  `service_process` varchar(255) DEFAULT NULL,
  `service_type_id` bigint(20) DEFAULT NULL,
  `special` varchar(255) DEFAULT NULL,
  `total_price` bigint(20) DEFAULT NULL,
  `view_count` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('7', '', '', '2017-04-05 16:31:43', '2017-04-05 16:31:43', null, '3', null, '14777777777', '18623814385', '0', '10000', '什么意思啊?', '哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈啊哈啊哈', null, '1', '10000', '0', '0', '0', '0', '1100000', '1', '北京医院', '很厉害的样子', null, null, null, '0', null, '12', null, '10000', '0');
INSERT INTO `product` VALUES ('8', '', '', '2017-04-05 16:45:03', '2017-04-05 16:45:03', null, '7', null, '18404030120', '4000315315', '0', '1', '专治  无痛苦', '无痛苦  方便 简洁', null, '2', '5000', '5000', '0', '0', '0', '60000', '2', '北京博爱医院', '口腔修复', null, null, null, '0', null, '9', null, '10000', '0');
INSERT INTO `product` VALUES ('9', '', '', '2017-04-05 16:54:07', '2017-04-05 16:54:07', null, '1', null, '13716743890', '1234567', '0', '1', '1234567890-&#61;', '1234567890', null, '1', '1', '0', '0', '0', '0', '100', '1', '北京医院', '专治鼻炎20年', null, null, null, '0', null, '11', null, '1', '0');
INSERT INTO `product` VALUES ('10', '', '', '2017-04-05 16:55:26', '2017-04-05 16:55:26', null, '18', null, '18801304725', '123456', '0', '1', '测试测试', '测试测试', null, '1', '1', '0', '0', '0', '0', '1', '4', '北京中西医结合医院', '专治口腔医师', null, null, null, '0', null, '9', null, '1', '0');
INSERT INTO `product` VALUES ('11', '', '', '2017-04-05 17:30:55', '2017-04-05 17:30:55', null, '1', null, '13716743890', '123456789', '0', '1', '1234', '1234', null, '1', '1', '0', '0', '0', '0', '100', '1', '北京医院', '哞哞哞哞哞', null, null, null, '0', null, '11', null, '1', '0');

-- ----------------------------
-- Table structure for `product_doctor`
-- ----------------------------
DROP TABLE IF EXISTS `product_doctor`;
CREATE TABLE `product_doctor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `doctor_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of product_doctor
-- ----------------------------
INSERT INTO `product_doctor` VALUES ('1', '', '', '2017-04-05 10:54:55', '2017-04-05 10:54:55', '2', '1');
INSERT INTO `product_doctor` VALUES ('2', '', '', '2017-04-05 11:06:45', '2017-04-05 11:06:45', '1', '2');
INSERT INTO `product_doctor` VALUES ('3', '', '', '2017-04-05 11:08:38', '2017-04-05 11:08:38', '1', '3');
INSERT INTO `product_doctor` VALUES ('4', '', '', '2017-04-05 11:49:28', '2017-04-05 11:49:28', '3', '4');
INSERT INTO `product_doctor` VALUES ('5', '', '', '2017-04-05 13:49:09', '2017-04-05 13:49:09', '7', '5');
INSERT INTO `product_doctor` VALUES ('6', '', '', '2017-04-05 13:49:58', '2017-04-05 13:49:58', '4', '6');
INSERT INTO `product_doctor` VALUES ('7', '', '', '2017-04-05 16:31:44', '2017-04-05 16:31:44', '1', '7');
INSERT INTO `product_doctor` VALUES ('8', '', '', '2017-04-05 16:45:04', '2017-04-05 16:45:04', '3', '8');
INSERT INTO `product_doctor` VALUES ('10', '', '', '2017-04-05 16:55:26', '2017-04-05 16:55:26', '2', '10');
INSERT INTO `product_doctor` VALUES ('12', '', '', '2017-04-06 10:31:52', '2017-04-06 10:31:52', '4', '11');
INSERT INTO `product_doctor` VALUES ('13', '', '', '2017-04-06 10:34:04', '2017-04-06 10:34:04', '4', '9');

-- ----------------------------
-- Table structure for `product_image`
-- ----------------------------
DROP TABLE IF EXISTS `product_image`;
CREATE TABLE `product_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of product_image
-- ----------------------------
INSERT INTO `product_image` VALUES ('1', '', '', '2017-04-05 10:54:56', '2017-04-05 10:54:56', 'upload/product/20170405105455525821580.jpg', '1');
INSERT INTO `product_image` VALUES ('2', '', '', '2017-04-05 11:06:45', '2017-04-05 11:06:45', 'upload/product/20170405110644993478205.jpg', '2');
INSERT INTO `product_image` VALUES ('3', '', '', '2017-04-05 11:08:38', '2017-04-05 11:08:38', 'upload/product/20170405110837906627404.jpg', '3');
INSERT INTO `product_image` VALUES ('4', '', '', '2017-04-05 11:49:28', '2017-04-05 11:49:28', 'upload/product/20170405114927592362420.jpg_600.jpg', '4');
INSERT INTO `product_image` VALUES ('5', '', '', '2017-04-05 13:49:09', '2017-04-05 13:49:09', 'upload/product/20170405134908629604823.png', '5');
INSERT INTO `product_image` VALUES ('6', '', '', '2017-04-05 13:49:58', '2017-04-05 13:49:58', 'upload/product/20170405134957881356586.jpg', '6');
INSERT INTO `product_image` VALUES ('7', '', '', '2017-04-05 16:31:44', '2017-04-05 16:31:44', 'upload/product/20170405163143625131423.png', '7');
INSERT INTO `product_image` VALUES ('8', '', '', '2017-04-05 16:45:05', '2017-04-05 16:45:05', 'upload/product/20170405164503527066844.jpg', '8');
INSERT INTO `product_image` VALUES ('9', '', '', '2017-04-05 16:54:07', '2017-04-05 16:54:07', 'upload/product/20170405165406647206910.jpg', '9');
INSERT INTO `product_image` VALUES ('10', '', '', '2017-04-05 16:55:27', '2017-04-05 16:55:27', 'upload/product/20170405165525685537024.jpg', '10');
INSERT INTO `product_image` VALUES ('11', '', '', '2017-04-05 17:30:55', '2017-04-05 17:30:55', 'upload/product/20170405173055398072114.jpg', '11');

-- ----------------------------
-- Table structure for `product_order`
-- ----------------------------
DROP TABLE IF EXISTS `product_order`;
CREATE TABLE `product_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `customer_phone` varchar(255) DEFAULT NULL,
  `deposite_price` bigint(20) DEFAULT NULL,
  `divide` int(11) DEFAULT NULL,
  `doctor_id` bigint(20) DEFAULT NULL,
  `doctor_phone` varchar(255) DEFAULT NULL,
  `fee1` bigint(20) DEFAULT NULL,
  `fee2` bigint(20) DEFAULT NULL,
  `fee3` bigint(20) DEFAULT NULL,
  `fee4` bigint(20) DEFAULT NULL,
  `fee5` bigint(20) DEFAULT NULL,
  `fee_pay_status1` int(11) DEFAULT NULL,
  `fee_pay_status2` int(11) DEFAULT NULL,
  `fee_pay_status3` int(11) DEFAULT NULL,
  `fee_pay_status4` int(11) DEFAULT NULL,
  `fee_pay_status5` int(11) DEFAULT NULL,
  `fee_update_time1` datetime DEFAULT NULL,
  `fee_update_time2` datetime DEFAULT NULL,
  `fee_update_time3` datetime DEFAULT NULL,
  `fee_update_time4` datetime DEFAULT NULL,
  `fee_update_time5` datetime DEFAULT NULL,
  `market_price` bigint(20) DEFAULT NULL,
  `merchant_address` varchar(255) DEFAULT NULL,
  `merchant_id` bigint(20) DEFAULT NULL,
  `merchant_name` varchar(255) DEFAULT NULL,
  `merchant_phone` varchar(255) DEFAULT NULL,
  `patient_address` varchar(255) DEFAULT NULL,
  `patient_name` varchar(255) DEFAULT NULL,
  `patient_phone` varchar(255) DEFAULT NULL,
  `pay_relative_id` varchar(255) DEFAULT NULL,
  `pay_status` int(11) DEFAULT NULL,
  `pay_time` datetime DEFAULT NULL,
  `pay_type` int(11) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `product_recomm_id` bigint(20) DEFAULT NULL,
  `product_recomm_name` varchar(255) DEFAULT NULL,
  `product_recomm_phone` varchar(255) DEFAULT NULL,
  `recomm_address` varchar(255) DEFAULT NULL,
  `recomm_id` bigint(20) DEFAULT NULL,
  `recomm_name` varchar(255) DEFAULT NULL,
  `recomm_phone` varchar(255) DEFAULT NULL,
  `total_price` bigint(20) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `zip_code` varchar(255) DEFAULT NULL,
  `book_type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of product_order
-- ----------------------------

-- ----------------------------
-- Table structure for `product_ype`
-- ----------------------------
DROP TABLE IF EXISTS `product_ype`;
CREATE TABLE `product_ype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of product_ype
-- ----------------------------

-- ----------------------------
-- Table structure for `relative_medicine_image`
-- ----------------------------
DROP TABLE IF EXISTS `relative_medicine_image`;
CREATE TABLE `relative_medicine_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `image_id` int(11) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `record_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of relative_medicine_image
-- ----------------------------

-- ----------------------------
-- Table structure for `relative_medicine_record`
-- ----------------------------
DROP TABLE IF EXISTS `relative_medicine_record`;
CREATE TABLE `relative_medicine_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `customer_relative` tinyblob,
  `des` varchar(255) DEFAULT NULL,
  `doctor_name` varchar(255) DEFAULT NULL,
  `during` int(11) DEFAULT NULL,
  `image_medicine_box` varchar(255) DEFAULT NULL,
  `image_recipe` varchar(255) DEFAULT NULL,
  `office_name` varchar(255) DEFAULT NULL,
  `relative_id` bigint(20) DEFAULT NULL,
  `relative_name` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of relative_medicine_record
-- ----------------------------

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `checkeds` varchar(255) DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  `role_id` int(11) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for `satisfaction_praise_tread`
-- ----------------------------
DROP TABLE IF EXISTS `satisfaction_praise_tread`;
CREATE TABLE `satisfaction_praise_tread` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_del` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of satisfaction_praise_tread
-- ----------------------------

-- ----------------------------
-- Table structure for `service_desc`
-- ----------------------------
DROP TABLE IF EXISTS `service_desc`;
CREATE TABLE `service_desc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `doctor_count` int(11) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `type_desc` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of service_desc
-- ----------------------------

-- ----------------------------
-- Table structure for `share_money`
-- ----------------------------
DROP TABLE IF EXISTS `share_money`;
CREATE TABLE `share_money` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `customer_phone` varchar(255) DEFAULT NULL,
  `divide` int(11) DEFAULT NULL,
  `fee` bigint(20) DEFAULT NULL,
  `order_number` varchar(255) DEFAULT NULL,
  `order_statues` int(11) DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `share_money` bigint(20) DEFAULT NULL,
  `share_type` int(11) DEFAULT NULL,
  `total_price` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of share_money
-- ----------------------------

-- ----------------------------
-- Table structure for `store`
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  `latitude` varchar(255) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `merchant_id` int(11) DEFAULT NULL,
  `merchant_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `store_phone` varchar(255) DEFAULT NULL,
  `hospital_type_id` bigint(20) DEFAULT NULL,
  `hospital_type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES ('1', '', '', '2017-04-05 10:43:23', '2017-04-05 10:43:23', '嘉里大通中心', '朝阳区', '0', '治疗疾病最好的医院', '39.959048', '116.466392', '1', '北京医院', '北京分院', '北京', '18810435085', null, null);
INSERT INTO `store` VALUES ('2', '', '', '2017-04-05 11:10:32', '2017-04-05 11:10:32', '霞光里', '朝阳区', '0', '方便', '39.957342', '116.459443', '2', '北京博爱医院', '长春博爱医院', '北京', '40000325315', null, null);
INSERT INTO `store` VALUES ('3', '', '', '2017-04-05 14:05:30', '2017-04-05 14:05:30', '三元桥嘉里大通706', '朝阳区', '0', '治疗疾病最好的医院', '39.959895', '116.465313', '4', '北京中西医结合医院', '北京中西医分院', '北京', '18633494252', null, null);
INSERT INTO `store` VALUES ('4', '', '', '2017-04-05 18:01:52', '2017-04-05 18:01:52', '昌平回龙观', '朝阳区', '0', '测试测试', '40.066686', '116.330615', '5', '北京三甲医院', '北京三甲分院', '北京', '18515644716', null, null);

-- ----------------------------
-- Table structure for `system_alipay_refund`
-- ----------------------------
DROP TABLE IF EXISTS `system_alipay_refund`;
CREATE TABLE `system_alipay_refund` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `batch_no` varchar(255) DEFAULT NULL,
  `batch_num` varchar(255) DEFAULT NULL,
  `detail_data` varchar(255) DEFAULT NULL,
  `input_charset` varchar(255) DEFAULT NULL,
  `notify_url` varchar(255) DEFAULT NULL,
  `partner` varchar(255) DEFAULT NULL,
  `refun_date` varchar(255) DEFAULT NULL,
  `result_details` varchar(255) DEFAULT NULL,
  `seller_email` varchar(255) DEFAULT NULL,
  `service` varchar(255) DEFAULT NULL,
  `sign` varchar(255) DEFAULT NULL,
  `sign_type` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `success_num` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of system_alipay_refund
-- ----------------------------

-- ----------------------------
-- Table structure for `takeoutt`
-- ----------------------------
DROP TABLE IF EXISTS `takeoutt`;
CREATE TABLE `takeoutt` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `kqorder_id` varchar(255) DEFAULT NULL,
  `auto_state` int(11) DEFAULT NULL,
  `bank` varchar(255) DEFAULT NULL,
  `bank_no` varchar(255) DEFAULT NULL,
  `bank_open` varchar(255) DEFAULT NULL,
  `chk_time` datetime DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `del_time` datetime DEFAULT NULL,
  `money` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `num` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `real_tx_money` bigint(20) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `sxf` bigint(20) DEFAULT NULL,
  `user_key_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of takeoutt
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `checkeds` varchar(255) DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  `image_code` varchar(255) DEFAULT NULL,
  `login_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '王江涛', '127.0.0.1', '2017-03-21 11:42:50', '2017-04-11 18:18:12', '0', '0', null, '0', '4561', 'admin', '827ccb0eea8a706c4c34a16891f84e7b', null, null);
INSERT INTO `user` VALUES ('2', '王江涛', '127.0.0.1', '2017-03-21 11:43:39', '2017-03-27 09:33:55', '0', '0', null, '0', '7823', 'admin1', '827ccb0eea8a706c4c34a16891f84e7b', null, null);
INSERT INTO `user` VALUES ('3', '王江涛', '127.0.0.1', '2017-03-21 11:44:18', '2017-03-28 14:33:04', '0', '0', null, '0', '6855', 'admin2', '827ccb0eea8a706c4c34a16891f84e7b', null, null);
INSERT INTO `user` VALUES ('4', '腾卉', '127.0.0.1', '2017-03-28 16:35:46', '2017-03-28 16:35:50', '0', '0', null, '0', null, 'adminadmin', '827ccb0eea8a706c4c34a16891f84e7b', null, null);

-- ----------------------------
-- Table structure for `user_comment`
-- ----------------------------
DROP TABLE IF EXISTS `user_comment`;
CREATE TABLE `user_comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `agree` int(11) DEFAULT NULL,
  `comment_date` datetime DEFAULT NULL,
  `comment_for_id` int(11) DEFAULT NULL,
  `comment_for_type` int(11) DEFAULT NULL,
  `customer_comment` varchar(255) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `customer_name` varchar(255) DEFAULT NULL,
  `customer_phone` varchar(255) DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  `disagree` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  `order_by` int(11) DEFAULT NULL,
  `user_comment_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_comment
-- ----------------------------

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `system_role` tinyblob,
  `user_role_id` int(11) NOT NULL,
  `user_table` tinyblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of user_role
-- ----------------------------

-- ----------------------------
-- Table structure for `version`
-- ----------------------------
DROP TABLE IF EXISTS `version`;
CREATE TABLE `version` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `app_name` varchar(255) DEFAULT NULL,
  `des` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `platform` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `version_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of version
-- ----------------------------

-- ----------------------------
-- Table structure for `video_info`
-- ----------------------------
DROP TABLE IF EXISTS `video_info`;
CREATE TABLE `video_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `comment_count` int(11) DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  `image_header` varchar(255) DEFAULT NULL,
  `image_video` varchar(255) DEFAULT NULL,
  `knowlg_tag` tinyblob,
  `name` varchar(255) DEFAULT NULL,
  `short_desc` varchar(255) DEFAULT NULL,
  `str_tag_name` varchar(255) DEFAULT NULL,
  `tag_id` int(11) DEFAULT NULL,
  `view_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of video_info
-- ----------------------------

-- ----------------------------
-- Table structure for `wechat_pay_info`
-- ----------------------------
DROP TABLE IF EXISTS `wechat_pay_info`;
CREATE TABLE `wechat_pay_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_user` varchar(50) DEFAULT NULL,
  `created_ip` varchar(100) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `appid` varchar(255) DEFAULT NULL,
  `attach` varchar(255) DEFAULT NULL,
  `bank_type` varchar(255) DEFAULT NULL,
  `body` varchar(255) DEFAULT NULL,
  `cash_fee` varchar(255) DEFAULT NULL,
  `code_url` varchar(255) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `err_code` varchar(255) DEFAULT NULL,
  `err_code_des` varchar(255) DEFAULT NULL,
  `fee_type` varchar(255) DEFAULT NULL,
  `goods_tag` int(11) NOT NULL,
  `limit_pay` varchar(255) DEFAULT NULL,
  `mch_id` varchar(255) DEFAULT NULL,
  `nonce_str` varchar(255) DEFAULT NULL,
  `notify_url` varchar(255) DEFAULT NULL,
  `openid` varchar(255) DEFAULT NULL,
  `out_trade_no` varchar(255) DEFAULT NULL,
  `prepay_id` varchar(255) DEFAULT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  `result_code` varchar(255) DEFAULT NULL,
  `return_code` varchar(255) DEFAULT NULL,
  `return_msg` varchar(255) DEFAULT NULL,
  `sign` varchar(255) DEFAULT NULL,
  `spbill_create_ip` varchar(255) DEFAULT NULL,
  `time_end` varchar(255) DEFAULT NULL,
  `time_expire` varchar(255) DEFAULT NULL,
  `time_start` varchar(255) DEFAULT NULL,
  `total_fee` varchar(255) DEFAULT NULL,
  `trade_type` varchar(255) DEFAULT NULL,
  `transaction_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of wechat_pay_info
-- ----------------------------
