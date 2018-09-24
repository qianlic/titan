/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.7.12 : Database - titan
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`titan` /*!40100 DEFAULT CHARACTER SET utf8 */;

/*Table structure for table `clr_crawler` */

DROP TABLE IF EXISTS `clr_crawler`;

CREATE TABLE `clr_crawler` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `resumable` bit(1) DEFAULT NULL,
  `seed` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `clr_crawler` */

/*Table structure for table `clr_page` */

DROP TABLE IF EXISTS `clr_page`;

CREATE TABLE `clr_page` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` tinyblob,
  `contentCharset` varchar(255) DEFAULT NULL,
  `contentEncoding` varchar(255) DEFAULT NULL,
  `contentType` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `redirect` bit(1) NOT NULL,
  `redirectedToUrl` varchar(255) DEFAULT NULL,
  `statusCode` int(11) NOT NULL,
  `urlId` bigint(20) NOT NULL,
  `status` bit(1) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `clr_page` */

/*Table structure for table `clr_weburl` */

DROP TABLE IF EXISTS `clr_weburl`;

CREATE TABLE `clr_weburl` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `anchor` varchar(255) DEFAULT NULL,
  `depth` smallint(6) NOT NULL,
  `domain` varchar(255) DEFAULT NULL,
  `parentUrl` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `subDomain` varchar(255) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `clr_weburl` */

/*Table structure for table `qtz_blob_triggers` */

DROP TABLE IF EXISTS `qtz_blob_triggers`;

CREATE TABLE `qtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `qtz_blob_triggers` */

/*Table structure for table `qtz_calendars` */

DROP TABLE IF EXISTS `qtz_calendars`;

CREATE TABLE `qtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `qtz_calendars` */

/*Table structure for table `qtz_cron_triggers` */

DROP TABLE IF EXISTS `qtz_cron_triggers`;

CREATE TABLE `qtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `qtz_cron_triggers` */

insert  into `qtz_cron_triggers`(`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`CRON_EXPRESSION`,`TIME_ZONE_ID`) values ('scheduler','成vv','GROUPA','0 30 9 1 * ?','Asia/Shanghai');

/*Table structure for table `qtz_fired_triggers` */

DROP TABLE IF EXISTS `qtz_fired_triggers`;

CREATE TABLE `qtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `qtz_fired_triggers` */

/*Table structure for table `qtz_job_details` */

DROP TABLE IF EXISTS `qtz_job_details`;

CREATE TABLE `qtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `qtz_job_details` */

insert  into `qtz_job_details`(`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`,`DESCRIPTION`,`JOB_CLASS_NAME`,`IS_DURABLE`,`IS_NONCONCURRENT`,`IS_UPDATE_DATA`,`REQUESTS_RECOVERY`,`JOB_DATA`) values ('scheduler','顶顶顶顶','GROUPA','dasda','com.cjwx.titan.quartz.execute.BaseExecService','1','0','0','0','��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0DATApt\0METHODt\0ssst\0SERVICEt\0aaax\0'),('scheduler','J_null','DEFAULT',NULL,'com.cjwx.titan.quartz.execute.BaseExecService','1','0','0','0','��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0jobParamt\0{}x\0'),('scheduler','ddddd','www','dada','com.cjwx.titan.quartz.execute.BaseExecService','1','0','0','0','��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0jobParamt\0{}t\0DATApt\0METHODt\0aaat\0SERVICEt\0打打x\0'),('scheduler','tttt','GROUPA','dddd','com.cjwx.titan.quartz.execute.BaseExecService','1','0','0','0','��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0DATAt\011111t\0METHODt\0asdt\0SERVICEt\0asdx\0');

/*Table structure for table `qtz_locks` */

DROP TABLE IF EXISTS `qtz_locks`;

CREATE TABLE `qtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `qtz_locks` */

insert  into `qtz_locks`(`SCHED_NAME`,`LOCK_NAME`) values ('scheduler','STATE_ACCESS'),('scheduler','TRIGGER_ACCESS');

/*Table structure for table `qtz_paused_trigger_grps` */

DROP TABLE IF EXISTS `qtz_paused_trigger_grps`;

CREATE TABLE `qtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `qtz_paused_trigger_grps` */

/*Table structure for table `qtz_scheduler_state` */

DROP TABLE IF EXISTS `qtz_scheduler_state`;

CREATE TABLE `qtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `qtz_scheduler_state` */

insert  into `qtz_scheduler_state`(`SCHED_NAME`,`INSTANCE_NAME`,`LAST_CHECKIN_TIME`,`CHECKIN_INTERVAL`) values ('scheduler','CJWX-PC1537449478844',1537452412688,20000);

/*Table structure for table `qtz_simple_triggers` */

DROP TABLE IF EXISTS `qtz_simple_triggers`;

CREATE TABLE `qtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `qtz_simple_triggers` */

/*Table structure for table `qtz_simprop_triggers` */

DROP TABLE IF EXISTS `qtz_simprop_triggers`;

CREATE TABLE `qtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `qtz_simprop_triggers` */

/*Table structure for table `qtz_triggers` */

DROP TABLE IF EXISTS `qtz_triggers`;

CREATE TABLE `qtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

/*Data for the table `qtz_triggers` */

insert  into `qtz_triggers`(`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`JOB_NAME`,`JOB_GROUP`,`DESCRIPTION`,`NEXT_FIRE_TIME`,`PREV_FIRE_TIME`,`PRIORITY`,`TRIGGER_STATE`,`TRIGGER_TYPE`,`START_TIME`,`END_TIME`,`CALENDAR_NAME`,`MISFIRE_INSTR`,`JOB_DATA`) values ('scheduler','成vv','GROUPA','顶顶顶顶','GROUPA','dadas',1535765400000,-1,1,'PAUSED','CRON',1533719364000,0,NULL,0,'');

