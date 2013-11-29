<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="info.yzf.service.manager.EmployeeManager, 
				info.yzf.database.model.Department, 
				info.yzf.database.model.Employee,
				java.util.Vector;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>日志操作</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="bootstrap/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="css/base.css" />
<style type="text/css">
#time_2 {
	height: 120px;
}
</style>
</head>
<body>
	<jsp:include page="navbar.jsp" />
	
	<%
	Employee employee = (Employee) session.getAttribute("employee");
	Vector<Department> deps = EmployeeManager.getInstance().getDepartments();
	%>
	
	<div class="container container-top">
		<div class="row">
			<div class="col-md-span3">
				<ul class="nav nav-list affix" style="width: 258px; top: 100px;">
					<li class="list-item" value="0">
						<a href="#">我的日志报表</a>
					</li>
					<%if (employee.getType() > 0) { %>
					<li class="list-item" value="1">
						<a href="#">员工日志报表</a>
					</li>
					<%} %>
					<%if (employee.getType() > 0) { %>
					<li class="list-item" value="2">
						<a href="#">部门日志报表</a>
					</li>
					<%} %>
					<%if (employee.getType() > 1) { %>
					<li class="list-item" value="3">
						<a href="#">银行日志报表</a>
					</li>
					<%} %>
					<li class="list-item" value="4">
						<a href="#">填写总结报告</a>
					</li>
				</ul>
			</div>
		</div>
		<div id="journal">
			<form class="form-data journal-form" action="Report.do" method="post" target="_blank">
				<h2 class="form-data-heading">我的日志报表</h2>
				<input type="hidden" name="op" value="0" />
				<div id="one">
					<input class="form-control" name="username" type="text" placeholder="雇员账号" />
				</div>
				<% %>
				<div id="dep" class="radio-margin text-center">部门：
					<select name="depId" style="width: 250px;">
						<%for (Department d : deps) {%>
						<option value="<%= d.getId() %>"><%= d.getName() %></option>
						<%} %>
					</select>
				</div>
				<div class="radio-margin text-center">类型：
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
				<div id="time_0" class="time-input">
					<div class="input-group date form_date" data-date="2013-01-01" 
						data-date-format="yyyy-mm-dd" data-date-link-format="yyyy-mm-dd" data-link-field="dtp_input0">
						<input class="form-control" size="16" type="text" value="请选择日期" readonly>
						<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
			         </div>
					<input type="hidden" id="dtp_input0" name="time" value="" required /><br/>
				</div>
				<div id="time_1" class="time-input">
					<div class="input-group date form_month" data-date="2013-01-01" 
						data-date-format="yyyy-mm" data-date-link-format="yyyy-mm" data-link-field="dtp_input1">
						<input class="form-control" size="16" type="text" value="请选择月份" readonly>
						<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
			         </div>
					<input type="hidden" id="dtp_input1" name="time" value="" required /><br/>
				</div>
				<div id="time_2" class="time-input">
					<div class="input-group date form_year" data-date="2013-01-01" 
						data-date-format="yyyy" data-date-link-format="yyyy" data-link-field="dtp_input2">
						<input class="form-control" size="16" type="text" value="请选择年份" readonly>
						<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
			         </div>
					<input type="hidden" id="dtp_input2" name="time" value="" required /><br/>
					<div class="radio-margin text-center">季度：
						<label class="radio-inline">
							<input name="quarter" value="1" type="radio" checked="checked" />一
						</label>
						<label class="radio-inline">
							<input name="quarter" value="2" type="radio"/>二
						</label>
						<label class="radio-inline">
							<input name="quarter" value="3" type="radio"/>三
						</label>
						<label class="radio-inline">
							<input name="quarter" value="4" type="radio"/>四
						</label>
					</div>
				</div>
				<div id="time_3" class="time-input">
					<div class="input-group date form_year" data-date="2013-01-01" 
						data-date-format="yyyy" data-date-link-format="yyyy" data-link-field="dtp_input3">
						<input class="form-control" size="16" type="text" value="请选择年份" readonly>
						<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
			         </div>
					<input type="hidden" id="dtp_input3" name="time" value="" required /><br/>
				</div>
				<button class="btn btn-lg btn-primary btn-block" type="submit">查看</button>
			</form>        
		</div>
		<div id="summary">
			<form class="form-data journal-form" action="Summary.do" method="post">
				<h2 class="form-data-heading">总结报告</h2>
				<textarea name="content" rows="5" cols="50"></textarea>
				<label class="checkbox"></label>
				<button class="btn btn-lg btn-primary btn-block" type="submit">提交</button>
			</form>
		</div>
	</div>

	<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="js/base.js"></script>
	<script type="text/javascript">
	var bindEvents = function () {
		$("#dep").hide();
		$("#one").hide();
		$("#summary").hide();
		$(".list-item").click(function () {
			var val = $(this).val();
			$("input[name='op']").val(val);
			$("#journal").show();
			$("#summary").hide();
			if (val == 0) {
				$("#journal h2").html("我的日志报表");
				$("#dep").hide();
				$("#one").hide();
			}
			if (val == 1) {
				$("#journal h2").html("员工日志报表");
				$("#dep").hide();
				$("#one").show();
			}
			if (val == 2) {
				$("#journal h2").html("部门日志报表");
				$("#dep").show();
				$("#one").hide();
			}
			if (val == 3){
				$("#journal h2").html("银行日志报表");
				$("#dep").hide();
				$("#one").hide();
			}
			if (val == 4) {
				$("#journal").hide();
				$("#summary").show();
			}
		});
		$(".time-input input").attr("disabled", "disabled");
		$(".time-input").hide();
		$("#time_0 input").removeAttr("disabled");
		$("#time_0").show();
		$("input[name='type']").click(function () {
			var val = $(this).val();
			$(".time-input input").attr("disabled", "disabled");
			$(".time-input").hide();
			$("#time_" + val + " input").removeAttr("disabled");
			$("#time_" + val).show();
		});
		$('.form_date').datetimepicker({
	        language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0,
			todayBtn: false
	    });
		$(".form_month").datetimepicker( {
			language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 3,
			minView: 3,
			forceParse: 0,
			todayBtn: false
		});
		$(".form_year").datetimepicker( {
			language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 4,
			minView: 4,
			forceParse: 0,
			todayBtn: false
		});
	};

	addLoadEvents(bindEvents);
	</script>
</body>
</html>