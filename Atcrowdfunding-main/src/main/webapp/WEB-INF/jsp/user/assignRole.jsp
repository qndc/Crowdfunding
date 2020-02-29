<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
		cursor:pointer;
	}
	</style>
<title></title>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="user.html">众筹平台 - 用户维护</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<jsp:include page="/WEB-INF/jsp/common/userInfo.jsp"></jsp:include>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
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
				  <li class="active">分配角色</li>
				</ol>
			<div class="panel panel-default">
			  <div class="panel-body">
				<form role="form" class="form-inline">
				  <div class="form-group">
					<label for="exampleInputPassword1">未分配角色列表</label><br>
					<select class="form-control" multiple size="10" style="width:200px;overflow-y:auto;" id="leftRole">
                        <c:forEach items="${leftRoles }" var="role">
                        	<option value="${role.id }">${role.name }</option>
                        </c:forEach>
                    </select>
				  </div>
				  <div class="form-group">
                        <ul>
                            <li id="leftToRightBtn" class="btn btn-default glyphicon glyphicon-chevron-right"></li>
                            <br>
                            <li id="rightToLeftBtn" class="btn btn-default glyphicon glyphicon-chevron-left" style="margin-top:20px;"></li>
                        </ul>
				  </div>
				  <div class="form-group" style="margin-left:40px;">
					<label for="exampleInputPassword1">已分配角色列表</label><br>
					<select class="form-control" multiple size="10" style="width:200px;overflow-y:auto;" id="rightRole">
                        <c:forEach items="${rightRoles }" var="role">
                        	<option value="${role.id }">${role.name }</option>
                        </c:forEach>
                    </select>
				  </div>
				</form>
			  </div>
			</div>
        </div>
      </div>
    </div>
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
		<div class="modal-content">
		  <div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
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
            });
            
            /* 左侧的角色移到右侧 */
            var loadingIndex = -1;
            $("#leftToRightBtn").click(function () {
            	var leftSelect = $("#leftRole option:selected");
            	var roleid = "";

            	$.each(leftSelect,function(index,item){
            		roleid += ( item.value + "&" );
            	})
            	roleid = roleid.substring(0,roleid.length-1);
            	
            	$.ajax({
					url:"${APP_PATH}/user/assignRole.do",
					type:"POST",
					data:{
						"userid":"${param.id}",
						"roleid":roleid
					},
					beforeSend:function(){
						loadingIndex = layer.load(2,{time:10*1000});
						return true;
					},
					success:function(result){
						layer.close(loadingIndex);
						if (result.status == 200) {
							leftSelect.remove();
							$("#rightRole").append(leftSelect);
						}else {
							layer.msg(result.message,{time:1000,icon:5,shift:6})
						}
					},
					error:function(result){
						layer.msg(result.message,{time:1000,icon:5,shift:6})
					}
				})				
			})
            
            /* 右侧的角色移到左侧 */
            $("#rightToLeftBtn").click(function () {
            	var rightSelect = $("#rightRole option:selected");
            	var roleid = "";

            	$.each(rightSelect,function(index,item){
            		roleid += ( item.value + "&" );
            	})
            	roleid = roleid.substring(0,roleid.length-1);
            	
            	$.ajax({
					url:"${APP_PATH}/user/delRole.do",
					type:"POST",
					data:{
						"userid":"${param.id}",
						"roleid":roleid
					},
					beforeSend:function(){
						loadingIndex = layer.load(2,{time:10*1000});
						return true;
					},
					success:function(result){
						layer.close(loadingIndex);
						if (result.status == 200) {
							rightSelect.remove();
							$("#leftRole").append(rightSelect);
						}else {
							layer.msg(result.message,{time:1000,icon:5,shift:6})
						}
					},
					error:function(result){
						layer.msg(result.message,{time:1000,icon:5,shift:6})
					}
				})					
			})          
        </script>
</body>
</html>