<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单确认</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
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
									张三<span class="caret"></span></a>
								<ul class="dropdown-menu" role="menu">
									<li><a href="member.html"><i
											class="glyphicon glyphicon-scale"></i> 会员中心</a></li>
									<li><a href="#"><i class="glyphicon glyphicon-comment"></i>
											消息</a></li>
									<li class="divider"></li>
									<li><a href="index.html"><i
											class="glyphicon glyphicon-off"></i> 退出系统</a></li>
								</ul></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
	</div>

	<div class="container theme-showcase" role="main" style="margin-top: 70px">

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
													确认回报内容</div>
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
													确认订单</div>
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
												<div style="font-size: 16px; margin-top: 15px;">3. 付款</div>
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
										<div class="alert alert-warning alert-dismissable"
											style="color: red;">
											<button type="button" class="close" data-dismiss="alert"
												aria-hidden="true">×</button>
											<i class="glyphicon glyphicon-info-sign"></i> <strong>请在下单后15分钟内付款，否则您的订单会被自动关闭。</strong>
										</div>
									</div>
								</div>
							</div>

							<div id="address" class="container-fluid">
								<div class="row clearfix">
									<div class="col-md-12 column">
										<blockquote
											style="border-left: 5px solid #f60; color: #f60; padding: 0 0 0 20px;">
											<b> 收货地址 </b>
										</blockquote>
									</div>
									<div class="col-md-12 column radios" style="padding: 0 120px;">
										<c:forEach var="addr" items="${requestScope.addresses }">
											<div class="radio">
												<label>
													<input type="radio" name="address" value="${addr.id }"> 
													${addr.name } ${addr.phone } ${addr.address }
												</label>
											</div>
										</c:forEach>
										
										<div class="radio">
											<label> <input type="radio" onclick="showForm()"
												id="optionsRadios2" value="option2"> 新增地址
											</label>
										</div>
										<div id="d1" style="border: 10px solid #f60; border-bottom-color: #eceeef; border-width: 10px; width: 20px; margin-left: 20px; margin-top: -20px; border-left-color: rgba(221, 221, 221, 0); border-top-color: rgba(221, 221, 221, 0); border-right-color: rgba(221, 221, 221, 0);display: none"></div>
										<div id="d2" class="panel panel-default" style="border-style: dashed; background-color: #eceeef;display: none">
											<div class="panel-body">
												<div class="col-md-12 column">
													<form class="form-horizontal" id = "addrForm">
														<div class="form-group">
															<label class="col-sm-2 control-label">收货人（*）</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" name = "name"
																	style="width: 200px;" placeholder="姓名：xxxx">
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">手机（*）</label>
															<div class="col-sm-10">
																<input class="form-control" type="text" name = "phone"
																	style="width: 200px;" placeholder="请填写11位手机号码"></input>
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">地址（*）</label>
															<div class="col-sm-10">
																<div data-toggle="distpicker">
															        <div class="form-group" style="width: 100px;display: inline-block;margin:0 5px 10px 0px">
															          <label class="sr-only" for="province">Province</label>
															          <select class="form-control" id="province" name="province" ></select>
															        </div>
															        <div class="form-group" style="width: 100px;display: inline-block;margin:0 5px 0 0px">
															          <label class="sr-only" for="city">City</label>
															          <select class="form-control" id="city" name="city"></select>
															        </div>
															        <div class="form-group" style="width: 100px;display: inline-block;margin:0 5px 0 0">
															          <label class="sr-only" for="district">District</label>
															          <select class="form-control" id="district" name="district"></select>
															        </div>
															      </div>
															      <input class="form-control" id="detail" type="text" name="detail"
																	style="width: 400px;display: inline-block;" placeholder="请填写详细地址"></input>
																
															</div>
														</div>
														<div class="form-group">
															<label for="inputEmail3" class="col-sm-2 control-label"></label>
															<div class="col-sm-10">
																<button type="button" onclick="addAttr()" class="btn btn-primary">确认配送信息</button>
															</div>
														</div>
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<c:if test="${ret.invoice == 1 }">
								<div class="container-fluid">
								<div class="row clearfix">
									<div class="col-md-12 column">
										<blockquote
											style="border-left: 5px solid #f60; color: #f60; padding: 0 0 0 20px;">
											<b> 发票信息 </b>
										</blockquote>
									</div>
									<div class="col-md-12 column" style="padding: 0 120px;">
										<div class="radio">
											<label> <input type="radio" name="optionsRadios1"
												id="optionsRadios1" value="option1" checked> 无需发票
											</label>
										</div>
										<div class="radio">
											<label><input type="radio" name="optionsRadios1" id= "comp"
												id="optionsRadios2" value="option2"> 新增发票
											</label>
										</div>
										<div
											style="border: 10px solid #f60; border-bottom-color: #eceeef; border-width: 10px; width: 20px; margin-left: 20px; margin-top: -20px; border-left-color: rgba(221, 221, 221, 0); border-top-color: rgba(221, 221, 221, 0); border-right-color: rgba(221, 221, 221, 0);display: none"></div>
										<div class="panel panel-default"
											style="border-style: dashed; background-color: #eceeef;display: none">
											<div class="panel-body">
												<div class="col-md-12 column">
													<form class="form-horizontal" id="invoiceForm">
														<div class="form-group">
															<label class="col-sm-2 control-label">发票抬头（*）</label>
															<div class="col-sm-10">
																<input type="text" name="invoice" class="form-control" placeholder="公司抬头"
																	style="width: 300px;">
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">税号（*）</label>
															<div class="col-sm-10">
																<input type="text" name="tax" class="form-control" placeholder="税号"
																	style="width: 300px;" >
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">地址、电话</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" name="addrtel" placeholder="地址、电话"
																	style="width: 300px;" >
															</div>
														</div>
														<div class="form-group">
															<label class="col-sm-2 control-label">开户行及账号</label>
															<div class="col-sm-10">
																<input type="text" class="form-control" name="banknum" placeholder="开户行及账号"
																	style="width: 300px;" >
															</div>
														</div>
														<div class="form-group">
															<label for="inputEmail3" class="col-sm-2 control-label"></label>
															<div class="col-sm-10">
																<button type="button" onclick="addInvoice()" class="btn btn-primary">确认发票信息</button>
															</div>
														</div>
													</form>
													
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							</c:if>
							<div class="container-fluid">
								<div class="row clearfix">
									<div class="col-md-12 column">
										<blockquote
											style="border-left: 5px solid #f60; color: #f60; padding: 0 0 0 20px;">
											<b> 项目信息 <a style="font-size: 12px;"
												href="pay-step-1.html">修改数量</a>
											</b>
										</blockquote>
									</div>
									<div class="col-md-12 column">
										<table class="table table-bordered"
											style="text-align: center;">
											<thead>
												<tr style="background-color: #ddd;">
													<td>项目名称</td>
													<td>发起人</td>
													<td width="300">回报内容</td>
													<td width="80">回报数量</td>
													<td>支持单价</td>
													<td>配送费用</td>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td>${project.name }</td>
													<td>${comp.compna }</td>
													<td>${ret.content}</td>
													<td>${requestScope.num}</td>
													<td style="color: #F60">￥ ${ret.supportmoney }.00</td>
													<c:if test="${ret.freight == 0 }">
														<td>免运费</td>
													</c:if>
													<c:if test="${ret.freight != 0 }">
														<td>${ret.freight }元</td>
													</c:if>
												</tr>
											</tbody>
										</table>
									</div>
									<div class="col-md-12 column">
										<div class="form-group">
											<label class="col-sm-2 control-label">备注</label>
											<div class="col-sm-10">
												<textarea class="form-control" rows="1"
													placeholder="填写关于回报或发起人希望您备注的信息"></textarea>
											</div>
										</div>
									</div>
									<div class="col-md-12 column">
										<blockquote
											style="border-left: 5px solid #f60; color: #f60; padding: 0 0 0 20px;">
											<b> 账单 </b>
										</blockquote>
									</div>
									<div class="col-md-12 column">
										<div class="alert alert-warning alert-dismissable"
											style="text-align: right; border: 2px solid #ffc287;">
											<ul style="list-style: none;">
												<li style="margin-top: 10px;">支持金额：<span
													style="color: red;">￥${ret.supportmoney * num }.00</span></li>
												<li style="margin-top: 10px;">配送费用：<span
													style="color: red;">￥${ret.freight }.00</span></li>
												<li style="margin-top: 10px;">优惠金额：<span
													style="color: red;">-￥0.00</span></li>
												<li style="margin-top: 10px; margin-bottom: 10px;"><h2>
														支付总金额：<span style="color: red;">￥${ret.supportmoney * num - ret.freight }.00</span>
													</h2></li>
												<li
													style="margin-top: 10px; padding: 5px; border: 1px solid #F00; display: initial; background: #FFF;">
													<i class="glyphicon glyphicon-info-sign"></i> <strong>您需要先
														<a href="#address">设置配送信息</a> ,再提交订单
												</strong>
												</li>
												<li style="margin-top: 10px;">
													请在下单后15分钟内付款，否则您的订单会被自动关闭。</li>
												<li style="margin-top: 10px;">
													<button disabled="disabled" type="button" id="toPay"
														class="btn btn-warning btn-lg">
														<i class="glyphicon glyphicon-credit-card"></i> 立即付款
													</button>
												</li>
												<li style="margin-top: 10px;">
													<div class="checkbox">
														<label> <input type="checkbox" id="rule" checked >
															我已了解风险和规则
														</label>
													</div>
												</li>
											</ul>
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
													<br> <span style="font-size: 12px;">1.众筹并非商品交易，存在一定风险。支持者根据自己的判断选择、支持众筹项目，与发起人共同实现梦想并获得发起人承诺的回报。<br>
														2.众筹平台仅提供平台网络空间及技术支持等中介服务，众筹仅存在于发起人和支持者之间，使用众筹平台产生的法律后果由发起人与支持者自行承担。<br>
														3.本项目必须在2017-06-04之前达到 ￥1000000.00
														的目标才算成功，否则已经支持的订单将取消。订单取消或募集失败的，您支持的金额将原支付路径退回。<br>
														4.请在支持项目后15分钟内付款，否则您的支持请求会被自动关闭。<br>
														5.众筹成功后由发起人统一进行发货，售后服务由发起人统一提供；如果发生发起人无法发放回报、延迟发放回报、不提供回报后续服务等情况，您需要直接和发起人协商解决。<br>
														6.如您不同意上述风险提示内容，您有权选择不支持；一旦选择支持，视为您已确认并同意以上提示内容。
													</span>
												</blockquote>
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
	<script src="${APP_PATH }/js/distpicker.data.js"></script>
	<script src="${APP_PATH }/js/distpicker.js"></script>
	<script src="${APP_PATH }/js/main.js"></script>
	<script src="${APP_PATH }/jquery/layer/layer.js"></script>
	<script src="${APP_PATH }/jquery/layui/layui.js"></script>
	<script src="${APP_PATH }/jquery/jquery-form.min.js"></script>
	<script>
		
		$('#myTab a').click(function(e) {
			e.preventDefault()
			$(this).tab('show')
		})
		//异步添加地址
		function addAttr() {
			$.ajax({
				url:"${APP_PATH}/homepage/addAddr.do",
				type:"post",
				data:$("#addrForm").serialize(),
				success:function(result){
					if (result.status == 200) {
						layer.msg("添加成功",{time:1000,icon:6})
						//将添加的信息追加到页面进行显示
						var name = $("input[name = 'name']").val();
						var phone = $("input[name = 'phone']").val();
						var province = $("#province").val();
						var city = $("#city").val();
						var district = $("#district").val();
						var detail = $("#detail").val();
						var radio = $(".radios .radio").eq($(".radios .radio").size()-2);
						var content = "<div class='radio'>"
						+"<label>"
						+"<input type='radio' name='optionsRadios' value='"+result.message+"'>"
						+ name +" " + phone + " " + province + " " + city + " " + district + " " + detail 
						+"</label>"
						+"</div>";
						radio.after(content);
						
					}else{
						layer.msg(result.message,{time:1000,icon:5,shift:6})
					}
				},
				error:function(result){
					layer.msg(result.message,{time:1000,icon:5,shift:6})
				}
			})
		}
		
		//切换地址添加面板
		function showForm() {
			$("#d1").toggle(500);
			$("#d2").toggle(500);
		}
		
		$("#comp").click(function () {
			$(this).parent().parent().next().toggle("slow").next().toggle("slow");
		})
		
		
		//添加发票
		function addInvoice(){
			if ($("#cIn").val().trim() != "" && $("#tax").val().trim() != "") {
				$.ajax({
					url:"${APP_PATH}/homepage/addInvoice.do",
					type:"post",
					data:$("#compIn").serialize(),
					success:function(result){
						if (result.status == 200) {
							console.log(result.message);
							layer.msg("添加成功",{time:1000,icon:6})
							//将添加的信息追加到页面进行显示
							/* var name = $("input[name = 'name']").val();
							var phone = $("input[name = 'phone']").val();
							var province = $("#province").val();
							var city = $("#city").val();
							var district = $("#district").val();
							var detail = $("#detail").val();
							var radio = $(".radios .radio").eq($(".radios .radio").size()-2);
							var content = "<div class='radio'>"
							+"<label>"
							+"<input type='radio' name='optionsRadios' value='"+result.message+"'>"
							+ name +" " + phone + " " + province + " " + city + " " + district + " " + detail 
							+"</label>"
							+"</div>";
							radio.after(content); */
							
						}else{
							layer.msg(result.message,{time:1000,icon:5,shift:6})
						}
					},
					error:function(result){
						layer.msg(result.message,{time:1000,icon:5,shift:6})
					}
				})
			}else{
				layer.msg("发票抬头税号不能为空",{time:1000,icon:5,shift:6})
			}
		}			
	
		//选中地址
		$("input[name='address']").click(function () {
			if ($("#rule").is(":checked")) {
				$("#toPay").removeAttr("disabled");
			}
		})
		
		//去付款
		$("#toPay").click(function () {
			if ($("#rule").is(":checked")) {
				//地址编号
				var addrid = $("input[name='address']:checked").val();
				//发票编号:先默认为0，不开发票
				//订单编号
				var orderNum = GetDateNow();
				window.open('${APP_PATH }/homepage/${ret.projectid}/${ret.id}/0/${requestScope.num}/'+addrid+'/'+orderNum+'/toPay.do');
			}else{
				layer.msg("请阅读风险提示，并进行勾选",{time:1000,icon:5,shift:6})
			}
		})
		
		//生成订单号
		function GetDateNow() {
			var vNow = new Date();
			var sNow = "";
			sNow += String(vNow.getFullYear());
			sNow += String(vNow.getMonth() + 1);
			sNow += String(vNow.getDate());
			sNow += String(vNow.getHours());
			sNow += String(vNow.getMinutes());
			sNow += String(vNow.getSeconds());
			sNow += String(vNow.getMilliseconds());
			//document.getElementById("WIDout_trade_no").value =  sNow;
			return sNow;
		}
		
	</script>
</body>
</html>