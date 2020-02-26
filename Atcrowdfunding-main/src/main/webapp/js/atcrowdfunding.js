//我支持的
$(function() {
	OrderInteraction(0);
})

//我关注的
function myFollow() {
	$.ajax({
		url:"/record/myfollow.do",
		type:"post",
		success:function(result){
			if (result.status == 200) {
				//拼接列表
				$("#follow").empty();
				if (result.message != null) {
					$.each(result.message,function(index,item){
					var content = '<tr>'
					+'<td style="vertical-align: middle;">'
					+'<div class="thumbnail">'
					+'<div class="caption">'
					+'<h4>'+item.name+'</h4>'
					+'<p><i class="glyphicon glyphicon-jpy"></i>已筹集 '+item.supportmoney+'元</p>'
					+'<div style="float: left;"><i class="glyphicon glyphicon-screenshot" title="目标金额"></i>已完成 '+item.completion+'%</div>'
					+'<div style="float: right;"><i class="glyphicon glyphicon-calendar"></i> '+item.enddate+'</div>'
					+'<br>'
					+'<div class="progress" style="margin-bottom: 4px;">'
					+'<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="'+item.completion+'" aria-valuemin="0" aria-valuemax="100" style="width: '+item.completion+'%">'
					+'<span>众筹中</span></div></div></div></div></td>'
					+'<td style="vertical-align: middle;">'+item.supporter+'</td>'
					+'<td style="vertical-align: middle;">'+item.follower+'</td>'
					+'<td style="vertical-align: middle;">'
					+'<div class="btn-group-vertical" role="group" aria-label="Vertical button group">'
					+'<button type="button" class="btn btn-default" onclick="RemoveConcerns('+item.id+')">取消关注</button>'
					+'</div></td></tr>';	
					$("#follow").append(content);
				})
				}

			}else {
				layer.msg(result.message,{time:1000,icon:5,shift:6});
			}
		},
		error:function(result){
			layer.msg(result.message,{time:1000,icon:5,shift:6})
		}
	})
}

//我发起的/状态分类
$("#crowfundStatus li").click(function () {
	var status = $("#crowfundStatus li").index($(this));
	Interaction(status);
})

//我发起的后台交互ajax
function Interaction(status) {
	var tabpanel = $("#crowfundStatus").next().children().eq(status);
	$.ajax({
		url:"/record/mylaunch.do",
		type:"post",
		data:{
			"status":status
		},
		success:function(result){
			if (result.status == 200) {
				console.log(result.message);
				tabpanel.empty();
				var body = "";
				$.each(result.message,function(index,item){
					//拼接状态\操作按钮
					var status = '';
					var btn = '';
					if (item.status == 0) {
						status = "未开始";
						btn = '<button type="button" class="btn btn-default" onclick = "delProject('+item.id+')">删除项目</button>';
					}else if (item.status == 1) {
						status = "众筹中";
					}else if (item.status == 2) {
						status = "众筹成功";
						btn = '<button type="button" class="btn btn-default" onclick = "delProject('+item.id+')">删除项目</button>';
					}else if (item.status == 3) {
						status = "众筹失败";
						btn = '<button type="button" class="btn btn-default" onclick = "delProject('+item.id+')">删除项目</button>';
					}
					
					body += '<tr>'
					+'<td style="vertical-align: middle;">'
					+'<div class="thumbnail">'
					+'<div class="caption">'
					+'<p>'+item.name+'</p>'
					+'<p><div style="float: left;"><i class="glyphicon glyphicon-screenshot" title="目标金额"></i>已完成 '+item.completion+'%</div><div style="float: right;">'
					+'<i title="截至日期" class="glyphicon glyphicon-calendar"></i>'+item.enddate+'</div></p><br>'
					+'<div class="progress" style="margin-bottom: 4px;">'
					+'<div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="'+item.completion+'" aria-valuemin="0" aria-valuemax="100" style="width: '+item.completion+'%">'
					+'<span></span></div></div></div></div></td>'
					+'<td style="vertical-align: middle;">'+item.money+'.00</td>'
					+'<td style="vertical-align: middle;">'+status+'</td>'
					+'<td style="vertical-align: middle;">'
					+'<div class="btn-group-vertical" role="group" aria-label="Vertical button group">'
					+'<button type="button" class="btn btn-default" onclick="preview('+item.id+')">项目预览</button>'
					+ btn
					+'</div></td></tr>';
					
				});
				
				var content = '<div class="col-md-12 column" style="padding: 0; margin-top: 10px;">'
					+'<table class="table table-bordered" style="text-align: center;">'
					+'<thead><tr style="background-color: #ddd;">'
					+'<td>项目信息</td>'
					+'<td width="120">募集金额（元）</td>'
					+'<td width="80">当前状态</td>'
					+'<td width="120">操作</td>'
					+'</tr></thead><tbody>'+body+'</tbody></table></div>';
				
				tabpanel.append(content);
				
			}else{
				layer.msg(result.message,{time:1000,icon:5,shift:6})
			}
		},
		error:function(result){
			layer.msg(result.message,{time:1000,icon:5,shift:6})
		}
	})
}

