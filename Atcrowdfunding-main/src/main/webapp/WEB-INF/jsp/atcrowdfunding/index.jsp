<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的众筹</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/theme.css">
<style>
#footer {
	padding: 15px 0;
	background: #fff;
	border-top: 1px solid #ddd;
	text-align: center;
}

#topcontrol {
	color: #fff;
	z-index: 99;
	width: 30px;
	height: 30px;
	font-size: 20px;
	background: #222;
	position: relative;
	right: 14px !important;
	bottom: 11px !important;
	border-radius: 3px !important;
}

#topcontrol:after {
	/*top: -2px;*/
	left: 8.5px;
	content: "\f106";
	position: absolute;
	text-align: center;
	font-family: FontAwesome;
}

#topcontrol:hover {
	color: #fff;
	background: #18ba9b;
	-webkit-transition: all 0.3s ease-in-out;
	-moz-transition: all 0.3s ease-in-out;
	-o-transition: all 0.3s ease-in-out;
	transition: all 0.3s ease-in-out;
}
.thumbnail{
	margin-bottom: 0px;
}
.thumbnail .caption h4,p{
	margin: 0 0 8px 0;
}
#crowfundStatus li a,#payStatus li a{
	padding: .2em .6em .3em;
	font-size: 75%;
    font-weight: 700;
    color: #000;
}
#crowfundStatus li.active a,#payStatus li.active a{
	background-color: #f0ad4e;
	color: #fff;
}

