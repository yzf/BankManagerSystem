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
<link rel="stylesheet" href="css/base.css" />
</head>
<body>
	<jsp:include page="navbar.jsp" />

	<div class="container container-top">
		<form class="form-data" action="Query.do" method="post">
			<h2 class="form-data-heading">请输入用户信息</h2>
			<ul class="nav nav-tabs">
			  	<li class="active tab-item" value="0">
			    	<a href="#">账户余额</a>
			  	</li>
			  	<li class="tab-item" value="1">
			  		<a href="#">账户操作</a>
			  	</li>
			</ul>
			<input class="form-control" name="identity" type="text" placeholder="身份证（企业凭证）" required autofocus />
			<input class="form-control" name="username" type="text" placeholder="账号" required />
			<input class="form-control" name="password" type="password" placeholder="密码" required />
			<div id="detailTime" style="display: none;">
				<input class="form-control" id="begin" name="begin" type="text" placeholder="开始日期：yyyy-MM-dd" />
				<input class="form-control" id="end" name="end" type="text" placeholder="结束日期：yyyy-MM-dd" />
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit">查询</button>
		</form>
	</div>
	
	<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
	<script type="text/javascript">
	var bindEvents = function () {
		$(".tab-item").click(function () {
			$(".tab-item").removeClass("active");
			$(this).addClass("active");
			if ($(this).val() == 0) {
				$("#detailTime").hide();
				$("#begin").val("");
				$("#end").val("");
				$("#begin").removeAttr("required");
				$("#end").removeAttr("required");
			}
			else {
				$("#detailTime").show();
				$("#begin").attr("required", "");
				$("#end").attr("required", "");
			}
		});
	};

	addLoadEvents(bindEvents);
	</script>
</body>
</html>