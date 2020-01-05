<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/login.css">
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <div><a class="navbar-brand" href="${APP_PATH }/index.htm" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
      </div>
    </nav>

    <div class="container">

      <form class="form-signin" role="form" id="signForm">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户注册</h2>
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control" id="tel" name="tel"  placeholder="请输入手机号码" autofocus>
			<span class="glyphicon glyphicon-user form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<input type="password" class="form-control" id="userpswd" name="userpswd"  placeholder="请输入登录密码" style="margin-top:10px;">
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback" style="position: absolute;">
			<input type="text" class="form-control" id="code" name="code" style="width: 180px;float: left;" placeholder="请输入验证码" style="margin-top:10px;">
			<button type="button" class="btn btn-lg btn-success btn-block"  style="width: 120px;float: right;font-size: 12px;margin-top: 4px;padding-left: -10px" id="sendCodeBtn"> 点击获取</button>
		  </div>
        <div class="form-group has-success has-feedback" style="position: relative;margin-top: 70px">
        	<label style="float:left;">
            <a href="javascript:void(0)">忘记密码?</a>
          </label>
        	<label style="float:right">
            <a href="${APP_PATH }/login.htm">我有账号</a>
          </label>
			 <button class="btn btn-lg btn-success btn-block" style="margin-top: 10px" id="sub" type="button"> 注册</button>
		 </div>
       
      </form>
    </div>
    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
    <script src="${APP_PATH }/jquery/layer/layer.js"></script>
    <script type="text/javascript">
    
    	//发送验证码
    	$("#sendCodeBtn").click(function () {
    		var tel = $("#tel").val().trim();
    		if(tel != ""){
    			$.ajax({
    				url:"${APP_PATH}/sendCode.do",
    				type:"post",
    				data:{
    					"tel":tel
    				},
    				success:function(result){
    					if (result.status == 200) {
    						resetTime($("#sendCodeBtn"));
    						layer.msg("短信验证码发送成功，请注意查收",{time:2000,icon:6,shift:6});
    						
    					}else {
    						layer.msg("验证码发送异常，请稍后再试",{time:1000,icon:5,shift:6});
    					}
    				},
    				error:function(result){
    					layer.msg("验证码发送异常，请稍后再试",{time:1000,icon:5,shift:6})
    				}
    			})
    		}else {
    			layer.msg("电话号码不能为空",{time:1500,icon:5,shift:6}); 
			} 
		})
		
		//注册用户
		$("#sub").click(function () {
    		if($("#tel").val().trim() != "" && $("#userpswd").val().trim() != "" && $("#code").val().trim() != ""){
    			$.ajax({
    				url:"${APP_PATH}/signUp.do",
    				type:"post",
    				data:$("#signForm").serialize(),
    				success:function(result){
    					if (result.status == 200) {
    						layer.msg("注册成功，请登录",{time:2000,icon:6,shift:6});
    						
    					}else {
    						layer.msg(result.message,{time:1000,icon:5,shift:6});
    					}
    				},
    				error:function(result){
    					layer.msg(result.message,{time:1000,icon:5,shift:6});
    				}
    			}) 
    		}else {
    			layer.msg("注册信息不能为空",{time:1500,icon:5,shift:6}); 
			} 
		})
		
		//异步校验电话号码是否已经注册
		$("#tel").blur(function () {
			var tel = $(this).val().trim();
			$.ajax({
				url:"${APP_PATH}/check.do",
				type:"post",
				data:{
					"tel":tel
				},
				success:function(result){
					console.log(result);
					if (result.status == 200) {
						$("#sub").attr('disabled',false);
						layer.msg(result.message,{time:2000,icon:6,shift:6});
					}else {
						$("#sub").attr('disabled',true);
						layer.msg(result.message,{time:1000,icon:5,shift:6});
					}
				},
				error:function(result){
					layer.msg(result.message,{time:1000,icon:5,shift:6});
				}
			})
		})
    	
    	/**
    	 * 倒计时
    	 * e 代表发送按钮对象
    	 */
    	function resetTime(e){
    	    var second = 60;
    	    var timer = null;
    	    timer = setInterval(function(){
    	        second -= 1;
    	        if(second >0){
    	            $(e).attr('disabled',true);
    	            $(e).text(second + "秒后重新获取");
    	        }else{
    	            clearInterval(timer);
    	            $(e).attr('disabled',false);
    	            $(e).text("点击获取");
    	        }
    	    },1000);
    	}
    </script>
</body>
</html>