<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>交易详情</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/theme.css">
<link rel="icon" href="${APP_PATH }/img/crowdfunding.png" sizes="32x32" type="image/png">
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

.label-type, .label-status, .label-order {
	background-color: #fff;
	color: #f60;
	padding: 5px;
	border: 1px #f60 solid;
}

#typeList
:not(:first-child){
margin-top:20px;

}
.label-txt {
	margin: 10px 10px;
	border: 1px solid #ddd;
	padding: 4px;
	font-size: 14px;
}

.panel {
	border-radius: 0;
}

.progress-bar-default {
	background-color: #ddd;
}
#orderInfo li{
	list-style-type: none;
	
}

#orderInfo li .title{
	display: inline-block;
	width: 80px;
	text-align: right;
	margin: 0 10px 15px 0;
	font-weight: bold;
	color: #666;	
}
.project-info {
    width: 900px;
    table-layout: fixed;
    border: 1px solid grey;
    border-spacing: 0px;
	border-collapse: collapse;
	margin-left: 95px;
	text-align: center;
	margin-bottom: 20px;
}
	
.project-info td{
	border: 1px gray solid;
	padding: 5px;
}

.project-info th{
	border: 1px gray solid;
	padding: 5px;
	text-align: center;
}
.num{
	color: #F84D2C;
    font-weight: bold;
}
</style>
</head>
<body>
	<div class="navbar-wrapper">
		<div class="container">
			<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
				<div class="container">
					<div class="navbar-header">
						<a class="navbar-brand" href="index.html" style="font-size: 32px;">尚筹网-创意产品众筹平台</a>
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
									<li><a href="#"><i class="glyphicon glyphicon-comment"></i>消息</a></li>
									<li class="divider"></li>
									<li><a href="${APP_PATH }/logout.do"><i
											class="glyphicon glyphicon-off"></i> 退出系统</a></li>
								</ul></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
	</div>

	<div class="container theme-showcase" role="main">


		<div class="container" style="height: 500px">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="panel panel-default" style="height: 600px;margin-top: 20px;">
						<div class="panel-heading">
							<h4 style="color: #666;margin-left: 20px;"><b>交易详情</b></h4>
						</div>
						<div class="panel-body">
							<div class="container-fluid">
								<div class="row clearfix">
									<div class="col-md-12 column" style="margin: 30px 0">
										<ul id="orderInfo">
											<li>
												<span class="title">订单号：</span>
												${order.ordernum }
											</li>
											<li>
												<span class="title">订单日期：</span>
												${order.createdate }
											</li>
											<li>
												<span class="title">订单状态：</span>
												${order.status }
											</li>
											<li>
												<span class="title">收货地址：</span>
												${address.name } ${address.phone } ${address.address }
											</li>
											<c:if test="${order.invoice == 0 }">
												<li>
													<span class="title">发票信息：</span>
													不开发票
												</li>
											</c:if>
											<c:if test="${order.invoice == 1 }">
												<li>
													<span class="title">发票抬头：</span>
													${invoice.invoice }
												</li>
												<c:if test="${ invoice.type == 0}">
													<li>
														<span class="title">税号：</span>
														${invoice.tax }
													</li>
												</c:if>
											</c:if>
											<li>
												<span class="title">项目信息：</span><br>
												<table class="project-info" >
													<tbody>
														<tr>
															<th width="179">项目名称</th>
															<th width="119">发起人</th>
															<th width="319">回报内容</th>
															<th width="90">回报数量</th>
															<th width="90">支持单价</th>
															<th style="width:90px;">配送费用</th>
														</tr>
														<tr>
															<td>${project.name }</td>
															<td>${comp.compna } </td>
															<td>${tReturn.content }</td>
															<td>${order.rtncount }</td>
															<td class="num">¥ ${order.money }.00</td>
															<td class="num">¥ ${tReturn.freight }.00</td>
														</tr>
													</tbody>
												</table>
											</li>
											<li>
												<span class="title">支付总额：</span>
												<span class="num">¥ ${order.money }.00</span>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="container" style="margin-top: 20px;">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div id="footer">
						<div class="footerNav">
							<a rel="nofollow" href="http://www.drper.cn">关于我们</a> | <a
								rel="nofollow" href="http://www.drper.cn">服务条款</a> | <a
								rel="nofollow" href="http://www.drper.cn">免责声明</a> | <a
								rel="nofollow" href="http://www.drper.cn">网站地图</a> | <a
								rel="nofollow" href="http://www.drper.cn">联系我们</a>
						</div>
						<div class="copyRight">Copyright ?2020-2025 drper.cn 版权所有
						</div>
					</div>

				</div>
			</div>
		</div>

	</div>
	<!-- /container -->
	<script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
	<script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script src="${APP_PATH }/script/back-to-top.js"></script>
	<script>
		$('#myTab a').click(function(e) {
			e.preventDefault()
			$(this).tab('show')
		})
		
		//数量改变，总价改变
		$("#num").change(function () {
			//总价 = 单价 * 数量
			var unit = '${ret.supportmoney }';//单价
			var num = $("#num").val();
			var total = unit * num;
			$("#total").text("￥ "+total+".00");//总价
		})
		
		//去结算
		function toPay() {
			var num = $("#num").val();
			window.location.href='/homepage/${ret.projectid}/${ret.id}/'+num+'/confirmOrder.htm';
		}
	</script>
</body>
</html>