<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>资质管理</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet"
	href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
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
					<a class="navbar-brand" style="font-size: 32px;" href="#">众筹平台
						- 资质管理</a>
				</div>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li style="padding-top: 8px;"><jsp:include
							page="/WEB-INF/jsp/common/userInfo.jsp"></jsp:include></li>
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
						<button type="button" class="btn btn-primary"
							style="float: right;" onclick="window.location.href='${APP_PATH}/cert/toAdd.htm'">
							<i class="glyphicon glyphicon-plus"></i> 新增
						</button>
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="30">#</th>
										<th>名称</th>
										<th width="100">操作</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
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
			
			queryCert() ;
			
		});
		
		/* 删除用户 */
        function deleteCert(fid) {
				layer.confirm("确定删除该资质吗？",{icon:3,title:'提示'},function(cindex){
					
					$.ajax({
						url:"${APP_PATH}/cert/delete.do",
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
								queryCert();
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
		
		/* 异步加载用户列表 */
		var loadingIndex = -1;
		function queryCert() {
			$.ajax({
				url:"${APP_PATH}/cert/list.do",
				type:"post",
				beforeSend:function(){
					loadingIndex = layer.load(2,{time:10*1000});
					return true;
				},
				success:function(result){
					layer.close(loadingIndex);
					if (result.status == 200) {
	    				//拼接用户列表字符串
	    				$("tbody").empty();
	    				$.each(result.message,function(index,item){
	    					var content = '<tr>'
								+'<td>'+item.id+'</td>'
								+'<td>'+item.name+'</td>'
								+'<td>'
								+'	<a type="button" class="btn btn-primary btn-xs" href="${APP_PATH}/cert/toUpdate.htm?id='+item.id+'">'
								+'		<i class=" glyphicon glyphicon-pencil"></i>'
								+'	</a>'
								+'	<a type="button" class="btn btn-danger btn-xs" onclick="deleteCert('+item.id+')">'
								+'		<i class=" glyphicon glyphicon-remove"></i>'
								+'	</a>'
								+'</td>'
								+'</tr>';
							$("tbody").append(content);	
	    				});
					}else{
						layer.msg(result.message,{time:1000,icon:5,shift:6})
					}
				},
				
				error:function(){
					layer.msg("加载数据失败",{time:1000,icon:5,shift:6})
				}
			})
		}
	</script>
</body>
</html>