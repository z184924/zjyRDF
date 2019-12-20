/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : zjyrdf

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 20/12/2019 15:57:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for demo_demo
-- ----------------------------
DROP TABLE IF EXISTS `demo_demo`;
CREATE TABLE `demo_demo`  (
  `demoId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'id',
  `demoText1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文本1',
  `demoText2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文本2',
  `demoNumber1` int(11) NULL DEFAULT NULL COMMENT '数字1',
  `demoNumber2` int(11) NULL DEFAULT NULL COMMENT '数字2',
  `demoBoolean1` bit(1) NULL DEFAULT NULL COMMENT '布尔1',
  `demoBoolean2` bit(1) NULL DEFAULT NULL COMMENT '布尔2',
  `demoTime1` datetime(0) NULL DEFAULT NULL COMMENT '时间1',
  `demoTime2` datetime(0) NULL DEFAULT NULL COMMENT '时间2',
  PRIMARY KEY (`demoId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of demo_demo
-- ----------------------------
INSERT INTO `demo_demo` VALUES ('03f414614c4e4428b9322ca0d96018a9', 'demoText557', 'demoText557', 557, 557, b'1', b'0', '2019-12-09 16:20:09', '2019-12-09 16:20:09');
INSERT INTO `demo_demo` VALUES ('05fb684bdfdf4d20a3e968b73e9a689e', 'demoText2312', 'demoText2312', 2312, 2312, b'1', b'0', '2019-12-09 16:20:09', '2019-12-09 16:20:09');
INSERT INTO `demo_demo` VALUES ('0c9e0deab836478fa78ff296da9668ed', 'demoText-5455', 'demoText-5455', -5455, -5455, b'1', b'0', '2019-12-09 16:20:10', '2019-12-09 16:20:10');
INSERT INTO `demo_demo` VALUES ('1e0c86b5ce8242d3b6bf830c17f5d8a1', 'demoText-278', 'demoText-278', -278, -278, b'1', b'0', '2019-12-09 16:20:10', '2019-12-09 16:20:10');
INSERT INTO `demo_demo` VALUES ('1f72f2cd78b140c282631385130cf369', 'demoText6991', 'demoText6991', 6991, 6991, b'1', b'0', '2019-12-09 16:20:09', '2019-12-09 16:20:09');
INSERT INTO `demo_demo` VALUES ('2a3c7bc36fc04c39bf520400673a7101', 'demoText-8511', 'demoText-8511', -8511, -8511, b'1', b'0', '2019-12-09 16:20:09', '2019-12-09 16:20:09');
INSERT INTO `demo_demo` VALUES ('2b5a566ce18b4772845505a923836760', 'demoText325', 'demoText325', 325, 325, b'1', b'0', '2019-12-09 16:20:10', '2019-12-09 16:20:10');
INSERT INTO `demo_demo` VALUES ('496fdc57327b4a568ee8c5bc623d05f5', 'demoText9580', 'demoText9580', 9580, 9580, b'1', b'0', '2019-12-09 16:20:10', '2019-12-09 16:20:10');
INSERT INTO `demo_demo` VALUES ('4d637abfe78644a78a28125d81fe5a43', 'demoText6342', 'demoText6342', 6342, 6342, b'1', b'0', '2019-12-09 16:20:09', '2019-12-09 16:20:09');
INSERT INTO `demo_demo` VALUES ('50e68ac8f39d4ef2906f16d782627a12', 'demoText-5481', 'demoText-5481', -5481, -5481, b'1', b'0', '2019-12-09 16:20:10', '2019-12-09 16:20:10');
INSERT INTO `demo_demo` VALUES ('532e4df62d7149bd8b9814bc8c1658a8', 'demoText-8652', 'demoText-8652', -8652, -8652, b'1', b'0', '2019-12-09 16:20:10', '2019-12-09 16:20:10');
INSERT INTO `demo_demo` VALUES ('564a3c28f1a042d3915cd439638a491f', 'demoText-7051', 'demoText-7051', -7051, -7051, b'1', b'0', '2019-12-09 16:20:10', '2019-12-09 16:20:10');
INSERT INTO `demo_demo` VALUES ('606fb49b45354134abaf0912c8687401', 'demoText-7078', 'demoText-7078', -7078, -7078, b'1', b'0', '2019-12-09 16:20:09', '2019-12-09 16:20:09');
INSERT INTO `demo_demo` VALUES ('626b044291b04cb1b8be15c1475cb5e6', 'demoText-986', 'demoText-986', -986, -986, b'1', b'0', '2019-12-09 16:20:10', '2019-12-09 16:20:10');
INSERT INTO `demo_demo` VALUES ('8a5a02a4fe6642caa7b0f073d5d90bfd', 'demoText8753', 'demoText8753', 8753, 8753, b'1', b'0', '2019-12-09 16:20:09', '2019-12-09 16:20:09');
INSERT INTO `demo_demo` VALUES ('8b08579050e84861818b0b1c200169ff', 'demoText1515', 'demoText1515', 1515, 1515, b'1', b'0', '2019-12-09 16:20:09', '2019-12-09 16:20:09');
INSERT INTO `demo_demo` VALUES ('91d48539bff844c58016314cf3bdb765', 'demoText-5032', 'demoText-5032', -5032, -5032, b'1', b'0', '2019-12-09 16:20:10', '2019-12-09 16:20:10');
INSERT INTO `demo_demo` VALUES ('92712b9ed0c946c8a7bfd4eb30ef8bf3', 'demoText965', 'demoText965', 965, 965, b'1', b'0', '2019-12-09 16:20:10', '2019-12-09 16:20:10');
INSERT INTO `demo_demo` VALUES ('958c14cd818d4449b563a811b2f93509', 'demoText5717', 'demoText5717', 5717, 5717, b'1', b'0', '2019-12-09 16:20:09', '2019-12-09 16:20:09');
INSERT INTO `demo_demo` VALUES ('958e937c2be74b20a870089121e28330', 'demoText-451', 'demoText-451', -451, -451, b'1', b'0', '2019-12-09 16:20:09', '2019-12-09 16:20:09');
INSERT INTO `demo_demo` VALUES ('9a83485097924b268ecfd617b615ac60', 'demoText1732', 'demoText1732', 1732, 1732, b'1', b'0', '2019-12-09 16:20:09', '2019-12-09 16:20:09');
INSERT INTO `demo_demo` VALUES ('a042b0b1a0a54d2eb52ea49f26630636', 'demoText1661', 'demoText1661', 1661, 1661, b'1', b'0', '2019-12-09 16:20:09', '2019-12-09 16:20:09');
INSERT INTO `demo_demo` VALUES ('a422ad2c1d5c4879948bc6abe58960cb', 'demoText5128', 'demoText5128', 5128, 5128, b'1', b'0', '2019-12-09 16:20:09', '2019-12-09 16:20:09');
INSERT INTO `demo_demo` VALUES ('a8342a80aa3d478c91f97702733f16bd', 'demoText-926', 'demoText-926', -926, -926, b'1', b'0', '2019-12-09 16:20:10', '2019-12-09 16:20:10');
INSERT INTO `demo_demo` VALUES ('a859f909d2c44e5f9ac98e4a84d7566a', 'demoText9850', 'demoText9850', 9850, 9850, b'1', b'0', '2019-12-09 16:20:09', '2019-12-09 16:20:09');
INSERT INTO `demo_demo` VALUES ('b139a2cab10141ea82f92afb06e89d90', 'demoText-9172', 'demoText-9172', -9172, -9172, b'1', b'0', '2019-12-09 16:20:09', '2019-12-09 16:20:09');
INSERT INTO `demo_demo` VALUES ('b5befa6d63bb472ca2bdddf1ee28a1cd', 'demoText4038', 'demoText4038', 4038, 4038, b'1', b'0', '2019-12-09 16:20:10', '2019-12-09 16:20:10');
INSERT INTO `demo_demo` VALUES ('bbbdfb84a1d740e2baf290e01db0ae5c', 'demoText989', 'demoText989', 989, 989, b'1', b'0', '2019-12-09 16:20:09', '2019-12-09 16:20:09');
INSERT INTO `demo_demo` VALUES ('bbf21fdfcc084d1c839006de4cd5764a', 'demoText-5835', 'demoText-5835', -5835, -5835, b'1', b'0', '2019-12-09 16:20:09', '2019-12-09 16:20:09');
INSERT INTO `demo_demo` VALUES ('d4f0f43be6c44a9389b9d884d40649b8', '444', '44123', 1, -1, b'0', b'1', '2019-12-20 14:11:02', '2019-12-20 14:11:04');
INSERT INTO `demo_demo` VALUES ('ef6e6ef40a9248469cb2e69e992d77b9', 'demoText3872', 'demoText3872', 3872, 3872, b'1', b'0', '2019-12-09 16:20:09', '2019-12-09 16:20:09');

-- ----------------------------
-- Table structure for log_index
-- ----------------------------
DROP TABLE IF EXISTS `log_index`;
CREATE TABLE `log_index`  (
  `logIndexId` int(32) NOT NULL AUTO_INCREMENT COMMENT '日志表ID',
  `logTableName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志表名',
  `beginTime` datetime(3) NULL DEFAULT NULL COMMENT '起始时间',
  `endTime` datetime(3) NULL DEFAULT NULL COMMENT '结束时间',
  `systemTag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '系统标签',
  PRIMARY KEY (`logIndexId`) USING BTREE,
  UNIQUE INDEX `logTableNameUnique`(`logTableName`) USING BTREE COMMENT '表名唯一性索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_rights
-- ----------------------------
DROP TABLE IF EXISTS `sys_rights`;
CREATE TABLE `sys_rights`  (
  `rightsId` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '后台路径',
  `rightsCode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限编码',
  `rightsName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `rightsType` int(2) NULL DEFAULT NULL COMMENT '权限类型',
  `parentId` int(11) UNSIGNED NULL DEFAULT NULL COMMENT '上级ID',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '前端图标',
  `sorting` int(11) NULL DEFAULT NULL COMMENT '排序编码',
  PRIMARY KEY (`rightsId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_rights
-- ----------------------------
INSERT INTO `sys_rights` VALUES (1, '#', 'system', '系统管理', 0, 0, 'el-icon-setting', 0);
INSERT INTO `sys_rights` VALUES (2, '/user/listPage', 'user-list', '用户管理', 1, 1, NULL, 2);
INSERT INTO `sys_rights` VALUES (3, '/user/findById', 'user-detail', '用户查看', 2, 2, NULL, 0);
INSERT INTO `sys_rights` VALUES (4, '/user/save', 'user-add', '用户添加', 2, 2, NULL, 0);
INSERT INTO `sys_rights` VALUES (5, '/user/edit', 'user-edit', '用户编辑', 2, 2, NULL, 0);
INSERT INTO `sys_rights` VALUES (6, '/user/delete', 'user-delete', '用户删除', 2, 2, NULL, 0);
INSERT INTO `sys_rights` VALUES (7, '/role/listPage', 'role-list', '角色管理', 1, 1, NULL, 1);
INSERT INTO `sys_rights` VALUES (8, '/role/findById', 'role-detail', '角色查看', 2, 7, NULL, 0);
INSERT INTO `sys_rights` VALUES (9, '/role/save', 'role-add', '角色添加', 2, 7, NULL, 0);
INSERT INTO `sys_rights` VALUES (10, '/role/edit', 'role-edit', '角色编辑', 2, 7, NULL, 0);
INSERT INTO `sys_rights` VALUES (11, '/role/delete', 'role-delete', '角色删除', 2, 7, NULL, 0);
INSERT INTO `sys_rights` VALUES (12, '/rights/listPage', 'rights-list', '权限管理', 1, 1, NULL, 0);
INSERT INTO `sys_rights` VALUES (13, '/rights/findById', 'rights-detail', '权限查看', 2, 12, NULL, 0);
INSERT INTO `sys_rights` VALUES (14, '/rights/save', 'rights-add', '权限添加', 2, 12, NULL, 0);
INSERT INTO `sys_rights` VALUES (15, '/rights/edit', 'rights-edit', '权限编辑', 2, 12, NULL, 0);
INSERT INTO `sys_rights` VALUES (16, '/rights/delete', 'rights-delete', '权限删除', 2, 12, NULL, 0);
INSERT INTO `sys_rights` VALUES (17, '#', 'demo', '模板样例', 0, 0, 'el-icon-document', 1);
INSERT INTO `sys_rights` VALUES (18, '/demo/listPage', 'demo-list', '模板样例', 1, 17, NULL, 1);
INSERT INTO `sys_rights` VALUES (19, '/demo/edit', 'demo-edit', '样例数据编辑', 2, 18, NULL, 1);
INSERT INTO `sys_rights` VALUES (20, '/demo/save', 'demo-add', '样例数据添加', 2, 18, NULL, 1);
INSERT INTO `sys_rights` VALUES (21, '/demo/delete', 'demo-delete', '样例数据删除', 2, 18, NULL, 1);
INSERT INTO `sys_rights` VALUES (22, '/demo/findById', 'demo-detail', '样例数据查看', 2, 18, NULL, 1);
INSERT INTO `sys_rights` VALUES (23, '/systemLog/listPage', 'system-log-list', '系统日志', 1, 28, NULL, 1);
INSERT INTO `sys_rights` VALUES (24, '/systemLog/edit', 'system-log-edit', '系统日志编辑', 2, 23, NULL, 1);
INSERT INTO `sys_rights` VALUES (25, '/systemLog/save', 'system-log-add', '系统日志添加', 2, 23, NULL, 1);
INSERT INTO `sys_rights` VALUES (26, '/systemLog/delete', 'system-log-delete', '系统日志删除', 2, 23, NULL, 1);
INSERT INTO `sys_rights` VALUES (27, '/systemLog/findById', 'system-log-detail', '系统日志查看', 2, 23, NULL, 1);
INSERT INTO `sys_rights` VALUES (28, '#', 'system-log', '日志中心', 0, 0, 'el-icon-document', 2);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `roleId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色Id',
  `roleName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `sorting` int(11) NULL DEFAULT NULL COMMENT '排序编号',
  PRIMARY KEY (`roleId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('a23ffac219244049a45c35c7cf3de9dc', '管理员', 0);
INSERT INTO `sys_role` VALUES ('a91e450f8f3148b0a13b915cab7d3bd1', '测试角色', 0);

-- ----------------------------
-- Table structure for sys_role_rights
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_rights`;
CREATE TABLE `sys_role_rights`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `roleId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `rightsId` int(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色权限关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_rights
-- ----------------------------
INSERT INTO `sys_role_rights` VALUES ('02668f4d2a534fe894ddd5d31d06b753', 'a91e450f8f3148b0a13b915cab7d3bd1', 19);
INSERT INTO `sys_role_rights` VALUES ('0334b248572640229058866b1e77e75c', 'a23ffac219244049a45c35c7cf3de9dc', 14);
INSERT INTO `sys_role_rights` VALUES ('039d2fabd901467db80f91875b7a7a46', 'a23ffac219244049a45c35c7cf3de9dc', 4);
INSERT INTO `sys_role_rights` VALUES ('0c0170464c1a4314a21e0469a561d0d9', 'a23ffac219244049a45c35c7cf3de9dc', 13);
INSERT INTO `sys_role_rights` VALUES ('142d1bceb1004f9fb99e3b9543fe979b', 'a23ffac219244049a45c35c7cf3de9dc', 6);
INSERT INTO `sys_role_rights` VALUES ('1632756b63634311906919f234e2fba1', 'a23ffac219244049a45c35c7cf3de9dc', 1);
INSERT INTO `sys_role_rights` VALUES ('179d04a28ded4b52a40492a60ef3e9dd', 'a23ffac219244049a45c35c7cf3de9dc', 0);
INSERT INTO `sys_role_rights` VALUES ('17e80e031d314913975d4ffeeebbda08', 'a23ffac219244049a45c35c7cf3de9dc', 3);
INSERT INTO `sys_role_rights` VALUES ('2a89619c01af474583076b560f466f57', 'a23ffac219244049a45c35c7cf3de9dc', 24);
INSERT INTO `sys_role_rights` VALUES ('310df25611494e27a3cc00e7d23b5755', 'a23ffac219244049a45c35c7cf3de9dc', 7);
INSERT INTO `sys_role_rights` VALUES ('31464df7d2af433a80464930041c20e7', 'a23ffac219244049a45c35c7cf3de9dc', 9);
INSERT INTO `sys_role_rights` VALUES ('3335596c493649a4b7d6d8bec4e51b12', 'a23ffac219244049a45c35c7cf3de9dc', 1);
INSERT INTO `sys_role_rights` VALUES ('35e0b1f40fb84ec0b8c0afa36325abaf', 'a23ffac219244049a45c35c7cf3de9dc', 23);
INSERT INTO `sys_role_rights` VALUES ('3a2d668c867143a5b41649ec3366510a', 'a23ffac219244049a45c35c7cf3de9dc', 25);
INSERT INTO `sys_role_rights` VALUES ('3d2e5b3d82af4568be28db1e0e7dede4', 'a23ffac219244049a45c35c7cf3de9dc', 2);
INSERT INTO `sys_role_rights` VALUES ('54aef0b9351b4323a11694ff6c6040cf', 'a91e450f8f3148b0a13b915cab7d3bd1', 28);
INSERT INTO `sys_role_rights` VALUES ('5732e6231cb3472f9edeafb9a785a517', 'a23ffac219244049a45c35c7cf3de9dc', 11);
INSERT INTO `sys_role_rights` VALUES ('6e09dbc5df9b4ce58135bb1bfffbeb0f', 'a23ffac219244049a45c35c7cf3de9dc', 15);
INSERT INTO `sys_role_rights` VALUES ('73fae06aa6d74a79a3821ea3d033998f', 'a23ffac219244049a45c35c7cf3de9dc', 17);
INSERT INTO `sys_role_rights` VALUES ('7622e1f48ee34909a875affad2713401', 'a23ffac219244049a45c35c7cf3de9dc', 22);
INSERT INTO `sys_role_rights` VALUES ('81f7e7d9751544c0ba2fe1244bb46827', 'a23ffac219244049a45c35c7cf3de9dc', 19);
INSERT INTO `sys_role_rights` VALUES ('8625f3a12a284156b440792fbfa59969', 'a23ffac219244049a45c35c7cf3de9dc', 18);
INSERT INTO `sys_role_rights` VALUES ('874ca3e045f7426590d7e1fd49937a2b', 'a91e450f8f3148b0a13b915cab7d3bd1', 18);
INSERT INTO `sys_role_rights` VALUES ('8ec14879f4ff49b8b66b327d321eaf76', 'a23ffac219244049a45c35c7cf3de9dc', 26);
INSERT INTO `sys_role_rights` VALUES ('934a14c0777547c19ecc8f356c09c321', 'a23ffac219244049a45c35c7cf3de9dc', 12);
INSERT INTO `sys_role_rights` VALUES ('972f891526844fb0b12b76790ba3b596', 'a91e450f8f3148b0a13b915cab7d3bd1', 23);
INSERT INTO `sys_role_rights` VALUES ('afbbd9a9ed554921a8fef0cd2e0a7123', 'a23ffac219244049a45c35c7cf3de9dc', 27);
INSERT INTO `sys_role_rights` VALUES ('be750d4abc7c4c5ca2aa9ff6a453f23f', 'a91e450f8f3148b0a13b915cab7d3bd1', 20);
INSERT INTO `sys_role_rights` VALUES ('be86f1e6b18c447bac897b25a37f3092', 'a23ffac219244049a45c35c7cf3de9dc', 20);
INSERT INTO `sys_role_rights` VALUES ('d418c69a11f04374be9ae3b8eb2518fd', 'a91e450f8f3148b0a13b915cab7d3bd1', 17);
INSERT INTO `sys_role_rights` VALUES ('d5e7e3dfe08d48df803b743d7c7b298f', 'a23ffac219244049a45c35c7cf3de9dc', 21);
INSERT INTO `sys_role_rights` VALUES ('d8da54d28b874d6e8fd923ced3db40a0', 'a23ffac219244049a45c35c7cf3de9dc', 8);
INSERT INTO `sys_role_rights` VALUES ('e57192084ab94344bee34812ff7a4f9d', 'a91e450f8f3148b0a13b915cab7d3bd1', 27);
INSERT INTO `sys_role_rights` VALUES ('e63d5f02cc154391b363a1f7ed0bd9b4', 'a23ffac219244049a45c35c7cf3de9dc', 28);
INSERT INTO `sys_role_rights` VALUES ('ee7ef98ddd374023830925c7a6f89176', 'a91e450f8f3148b0a13b915cab7d3bd1', 21);
INSERT INTO `sys_role_rights` VALUES ('fa04af6c917c447589365040532b1ff4', 'a91e450f8f3148b0a13b915cab7d3bd1', 22);
INSERT INTO `sys_role_rights` VALUES ('facf1f2dbfa147f1858c7942ed3aef1f', 'a23ffac219244049a45c35c7cf3de9dc', 16);
INSERT INTO `sys_role_rights` VALUES ('fdde606b1d4b4e37a1a890fa49703200', 'a23ffac219244049a45c35c7cf3de9dc', 5);
INSERT INTO `sys_role_rights` VALUES ('fed9dff672e44d23b8bf87acee3af6c6', 'a23ffac219244049a45c35c7cf3de9dc', 10);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `userId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '名称',
  `locked` bit(1) NOT NULL COMMENT '锁定标识',
  `disable` bit(1) NOT NULL COMMENT '禁用标识',
  `specialRole` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '特殊权限',
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '$2a$10$UwdWOlIaEP7BKYBEsUBU9.3BQVat4upwz8s9Jp1W9j4e3qWJMXzCm', 'admin', b'0', b'0', 'ROLE_admin');
INSERT INTO `sys_user` VALUES ('b8e9ba17b06a4674b69342c93a7fd774', 'test', '$2a$10$ifNZ4DJwmS.d1/GtejoCa.fXzO625FB/XO6NLoU5ox.VtfqehSVHy', 'test', b'0', b'0', '');
INSERT INTO `sys_user` VALUES ('eda56955597847d39707fe0cfba4ebab', 'user', '$2a$10$pJlKA2REfYux5h2gYIGp4.PH5pd.Tl4kSnB9p/xID4UChOK7pShlq', 'user', b'0', b'0', 'ROLE_user');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `userId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `roleId` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('862944d73f9640c4942eee5a8ada5639', 'eda56955597847d39707fe0cfba4ebab', 'a91e450f8f3148b0a13b915cab7d3bd1');
INSERT INTO `sys_user_role` VALUES ('979cef4f08184372be3fe55501372472', '1', 'a91e450f8f3148b0a13b915cab7d3bd1');
INSERT INTO `sys_user_role` VALUES ('ce12ddb354c54704aabf456b8dd1238b', 'b8e9ba17b06a4674b69342c93a7fd774', 'a91e450f8f3148b0a13b915cab7d3bd1');
INSERT INTO `sys_user_role` VALUES ('f24992aead674928839c5bb80ddf8520', '1', 'a23ffac219244049a45c35c7cf3de9dc');

SET FOREIGN_KEY_CHECKS = 1;
