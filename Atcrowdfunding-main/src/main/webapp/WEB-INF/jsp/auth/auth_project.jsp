<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>项目审核</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/main.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
	</style>
</head>
 <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 项目审核</a></div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="padding-top:8px;">
				<jsp:include page="/WEB-INF/jsp/common/userInfo.jsp"></jsp:include>
			</li>
            <li style="margin-left:10px;padding-top:8px;">
				<button type="button" class="btn btn-default btn-danger">
				  <span class="glyphicon glyphicon-question-sign"></span> 帮助
				</button>
			</li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
			<div class="tree">
				<jsp:include page="/WEB-INF/jsp/common/menu.jsp"></jsp:include>
			</div>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<div class="panel panel-default">
			  <div class="panel-heading">
				<h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
			  </div>
			  <div class="panel-body">
<form class="form-inline" role="form" style="float:left;">
  <div class="form-group has-feedback">
    <div class="input-group">
      <div class="input-group-addon">查询条件</div>
      <input class="form-control has-success" type="text" placeholder="请输入查询条件">
    </div>
  </div>
  <button type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
                  <th>项目名称</th>
                  <th>发起人</th>
                  <th>所属公司</th>
                  <th>目标金额（元）</th>
                  <th>众筹周期(天)</th>
                  <th>创建时间</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody>
               
            </table>
          </div>
			  </div>
			</div>
        </div>
      </div>
    </div>

    <script src="${APP_PATH }/jquery/jquery-2.1.1.min.js"></script>
    <script src="${APP_PATH }/bootstrap/js/bootstrap.min.js"></script>
	<script src="${APP_PATH }/script/docs.min.js"></script>
	<script src="${APP_PATH }/jquery/layer/layer.js"></script>
        <script type="text/javascript">
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
			    
			    proList();
            });
            
            //异步加载项目审核列表
            var loadingIndex = -1;
            function proList() {
            	$.ajax({
    				url:"${APP_PATH}/auth/proList.do",
    				type:"post",
    				beforeSend:function(){
    					loadingIndex = layer.load(2,{time:10*1000});
    					return true;
    				},
    				success:function(result){
    					layer.close(loadingIndex);
    					if (result.status == 200) {
    	    				//拼接项目列表字符串
    	    				$("tbody").empty();
    	    				$.each(result.message,function(index,item){
    	    					console.log(item);
    	    					var content = '<tr>'
    	    		                  +'<td>'+(index+1)+'</td>'
    	    		                  +'<td>'+item.name+'</td>'
    	    		                  +'<td>'+item.member.realname+'</td>'
    	    		                  +'<td>'+item.projectComp.compna+'</td>'
    	    		                  +'<td>'+item.money+'.00</td>'
    	    		                  +'<td>'+item.day+'</td>'
    	    		                  +'<td>'+item.createdate+'</td>'
    	    		                  +'<td>'
    	    		                  +'   <button type="button" onclick="showInfo('+item.id+')" class="btn btn-success btn-xs"><i class="glyphicon glyphicon-eye-open"></i></button>'
    	    						  +'</td>'
    	    		                +'</tr>';
  							$("tbody").append(content);	
    	    				})
    	    				
    					}else{
    						layer.msg("数据加载失败，请稍后再试",{time:1000,icon:5,shift:6})
    					}
    				},
    				
    				error:function(){
    					layer.msg("数据加载失败，请稍后再试",{time:1000,icon:5,shift:6})
    				}
    			})
			}
            
            //查看详细项目信息
            function showInfo(id) {
            	window.location.href="/auth/"+id+"/showInfo.do"
			}
        </script>
  </body>
</html>