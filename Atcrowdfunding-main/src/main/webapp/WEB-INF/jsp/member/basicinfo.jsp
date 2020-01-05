<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/theme.css">
	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrapValidator.css">
	<style>
#footer {
    padding: 15px 0;
    background: #fff;
    border-top: 1px solid #ddd;
    text-align: center;
}
	</style>
</head>
<body>
 <div class="navbar-wrapper">
      <div class="container">
			<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			  <div class="container">
				<div class="navbar-header">
				  <a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a>
				</div>
            <div id="navbar" class="navbar-collapse collapse" style="float:right;">
              <ul class="nav navbar-nav">
                <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i> ${sessionScope.member.username }<span class="caret"></span></a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="member.html"><i class="glyphicon glyphicon-scale"></i> 会员中心</a></li>
                    <li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
                    <li class="divider"></li>
                    <li><a href="${APP_PATH }/logout.do"><i class="glyphicon glyphicon-off"></i> 退出系统</a></li>
                  </ul>
                </li>
              </ul>
            </div>
			  </div>
			</nav>

      </div>
    </div>

    <div class="container theme-showcase" role="main">
      <div class="page-header">
        <h1>实名认证 - 申请</h1>
      </div>

		<ul class="nav nav-tabs" role="tablist">
		  <li role="presentation" class="active"><a href="#"><span class="badge">1</span> 基本信息</a></li>
		  <li role="presentation"><a href="#"><span class="badge">2</span> 资质文件上传</a></li>
		  <li role="presentation"><a href="#"><span class="badge">3</span> 邮箱确认</a></li>
		  <li role="presentation"><a href="#"><span class="badge">4</span> 申请确认</a></li>
		</ul>
        
		<form role="form" style="margin-top:20px;" id="basicInfoForm">
		  <div class="form-group">
			<label for="realname">真实名称</label>
			<input type="text" class="form-control" id="realname" value="${member.realname }" name="realname" placeholder="请输入真实名称">
		  </div>
		  <div class="form-group">
			<label for="cardnum">身份证号码</label>
			<input type="text" class="form-control" id="cardnum" value="${member.cardnum }" name="cardnum" placeholder="请输入身份证号码">
		  </div>
		  <div class="form-group">
			<label for="tel">电话号码</label>
			<input type="text" class="form-control" id="tel" value="${member.tel }" name="tel" placeholder="请输入电话号码">
		  </div>
          <button type="button" onclick="pre()" class="btn btn-default">上一步</button>
		  <button type="submit"  class="btn btn-success">下一步</button>
		</form>
		<hr>
    </div> <!-- /container -->
        <div class="container" style="margin-top:20px;">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <div id="footer">
                        <div class="footerNav">
                             <a rel="nofollow" href="http://www.atguigu.com">关于我们</a> | <a rel="nofollow" href="http://www.atguigu.com">服务条款</a> | <a rel="nofollow" href="http://www.atguigu.com">免责声明</a> | <a rel="nofollow" href="http://www.atguigu.com">网站地图</a> | <a rel="nofollow" href="http://www.atguigu.com">联系我们</a>
                        </div>
                        <div class="copyRight">
                            Copyright ?2017-2017 atguigu.com 版权所有
                        </div>
                    </div>
                    
                </div>
            </div>
        </div>
    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script src="${APP_PATH }/jquery/layer/layer.js"></script>
	<script src="${APP_PATH }/bootstrap/js/bootstrapValidator.js"></script>
    
	<script>
	
	$(function () {
	    const bootstrapValidator = $('form').bootstrapValidator({
	        message: 'This value is not valid',
	        /*所有需要用到的图标*/
	        feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        fields: {
	        	realname: {
	                message: '真实名称验证失败',
	                validators: {
	                    notEmpty: {
	                        message: '真实名称不能为空'
	                    },
	                    regexp: {
	                        regexp: /^[\u2E80-\u9FFF]+$/,
	                        message: '真实名称格式不正确'
	                    }
	                }
	            },
	            cardnum: {
	                validators: {
	                    notEmpty: {
	                        message: '身份证号码不能为空'
	                    },
	                    stringLength: {
	                        min: 18,
	                        max: 18,
	                        message: '请输入18位身份证号码'
	                    },
	                    regexp: {
	                        regexp: /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/,
	                        message: '身份证号码格式不正确'
	                    }
	                }
	            },
	            tel: {
	                validators: {
	                    notEmpty: {
	                        message: '手机号码不能为空'
	                    },
	                    stringLength: {
	                        min: 11,
	                        max: 11,
	                        message: '请输入11位手机号码'
	                    },
	                    regexp: {
	                        regexp: /^1[3|5|8]{1}[0-9]{9}$/,
	                        message: '手机号码格式不正确'
	                    }
	                }
	            },
	        },
	    }).on("success.form.bv", function (e) {
	        //阻止表单提交刷新页面
	        e.preventDefault();
	        //获取表单实例
	        const $form = $(e.target);
	        //获取表单验证器实例
	        const bv = $form.data(bootstrapValidator);
	        //使用ajax提交表单数据，提交至form标签中的action，result自定义
	        $.post("${APP_PATH}/member/updateBasicInfo.do", $("#basicInfoForm").serialize(), function (data, status) {
	        	if (data.status == 200) {
					window.location.href = "${APP_PATH}/member/toAcctType.htm";
				}else {
					layer.msg(data.message,{time:1000,icon:5,shift:6})
				}
	        });
	    });
	});
	
        $('#myTab a').click(function (e) {
          e.preventDefault()
          $(this).tab('show')
        });    
	</script>
  </body>
</html>