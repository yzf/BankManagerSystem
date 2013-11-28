<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="bootstrap/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" href="css/base.css" />
<style type="text/css">
#detailTime>div {
	height: 34px;
}
</style>
</head>
<body>
	<jsp:include page="navbar.jsp" />

	<div class="container container-top">
		<form class="form-data query-form" action="Query.do" method="post">
			<input type="hidden" name="op" value="0" />
			<h2 class="form-data-heading">请输入用户信息</h2>
			<ul class="nav nav-tabs">
			  	<li class="active tab-item" value="0">
			    	<a href="#">账户余额</a>
			  	</li>
			  	<li class="tab-item" value="1">
			  		<a href="#">账户操作</a>
			  	</li>
			</ul>
			<input class="form-control" name="identity" type="text" placeholder="身份证" required autofocus />
			<input class="form-control" name="username" type="text" placeholder="账号" required />
			<input class="form-control" name="password" type="password" placeholder="密码" required />
			<div id="detailTime">
				<div>
					<div class="input-group date form_date" data-date="2013-11-25" 
						data-date-format="yyyy-mm-dd" data-date-link-format="yyyy-mm-dd" data-link-field="dtp_input1">
						<input class="form-control" size="16" type="text" value="" readonly>
						<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
			         </div>
					<input type="hidden" id="dtp_input1" name="begin" value="" required /><br/>
				</div>
				<div>
					<div class="input-group date form_date" data-date="2013-11-25" 
						data-date-format="yyyy-mm-dd" data-date-link-format="yyyy-mm-dd" data-link-field="dtp_input2">
						<input class="form-control" size="16" type="text" value="" readonly>
						<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
						<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
			         </div>
					<input type="hidden" id="dtp_input2" name="end" value="" required /><br/>
				</div>
			</div>
			<label class="checkbox"></label>
			<button class="btn btn-lg btn-primary btn-block" type="submit">查询</button>
		</form>
	</div>
	
	
	<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script type="text/javascript" src="js/base.js"></script>
	<script type="text/javascript">
	var bindEvents = function () {
		$("#detailTime").hide();
		$(".tab-item").click(function () {
			$(".tab-item").removeClass("active");
			$(this).addClass("active");
			if ($(this).val() == 0) {
				$("#detailTime").hide();
				$("input[name='op']").val("0");
			}
			else {
				$("#detailTime").show();
				$("input[name='op']").val("1");
			}
		});
		$('.form_date').datetimepicker({
	        language:  'zh-CN',
	        weekStart: 1,
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
	    });
	};
	

	addLoadEvents(bindEvents);
	</script>
</body>
</html>