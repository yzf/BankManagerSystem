<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="info.yzf.service.manager.EmployeeManager" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统管理</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="css/base.css" />
</head>
<body>
	<jsp:include page="navbar.jsp" />
	
	<%
	request.setAttribute("deps", EmployeeManager.getInstance().getDepartments());
	%>
	
	<div class="container container-top">
		<div class="row">
			<div class="col-md-span3" style="">
				<ul class="nav nav-list affix" style="width: 258px; top: 100px;">
					<li class="list-item" value="0">
						<a href="#">增加雇员</a>
					</li>
					<li class="list-item" value="1">
						<a href="#">删除雇员</a>
					</li>
				</ul>
			</div>
			<div id="form-add" class="col-md-span7">
				<form class="form-data setting-form" action="Admin.do" method="post">
				<input type="hidden" name="op" value="add" />
					<h2 class="form-data-heading">请输入雇员信息</h2>
					<input class="form-control" name="name" type="text" placeholder="雇员名称" required autofocus />
					<input class="form-control" name="username" type="text" placeholder="账号" required />
					<input class="form-control" name="password" type="password" placeholder="密码" required />
					<div class="radio-margin">类型：
						<label class="radio-inline">
							<input name="type" value="0" type="radio" checked="checked" />前台操作人
						</label>
						<label class="radio-inline">
							<input name="type" value="1" type="radio"/>银行经理
						</label>
						<label class="radio-inline">
							<input name="type" value="2" type="radio"/>银行业务总管
						</label>
					</div>
					<div id="dep" class="radio-margin">部门：
						<select name="depId" style="width: 300px;">
							<c:forEach items="${deps}" var="dep">
							<option value="${dep.id }">${dep.name }</option>
							</c:forEach>
						</select>
					</div>
					<button class="btn btn-lg btn-primary btn-block" type="submit">添加</button>
				</form>
			</div>
			<div id="form-delete" class="col-md-span7">
				<form class="form-data setting-form" action="Admin.do" method="post">
					<input type="hidden" name="op" value="delete" />
					<h2 class="form-data-heading">请输入雇员信息</h2>
					<input class="form-control" name="name" type="text" placeholder="雇员名称" required autofocus />
					<input class="form-control" name="username" type="text" placeholder="账号" required />
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
		$("input[name='type']").click(function () {
			if ($(this).val() == 2) {
				$("#dep").hide();
			}
			else {
				$("#dep").show();
			}
		});
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