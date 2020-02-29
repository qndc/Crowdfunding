<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>项目分类</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
<link rel="icon" href="${APP_PATH }/img/crowdfunding.png" sizes="32x32" type="image/png">
	<link rel="stylesheet" href="${APP_PATH }/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/font-awesome.min.css">
	<link rel="stylesheet" href="${APP_PATH }/css/main.css">
	<style>
	.tree li {
        list-style-type: none;
		cursor:pointer;
	}
	.tag{
		display:inline-block;
		background-color: #ecf5ff;
		color: #409EFF;
		border: 1px solid #d9ecff;
		margin-right: 2px;
		margin-bottom: 2px;
	}
	.tag-btn{
		padding: 3px;
		border-radius: 50%;
		cursor: pointer;
	}
	.tag-btn:hover{
		background-color: #409EFF;
		color: #fff;	
	}
	td span:last-of-type{
		cursor: pointer;
	}
	td span:last-of-type i {
		padding: 3px;
	}
	.modal-header,.modal-footer{
		padding: 8px
	}
	</style>
</head>
 <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
      <div class="container-fluid">
        <div class="navbar-header">
          <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 项目分类</a></div>
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
      <input class="form-control has-success" type="text" placeholder="请输入查询条件" id="queryContent">
    </div>
  </div>
  <button type="button" class="btn btn-warning" id="query"><i class="glyphicon glyphicon-search"></i>查询</button>
</form>
<button type="button" class="btn btn-danger" style="float:right;margin-left:10px;" id="deleteAll"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='${APP_PATH}/projecttype/toAdd.htm'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input type="checkbox" id="allChoose"></th>
                  <th width="200">分类名称</th>
                  <th width="250">简介</th>
                  <th>标签</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody>
                
              </tbody>
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
			    
			    queryPage(); 
            });
            
            /* 查找 */
            $("#query").click(function () {
				content = $("#queryContent").val();
				queryPage(content);
			})
            
            /* 全选/取消全选 */
            $("#allChoose").click(function () {
            	var status = this.checked;
    			$("tbody tr td input[type='checkbox']").prop("checked",status);
			})
			
			$("#deleteAll").click(function () {
				var idArr = "";
				var allChecked = $("tbody tr td input:checked");
				if (allChecked.length == 0) {
					layer.msg("至少选择一个用户进行删除！",{time:1000,icon:5,shift:6})
					return false;	
				}
				$.each(allChecked,function(index,item){
					idArr += ( "id=" + item.id + "&");
				})
				idArr = idArr.substring(0,idArr.length-1);
				layer.confirm("确定批量删除分类吗？",{icon:3,title:'提示'},function(cindex){
					$.ajax({
						url:"${APP_PATH}/projecttype/deleteAll.do",
						type:"POST",
						data:idArr,
						beforeSend:function(){
							return true;
						},
						success:function(result){
							layer.close(loadingIndex);
							if (result.status == 200) {
								queryPage();
							}else {
								layer.msg(result.message,{time:1000,icon:5,shift:6})
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
            
        /* 异步加载分类列表 */
		var loadingIndex = -1;
		function queryPage(content) {
			$.ajax({
				url:"${APP_PATH}/projecttype/all.do",
				type:"post",
				data:{
					content:content
				},
				beforeSend:function(){
					loadingIndex = layer.load(2,{time:10*1000});
					return true;
				},
				success:function(result){
					layer.close(loadingIndex);
					if (result.status == 200) {
	    				$("tbody").empty();
	    				
	    				$.each(result.message,function(index,item){
	    					//拼接标签
	    					var tags = '';
	    					$.each(item.tags,function(index,tag){
	    						tags += '<span class="tag label label-info">'+tag.name+'<i class="glyphicon glyphicon-remove tag-btn" onclick="deleteTag('+tag.id +','+item.id +',$(this))"></i></span>';
	    					})
	    					//拼接列表
	    					var content = '<tr>'
	    		                  +'<td>'+(index+1)+'</td>'
	    		                  +'<td><input type="checkbox" id="'+item.id +'"></td>'
	    		                  +'<td>'+item.name+'</td>'
	    		                  +'<td>'+item.remark+'</td>'
	    		                  +'<td>'+tags+'<span class="tag label label-info" onclick="addTag('+item.id +')"><i class="glyphicon glyphicon-plus"></i>添加</span></td>'
	    		                  +'<td>'
	    		                  +'  <button type="button" class="btn btn-primary btn-xs" onclick="window.location.href=\'${APP_PATH}/projecttype/toUpdate.htm?id='+item.id+'\'"><i class=" glyphicon glyphicon-pencil"></i></button>'
	    		                  +'  <button type="button" class="btn btn-danger btn-xs" onclick="deleteById('+item.id+')"><i class=" glyphicon glyphicon-remove"></i></button>'
	    		                  +'</td>'
	    		                  +'</tr>';
							$("tbody").append(content);	
	    				});
	    				/* 
	    				*/
					}else{
						layer.msg(result.message,{time:1000,icon:5,shift:6})
					}
				},
				
				error:function(){
					layer.msg("加载数据失败",{time:1000,icon:5,shift:6})
				}
			})
		}

	/* 删除 */
	function deleteById(fid) {
		layer.confirm("确定删除该分类吗？",{icon:3,title:'提示'},function(cindex){
			
			$.ajax({
				url:"${APP_PATH}/projecttype/deleteById.do",
				type:"POST",
				data:{
					id:fid
				},
				beforeSend:function(){
					return true;
				},
				success:function(result){
					layer.close(loadingIndex);
					if (result.status == 200) {
						queryPage();
					}else {
						layer.msg(result.message,{time:1000,icon:5,shift:6})
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
	}
	
	/* 
	  删除标签
	*/
	function deleteTag(tagId,typeId,obj) {
		layer.confirm("确定删除该标签吗？",{icon:3,title:'提示'},function(cindex){
			$.ajax({
				url:"${APP_PATH}/projecttype/deleteTag.do",
				type:"POST",
				data:{
					tagId:tagId,
					typeId:typeId
				},
				beforeSend:function(){
					return true;
				},
				success:function(result){
					layer.close(loadingIndex);
					if (result.status == 200) {
						obj.parent().remove();
					}else {
						layer.msg(result.message,{time:1000,icon:5,shift:6})
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
	}
	
	/* 添加标签 */
	function addTag(typeId) {
		
		var temp = typeId;

		layer.prompt({
		        formType: 3,
		        value: '',
		        title: '添加标签',
		        area: ['300px', '50px'] //自定义文本域宽高
		    }, function(value, index, elem){
		        //此处异步提交弹窗数据，添加标签
		        $.ajax({
					url:"${APP_PATH}/projecttype/addTag.do",
					type:"POST",
					data:{
						content:value,
						typeId:temp
					},
					beforeSend:function(){
						return true;
					},
					success:function(result){
						layer.close(loadingIndex);
						if (result.status == 200) {
							layer.close(index); 
							queryPage();
						}else {
							layer.msg(result.message,{time:1000,icon:5,shift:6})
						}
					},
					error:function(result){
						layer.msg(result.message,{time:1000,icon:5,shift:6})
					}
				})
		    });
		
	}
	
   </script>
   
   
  </body>
</html>