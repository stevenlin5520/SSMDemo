/*
SQLyog  v12.2.6 (64 bit)
MySQL - 5.7.17-log : Database - db_bike_mis
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_bike_mis` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_bike_mis`;

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `user_id` varchar(32) NOT NULL,
  `user_name` varchar(10) NOT NULL DEFAULT 'Cusmer' COMMENT '用户名',
  `user_pwd` varchar(32) NOT NULL COMMENT '密码',
  `user_phone` varchar(16) NOT NULL COMMENT '手机号码',
  `user_type` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户类型：0/1/2/3|普通/财务管理员/管理员/超级管理员',
  `user_photo` varchar(40) DEFAULT NULL COMMENT '用户头像：',
  `user_sex` bigint(20) NOT NULL DEFAULT '0' COMMENT '性别：0/1|男/女',
  `user_address` varchar(32) DEFAULT NULL COMMENT '地址',
  `user_birthday` date DEFAULT NULL COMMENT '出生日期',
  `user_email` varchar(30) DEFAULT NULL COMMENT '个性签名',
  `user_describe` text COMMENT '描述',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`user_id`,`user_name`,`user_pwd`,`user_phone`,`user_type`,`user_photo`,`user_sex`,`user_address`,`user_birthday`,`user_email`,`user_describe`) values 
('12d1sfssdfs','root','root','15488454654',3,'user_admin_72px',1,'黑龙江省-哈尔滨市-平房区','2016-05-11','steven@163.com','超管的用户签名'),
('156412sdfs','admin','123456','12345671544',3,'user_admin_72px',1,'江苏省-南京市-雨花台区','2017-06-19','admin@163.com','这个家伙很懒，不愿意动弹'),
('4554af5as156fa14faasdada','管理员','admin','17756485624',1,NULL,1,'黑龙江省-哈尔滨市-南岗区','2018-07-01','admin@gmail.com','我是管理员，我热爱工作'),
('536e7d5e055f4f3a91285f626630d51e','test','13579','12121212121',0,NULL,0,NULL,NULL,NULL,'你好呀，这家伙很烂漫的，你要萌萌哒。。。。'),
('5454fa5fasf5as5f5a15f51451a5s6d','guanliyuan','admin','17745608546',1,NULL,0,'贵州省-遵义市-赤水市','1990-07-01','admin@gmail.com','我是遵义人，我爱吃豆花'),
('57fa0efdea5349539851cc5a65aa1d70','小强','xiaoqiang4sb','17745602154',0,NULL,0,NULL,NULL,NULL,NULL),
('5c29eeb5d250437e97c1a8849fbd379b','韩晓峰','232323','12456245895',0,NULL,1,NULL,NULL,NULL,NULL),
('dsfgs5fs45fsgs545138614854135415','gusmer','gusmer','12563589654',0,NULL,0,'四川省-泸州市-合江县','2004-06-09','gusmer@outlook.com','你好啊，小屁孩，我是顾客，你要好好服务我');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
