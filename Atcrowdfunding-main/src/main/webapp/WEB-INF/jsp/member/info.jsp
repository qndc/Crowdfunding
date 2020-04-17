<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>用户详情</title>
	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/main.css">
	<link rel="stylesheet" href="${APP_PATH }/css/doc.min.css">
	<link rel="icon" href="${APP_PATH }/img/crowdfunding.png" sizes="32x32" type="image/png">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	</style>
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="user.html">众筹平台 - 用户信息</a></div>
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
				  <li class="active">显示会员信息</li>
				</ol>
			<div class="panel panel-default">
              <div class="panel-heading">显示会员信息<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
			  <div class="panel-body">
			  <div class="panel-body">
				<form>
				  <div class="form-group">
					<label for="frealname">登录账号：</label>
					${member.loginacct }
				  </div>
				  <div class="form-group">
					<label for="frealname">用户名：</label>
					${member.username }
				  </div>
				  <div class="form-group">
					<label for="frealname">邮箱：</label>
					${member.email }
				  </div>
				  <div class="form-group">
					<label for="frealname">状态：</label>
					<c:if test="${member.authstatus == 0}">
						未实名认证
					</c:if>
					<c:if test="${member.authstatus == 1}">
						实名认证申请中
					</c:if>
					<c:if test="${member.authstatus == 2}">
						已实名认证
					</c:if>
					<c:if test="${member.authstatus == 3}">
						已停用
					</c:if>
				  </div>
				  <div class="form-group">
					<label for="frealname">真实姓名：</label>
					${member.realname }
				  </div>
				  <div class="form-group">
					<label for="fcardnum">会员身份证号：</label>
					${member.cardnum }
				  </div>
				  <div class="form-group">
					<label for="fcardnum">账号类型：</label>
					<c:if test="${member.accttype == 0}">
						企业
					</c:if>
					<c:if test="${member.accttype == 1}">
						个体工商户
					</c:if>
					<c:if test="${member.accttype == 2}">
						个人
					</c:if>
					<c:if test="${member.accttype == 3}">
						政府及事业单位
					</c:if>
				  </div>
				  <div class="form-group">
					<label for="ftel">会员电话号：</label>
					${member.tel }
				  </div>
				  <hr>
				  <c:forEach items="${requestScope.list}" var="map">				  
					  <div class="form-group">
						<label for="frealname">${map.certname }</label><br>
						<img src="${map.iconpath}" style="width: 250px;height: 200px">
					  </div>
				  </c:forEach>
				</form>
			  </div>
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
		</div>
	  </div>
	</div>
    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script type="text/javascript" src="${APP_PATH }/jquery/layer/layer.js"></script>
	
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
            
           $("#passBtn").click(function(){
            	$.ajax({
            		type : "POST",
            		url  : "${APP_PATH}/auth/pass.do",
            		data : {
            			taskid : "${param.taskId}",
            			memberid : "${param.memberId}"
            		},
            		success : function(result) {
            			window.location.href = "${APP_PATH}/toAuthCert.htm";
            		}
            	});
            });
            
            $("#refuseBtn").click(function(){
            	$.ajax({
            		type : "POST",
            		url  : "${APP_PATH}/auth/refuse.do",
            		data : {
            			taskid : "${param.taskId}",
            			memberid : "${param.memberId}"
            		},
            		success : function(result) {
            			window.location.href = "${APP_PATH}/toAuthCert.htm";
            		}
            	});

            }); 
        </script>
  </body>
</html>
    