<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>项目审核</title>
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

#proInfo .form-group {
	margin-bottom: 10px;
}

#proInfo .form-group .col-sm-10 {
	padding-top: 8px;
}

#compInfo .form-group {
	margin-bottom: 10px;
}

#compInfo .form-group .col-sm-10 {
	padding-top: 8px;
}

.tag {
	display: inline-block;
	background-color: #ecf5ff;
	color: #409EFF;
	border: 1px solid #d9ecff;
	padding: 2px 5px;
	margin: 5px 5px 0 0;
	border-radius: 3px;
}

.ctImg {
	cursor: pointer;
	color: #0695f0;
}
</style>
</head>
<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<div>
					<a class="navbar-brand" style="font-size: 32px;" href="#">众筹平台
						- 项目审核</a>
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
							<i class="glyphicon glyphicon-th"></i>信息审核
						</h3>
					</div>
					<div class="panel-body">
						<div class="col-md-12 column">
							<div class="page-header" style="border-bottom-style: dashed;">
								<h3>项目信息</h3>
							</div>
							<form class="form-horizontal" id="proInfo">
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-2 control-label">分类信息
										:</label>
									<div class="col-sm-10">${proInfo.type.name }</div>
								</div>
								<div class="form-group">
									<label for="inputEmail3" class="col-sm-2 control-label">标签
										:</label>
									<div class="col-sm-10" id="tags">
										<c:forEach items="${proInfo.tags }" var="tag">
											<span class="tag">${tag.tagName }</span>
										</c:forEach>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">项目名称 :</label>
									<div class="col-sm-10">${proInfo.project.name }</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">项目简介 :</label>
									<div class="col-sm-10">${proInfo.project.remark }</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">筹资金额（元）:</label>
									<div class="col-sm-10">￥ ${proInfo.project.money }.00</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">筹资天数（天）:</label>
									<div class="col-sm-10">${proInfo.project.day }</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">项目头图 :</label>
									<div class="col-sm-10">
										<c:forEach items="${proInfo.imgs }" var="img">
											<c:if test="${img.type == 0 }">
												<span class="ctImg" onclick="showImg('${img.img}')">项目头图</span>
											</c:if>
										</c:forEach>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">项目详情 :</label>
									<div class="col-sm-10" id="detailImg">
										<c:forEach items="${proInfo.imgs }" var="img" varStatus="hx">
											<c:if test="${img.type == 1 }">
												<span class="ctImg" onclick="showImg('${img.img}')">项目详情图${hx.index }</span>&nbsp;&nbsp;
											</c:if>
										</c:forEach>
									</div>
								</div>
							</form>
							<div class="page-header" style="border-bottom-style: dashed;">
								<h3>回报信息</h3>
							</div>
							<table class="table table-bordered" style="text-align: center;">
								<thead>
									<tr style="background-color: #ddd;">
										<td>序号</td>
										<td>支付金额</td>
										<td>名额</td>
										<td>单笔限购</td>
										<td>回报内容</td>
										<td>回报时间</td>
										<td>运费</td>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${proInfo.returns }" var="ret" varStatus="r">
										<tr>
											<td scope="row">${r.count }</td>
											<td>￥${ret.supportmoney }.00</td>
											<c:if test="${ret.count > 0 }">
												<td>限额${ret.count }</td>
											</c:if>
											<c:if test="${ret.count == 0 }">
												<td>不限额</td>
											</c:if>
											<c:if test="${ret.signalpurchase == 0}">
												<td>不限购</td>
											</c:if>
											<c:if test="${ret.signalpurchase == 1}">
												<td>${ret.purchase}人/把</td>
											</c:if>
											<td onclick="showImg('${ret.img}')" class="ctImg">${ret.content }</td>
											<td>项目结束后的${ret.rtndate }天</td>
											<c:if test="${ret.freight > 0}">
												<td>￥${ret.freight }.00</td>
											</c:if>
											<c:if test="${ret.freight == 0}">
												<td>免运费</td>
											</c:if>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div class="page-header" style="border-bottom-style: dashed;">
								<h3>公司信息</h3>
							</div>
							<form class="form-horizontal" id="compInfo">
								<div class="form-group">
									<label for="" class="col-sm-2 control-label">企业名称
										:</label>
									<div class="col-sm-10">${proInfo.comp.compna }</div>
								</div>
								<div class="form-group">
									<label for="" class="col-sm-2 control-label">法人身份证号
										:</label>
									<div class="col-sm-10">
										${proInfo.comp.compno }
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">社会信用代码 :</label>
									<div class="col-sm-10">${proInfo.comp.compcd }</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">商家账户（支付宝） :</label>
									<div class="col-sm-10">${proInfo.comp.compacct }</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">客服电话:</label>
									<div class="col-sm-10">${proInfo.comp.servicetel }</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">服务时间:</label>
									<div class="col-sm-10">${proInfo.comp.servicetime }</div>
								</div>
								<div class="form-group">
									<label class="col-sm-2 control-label">
										<c:if test="${ proInfo.comp.userexist == 0}">
											委托书: 
										</c:if>
										<c:if test="${ proInfo.comp.userexist == 1}">
											已认证信息: 
										</c:if>
									</label>
									<div class="col-sm-10">
										<c:if test="${proInfo.comp.userexist == 0 }">
											<span class="ctImg" onclick="showTemp('${proInfo.comp.template}')">查看委托书</span>
										</c:if>
										<c:if test="${proInfo.comp.userexist == 1 }">
											<c:forEach items="${proInfo.certInfos }" var="certInfo">
												<span class="ctImg" onclick="showImg('${certInfo.iconpath}')">${certInfo.name}</span>&nbsp;&nbsp;
											</c:forEach>
										</c:if>
									</div>
								</div>
								<div class="page-header" style="border-bottom-style: dashed;">
								<h3>审核结果</h3>
								</div>
								<div class="form-group" style="margin: 20px auto;">
									<textarea class="form-control" id="result" rows="3" style="margin-bottom: 20px;" placeholder="请在此处填写拒绝的原因"></textarea>
									<button id="passBtn" type="button" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 通过</button>
				  					<button id="refuseBtn" type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 拒绝</button>
								</div>
								
							</form>
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
	<script src="${APP_PATH }/jquery/layui/layui.js"></script>
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

		//显示回报图片
		function showImg(obj) {
			var img = "<img src='"+obj+"' style='width: 600px;height:500px'/>";
			//页面层-图片
			layer.open({
				type : 1,
				title : false,
				closeBtn : 0,
				skin : 'layui-layer-nobg', //没有背景色
				area : [ 'auto' ],
				shadeClose : true,
				content : img
			});
		}
		
		//在线预览委托书
		function showTemp(doc) {
			window.open("https://view.officeapps.live.com/op/view.aspx?src="+doc);
		}
		
		//审核通过
		$("#passBtn").click(function () {
			$.ajax({
				url:"${APP_PATH}/auth/proPass.do",
				type:"post",
				data:{
					proId:"${proInfo.project.id }"
				},
				success:function(result){
					if (result.status == 200) {
						window.location.href='${APP_PATH}/toAuthProject.htm';
					}else {
						layer.msg(result.message,{time:1000,icon:5,shift:6});
					}
				},
				error:function(result){
					layer.msg(result.message,{time:1000,icon:5,shift:6})
				}
			})
		})
		
		//审核失败
		$("#refuseBtn").click(function () {
			if($("#result").val().trim() != ""){
				$.ajax({
					url:"${APP_PATH}/auth/proRefuse.do",
					type:"post",
					data:{
						proId:"${proInfo.project.id }",
						result:$("#result").val().trim()
					},
					success:function(result){
						if (result.status == 200) {
							window.location.href='${APP_PATH}/toAuthProject.htm';
						}else {
							layer.msg(result.message,{time:1000,icon:5,shift:6});
						}
					},
					error:function(result){
						layer.msg(result.message,{time:1000,icon:5,shift:6})
					}
				})
			}else{
				layer.msg("拒绝的原因不能为空",{time:1000,icon:5,shift:6})
			}
		})
	</script>
</body>
</html>