/*Table structure for table `sys_image` */

DROP TABLE IF EXISTS `sys_image`;

CREATE TABLE `sys_image` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `filename` varchar(255) DEFAULT NULL,
  `storename` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `hash` varchar(255) DEFAULT NULL,
  `height` varchar(255) DEFAULT NULL,
  `width` varchar(255) DEFAULT NULL,
  `size` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `sys_image` */

insert  into `sys_image`(`id`,`filename`,`storename`,`path`,`hash`,`height`,`width`,`size`,`status`,`ts`) values (2,'3_140701151649_6','5b9c6b4e4b7ae.jpg','/2018/09/15/5b9c6b4e4b7ae.jpg','n3QtVZDsRUcak8E','815','600','199317','','2018-09-15 10:15:46'),(3,'timg','5b9c6ba1607fb.jpg','/2018/09/15/5b9c6ba1607fb.jpg','sgOdxvV79CDSjhB','551','378','44092','','2018-09-15 10:17:09'),(4,'timg','5b9c6bd5cdf8a.jpg','/2018/09/15/5b9c6bd5cdf8a.jpg','2rDIYx5PZ6wbXsH','456','311','26277','','2018-09-15 10:18:02'),(5,'u=3975855483,3677230256&fm=11&gp=0','5b9c6c6523a2a.jpg','/2018/09/15/5b9c6c6523a2a.jpg','lRJ6eo7WHtgYjUx','714','478','43621','','2018-09-15 10:20:25'),(6,'ak','5b9c6ea41e20e.jpeg','/2018/09/15/5b9c6ea41e20e.jpeg','uKFYbzpiymGB3IA','398','224','23083','','2018-09-15 10:30:00'),(8,'1-1506261A031','5b9cad69cf60b.jpg','/2018/09/15/5b9cad69cf60b.jpg','gyANUeI5J8nr6Wl','512','852','64381','','2018-09-15 14:57:50'),(9,'ak','5b9caf2e61d26.jpeg','/2018/09/15/5b9caf2e61d26.jpeg','xM8vgdyY2awNuBe','398','224','23083','','2018-09-15 15:05:39'),(10,'3_140701151649_6','5b9caf785c4f5.jpg','/2018/09/15/5b9caf785c4f5.jpg','XtmGe9sSzniNxkq','815','600','199317','','2018-09-15 15:06:36'),(11,'1-1506261A031','5b9caf8403e7f.jpg','/2018/09/15/5b9caf8403e7f.jpg','hWyUeBMSwNHsDRz','512','852','64381','','2018-09-15 15:06:48'),(12,'u=4265221231,1232132060&fm=200&gp=0','5ba1ee68668f3.jpg','/2018/09/19/5ba1ee68668f3.jpg','IQ5i3tCDyJKwm8o','500','500','12671','','2018-09-19 14:36:27'),(13,'u=4265221231,1232132060&fm=200&gp=0','5ba1eec6aabbf.jpg','/2018/09/19/5ba1eec6aabbf.jpg','vN8ZLzCd2XrOeQI','320','320','19518','','2018-09-19 14:38:01');

