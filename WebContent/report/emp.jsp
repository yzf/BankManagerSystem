<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="info.yzf.database.model.*,
				info.yzf.util.*,
				info.yzf.service.manager.LogManager,
				java.util.*,
				java.sql.Timestamp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日志报表</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="css/base.css" />
</head>
<body>
	<%
	String type = (String) request.getAttribute("type");
	String begin = (String) request.getAttribute("begin");
	String end = (String) request.getAttribute("end");
	Report report = (Report) request.getAttribute("report");
	Vector<Employee> subordinate = report.getSubordinate();
	Vector<Summary> summary = report.getSummary();
	Vector<Log> logs = report.getLog();
	%>
	<div class="container container-top">
		<table class="table table-bordered table-striped">
			<caption>个人日志报表 <%= type %>（<%= begin %> ~ <%= end %>）</caption>
			<thead>
				<tr>
					<td>雇员：</td>
					<td><%= report.getEmployee().getName() %></td>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td>统计信息：</td>
					<td colspan="5">
					<%for (Map.Entry<String, Integer> entry : report.getStatistics().entrySet()) {
						String key = entry.getKey();
						Integer value = entry.getValue();
						out.print(key + value + "次 ");
					}
					%>
					</td>
				</tr>
				<tr>
					<td class="text-center" colspan="6">总结报告</td>
				</tr>
				<tr>
					<td>#</td>
					<td>时间</td>
					<td colspan="4">内容</td>
				</tr>
			</thead>
			<tbody>
				<%
				for (int i = 0; i < summary.size(); ++ i) {
					Summary s = summary.get(i);
					out.print("<tr>");
					out.print("<td>" + (i + 1) + "</td>");
					out.print("<td>" + Util.formatTime(s.getTime()) + "</td>");
					out.print("<td colspan='4'>" + s.getContent() + "</td>");
					out.print("</tr>");
				}
				%>
			</tbody>
			<thead>
				<tr>
					<td class="text-center" colspan="6">详细日志</td>
				</tr>
				<tr>
					<td>#</td>
					<td>时间</td>
					<td>客户</td>
					<td>帐户</td>
					<td>操作</td>
					<td>其他信息</td>
				</tr>
			</thead>
			<tbody>
				<%
				for (int i = 0; i < logs.size(); ++ i) {
					Log l = logs.get(i);
					out.print("<tr>");
					out.print("<td>" + (i + 1) + "</td>");
					out.print("<td>" + l.getFormatTime() + "</td>");
					out.print("<td>" + l.getTopName() + "</td>");
					out.print("<td>" + l.getTopUsername() + "</td>");
					out.print("<td>" + l.getOperation() + "</td>");
					if (l.getType().equals(Log.Transfer)) {
						out.print("<td>转入方为：" + l.getBottomName() +
								" " + l.getBottomUsername() + "</td>");	
					}
					else if (l.getType().equals(Log.Other)) {
						out.print("<td>被处理的操作人为：" + l.getBottomName() + "</td>");
					}
					else {
						out.print("<td></td>");
					}
					out.print("</tr>");
				}
				%>
			</tbody>
		</table>
	</div>
	
	<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
	<script type="text/javascript">

	</script>
</body>
</html>