<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="${APP_PATH }/img/crowdfunding.png" sizes="32x32" type="image/png">
<link rel="stylesheet" href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH}/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH}/css/main.css">
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
						- 流程管理</a>
				</div>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li style="padding-top: 8px;">
					<jsp:include
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
						<form class="form-inline" role="form" style="float: left;">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">查询条件</div>
									<input class="form-control has-success" type="text"
										placeholder="请输入查询条件">
								</div>
							</div>
							<button type="button" class="btn btn-warning">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>

						<button type="button" class="btn btn-primary" id="uploadPrcDefBtn"
							style="float: right;">
							<i class="glyphicon glyphicon-upload"></i> 上传流程定义文件
						</button>
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">
							
							<form id="deployForm" action="" method="POST" enctype="multipart/form-data">
				          		<input id="processDefFile" style="display:none" type="file" name="processDefFile" accept=".bpmn">
				          	</form>
						
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="30">#</th>
										<th>流程编号</th>
										<th>流程名称</th>
										<th>流程版本</th>
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

	<script src="${APP_PATH}/jquery/jquery-2.1.1.min.js"></script>
	<script src="${APP_PATH}/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH}/script/docs.min.js"></script>
	<script src="${APP_PATH }/jquery/layer/layer.js"></script>
	<script src="${APP_PATH }/jquery/jquery-form.min.js"></script>
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
			
			queryPage();
		});
		
		/* 异步加载用户列表 */
		var loadingIndex = -1;
		function queryPage() {
			$.ajax({
				url:"${APP_PATH}/process/list.do",
				type:"post",
				beforeSend:function(){
					loadingIndex = layer.load(2,{time:10*1000});
					return true;
				},
				success:function(result){
					
					if (result.status == 200) {
						layer.close(loadingIndex);
	    				
	    				$("tbody").empty();
	    				
	    				$.each(result.message,function(index,item){
	    					var content = "<tr>"
								+"<td>"+(index+1)+"</td>"
								+"<td>"+item.key+"</td>"
								+"<td>"+item.name+"</td>"
								+"<td>"+item.version+"</td>"
								+"<td>"
								+'	<button type="button" class="btn btn-success btn-xs" onclick="showImg(\''+item.id+'\')">'
								+"		<i class='glyphicon glyphicon-eye-open'></i>"
								+"	</button>"
								+'	<button type="button" class="btn btn-danger btn-xs" onclick="deleteProDef(\''+item.id+'\')">'
								+"		<i class='glyphicon glyphicon-remove'></i>"
								+"	</button>"
								+"</td>"
								+"</tr>";
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
		
		$("#uploadPrcDefBtn").click(function(){  //click()函数增加回调函数这个参数,表示绑定事件.
        	$("#processDefFile").click(); //click()函数没有参数,表示触发单击事件.
        });
		
		//选择文件的“打开按钮”点击了以后异步提交表单
		$("#processDefFile").change(function(){
			
			var options = {
	      			url:"${APP_PATH}/process/deploy.do",
	    			beforeSubmit : function(){
	    					loadingIndex = layer.msg('数据正在部署中', {icon: 6});
	            			return true ; //必须返回true,否则,请求终止.
	    			},
	    			success : function(result){
	           			layer.close(loadingIndex);
	           			if (result.status == 200) {
	           				layer.msg("部署成功", {time:1000, icon:6});
	           				queryPage();
    					}else{
    						layer.msg(result.message,{time:1000,icon:5,shift:6})
    					}
	           		}	
	       		};
	       		
	       		$("#deployForm").ajaxSubmit(options); //异步提交
	       		return ; 
		});
		
		//删除部署的流程
		function deleteProDef(id) {
			layer.confirm("确定删除该流程吗？",{icon:3,title:'提示'},function(cindex){
				$.ajax({
					url:"${APP_PATH}/process/delete.do?id="+id,
					type:"post",
					beforeSend:function(){
						loadingIndex = layer.load(2,{time:10*1000});
						return true;
					},
					success:function(result){
						if (result.status == 200) {
							layer.close(loadingIndex);
							layer.msg(result.message,{time:1000,icon:6,shift:6})
							queryPage();
						}else{
							layer.msg(result.message,{time:1000,icon:5,shift:6})
						}
					},
					error:function(){
						layer.msg("删除失败",{time:1000,icon:5,shift:6})
					}
				})
				layer.close(cindex);
			},function(cindex){
				layer.close(cindex); 
			})
		}
		
		//查看流程部署图片
		function showImg(id) {
			//jquery的ajax请求不支持流的格式数据
			var url = "${APP_PATH}/process/showImg.do?id="+id;
			layer.open({
				  title:'流程图片',
				  type: 1,
				  skin: 'layui-layer-rim', //加上边框
				  area: ['620px', '440px'], //宽高
				  offset: '200px',
				  content: '<img alt="" src="" id="showImage" style="height:380px;margin: 0px 100px">'
				});
			$("#showImage").attr("src", url);
		}
	</script>
	<script src="${APP_PATH}/script/menu.js"></script>
</body>
</html>