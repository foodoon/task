DROP DATABASE IF EXISTS `task`;
CREATE DATABASE `task` default charset=utf8 ;
use `task`;


delete from mysql.user where User = 'task_user';
grant select,update,delete,insert on `task`.* to 'task_user'@'%' identified by 'task_pwd';
grant select,update,delete,insert on `task`.* to 'task_user'@'localhost' identified by 'task_pwd';
flush privileges;