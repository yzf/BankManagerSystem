<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Vector, info.yzf.database.model.Log, info.yzf.util.Util"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>提示</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="css/base.css" />
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<%
	Log log = (Log) request.getAttribute("log");
	String message = (String) request.getAttribute("message");
	String detail = (String) request.getAttribute("detail");
	Vector<Log> logs = (Vector<Log>)request.getAttribute("logs");
	%>
	
	<div class=" container container-top">
		<div class="jumbotron">
			<h2><b>温馨提示：</b></h2>
			<p><%= message == null ? "" : message %></p>
			<p><%= detail == null ? "" : detail %></p>
			<% if (log != null) { %>
				<h3>日志：</h3>
				<p>时间 <%= Util.formatDay(log.getTime()) %></p>
				<p>操作员 <%= log.getEmpName() %></p>
				<p>客户 <%= log.getTopName() %></p>
				<p>帐户 <%= log.getTopUsername() %></p>
				<% if (! log.getBottomName().equals("")) { %>
					<p>客户 <%= log.getBottomName() %></p>
				
					<p>帐户 <%= log.getBottomUsername() %></p>
				<%} %>
				<p>操作 <%= log.getOperation() %></p>
			<%} %>
			<% if (logs != null) {%>
				<h3><b>操作记录：</b></h3>
				<table class="table table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>时间</th>
							<th>员工</th>
							<th>客户</th>
							<th>账户</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<%for (int i = 0; i < logs.size(); ++ i) {
							out.print("<tr>");
							out.print("<th>" + (i + 1) + "</th>");
							out.print("<th>" + Util.formatDay(logs.get(i).getTime()) + "</th>");
							out.print("<th>" + logs.get(i).getEmpName() + "</th>");
							out.print("<th>" + logs.get(i).getTopName() + "</th>");
							out.print("<th>" + logs.get(i).getTopUsername()+ "</th>");
							out.print("<th>" + logs.get(i).getOperation() + "</th>");
							out.print("</tr>");
						%>
						<%} %>
					</tbody>
				</table>
			<%} %>
				
			<span id="backToHome" class="btn btn-lg btn-primary right">返回首页</span>
			<span id="backBtn" class="btn btn-lg btn-primary right">后退</span>
			<span class="clear" >&nbsp;</span>
		</div>
	</div>
	
	<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
	
	<script type="text/javascript">
	var bindEvents = function () {
		$("#backBtn").click(function () {
			window.history.back();
		});
		$("#backToHome").click(function () {
			window.location.href = "home.jsp";
		});
		
	};

	addLoadEvents(bindEvents);
	</script>
</body>
</html>