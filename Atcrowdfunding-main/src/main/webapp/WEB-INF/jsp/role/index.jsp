<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<link rel="stylesheet"
	href="${APP_PATH}/bootstrap/css/bootstrap.min.css">
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
						- 角色维护</a>
				</div>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
					<li style="padding-top: 8px;">
						<%@include file="/WEB-INF/jsp/common/userInfo.jsp"%>
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
					<%@include file="/WEB-INF/jsp/common/menu.jsp" %>
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
						<form class="form-inline" name="form" style="float: left;">
							<div class="form-group has-feedback">
								<div class="input-group">
									<div class="input-group-addon">查询条件</div>
									<input id="queryText" class="form-control has-success" type="text"
										placeholder="请输入查询条件">
								</div>
							</div>
							<button type="button" class="btn btn-warning" onclick="queryRole()">
								<i class="glyphicon glyphicon-search"></i> 查询
							</button>
						</form>
						<button id="batchDelete" type="button" class="btn btn-danger"
							style="float: right; margin-left: 10px;">
							<i class="glyphicon glyphicon-remove"></i> 删除
						</button>
						<button type="button" class="btn btn-primary"
							style="float: right;" onclick="window.location.href='${APP_PATH}/role/add.htm'">
							<i class="glyphicon glyphicon-plus"></i> 新增
						</button>
						<br>
						<hr style="clear: both;">
						<div class="table-responsive">
							<table class="table  table-bordered">
								<thead>
									<tr>
										<th width="30">#</th>
										<th width="30"><input type="checkbox"></th>
										<th>name</th>
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
	<script src="${APP_PATH}/jquery/layer/layer.js"></script>
	<script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
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
    				url:"${APP_PATH}/role/list.do",
    				type:"post",
    				beforeSend:function(){
    					loadingIndex = layer.load(2,{time:10*1000});
    					return true;
    				},
    				success:function(result){
    					
    					if (result.status == 200) {
    						layer.close(loadingIndex);
    	    				//拼接用户列表字符串
    	    				$("tbody").empty();
    	    				
    	    				$.each(result.message,function(index,item){
    	    					var content = "<tr>"
    	    		                  +"<td>"+item.id+"</td>"
    	    		                  +"<td><input type='checkbox'></td>"
    	    		                  +"<td>"+item.name+"</td>"
    	    		                  +"<td>"
    	    		                  +"  <a type='button' class='btn btn-success btn-xs' href='${APP_PATH}/role/toAssignPermission.htm?id="+item.id+"'><i class='glyphicon glyphicon-check'></i></a>"
    	    		                  +"  <a type='button' class='btn btn-primary btn-xs'><i class='glyphicon glyphicon-pencil'></i></a>"
    	    		                  +"  <a type='button' class='btn btn-danger btn-xs'><i class=' glyphicon glyphicon-remove'></i></a>"
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

	</script>
<script type="text/javascript" src="${APP_PATH }/script/menu.js"></script>
        
</body>

</html>