<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>个人中心</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/theme.css">
<link rel="icon" href="${APP_PATH }/img/crowdfunding.png" sizes="32x32" type="image/png">
<style>
em, i {
    font-style: normal;
}
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
.no-result .info .empty {
    float: left;
    width: 50px;
    height: 50px;
    font-size: 14px;
    overflow: hidden;
    background: url(../img/crowdfunding.jpg) -13px 0px no-repeat;
}
.control-label {
    float: left;
    width: 194px;
    text-align: right;
    line-height: 26px;
    color: #666;
}
.control-label em {
    color: #f00;
}
.form-group {
    margin-bottom: 10px;
}

/*地址管理样式*/
.address-def .default {
    margin-top: 19px;
    height: 149px;
}
.default .defau-mod {
    margin-right: 20px;
    margin-bottom: 10px;
}
.defau-mod {
    margin: 0;
    padding: 0;
    width: 243px;
    height: 148px;
    box-shadow: none;
    border: solid #DDD;
    border-width: 0 1px 1px 1px;
    float: left;
    position: relative;
}
.defau-mod .addr-state {
    margin-top: 6px;
    padding: 0 10px;
}
.defau-mod:hover .addr-topbg {
    background-position: 0 -188px;
}
.defau-mod:hover .compile {
    display: block;
}
.defau-mod:hover .delete {
    display: block;
}
.default .def-addr-sure {
    display: block;
}
.addr-state .def-addr-sure {
    float: none;
    height: 22px;
    clear: none;
}

.def-addr-sure .setdef {
    float: left;
}
.default .addr-info-sure {
    margin-top: 12px;
}

.addr-info-sure {
    padding: 0 20px;
}
.addr-ne {
    padding-bottom: 7px;
    border-bottom: 1px dashed #DDD;
}
.default .def-addr-sure a {
    float: left;
    line-height: 22px;
}
.def-addr-sure a {
    color: #F60;
    text-decoration: none;
}
.default .def-addr-sure .home {
	font-size:10px;
    float: left;
    margin-top:5px;
    margin-right: 5px;
}

.addr-ne {
    height: 24px;
    line-height: 18px;
    white-space: nowrap;
    text-overflow: ellipsis;
    overflow: hidden;
}
.addr-hd {
     margin-top: 3px;
     height: 26px;
    line-height: 18px;
    white-space: nowrap;
    text-overflow: ellipsis;
    overflow: hidden;
}
.addr-ne .name {
    padding-right: 13px;
    font-weight: 400;
}
.addr-ne .iphone {
    font-family: "Arial";
}

.addr-info-sure span {
    color: #333;
}
.defau-mod span, .defau-mod a {
    font-size: 12px;
    line-height: 18px;
    font-family: "SimSun";
}
.addr-hd span {
    line-height: 26px;
}

.addr-info-sure span {
    color: #333;
}
.addr-hd span {
    padding-right: 8px;
}
.addr-deail span {
    color: #999;
}
.defau-mod span, .defau-mod a {
    font-size: 12px;
    line-height: 18px;
    font-family: "SimSun";
}
.def-addr-sure .way {
    position: relative;
    float: right;
    margin-top: 6px;
    margin-left: 9px;
    cursor: pointer;
    *z-index: 1;
}
.delete {
    display: none;
    height: 12px;
    width: 14px;
    background: url(../img/newbg.png) no-repeat -28px 0;
    overflow: hidden;
}
.compile {
    display: none;
    height: 12px;
    width: 14px;
    background: url(../img/newbg.png) no-repeat -14px 0;
    overflow: hidden;
}
.def-addr-sure .way p {
    display: none;
    position: absolute;
    top: -27px;
    padding: 0 4px;
    line-height: 20px;
    background-color: #FFF;
    border: 1px solid #AAA;
    text-align: center;
    white-space: nowrap;
    z-index: 1;
}

