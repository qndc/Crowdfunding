<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
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

input[type=checkbox] {
	width: 18px;
	height: 18px;
}
</style>
</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<div>
					<a class="navbar-brand" style="font-size: 32px;" href="#">众筹平台
						- 分类管理</a>
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
							<i class="glyphicon glyphicon-th"></i> 数据矩阵
						</h3>
					</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th>名称</th>
										<th>商业公司</th>
										<th>个体工商户</th>
										<th>个人经营</th>
										<th>政府及非营利组织</th>
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
			
			queryCerttype();
			
			
		});
		
		/* 异步加载分类资质矩阵 */
		var loadingIndex = -1;
		function queryCerttype() {
			$.ajax({
				url:"${APP_PATH}/certtype/list.do",
				type:"post",
				beforeSend:function(){
					loadingIndex = layer.load(2,{time:10*1000});
					return true;
				},
				success:function(result){
					layer.close(loadingIndex);
					if (result.status == 200) {				
	    				$("tbody").empty();
	    				$.each(result.message,function(index,item){
	    					//填充表格
	    					var content = '<tr><td>'+item.name+'</td>';
	    					var checkboxs = '';
	    					for (var i = 0; i < 4; i++) {
	    						checkboxs += '<td><input type="checkbox" onclick="choose(this,'+i+','+item.id+')"></td>';
							}
	    					content += checkboxs + '</tr>';
							$("tbody").append(content);

							//选中不同类型的资质
							var types = item.type;
							for (var i = 0; i < types.length; i++) {
								$("tbody>tr").eq(index).find("input").eq(types[i]).prop("checked",true)
							}
							
	    				});
					}else{
						layer.msg(result.message,{time:1000,icon:5,shift:6})
					}
				},
				
				error:function(){
					layer.msg("加载数据失败",{time:1000,icon:5,shift:6})
				}
			})
		};
		
		//复选框选中事件:0-添加，1-删除
		function choose(obj,faccttype,fcertid) {
			$.ajax({
				url:"${APP_PATH}/certtype/addOrDel.do",
				data:{
					flag:obj.checked,
					accttype:faccttype,
					certid:fcertid
				},
				type:"post",
				beforeSend:function(){
					loadingIndex = layer.load(2,{time:10*1000});
					return true;
				},
				success:function(result){
					layer.close(loadingIndex);
					if (result.status == 200) {	
						queryCerttype();

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