//取消关注
function RemoveConcerns(proid) {
	$.ajax({
		url:"/homepage/cancel.do",
		type:"post",
		data:{
			"proId":proid
		},
		success:function(result){
			if (result.status == 200) {
				//重新加载
				myFollow();
			}else{
				layer.msg(result.message,{time:1000,icon:5,shift:6})
			}
		},
		error:function(result){
			layer.msg(result.message,{time:1000,icon:5,shift:6})
		}
	})
}

//删除项目
function delProject(proid) {
	$.ajax({
		url:"/record/delPro.do",
		type:"post",
		data:{
			"proId":proid
		},
		success:function(result){
			if (result.status == 200) {
				//重新加载
				Interaction(0);
			}else{
				layer.msg(result.message,{time:1000,icon:5,shift:6});
			}
		},
		error:function(result){
			layer.msg(result.message,{time:1000,icon:5,shift:6});
		}
	})
}


//项目预览
function preview(proid) {
	layer.open({
		type: 2, 
		area: ['1100px', '600px'],
		title:'项目预览',
		content: 'http://28r30857o8.zicp.vip/record/'+proid+'/preview.htm'
	}); 
}

//我支持的/分类显示
$("#payStatus li").click(function () {
	var status = $("#payStatus li").index($(this));
	OrderInteraction(status)
})


//订单ajax交互
function OrderInteraction(status) {
	$.ajax({
		url:"/record/mysupport.do",
		type:"post",
		data:{
			"status":status
		},
		success:function(result){
			if (result.status == 200) {
				var content = '';
				$("#support .tab-pane").eq(status).empty();
				$.each(result.message,function(index,item){
					var sInfo = '';
					if (item.status == 1) {
						sInfo = "已付款";
					}else if (item.status == 2) {
						sInfo = "未付款";
					}else if (item.status == 3) {
						sInfo = "交易关闭";
					}else if (item.status == 5) {
						sInfo = "已发货";
					}else if (item.status == 6) {
						sInfo = "已收货";
					}
					 content += '<tr>'
                        +'<td style="vertical-align:middle;">'
                        +'<div class="thumbnail">'
                        +'<div class="caption">'
                        +'<h4>'+item.project.name+'</h4>'
                        +'<p>订单编号:'+item.ordernum+'</p><p>'
                        +'<div style="float:left;"><i class="glyphicon glyphicon-screenshot" title="目标金额" ></i> 已完成'+item.project.completion+'% </div>'
                        +'<div style="float:right;"><i title="截至日期" class="glyphicon glyphicon-calendar"></i> '+item.project.enddate+' </div>'
                        +'</p><br>'
                        +'<div class="progress" style="margin-bottom: 4px;">'
                        +'<div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="'+item.project.completion+'" aria-valuemin="0" aria-valuemax="100" style="width: '+item.project.completion+'%">'
                        +'</div></div></div></div></td>'
                        +'<td style="vertical-align:middle;">'+item.createdate+'</td>'
                        +'<td style="vertical-align:middle;">'+item.money+'.00<br>(运费：'+item.tReturn.freight+'.00 )</td>'
                        +'<td style="vertical-align:middle;">'+item.rtncount+'</td>'
                        +'<td style="vertical-align:middle;">'+sInfo+'</td>'
                        +'<td style="vertical-align:middle;">'
                        +'<div class="btn-group-vertical" role="group" aria-label="Vertical button group">'
                        +'<button type="button" class="btn btn-default" onclick="delOrder('+item.id+','+status+')">删除订单</button>'
                        +'<button type="button" class="btn btn-default" onclick="toDetail('+item.id+')">交易详情</button>'
                        +'</div></td></tr>';
				});
				var head = '<div class="col-md-12 column" style="margin-top: 10px; padding: 0;">'
					+'<table class="table table-bordered" style="text-align: center;">'
					+'<thead>'
					+'<tr style="background-color: #ddd;">'
					+'<td>项目信息</td>'
					+'<td width="90">支持日期</td>'
					+'<td width="120">支持金额（元）</td>'
					+'<td width="80">回报数量</td>'
					+'<td width="80">交易状态</td>'
					+'<td width="120">操作</td>'
					+'</tr>'
					+'</thead>'
					+'<tbody>'+content+'</tbody>'
					+'</table>'
					+'</div>';
				$("#support .tab-pane").eq(status).append(head);
			}else{
				layer.msg(result.message,{time:1000,icon:5,shift:6});
			}
		},
		error:function(result){
			layer.msg(result.message,{time:1000,icon:5,shift:6});
		}
	})
}

function toDetail(orderid) {
	window.open("/record/"+orderid+"/orderdetail.htm");
}

//删除订单
function delOrder(id,status) {
	$.ajax({
		url:"/record/delOrder.do",
		type:"post",
		data:{
			"id":id
		},
		success:function(result){
			if (result.status == 200) {
				//重新加载
				OrderInteraction(status);
			}else{
				layer.msg(result.message,{time:1000,icon:5,shift:6});
			}
		},
		error:function(result){
			layer.msg(result.message,{time:1000,icon:5,shift:6});
		}
	})
}