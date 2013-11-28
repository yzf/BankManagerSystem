<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="info.yzf.service.manager.EmployeeManager" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日志操作</title>
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
		<form class="form-data journal-form" action="Log.do" method="post">
			<h2 class="form-data-heading">操作日志</h2>
			<div class="radio-margin">等级：
				<label class="radio-inline">
					<input name="level" value="0" type="radio" checked="checked" />个人
				</label>
				<c:if test="${employee.type > 0}">
				<label class="radio-inline">
					<input name="level" value="1" type="radio"/>部门
				</label>
				</c:if>
				<c:if test="${employee.type > 1}">
				<label class="radio-inline">
					<input name="level" value="2" type="radio"/>全部
				</label>
				</c:if>
			</div>
			<div id="dep" class="radio-margin">部门：
				<select name="depId" style="width: 240px;">
				<c:forEach items="${deps}" var="dep">
					<option value="${dep.id }">${dep.name }</option>
				</c:forEach>
				</select>
			</div>
			<div class="radio-margin">类型：
				<label class="radio-inline">
					<input name="type" value="0" type="radio" checked="checked" />日报
				</label>
				<label class="radio-inline">
					<input name="type" value="1" type="radio"/>月报
				</label>
				<label class="radio-inline">
					<input name="type" value="2" type="radio"/>季度报
				</label>
				<label class="radio-inline">
					<input name="type" value="3" type="radio"/>年报
				</label>
			</div>
			<input class="form-control" name="time" type="text" placeholder="格式（yyyy-MM-dd）" required />
			<label class="checkbox"></label>
			<button class="btn btn-lg btn-primary btn-block" type="submit">查看</button>
		</form>
	</div>

	<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
	<script type="text/javascript">
	var bindEvents = function () {
		$("#dep").hide();
		$("input[name='level']").click(function () {
			if ($(this).val() != 1) {
				$("#dep").hide();
			}
			else {
				$("#dep").show();
			}
		});
		$("input[name='type']").click(function () {
			var val = $(this).val();
			if (val == 0) {
				$("input[name='time']").attr("placeholder", "格式（yyyy-MM-dd）");
				$("input[name='time']").val("");
			}
			else if (val == 1) {
				$("input[name='time']").attr("placeholder", "格式（yyyy-MM）");
				$("input[name='time']").val("");
			}
			else if (val == 2) {
				$("input[name='time']").attr("placeholder", "格式（yyyy n）");
				$("input[name='time']").val("");
			}
			else {
				$("input[name='time']").attr("placeholder", "格式（yyyy）");
				$("input[name='time']").val("");
			}
		});
	};

	addLoadEvents(bindEvents);
	</script>
</body>
</html>