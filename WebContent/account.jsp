<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>开户</title>
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
						<a href="#">开户</a>
					</li>
					<li class="list-item" value="1">
						<a href="#">销户</a>
					</li>
				</ul>
			</div>
			<div id="form-add" class="col-md-span7">
				<form class="form-data" action="CreateAccount.do" method="post">
					<h2 class="form-data-heading">请输入开户信息</h2>
					<ul class="nav nav-tabs">
					  	<li class="active tab-item" value="0">
					    	<a href="#">个人账户</a>
					  	</li>
					  	<li class="tab-item" value="1">
					  		<a href="#">企业账户</a>
					  	</li>
					</ul>
					<input class="form-control" name="identity" type="text" placeholder="身份证" required autofocus />
					<input class="form-control" name="name" type="text" placeholder="用户名称" required />
					<input class="form-control" name="balance" type="text" placeholder="金额" required />
					<input class="form-control" name="password" type="password" placeholder="输入密码" required />
					<input class="form-control" name="passwordAgain" type="password" placeholder="再次输入密码" required />
					<div class="radio-margin">帐户类型：
						<label class="radio-inline">
							<input name="aType" value="0" type="radio" checked="checked" />活期
						</label>
						<label class="radio-inline">
							<input name="aType" value="1" type="radio"/>定期
						</label>
					</div>
					<div id="differ">
						<div class="radio-margin">用户类型：
							<label class="radio-inline">
								<input name="uType" value="0" type="radio" checked="checked"/>普通用户
							</label>
							<label class="radio-inline">
								<input name="uType" value="1" type="radio"/>VIP用户
							</label>
						</div>
					</div>
					<button class="btn btn-lg btn-primary btn-block" type="submit">开户</button>
				</form>
			</div>
			<div id="form-delete" class="col-md-span7">
				<form class="form-data" action="CloseAccount.do" method="post">
					<h2 class="form-data-heading">请输入用户信息</h2>
					<input class="form-control" name="identity" type="text" placeholder="身份证" required autofocus />
					<input class="form-control" name="username" type="text" placeholder="账号" required />
					<input class="form-control" name="password" type="password" placeholder="密码" required />
					<label class="checkbox"></label>
					<button class="btn btn-lg btn-primary btn-block" type="submit">销户</button>
				</form>
			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
	<script type="text/javascript">
	var individal = "<div class='radio-margin'>用户类型：" + 
						"<label class='radio-inline'>" + 
							"<input name='uType' value='0' type='radio' checked='checked'/>普通用户" + 
						"</label>" + 
						"<label class='radio-inline'>" + 
							"<input name='uType' value='1' type='radio'/>VIP用户" + 
						"</label>" + 
					"</div>";
	var enterprise = "<div>" + 
						"<label class='radio-inline'>" + 
							"<input name='uType' value='2' type='hidden'/>" + 
						"</label>" + 
						"<input id='enIdentity' class='form-control' name='enIdentity' type='text' placeholder='企业凭证' require />" + 
						"<input id='enName' class='form-control' name='enName' type='text' placeholder='企业名称' require />" + 
					"</div>";
	var bindEvents = function () {
		$(".tab-item").click(function () {
			$(".tab-item").removeClass("active");
			$(this).addClass("active");
			if ($(this).val() == 0) {
				$("#differ").html(individal);
			}
			else {
				$("#differ").html(enterprise);
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