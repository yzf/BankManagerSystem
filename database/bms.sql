-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- 主机: 127.0.0.1
-- 生成日期: 2013 年 12 月 06 日 15:39
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='帐户' AUTO_INCREMENT=3 ;

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
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='员工' AUTO_INCREMENT=7 ;

--
-- 转存表中的数据 `employee`
--

INSERT INTO `employee` (`id`, `name`, `username`, `password`, `type`, `dep_id`) VALUES
(1, '系统管理员', 'admin', 'admin', 3, 0),
(2, '嘉嘉', 'jiajia', '1', 2, 0),
(3, '疯疯', 'yuan', '1', 1, 1),
(4, '吖霍', 'huo', '1', 0, 1);

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='企业账户关系' AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- 表的结构 `log`
--

CREATE TABLE IF NOT EXISTS `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '时间',
  `e_id` int(11) NOT NULL COMMENT '员工id',
  `top_ua_id` int(11) NOT NULL COMMENT '攻方id',
  `bottom_ua_id` int(11) NOT NULL COMMENT '受方id',
  `operation` varchar(512) NOT NULL COMMENT '操作',
  `type` varchar(128) NOT NULL COMMENT '类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志' AUTO_INCREMENT=1 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='总结报告' AUTO_INCREMENT=10 ;

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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='用户' AUTO_INCREMENT=2 ;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户帐户关系' AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