</style>
</head>
<body>
	<div class="navbar-wrapper">
		<div class="container">
			<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
				<div class="container">
					<div class="navbar-header">
						<a class="navbar-brand" href="/" style="font-size: 32px;">尚筹网-创意产品众筹平台</a>
					</div>
					<div id="navbar" class="navbar-collapse collapse"
						style="float: right;">
						<ul class="nav navbar-nav">
							<li class="dropdown"><a href="#" class="dropdown-toggle"
								data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i>
									${sessionScope.member.username }<span class="caret"></span></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="${APP_PATH }/member.htm"><i
											class="glyphicon glyphicon-scale"></i> 会员中心</a></li>
									<li><a href="#"><i class="glyphicon glyphicon-comment"></i>
											消息</a></li>
									<li class="divider"></li>
									<li><a href="${APP_PATH }/logout.do"><i
											class="glyphicon glyphicon-off"></i> 退出系统</a></li>
								</ul>
							</li>
						</ul>
					</div>
				</div>
			</nav>

		</div>
	</div>
	<div class="container"  style="height: 650px">
		<div class="row clearfix">
			<div class="col-sm-3 col-md-3 column">
				<div class="row">
					<div class="col-md-12">
						<div class="thumbnail" style="border-radius: 0px;">
							<img src="${APP_PATH }/img/services-box1.jpg" class="img-thumbnail" alt="">
							<div class="caption" style="text-align: center;">
								<h3>${sessionScope.member.username }</h3>
								<c:choose>
									<c:when test="${member.authstatus eq '1' }">
										<span class="label label-warning" style="cursor: pointer;">实名认证中</span>
									</c:when>
									<c:when test="${member.authstatus eq '2' }">
										<span class="label label-success" style="cursor: pointer;">已实名认证</span>
									</c:when>
									<c:otherwise>
										<span class="label label-danger" style="cursor: pointer;" 
										onclick="window.location.href='${APP_PATH}/member/toAcctType.htm'">未实名认证</span>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
				<div class="list-group">
					<div class="list-group-item" style="cursor: pointer;"
						onclick="window.location.href='/member.htm'">
						资产总览<span class="badge"><i
							class="glyphicon glyphicon-chevron-right"></i></span>
					</div>
					<div class="list-group-item active" style="cursor: pointer;"
						onclick="window.location.href='/atcrowdfunding/index.htm'">
						我的众筹<span class="badge"><i
							class="glyphicon glyphicon-chevron-right"></i></span>
					</div>
				</div>
			</div>
			<div class="col-sm-9 col-md-9 column">
				<ul id="myTab" style="" class="nav nav-pills" role="tablist">
					<li role="presentation" class="active"><a href="#home"
						role="tab" data-toggle="tab" aria-controls="home"
						aria-expanded="true">我的众筹</a></li>
					<li role="presentation"><a href="#profile">众筹资产</a></li>
				</ul>
				<div id="myTabContent" class="tab-content" style="margin-top: 10px;">
					<div role="tabpanel" class="tab-pane fade active in" id="home" aria-labelledby="home-tab">
						<ul id="myTab1" class="nav nav-tabs">
							<li role="presentation" class="active"><a href="#support">我支持的</a></li>
							<li role="presentation" onclick="myFollow()"><a href="#attension" >我关注的</a></li>
							<li role="presentation" onclick="Interaction(0)"><a href="#add">我发起的</a></li>
							<li class=" pull-right">
								<button type="button" class="btn btn-warning" onclick="window.location.href='/atcrowdfunding/apply.do'">发起众筹</button>
							</li>
						</ul>
						<div id="myTab1" class="tab-content" style="margin-top: 10px;">
							<!-- 我支持的 -->
							<div role="tabpanel" class="tab-pane fade active in" id="support" aria-labelledby="home-tab">
								<div class="container-fluid">
									<div class="row clearfix">
										<ul class="nav nav-pills" role="tablist" id="payStatus">
											<li role="presentation" class="active"><a href="#orders">全部</a></li>
										    <li role="presentation" ><a href="#paid">已支付</a></li>
										    <li role="presentation"><a href="#unpaid">未支付</a></li>
										</ul>
										<div class="tab-content">
											<div role="tabpanel" class="tab-pane active" id="orders"></div>
										    <div role="tabpanel" class="tab-pane" id="paid"></div>
										    <div role="tabpanel" class="tab-pane" id="unpaid"></div>
										</div>
									</div>
								</div>
							</div>
							<!-- 我关注的 -->
							<div role="tabpanel" class="tab-pane fade" id="attension"
								aria-labelledby="attension-tab">
								<div class="container-fluid">
									<div class="row clearfix">
										<div class="col-md-12 column" style="padding: 0;">
											<table class="table table-bordered"
												style="text-align: center;">
												<thead>
													<tr style="background-color: #ddd;">
														<td>项目信息</td>
														<td width="120">支持人数</td>
														<td width="120">关注人数</td>
														<td width="120">操作</td>
													</tr>
												</thead>
												<tbody id="follow"></tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
							<!-- 我发起的 -->
							<div role="tabpanel" class="tab-pane fade" id="add" aria-labelledby="add-tab">
								<div class="container-fluid">
									<div class="row clearfix">
										<div class="col-md-12 column">
											<ul class="nav nav-pills" role="tablist" id="crowfundStatus">
											    <li role="presentation" class="active"><a href="#all">全部</a></li>
											    <li role="presentation"><a href="#crowdfunding">众筹中</a></li>
											    <li role="presentation"><a href="#success">众筹成功</a></li>
											    <li role="presentation"><a href="#fail">众筹失败</a></li>
										    </ul>
										    <div class="tab-content">
											    <div role="tabpanel" class="tab-pane active" id="all"></div>
											    <div role="tabpanel" class="tab-pane" id="crowdfunding"></div>
											    <div role="tabpanel" class="tab-pane" id="success"></div>
											    <div role="tabpanel" class="tab-pane" id="fail"></div>
										    </div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div role="tabpanel" class="tab-pane fade" id="profile"
						aria-labelledby="profile-tab">众筹资产</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row clearfix">
			<div class="col-md-12 column">
				<div id="footer">
					<div class="footerNav">
						<a rel="nofollow" href="http://www.atguigu.com">关于我们</a> | <a
							rel="nofollow" href="http://www.atguigu.com">服务条款</a> | <a
							rel="nofollow" href="http://www.atguigu.com">免责声明</a> | <a
							rel="nofollow" href="http://www.atguigu.com">网站地图</a> | <a
							rel="nofollow" href="http://www.atguigu.com">联系我们</a>
					</div>
					<div class="copyRight">Copyright ?2017-2017atguigu.com 版权所有</div>
				</div>

			</div>
		</div>
	</div>
	<script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
	<script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script src="${APP_PATH }/script/back-to-top.js"></script>
	<script src="${APP_PATH }/jquery/layer/layer.js"></script>
	<script src="${APP_PATH }/js/atcrowdfunding.js"></script>
	<script>
		$('#myTab a').click(function(e) {
			e.preventDefault()
			$(this).tab('show')
		})
		$('#myTab1 a').click(function(e) {
			e.preventDefault()
			$(this).tab('show')
		})

	</script>

</body>

</html>