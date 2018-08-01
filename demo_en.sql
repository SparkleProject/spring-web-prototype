/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : demo

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 21/07/2018 17:49:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_code
-- ----------------------------
DROP TABLE IF EXISTS `t_code`;
CREATE TABLE `t_code`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `type` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `code` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `seq` int(10) NULL DEFAULT NULL COMMENT '˳',
  `enable` tinyint(1) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_code_unique`(`type`, `code`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = 'SYSTEM REFERENCE CODE' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_code
-- ----------------------------
INSERT INTO `t_code` VALUES (50, '7', 'TEST_CODE_1', 'TEST', 'test', 1, 1, '');

-- ----------------------------
-- Table structure for t_code_type
-- ----------------------------
DROP TABLE IF EXISTS `t_code_type`;
CREATE TABLE `t_code_type`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `code` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'CODE',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `seq` int(10) NULL DEFAULT NULL COMMENT '˳',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'DESCRIPTION',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = 'SYSTEM CODE TYPE' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_code_type
-- ----------------------------
INSERT INTO `t_code_type` VALUES (7, 'TEST_CODE', 'TEST', NULL, '');

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'UUID',
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'DEPARTMENT NAME',
  `pid` int(10) NULL DEFAULT NULL COMMENT 'PARENT DEPT ID',
  `seq` int(10) NULL DEFAULT 0 COMMENT 'SIBLING DEPT ORDER',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'DEPARTMENT DESC',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = 'SYS DEPARTMENT' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES (5, 'Department 1-1', 1, NULL, 'dddddaa');
INSERT INTO `t_department` VALUES (20, 'Department', 0, 0, '');

-- ----------------------------
-- Table structure for t_function
-- ----------------------------
DROP TABLE IF EXISTS `t_function`;
CREATE TABLE `t_function`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'UUID',
  `code` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'FUNCTION CODE',
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'FUNCTION NAME',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'FUNCTION DESC',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = 'SYS FUNCTION' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_function
-- ----------------------------
INSERT INTO `t_function` VALUES (5, 'admin:staff:add', 'New', 'New');
INSERT INTO `t_function` VALUES (4, 'admin:staff:delete', 'Delete', 'Delete');

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime(0) NULL DEFAULT NULL,
  `logger` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `priority` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` int(4) NOT NULL AUTO_INCREMENT COMMENT 'UUID',
  `pid` int(4) NOT NULL COMMENT 'PARENT MENU',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'MENU NAME',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'MENU LINK',
  `target` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'TARGET',
  `relative` tinyint(1) NULL DEFAULT NULL COMMENT 'IS RELATIVE URL，0－NO，1－YES',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'MENU ICON URL',
  `seq` int(10) UNSIGNED NULL DEFAULT 0 COMMENT 'SIBLING MENU ORDER',
  `enable` tinyint(1) NULL DEFAULT NULL COMMENT 'IS ACTIVATE，0－NO，1－YES',
  `visible` tinyint(1) NULL DEFAULT NULL COMMENT 'IS VISIBLE，0－NO，1－YES',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 49 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = 'SYS MENU' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, 0, 'Base Info', '', '', NULL, NULL, 49, 0, NULL);
INSERT INTO `t_menu` VALUES (2, 0, 'SYS Admin', '', NULL, NULL, NULL, 50, 1, NULL);
INSERT INTO `t_menu` VALUES (4, 2, 'Login Info', '/admin/staff-login', NULL, NULL, NULL, 0, 1, NULL);
INSERT INTO `t_menu` VALUES (6, 1, 'Administrator', '/admin/staff', '', NULL, '', 1, 1, NULL);
INSERT INTO `t_menu` VALUES (7, 1, 'Department', '/admin/department', NULL, NULL, NULL, 2, 1, NULL);
INSERT INTO `t_menu` VALUES (8, 2, 'Role', '/admin/role', NULL, NULL, NULL, 3, 1, NULL);
INSERT INTO `t_menu` VALUES (9, 2, 'Menu', '/admin/menu', NULL, NULL, NULL, 4, 1, NULL);
INSERT INTO `t_menu` VALUES (10, 2, 'Feature', '/admin/function', NULL, NULL, NULL, 5, 1, NULL);
INSERT INTO `t_menu` VALUES (11, 2, 'SYS Code', '/admin/code', '', NULL, '', 6, 1, NULL);
INSERT INTO `t_menu` VALUES (12, 2, 'Authorization', '/admin/role-staff', NULL, NULL, NULL, 1, 1, NULL);
INSERT INTO `t_menu` VALUES (16, 2, 'Scheduled Tasks', '/admin/task', '_self', NULL, NULL, 14, 1, NULL);
INSERT INTO `t_menu` VALUES (17, 2, 'Monintor', '/druid/index.html', '_blank', NULL, '', 90, 1, NULL);
INSERT INTO `t_menu` VALUES (27, 0, 'Management', '', '_self', NULL, '', 0, 1, NULL);
INSERT INTO `t_menu` VALUES (48, 27, 'Users', '/admin/user', '_self', NULL, '', 1, 1, NULL);
INSERT INTO `t_menu` VALUES (31, 32, 'Home', '/user/index', '_self', NULL, 'icon-home4', 0, 1, NULL);
INSERT INTO `t_menu` VALUES (32, 27, 'User Menu', '/user/withdraw', '_self', NULL, '', 100, 1, NULL);
INSERT INTO `t_menu` VALUES (33, 32, 'Change Password', '/user/password', '_self', NULL, 'icon-pencil3', 9, 1, NULL);
INSERT INTO `t_menu` VALUES (34, 32, 'Log Out', '/user/logout', '_self', NULL, 'icon-switch2', 10, 1, NULL);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'UUID',
  `code` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'ROLE CODE',
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'ROLE NAME',
  `description` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'ROLE DESC',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = 'SYS ROLE' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'ROLE_ADMIN', 'Administrator', 'Adminstrator');
