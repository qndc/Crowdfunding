<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>项目信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet"
	href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/theme.css">
<link rel="stylesheet" href="${APP_PATH }/jquery/layui/css/layui.css">
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
  
:not
 
(
:first-child
 
)
{
margin-top
:
 
20
px
;


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

.tag {
	display: inline-block;
	background-color: #ecf5ff;
	color: #409EFF;
	border: 1px solid #d9ecff;
	padding: 2px 5px;
	margin: 5px 5px 0 0;
	cursor: pointer;
	border-radius: 3px;
}

.tag:hover {
	background-color: #409EFF;
	color: #fff;
	transition-property: all;
	transition-duration: .3s;
}

.flag {
	background-color: #409EFF;
	color: #fff;
}
.proImg{
	width: 100px;
	height: 75px;
	margin-top: 5px;
	margin-right: 5px;
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
											<div class="progress-bar progress-bar-success"
												role="progressbar" aria-valuenow="60" aria-valuemin="0"
												aria-valuemax="100" style="width: 100%; height: 50px;">
												<div style="font-size: 16px; margin-top: 15px;">1.
													项目及发起人信息</div>
											</div>
										</div>
										<div
											style="right: 0; border: 10px solid #ddd; border-left-color: #88b7d5; border-width: 25px; position: absolute; border-left-color: rgba(92, 184, 92, 1); border-top-color: rgba(92, 184, 92, 0); border-bottom-color: rgba(92, 184, 92, 0); border-right-color: rgba(92, 184, 92, 0);">
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
											<div class="progress-bar progress-bar-default"
												role="progressbar" aria-valuenow="60" aria-valuemin="0"
												aria-valuemax="100" style="width: 100%; height: 50px;">
												<div style="font-size: 16px; margin-top: 15px;">3.
													公司信息</div>
											</div>
										</div>
										<div
											style="right: 0; border: 10px solid #ddd; border-left-color: #88b7d5; border-width: 25px; position: absolute; border-left-color: rgba(221, 221, 221, 1); border-top-color: rgba(221, 221, 221, 0); border-bottom-color: rgba(221, 221, 221, 0); border-right-color: rgba(221, 221, 221, 0);">
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
									<div class="col-md-12 column">
										<blockquote
											style="border-left: 5px solid #f60; color: #f60; padding: 0 0 0 20px;">
											<b> 项目及发起人信息 </b>
										</blockquote>
									</div>
									<div class="col-md-12 column">
										<div class="page-header" style="border-bottom-style: dashed;">
											<h3>项目信息</h3>
										</div>
										<form class="form-horizontal" id="proInfo">
											<div class="form-group">
												<label for="inputEmail3" class="col-sm-2 control-label">分类信息</label>
												<div class="col-sm-10" id="types"></div>
											</div>
											<div class="form-group">
												<label for="inputEmail3" class="col-sm-2 control-label">标签</label>
												<div class="col-sm-10" id="tags"></div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">项目名称</label>
												<div class="col-sm-10">
													<input type="text" class="form-control" name="name">
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">项目简介</label>
												<div class="col-sm-10">
													<textarea class="form-control" rows="5" name="remark"></textarea>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">筹资金额（元）</label>
												<div class="col-sm-10">
													<input type="text" class="form-control" style="width: 100px;" name="money"> 
													<label class="control-label">筹资金额不能低于100元,不能高于1000000000元</label>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">筹资天数（天）</label>
												<div class="col-sm-10">
													<input type="text" class="form-control" name="day"style="width: 100px;"> 
													<label class="control-label">一般10-90天，建议30天</label>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">项目头图</label>
												<div class="col-sm-10" id="headImg">
													<button type="button" class="layui-btn" id="test1">
														<i class="layui-icon">&#xe67c;</i>上传图片
													</button>
													<label class="control-label">图片上无文字,支持jpg、jpeg、png、gif格式，大小不超过2M，建议尺寸：740*457px</label><br>
													
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-2 control-label">项目详情</label>
												<div class="col-sm-10" id="detailImg">
													<button type="button" class="layui-btn" id="test2">
														<i class="layui-icon">&#xe67c;</i>上传图片
													</button>
													<label class="control-label">支持jpg、jpeg、png、gif格式，大小不超过2M，建议尺寸：宽740px</label><br>
												</div>
											</div>
										</form>
									</div>
									
								</div>
							</div>
						</div>
						<div class="panel-footer" style="text-align: center;">
							<button type="button" class="btn  btn-warning btn-lg"
								onclick="submit()">下一步</button>
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
	<script>
		$(function () {
			//页面加载是加载分类
			$.ajax({
				url:"${APP_PATH}/atcrowdfunding/getType.do",
				type:"post",
				success:function(result){
					$("#types").empty();
					var types = "";
					$.each(result.message,function(index,item){
						types += '<label class="radio-inline"><input type="radio" onclick="getTagById('+item.id+')" name="type" class="type" value="'+item.id+'">'+item.name+'</label> ';
					})
					$("#types").append(types);
					
					//页面加载成功。默认第一个单选框被选中，并且发送请求查询默认选中的类型的标签
					$("input[type='radio']").eq(0).attr("checked",true);
					var typeid = $("input[type='radio']:checked").val();
					getTagById(typeid);
				},
				error:function(result){
					layer.msg(result.message,{time:1000,icon:5,shift:6})
				}
			})
			
			
		})
		
		layui.use('upload', function(){
		  var upload = layui.upload;
		  var layer = layui.layer;
		   
		  //上传项目头图
		  var uploadHead = upload.render({
		    elem: '#test1' //绑定元素
		    ,url: '${APP_PATH}/atcrowdfunding/upload.do' //上传接口
		    ,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
		    	 layer.load(); //上传loading
		    }
		    ,done: function(res){
		      //上传完毕回调
		      if (res.code == 200) {
		    	  var src = "<img alt='' src='"+res.data[0].src+"' class='proImg headImg' >";
			      $("#headImg").append(src);
			  }else{
				  layer.msg(res.msg,{time:1000,icon:5,shift:6}); 
			  }
		      layer.closeAll('loading'); //关闭loading
		    }
		    ,error: function(){
		      //请求异常回调
		      layer.msg("图片上传失败！",{time:1000,icon:5,shift:6})
		    }
		 
		  });
		});
		  
		  //多文件上传：项目详情图片
		  layui.use('upload', function(){
			  var upload = layui.upload;
			  var layer = layui.layer;
			  //上传项目详情
			  var uploadDetail = upload.render({
				elem: '#test2' //绑定元素
				,url: '${APP_PATH}/atcrowdfunding/upload.do' //上传接口
				,multiple:true	//多文件上传
				,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
			    	 layer.load(); //上传loading
			    }
				,allDone: function(obj){ //当文件全部被提交后，才触发
				    var total = obj.total;//得到总文件数
				    var success = obj.successful;//请求成功的文件数
				    var error = obj.aborted;//请求失败的文件数
				    var msg = success+"个文件上传成功";
				    if(error > 0){
				    	msg += (error+"个文件上传失败");
				    	layer.msg(msg,{time:1500,icon:5,shift:6})
				    }else{
				    	layer.msg(msg,{time:1500,icon:6,shift:6})
				    } 
				}
				,done: function(res){
				  //上传完毕回调
			      if (res.code == 200) {
			    	  var src = "<img alt='' src='"+res.data[0].src+"' class='proImg detailImg' >";
				      $("#detailImg").append(src);
				  }else{
					  layer.msg(res.msg,{time:1500,icon:5,shift:6}); 
				  }
			      layer.closeAll('loading'); //关闭loading
				}
				,error: function(){
				  //请求异常回调
				  
				}
			  });
			});
	
		$('#myTab a').click(function(e) {
			e.preventDefault()
			$(this).tab('show')
		})
		
		//根据类型id查询标签
		function getTagById(id) {
			$.ajax({
				url:"${APP_PATH}/atcrowdfunding/getTagById.do",
				type:"post",
				data:{
					typeid:id
				},
				success:function(result){
					$("#tags").empty();
					var tags = "";
					$.each(result.message,function(index,item){
						tags += '<span class="tag" onclick="addClass($(this))" tagId="'+item.id+'">'+item.name+'</span>';
					})
					$("#tags").append(tags);
				},
				error:function(result){
					layer.msg(result.message,{time:1000,icon:5,shift:6})
				}
			})
		}
		
		//选中标签
		function addClass(obj) {
			obj.toggleClass("flag");
		}
		
		//异步提交
		function submit() {
			//遍历选中的标签
			var tags = $('.flag');
			var idArr = "";
			for (var i = 0; i < tags.size(); i++) {
				idArr += ( "tagId=" + tags.eq(i).attr('tagid') + "&");
			}
			idArr = idArr.substring(0,idArr.length-1);
			//遍历项目头图以及项目详情图
			var imgs = "projectImg.headimg="+$(".headImg").attr("src")+"&";
			var detailImgs = $(".detailImg");
			for (var i = 0; i < detailImgs.size(); i++) {
				imgs += ( "projectImg.detailImg=" + detailImgs.eq(i).attr('src') + "&");
			}
			imgs = imgs.substring(0,imgs.length-1);	
			
			$.ajax({
				url:"${APP_PATH}/atcrowdfunding/proInfo.do",
				type:"post",
				data:$("#proInfo").serialize()+"&"+idArr+"&"+imgs,
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
	</script>
</body>
</html>