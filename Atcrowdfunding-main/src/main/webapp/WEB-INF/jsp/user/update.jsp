<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改用户</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="${APP_PATH }/img/crowdfunding.png" sizes="32x32" type="image/png">
<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/main.css">
<link rel="stylesheet" href="${APP_PATH }/css/doc.min.css">
<style>
.tree li {
	list-style-type: none;
	cursor: pointer;
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<div>
				<a class="navbar-brand" style="font-size: 32px;" href="user.html">众筹平台
					- 用户维护</a>
			</div>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<li style="padding-top: 8px;">
					<jsp:include page="/WEB-INF/jsp/common/userInfo.jsp"></jsp:include>
				</li>
				<li style="margin-left: 10px; padding-top: 8px;">
					<button type="button" class="btn btn-default btn-danger">
						<span class="glyphicon glyphicon-question-sign"></span> 帮助
					</button>
				</li>
			</ul>
			<form class="navbar-form navbar-right">
				<input type="text" class="form-control" placeholder="Search...">
			</form>
		</div>
	</div>
	</nav>

	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2 sidebar">
				<div class="tree">
					<jsp:include page="/WEB-INF/jsp/common/menu.jsp"></jsp:include>
				</div>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<ol class="breadcrumb">
					<li><a href="#">首页</a></li>
					<li><a href="#">数据列表</a></li>
					<li class="active">修改</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-heading">
						表单数据
						<div style="float: right; cursor: pointer;" data-toggle="modal"
							data-target="#myModal">
							<i class="glyphicon glyphicon-question-sign"></i>
						</div>
					</div>
					<div class="panel-body">
						<form role="form" id="updateForm">
							<input type="hidden" name="id" value="${user.id }">
							<div class="form-group">
								<label for="exampleInputPassword1">登陆账号</label> <input
									type="text" class="form-control" id="exampleInputPassword1" name="loginacct"
									value="${user.loginacct }">
							</div>
							<div class="form-group">
								<label for="exampleInputPassword1">用户名称</label> <input
									type="text" class="form-control" id="exampleInputPassword1" name="username"
									value="${user.username }">
							</div>
							<div class="form-group">
								<label for="exampleInputEmail1">邮箱地址</label> <input type="email" name="email"
									class="form-control" id="exampleInputEmail1"
									value="${user.email }">
								<p class="help-block label label-warning">请输入合法的邮箱地址, 格式为：
									xxxx@xxxx.com</p>
							</div>
							<button type="button" class="btn btn-success" onclick="updateUser()">
								<i class="glyphicon glyphicon-edit"></i> 修改
							</button>
							<button type="reset" class="btn btn-danger">
								<i class="glyphicon glyphicon-refresh"></i> 重置
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">帮助</h4>
				</div>
				<div class="modal-body">
					<div class="bs-callout bs-callout-info">
						<h4>测试标题1</h4>
						<p>测试内容1，测试内容1，测试内容1，测试内容1，测试内容1，测试内容1</p>
					</div>
					<div class="bs-callout bs-callout-info">
						<h4>测试标题2</h4>
						<p>测试内容2，测试内容2，测试内容2，测试内容2，测试内容2，测试内容2</p>
					</div>
				</div>
				<!--
		  <div class="modal-footer">
			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			<button type="button" class="btn btn-primary">Save changes</button>
		  </div>
		  -->
			</div>
		</div>
	</div>
	<script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
	<script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
		<script src="${APP_PATH }/jquery/layer/layer.js"></script>
	<script type="text/javascript">
		$(function() {
			$(".list-group-item").click(function() {
				if ($(this).find("ul")) {
					$(this).toggleClass("tree-closed");
					if ($(this).hasClass("tree-closed")) {
						$("ul", this).hide("fast");
					} else {
						$("ul", this).show("fast");
					}
				}
			});
		});
		
		//修改用户
		var loadingIndex = -1;
        function updateUser() {
				$.ajax({
					url:"${APP_PATH}/user/update.do",
					type:"POST",
					data:$("#updateForm").serialize(),
					beforeSend:function(){
						loadingIndex = layer.load(2,{time:10*1000});
						return true;
					},
					success:function(result){
						layer.close(loadingIndex);
						if (result.status == 200) {
							window.location.href = "${APP_PATH}/user/toUserIndex.htm";
						}else {
							layer.msg("修改用户失败！",{time:1000,icon:5,shift:6})
						}
					},
					error:function(result){
						layer.msg("修改用户失败！",{time:1000,icon:5,shift:6})
					}
				})
			} 
        
        
	</script>
</body>
</html>