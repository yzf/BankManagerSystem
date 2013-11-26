<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>转账</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="css/base.css" />
</head>
<body>
	<jsp:include page="navbar.jsp" />

	<div class="container container-top">
		<form class="form-data" action="Transfer.do" method="post">
			<h2 class="form-data-heading">转出方信息</h2>
			<input class="form-control" name="fromIdentity" type="text" placeholder="身份证（企业凭证）" required autofocus />
			<input class="form-control" name="fromName" type="text" placeholder="用户名称" required />
			<input class="form-control" name="fromUsername" type="text" placeholder="账号" required />
			<input class="form-control" name="fromPassword" type="password" placeholder="密码" required />
			<input class="form-control" name="money" type="text" placeholder="转出金额" required />
			<h2 class="form-data-heading">转入方信息</h2>
			<input class="form-control" name="toName" type="text" placeholder="用户名称" required />
			<input class="form-control" name="toUsername" type="text" placeholder="账号" required />
			<label class="checkbox"></label>
			<button class="btn btn-lg btn-primary btn-block" type="submit">转账</button>
		</form>
	</div>
	
	<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
</body>
</html>