INSERT INTO `t_role` VALUES (8, 'ROLE_USER', 'User', 'User');

-- ----------------------------
-- Table structure for t_role_res
-- ----------------------------
DROP TABLE IF EXISTS `t_role_res`;
CREATE TABLE `t_role_res`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'UUID',
  `role_id` int(10) NULL DEFAULT NULL COMMENT 'ROLE ID',
  `res_type` int(1) NULL DEFAULT NULL COMMENT 'RESOURCE TYPE，1－MENU，2－FUNCTION',
  `res_id` int(10) NULL DEFAULT NULL COMMENT 'RESOURCE ID: MENU ID OR FUNCTION ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 456 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = 'SYS ROLE RESOUCE' ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of t_role_res
-- ----------------------------
INSERT INTO `t_role_res` VALUES (455, 8, 1, 34);
INSERT INTO `t_role_res` VALUES (23, 1, 2, 5);
INSERT INTO `t_role_res` VALUES (452, 1, 1, 17);
INSERT INTO `t_role_res` VALUES (451, 1, 1, 16);
INSERT INTO `t_role_res` VALUES (450, 1, 1, 11);
INSERT INTO `t_role_res` VALUES (449, 1, 1, 10);
INSERT INTO `t_role_res` VALUES (448, 1, 1, 9);
INSERT INTO `t_role_res` VALUES (447, 1, 1, 8);
INSERT INTO `t_role_res` VALUES (446, 1, 1, 12);
INSERT INTO `t_role_res` VALUES (445, 1, 1, 4);
INSERT INTO `t_role_res` VALUES (454, 8, 1, 33);
INSERT INTO `t_role_res` VALUES (444, 1, 1, 2);
INSERT INTO `t_role_res` VALUES (443, 1, 1, 7);
INSERT INTO `t_role_res` VALUES (453, 8, 1, 31);
INSERT INTO `t_role_res` VALUES (442, 1, 1, 6);
INSERT INTO `t_role_res` VALUES (441, 1, 1, 1);
INSERT INTO `t_role_res` VALUES (440, 1, 1, 47);
INSERT INTO `t_role_res` VALUES (439, 1, 1, 48);

-- ----------------------------
-- Table structure for t_role_staff
-- ----------------------------
DROP TABLE IF EXISTS `t_role_staff`;
CREATE TABLE `t_role_staff`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'UUID',
  `role_id` int(10) NULL DEFAULT NULL COMMENT 'ROLE ID',
  `staff_id` int(10) NULL DEFAULT NULL COMMENT 'STAFF ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = 'SYS ROLES OF STUFF' ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of t_role_staff
-- ----------------------------
INSERT INTO `t_role_staff` VALUES (9, 1, 4);
INSERT INTO `t_role_staff` VALUES (10, 2, 4);
INSERT INTO `t_role_staff` VALUES (11, 4, 4);
INSERT INTO `t_role_staff` VALUES (21, 7, 2);
INSERT INTO `t_role_staff` VALUES (19, 1, 1);
INSERT INTO `t_role_staff` VALUES (23, 8, 118);
INSERT INTO `t_role_staff` VALUES (24, 8, 119);
INSERT INTO `t_role_staff` VALUES (25, 8, 121);
INSERT INTO `t_role_staff` VALUES (26, 8, 122);
INSERT INTO `t_role_staff` VALUES (27, 8, 123);
INSERT INTO `t_role_staff` VALUES (28, 8, 124);
INSERT INTO `t_role_staff` VALUES (29, 8, 125);
INSERT INTO `t_role_staff` VALUES (30, 8, 120);
INSERT INTO `t_role_staff` VALUES (31, 8, 126);
INSERT INTO `t_role_staff` VALUES (32, 8, 127);
INSERT INTO `t_role_staff` VALUES (33, 8, 128);
INSERT INTO `t_role_staff` VALUES (34, 8, 129);
INSERT INTO `t_role_staff` VALUES (35, 8, 130);
INSERT INTO `t_role_staff` VALUES (36, 8, 131);
INSERT INTO `t_role_staff` VALUES (37, 8, 132);
INSERT INTO `t_role_staff` VALUES (38, 8, 134);
INSERT INTO `t_role_staff` VALUES (39, 8, 135);

