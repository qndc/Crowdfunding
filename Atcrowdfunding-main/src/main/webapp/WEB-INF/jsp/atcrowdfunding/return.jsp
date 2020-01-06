<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>回报信息</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet"
	href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
<link rel="stylesheet" href="${APP_PATH }/css/theme.css">
<link rel="stylesheet" href="${APP_PATH }/jquery/layui/css/layui.css">
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

.proImg {
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
									<li><a href="member.html"><i
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
											<div class="progress-bar progress-bar-success"
												role="progressbar" aria-valuenow="60" aria-valuemin="0"
												aria-valuemax="100" style="width: 100%; height: 50px;">
												<div style="font-size: 16px; margin-top: 15px;">2.
													回报设置</div>
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
												<div style="font-size: 16px; margin-top: 15px;">3.
													确认信息</div>
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
											<b> 回报设置 </b>
										</blockquote>
									</div>
									<div class="col-md-12 column">
										<table class="table table-bordered"
											style="text-align: center;">
											<thead>
												<tr style="background-color: #ddd;">
													<td>序号</td>
													<td>支付金额</td>
													<td>名额</td>
													<td>单笔限购</td>
													<td>回报内容</td>
													<td>回报时间</td>
													<td>运费</td>
													<td>发票</td>
													<td>操作</td>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${requestScope.returns }" var="ret" varStatus="hx">
													<tr>
														<td scope="row">${hx.count }</td>
														<td>￥${ret.supportmoney }.00</td>
														<c:if test="${ret.count == 0 }">
															<td>不限回报数量</td>
														</c:if>
														<c:if test="${ret.count != 0 }">
															<td>${ret.count }</td>
														</c:if>
														<c:if test="${ret.signalpurchase == 0 }">
															<td>单笔不限购</td>
														</c:if>
														<c:if test="${ret.signalpurchase != 0 }">
															<td>${ret.purchase }</td>
														</c:if>
														<td><a href="javascript:;" style="text-decoration: none;color:#1e9fff" onclick="show('${ret.img}')">${ret.content }</a></td>
														<td>项目结束后的${ret.rtndate }天</td>
														<c:if test="${ret.freight == 0 }">
															<td>免运费</td>
														</c:if>
														<c:if test="${ret.freight != 0 }">
															<td>${ret.freight }</td>
														</c:if>
														<c:if test="${ret.invoice == 0 }">
															<td>不可开发票</td>
														</c:if>
														<c:if test="${ret.invoice != 0 }">
															<td>可开发票</td>
														</c:if>
														<td>
															<button type="button" class="btn btn-primary btn-xs" onclick="getRetById(${ret.id})">
																<i class=" glyphicon glyphicon-pencil"></i>
															</button>
															<button type="button" class="btn btn-danger btn-xs delBtn" del-id="${ret.id }">
																<i class=" glyphicon glyphicon-remove"></i>
															</button>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
										<button type="button" class="btn btn-primary btn-lg" id="Jumpflag">
											<i class="glyphicon glyphicon-plus"></i> 添加回报
										</button>
										<div
											style="border: 10px solid #f60; border-bottom-color: #eceeef; border-width: 10px; width: 20px; margin-left: 20px; border-left-color: rgba(221, 221, 221, 0); border-top-color: rgba(221, 221, 221, 0); border-right-color: rgba(221, 221, 221, 0);"></div>
										<div class="panel panel-default"
											style="border-style: dashed; background-color: #eceeef">
											<div class="panel-body">

												<div class="col-md-12 column">
													<form class="form-horizontal" id="retForm">
														<div class="form-group">
															<label for="inputEmail3" class="col-sm-2 control-label">回报类型</label>
															<div class="col-sm-10">
																<label class="radio-inline"> 
																	<input type="radio" name="type" value="0" checked="checked"> 实物回报
																</label> 
																<label class="radio-inline"> 
																	<input type="radio" name="type" value="1"> 虚拟物品回报
																</label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">支持金额（元）</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" style="width: 100px;" name="supportmoney">
																<input type="hidden" value="${requestScope.projectId }" name="projectid">
																<input type="hidden" value="" name="id">
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">回报内容</label>
															<div class="col-sm-10">
																<input class="form-control" name="content" placeholder="不超过14字"/>
															 </div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">回报描述</label>
															<div class="col-sm-10">
																<textarea class="form-control" name="returndesc" rows="5" placeholder="简单描述回报内容，不超过200字"></textarea>
															 </div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">回报图片</label>
															<div class="col-sm-10">
																<button type="button" id="returnImg" class="layui-btn" style="background-color: #337ab7">
																	<i class="layui-icon">&#xe67c;</i>上传图片
																</button>	
																<input type="hidden" id="retImg" name="img">
																<label class="control-label">支持jpg、jpeg、png、gif格式，大小不超过2M，建议尺寸：760*510px选择文件</label>
																<br>
																<img alt="" id="showImg" style="width: 100px;height: 75px;float: left;margin: 10px 0 0 0;display: none;">
															</div>
														</div>
														
														<div class="form-group">
															<label class="col-sm-2 control-label">回报数量（名）</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" name="count"
																	style="width: 100px; display: inline"> <label
																	class="control-label">“0”为不限回报数量</label>
															</div>
														</div>
														<div class="form-group">
															<label for="inputEmail3" class="col-sm-2 control-label">单笔限购</label>
															<div class="col-sm-10">
																<label class="radio-inline"> 
																	<input type="radio" name="signalpurchase" onclick="clearNum()" value="0" checked="checked"> 不限购
																</label> 
																<label class="radio-inline"> 
																	<input type="radio" name="signalpurchase" value="1"> 限购
																</label> 
																<input type="text" class="form-control" name="purchase" style="width: 100px; display: inline"> 
																<label class="radio-inline">默认数量为1，且不超过上方已设置的回报数量</label>
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">运费(元)</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" name="freight"
																	style="width: 100px; display: inline" value="0">
																<label class="control-label">“0”为包邮</label>
															</div>
														</div>
														<div class="form-group">
															<label for="inputEmail3" class="col-sm-2 control-label">发票</label>
															<div class="col-sm-10">
																<label class="radio-inline"> 
																	<input type="radio" name="invoice"  value="0" checked="checked"> 不可开发票
																</label> 
																<label class="radio-inline"> 
																	<input type="radio" name="invoice" value="1">可开发票（包括个人发票和自定义抬头发票）
																</label>
															</div>
														</div>
														<div class="form-group">
															<label for="inputEmail3" class="col-sm-2 control-label">回报时间</label>
															<div class="col-sm-10">
																<label class="radio-inline"> 项目结束后 </label> <input
																	type="text" class="form-control" name="rtndate"
																	style="width: 100px; display: inline"> <label
																	class="radio-inline">天，向支持者发送回报</label>
															</div>
														</div>
														<div class="form-group">
															<label for="inputEmail3" class="col-sm-2 control-label"></label>
															<div class="col-sm-10">
																<button type="button" id="subBtn" class="btn btn-primary" onclick="sub()">确定</button>
																<button type="button" class="btn btn-default">取消</button>
															</div>
														</div>

													</form>
												</div>


											</div>
										</div>
									</div>

									<div class="container">
										<div class="row clearfix">
											<div class="col-md-12 column">
												<blockquote>
													<p>
														<i class="glyphicon glyphicon-info-sign"
															style="color: #2a6496;"></i> 提示
													</p>
													<small>3个以上的回报：多些选择能提高项目的支持率。几十、几百、上千元的支持：3个不同档次的回报，能让你的项目更快成功。回报最好是项目的衍生品：<br>与项目内容有关的回报更能吸引到大家的支持。
													</small>
												</blockquote>
											</div>
										</div>
									</div>


								</div>
							</div>
						</div>
						<div class="panel-footer" style="text-align: center;">
							<button type="button" class="btn  btn-default btn-lg"
								onclick="window.location.href='start-step-1.html'">上一步</button>
							<button type="button" class="btn  btn-warning btn-lg"
								onclick="window.location.href='start-step-3.html'">下一步</button>
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
		$('#myTab a').click(function(e) {
			e.preventDefault()
			$(this).tab('show')
		})
		
		//删除回报
		var loadingIndex = -1;
		$(".delBtn").click(function () {
			var obj = $(this);
			
			layer.confirm("确定删除该回报吗？",{icon:3,title:'提示'},function(cindex){
				
				$.ajax({
					url:"${APP_PATH}/atcrowdfunding/delReturn.do",
					type:"post",
					data:{
						"returnId":obj.attr("del-id")
					},
					success:function(result){
						
						if (result.status == 200) {
							
							obj.parent().parent().remove();
							layer.msg(result.message,{time:1000,icon:6});
							
						}else {
							layer.msg(result.message,{time:1000,icon:5,shift:6});
						}
					},
					error:function(result){
						layer.msg(result.message,{time:1000,icon:5,shift:6})
					}
				})
				
				layer.close(cindex);
			},function(cindex){
				layer.close(cindex); 
			})
			
		})
		
		
		
		//根据id获取回报信息
		function getRetById(id) {
			
			$.ajax({
				url:"${APP_PATH}/atcrowdfunding/getReturnById.do",
				type:"post",
				data:{
					"returnId":id
				},
				success:function(result){
					//锚点跳转
					$('body,html').animate({scrollTop: $('#Jumpflag').offset().top}, 500);
					//控件赋值
					//$("input[name='type']").removeAttr('checked');
					$("input[name='type']").eq(result.message.type).prop("checked",'checked');
					$("input[name='supportmoney']").val(result.message.supportmoney);
					$("input[name='content']").val(result.message.content);
					$("textarea[name='returndesc']").val(result.message.returndesc);
					$("#retImg").val(result.message.img);
					$("#showImg").prop("src",result.message.img).show();
					$("input[name='count']").val(result.message.count);
					$("input[name='signalpurchase']").eq(result.message.signalpurchase).prop("checked",'checked');
					$("input[name='purchase']").val(result.message.purchase);
					$("input[name='invoice']").eq(result.message.invoice).prop("checked",'checked');
					$("input[name='freight']").val(result.message.freight);
					$("input[name='rtndate']").val(result.message.rtndate);
					$("input[name='id']").val(result.message.id);
					//修改表单提交方法
					$("#subBtn").attr("onclick","updateReturn()")
					},
				error:function(result){
					layer.msg(result.message,{time:1000,icon:5,shift:6})
				}
			})
		}
		
		//更新回报
		function updateReturn(retId) {
			$.ajax({
				url:"${APP_PATH}/atcrowdfunding/updateReturn.do",
				type:"post",
				data:$("#retForm").serialize(),
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
		
		//回报图片上传
		layui.use('upload', function(){
		  var upload = layui.upload;
		  var layer = layui.layer;
		   
		  var uploadHead = upload.render({
		    elem: '#returnImg' //绑定元素
		    ,url: '${APP_PATH}/atcrowdfunding/upload.do' //上传接口
		    ,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
		    	 layer.load(); //上传loading
		    }
		    ,done: function(res){
		      //上传完毕回调
		      if (res.code == 200) {
			      $("#retImg").val(res.data[0].src);
			      $("#showImg").prop("src",res.data[0].src).show();
			      layer.msg("上传成功",{time:1000,icon:6}); 
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
		
		//提交回报
		function sub() {
			
			if ($("input[name='supportmoney']").val().trim() != "" && $("input[name='content']").val().trim() != "" && $("textarea[name='returndesc']").val().trim() != "" && $("input[name='img']").val().trim() != "" && $("input[name='count']").val().trim() != "" && $("input[name='freight']").val().trim() != ""  && $("input[name='rtndate']").val().trim() != "") {
				if ($("input[name='signalpurchase']:checked").val() == 1 && $("input[name='purchase']").val().trim() == "") {
					
					layer.msg("请将信息填写完善！",{time:1000,icon:5,shift:6});
				
				}else{
					
					$.ajax({
						url:"${APP_PATH}/atcrowdfunding/addReturn.do",
						type:"post",
						data:$("#retForm").serialize(),
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
			}else{
				layer.msg("请将信息填写完善！",{time:1000,icon:5,shift:6});
			}
		}
		
		//清空单人限购数量
		function clearNum() {
			$("input[name='purchase']").val("");
		}
		
		//图片预览
		function show(url) {
			layer.open({
				  type: 1,
				  title: false,
				  closeBtn: 0,
				  area: ['500px', '350px'],
				  skin: 'layui-layer-nobg', //没有背景色
				  shadeClose: true,
				  content: "<img src = '"+url+"' style='width:100%;height:100%;'>"
				});
		}
	
	</script>
</body>
</html>