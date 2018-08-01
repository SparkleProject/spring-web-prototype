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
) ENGINE = MyISAM AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '系统数据字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_code
-- ----------------------------
INSERT INTO `t_code` VALUES (50, '7', 'TEST_CODE_1', '测试', 'test', 1, 1, '');

-- ----------------------------
-- Table structure for t_code_type
-- ----------------------------
DROP TABLE IF EXISTS `t_code_type`;
CREATE TABLE `t_code_type`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `code` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '码编',
  `name` varchar(128) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL,
  `seq` int(10) NULL DEFAULT NULL COMMENT '˳',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '˵��',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '系统数据字典类型' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_code_type
-- ----------------------------
INSERT INTO `t_code_type` VALUES (7, 'TEST_CODE', '测试', NULL, '');

-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'UUID',
  `name` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '部门名称',
  `pid` int(10) NULL DEFAULT NULL COMMENT '上级部门ID',
  `seq` int(10) NULL DEFAULT 0 COMMENT '同级部门顺序',
  `description` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '部门描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '系统部门' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_department
-- ----------------------------
INSERT INTO `t_department` VALUES (5, '部门1-1', 1, NULL, 'dddddaa');
INSERT INTO `t_department` VALUES (20, '部门一', 0, 0, '');

-- ----------------------------
-- Table structure for t_function
-- ----------------------------
DROP TABLE IF EXISTS `t_function`;
CREATE TABLE `t_function`  (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT 'UUID',
  `code` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '功能编码',
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '功能名称',
  `description` varchar(200) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '功能描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '系统功能' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_function
-- ----------------------------
INSERT INTO `t_function` VALUES (5, 'admin:staff:add', '新增', '新增的');
INSERT INTO `t_function` VALUES (4, 'admin:staff:delete', '删除', '删除的');

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
  `pid` int(4) NOT NULL COMMENT '上级菜单',
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '菜单名称',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '菜单链接',
  `target` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '菜单链接所在框架名',
  `relative` tinyint(1) NULL DEFAULT NULL COMMENT '是否相对路径，0－否，1－是。',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '菜单图标路径',
  `seq` int(10) UNSIGNED NULL DEFAULT 0 COMMENT '同级菜单顺序号',
  `enable` tinyint(1) NULL DEFAULT NULL COMMENT '是否启用，0－否，1－是。',
  `visible` tinyint(1) NULL DEFAULT NULL COMMENT '是否可见，0－否，1－是。',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 49 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '系统菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (1, 0, '基础信息管理', '', '', NULL, NULL, 49, 0, NULL);
INSERT INTO `t_menu` VALUES (2, 0, '系统管理', '', NULL, NULL, NULL, 50, 1, NULL);
INSERT INTO `t_menu` VALUES (4, 2, '登录情况', '/admin/staff-login', NULL, NULL, NULL, 0, 1, NULL);
INSERT INTO `t_menu` VALUES (6, 1, '管理员', '/admin/staff', '', NULL, '', 1, 1, NULL);
INSERT INTO `t_menu` VALUES (7, 1, '部门管理', '/admin/department', NULL, NULL, NULL, 2, 1, NULL);
INSERT INTO `t_menu` VALUES (8, 2, '角色管理', '/admin/role', NULL, NULL, NULL, 3, 1, NULL);
INSERT INTO `t_menu` VALUES (9, 2, '菜单管理', '/admin/menu', NULL, NULL, NULL, 4, 1, NULL);
INSERT INTO `t_menu` VALUES (10, 2, '功能管理', '/admin/function', NULL, NULL, NULL, 5, 1, NULL);
INSERT INTO `t_menu` VALUES (11, 2, '数据字典', '/admin/code', '', NULL, '', 6, 1, NULL);
INSERT INTO `t_menu` VALUES (12, 2, '用户授权', '/admin/role-staff', NULL, NULL, NULL, 1, 1, NULL);
INSERT INTO `t_menu` VALUES (16, 2, '任务计划', '/admin/task', '_self', NULL, NULL, 14, 1, NULL);
INSERT INTO `t_menu` VALUES (17, 2, '系统监控', '/druid/index.html', '_blank', NULL, '', 90, 1, NULL);
INSERT INTO `t_menu` VALUES (27, 0, '网站管理', '', '_self', NULL, '', 0, 1, NULL);
INSERT INTO `t_menu` VALUES (48, 27, '网站用户', '/admin/user', '_self', NULL, '', 1, 1, NULL);
INSERT INTO `t_menu` VALUES (31, 32, '首页', '/user/index', '_self', NULL, 'icon-home4', 0, 1, NULL);
INSERT INTO `t_menu` VALUES (32, 27, '客户菜单', '/user/withdraw', '_self', NULL, '', 100, 1, NULL);
INSERT INTO `t_menu` VALUES (33, 32, '修改密码', '/user/password', '_self', NULL, 'icon-pencil3', 9, 1, NULL);
INSERT INTO `t_menu` VALUES (34, 32, '退出登录', '/user/logout', '_self', NULL, 'icon-switch2', 10, 1, NULL);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'UUID',
  `code` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '角色编码',
  `name` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(150) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '角色说明',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '系统角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'ROLE_ADMIN', '超级管理员', '超级管理员');
INSERT INTO `t_role` VALUES (8, 'ROLE_USER', '网站用户', '');

-- ----------------------------
-- Table structure for t_role_res
-- ----------------------------
DROP TABLE IF EXISTS `t_role_res`;
CREATE TABLE `t_role_res`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT 'UUID',
  `role_id` int(10) NULL DEFAULT NULL COMMENT '角色ID',
  `res_type` int(1) NULL DEFAULT NULL COMMENT '资源类型，1－菜单，2－功能。',
  `res_id` int(10) NULL DEFAULT NULL COMMENT '资源ID，两种：菜单ID或功能ID。',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 456 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '系统角色资源' ROW_FORMAT = Fixed;

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
  `role_id` int(10) NULL DEFAULT NULL COMMENT '角色ID',
  `staff_id` int(10) NULL DEFAULT NULL COMMENT '员工ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '系统角色员工' ROW_FORMAT = Fixed;

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
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '姓名',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '登录名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '登录密码',
  `year_entry` varchar(4) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '入职年份',
  `year_separation` varchar(4) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '职离年份',
  `company_id` int(11) NULL DEFAULT NULL,
  `dept_id` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '所属部门',
  `position_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '岗位',
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别，0－女，1－男',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '手机号码',
  `education` varchar(2) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '教育程度，学历',
  `nation` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '民族',
  `marital` tinyint(1) NULL DEFAULT NULL COMMENT '婚否，0－未婚，1－已婚。',
  `household` varchar(60) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '籍贯',
  `profession` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '专业',
  `address` varchar(80) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '联系地址',
  `email` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `id_card` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `resume` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '简历信息',
  `evaluation` text CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL COMMENT '个人自评',
  `status` tinyint(1) NULL DEFAULT NULL COMMENT '在职状态，0－离职，1－在职',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '建创时间',
  `creator` int(10) NULL DEFAULT NULL,
  `modify_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `modificator` int(10) NULL DEFAULT NULL COMMENT '修改者',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '是否可用：0 禁用,1 可用',
  `locked` tinyint(1) NULL DEFAULT 0 COMMENT '是否锁定：0 未锁定，1 锁定',
  `follow` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '跟单',
  `broker` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '经纪商',
  `gmt` int(10) NULL DEFAULT NULL COMMENT 'GMT',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 137 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '系统员工' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_staff
-- ----------------------------
INSERT INTO `t_staff` VALUES (1, '超级管理员', 'admin', 'af1d5ab483ff0b1594fe2cac49dbcf95', '', NULL, 3, '20', NULL, 1, '2012-04-24', '13800000000', '', '汉族', 0, '', '', '福建福州', 'admin@126.com', '35042519800000000', '', '', NULL, NULL, NULL, '2018-07-21 17:33:02', NULL, 1, 0, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_staff_login
-- ----------------------------
DROP TABLE IF EXISTS `t_staff_login`;
CREATE TABLE `t_staff_login`  (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `staff_id` int(10) NULL DEFAULT NULL COMMENT '员工ID',
  `login_date` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '登录时间',
  `login_ip` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '登录IP',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_staff_lg_staff_id`(`staff_id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '系统员工登录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_task
-- ----------------------------
DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task`  (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `period` tinyint(2) NULL DEFAULT NULL COMMENT '任务周期',
  `target_object` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '目标类完整路径，如：com.app.job',
  `begin_date` datetime(0) NULL DEFAULT NULL COMMENT '起始日期',
  `end_date` datetime(0) NULL DEFAULT NULL COMMENT '结束日期',
  `cron_expression` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调度表达式',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  `state` tinyint(1) NULL DEFAULT 0 COMMENT '是否禁用 0:启用，1：停止 2：暂停',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `creator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `modify_date` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改日期',
  `modificator` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '改修者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统调度任务' ROW_FORMAT = Dynamic;

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
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '姓名',
  `login_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '登录名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '登录密码',
  `company_id` int(11) NULL DEFAULT NULL,
  `sex` tinyint(1) NULL DEFAULT NULL COMMENT '性别，0－女，1－男',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `mobile` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '手机号码',
  `address` varchar(80) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '联系地址',
  `email` varchar(40) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '邮箱',
  `id_card` varchar(30) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '建创时间',
  `creator` int(10) NULL DEFAULT NULL,
  `modify_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `modificator` int(10) NULL DEFAULT NULL COMMENT '修改者',
  `enabled` tinyint(1) NULL DEFAULT 1 COMMENT '是否可用：0 禁用,1 可用',
  `locked` tinyint(1) NULL DEFAULT 0 COMMENT '是否锁定：0 未锁定，1 锁定',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 142 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '系统员工' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (141, 'lin', 'kevin', NULL, NULL, 1, '2018-07-12', '13559118729', 'xxx', 'kevinlin@126.com', '35042519800000000', NULL, NULL, '2018-07-21 17:45:59', NULL, 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
