<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>存款</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="css/base.css" />
</head>
<body>
	<jsp:include page="navbar.jsp" />
	
	<div class="container container-top">
		<form class="form-data" action="Deposit.do" method="post">
			<h2 class="form-data-heading">请输入存款信息</h2>
			<input class="form-control" name="username" type="text" placeholder="账号" required autofocus />
			<input class="form-control" name="password" type="password" placeholder="密码" required />
			<input class="form-control" name="money" type="text" placeholder="存入金额" required />
			<label class="checkbox"></label>
			<button class="btn btn-lg btn-primary btn-block" type="submit">存款</button>
		</form>
	</div>
	
	<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
</body>
</html>