/*Table structure for table `sys_resource` */

DROP TABLE IF EXISTS `sys_resource`;

CREATE TABLE `sys_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resourcecode` varchar(50) DEFAULT NULL,
  `resourcename` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `type` char(2) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `ico` varchar(255) DEFAULT NULL,
  `level` int(10) DEFAULT NULL,
  `parentid` int(20) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

/*Data for the table `sys_resource` */

insert  into `sys_resource`(`id`,`resourcecode`,`resourcename`,`description`,`type`,`url`,`ico`,`level`,`parentid`,`status`,`ts`) values (1,'system','系统管理','系统管理','1','','navicon-round',1,0,'',NULL),(11,'user','用户管理','系统管理-用户管理','2',NULL,'person',2,1,'',NULL),(12,'role','角色管理','系统管理-角色管理','2','/system/role/list','person-stalker',2,1,'',NULL),(13,'resource','资源管理','系统管理-资源管理','2','/system/resource/list','ios-keypad',2,1,'',NULL),(18,'monitor','系统监控','系统监控','1',NULL,'navicon-round',1,0,'',NULL),(27,'thread','线程管理','系统监控-线程管理','2','/system/thread','ionic',2,18,'',NULL),(20,'tomcat','TOMCAT','系统监控-TOMCAT','2','/system/chgpwd','ios-color-filter',2,18,'',NULL),(26,'redis','REDIS','系统监控-REDIS','2','/system/crawler','help-buoy',2,18,'',NULL),(28,'memory','内存管理','系统监控-内存管理','2','/system/memory','ios-pie',2,18,'',NULL),(33,'ROLE_LIST','角色列表','系统管理-角色管理-列表','3','/system/role/list',NULL,0,12,'',NULL),(29,'USER_LIST','用户列表','系统管理-用户管理-列表','3','/system/user/list',NULL,0,11,'',NULL),(30,'USER_STATUS','用户状态','系统管理-用户管理-状态','3','/system/user/status',NULL,0,11,'',NULL),(31,'USER_CREATE','用户新增','系统管理-用户管理-新增','3','/system/user/create',NULL,0,11,'',NULL),(34,'USER_REMOVE','用户删除','系统管理-用户管理-删除','3','/system/user/remove',NULL,0,11,'',NULL),(35,'USER_PASSWORD','用户密码','系统管理-用户管理-密码','3','/system/user/password',NULL,0,11,'',NULL),(36,'USER_EDIT','用户编辑','系统管理-用户管理-编辑','3','/system/user/edit',NULL,0,11,'',NULL),(37,'ROLE_CREATE','角色新增','系统管理-角色管理-新增','3','/system/role/create',NULL,0,12,'',NULL),(38,'ROLE_EDIT','角色编辑','系统管理-角色管理-编辑','3','/system/role/edit',NULL,0,12,'',NULL),(39,'ROLE_STATUS','角色状态','系统管理-角色管理-状态','3','/system/role/status',NULL,0,12,'',NULL),(40,'ROLE_REMOVE','角色删除','系统管理-角色管理-删除','3','/system/role/remove',NULL,0,12,'',NULL),(41,'ROLE_AVAILABLE','角色全部','系统管理-角色管理-全部','3','/system/role/availablelist',NULL,0,12,'',NULL),(42,'RESOURCE_LIST','资源列表','系统管理-资源管理-列表','3','/system/resource/list',NULL,0,13,'',NULL),(43,'RESOURCE_CREATE','资源创建','系统管理-资源管理-新增','3','/system/resource/create',NULL,0,13,'',NULL),(44,'RESOURCE_EDIT','资源编辑','系统管理-资源管理-编辑','3','/system/resource/edit',NULL,0,13,'',NULL),(45,'RESOURCE_STATUS','资源状态','系统管理-资源管理-状态','3','/system/resource/status',NULL,0,13,'',NULL),(46,'RESOURCE_REMOVE','资源删除','系统管理-资源管理-删除','3','/system/resource/remove',NULL,0,13,'',NULL),(47,'RESOURCE_AVAILABLE','资源全部','系统管理-资源管理-全部','3','/system/resource/availablelist',NULL,0,13,'',NULL),(48,'quartz','定时任务','定时任务','1',NULL,'navicon-round',0,0,'',NULL),(49,'jobdetail','任务管理','定时任务-任务管理','2',NULL,'ios-fastforward',0,48,'',NULL),(50,'trigger','触发器管理','定时任务-触发器管理','2',NULL,'ios-time',0,48,'',NULL),(51,'crawler','网页爬虫','网页爬虫','1',NULL,'navicon-round',0,0,'','2018-08-13 17:24:43'),(52,'crawler','爬虫管理','网页爬虫-爬虫管理','2',NULL,'bug',0,51,'','2018-08-13 17:26:17'),(53,'weburl','页面管理','网页爬虫-页面管理','2',NULL,'link',0,51,'','2018-08-15 10:28:58'),(54,'image','图片管理','系统管理-图片管理','2',NULL,'image',0,1,'','2018-09-15 13:55:23');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rolecode` varchar(50) DEFAULT NULL,
  `rolename` varchar(100) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `resourceids` varchar(255) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=390 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`rolecode`,`rolename`,`description`,`resourceids`,`status`,`ts`) values (177,'manager','世界政府','维护世界秩序','12,13,11,20,26,28,1,18,27,33,29,30,44,42,41,40,39,38,37,36,35,34,31,46,47,45,43','',NULL),(179,'White Beard Pirates','白胡子海贼团','白胡子海贼团','11,12,13,20,26,28,29,1','',NULL),(11,'Big Mom Pirates','BIG·MOM海贼团','BIG·MOM海贼团','12,11,13,28,26,20,29,30,37,43','',NULL),(12,'Beasts Pirates','百兽海贼团','凯多海贼团','12,13,11,20,26,28','',NULL),(14,'Red Hair Pirates','红发海贼团','红发海贼团','31,34,35,36,29,30,11','',NULL),(16,'Black Beard Pirates','黑胡子海贼团','监控','12,13,28,26,20,11,29,30,31,34,35,36','',NULL),(17,'marine','海军','管理','12,13','',NULL),(386,'test','测试','啊啊撒','11,1,29,12,33,27,18','',NULL);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usercode` varchar(50) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `salt` varchar(32) DEFAULT NULL,
  `sex` char(2) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `mobile` char(12) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `imgurl` varchar(255) DEFAULT NULL,
  `lastlogintime` datetime DEFAULT NULL,
  `roleids` text,
  `status` bit(1) DEFAULT NULL,
  `ts` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`usercode`,`username`,`password`,`salt`,`sex`,`birthday`,`mobile`,`email`,`imgurl`,`lastlogintime`,`roleids`,`status`,`ts`) values (3,'namei','娜美','79722ca3f790560d2b849ad1f824ab65','ccdb4f2b724114a70db344001a3d16ad','F','1970-01-30 08:00:00','12345678910',NULL,'https://i.loli.net//2018/09/15/5b9c6c6523a2a.jpg','2018-07-05 15:35:04','177,179,11,12,14,16,17','',NULL),(4,'qiaoba','乔巴','79722ca3f790560d2b849ad1f824ab65','ccdb4f2b724114a70db344001a3d16ad','M','1970-01-08 08:00:00','12345678910',NULL,'https://i.loli.net//2018/09/15/5b9c6b4e4b7ae.jpg',NULL,'177,179,11,12,14,16,17,31,32,34,36,37,39,61,62,64,66,67,69,82,84,112,114,117,119,131,132,134,136,137,259,271,272,274,276,277,279,301,302,304','',NULL),(2,'suolo','索隆','79722ca3f790560d2b849ad1f824ab65','ccdb4f2b724114a70db344001a3d16ad','M','1970-01-15 08:00:00','12345678910',NULL,'https://i.loli.net//2018/09/15/5b9c6bd5cdf8a.jpg','2018-09-19 15:12:58','177,179,11,12,14,16,17,31,32,34,36,37,39,61,62,64,66,67,69,82,84,112,114,117,119,131,132,134,136,137,259,271,272,274,276,277,279,301,302,304','',NULL),(1,'lufei','路飞','ec831849e9d39d487319df8b09c589e1','135576a35439731172a73eae0fd080ae','M','2010-01-20 08:00:00','12345678910',NULL,'https://i.loli.net//2018/09/15/5b9c6ba1607fb.jpg','2018-08-27 15:49:08','177,179,11,12,14,16,17,31,32,34,36,37,39,61,62,64,66,67,69,82,84,112,114,117,119,131,132,134,136,137,259,271,272,274,276,277,279,301,302,304','\0',NULL),(9,'admin','系统管理员','49962d5181aa4adcd4786938cff39bb8','f97baedff1fa57022779dc5438d5227a','M','1970-01-13 08:00:00','125555513123','12345','https://i.loli.net//2018/09/19/5ba1eec6aabbf.jpg','2018-09-20 21:50:12','177,179,11,12,14,16,17,31,32,34,36,37,39,61,62,64,66,67,69,82,84,112,114,117,119,131,132,134,136,137,259,271,272,274,276,277,279,301,302,304','',NULL),(39,'test','测试','65a71b0517b4580c0940d0d553373826','c5db56eacd16873ee3876c8ff36dc247','F','2018-07-02 08:00:00','12345678910',NULL,NULL,'2018-09-19 15:00:56','386','',NULL),(40,'test2','测试','34598373f24ddaaef1cb4c626f653e18','a0c617dd58d45c3f32f16b0b2f063743','M','2018-07-12 08:00:00','23211111111',NULL,NULL,NULL,'177,179,11,12,14,16,17,386','\0',NULL),(42,'cccccc','顶顶顶顶','6b6bf0c38e1b989e677c2613c66a4d95','c93ffbcc6bee8b61939761ee22a45226','M','2018-09-10 08:00:00',NULL,NULL,'https://i.loli.net/2018/09/07/5b923f842ecff.jpeg',NULL,'177,179,11,12,14,16,17,386','','2018-09-12 16:24:34'),(43,'testccc','cess','b8d73fbe8412fe630dd4690a8cc0c164','a704116cfd098f71704a34dbe0d13b5a','M','2015-03-27 08:00:00','weqeqeq','31313qeq',NULL,NULL,'177,179,11,12,14,16,17,386','','2018-09-17 13:54:53'),(44,'vvvvvvvvvv','dadas','d58c0c63bbf9d13e964acde05c00fbcd','3d57077379bdcca2fa2455a35212b07d',NULL,NULL,'123123131','edada',NULL,NULL,'177,179,11,12,14,16,17,386','\0','2018-09-17 13:55:10'),(45,'gggggggggggg','dasda','5234728928165e7ec59a2e001791915a','9401089074b92c06e1bf616a42e6809b','M',NULL,'31313132','qeqe',NULL,NULL,'177,179,11,12,14,16,17,386','','2018-09-17 13:55:22');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
