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
<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/theme.css">
<link rel="icon" href="${APP_PATH }/img/crowdfunding.png" sizes="32x32" type="image/png">
<title>项目详情</title>
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

.nav-tabs>li.active>a, .nav-tabs>li.active>a:focus, .nav-tabs>li.active>a:hover
	{
	border-bottom-color: #ddd;
}

.nav-tabs>li>a {
	border-radius: 0;
}

.data_box{
	width:1200px;
	height: 280px;
	margin:50px auto;
}
.data_box ul li{
	display: block;
	float: left;
	width:280px;
	height:170px;
	margin:10px;
}
.data_box ul li img{
	width:100%;
}
body {
    padding-top: 20px;
    padding-bottom: 30px;
}
.container{
	padding: 0
}
</style>
</head>
<body>
	<div class="container theme-showcase" role="main">

		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="jumbotron nofollow" style="padding-top: 10px;">
						<h3>${project.name }</h3>
						<div style="float: left; width: 70%;">${project.remark }</div>
					</div>
				</div>
			</div>
		</div>

		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="row clearfix">
						<div class="col-md-8 column" style="width: ">
							<c:forEach items="${requestScope.imgs }" var="timg">
								<img alt="140x140" width="640" src="${timg.img }" />
							</c:forEach>
						</div>
						<div class="col-md-4 column">
							<div class="panel panel-default" style="border-radius: 0px;">
								<div class="panel-heading"
									style="background-color: #fff; border-color: #fff;">
									<span class="label label-success"><i
										class="glyphicon glyphicon-tag"></i> 众筹中</span>
								</div>
								<div class="panel-body">
									<h3>已筹资金：￥${project.supportmoney }.00</h3>
									<p>
										<span>目标金额 ： ￥${project.money }.00</span><span style="float: right;">达成
											： ${project.completion }%</span>
									</p>
									<div class="progress" style="height: 10px; margin-bottom: 5px;">
										<div class="progress-bar progress-bar-success"
											role="progressbar" aria-valuenow="60" aria-valuemin="0"
											aria-valuemax="100" style="width: ${project.completion }%;"></div>
									</div>
									<p>${requestScope.time }</p>
									<div>
										<p>
											<span>已有${project.supporter }人支持该项目
										</p>
										
									</div>
								</div>
								<div class="panel-footer"
									style="background-color: #fff; border-top: 1px solid #ddd; border-bottom-right-radius: 0px; border-bottom-left-radius: 0px;">
									<div class="container-fluid">
										<div class="row clearfix">
											<div class="col-md-3 column" style="padding: 0;">
												<img alt="140x140" src="${APP_PATH }/img/avat.png"
													data-holder-rendered="true"
													style="width: 80px; height: 80px;margin-top: 10px">
											</div>
											<div class="col-md-9 column">
												<div class="">
													<h5>
														<b>${comp.compna }</b> <span
															style="float: right; font-size: 10px;"
															class="label label-success">已认证</span>
													</h5>
													<p style="font-size: 12px">客服电话:${comp.servicetel }</p>
													<p style="font-size: 12px">服务时间:${comp.servicetime }</p>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<c:forEach items="${requestScope.returntList }" var="ret">
								<div class="panel panel-default" style="border-radius: 0px;" class= ".example">
								<div class="panel-heading">
									<h3>
										￥${ret.supportmoney }.00 
									</h3>
								</div>
								<div class="panel-body">
								<p style="font-size: 22px">${ret.content }</p>
								<p>${ret.returndesc }</p>
								<c:if test="${ret.count == 0 }">
									<p style="font-size: 12px;color: #999;">不限额，<span style="color: #fd6359">0</span>位支持者</p>
								</c:if>
								<c:if test="${ret.count != 0 }">
									<p style="font-size: 12px;color: #999;">限额<span style="color: #fd6359">${ret.count}</span>位，剩余<span style="color: #fd6359">${ret.count}</span>位</p>
								</c:if>
								<c:if test="${ret.freight == 0 }">	
									<p style="font-size: 12px;color: #999;">配送费用：包邮</p>
								</c:if>
								<c:if test="${ret.freight != 0 }">
									<p style="font-size: 12px;color: #999;">配送费用：${ret.freight }元</p>
								</c:if>
									<img alt="" src="${ret.img }" style="width: 60px;height: 60px;cursor: pointer;margin-bottom:10px;border: 1px solid rgb(227,227,227);">
									<p style="font-size: 12px;color: #999;">预计发放时间：项目筹款成功后的${ret.rtndate }天内</p>
								</div>
								</div>
							</c:forEach>
							
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
	<script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script src="${APP_PATH }/script/back-to-top.js"></script>
	<script src="${APP_PATH }/jquery/layer/layer.js"></script>
</body>
</html>