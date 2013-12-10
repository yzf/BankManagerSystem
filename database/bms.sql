-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- 主机: 127.0.0.1
-- 生成日期: 2013 年 12 月 10 日 08:13
-- 服务器版本: 5.5.32
-- PHP 版本: 5.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `bms`
--
CREATE DATABASE IF NOT EXISTS `bms` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `bms`;

-- --------------------------------------------------------

--
-- 表的结构 `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL COMMENT '账号',
  `a_type` int(11) NOT NULL DEFAULT '0' COMMENT '账户类型  0活期 1定期',
  `u_type` int(11) NOT NULL DEFAULT '0' COMMENT '用户类型  0普通 1VIP 2企业',
  `balance` double NOT NULL DEFAULT '0' COMMENT '金额',
  `freezed` tinyint(1) NOT NULL COMMENT '是否被冻结',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='帐户' AUTO_INCREMENT=11 ;

--
-- 转存表中的数据 `account`
--

INSERT INTO `account` (`id`, `username`, `a_type`, `u_type`, `balance`, `freezed`) VALUES
(9, '776404811115', 0, 0, 52000, 0),
(10, '796046430441', 0, 2, 30000, 0);

-- --------------------------------------------------------

--
-- 表的结构 `department`
--

CREATE TABLE IF NOT EXISTS `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT '部门名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='部门' AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `department`
--

INSERT INTO `department` (`id`, `name`) VALUES
(1, '业务部门'),
(2, '财政部门');

-- --------------------------------------------------------

--
-- 表的结构 `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL COMMENT '员工名称',
  `username` varchar(128) NOT NULL COMMENT '账号',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `type` int(11) NOT NULL DEFAULT '0' COMMENT '员工类型',
  `dep_id` int(11) NOT NULL COMMENT '部门id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='员工' AUTO_INCREMENT=7 ;

--
-- 转存表中的数据 `employee`
--

INSERT INTO `employee` (`id`, `name`, `username`, `password`, `type`, `dep_id`) VALUES
(1, '系统管理员', 'admin', 'admin', 3, 0),
(2, '嘉嘉', 'jiajia', '1', 2, 0),
(3, '疯疯', 'yuan', '1', 1, 1),
(4, '吖霍', 'huo', '1', 0, 1),
(6, '测试', 'test', '1', 0, 1);

-- --------------------------------------------------------

--
-- 表的结构 `enterprise_account`
--

CREATE TABLE IF NOT EXISTS `enterprise_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `e_id` int(11) NOT NULL COMMENT '企业id',
  `a_id` int(11) NOT NULL COMMENT '帐户id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `e_id` (`e_id`,`a_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='企业账户关系' AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `enterprise_account`
--

INSERT INTO `enterprise_account` (`id`, `e_id`, `a_id`) VALUES
(1, 4, 10);

-- --------------------------------------------------------

--
-- 表的结构 `log`
--

