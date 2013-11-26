<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>银行管理系统</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" href="bootstrap/css/bootstrap-theme.min.css" />
<link rel="stylesheet" href="css/base.css" />
</head>
<body>
	<jsp:include page="navbar.jsp" />
	<div class="container container-top">
		<!-- 第一行 -->
		<div class="row">
			<div class="col-md-4 active">
				<div class="thumbnail">
					<a href="createAccount.jsp" title="开户页面">
						<img alt="create account" src="img/create.png" />
					</a>
					<div class="caption text-center">
						<h3><a href="createAccount.jsp">开户</a></h3>
					</div>
				</div>
			</div>
			
			<div class="col-md-4">
				<div class="thumbnail">
					<a href="deposit.jsp" title="存款页面">
						<img alt="deposit" src="img/deposit.png" />
					</a>
					<div class="caption text-center">
						<h3><a href="deposit.jsp">存款</a></h3>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="thumbnail">
					<a href="withdrawal.jsp" title="取款页面">
						<img alt="withdrawal" src="img/withdrawal.png" />
					</a>
					<div class="caption text-center">
						<h3><a href="withdrawal.jsp">取款</a></h3>
					</div>
				</div>
			</div>
		</div>
		<!-- 第二行 -->
		<div class="row">
			<div class="col-md-4">
				<div class="thumbnail">
					<a href="query.jsp" title="查询页面">
						<img alt="query" src="img/query.png" />
					</a>
					<div class="caption text-center">
						<h3><a href="query.jsp">查询</a></h3>
					</div>
				</div>
			</div>
			
			<div class="col-md-4">
				<div class="thumbnail">
					<a href="transfer.jsp" title="转账页面">
						<img alt="transfer" src="img/transfer.png" />
					</a>
					<div class="caption text-center">
						<h3><a href="transfer.jsp">转账</a></h3>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="thumbnail">
					<a href="changePassword.jsp" title="修改密码页面">
						<img alt="changePassword" src="img/change.png" />
					</a>
					<div class="caption text-center">
						<h3><a href="changePassword.jsp">修改密码</a></h3>
					</div>
				</div>
			</div>
		</div>
		<!-- 第三行 -->
		<div class="row">
			<div class="col-md-4">
				<div class="thumbnail">
					<a href="closeAccount.jsp" title="销户页面">
						<img alt="close account" src="img/close.png" />
					</a>
					<div class="caption text-center">
						<h3><a href="closeAccount.jsp">销户</a></h3>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="thumbnail">
					<a href="enterprise.jsp" title="企业操作页面">
						<img alt="enterprise" src="img/enterprise.png" />
					</a>
					<div class="caption text-center">
						<h3><a href="enterprise.jsp">企业通道</a></h3>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="thumbnail">
					<a href="journal.jsp" title="日志操作">
						<img alt="journal" src="img/journal.png" />
					</a>
					<div class="caption text-center">
						<h3><a href="journal.jsp">日志操作</a></h3>
					</div>
				</div>
			</div>
		</div>
		<c:if test="${employee != null && employee.type == 3 }">
			<div class="row">
				<div class="col-md-4">
					<div class="thumbnail">
						<a href="admin.jsp" title="管理员页面">
							<img alt="close account" src="img/setting.png" />
						</a>
						<div class="caption text-center">
							<h3><a href="admin.jsp">系统管理</a></h3>
						</div>
					</div>
				</div>
			</div>
		</c:if>
		<hr/>
		<footer>
			<p>© yzf 2013</p>
		</footer>
	</div>
	
	<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/base.js"></script>
</body>
</html>