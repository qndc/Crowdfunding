<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/login.css">
	<link rel="icon" href="${APP_PATH }/img/crowdfunding.png" sizes="32x32" type="image/png">
	<title>登录</title>
  </head>
  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <div><a class="navbar-brand" href="/" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
      </div>
    </nav>

    <div class="container">
		
      <form id="loginForm" action="${APP_PATH }/doLogin.do" method="POST" class="form-signin" role="form">
      	${exception.message }
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户登录</h2>
		  <div class="form-group has-success has-feedback">
			<input type="text" class="form-control" id="loginacct" name="loginacct"  placeholder="请输入登录账号" autofocus>
			<span class="glyphicon glyphicon-user form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<input type="password" class="form-control" id="userpswd" name="userpswd" placeholder="请输入登录密码" style="margin-top:10px;">
			<span class="glyphicon glyphicon-lock form-control-feedback"></span>
		  </div>
		  <div class="form-group has-success has-feedback">
			<select id="type" class="form-control" name="type">
                <option value="member" selected>会员</option>
                <option value="admin">管理</option>
            </select>
		  </div>
        <div class="checkbox">
          <label>
            <input id="rememberme" type="checkbox" value="1"> 记住我两周
          </label>
          <br>
          <label>
            忘记密码
          </label>
          <label style="float:right">
            <a href="${APP_PATH }/reg.htm">我要注册</a>
          </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" onclick="dologin()" > 登录</a>
      </form>
    </div>
    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
    <script src="${APP_PATH }/jquery/layer/layer.js"></script>
    <script>
    
    /* 异步登录提交请求 */
    function dologin() {
    	var loginacct = $("#loginacct");
    	var userpswd = $("#userpswd");
    	var ftype = $("#type");
  		var flag = $("#rememberme")[0].checked;	//检测是否被选中
    	
    	if ($.trim(loginacct.val()) == "") {
			layer.msg("登录用户名不能为空，请重新输入!",{time:1000,icon:5,shift:6},function(){
				loginacct.val("");
				loginacct.focus();
			})
			return false;
		}
    	
    	$.ajax({
    		type:"POST",
    		data:{
    			"loginacct":loginacct.val(),
		    	"userpswd":userpswd.val(),
		    	"ftype":ftype.val(),
		    	"rememberme":flag?1:0
    		},
    		url:"${APP_PATH}/doLogin.do",
    		beforeSend:function(){
    			//一般做表单验证
    			return true;
    		},    		
    		success:function(result){
    			
    			if (result.status == 200) {
    				if (result.message == "member") {
    					window.location.href = "${APP_PATH}/member.htm";
					}else if(result.message == "admin"){
						window.location.href = "${APP_PATH}/main.htm";
					}else {
						layer.msg(result.message,{time:1000,icon:5,shift:6})
					}	
				}else{
					layer.msg(result.message,{time:1000,icon:5,shift:6})
				}
    		}
    	})
        
    }
    </script>
  </body>
</html>