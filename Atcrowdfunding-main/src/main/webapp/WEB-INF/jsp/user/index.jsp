<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员维护</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="${APP_PATH }/img/crowdfunding.png" sizes="32x32" type="image/png">
<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/main.css">
<style>
.tree li {
	list-style-type: none;
	cursor: pointer;
}

table tbody tr:nth-child(odd) {
	background: #F4F4F4;
}

table tbody td:nth-child(even) {
	color: #C00;
}
</style>
</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<div>
				<a class="navbar-brand" style="font-size: 32px;" href="#">众筹平台 -
					管理员维护</a>
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
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<i class="glyphicon glyphicon-th"></i> 数据列表
						</h3>
					</div>
					<div class="panel-body">
						<form class="form-inline" role="form" style="float: left;">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">查询条件</div>
									<input class="form-control has-success" type="text" id="queryText"
										placeholder="请输入查询条件">
								</div>
							</div>
							<button type="button" class="btn btn-warning" id="queryBtn">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>
						<button type="button" class="btn btn-danger"
							style="float: right; margin-left: 10px;" id="deleteAllChecked">
							<i class=" glyphicon glyphicon-remove" ></i> 删除
						</button>
						<button type="button" class="btn btn-primary"
							style="float: right;" onclick="window.location.href='${APP_PATH}/user/toAdd.htm'">
							<i class="glyphicon glyphicon-plus"></i> 新增
						</button>
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="30">#</th>
										<th width="30"><input type="checkbox" id="allChoose" ></th>
										<th>账号</th>
										<th>名称</th>
										<th>邮箱地址</th>
										<th width="100">操作</th>
									</tr>
								</thead>
								<tbody>
									
								</tbody>
								<tfoot>
									<tr>
										<td colspan="6" align="center">
											<ul class="pagination">
												
											</ul>
											
										</td>
									</tr>

								</tfoot>
							</table>
						</div>
					</div>
				</div>
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
			
			queryPage(1);
		});
		$("tbody .btn-success").click(function() {
			window.location.href = "assignRole.html";
		});
		$("tbody .btn-primary").click(function() {
			window.location.href = "edit.html";
		});
		
		/* 全选/取消全选效果，批量删除 */
		$("#allChoose").click(function () {
			var status = this.checked;
			$("tbody tr td input[type='checkbox']").prop("checked",status)
		})
		$("#deleteAllChecked").click(function () {
			var idArr = "";
			var allChecked = $("tbody tr td input:checked");
			if (allChecked.length == 0) {
				layer.msg("至少选择一个用户进行删除！",{time:1000,icon:5,shift:6})
				return false;	
			}
			$.each(allChecked,function(index,item){
				idArr += ( "id=" + item.id + "&");
			})
			idArr = idArr.substring(0,idArr.length-1);
			layer.confirm("确定批量删除用户吗？",{icon:3,title:'提示'},function(cindex){
					$.ajax({
						url:"${APP_PATH}/user/deleteAll.do",
						type:"POST",
						data:idArr,
						beforeSend:function(){
							return true;
						},
						success:function(result){
							layer.close(loadingIndex);
							if (result.status == 200) {
								queryPage(1);
							}else {
								layer.msg(result.message,{time:1000,icon:5,shift:6})
							}
						},
						error:function(result){
							layer.msg(result.message,{time:1000,icon:5,shift:6})
						}
					})
					layer.close(cindex);
				},function(cindex){
					layer.close(cindex); 
				})
		})
		
		
		/* 异步加载用户列表 */
		var loadingIndex = -1;
		function queryPage(pageNum,queryText) {
			queryText = $("#queryText").val();
			$.ajax({
				url:"${APP_PATH}/user/list.do",
				type:"post",
				data:{
					"pageTotal":pageNum,
					"pageSize":10,
					"queryText":queryText
				},
				beforeSend:function(){
					loadingIndex = layer.load(2,{time:10*1000});
					return true;
				},
				success:function(result){
					layer.close(loadingIndex);
					if (result.status == 200) {
	    				//拼接用户列表字符串
	    				$("tbody").empty();
	    				$(".pagination").empty();
	    				$.each(result.message.data,function(index,item){
	    					var content = "<tr>"
								+"<td>"+item.id +"</td>"
								+"<td><input type='checkbox' id="+item.id +"></td>"
								+"<td>"+item.loginacct +"</td>"
								+"<td>"+item.username +"</td>"
								+"<td>"+item.email +"</td>"
								+"<td>"
								+"	<a type='button' class='btn btn-success btn-xs' href='${APP_PATH}/user/toAssignRole.htm?id="+item.id+"'>"
								+"		<i class=' glyphicon glyphicon-check'></i>"
								+"	</a>"
								+"	<a type='button' class='btn btn-primary btn-xs' href='${APP_PATH}/user/toUpdate.htm?id="+item.id+"'>"
								+"		<i class=' glyphicon glyphicon-pencil'></i>"
								+"	</a>"
								+"	<button type='button' class='btn btn-danger btn-xs' onclick='deleteUser("+item.id+")'>"
								+"		<i class=' glyphicon glyphicon-remove'></i>"
								+"	</button>"
								+"</td>"
							+"</tr>";
							$("tbody").append(content);	
	    				});
	    				
	    				//拼接分页导航
	    				var contentBar = "";
	    				if (result.message.pageTotal == 1) {
							contentBar += '<li class="disabled"><a href="javascript:void(0)">上一页</a></li>';
						}else {
							contentBar += '<li><a href="javascript:void(0)" onclick="queryPage('+(result.message.pageTotal-1)+')">上一页</a></li>';	
						}
	    				
	    				for (var i = 1; i <= result.message.pageCounts; i++) {
						
							if (result.message.pageTotal == i) {
								contentBar += '<li class = "active"><a href="javascript:void" onclick="queryPage('+i+')">'+i +'</a></li>';	
							}else{
								contentBar += '<li ><a href="javascript:void" onclick="queryPage('+i+')">'+i +'</a></li>';	
							}
						
						}
	    				
	    				if (result.message.pageTotal == result.message.pageCounts) {
							contentBar += '<li class="disabled"><a href="javascript:void(0)">下一页</a></li>';
						}else {
							contentBar += '<li><a href="javascript:void(0)" onclick="queryPage('+(result.message.pageTotal+1)+')">下一页</a></li>';	
						}
	    				
	    				$(".pagination").html(contentBar);
					}else{
						layer.msg(result.message,{time:1000,icon:5,shift:6})
					}
				},
				
				error:function(){
					layer.msg("加载数据失败",{time:1000,icon:5,shift:6})
				}
			})
		}
		
		/* 按钮点击查询 */
		$("#queryBtn").click(function () {
			var queryText = $("#queryText").val();
			queryPage(1,queryText);
		})
		
		/* 删除用户 */
        function deleteUser(fid) {
				layer.confirm("确定删除该用户吗？",{icon:3,title:'提示'},function(cindex){
					
					$.ajax({
						url:"${APP_PATH}/user/delete.do",
						type:"POST",
						data:{
							id:fid
						},
						beforeSend:function(){
							return true;
						},
						success:function(result){
							layer.close(loadingIndex);
							if (result.status == 200) {
								queryPage(1);
							}else {
								layer.msg(result.message,{time:1000,icon:5,shift:6})
							}
						},
						error:function(result){
							layer.msg(result.message,{time:1000,icon:5,shift:6})
						}
					})
					layer.close(cindex);
				},function(cindex){
					layer.close(cindex); 
				})
				
			} 
	</script>
</body>
</html>