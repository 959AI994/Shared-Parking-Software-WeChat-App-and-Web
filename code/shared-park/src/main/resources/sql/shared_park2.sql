/*
 Navicat Premium Data Transfer

 Source Server         : mySQL
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : shared_park2

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 26/12/2021 15:07:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for feed_back
-- ----------------------------
DROP TABLE IF EXISTS `feed_back`;
CREATE TABLE `feed_back`  (
  `feedBackId` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id编号',
  `openId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户id',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '反馈内容',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`feedBackId`) USING BTREE,
  INDEX `pk_feed_openid`(`openId`) USING BTREE,
  CONSTRAINT `pk_feed_openid` FOREIGN KEY (`openId`) REFERENCES `normaluser` (`openId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '反馈表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of feed_back
-- ----------------------------
INSERT INTO `feed_back` VALUES (1, 'odxd_5J4XEz8ZgZULdRwYtVkoq7I', '找不到停车位', '2021-12-22 08:22:35');
INSERT INTO `feed_back` VALUES (2, 'odxd_5J4XEz8ZgZULdRwYtVkoq7I', '定位不准确', '2021-12-23 08:23:05');
INSERT INTO `feed_back` VALUES (3, 'odxd_5J4XEz8ZgZULdRwYtVkoq7I', '未能入场', '2021-12-24 08:23:26');

-- ----------------------------
-- Table structure for normaluser
-- ----------------------------
DROP TABLE IF EXISTS `normaluser`;
CREATE TABLE `normaluser`  (
  `openId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '微信用户唯一标识',
  `nickName` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '昵称',
  `realName` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `gender` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '性别',
  `idNumber` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '身份证号',
  `state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话',
  `carNumber` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '车牌号',
  `balance` double(20, 2) NULL DEFAULT NULL COMMENT '余额',
  `isOwner` tinyint(1) NULL DEFAULT NULL COMMENT '是否是业主0不是，1是',
  `avatarUrl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`openId`) USING BTREE,
  INDEX `user_fk_roleId`(`role_id`) USING BTREE,
  CONSTRAINT `user_fk_roleId` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '普通用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of normaluser
-- ----------------------------
INSERT INTO `normaluser` VALUES ('od6555555pwdeACNMiWWgU', '测试1', '咯咯', '0', '450881199812090000', '正常', '18377522951', '蒙FFFFFF', NULL, NULL, NULL, NULL);
INSERT INTO `normaluser` VALUES ('odxd_5ChhxfoLFkXHma4MIWlqqUo', 'kk上学', '梁保锟', '0', '450881199901263833', '正常', '18377522951', '桂K5527G', 482.56, 0, 'https://thirdwx.qlogo.cn/mmopen/vi_32/HWlrmZprFbQDXIxYLk5UoQXmvgDd9hkHoI5dBOnbZjheGjEdeg3nlDPADmHEJ9DjSbVAGH82xOhIdOycrJ2jsg/132', 4);
INSERT INTO `normaluser` VALUES ('odxd_5GK33IIImEevAXZU5wETzyg', '啊碕', '黄振骑', '0', '450881199901273956', '正常', '18377522951', '桂LF6865', 503.56, 0, 'https://thirdwx.qlogo.cn/mmopen/vi_32/kVQoibeRgauHzo89qhTfBUjgf1HbVmJGPGpBnTHFZM2GWXv6QKPUlInxJ6uVibuwmN9toMj65a46UxwVg6afPfmg/132', 4);
INSERT INTO `normaluser` VALUES ('odxd_5J4XEz8ZgZULdRwYtVkoq7I', 'Quattro', '莫槟铭', '0', '450881199810094157', '正常', '17777581901', '桂B119S9', 500.56, 0, 'https://thirdwx.qlogo.cn/mmopen/vi_32/ngzf2xoKZXuIrh8TYFfLFcrcB9TvTosdlzsR3E6MDwhWx6MBXDa53Q5JRMhkwmG30JImTsVEZOewjqQwZ2nwwA/132', 4);
INSERT INTO `normaluser` VALUES ('odxd_5K-c-CyEzlCvIjZkMPn6lAU', 'E02', 'E02', '0', '450881199901263833', '正常', '18377522951', '藏H76777', 497.56, 0, 'https://thirdwx.qlogo.cn/mmopen/vi_32/ffG5wBHkIM3cvNuebBqHSnVUp7eIQN2ib7qpbMlDIb79PPXqRFWjmoVTlxsApavx35gteKV5u442eXMNia10fZNQ/132', 4);
INSERT INTO `normaluser` VALUES ('odxd_5LQXXxgBk732-N16liGbTGs', '热心市民刘先生', '刘志平', '0', '450881199901263836', '正常', '13737540751', '桂K3669C', 521.56, 0, 'https://thirdwx.qlogo.cn/mmopen/vi_32/lNB71eYsgkuib0ic1s4wQD670Cvdiaqt9ZCdBfXyib3B0fUBMKJbnMtqNoibS7ficLqouTKQSnfibGqnCRaEKXw9ItickA/132', 4);
INSERT INTO `normaluser` VALUES ('odxd_5NogANZ3cypwdeACNMiWWgU', '此生&莫相离℡', '刘明旺', '0', '450881199812093866', '正常', '18377522951', '蒙FFFFFF', 497.56, 0, 'https://thirdwx.qlogo.cn/mmopen/vi_32/flk810Qttsr5tBcK64H9r6RiaCZGhXeVfoepa4uTgRibATS3CHmDSE6Pj5qAzxSDwXcqKLGtqNrUPMh8hrYYZHow/132', 4);

-- ----------------------------
-- Table structure for ownerauth
-- ----------------------------
DROP TABLE IF EXISTS `ownerauth`;
CREATE TABLE `ownerauth`  (
  `ownerAuthId` int(11) NOT NULL AUTO_INCREMENT COMMENT '业主编号',
  `openId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '关联用户',
  `ownerName` varchar(14) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '业主名称',
  `gender` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '性别',
  `phone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电话',
  `state` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态',
  `idNumber` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '身份证号',
  `parkingTileNumber` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '车位产权证编号',
  `imgUrl` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '证明图片路径',
  `villageName` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '小区名称',
  `parkingSpaceNumber` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '车位号',
  `notThroughReason` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '认证不通过原因',
  PRIMARY KEY (`ownerAuthId`) USING BTREE,
  INDEX `oforeuserid`(`openId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 64 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '业主信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ownerauth
-- ----------------------------
INSERT INTO `ownerauth` VALUES (56, 'odxd_5LQXXxgBk732-N16liGbTGs', '刘志平', '0', '13737540751', '正常', '450881199901263836', '666666666', 'http://localhost:8888/img/ba02762265e74e3393bd99b2f386fe9c.jpg', '玉林美林人家小区', 'D20', NULL);
INSERT INTO `ownerauth` VALUES (57, 'odxd_5NogANZ3cypwdeACNMiWWgU', '刘明旺', '0', '18377522951', '正常', '450881199812093866', '66666666666666', 'http://localhost:8888/img/fdac9da570b9481aab525167f54b0db9.jpg', '玉林测试小区', 'G102', NULL);
INSERT INTO `ownerauth` VALUES (58, 'odxd_5J4XEz8ZgZULdRwYtVkoq7I', '莫槟铭', '0', '17777581901', '正常', '450881199810094157', '1384685555', 'http://localhost:8888/img/ec87dd6cf72d4103a1756407a910ef40.jpg', '玉林红星美凯小区', 'G11', NULL);
INSERT INTO `ownerauth` VALUES (59, 'odxd_5GK33IIImEevAXZU5wETzyg', '黄振骑', '0', '18377522951', '正常', '450881199901273956', '55655556855', 'http://localhost:8888/img/8a285ec7231d4c658ebfd5a90aaee1a4.jpg', '玉林测试小区', 'L66', NULL);
INSERT INTO `ownerauth` VALUES (63, 'odxd_5ChhxfoLFkXHma4MIWlqqUo', '梁保锟', '0', '18377522951', '正常', '450881199901263833', '2555555', 'http://localhost:8888/img/5e0e09cf98fd4a7693b866b9d3f362ea.jpg', '玉林名人小区', 'Gbb', NULL);

-- ----------------------------
-- Table structure for parking_order
-- ----------------------------
DROP TABLE IF EXISTS `parking_order`;
CREATE TABLE `parking_order`  (
  `orderId` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '预约停车订单号',
  `openId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户标识',
  `parkingSpaceId` int(11) NULL DEFAULT NULL COMMENT '车位归属号',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `state` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '进行中，已完成，取消',
  `approachTime` datetime(0) NULL DEFAULT NULL COMMENT '进场时间',
  `actualPlayingTime` datetime(0) NULL DEFAULT NULL COMMENT '实际出场时间',
  `paymentAmount` float NULL DEFAULT NULL COMMENT '支付金额',
  `paymentTime` datetime(0) NULL DEFAULT NULL COMMENT '支付时间',
  `paymentStatus` varchar(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '支付状态',
  `parkingDuration` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '停车时长',
  PRIMARY KEY (`orderId`) USING BTREE,
  INDEX `fk_psID`(`parkingSpaceId`) USING BTREE,
  INDEX `fk_no`(`openId`) USING BTREE,
  CONSTRAINT `fk_no` FOREIGN KEY (`openId`) REFERENCES `normaluser` (`openId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_psID` FOREIGN KEY (`parkingSpaceId`) REFERENCES `parkingspace` (`parkingSpaceId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '预约停车' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of parking_order
-- ----------------------------
INSERT INTO `parking_order` VALUES ('120211126231208453791', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-11-26 23:12:08', '已完成', '2021-11-26 23:12:30', '2021-11-26 23:29:41', 1.13, '2021-12-19 10:50:18', '已支付', '52分钟');
INSERT INTO `parking_order` VALUES ('120211126232653665792', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-11-26 23:26:54', '已完成', '2021-11-26 23:32:42', '2021-11-27 14:19:19', 6.8, '2021-12-18 22:10:14', '已支付', '56分钟');
INSERT INTO `parking_order` VALUES ('120211211141725714797', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-10-11 14:17:26', '已完成', '2021-12-11 18:10:00', '2021-12-11 22:06:48', 88.65, '2021-12-18 22:14:50', '已支付', '3小时56分');
INSERT INTO `parking_order` VALUES ('120211211191317855793', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-12-11 19:13:18', '已完成', '2021-12-11 19:13:33', '2021-12-11 22:50:13', 28.8, '2021-12-18 22:14:12', '已支付', '3小时36分');
INSERT INTO `parking_order` VALUES ('120211212114342697791', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-12-12 11:43:43', '已完成', '2021-12-12 11:43:55', '2021-12-12 11:43:58', 0, '2021-12-09 10:51:24', '已支付', '0分钟');
INSERT INTO `parking_order` VALUES ('120211212135649176797', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-06-01 13:56:49', '已完成', '2021-12-12 13:56:53', '2021-12-12 15:14:33', 63.44, '2021-12-24 10:51:27', '已支付', '1小时17分');
INSERT INTO `parking_order` VALUES ('120211212154205386797', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-05-13 15:42:05', '已完成', '2021-12-12 15:43:51', '2021-12-12 16:03:57', 55.8, '2021-12-12 16:05:30', '已支付', '20分钟');
INSERT INTO `parking_order` VALUES ('120211219110458389796', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-04-21 11:04:58', '已完成', '2021-12-19 11:12:31', '2021-12-19 11:12:46', 93.55, '2021-12-19 11:14:09', '已支付', '10分钟');
INSERT INTO `parking_order` VALUES ('120211219111818099976', 'odxd_5LQXXxgBk732-N16liGbTGs', 32, '2021-06-01 11:18:18', '已完成', '2021-12-19 11:21:32', '2021-12-19 11:30:54', 3, '2021-12-19 11:31:36', '已支付', '9分钟');
INSERT INTO `parking_order` VALUES ('120211219111818144445', 'odxd_5LQXXxgBk732-N16liGbTGs', 31, '2021-11-19 11:18:18', '已完成', '2021-12-19 11:21:32', '2021-12-19 11:30:54', 3, '2021-12-19 11:31:36', '已支付', '9分钟');
INSERT INTO `parking_order` VALUES ('120211219111818194795', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-12-19 11:18:18', '已完成', '2021-12-19 11:21:32', '2021-12-19 11:30:54', 3, '2021-12-19 11:31:36', '已支付', '9分钟');
INSERT INTO `parking_order` VALUES ('120211219111818594795', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-12-19 11:18:18', '已完成', '2021-12-19 11:21:32', '2021-12-19 11:30:54', 3, '2021-12-19 11:31:36', '已支付', '9分钟');
INSERT INTO `parking_order` VALUES ('120211219113144277791', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-03-01 11:31:44', '已完成', '2021-12-19 11:31:48', '2021-12-19 11:31:54', 66, '2021-12-19 11:32:18', '已支付', '11分');
INSERT INTO `parking_order` VALUES ('120211219114159318797', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-02-01 11:41:59', '已完成', '2021-12-19 11:42:09', '2021-12-19 11:42:12', 89.66, '2021-12-19 11:49:22', '已支付', '10分钟');
INSERT INTO `parking_order` VALUES ('120211219114929698792', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-01-01 11:49:30', '已完成', '2021-12-19 11:49:33', '2021-12-19 11:49:35', 126.66, '2021-12-19 11:51:06', '已支付', '1分钟');
INSERT INTO `parking_order` VALUES ('120211219115113736794', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-12-19 11:51:14', '已完成', '2021-12-19 11:51:17', '2021-12-19 11:51:21', 3, '2021-12-19 11:51:30', '已支付', '3秒');
INSERT INTO `parking_order` VALUES ('120211219204121819795', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-03-01 20:41:22', '已完成', '2021-12-19 20:52:33', '2021-12-19 22:05:15', 10.2, '2021-12-19 22:53:44', '已支付', '1小时12分');
INSERT INTO `parking_order` VALUES ('120211219230826525792', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-03-01 23:08:27', '已完成', '2021-12-19 23:09:04', '2021-12-19 23:21:19', 1.7, '2021-12-19 23:21:52', '已支付', '12分钟');
INSERT INTO `parking_order` VALUES ('120211219232709021333', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-09-09 23:27:09', '已完成', '2021-12-19 23:27:18', '2021-12-19 23:27:50', 209.65, '2021-12-19 23:29:12', '已支付', '32秒');
INSERT INTO `parking_order` VALUES ('120211219232709021334', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-08-13 23:27:09', '已完成', '2021-12-19 23:27:18', '2021-12-19 23:27:50', 216.22, '2021-12-19 23:29:12', '已支付', '32秒');
INSERT INTO `parking_order` VALUES ('120211219232709021793', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-12-19 23:27:09', '已完成', '2021-12-19 23:27:18', '2021-12-19 23:27:50', 3, '2021-12-19 23:29:12', '已支付', '32秒');
INSERT INTO `parking_order` VALUES ('120211219232790213355', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-07-13 23:27:09', '已完成', '2021-12-19 23:27:18', '2021-12-19 23:27:50', 120.65, '2021-12-19 23:29:12', '已支付', '32秒');
INSERT INTO `parking_order` VALUES ('120211223171830176798', 'odxd_5NogANZ3cypwdeACNMiWWgU', 29, '2021-12-23 17:18:30', '已完成', '2021-12-23 17:20:24', '2021-12-23 17:20:36', 3, '2021-12-23 17:30:39', '已支付', '12秒');
INSERT INTO `parking_order` VALUES ('120211223173420558792', 'odxd_5NogANZ3cypwdeACNMiWWgU', 29, '2021-12-23 17:34:21', '已完成', '2021-12-25 17:35:06', '2021-12-26 17:35:42', 35, '2021-12-23 17:35:46', '已支付', '1分钟');
INSERT INTO `parking_order` VALUES ('120211223174623145791', 'odxd_5NogANZ3cypwdeACNMiWWgU', 29, '2021-12-23 17:46:23', '已完成', '2021-12-23 17:46:48', '2021-12-23 17:47:21', 3, '2021-12-23 17:47:25', '已支付', '32秒');
INSERT INTO `parking_order` VALUES ('120211223174731272794', 'odxd_5NogANZ3cypwdeACNMiWWgU', 29, '2021-12-23 17:47:31', '进行中', '2021-12-23 17:47:52', '2021-12-23 18:15:38', 2.25, '2021-12-24 18:15:59', '已支付', '27分钟');
INSERT INTO `parking_order` VALUES ('120211223193026928796', 'odxd_5J4XEz8ZgZULdRwYtVkoq7I', 29, '2021-12-23 19:30:27', '进行中', '2021-12-23 19:31:30', '2021-12-31 19:33:33', 6.8, NULL, '未支付', '2小时');
INSERT INTO `parking_order` VALUES ('120211224021932858796', 'odxd_5ChhxfoLFkXHma4MIWlqqUo', 30, '2021-12-24 02:19:33', '已完成', '2021-12-24 02:20:57', '2021-12-24 02:21:24', 3, '2021-12-24 02:21:29', '已支付', '26秒');
INSERT INTO `parking_order` VALUES ('120211224092847999794', 'odxd_5ChhxfoLFkXHma4MIWlqqUo', 29, '2021-12-24 09:28:48', '已完成', '2021-12-24 09:29:44', '2021-12-24 09:30:04', 3, '2021-12-24 09:30:13', '已支付', '19秒');
INSERT INTO `parking_order` VALUES ('120211224093033855794', 'odxd_5ChhxfoLFkXHma4MIWlqqUo', 29, '2021-12-24 09:30:34', '已完成', '2021-12-24 09:31:08', '2021-12-24 09:31:12', 3, '2021-12-24 09:31:24', '已支付', '4秒');
INSERT INTO `parking_order` VALUES ('120211224093150075793', 'odxd_5ChhxfoLFkXHma4MIWlqqUo', 29, '2021-12-24 09:31:50', '已完成', '2021-12-24 09:41:54', '2021-12-24 09:42:12', 3, '2021-12-24 09:48:13', '已支付', '17秒');
INSERT INTO `parking_order` VALUES ('120211224094915289791', 'odxd_5ChhxfoLFkXHma4MIWlqqUo', 32, '2021-12-24 09:49:15', '已完成', '2021-12-24 09:50:27', '2021-12-24 09:50:42', 3, '2021-12-24 09:50:51', '已支付', '15秒');
INSERT INTO `parking_order` VALUES ('120211224100254283798', 'odxd_5K-c-CyEzlCvIjZkMPn6lAU', 29, '2021-12-24 10:02:54', '已完成', '2021-12-24 10:05:12', '2021-12-24 10:05:48', 3, '2021-12-24 10:06:31', '已支付', '36秒');
INSERT INTO `parking_order` VALUES ('120211224113417867794', 'odxd_5ChhxfoLFkXHma4MIWlqqUo', 30, '2021-12-24 11:34:18', '已完成', '2021-12-24 11:35:26', '2021-12-24 11:36:38', 3, '2021-12-24 11:37:01', '已支付', '1分钟');
INSERT INTO `parking_order` VALUES ('120211299111818099976', 'odxd_5LQXXxgBk732-N16liGbTGs', 32, '2021-06-25 11:18:18', '已完成', '2021-12-19 11:21:32', '2021-12-19 11:30:54', 3, '2021-12-19 11:31:36', '已支付', '9分钟');
INSERT INTO `parking_order` VALUES ('123211126231208453791', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-11-26 23:12:08', '已完成', '2021-11-26 23:12:30', '2021-11-26 23:29:41', 1.13, '2021-12-19 10:50:18', '已支付', '52分钟');
INSERT INTO `parking_order` VALUES ('123211126232653665792', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-11-26 23:26:54', '已完成', '2021-11-26 23:32:42', '2021-11-27 14:19:19', 6.8, '2021-12-18 22:10:14', '已支付', '56分钟');
INSERT INTO `parking_order` VALUES ('123211211141725714797', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-10-11 14:17:26', '已完成', '2021-12-11 18:10:00', '2021-12-11 22:06:48', 88.65, '2021-12-18 22:14:50', '已支付', '3小时56分');
INSERT INTO `parking_order` VALUES ('123211211191317855793', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-10-01 19:13:18', '已完成', '2021-12-11 19:13:33', '2021-12-11 22:50:13', 28.8, '2021-12-18 22:14:12', '已支付', '3小时36分');
INSERT INTO `parking_order` VALUES ('123211212114342697791', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-11-12 11:43:43', '已完成', '2021-02-12 11:43:55', '2021-12-12 11:43:58', 0, '2021-12-09 10:51:24', '已支付', '0分钟');
INSERT INTO `parking_order` VALUES ('123211212135649176797', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-06-01 13:56:49', '已完成', '2021-12-12 13:56:53', '2021-12-12 15:14:33', 63.44, '2021-12-24 10:51:27', '已支付', '1小时17分');
INSERT INTO `parking_order` VALUES ('123211212154205386797', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-05-13 15:42:05', '已完成', '2021-12-12 15:43:51', '2021-12-12 16:03:57', 55.8, '2021-12-12 16:05:30', '已支付', '20分钟');
INSERT INTO `parking_order` VALUES ('123211219110458389796', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-04-21 11:04:58', '已完成', '2021-12-19 11:12:31', '2021-12-19 11:12:46', 93.55, '2021-12-19 11:14:09', '已支付', '10分钟');
INSERT INTO `parking_order` VALUES ('123211219111818194795', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-12-19 11:18:18', '已完成', '2021-10-19 11:21:32', '2021-12-19 11:30:54', 3, '2021-12-19 11:31:36', '已支付', '9分钟');
INSERT INTO `parking_order` VALUES ('123211219114159318797', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-02-01 11:41:59', '已完成', '2021-12-19 11:42:09', '2021-12-19 11:42:12', 89.66, '2021-12-19 11:49:22', '已支付', '10分钟');
INSERT INTO `parking_order` VALUES ('123211219114929698792', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-01-01 11:49:30', '已完成', '2021-11-19 11:49:33', '2021-12-19 11:49:35', 126.66, '2021-12-19 11:51:06', '已支付', '1分钟');
INSERT INTO `parking_order` VALUES ('123211219115113736794', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-07-01 11:51:14', '已完成', '2021-12-19 11:51:17', '2021-12-19 11:51:21', 3, '2021-12-19 11:51:30', '已支付', '3秒');
INSERT INTO `parking_order` VALUES ('123211219204121819795', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-07-02 11:51:14', '已完成', '2021-12-19 20:52:33', '2021-12-19 22:05:15', 10.2, '2021-12-19 22:53:44', '已支付', '1小时12分');
INSERT INTO `parking_order` VALUES ('123211219230826525792', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-07-29 23:08:27', '已完成', '2021-12-19 23:09:04', '2021-12-19 23:21:19', 1.7, '2021-12-19 23:21:52', '已支付', '12分钟');
INSERT INTO `parking_order` VALUES ('123211219232709021333', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-09-09 23:27:09', '已完成', '2021-09-19 23:27:18', '2021-12-19 23:27:50', 209.65, '2021-12-19 23:29:12', '已支付', '32秒');
INSERT INTO `parking_order` VALUES ('123211219232709021334', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-08-13 23:27:09', '已完成', '2021-10-19 23:27:18', '2021-12-19 23:27:50', 216.22, '2021-12-19 23:29:12', '已支付', '32秒');
INSERT INTO `parking_order` VALUES ('123211219232709021793', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-10-01 23:27:09', '已完成', '2021-08-19 23:27:18', '2021-12-19 23:27:50', 3, '2021-12-19 23:29:12', '已支付', '32秒');
INSERT INTO `parking_order` VALUES ('123211219232790213355', 'odxd_5LQXXxgBk732-N16liGbTGs', 29, '2021-07-13 23:27:09', '已完成', '2021-09-19 23:27:18', '2021-12-19 23:27:50', 120.65, '2021-12-19 23:29:12', '已支付', '32秒');
INSERT INTO `parking_order` VALUES ('555555519111867776664', 'odxd_5LQXXxgBk732-N16liGbTGs', 31, '2021-06-01 11:18:18', '已完成', '2021-12-19 11:21:32', '2021-12-19 11:30:54', 3, '2021-12-19 11:31:36', '已支付', '9分钟');

-- ----------------------------
-- Table structure for parkinginfo
-- ----------------------------
DROP TABLE IF EXISTS `parkinginfo`;
CREATE TABLE `parkinginfo`  (
  `parkingInfoId` int(11) NOT NULL AUTO_INCREMENT COMMENT '停车场编号',
  `villageName` varchar(18) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '小区名称',
  `province` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '市',
  `area` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区',
  `address` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '地址',
  `state` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '联系电话',
  `longitude` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '经度',
  `latitude` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '纬度',
  PRIMARY KEY (`parkingInfoId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '小区停车场表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of parkinginfo
-- ----------------------------
INSERT INTO `parkinginfo` VALUES (1, '玉林美林人家小区', '广西壮族自治区', '玉林市', '玉州区', '美林人家小区人民路108号', '正常', '18377522951', '110.235227', '22.164114');
INSERT INTO `parkinginfo` VALUES (2, '玉林测试小区', '广西壮族自治区', '玉林市', '玉州区', '医药大厦1201号', '正常', '18377722944', '110.150833', '22.625741');
INSERT INTO `parkinginfo` VALUES (3, '玉林名人小区', '广西壮族自治区', '玉林市', '玉州区', '名人酒店大厦明暗街道1201', '正常', '18887522953', '110.151088', '22.628062');
INSERT INTO `parkinginfo` VALUES (4, '玉林红星美凯小区', '广西壮族自治区', '玉林市', '玉州区', '红星美凯龙10号', '正常', '18377522952', '110.162499', '22.660174');
INSERT INTO `parkinginfo` VALUES (5, '玉林超越小区', '广西壮族自治区', '玉林市', '玉州区', '耳环106号环106号', '正常', '18377522950', '110.151088', '22.628062');
INSERT INTO `parkinginfo` VALUES (6, '长窝小脚区', '广西壮族自治区', '玉林市', '玉州区', '长窝脚小区耳环106号', '正常', '18377522951', '110.151088', '22.628062');
INSERT INTO `parkinginfo` VALUES (17, '桂平测试小区', '广西壮族自治区', '贵港市', '桂平市', '桂平市第五中学', '正常', '18377522957', '110.083961', '23.341915');
INSERT INTO `parkinginfo` VALUES (27, '荔湾小区花园', '广西壮族自治区', '玉林市', '玉州区', '玉东大道61号金桂丽湾小区', '正常', '18377522951', '110.185409', '22.661849');
INSERT INTO `parkinginfo` VALUES (28, '桂平市现代人家', '广西壮族自治区', '贵港市', '桂平市', '郁江东路现代人家', '正常', '18377522954', '110.079729', '23.368700');
INSERT INTO `parkinginfo` VALUES (29, '玉林鸿运家园', '广西壮族自治区', '玉林市', '玉州区', '禾力街1正西方向190米', '正常', '18377542147', '110.156392', '22.607589');
INSERT INTO `parkinginfo` VALUES (30, '玉林容县测试小区', '广西壮族自治区', '玉林市', '容县', '容县小学', '正常', '18371115555', '110.557874', '22.857823');
INSERT INTO `parkinginfo` VALUES (31, '玉林奥利华园', '广西壮族自治区', '玉林市', '玉州区', '江南路233号', '正常', '13878552845', '110.168531', '22.609908');

-- ----------------------------
-- Table structure for parkingspace
-- ----------------------------
DROP TABLE IF EXISTS `parkingspace`;
CREATE TABLE `parkingspace`  (
  `parkingSpaceId` int(11) NOT NULL AUTO_INCREMENT COMMENT '车位归属编号',
  `parkingSpaceNumber` varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '车位号',
  `parkingInfoId` int(11) NULL DEFAULT NULL COMMENT '停车场编号',
  `ownerAuthId` int(11) NULL DEFAULT NULL COMMENT '归属业主',
  `state` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '共享中，未共享，禁用',
  `isTakeUp` int(8) NOT NULL COMMENT '是否占用，1：占用，0：未占用',
  `price` float(10, 2) NULL DEFAULT NULL COMMENT '共享价格',
  `shareStartTime` datetime(0) NULL DEFAULT NULL COMMENT '开始共享时间',
  `shareEndTime` datetime(0) NULL DEFAULT NULL COMMENT '结束共享时间',
  PRIMARY KEY (`parkingSpaceId`) USING BTREE,
  INDEX `fk_pk`(`parkingInfoId`) USING BTREE,
  INDEX `fk_woer`(`ownerAuthId`) USING BTREE,
  CONSTRAINT `fk_pk` FOREIGN KEY (`parkingInfoId`) REFERENCES `parkinginfo` (`parkingInfoId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_woer` FOREIGN KEY (`ownerAuthId`) REFERENCES `ownerauth` (`ownerAuthId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '车位表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of parkingspace
-- ----------------------------
INSERT INTO `parkingspace` VALUES (29, 'D20', 1, 56, '未共享', 0, 5.00, '2021-12-24 01:00:00', '2021-12-25 02:00:00');
INSERT INTO `parkingspace` VALUES (30, 'G102', 2, 57, '共享中', 0, 9.00, '2021-12-24 00:00:00', '2022-01-03 05:00:00');
INSERT INTO `parkingspace` VALUES (31, 'G11', 4, 58, '未共享', 1, 7.00, '2021-12-24 07:32:46', '2021-12-26 19:32:46');
INSERT INTO `parkingspace` VALUES (32, 'L66', 2, 59, '未共享', 0, 5.00, '2021-12-23 09:56:51', '2021-12-25 19:56:51');
INSERT INTO `parkingspace` VALUES (36, 'Gbb', 3, 63, '未共享', 0, 5.00, '2021-12-24 20:00:00', '2021-12-26 00:00:00');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单path',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `component` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '组件地址',
  `redirect` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '面包屑中重定向地址',
  `perms` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '菜单管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 0, '/parking', '小区停车场管理目录', 'Layout', '/', NULL, 0, 'el-icon-location-information', 0);
INSERT INTO `sys_menu` VALUES (2, 0, '/parkingSpace', '车位管理目录', 'Layout', '/', NULL, 0, 'example', 1);
INSERT INTO `sys_menu` VALUES (3, 0, '/system', '系统管理目录', 'Layout', '/', NULL, 0, 'table', 2);
INSERT INTO `sys_menu` VALUES (4, 0, '/sys/menu', '菜单管理', 'Layout', '/', NULL, 0, 'table', 3);
INSERT INTO `sys_menu` VALUES (5, 0, '/localhost:8080/renrenl', 'SQL监控', NULL, '/', NULL, 0, 'table', 4);
INSERT INTO `sys_menu` VALUES (6, 3, '/user', '微信用户管理', '/system/user/userList', NULL, NULL, 2, 'table', 5);
INSERT INTO `sys_menu` VALUES (7, 1, '/parkingList', '小区停车场管理', '/parking/parkingList', '/parking/parkingList', 'sys:schedule:list,sys:schedule:info', 2, 'el-icon-location-information', 6);
INSERT INTO `sys_menu` VALUES (8, 2, '/parkingSpaceList', '车位管理', '/parkingSpace/parkingSpaceList', '/parkingSpace/parkingSpaceList', 'sys:schedule:save', 2, 'el-icon-bank-card', 7);
INSERT INTO `sys_menu` VALUES (9, 0, '/order', '订单管理目录', 'Layout', '/', '', 0, NULL, 9);
INSERT INTO `sys_menu` VALUES (10, 9, '/order/orderList', '共享预约订单管理', '/order/orderList', NULL, '', 2, 'el-icon-notebook-1', 0);
INSERT INTO `sys_menu` VALUES (11, 0, '/system', '系统管理目录', 'Layout', '/', '', 0, 'table', 0);
INSERT INTO `sys_menu` VALUES (12, 11, '/system/user', '微信用户管理', '/system/user/userList', '/system/user', '', 2, 'table', 0);
INSERT INTO `sys_menu` VALUES (13, 0, '/owner', '业主管理目录', 'Layout', '/', '', 0, 'el-icon-burger', 8);
INSERT INTO `sys_menu` VALUES (14, 13, '/owner/ownerList', '业主管理', '/owner/ownerList', '/ownerList', '', 2, 'el-icon-place', 0);
INSERT INTO `sys_menu` VALUES (15, 0, '/feedback', '用户反馈目录', 'Layout', '/', '', 0, 'el-icon-thumb', 11);
INSERT INTO `sys_menu` VALUES (16, 15, '/feedback/feedbackList', '用户反馈管理', '/feedback/feedbackList', '/', NULL, 2, 'el-icon-thumb', 0);
INSERT INTO `sys_menu` VALUES (17, 0, '/orderChart', '图表目录', 'Layout', '/', '', 0, 'el-icon-pie-chart', 10);
INSERT INTO `sys_menu` VALUES (18, 17, '/orderChartList', '财务报表查看', '/financialChart/index', '/', NULL, 2, 'el-icon-pie-chart', 0);
INSERT INTO `sys_menu` VALUES (19, 0, '/odiscernCar', '车辆识别', 'Layout', '/', '', 0, 'el-icon-s-flag\r\n', 12);
INSERT INTO `sys_menu` VALUES (20, 19, '/odiscernCar/index', '确认车辆出入', '/discernCar/index', '/discernCar/index', NULL, 2, 'el-icon-s-flag\r\n', 0);
INSERT INTO `sys_menu` VALUES (21, 0, '/orderSee', '订单管理查看目录', 'Layout', '/', '', 0, 'el-icon-notebook-1', 10);
INSERT INTO `sys_menu` VALUES (22, 21, '/order/oorderSee', '共享预约订单查看', '/order/orderSee', '/order/oorderSee', '', 2, 'el-icon-notebook-1', 0);
INSERT INTO `sys_menu` VALUES (23, 0, '/systey', '系统菜单目录', 'Layout', '/', '', 0, 'el-icon-notebook-1', 0);
INSERT INTO `sys_menu` VALUES (24, 23, '/sys/menu', '系统菜单管理', '/sys/menu', NULL, NULL, 2, 'el-icon-notebook-1', 0);
INSERT INTO `sys_menu` VALUES (25, 0, '/sysUser', '系统用户目录', 'Layout', '/', '', 0, 'el-icon-notebook-1', 0);
INSERT INTO `sys_menu` VALUES (26, 25, '/sys/user', '系统用户管理', '/sys/user', '/sys/user', '', 2, 'el-icon-user-solid\r\n', 0);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色名称',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '系统管理员', NULL, NULL, '2021-12-08 12:14:58');
INSERT INTO `sys_role` VALUES (2, '后台管理员', NULL, NULL, '2021-12-08 12:15:02');
INSERT INTO `sys_role` VALUES (3, '出入记录核实员', NULL, NULL, '2021-12-08 12:14:55');
INSERT INTO `sys_role` VALUES (4, '微信用户', '手机用户', NULL, '2021-12-08 12:14:49');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `s_r_pk_rid`(`role_id`) USING BTREE,
  INDEX `s_r_pk_mId`(`menu_id`) USING BTREE,
  CONSTRAINT `s_r_pk_mId` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`menu_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `s_r_pk_rid` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色与菜单对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1, 1, 3);
INSERT INTO `sys_role_menu` VALUES (2, 1, 6);
INSERT INTO `sys_role_menu` VALUES (3, 2, 7);
INSERT INTO `sys_role_menu` VALUES (4, 2, 8);
INSERT INTO `sys_role_menu` VALUES (5, 2, 1);
INSERT INTO `sys_role_menu` VALUES (6, 2, 2);
INSERT INTO `sys_role_menu` VALUES (7, 2, 9);
INSERT INTO `sys_role_menu` VALUES (8, 2, 10);
INSERT INTO `sys_role_menu` VALUES (9, 3, 21);
INSERT INTO `sys_role_menu` VALUES (10, 3, 22);
INSERT INTO `sys_role_menu` VALUES (11, 2, 12);
INSERT INTO `sys_role_menu` VALUES (12, 2, 13);
INSERT INTO `sys_role_menu` VALUES (13, 2, 15);
INSERT INTO `sys_role_menu` VALUES (14, 2, 16);
INSERT INTO `sys_role_menu` VALUES (15, 2, 17);
INSERT INTO `sys_role_menu` VALUES (16, 2, 18);
INSERT INTO `sys_role_menu` VALUES (17, 3, 19);
INSERT INTO `sys_role_menu` VALUES (18, 3, 20);
INSERT INTO `sys_role_menu` VALUES (19, 2, 14);
INSERT INTO `sys_role_menu` VALUES (21, 1, 23);
INSERT INTO `sys_role_menu` VALUES (22, 1, 24);
INSERT INTO `sys_role_menu` VALUES (23, 1, 25);
INSERT INTO `sys_role_menu` VALUES (24, 1, 26);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '盐',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) NULL DEFAULT NULL COMMENT '创建者ID',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '系统用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'systemAdmin', '38ccc98b4c4661d444f86977cd6da09a52baa73b7be7a6542fb44f10ca3bc985', 'XfeHPX26CmRVggDPm34c', 'root@renren.io', '13612345678', 1, 1, '2016-11-11 11:11:11');
INSERT INTO `sys_user` VALUES (2, 'admin', '38ccc98b4c4661d444f86977cd6da09a52baa73b7be7a6542fb44f10ca3bc985', 'XfeHPX26CmRVggDPm34c', '2305975309@qq.com', '18377522951', 1, NULL, '2021-12-07 12:40:09');
INSERT INTO `sys_user` VALUES (3, 'operator', '38ccc98b4c4661d444f86977cd6da09a52baa73b7be7a6542fb44f10ca3bc985', 'XfeHPX26CmRVggDPm34c', '451686854509@qq.com', '17777777777', 1, NULL, '2021-12-10 19:38:24');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `pk_uid`(`user_id`) USING BTREE,
  INDEX `pk_rid`(`role_id`) USING BTREE,
  CONSTRAINT `pk_rid` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `pk_uid` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户与角色对应关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2, 2);
INSERT INTO `sys_user_role` VALUES (3, 3, 3);

SET FOREIGN_KEY_CHECKS = 1;
