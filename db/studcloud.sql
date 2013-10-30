/*
SQLyog - Free MySQL GUI v5.0
Host - 5.0.45-community-nt : Database - studcloud
*********************************************************************
Server version : 5.0.45-community-nt
*/


create database if not exists `studcloud`;

USE `studcloud`;

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `username` varchar(100) default NULL,
  `password` varchar(100) default NULL,
  `usertype` varchar(500) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert into `login` values 
('admin','admin','Admin'),
('rajkarekar','iiii','school'),
('ppgh','userll','school'),
('ppgh','user','school');

/*Table structure for table `schoolreg` */

DROP TABLE IF EXISTS `schoolreg`;

CREATE TABLE `schoolreg` (
  `code` varchar(50) default NULL,
  `schoolname` varchar(500) default NULL,
  `address` varchar(500) default NULL,
  `phone` varchar(20) default NULL,
  `stdfrm` varchar(10) default NULL,
  `stdto` varchar(10) default NULL,
  `contactperson` varchar(100) default NULL,
  `cpmob` varchar(50) default NULL,
  `principal` varchar(100) default NULL,
  `pmob` varchar(50) default NULL,
  `sch_username` varchar(100) default NULL,
  `sch_password` varchar(100) default NULL,
  `registerOn` varchar(50) default NULL,
  `registerBy` varchar(50) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `schoolreg` */

insert into `schoolreg` values 
('101','Padmaraje Highschool','Petala Kolhapur','0231111111','01','04','Mr.Shinde','888888888','Mrs.Patil','999999999','ppgh','user',NULL,NULL);

/*Table structure for table `schoralshipexamnotice` */

DROP TABLE IF EXISTS `schoralshipexamnotice`;

CREATE TABLE `schoralshipexamnotice` (
  `srno` int(250) NOT NULL auto_increment,
  `std` varchar(100) default '00',
  `exam` varchar(1000) default NULL,
  `examdate` varchar(1000) default NULL,
  `examtime` varchar(1000) default NULL,
  `attachment` varchar(1000) default NULL,
  PRIMARY KEY  (`srno`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `schoralshipexamnotice` */

insert into `schoralshipexamnotice` values 
(1,'01','Beginner','23-09-2013','10.30 A.M.','Android-Tutorial.pdf'),
(2,'01','Intermigate','33','55','Android-Tutorial.pdf'),
(3,'01','Beginner','5','7','Android-Tutorial.pdf');

/*Table structure for table `subjectlist` */

DROP TABLE IF EXISTS `subjectlist`;

CREATE TABLE `subjectlist` (
  `subid` int(50) NOT NULL auto_increment,
  `subject` varchar(100) default NULL,
  `standard` varchar(50) default '00',
  `subAddOn` varchar(50) default NULL,
  `subAddBy` varchar(100) default NULL,
  `category` varchar(500) default NULL,
  PRIMARY KEY  (`subid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

/*Data for the table `subjectlist` */

insert into `subjectlist` values 
(2,'MARATHI','01','13-09-13','admin','std'),
(3,'MATHS','01','13-09-13','admin','std'),
(4,'ENGLISH','01','13-09-13','admin','std'),
(5,'SCIENCE','01','13-09-13','admin','std'),
(6,'MARATHI','02','13-09-13','admin','std'),
(7,'ENGLISH','02','13-09-13','admin','std'),
(8,'SCIENCE','02','13-09-13','admin','std'),
(9,'MATHS','02','13-09-13','admin','std'),
(10,'MARATHI','03','13-09-13','admin','std'),
(11,'MARATHI','04','13-09-13','admin','std'),
(12,'MATHS','03','13-09-13','admin','std'),
(13,'MATHS','04','13-09-13','admin','std'),
(14,'ENGLISH','03','13-09-13','admin','std'),
(16,'SCIENCE','03','13-09-13','admin','std'),
(17,'HISTORY','03','13-09-13','admin','std'),
(18,'GEOGRAPHY','03','13-09-13','admin','std'),
(19,'ENGLISH','04','13-09-13','admin','std'),
(20,'SCIENCE','04','13-09-13','admin','std'),
(21,'HISTORY','04','13-09-13','admin','std'),
(22,'GEOGRAPHY','04','13-09-13','admin','std'),
(23,'MARATHI','00','13-09-2013','admin','gen'),
(24,'ENGLISH','00','13-09-13','admin','gen'),
(25,'SCIENCE','00','13-09-13','admin','gen'),
(26,'MATHS','00','13-09-13','admin','gen'),
(27,'HISTORY','00','13-09-13','admin','gen');

/*Table structure for table `subjectstdwise` */

DROP TABLE IF EXISTS `subjectstdwise`;

CREATE TABLE `subjectstdwise` (
  `subid` decimal(20,0) NOT NULL,
  `standard` decimal(50,0) default NULL,
  `subject` varchar(100) default NULL,
  `subAddOn` varchar(50) default NULL,
  `subAddBy` varchar(100) default NULL,
  PRIMARY KEY  (`subid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `subjectstdwise` */

/*Table structure for table `tabregister` */

DROP TABLE IF EXISTS `tabregister`;

CREATE TABLE `tabregister` (
  `macadd` varchar(500) NOT NULL,
  `studname` varchar(500) default NULL,
  `address` varchar(500) default NULL,
  `contactno` decimal(50,0) default NULL,
  `stdcode` varchar(100) default NULL,
  `studcode` varchar(100) default NULL,
  `schoolcode` varchar(100) default NULL,
  `tabAddby` varchar(100) default NULL,
  `tabAddon` varchar(100) default NULL,
  PRIMARY KEY  (`macadd`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tabregister` */

insert into `tabregister` values 
('00:c3:b1:12:70:4a','Prakash Patil','Kolhapur','9975956334','01',NULL,NULL,NULL,NULL),
('09084','dfd','dfdf','0','dfdf','dfdf','fdfd','admin','09-09-13'),
('09985','fff','fff','0','fdf','fd','df','admin','03-03-13'),
('1234','ss','dd','23','01',NULL,NULL,NULL,NULL),
('12345','ee','ww','233','03',NULL,NULL,NULL,NULL),
('234','sdsd','ssds`','112','01',NULL,NULL,NULL,NULL);

/*Table structure for table `uploadfile` */

DROP TABLE IF EXISTS `uploadfile`;

CREATE TABLE `uploadfile` (
  `srno` int(250) NOT NULL auto_increment,
  `usercode` varchar(1000) default NULL,
  `std` varchar(100) default '00',
  `subjectcode` varchar(1000) default NULL,
  `title` varchar(1000) default NULL,
  `filename` varchar(1000) default NULL,
  `filepath` varchar(1000) default NULL,
  `filedoctype` varchar(1000) default NULL,
  `isStd` varchar(100) default 'N',
  `isGeneral` varchar(100) default 'N',
  PRIMARY KEY  (`srno`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Data for the table `uploadfile` */

insert into `uploadfile` values 
(13,'admin','01','Hindi','hh','imgicon.png','uploads/Admin/Images/Standard/01/Hindi','Images','Y','N'),
(14,'admin','00','MARATHI','gen','videos.png','uploads/Admin/Images/Standard/00/Marathi','Images','N','Y'),
(15,'admin','01','MARATHI','vvd','Wildlife.wmv','uploads/Admin/Video/Standard/01/MARATHI','Video','Y','N'),
(16,'admin','01','MARATHI','eb','xyz.pdf','uploads/Admin/E-Books/Standard/01/MARATHI','E-Books','Y','N'),
(17,'admin','00','MARATHI','hh','Wildlife.wmv','uploads/Admin/Video/Standard/00/MARATHI','Video','N','Y'),
(18,'admin','00','MARATHI','ii','Koala.jpg','uploads/Admin/Images/Standard/00/MARATHI','Images','N','Y');

/*Table structure for table `uploadnotification` */

DROP TABLE IF EXISTS `uploadnotification`;

CREATE TABLE `uploadnotification` (
  `srno` int(250) NOT NULL auto_increment,
  `notice` varchar(1000) default NULL,
  `pdf_file` varchar(1000) default NULL,
  `filepath` varchar(1000) default NULL,
  `std` varchar(100) default '00',
  `usercode` varchar(1000) default NULL,
  `isStd` varchar(100) default 'N',
  `isGen` varchar(100) default 'N',
  PRIMARY KEY  (`srno`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `uploadnotification` */

insert into `uploadnotification` values 
(1,'rt','Advt No. 1_2013-29sept.pdf','uploads/Admin/Notification/Standard/01','01','admin','Y','N'),
(2,'gen','Android-Tutorial.pdf','uploads/Admin/Notification/Standard/00','00','admin','N','Y');
