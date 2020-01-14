<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>项目总览</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet"
	href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/theme.css">
<style type="text/css">
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

#typeList .active {
	background-color: #fff;
	color: #f60;
	padding: 5px;
	border: 1px #f60 solid;
	border-radius: 3px;
}

#typeList  :not (:first-child ) {
	margin-top: 20px;
}

#typeList li{
	margin-bottom: 10px;
	
}
#typeList li a{
	text-decoration: none;
	color:#000;
	margin-right: 10px;
	cursor: pointer;
}

#typeList li a:hover{
	color: #f60;
}

.panel {
	border-radius: 0;
}

h3.break {
	font-size: 16px;
	display: block;
	white-space: nowrap;
	word-wrap: normal;
	overflow: hidden;
	text-overflow: ellipsis;
}

h3.break>a {
	text-decoration: none;
}
</style>
</head>
<body>
	<div class="navbar-wrapper">
		<div class="container">
			<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
				<div class="container">
					<div class="navbar-header">
						<a class="navbar-brand" href="#" style="font-size: 32px;">互联网众筹系统</a>
					</div>
					<div id="navbar" class="navbar-collapse collapse"
						style="float: right;">
						<ul class="nav navbar-nav">
							<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i>${sessionScope.member.username }<span class="caret"></span></a>
			                  <ul class="dropdown-menu" role="menu">
			                    <li><a href="member.html">会员中心</a></li>
			                    <li><a href="message.html">消息 <span class="badge badge-success">42</span></a></li>
			                    <li class="divider"></li>
			                    <li><a href="${APP_PATH }/logout.do">退出系统</a></li>
			                  </ul>
                  			</li>
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
								<li><a rel="nofollow" href="/"><i
										class="glyphicon glyphicon-home"></i> 众筹首页</a></li>
								<li class="active"><a rel="nofollow" href="javascript:;"><i
										class="glyphicon glyphicon-th-large"></i> 项目总览</a></li>
								<li><a rel="nofollow" style="cursor: pointer;" onclick="toStart()"><i
										class="glyphicon glyphicon-edit"></i> 发起众筹</a></li>
								<li><a rel="nofollow" style="cursor: pointer;" onclick="toAtcrowd()"><i
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
					<div class="panel panel-default">
						<div class="panel-body">
							<ul id="typeList" style="list-style: none;">
								<li>分类：
									<a href="/homepage/0/3/0/1/projects.htm" class="${map.typeid  == 0 ? 'active':'' }">全部</a>
									<c:forEach items="${requestScope.map.types }" var="type">
										<a href="/homepage/${type.id }/3/0/1/projects.htm" class="${map.typeid  == type.id ? 'active':'' }">${type.name }</a> 
									</c:forEach>
									
								</li>
								<li>状态：
									<a href="/homepage/${map.typeid }/3/0/1/projects.htm" class="${map.status  == 3 ? 'active':'' }">全部</a>
									<a href="/homepage/${map.typeid }/0/0/1/projects.htm" class="${map.status  == 0 ? 'active':'' }">即将开始</a>
									<a href="/homepage/${map.typeid }/1/0/1/projects.htm" class="${map.status  == 1 ? 'active':'' }">众筹中</a>
									<a href="/homepage/${map.typeid }/2/0/1/projects.htm" class="${map.status  == 2 ? 'active':'' }">众筹成功</a>
								</li>
								<li>排序：
									<a href="/homepage/${map.typeid }/${map.status }/0/${map.page.pageno }/projects.htm" class="${map.sort  == 0 ? 'active':'' }">综合排序</a> 
									<a href="/homepage/${map.typeid }/${map.status }/1/${map.page.pageno }/projects.htm" class="${map.sort  == 1 ? 'active':'' } ${map.status  == 0 || map.status == 2 ? 'hide':'' }">最新上线</a> 
									<a href="/homepage/${map.typeid }/${map.status }/2/${map.page.pageno }/projects.htm" class="${map.sort  == 2 ? 'active':'' }">金额最多</a>
									<a href="/homepage/${map.typeid }/${map.status }/3/${map.page.pageno }/projects.htm" class="${map.sort  == 3 ? 'active':'' } ${map.status  == 0 ? 'hide':'' }">支持最多</a>
								</li>
							</ul>
						</div>
						<div class="panel-footer" style="height: 50px; padding: 0;">
							<div style="float: left; padding: 15px;">共${map.page.totalsize}个众筹项目</div>
							<div style="float: right;">
								<form class="navbar-form navbar-left" role="search" action="/homepage/0/3/0/1/projects.htm">
									<div class="form-group">
										<input type="text" class="form-control" name="keyWords" placeholder="请输入搜索内容">
									</div>
									<button type="submit" class="btn btn-default">
										<i class="glyphicon glyphicon-search"></i> 搜索
									</button>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column">
					<div class="row">
						<c:forEach items="${map.page.data }" var="typeAndPro">
							<div class="col-md-3">
							<div class="thumbnail">
								<img alt="300x200"  style="width: 250px;height: 150px;cursor: pointer;" src="${typeAndPro.imgs.img }" onclick="detailInfo(${typeAndPro.id},${ typeAndPro.status})"/>
								<div class="caption">
									<h3 class="break">
										<a href="javascript:;" onclick="detailInfo(${typeAndPro.id},${ typeAndPro.status})">${typeAndPro.name }</a>
									</h3>
									<p>
									<div style="float: left;">
										<i class="glyphicon glyphicon-screenshot" title="目标金额"></i>
										￥${typeAndPro.money }.00
									</div>
									<div style="float: right;">
										<i title="截至日期" class="glyphicon glyphicon-calendar"></i>
										${typeAndPro.enddate }
									</div>
									</p>
									<br>
									<div class="progress" style="margin-bottom: 4px;">
										<div class="progress-bar progress-bar-success"
											role="progressbar" aria-valuenow="${typeAndPro.completion}" aria-valuemin="0"
											aria-valuemax="100" style="width: ${typeAndPro.completion}%">
											<span>${typeAndPro.completion}% </span>
										</div>
									</div>
									<div>
										<span style="float: right;"><i
											class="glyphicon glyphicon-star-empty"></i>${typeAndPro.follower }</span> <span><i
											class="glyphicon glyphicon-user" title="支持人数"></i> ${typeAndPro.supporter }</span>
									</div>
								</div>
							</div>
						</div>
						</c:forEach>
					</div>

				</div>
			</div>
		</div>
		
		<div class="container">
			<div class="row clearfix">
				<div class="col-md-12 column" style="text-align: center">
					<ul class="pagination">
						<li>
							<a 
							class='btn ${map.page.pageno - 1  <= 0 ? "disabled":"" }'
							rel="nofollow" 
							href="/homepage/${map.typeid }/${map.status }/${map.sort }/${ map.page.pageno - 1  <= 0 ? map.page.pageno:map.page.pageno-1}/projects.htm">上一页
							</a>
						</li>
						<c:forEach begin="1" end="${map.page.totalno }" var="pageno">
							<li class='${pageno == map.page.pageno ? "active":"" }' >
								<a rel="nofollow" href="/homepage/${map.typeid }/${map.status }/${map.sort }/${pageno}/projects.htm">${pageno}</a>
							</li>
						</c:forEach>
						<li>
							<a 
							 class='btn ${map.page.pageno + 1  > map.page.totalno ? "disabled":"" }'
							 rel="nofollow"
							 href="/homepage/${map.typeid }/${map.status }/${map.sort }/${ map.page.pageno + 1  > map.page.totalno ? map.page.pageno:map.page.pageno+1}/projects.htm">下一页
							 </a>
						</li>
					</ul>
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
	<script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
	<script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script src="${APP_PATH }/script/back-to-top.js"></script>
	<script src="${APP_PATH }/jquery/layer/layer.js"></script>
	<script>
		$('#myTab a').click(function(e) {
			e.preventDefault()
			$(this).tab('show')
		})
		
		//图片点击查看项目详情
		function detailInfo(proId,status) {
			if (status != 0) {
				window.location.href="/homepage/"+proId+"/detailInfo.htm";
			}else{
				layer.msg("当前项目还未开始！",{time:1000,icon:5,shift:6})
			}
		}
		
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
		
		function toStart() {
			if (${sessionScope.member.authstatus} != 2) {
				layer.msg("请先实名认证！",{time:1000,icon:5,shift:6});
			}else{
				window.location.href="/atcrowdfunding/start.htm";
			}
		}
	</script>
</body>
</html>