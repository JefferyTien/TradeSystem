
insert  into `dict`(`ID`,`CODE`,`NAME`,`LABEL`,`VALUE`,`TYPE`,`DESCRIPTION`,`SORT`,`REMARK`,`DEL_FLAG`) values (13,'order_status',NULL,'未开始','1','order_module','订单模块参数1',NULL,'',NULL),(14,'order_status','订单状态','已开始','2','order_module','订单模块参数',2,'',NULL),(15,'order_status','订单状态','已完成','3','order_module','订单模块参数',3,'',NULL),(16,'order_status','订单状态','订单取消','4','order_module','订单模块参数',4,'',NULL);

insert  into `permission`(`ID`,`PID`,`NAME`,`TYPE`,`SORT`,`URL`,`PERM_CODE`,`ICON`,`STATE`,`DESCRIPTION`) values (1,NULL,'系统管理','F',1,'','','icon-standard-cog','',''),(2,1,'角色管理','F',3,'system/role.do','','icon-hamburg-my-account',NULL,''),(3,1,'用户管理','F',2,'system/user.do','','icon-hamburg-user',NULL,''),(4,2,'添加','O',NULL,'','sys:role:add','','','角色添加'),(5,2,'删除','O',NULL,'','sys:role:delete','','','角色删除'),(6,2,'修改','O',NULL,'','sys:role:update','','','角色修改'),(7,3,'添加','O',NULL,'','sys:user:add','','','用户添加'),(8,3,'删除','O',NULL,'','sys:user:delete','','','用户删除'),(12,1,'权限管理','F',5,'system/permission.do','','icon-hamburg-login',NULL,''),(14,15,'数据源监控','F',6,'druid.do','','icon-hamburg-database','',''),(15,NULL,'系统监控','F',10,'',NULL,'icon-hamburg-graphic','',''),(16,3,'修改','O',NULL,'','sys:user:update','','','用户修改'),(20,15,'日志管理','F',7,'system/log.do','','icon-hamburg-archives','',''),(25,12,'添加','O',NULL,'','sys:perm:add','','','菜单添加'),(26,12,'修改','O',NULL,'','sys:perm:update','','','菜单修改'),(27,12,'删除','O',NULL,'','sys:perm:delete','','','菜单删除'),(28,2,'查看','O',NULL,'','sys:role:view','','','角色查看'),(29,3,'查看','O',NULL,'','sys:user:view','',NULL,'用户查看'),(30,12,'查看','O',NULL,'','sys:perm:view','',NULL,'权限查看'),(31,20,'删除','O',NULL,'','sys:log:delete','',NULL,'删除日志'),(32,20,'导出excel','O',NULL,'','sys:log:exportExcel','',NULL,'导出日志excel'),(33,3,'查看用户角色','O',NULL,'','sys:user:roleView','',NULL,'查看用户角色'),(34,2,'保存授权','O',NULL,'','sys:role:permUpd','',NULL,'保存修改的角色权限'),(35,3,'修改用户角色','O',NULL,'','sys:user:roleUpd','',NULL,'修改用户拥有的角色'),(36,2,'查看角色权限','O',NULL,'','sys:role:permView','',NULL,'查看角色拥有的权限'),(37,15,'定时任务管理','F',NULL,'system/scheduleJob.do','','icon-hamburg-full-time',NULL,'定时任务管理，支持集群'),(38,15,'cron表达式生成','F',NULL,'system/scheduleJob/quartzCron.do','','icon-hamburg-future',NULL,''),(39,1,'菜单管理','F',4,'system/permission/menu.do','','icon-hamburg-old-versions',NULL,''),(40,1,'字典管理','F',6,'system/dict.do',NULL,'icon-hamburg-address',NULL,'数据字典管理'),(45,39,'修改','O',NULL,'','sys:perm:update',NULL,NULL,'菜单管理'),(58,39,'添加','O',NULL,'','sys:perm:add',NULL,NULL,'菜单管理'),(59,39,'删除','O',NULL,'','sys:perm:delte',NULL,NULL,'菜单管理'),(61,40,'添加','O',NULL,'','sys:dict:add',NULL,NULL,'字典管理'),(62,40,'删除','O',NULL,'','sys:dict:delete',NULL,NULL,'字典管理'),(63,40,'修改','O',NULL,'','sys:dict:update',NULL,NULL,'字典管理'),(68,20,'查看','O',NULL,'','sys:log:view',NULL,NULL,'查看日志'),(69,40,'查看','O',NULL,'','sys:dict:view',NULL,NULL,'字典管理'),(70,39,'查看','O',NULL,'','sys:perm:menu:view',NULL,NULL,'菜单管理'),(72,NULL,'实验室','F',40,'',NULL,'icon-hamburg-delicious',NULL,'测试用'),(77,72,'测试','F',1,'lab/test.do',NULL,'icon-hamburg-world',NULL,NULL),(78,72,'Lottery','F',2,'lab/lottery.do',NULL,'icon-hamburg-world',NULL,NULL),(79,1,'修改密码','F',7,'system/user/updatePwd.do',NULL,'icon-standard-lock',NULL,NULL),(80,72,'订单管理','F',3,'order/find.do',NULL,'icon-hamburg-world',NULL,NULL),(81,72,'Dashboard','F',4,'lab/dashboard.do',NULL,'icon-hamburg-world',NULL,NULL),(82,1,'参数配置','F',8,'system/config.do',NULL,'icon-hamburg-config',NULL,NULL),(83,NULL,'工作流','F',60,'',NULL,'icon-hamburg-delicious',NULL,''),(84,NULL,'移动端管理','F',20,'',NULL,'icon-hamburg-phone',NULL,'');

