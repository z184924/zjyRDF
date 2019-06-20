/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : zjyrdf

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2019-06-20 19:16:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for demo_demo
-- ----------------------------
DROP TABLE IF EXISTS `demo_demo`;
CREATE TABLE `demo_demo` (
  `demoId` varchar(32) DEFAULT NULL,
  `demoText1` varchar(255) DEFAULT NULL,
  `demoText2` varchar(255) DEFAULT NULL,
  `demoNumber1` int(11) DEFAULT NULL,
  `demoNumber2` int(11) DEFAULT NULL,
  `demoBoolean1` bit(1) DEFAULT NULL,
  `demoBoolean2` bit(1) DEFAULT NULL,
  `demoTime1` datetime DEFAULT NULL,
  `demoTime2` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of demo_demo
-- ----------------------------
INSERT INTO `demo_demo` VALUES ('67cfa53b0fc648cbb5e863e702b2ff87', '123', '123', '1', '2', '', '', '2019-03-04 17:34:57', '2019-03-04 17:34:58');
INSERT INTO `demo_demo` VALUES ('b3695b83d77142c794eefc1c9e48fb1c', '1', '1', '2', '-2', '\0', '', '2019-03-05 14:42:29', '2019-03-05 14:42:30');

-- ----------------------------
-- Table structure for sys_rights
-- ----------------------------
DROP TABLE IF EXISTS `sys_rights`;
CREATE TABLE `sys_rights` (
  `rightsId` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `rightsCode` varchar(255) DEFAULT NULL,
  `rightsName` varchar(255) DEFAULT NULL,
  `rightsType` int(2) DEFAULT NULL,
  `parentId` int(11) unsigned DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `sorting` int(11) DEFAULT NULL,
  PRIMARY KEY (`rightsId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_rights
-- ----------------------------
INSERT INTO `sys_rights` VALUES ('1', '#', 'system', '系统管理', '0', '0', 'el-icon-setting', '0');
INSERT INTO `sys_rights` VALUES ('2', '/user/listPage', 'user-list', '用户管理', '1', '1', null, '2');
INSERT INTO `sys_rights` VALUES ('3', '/user/findById', 'user-detail', '用户查看', '2', '2', null, '0');
INSERT INTO `sys_rights` VALUES ('4', '/user/save', 'user-add', '用户添加', '2', '2', null, '0');
INSERT INTO `sys_rights` VALUES ('5', '/user/edit', 'user-edit', '用户编辑', '2', '2', null, '0');
INSERT INTO `sys_rights` VALUES ('6', '/user/delete', 'user-delete', '用户删除', '2', '2', null, '0');
INSERT INTO `sys_rights` VALUES ('7', '/role/listPage', 'role-list', '角色管理', '1', '1', null, '1');
INSERT INTO `sys_rights` VALUES ('8', '/role/findById', 'role-detail', '角色查看', '2', '7', null, '0');
INSERT INTO `sys_rights` VALUES ('9', '/role/save', 'role-add', '角色添加', '2', '7', null, '0');
INSERT INTO `sys_rights` VALUES ('10', '/role/edit', 'role-edit', '角色编辑', '2', '7', null, '0');
INSERT INTO `sys_rights` VALUES ('11', '/role/delete', 'role-delete', '角色删除', '2', '7', null, '0');
INSERT INTO `sys_rights` VALUES ('12', '/rights/listPage', 'rights-list', '权限管理', '1', '1', null, '0');
INSERT INTO `sys_rights` VALUES ('13', '/rights/findById', 'rights-detail', '权限查看', '2', '12', null, '0');
INSERT INTO `sys_rights` VALUES ('14', '/rights/save', 'rights-add', '权限添加', '2', '12', null, '0');
INSERT INTO `sys_rights` VALUES ('15', '/rights/edit', 'rights-edit', '权限编辑', '2', '12', null, '0');
INSERT INTO `sys_rights` VALUES ('16', '/rights/delete', 'rights-delete', '权限删除', '2', '12', null, '0');
INSERT INTO `sys_rights` VALUES ('17', '#', 'demo', '模板样例', '0', '0', 'el-icon-document', '1');
INSERT INTO `sys_rights` VALUES ('18', '/demo/listPage', 'demo-list', '模板样例', '1', '17', null, '1');
INSERT INTO `sys_rights` VALUES ('19', '/demo/edit', 'demo-edit', '样例数据编辑', '2', '18', null, '1');
INSERT INTO `sys_rights` VALUES ('20', '/demo/save', 'demo-add', '样例数据添加', '2', '18', null, '1');
INSERT INTO `sys_rights` VALUES ('21', '/demo/delete', 'demo-delete', '样例数据删除', '2', '18', null, '1');
INSERT INTO `sys_rights` VALUES ('22', '/demo/findById', 'demo-detail', '样例数据查看', '2', '18', null, '1');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `roleId` varchar(32) NOT NULL,
  `roleName` varchar(255) DEFAULT NULL,
  `sorting` int(11) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('a23ffac219244049a45c35c7cf3de9dc', '管理员', '0');
INSERT INTO `sys_role` VALUES ('a91e450f8f3148b0a13b915cab7d3bd1', '测试角色', '0');

-- ----------------------------
-- Table structure for sys_role_rights
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_rights`;
CREATE TABLE `sys_role_rights` (
  `id` varchar(32) NOT NULL,
  `roleId` varchar(32) NOT NULL,
  `rightsId` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_role_rights
-- ----------------------------
INSERT INTO `sys_role_rights` VALUES ('1', 'a23ffac219244049a45c35c7cf3de9dc', '0');
INSERT INTO `sys_role_rights` VALUES ('10', 'a23ffac219244049a45c35c7cf3de9dc', '8');
INSERT INTO `sys_role_rights` VALUES ('11', 'a23ffac219244049a45c35c7cf3de9dc', '9');
INSERT INTO `sys_role_rights` VALUES ('12', 'a23ffac219244049a45c35c7cf3de9dc', '10');
INSERT INTO `sys_role_rights` VALUES ('13', 'a23ffac219244049a45c35c7cf3de9dc', '11');
INSERT INTO `sys_role_rights` VALUES ('14', 'a23ffac219244049a45c35c7cf3de9dc', '12');
INSERT INTO `sys_role_rights` VALUES ('15', 'a23ffac219244049a45c35c7cf3de9dc', '13');
INSERT INTO `sys_role_rights` VALUES ('16', 'a23ffac219244049a45c35c7cf3de9dc', '14');
INSERT INTO `sys_role_rights` VALUES ('17', 'a23ffac219244049a45c35c7cf3de9dc', '15');
INSERT INTO `sys_role_rights` VALUES ('18', 'a23ffac219244049a45c35c7cf3de9dc', '16');
INSERT INTO `sys_role_rights` VALUES ('19', 'a23ffac219244049a45c35c7cf3de9dc', '17');
INSERT INTO `sys_role_rights` VALUES ('2', 'a23ffac219244049a45c35c7cf3de9dc', '1');
INSERT INTO `sys_role_rights` VALUES ('20', 'a23ffac219244049a45c35c7cf3de9dc', '18');
INSERT INTO `sys_role_rights` VALUES ('21', 'a23ffac219244049a45c35c7cf3de9dc', '19');
INSERT INTO `sys_role_rights` VALUES ('22', 'a23ffac219244049a45c35c7cf3de9dc', '20');
INSERT INTO `sys_role_rights` VALUES ('2230e6192ccb40cb844774710a6f1191', 'a91e450f8f3148b0a13b915cab7d3bd1', '18');
INSERT INTO `sys_role_rights` VALUES ('23', 'a23ffac219244049a45c35c7cf3de9dc', '21');
INSERT INTO `sys_role_rights` VALUES ('24', 'a23ffac219244049a45c35c7cf3de9dc', '22');
INSERT INTO `sys_role_rights` VALUES ('3', 'a23ffac219244049a45c35c7cf3de9dc', '1');
INSERT INTO `sys_role_rights` VALUES ('4', 'a23ffac219244049a45c35c7cf3de9dc', '2');
INSERT INTO `sys_role_rights` VALUES ('5', 'a23ffac219244049a45c35c7cf3de9dc', '3');
INSERT INTO `sys_role_rights` VALUES ('596410b815b14a5ba62f9af1d5a99ef0', 'a91e450f8f3148b0a13b915cab7d3bd1', '22');
INSERT INTO `sys_role_rights` VALUES ('6', 'a23ffac219244049a45c35c7cf3de9dc', '4');
INSERT INTO `sys_role_rights` VALUES ('7', 'a23ffac219244049a45c35c7cf3de9dc', '5');
INSERT INTO `sys_role_rights` VALUES ('8', 'a23ffac219244049a45c35c7cf3de9dc', '6');
INSERT INTO `sys_role_rights` VALUES ('9', 'a23ffac219244049a45c35c7cf3de9dc', '7');
INSERT INTO `sys_role_rights` VALUES ('c1ef7d9acc924c39b6db36cbbaec6cb4', 'a91e450f8f3148b0a13b915cab7d3bd1', '21');
INSERT INTO `sys_role_rights` VALUES ('d2faba6a916a45a28686ab53f773cce8', 'a91e450f8f3148b0a13b915cab7d3bd1', '17');
INSERT INTO `sys_role_rights` VALUES ('da401c56674c4456a7f3a6980deebaa7', 'a91e450f8f3148b0a13b915cab7d3bd1', '20');
INSERT INTO `sys_role_rights` VALUES ('ee736026869f43ccaa9b2cf1b2e0662f', 'a91e450f8f3148b0a13b915cab7d3bd1', '19');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `userId` varchar(32) NOT NULL,
  `account` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `userName` varchar(255) NOT NULL,
  `locked` bit(1) NOT NULL,
  `disable` bit(1) NOT NULL,
  `specialRole` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$UwdWOlIaEP7BKYBEsUBU9.3BQVat4upwz8s9Jp1W9j4e3qWJMXzCm', 'admin', '\0', '\0', 'ROLE_admin');
INSERT INTO `sys_user` VALUES ('b8e9ba17b06a4674b69342c93a7fd774', 'test', '$2a$10$ifNZ4DJwmS.d1/GtejoCa.fXzO625FB/XO6NLoU5ox.VtfqehSVHy', 'test', '\0', '\0', '');
INSERT INTO `sys_user` VALUES ('eda56955597847d39707fe0cfba4ebab', 'user', '$2a$10$pJlKA2REfYux5h2gYIGp4.PH5pd.Tl4kSnB9p/xID4UChOK7pShlq', 'user', '\0', '\0', 'ROLE_user');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` varchar(32) NOT NULL,
  `userId` varchar(32) NOT NULL,
  `roleId` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('862944d73f9640c4942eee5a8ada5639', 'eda56955597847d39707fe0cfba4ebab', 'a91e450f8f3148b0a13b915cab7d3bd1');
INSERT INTO `sys_user_role` VALUES ('979cef4f08184372be3fe55501372472', '1', 'a91e450f8f3148b0a13b915cab7d3bd1');
INSERT INTO `sys_user_role` VALUES ('ce12ddb354c54704aabf456b8dd1238b', 'b8e9ba17b06a4674b69342c93a7fd774', 'a91e450f8f3148b0a13b915cab7d3bd1');
INSERT INTO `sys_user_role` VALUES ('f24992aead674928839c5bb80ddf8520', '1', 'a23ffac219244049a45c35c7cf3de9dc');
