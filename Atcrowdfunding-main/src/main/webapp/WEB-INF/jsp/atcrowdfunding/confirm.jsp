<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>确认信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/theme.css">
<link rel="stylesheet" href="${APP_PATH }/jquery/layui/css/layui.css">
<link href="${APP_PATH }/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
<link rel="stylesheet" href="${APP_PATH }/css/jtoggler.styles.css">
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

#typeList  :not (:first-child ) {
	margin-top: 20px;
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
.jtoggler-wrapper{
	position: absolute;
}
.jtoggler:checked + .jtoggler-control {
    background: #f0ad4e;
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
								<li><a rel="nofollow" href="index.html"><i
										class="glyphicon glyphicon-home"></i> 众筹首页</a></li>
								<li><a rel="nofollow" href="projects.html"><i
										class="glyphicon glyphicon-th-large"></i> 项目总览</a></li>
								<li class="active"><a rel="nofollow" href="javascript:;"><i
										class="glyphicon glyphicon-edit"></i> 发起项目</a></li>
								<li><a rel="nofollow" href="minecrowdfunding.html"><i
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
						<div class="panel-heading" style="text-align: center;">
							<div class="container-fluid">
								<div class="row clearfix">
									<div class="col-md-3 column">
										<div class="progress"
											style="height: 50px; border-radius: 0; position: absolute; width: 75%; right: 49px;">
											<div class="progress-bar progress-bar-default"
												role="progressbar" aria-valuenow="60" aria-valuemin="0"
												aria-valuemax="100" style="width: 100%; height: 50px;">
												<div style="font-size: 16px; margin-top: 15px;">1.
													项目及发起人信息</div>
											</div>
										</div>
										<div
											style="right: 0; border: 10px solid #ddd; border-left-color: #88b7d5; border-width: 25px; position: absolute; border-left-color: rgba(221, 221, 221, 1); border-top-color: rgba(221, 221, 221, 0); border-bottom-color: rgba(221, 221, 221, 0); border-right-color: rgba(221, 221, 221, 0);">
										</div>
									</div>
									<div class="col-md-3 column">
										<div class="progress"
											style="height: 50px; border-radius: 0; position: absolute; width: 75%; right: 49px;">
											<div class="progress-bar progress-bar-default"
												role="progressbar" aria-valuenow="60" aria-valuemin="0"
												aria-valuemax="100" style="width: 100%; height: 50px;">
												<div style="font-size: 16px; margin-top: 15px;">2.
													回报设置</div>
											</div>
										</div>
										<div
											style="right: 0; border: 10px solid #ddd; border-left-color: #88b7d5; border-width: 25px; position: absolute; border-left-color: rgba(221, 221, 221, 1); border-top-color: rgba(221, 221, 221, 0); border-bottom-color: rgba(221, 221, 221, 0); border-right-color: rgba(221, 221, 221, 0);">
										</div>
									</div>
									<div class="col-md-3 column">
										<div class="progress"
											style="height: 50px; border-radius: 0; position: absolute; width: 75%; right: 49px;">
											<div class="progress-bar progress-bar-success"
												role="progressbar" aria-valuenow="60" aria-valuemin="0"
												aria-valuemax="100" style="width: 100%; height: 50px;">
												<div style="font-size: 16px; margin-top: 15px;">3.
													公司信息</div>
											</div>
										</div>
										<div
											style="right: 0; border: 10px solid #ddd; border-left-color: #88b7d5; border-width: 25px; position: absolute; border-left-color: rgba(92, 184, 92, 1); border-top-color: rgba(92, 184, 92, 0); border-bottom-color: rgba(92, 184, 92, 0); border-right-color: rgba(92, 184, 92, 0);">
										</div>
									</div>
									<div class="col-md-3 column">
										<div class="progress" style="height: 50px; border-radius: 0;">
											<div class="progress-bar progress-bar-default"
												role="progressbar" aria-valuenow="60" aria-valuemin="0"
												aria-valuemax="100" style="width: 100%; height: 50px;">
												<div style="font-size: 16px; margin-top: 15px;">4. 完成</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="panel-body">

							<div class="container-fluid">
								<div class="row clearfix">
									<div class="col-md-12 column" style="margin-bottom: 20px">
										<blockquote
											style="border-left: 5px solid #f60; color: #f60; padding: 0 0 0 20px;">
											<b>公司信息 </b>
										</blockquote>
									</div>
									<div class="col-md-12 column">
										<div class="row clearfix">
											<div class="col-md-6 column">
												<form role="form" id="compForm">
													<input type="hidden" id="proid" name="proid" value="${requestScope.projectId }">
													<div class="form-group">
														<label for="">企业名称：</label>
														<input type="text" class="form-control" id="compna"  name="compna" />
													</div>
													<div class="form-group">
														<label for="exampleInputPassword1">法人身份证号：</label>
														<input type="text" class="form-control" id="compno"  name="compno"/>
													</div>
													<div class="form-group">
														<label for="exampleInputPassword1">社会信用代码：</label>
														<input type="text" class="form-control" id="compcd"  name="compcd"/>
													</div>
													<div class="form-group">
														<label for="exampleInputPassword1">商家账户（支付宝）：</label>
														<input type="text" class="form-control" id="compacct"  name="compacct"/>
											 		</div>
													<div class="form-group">
														<label for="exampleInputPassword1">客服电话：</label>
														<input type="text" class="form-control" id="servicetel"  name="servicetel"/>
													</div>
													<div class="form-group" style="position: relative;">
														<label for="" style="display: inline-block;">服务时间：</label><br/>
									                    <div class="input-group date form_time col-md-3" data-date="" data-date-format="hh:ii" data-link-field="start_time" data-link-format="hh:ii" style="float: left;">
									                        <input class="form-control" size="16" type="text" value="" readonly>
									                        <span class="input-group-addon"><span class="fa fa-calendar"></span></span>
									                    </div>
									                    <span style="display: inline-block;height: 100%;position: absolute;padding: 8px 5px">- - -</span>
									                    <div class="input-group date form_time col-md-3" data-date="" data-date-format="hh:ii" data-link-field="end_time" data-link-format="hh:ii" style="float: right;margin-right: 230px">
									                        <input class="form-control" size="16" type="text" value="" readonly>
									                        <span class="input-group-addon"><span class="fa fa-calendar"></span></span>
									                    </div>
									                    <input type="hidden" id="start_time" name="start_time"/><br />
									                  	
									                    <input type="hidden" id="end_time" name="end_time"/><br />
									                </div>
													<div class="form-group" style="margin-top: 20px">
														<label style="margin-top: 5px;">是否使用已认证的公司信息：</label>
														<input type="checkbox" class="jtoggler" id="userexist" name="userexist" value="0">
													</div>
													<div class="form-group" style="height: 40px">
														<button type="button" id="upTemp" class="layui-btn" style="background-color: #f0ad4e;">
															<i class="layui-icon">&#xe67c;</i>委托书扫描件
														</button>	
														<input type="hidden" id="template" name="template">
													</div>
												</form>
											</div>
											<div class="col-md-6 column" style="margin-top: 22px;">
												<div class="panel panel-default">
													<div class="panel-body" style="padding: 40px;">
														<i class="glyphicon glyphicon-user"></i> 当前账户名：${member.loginacct }<br>
														<br>
														<span style="margin-left: 20px;">您正在使用该账号发起众筹项目</span><br>
														<span style="margin-left: 20px;">如果您是个人经营用户或者不使用当前认证的企业进行众筹，请先填写委托的企业信息，并下载<a href="${APP_PATH }/atcrowdfunding/template.doc/download.do" style="cursor: pointer;color: blue;">众筹委托书</a>，上传委托书扫描件！</span>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="panel-footer" style="text-align: center;">
							<button type="button" class="btn  btn-default btn-lg"
								onclick="window.location.href='/atcrowdfunding/return.htm'">上一步</button>
							<button type="button" class="btn  btn-warning btn-lg"
								onclick="sub()">提交</button>
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
							<a rel="nofollow" href="http://www.layoutit.cn">关于我们</a> | <a
								rel="nofollow" href="http://www.layoutit.cn">服务条款</a> | <a
								rel="nofollow" href="http://www.layoutit.cn">免责声明</a> | <a
								rel="nofollow" href="http://www.layoutit.cn">网站地图</a> | <a
								rel="nofollow" href="http://www.layoutit.cn">联系我们</a>
						</div>
						<div class="copyRight">Copyright ?2017-2017layoutit.cn 版权所有
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
	<script src="${APP_PATH }/jquery/layui/layui.js"></script>
	<script src="${APP_PATH }/script/jtoggler.js"></script> 
	<script type="text/javascript" src="${APP_PATH }/bootstrap/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
    <script type="text/javascript" src="${APP_PATH }/bootstrap/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	
	<script>
		//初始化开关插件
		$(function(){
		  $('.jtoggler').jtoggler(); 
		}); 
		
		
		$('#myTab a').click(function(e) {
			e.preventDefault()
			$(this).tab('show')
		})
		
		$('.form_time').datetimepicker({
            language: 'zh-CN',
            weekStart: 1,
            todayBtn: 1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 1,
            minView: 0,
            maxView: 1,
            forceParse: 0
        });
		
		//查看开关状态的改变
		$(document).on('jt:toggled', function(event, target) {
			  //console.log(event, target);
			  console.info($(target).prop('checked'))
			  if ($(target).prop('checked')) {
				 $("#upTemp").hide();
				 $(target).val("1");//使用已认证的信息
			  }else{
				 $("#upTemp").show();
				 $(target).val("0") //使用委托书
			  }
		});
		
		//委托书上传
		layui.use('upload', function(){
		  var upload = layui.upload;
		  var layer = layui.layer;
		   
		  var uploadHead = upload.render({
		    elem: '#upTemp' //绑定元素
		    ,url: '${APP_PATH}/atcrowdfunding/upload.do' //上传接口
		    ,accept: 'file'
		    ,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
		    	 layer.load(); //上传loading
		    }
		    ,done: function(res){
		      //上传完毕回调
		      if (res.code == 200) {
			      $("#template").val(res.data[0].src);
			      layer.msg("上传成功",{time:1000,icon:6}); 
			  }else{
				  layer.msg(res.msg,{time:1000,icon:5,shift:6}); 
			  }
		      layer.closeAll('loading'); //关闭loading
		    }
		    ,error: function(){
		      //请求异常回调
		      layer.msg("委托书上传失败！",{time:1000,icon:5,shift:6})
		    }
		 
		  });
		});
		
		//提交
		function sub() {
			if ($("#compna").val().trim() == "" || $("#compno").val().trim() == "" || $("#compcd").val().trim() == "" || $("#compacct").val().trim() == "" || $("#start_time").val().trim() == "" || $("#end_time").val().trim() == "" ) {
				layer.msg("请将信息填写完善",{time:1000,icon:5,shift:6});
			}else{
				if ($("#userexist").val() == "0" && $("#template").val().trim() == "") {
					layer.msg("请将信息填写完善",{time:1000,icon:5,shift:6});
				}else{
					$.ajax({
						url:"${APP_PATH}/atcrowdfunding/addComp.do",
						type:"post",
						data:$("#compForm").serialize(),
						success:function(result){
							if (result.status == 200) {
								window.location.href="/atcrowdfunding/apply.do";
							}else {
								layer.msg(result.message,{time:1000,icon:5,shift:6});
							}
						},
						error:function(result){
							layer.msg(result.message,{time:1000,icon:5,shift:6})
						}
					})
				}
			}
		}
		
		
	</script>
</body>
</html>