.defau-mod .addr-topbg {
    position: absolute;
    top: 0;
    left: -1px;
    width: 243px;
    height: 6px;
    background: url(../img/newbg.png) no-repeat 0 -194px;
    overflow: hidden;
}

.defau-mod .addr-botbg {
    position: absolute;
    bottom: -1px;
    right: -1px;
    _right: -2px;
    _bottom: -2px;
    width: 20px;
    height: 20px;
    background: url(../img/newbg.png) no-repeat 0 -168px;
    overflow: hidden;
}
.add-addr {
    width: 245px;
    height: 149px;
    overflow: hidden;
}
.add-addr {
    float: left;
    width: 250px;
    height: 149px;
    background: url(../img/add-adder.png) no-repeat 0 0;
    cursor: pointer;
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
					<div class="list-group-item" style="cursor: pointer;"
						onclick="window.location.href='/atcrowdfunding/index.htm'">
						我的众筹<span class="badge"><i
							class="glyphicon glyphicon-chevron-right"></i></span>
					</div>
					<div class="list-group-item active" style="cursor: pointer;"
						onclick="window.location.href='/atcrowdfunding/personal.htm'">
						个人中心<span class="badge"><i
							class="glyphicon glyphicon-chevron-right"></i></span>
					</div>
				</div>
			</div>
			<div class="col-sm-9 col-md-9 column">
				<div id="myTabContent" class="tab-content" style="margin-top: 10px;">
					<div role="tabpanel" class="tab-pane fade active in" id="home" aria-labelledby="home-tab">
						<ul id="myTab1" class="nav nav-tabs">
							<li role="presentation" class="active"><a href="#info">个人信息</a></li>
							<li role="presentation" onclick="Loadaddr()"><a href="#addr" >地址管理</a></li>
							<li role="presentation" onclick=""><a href="#invoice">发票管理</a></li>
						</ul>
						<div id="myTab1" class="tab-content" style="margin-top: 10px;">
							<!--个人信息 -->
							<div role="tabpanel" class="tab-pane fade active in" id="info" aria-labelledby="home-tab">
								<div class="container-fluid">
									<div class="row clearfix">
										<form class="form-horizontal">
											<div class="form-group">
												<label for="realname" class="col-sm-2 control-label"><em>*</em>真实姓名：</label>
												<div class="col-sm-6"  style="padding-top: 3px">
													<input type="email" class="form-control" id="realname">
												</div>
											</div>
											<div class="form-group">
												<label for="username" class="col-sm-2 control-label">昵称：</label>
												<div class="col-sm-6"  style="padding-top: 3px">
													<input type="text" class="form-control" id="username">
												</div>
											</div>
											<div class="form-group">
												<label for="username" class="col-sm-2 control-label"><em>*</em>性别：</label>
												<div class="col-sm-6" style="padding-top: 3px">
													<label class="radio-inline"> <input type="radio"
														name="sex" id="inlineRadio1" value="option1">
														男
													</label> 
													<label class="radio-inline"> <input
														type="radio" name="inlineRadioOptions" id="inlineRadio2"
														value="option2">女
													</label>
												</div>
											</div>
											<div class="form-group">
												<label for="username" class="col-sm-2 control-label">手机：</label>
												<div class="col-sm-6"  style="padding-top: 3px;line-height: 20px">
													18229735193
												</div>
											</div>
											
										</form>
									</div>
								</div>
							</div>
							<!-- 地址管理 -->
							<div role="tabpanel" class="tab-pane fade" id="addr"
								aria-labelledby="attension-tab">
								<div class="container-fluid">
									<div class="default clearfix" id="addrs">
										<div name="address_index_info_address01" class="add-addr"></div>
						            </div>
						            
								</div>
							</div>
							<!-- 发票管理 -->
							<div role="tabpanel" class="tab-pane fade" id="invoice" aria-labelledby="add-tab">
								<div class="container-fluid">
									<div class="row clearfix">
										<div class="col-md-12 column">
											发票管理
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
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
	<script src="${APP_PATH }/js/personal.js"></script>
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