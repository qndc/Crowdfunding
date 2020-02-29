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
								data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i>${sessionScope.member.username }<span
									class="caret"></span></a>
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

		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<nav class="navbar navbar-default" role="navigation">
						<div class="collapse navbar-collapse"
							id="bs-example-navbar-collapse-1">
							<ul class="nav navbar-nav">
								<li><a rel="nofollow" href="${APP_PATH }/index.htm"><i
										class="glyphicon glyphicon-home"></i> 众筹首页</a></li>
								<li><a rel="nofollow" href="${APP_PATH }/homepage/0/3/0/1/projects.htm"><i
										class="glyphicon glyphicon-th-large"></i> 众筹项目</a></li>
								<li><a rel="nofollow" href="${APP_PATH }/atcrowdfunding/apply.do"><i
										class="glyphicon glyphicon-edit"></i> 发起众筹</a></li>
								<li><a rel="nofollow" href="javascript:;" onclick="toAtcrowd()"><i
										class="glyphicon glyphicon-user"></i> 我的众筹</a></li>
							</ul>
						</div>
					</nav>
				</div>
			</div>
		</div>


		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="jumbotron nofollow" style="padding-top: 10px;">
						<h3>${project.name }</h3>
						<div style="float: left; width: 70%;">
							${project.remark }</div>
						<div style="float: right;">
							<button type="button" class="btn btn-default" id="follower">
								<c:if test="${requestScope.isFollow  == true}">
									<i style="color: #f60" class="fa fa-heart"></i>
									<span>取消关注&nbsp;&nbsp;${project.follower } </span>
								</c:if>
								<c:if test="${requestScope.isFollow  == false}">
									<i style="color: #f60" class="fa fa-heart-o"></i>
									<span>关注&nbsp;&nbsp;${project.follower } </span>
								</c:if>
							</button>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="row clearfix">
						<div class="col-md-8 column">
							<c:forEach items="${requestScope.imgs }" var="timg">
								<img alt="140x140" width="740" src="${timg.img }" />
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
										<button type="button"
											class="btn  btn-warning btn-lg btn-block" data-toggle="modal"
											data-target="#myModal">立即支持</button>
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
										<button type="button" class="btn  btn-warning btn-ms" style="float: right;margin-top: -8px"
										onclick="toSupport(${project.id},${ret.id })">支持</button>
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

							<div class=" panel panel-default" style="border-radius: 0px;">
								<div class="panel-heading">
									<h3>风险提示</h3>
								</div>
								<div class="panel-body">
									<p>
										1.众筹并非商品交易，存在一定风险。支持者根据自己的判断选择、支持众筹项目，与发起人共同实现梦想并获得发起人承诺的回报。<br>
										2.众筹平台仅提供平台网络空间及技术支持等中介服务，众筹仅存在于发起人和支持者之间，使用众筹平台产生的法律后果由发起人与支持者自行承担。<br>
										3.本项目必须在2017-06-09之前达到￥10000.00
										的目标才算成功，否则已经支持的订单将取消。订单取消或募集失败的，您支持的金额将原支付路径退回。<br>
										4.请在支持项目后15分钟内付款，否则您的支持请求会被自动关闭。<br>
										5.众筹成功后由发起人统一进行发货，售后服务由发起人统一提供；如果发生发起人无法发放回报、延迟发放回报、不提供回报后续服务等情况，您需要直接和发起人协商解决。<br>
										6.如您不同意上述风险提示内容，您有权选择不支持；一旦选择支持，视为您已确认并同意以上提示内容。
									</p>
								</div>
							</div>

							<div>
								<h2>为你推荐</h2>
								<hr>
							</div>
							<div class="prjtip panel panel-default"
								style="border-radius: 0px;">
								<div class="panel-body">
									<img src="${APP_PATH }/img/product-3.png" width="100%" height="100%">
								</div>
							</div>

							<div class="prjtip panel panel-default"
								style="border-radius: 0px;">
								<div class="panel-body">
									<img src="${APP_PATH }/img/product-4.jpg" width="100%" height="100%">
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
							<a rel="nofollow" href="http://www.atguigu.com">关于我们</a> | <a
								rel="nofollow" href="http://www.atguigu.com">服务条款</a> | <a
								rel="nofollow" href="http://www.atguigu.com">免责声明</a> | <a
								rel="nofollow" href="http://www.atguigu.com">网站地图</a> | <a
								rel="nofollow" href="http://www.atguigu.com">联系我们</a>
						</div>
						<div class="copyRight">Copyright ?2010-2014atguigu.com 版权所有
						</div>
					</div>

				</div>
			</div>
		</div>

	</div>
	<!-- /container -->


	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog " style="width: 960px; height: 400px;"
			role="document">
			<div class="modal-content" data-spy="scroll"
				data-target="#myScrollspy">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">选择支持项</h4>
				</div>
				<div class="modal-body">
					<div class="container-fluid">
						<div class="row clearfix">
							<div class="col-sm-3 col-md-3 column" id="myScrollspy">
								<ul class="nav nav-tabs nav-stacked">
									<c:forEach items="${requestScope.returntList }" var="ret" varStatus="hx">
										<li class="active"><a href="#section-${hx.count }">￥${ret.supportmoney }.00</a></li>
									</c:forEach>
								</ul>
							</div>
							<div id="navList" class="col-sm-9 col-md-9 column"
								style="height: 400px; overflow-y: auto;">
								<c:forEach items="${requestScope.returntList }" var="ret" varStatus="hx">
									<h2 id="section-${hx.count }" style="border-bottom: 1px dashed #ddd;">
									<span style="font-size: 20px; font-weight: bold;">￥${ret.supportmoney }.00</span>
									<c:if test="${ret.count == 0 }">
										<span style="font-size: 12px; margin-left: 60px;">无限额，<span style="color: #fd6359">0</span>位支持者</span>
									</c:if>
									<c:if test="${ret.count != 0 }">
										<span style="font-size: 12px; margin-left: 60px;">限额<span style="color: #fd6359">${ret.count}</span>位，剩余<span style="color: #fd6359">${ret.count}</span>位</span>
									</c:if>
									</h2>
									<c:if test="${ret.freight == 0 }">	
										<p>配送费用：包邮</p>
									</c:if>
									<c:if test="${ret.freight != 0 }">
										<p>配送费用：${ret.freight }元</p>
									</c:if>
									<p>预计发放时间：项目筹款成功后的${ret.rtndate }天内</p>
									<img src="${ret.img }" 
									style="width: 60px;height: 60px;cursor: pointer;margin-bottom:10px;border: 1px solid rgb(227,227,227);">
									<p>${ret.returndesc }</p>
									<button type="button" class="btn  btn-warning btn-lg "
										onclick="toSupport(${project.id},${ret.id })">支持</button>
									<hr>
								</c:forEach>
							</div>
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
	<script>
		$(".prjtip img").css("cursor", "pointer");
		$(".prjtip img").click(function() {
			window.location.href = 'project.html';
		});
		
		function toAtcrowd() {
			$.ajax({
				url:"${APP_PATH}/atcrowdfunding/toIndex.htm",
				type:"GET",
				success:function(result){
					console.log(result);
					if (result.status == 200) {
						window.location.href='/atcrowdfunding/index.htm';
					}else {
						layer.msg(result.message,{time:1000,icon:5,shift:6});
					}
				},
				error:function(result){
					layer.msg(result.message,{time:1000,icon:5,shift:6})
				}
			})
		}
		
		//实名认证后才可支持项目
		function toSupport(proid,retid) {
			if (${sessionScope.member.authstatus} != 2) {
				layer.msg("请先实名认证！",{time:1000,icon:5,shift:6});
			}else{
				window.location.href='/homepage/'+proid+'/'+retid+'/confirmReturn.htm';
			}
		}
		
		//关注
		$("#follower").click(function () {
			var btn = $(this);
			if ($(this).find($("i")).attr("class") == "fa fa-heart") {
				$.ajax({
					url:"${APP_PATH}/homepage/cancel.do",
					type:"post",
					data:{
						"proId":"${project.id }"
					},
					success:function(result){
						console.log(result);
						if (result.status == 200) {
							//样式修改
							btn.find($("i")).attr("class","fa fa-heart-o");
							btn.find($("span")).text("关注 "+ result.message);
						}else{
							layer.msg(result.message,{time:1000,icon:5,shift:6})
						}
					},
					error:function(result){
						layer.msg(result.message,{time:1000,icon:5,shift:6})
					}
				})
			}else{
				$.ajax({
					url:"${APP_PATH}/homepage/follower.do",
					type:"post",
					data:{
						"proId":"${project.id }"
					},
					success:function(result){
						console.log(result);
						if (result.status == 200) {
							
							btn.find($("i")).attr("class","fa fa-heart");
							btn.find($("span")).text("取消关注 " + result.message.followers);
						}else{
							layer.msg(result.message,{time:1000,icon:5,shift:6})
						}
					},
					error:function(result){
						layer.msg(result.message,{time:1000,icon:5,shift:6})
					}
				})
			}
		});
	</script>
</body>
</html>