CREATE TABLE IF NOT EXISTS `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `emp_id` int(11) NOT NULL COMMENT '员工id',
  `emp_n` varchar(128) NOT NULL COMMENT '员工名',
  `top_u_n` varchar(128) NOT NULL COMMENT '攻方用户名称',
  `top_a_un` varchar(128) NOT NULL COMMENT '攻方帐户账号',
  `bottom_u_n` varchar(128) NOT NULL COMMENT '受方用户名称',
  `bottom_a_un` varchar(128) NOT NULL COMMENT '受方帐户账号',
  `operation` varchar(128) NOT NULL COMMENT '操作描述',
  `type` varchar(128) NOT NULL COMMENT '操作类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='日志' AUTO_INCREMENT=23 ;

--
-- 转存表中的数据 `log`
--

INSERT INTO `log` (`id`, `time`, `emp_id`, `emp_n`, `top_u_n`, `top_a_un`, `bottom_u_n`, `bottom_a_un`, `operation`, `type`) VALUES
(7, '2013-12-10 06:09:34', 3, '疯疯', '袁镇锋', '665677664407', '', '', '创建普通活期账户', '开户'),
(8, '2013-12-10 06:10:04', 3, '疯疯', '袁镇锋', '665677664407', '', '', '存入：248.0元', '存款'),
(9, '2013-12-10 06:11:15', 3, '疯疯', '袁镇锋', '665677664407', '', '', '取出：12345.0元', '取款'),
(10, '2013-12-10 06:12:32', 3, '疯疯', '袁镇锋', '665677664407', '', '', '销户', '销户'),
(11, '2013-12-10 06:17:53', 3, '疯疯', '袁镇锋', '513183703375', '', '', '创建普通活期账户', '开户'),
(12, '2013-12-10 06:21:20', 3, '疯疯', '袁镇锋', '513183703375', '', '', '存入：100000.0元', '存款'),
(13, '2013-12-10 06:22:03', 3, '疯疯', '袁镇锋', '513183703375', '', '', '取出：100000.0元', '取款'),
(14, '2013-12-10 06:22:52', 3, '疯疯', '袁镇锋', '513183703375', '', '', '存入：100000.0元', '存款'),
(15, '2013-12-10 06:25:00', 3, '疯疯', '袁镇锋', '513183703375', '', '', '取出：5000.0元', '取款'),
(16, '2013-12-10 06:27:47', 3, '疯疯', '袁镇锋', '513183703375', '', '', '查询余额', '查询'),
(17, '2013-12-10 06:28:54', 3, '疯疯', '袁镇锋', '776404811115', '', '', '创建普通活期账户', '开户'),
(18, '2013-12-10 06:35:05', 3, '疯疯', '袁镇锋', '513183703375', '袁镇锋', '776404811115', '转账：2000.0元', '转账'),
(19, '2013-12-10 06:39:19', 3, '疯疯', '袁镇锋', '513183703375', '', '', '修改密码', '修改密码'),
(20, '2013-12-10 06:42:17', 3, '疯疯', '袁镇锋', '513183703375', '', '', '销户', '销户'),
(21, '2013-12-10 06:45:19', 3, '疯疯', '袁镇锋', '796046430441', '', '', '创建企业账户', '开户'),
(22, '2013-12-10 06:48:23', 3, '疯疯', '袁镇锋', '796046430441', '吴学锋', '796046430441', '创建普通操作人', '其他');

-- --------------------------------------------------------

--
-- 表的结构 `summary`
--

CREATE TABLE IF NOT EXISTS `summary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `e_id` int(11) NOT NULL COMMENT '员工id',
  `content` text NOT NULL COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='总结报告' AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `summary`
--

INSERT INTO `summary` (`id`, `time`, `e_id`, `content`) VALUES
(1, '2013-12-10 07:00:29', 3, '我工作认真');

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `identity` varchar(128) NOT NULL COMMENT '身份证（企业凭证）',
  `name` varchar(128) NOT NULL COMMENT '名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `identity` (`identity`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='用户' AUTO_INCREMENT=6 ;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `identity`, `name`) VALUES
(3, '441900199008010872', '袁镇锋'),
(4, '32165498', '东莞中学'),
(5, '441900199008010873', '吴学锋');

-- --------------------------------------------------------

--
-- 表的结构 `user_account`
--

CREATE TABLE IF NOT EXISTS `user_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `u_id` int(11) NOT NULL COMMENT '用户id',
  `a_id` int(11) NOT NULL COMMENT '帐户id',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `role` int(11) NOT NULL COMMENT '角色',
  PRIMARY KEY (`id`),
  UNIQUE KEY `u_id` (`u_id`,`a_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='用户帐户关系' AUTO_INCREMENT=10 ;

--
-- 转存表中的数据 `user_account`
--

INSERT INTO `user_account` (`id`, `u_id`, `a_id`, `password`, `role`) VALUES
(6, 3, 8, '2', 0),
(7, 3, 9, '1', 0),
(8, 3, 10, '1', 1),
(9, 5, 10, '3', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
