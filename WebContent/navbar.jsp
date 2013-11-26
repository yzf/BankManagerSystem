<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 导航 -->
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<span class="navbar-brand">银行管理系统</span>
		</div>
		<div class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="active">
					<a href="home.jsp">首页</a>
				</li>
				<c:if test="${employee != null}">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						功能 
						<b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li><a href="createAccount.jsp">开户</a></li>
						<li><a href="deposit.jsp">存款</a></li>
						<li><a href="withdrawal.jsp">取款</a></li>
						<li><a href="query.jsp">查询</a></li>
						<li><a href="transfer.jsp">转账</a></li>
						<li><a href="changePassword.jsp">修改密码</a></li>
						<li><a href="closeAccount.jsp">销户</a></li>
						<li><a href="enterprise.jsp">企业通道</a></li>
						<li><a href="journal.jsp">日志操作</a></li>
						<c:if test="${employee != null && employee.type == 3}">
						<li><a href="admin.jsp">管理</a></li>
						</c:if>
					</ul>
				</li>
				</c:if>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${employee != null}">
				<li>
					<span class="navbar-brand"><c:out value="${employee.name }"/></span>
				</li>	
				</c:if>
				<li>
					<c:choose>
						<c:when test="${employee == null }">
							<a href="login.jsp">登陆</a>
						</c:when>
						<c:otherwise>
							<span class="navbar-brand">已登陆</span>
						</c:otherwise>
					</c:choose>
				</li>
				<li>
					<a href="Logout.do">退出登陆</a>
				</li>
			</ul>
		</div>
	</div>
</div>