insert  into `role`(`ID`,`NAME`,`ROLE_CODE`,`DESCRIPTION`,`SORT`,`DEL_FLAG`) values (1,'admin','admin','admin',2,NULL),(5,'guest','guest','guest',3,NULL),(13,'superadmin','superadmin','超级管理员',1,NULL),(14,'ddd','ddddd','dd',1,NULL);

insert  into `role_permission`(`ID`,`ROLE_ID`,`PERMISSION_ID`) values (1,1,2),(2,1,1),(28,5,1),(61,13,1),(62,13,3),(63,13,16),(64,13,7),(65,13,2),(66,13,4),(67,13,5),(68,13,6),(69,13,12),(70,13,25),(71,13,26),(72,13,27),(74,13,15),(75,13,14),(76,13,20),(77,13,8),(81,1,3),(88,1,12),(93,1,15),(94,1,14),(95,1,20),(118,1,28),(120,1,30),(121,1,31),(125,1,33),(126,1,36),(127,1,35),(129,1,34),(130,1,32),(133,5,15),(135,1,37),(142,1,38),(145,1,40),(147,1,29),(151,1,61),(152,1,62),(153,1,63),(162,5,39),(164,5,58),(176,5,40),(177,1,39),(178,1,58),(179,1,59),(184,1,6),(185,1,26),(186,1,27),(187,1,5),(189,1,25),(190,1,45),(191,1,7),(192,1,8),(193,1,16),(194,13,28),(195,13,34),(196,13,36),(197,13,29),(198,13,33),(199,13,35),(200,13,30),(201,13,39),(202,13,45),(203,13,58),(204,13,59),(205,13,40),(206,13,61),(207,13,62),(208,13,63),(209,13,31),(210,13,32),(211,13,37),(212,13,38),(213,1,69),(215,5,69),(216,5,20),(219,5,68),(220,5,38),(221,1,70),(222,5,70),(223,5,3),(227,5,29),(228,5,33),(229,5,35),(231,5,2),(234,5,28),(235,5,45),(236,5,59),(239,5,36),(240,1,68),(241,1,72),(242,1,77),(243,NULL,NULL),(244,NULL,NULL),(245,NULL,NULL),(246,14,7),(247,14,3),(248,14,1),(249,1,78),(250,1,79),(251,1,80),(252,1,81),(253,1,82),(254,1,83),(255,1,84),(256,1,4);

insert  into `user`(`ID`,`LOGIN_NAME`,`NAME`,`PASSWORD`,`SALT`,`BIRTHDAY`,`GENDER`,`EMAIL`,`PHONE`,`ICON`,`CREATE_DATE`,`STATE`,`DESCRIPTION`,`LOGIN_COUNT`,`PREVIOUS_VISIT`,`LAST_VISIT`,`DEL_FLAG`) values (1,'admin','admin','5bf578215ded853f5a857589eba77d9cb3c14f93','de3283313044a0f7','2019-10-21 00:00:00',1,'779205344@qq.com','123456789','aaa','2014-03-20 14:38:57','0','hh',129,'2015-01-16 10:22:19','2015-01-16 10:24:21',NULL),(3,'tianyu','tiany','1e8df4b59b3a3ab452ed1707ad3cb1a8e63a0630','bb2aa40007ad1238','2014-04-02 00:00:00',0,'','300','','2014-04-02 11:49:13','0',NULL,NULL,NULL,NULL,NULL),(5,'test','tyty11','dc6d230074477c8d736bfe0205260e9320565aa6','94886d7223c80850','2014-12-05 00:00:00',1,'','',NULL,'2014-12-05 11:55:03','1','ss',1,NULL,'2014-12-14 00:09:27',NULL),(6,'superadmin','超级管理员','df894ac0dd60772f22b5d67fe5d8b04fb4c9188d','97efb48ee6adff63','2015-01-15 00:00:00',1,'779205344@qq.com','13721035120',NULL,'2015-01-15 15:55:37',NULL,'超级管理员',NULL,NULL,NULL,NULL),(7,'test','testnick','d6147ea6ef23300d5c0e9dabe18623047401ef5e','68f239a002110b55',NULL,1,'JefferyTien@hotmail.com','15666971468',NULL,'2019-10-06 16:38:30',NULL,'miaoshu',NULL,NULL,NULL,NULL),(11,'hehe','hehe','c80d34b1f0bce947f9ae2528601463ea0d4fe5da','cd26aebc8712ba68',NULL,1,'cc','dd',NULL,'2019-10-20 12:29:29',NULL,NULL,NULL,NULL,NULL,NULL);

insert  into `user_role`(`ID`,`USER_ID`,`ROLE_ID`) values (1,1,1),(19,3,5),(32,5,5),(35,6,13),(36,6,1),(37,1,5);

insert  into `user_token`(`ID`,`USER_ID`,`DEVICE_ID`,`TOKEN`,`CREATE_DATE`,`STATE`,`REMARK`) values (5,1,1,'eyJhbGciOiJIUzI1NiJ9.eyJ1aWQiOiIxMjM0NTYiLCJzdWIiOiJ7dXNlcm5hbWU6J2FkbWluJ30iLCJ1c2VyX25hbWUiOiJhZG1pbiIsImlzcyI6IlJlZERvb3IiLCJpYXQiOjE1NzI4MTA0OTEsImp0aSI6ImZjNmJkNGE4LWNiYTQtNGM2Mi1iNjQxLTg1NDg2NmZiOGYwNCJ9.qUbelsNhJQH-D4d9I-j-EaVIlF7A6AK0YfyZaJ2XUh4','2019-11-04 03:48:12',NULL,NULL);


	
			