
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict`(
	`ID` int(11) NOT NULL AUTO_INCREMENT, 
	`CODE` varchar(255) DEFAULT NULL,
	`NAME` varchar(255) DEFAULT NULL,
	`LABEL` varchar(255) DEFAULT NULL,
	`VALUE` varchar(255) DEFAULT NULL,
	`TYPE` varchar(255) DEFAULT NULL,
	`DESCRIPTION` varchar(255) DEFAULT NULL,
	`SORT` int(11) DEFAULT NULL,
	`REMARK` varchar(255) DEFAULT NULL,
	`DEL_FLAG` varchar(255) DEFAULT NULL,
	PRIMARY KEY(`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `sys_configuration`;
CREATE TABLE `sys_configuration`(
	`ID` int(11) NOT NULL AUTO_INCREMENT, 
	`CODE` varchar(255) DEFAULT NULL,
	`VALUE` varchar(255) DEFAULT NULL,
	`TYPE` varchar(255) DEFAULT NULL,
	`DESCRIPTION` varchar(255) DEFAULT NULL,
	PRIMARY KEY(`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `OPERATION_CODE` varchar(50) NOT NULL COMMENT '操作编码',
  `CREATER` varchar(20) DEFAULT NULL COMMENT '操作用户名称',
  `CREATE_DATE` datetime NOT NULL COMMENT '日志生成时间',
  `TYPE` int(11) DEFAULT NULL COMMENT '日志类型: 1：安全日志 2：表示操作日志',
  `OS` varchar(20) DEFAULT NULL,
  `BROWSER` varchar(20) DEFAULT NULL COMMENT '浏览器类型',
  `IP` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  `MAC` varchar(20) DEFAULT NULL COMMENT '物理地址',
  `EXECUTE_TIME` int(11) DEFAULT NULL COMMENT '执行时间',
  `DESCRIPTION` varchar(500) DEFAULT NULL COMMENT '详细描述',
  `REQUEST_PARAM` varchar(500) DEFAULT NULL COMMENT '请求参数',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2267 DEFAULT CHARSET=utf8 COMMENT='日录资料表';


-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOGIN_NAME` varchar(20) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `PASSWORD` varchar(255) NOT NULL,
  `SALT` varchar(255) DEFAULT NULL,
  `BIRTHDAY` datetime DEFAULT NULL,
  `GENDER` smallint(6) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `PHONE` varchar(20) DEFAULT NULL,
  `ICON` varchar(500) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `STATE` char(1) DEFAULT NULL,
  `DESCRIPTION` text,
  `LOGIN_COUNT` int(11) DEFAULT NULL,
  `PREVIOUS_VISIT` datetime DEFAULT NULL,
  `LAST_VISIT` datetime DEFAULT NULL,
  `DEL_FLAG` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(20) NOT NULL,
  `ROLE_CODE` varchar(20) NOT NULL,
  `DESCRIPTION` text,
  `SORT` smallint(6) DEFAULT NULL,
  `DEL_FLAG` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `PID` int(11) DEFAULT NULL COMMENT '父节点名称',
  `NAME` varchar(50) NOT NULL COMMENT '名称',
  `TYPE` varchar(20) DEFAULT NULL COMMENT '类型:菜单or功能',
  `SORT` int(11) DEFAULT NULL COMMENT '排序',
  `URL` varchar(255) DEFAULT NULL,
  `PERM_CODE` varchar(50) DEFAULT NULL COMMENT '菜单编码',
  `ICON` varchar(255) DEFAULT NULL,
  `STATE` varchar(10) DEFAULT NULL,
  `DESCRIPTION` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_USER_ROL_REFERENCE_ROLE` (`ROLE_ID`) USING BTREE,
  KEY `FK_USER_ROL_REFERENCE_USERS` (`USER_ID`) USING BTREE,
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`),
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`USER_ID`) REFERENCES `user` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` int(11) DEFAULT NULL,
  `PERMISSION_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_ROLE_PER_REFERENCE_PERMISSI` (`PERMISSION_ID`) USING BTREE,
  KEY `FK_ROLE_PER_REFERENCE_ROLE` (`ROLE_ID`) USING BTREE,
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`PERMISSION_ID`) REFERENCES `permission` (`ID`),
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user_token
-- ----------------------------
DROP TABLE IF EXISTS `user_token`;
CREATE TABLE `user_token` (
  `ID` bigint(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) DEFAULT NULL,
  `DEVICE_ID` int(11) DEFAULT NULL,
  `TOKEN` varchar(500) NOT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `STATE` varchar(10) DEFAULT NULL,
  `REMARK` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for url_permission
-- ----------------------------
DROP TABLE IF EXISTS `url_permission`;
CREATE TABLE `url_permission` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `URL` varchar(500) NOT NULL,
  `PERMISSION_ID` int(11) DEFAULT NULL,
  `ROLE_ID` int(11) DEFAULT NULL,
  `REMARK` text,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