-- ----------------------------
-- Table structure for t_staff
-- ----------------------------
DROP TABLE IF EXISTS `t_staff`;
CREATE TABLE `t_staff`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'PRIMARY KEY:ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'NAME',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'LOGIN IN NAME',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'PASSWORD',
  `year_entry` varchar(4) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'EMPLOY YEAR',
  `year_separation` varchar(4) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'QUITE YEAR',
  `company_id` int(11) NULL DEFAULT NULL,
  `dept_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'DEPARTMENT',
  `position_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'POSITION',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT 'GENDER，0－FEMALE，1－MALE',
  `birthday` date NULL DEFAULT NULL COMMENT 'DOB',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'MOBILE NUMBER',
  `education` varchar(2) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'EDUCATION/QULIFICATION',
  `nation` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'NATIONALITY',
  `marital` tinyint(1) NULL DEFAULT NULL COMMENT 'Marital status，0－SINGLE，1－married',
  `household` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'HOME TOWN',
  `profession` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '',
  `address` varchar(80) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'ADDRESS',
  `email` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'EMAIL',
  `id_card` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'ID NUMBER',
  `resume` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT 'RESEUM',
  `evaluation` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT 'EVALUATION',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT 'STATE，0－EMPOLYED，1－DISMISS',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'CREATE TIME',
  `creator` int(10) NULL DEFAULT NULL,
  `modify_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT 'UPDATE TIME',
  `modificator` int(10) NULL DEFAULT NULL COMMENT 'MODIFICATOR',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT 'IS-ENABLE：0 DISABLE,1 ENABLE',
  `locked` tinyint(1) NULL DEFAULT 0 COMMENT 'IS-LOCK：0 UNLOCK，1 LOCK',
  `follow` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'FOLLOW',
  `broker` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'BROKER',
  `gmt` int(10) NULL DEFAULT NULL COMMENT 'GMT',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 137 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = 'SYS STAFF' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_staff
-- ----------------------------
INSERT INTO `t_staff` VALUES (1, 'ADMIN', 'admin', 'af1d5ab483ff0b1594fe2cac49dbcf95', '', NULL, 3, '20', NULL, 1, '2012-04-24', '13800000000', '', 'CN', 0, '', '', 'SH', 'admin@126.com', '35042519800000000', '', '', NULL, NULL, NULL, '2018-07-21 17:33:02', NULL, 1, 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_staff_login
-- ----------------------------
DROP TABLE IF EXISTS `t_staff_login`;
CREATE TABLE `t_staff_login`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `staff_id` int(10) NULL DEFAULT NULL COMMENT 'STAFF ID',
  `login_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT 'LOG IN TIME',
  `login_ip` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'LOG IN IP',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_staff_lg_staff_id`(`staff_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = 'SYS STAFF LOG IN INFO' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_task
-- ----------------------------
DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'PRIMARY KEY',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'TASK NAME',
  `period` tinyint(2) NULL DEFAULT NULL COMMENT 'TASK REPEAT',
  `target_object` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'TARGET BACKAGE，eg：com.app.job',
  `begin_date` datetime(0) NULL DEFAULT NULL COMMENT 'BEGIN DATE',
  `end_date` datetime(0) NULL DEFAULT NULL COMMENT 'END DATE',
  `cron_expression` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'EXPRESSION',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'DESC',
  `state` tinyint(1) NULL DEFAULT 0 COMMENT 'STATE 0:START，1：STOP 2：PAUSE',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT 'CREATE DATE',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'AUTHOR',
  `modify_date` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT 'UPDATE DATE',
  `modificator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'MODIFICATOR',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = 'SYS SCHEDULED TASK' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_test
-- ----------------------------
DROP TABLE IF EXISTS `t_test`;
CREATE TABLE `t_test`  (
  `id` int(11) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_test
-- ----------------------------
INSERT INTO `t_test` VALUES (1, 'Kevin');
INSERT INTO `t_test` VALUES (2, 'Jonathan');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'PRIMARY KEY/ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'NAME',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT 'LOGIN NAME',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'PASSWORD',
  `company_id` int(11) NULL DEFAULT NULL,
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT 'GENDER，0－FEMALE，1－MALE',
  `birthday` date NULL DEFAULT NULL COMMENT 'DOB',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'MOBILE NUMBER',
  `address` varchar(80) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'ADDRESS',
  `email` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'EMAIL',
  `id_card` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT 'ID NUMBER',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT 'CREATE TIME',
  `creator` int(10) NULL DEFAULT NULL,
  `modify_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT 'UPDATE TIME',
  `modificator` int(10) NULL DEFAULT NULL COMMENT 'MODIFICATOR',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT 'IS ENABLE：0 DISABLE,1 ENABLE',
  `locked` tinyint(1) NULL DEFAULT 0 COMMENT 'IS LOCK：0 NO，1 LOCK',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 142 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = 'SYS USER' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (141, 'Lin', 'Eric', NULL, NULL, 1, '2018-07-12', '13559118729', 'xxx', 'lin@126.com', '35042519800000000', NULL, NULL, '2018-07-21 17:45:59', NULL, 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
