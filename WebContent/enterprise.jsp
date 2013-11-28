<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>企业通道</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="css/base.css" />
</head>
<body>
	<jsp:include page="navbar.jsp" />
	
	<div class="container container-top">
		<div class="row">
			<div class="col-md-span3" style="">
				<ul class="nav nav-list affix" style="width: 258px; top: 100px;">
					<li class="list-item" value="0">
						<a href="#">增加操作人</a>
					</li>
					<li class="list-item" value="1">
						<a href="#">删除操作人</a>
					</li>
				</ul>
			</div>
			<div id="form-add" class="col-md-span7">
				<form class="form-data enterprise-form" action="Operator.do" method="post">
					<input type="hidden" name="op" value="add" />
					<h2 class="form-data-heading">企业信息</h2>
					<input class="form-control" name="enIdentity" type="text" placeholder="企业凭证" required autofocus />
					<input class="form-control" name="enName" type="text" placeholder="企业名称" required />
					<h2 class="form-data-heading">超级操作人信息</h2>
					<input class="form-control" name="identity" type="text" placeholder="身份证" required />
					<input class="form-control" name="username" type="text" placeholder="账号" required />
					<input class="form-control" name="password" type="password" placeholder="密码" required />
					<h2 class="form-data-heading">新增操作人信息</h2>
					<input class="form-control" name="newIdentity" type="text" placeholder="身份证" required autofocus />
					<input class="form-control" name="newName" type="text" placeholder="用户名称" required />
					<input class="form-control" name="newPassword" type="password" placeholder="输入密码" required />
					<input class="form-control" name="newPasswordAgain" type="password" placeholder="再次输入密码" required />
					<div class="radio-margin">操作人类型：
						<label class="radio-inline">
							<input name="role" value="0" type="radio" checked="checked" />普通操作人
						</label>
						<label class="radio-inline">
							<input name="role" value="1" type="radio"/>超级操作人
						</label>
					</div>
					<button class="btn btn-lg btn-primary btn-block" type="submit">增加</button>
				</form>
			</div>
			<div id="form-delete" class="col-md-span7">
				<form class="form-data enterprise-form" action="Operator.do" method="post">
					<input type="hidden" name="op" value="delete" />
					<h2 class="form-data-heading">企业信息</h2>
					<input class="form-control" name="enIdentity" type="text" placeholder="企业凭证" required autofocus />
					<input class="form-control" name="enName" type="text" placeholder="企业名称" required />
					<h2 class="form-data-heading">超级操作人信息</h2>
					<input class="form-control" name="identity" type="text" placeholder="身份证" required autofocus />
					<input class="form-control" name="username" type="text" placeholder="账号" required />
					<input class="form-control" name="password" type="password" placeholder="密码" required />
					<h2 class="form-data-heading">要删除的操作人信息</h2>
					<input class="form-control" name="newIdentity" type="text" placeholder="身份证" required autofocus />
					<input class="form-control" name="newName" type="text" placeholder="用户名称" required />
					<label class="checkbox"></label>
					<button class="btn btn-lg btn-primary btn-block" type="submit">删除</button>
				</form>
			</div>
		</div>
	</div>

	<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
	<script type="text/javascript">
	var bindEvents = function () {
		$("#form-delete").hide();
		$(".list-item").click(function () {
			if ($(this).val() == 0) {
				$("#form-add").show();
				$("#form-delete").hide();
			}
			else {
				$("#form-delete").show();
				$("#form-add").hide();
			}
		});
	};

	addLoadEvents(bindEvents);
	</script>
</body>
</html>